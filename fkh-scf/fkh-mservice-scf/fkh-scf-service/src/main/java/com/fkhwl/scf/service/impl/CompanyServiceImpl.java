package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.entity.po.AuthRole;
import com.fkhwl.scf.entity.po.AuthRoleFunc;
import com.fkhwl.scf.entity.po.Company;
import com.fkhwl.scf.entity.po.ScfConfig;
import com.fkhwl.scf.entity.po.ScfUserConfig;
import com.fkhwl.scf.enums.AuthRoleTypeEnum;
import com.fkhwl.scf.enums.CompanyTypeEnum;
import com.fkhwl.scf.enums.ScfConfigEnum;
import com.fkhwl.scf.enums.UserType;
import com.fkhwl.scf.service.AuthRoleFuncRepositoryService;
import com.fkhwl.scf.service.AuthRoleRepositoryService;
import com.fkhwl.scf.service.CompanyRepositoryService;
import com.fkhwl.scf.service.CompanyService;
import com.fkhwl.scf.service.ScfConfigRepositoryService;
import com.fkhwl.scf.service.ScfUserConfigRepositoryService;
import com.fkhwl.scf.service.ScfUserService;
import com.fkhwl.scf.third.service.AnxinSignService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.AuthRoleServiceConverterWrapper;
import com.fkhwl.scf.wrapper.CompanyServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.mybatis.support.Condition;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 部门表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepositoryService companyRepositoryService;

    private final ScfUserService scfUserService;

    private final AuthRoleRepositoryService authRoleRepositoryService;

    private final AuthRoleFuncRepositoryService authRoleFuncRepositoryService;

    private final ScfConfigRepositoryService scfConfigRepositoryService;

    private final ScfUserConfigRepositoryService scfUserConfigRepositoryService;

    private final AnxinSignService anxinSignService;
    @Override
    public IPage<CompanyDTO> listPage(IPage<CompanyDTO> page, Map<String, Object> params) {
        return companyRepositoryService.listPage(page, params);
    }

    @Override
    public CompanyDTO getDetail(Long id) {
        return CompanyServiceConverterWrapper.INSTANCE.dto(companyRepositoryService.getById(id));
    }

    @Transactional
    @Override
    public void saveOrUpdate(CompanyDTO companyDTO) {
        Date currentDate = new Date();
        Company company = CompanyServiceConverterWrapper.INSTANCE.po(companyDTO);
        company.setUpdateTime(currentDate);

        //验证企业名称是否重复
        Map<String, Object> params = new HashMap<>();
        params.put("companyName", companyDTO.getCompanyName());
        CompanyDTO sameNameCompany = companyRepositoryService.getByParams(params);
        if (BaseValidate.errorLong(companyDTO.getId())) {
            AssertUtils.isNull(sameNameCompany, companyDTO.getCompanyName() + "已存在");
            company.setDeleted(DeleteEnum.N);
            company.setCompanyStatus(1);
            company.setCreateTime(currentDate);
            if (CompanyTypeEnum.FUNDING.getValue().equals(companyDTO.getCompanyType())) {
                //生成资金方企业主账号
                generateFundingMasterUser(companyDTO);
            } else {
                //生成借款方企业主账号
                generateBorrowerMasterUser(companyDTO);
            }
            //设置company的ownerId：为页面创新账户的id
            company.setOwnerId(companyDTO.getOwnerId());
            //添加安心签的账号和签章图片
            //注册失败
            String companyAccount=anxinSignService.addCompanyAccount(companyDTO);
            AssertUtils.isTrue(!companyAccount.contains("error") && !companyAccount.contains("{"),"注册安心签账户失败："+companyAccount);
            company.setSignAccountId(companyAccount);
            company.setSignSealData(anxinSignService.createCompanySeal(company.getCompanyName()));
            companyRepositoryService.save(company);
        } else {
            if (sameNameCompany != null && !sameNameCompany.getId().equals(companyDTO.getId())) {
                throw new IllegalArgumentException(companyDTO.getCompanyName() + "已存在");
            }
            companyRepositoryService.updateById(company);
        }
    }

    /**
     * 生成企业主账号, 及其角色信息
     * @param companyDTO
     */
    private void generateFundingMasterUser(CompanyDTO companyDTO) {
        //生成资金方企业的角色信息
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",Integer.MAX_VALUE);
        params.put("ownerId",companyDTO.getCreateOwnerId());
        IPage<AuthRoleDTO> sysRolePage = authRoleRepositoryService.listPage(Condition.getPage(params), params);
        AssertUtils.notEmpty(sysRolePage.getRecords(), "请先创建角色");

        ScfUserDTO masterUser = initScfUser(companyDTO);
        masterUser.setOwnerId(0L);
        masterUser.setUserType(UserType.FUNDING.getValue());
        //特殊角色id,2：资金方企业主账号
        masterUser.setRoleId(2L);
        scfUserService.saveOrUpdate(masterUser);
        //设置company的ownerId：为页面创新账户的id
        companyDTO.setOwnerId(masterUser.getId());

        //生成资金方相关角色
        Date currentDate = new Date();
        for (AuthRoleDTO item : sysRolePage.getRecords()) {
            //特殊角色id：1：平台超级管理员,2：资金方企业主账号,3：客户主账号
            if (item.getId() < 2) {
                continue;
            }
            AuthRole role = AuthRoleServiceConverterWrapper.INSTANCE.po(item);
            role.setId(null);
            role.setOwnerId(masterUser.getId());
            role.setDeleted(DeleteEnum.N);
            role.setCreateTime(currentDate);
            role.setUpdateTime(currentDate);
            authRoleRepositoryService.save(role);
            if (item.getId() == 2) {//资金方企业主账号
                masterUser.setRoleId(role.getId());
                scfUserService.saveOrUpdate(masterUser);
            }

            //生成资金方相关角色-菜单权限关联关系
            List<AuthRoleFunc> authRoleFuncs = authRoleFuncRepositoryService.listByRoleId(item.getId());
            if (!authRoleFuncs.isEmpty()) {
                for (AuthRoleFunc roleFunc : authRoleFuncs) {
                    roleFunc.setId(null);
                    roleFunc.setAuthRoleId(role.getId());
                }
                authRoleFuncRepositoryService.saveBatch(authRoleFuncs);
            }
        }

        //生成资金方用信规则匹配
        List<ScfConfig> creditRegulationList = scfConfigRepositoryService.getConfigsByParentConfigKey(ScfConfigEnum
            .CREDIT_REGULATION_CHECK_LIST.getCacheKey());
        AssertUtils.notEmpty(creditRegulationList, "请先创建用信匹配规则");

        List<ScfUserConfig> userConfigList = new ArrayList<>(creditRegulationList.size());
        for (ScfConfig creditConfig : creditRegulationList) {
            ScfUserConfig scfUserConfig = initScfUserConfig(masterUser, currentDate, creditConfig);
            userConfigList.add(scfUserConfig);
        }

        //生成放款审核配置
        ScfConfig loanReviewConfig = scfConfigRepositoryService.getConfigByConfigKey(ScfConfigEnum
            .LOAN_REVIEW_CONFIG_CHECK.getCacheKey());
        AssertUtils.notNull(loanReviewConfig, "请先创建放款审核配置");
        userConfigList.add(initScfUserConfig(masterUser, currentDate, loanReviewConfig));

        scfUserConfigRepositoryService.saveBatch(userConfigList);
    }

    /**
     * 初始化用户配置
     * @param masterUser
     * @param currentDate
     * @param creditConfig
     * @return
     */
    @NotNull
    private ScfUserConfig initScfUserConfig(ScfUserDTO masterUser, Date currentDate, ScfConfig creditConfig) {
        ScfUserConfig scfUserConfig = new ScfUserConfig();
        scfUserConfig.setOwnerId(masterUser.getId());
        scfUserConfig.setConfigId(creditConfig.getId());
        scfUserConfig.setConfigStatus(creditConfig.getConfigStatus());
        scfUserConfig.setConfigValue("0");
        scfUserConfig.setDeleted(DeleteEnum.N);
        scfUserConfig.setCreateTime(currentDate);
        scfUserConfig.setUpdateTime(currentDate);
        return scfUserConfig;
    }

    /**
     * 生成借款方客户主账号
     * @param companyDTO
     */
    private void generateBorrowerMasterUser(CompanyDTO companyDTO) {
        ScfUserDTO masterUser = initScfUser(companyDTO);
        masterUser.setOwnerId(0L);
        masterUser.setUserType(UserType.BORROWER.getValue());
        AuthRoleDTO role = authRoleRepositoryService.getByOwnerIdAndRoleType(companyDTO.getCreateOwnerId(), AuthRoleTypeEnum.BORROWER.getValue());
        AssertUtils.notNull(role, "请先创建角色");
        //特殊角色id,3：客户主账号
        masterUser.setRoleId(role.getId());
        scfUserService.saveOrUpdate(masterUser);
        companyDTO.setOwnerId(masterUser.getId());
    }

    /**
     * 初始化资金方/借款方用户
     * @param companyDTO
     * @return
     */
    @NotNull
    private ScfUserDTO initScfUser(CompanyDTO companyDTO) {
        ScfUserDTO masterUser = new ScfUserDTO();
        masterUser.setUsername(companyDTO.getOwnerLoginName());
        masterUser.setUserPassword(companyDTO.getUserPassword());
        masterUser.setNickName(companyDTO.getOwnerName());
//        masterUser.setCompanyId(companyDTO.getId());
//        masterUser.setCompanyName(companyDTO.getCompanyName());
        return masterUser;
    }

    @Override
    public void delete(Long id) {
        //逻辑删除企业
        companyRepositoryService.removeById(id);
    }

    @Override
    public List<Long> getBorrowerOwnerIdsByCapital(Long ownerId) {
        return  companyRepositoryService.getBorrowerOwnerIdsByCapital(ownerId);
    }

    @Override
    public CompanyDTO getByOwnerId(Long ownerId) {
        return companyRepositoryService.getByOwnerId(ownerId);
    }

    @Override
    public void updateAccountMobileNos(Long companyId, String mobileNos) {
        Company company = companyRepositoryService.getById(companyId);
        company.setAccountMobileNos(mobileNos);
         companyRepositoryService.updateById(company);
    }
}
