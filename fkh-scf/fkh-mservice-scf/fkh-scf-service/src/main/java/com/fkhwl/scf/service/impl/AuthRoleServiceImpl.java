package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.po.AuthRole;
import com.fkhwl.scf.entity.po.AuthRoleFunc;
import com.fkhwl.scf.enums.AuthRoleTypeEnum;
import com.fkhwl.scf.service.AuthRoleFuncRepositoryService;
import com.fkhwl.scf.service.AuthRoleRepositoryService;
import com.fkhwl.scf.service.AuthRoleService;
import com.fkhwl.scf.utils.Const;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.AuthRoleServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;

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
 * <p>Description: 角色表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthRoleServiceImpl implements AuthRoleService {

    private final AuthRoleRepositoryService authRoleRepositoryService;

    private final AuthRoleFuncRepositoryService authRoleFuncRepositoryService;

    @Override
    public IPage<AuthRoleDTO> listPage(IPage<AuthRoleDTO> page, Map<String, Object> params) {
        return authRoleRepositoryService.listPage(page, params);
    }

    @Override
    public AuthRoleDTO getDetail(Long id) {
        //todo add-by chenli 异常机制
        AuthRoleDTO authRoleDTO = AuthRoleServiceConverterWrapper.INSTANCE.dto(authRoleRepositoryService.getById(id));
        //获取“权限id”
        List<AuthRoleFunc> authRoleFuncs = authRoleFuncRepositoryService.listByRoleId(id);
        if (BaseValidate.errorList(authRoleFuncs)) {
            StringBuilder funcIds = new StringBuilder();
            for (AuthRoleFunc item : authRoleFuncs) {
                funcIds.append(item.getAuthFuncId()).append(Const.COMMA_CHAR);
            }
            authRoleDTO.setFuncIds(funcIds.substring(0, funcIds.length() - 1));
        }

        return authRoleDTO;
    }

    @Transactional
    @Override
    public void saveOrUpdate(AuthRoleDTO authRoleDTO) {
        //todo add-by chenli 添加权限验证：角色管理
//        Date currentDate = new Date();
        AuthRole authRole = AuthRoleServiceConverterWrapper.INSTANCE.po(authRoleDTO);
//        authRole.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(authRoleDTO.getId())) {//页面新增的类型只能为：普通类角色
            authRole.setRoleType(AuthRoleTypeEnum.NORMAL.getValue());
            authRole.setDeleted(DeleteEnum.N);
//            authRole.setCreateTime(currentDate);
        }
        authRoleRepositoryService.saveOrUpdate(authRole);

        //回填id
        authRoleDTO.setId(authRole.getId());
        //保存“角色-权限关系表”
        saveOrUpdateRoleFunc(authRoleDTO);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //逻辑删除角色
        authRoleRepositoryService.removeById(id);
        Map<String, Object> params = new HashMap<>();
        params.put("updateTime", ToolsHelper.getCurrentDate());
        params.put("deleted", DeleteEnum.Y.getValue());
        params.put("authRoleId", id);
        //逻辑删除“角色-权限关系表”
        authRoleFuncRepositoryService.updateDeletedByParams(params);
    }

    /**
     *  保存“角色-权限关系表”
     */
    private void saveOrUpdateRoleFunc(AuthRoleDTO authRoleDTO) {
        List<Long> funcIds = ToolsHelper.removeReplaceLong(authRoleDTO.getFuncIds(), Const.COMMA_CHAR);
        List<AuthRoleFunc> dbAuthRoleFuncs = authRoleFuncRepositoryService.listByRoleId(authRoleDTO.getId());
        List<AuthRoleFunc> addAuthRoleFuncs = new ArrayList<>();
        List<Long> deleteIds = null;
        List<Long> editIds = null;
        List<Long> dbFuncIds;
        Date currentDate = ToolsHelper.getCurrentDate();
        if (!BaseValidate.errorList(dbAuthRoleFuncs)) {
            deleteIds = new ArrayList<>();
            editIds = new ArrayList<>();
            dbFuncIds = new ArrayList<>();
            for (AuthRoleFunc item : dbAuthRoleFuncs) {
                dbFuncIds.add(item.getAuthFuncId());
                //删除
                if (!funcIds.contains(item.getAuthFuncId())) {
                    deleteIds.add(item.getId());
                } else {//编辑
                    if (DeleteEnum.Y == item.getDeleted()) {
                        editIds.add(item.getId());
                    }
                }
            }
            //新增
            for (Long funcId : funcIds) {
                if (!dbFuncIds.contains(funcId)) {
                    addAuthRoleFuncs.add(generateAuthRoleFunc(authRoleDTO, currentDate, funcId));
                }
            }
        } else {
            //新增
            for (Long funcId : funcIds) {
                addAuthRoleFuncs.add(generateAuthRoleFunc(authRoleDTO, currentDate, funcId));
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("updateTime", currentDate);
        if (!BaseValidate.errorList(deleteIds)) {//逻辑删除
            params.put("ids", deleteIds);
            params.put("deleted", DeleteEnum.Y.getValue());
            authRoleFuncRepositoryService.updateDeletedByParams(params);
        }
        if (!BaseValidate.errorList(editIds)) {//编辑
            params.put("ids", editIds);
            params.put("deleted", DeleteEnum.N.getValue());
            authRoleFuncRepositoryService.updateDeletedByParams(params);
        }
        if (!BaseValidate.errorList(addAuthRoleFuncs)) {//新增
            authRoleFuncRepositoryService.saveBatch(addAuthRoleFuncs);
        }
    }

    /**
     *  生成“角色-权限关系表”
     */
    @NotNull
    private AuthRoleFunc generateAuthRoleFunc(AuthRoleDTO authRoleDTO, Date currentDate, Long funcId) {
        AuthRoleFunc authRoleFunc = new AuthRoleFunc();
        authRoleFunc.setAuthFuncId(funcId);
        authRoleFunc.setAuthRoleId(authRoleDTO.getId());
        authRoleFunc.setCreateTime(currentDate);
        authRoleFunc.setUpdateTime(currentDate);
        authRoleFunc.setDeleted(DeleteEnum.N);
        return authRoleFunc;
    }

    @Override
    public AuthRoleDTO getByOwnerIdAndRoleType(Long ownerId, Integer roleType) {
        return authRoleRepositoryService.getByOwnerIdAndRoleType(ownerId, roleType);
    }
}
