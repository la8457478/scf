package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CompanyContractProvider;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.enums.ScfBaseCodes;
import com.fkhwl.scf.service.CompanyContractService;
import com.fkhwl.scf.wrapper.CompanyContractServiceConverterWrapper;
import com.fkhwl.starter.autoconfigure.exception.BusinessException;
import com.fkhwl.starter.core.support.AssertUtils;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Title: 系统配置dobbu服务实现</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date:
 * updatetime:
 * reason:
 */
@Service
@AllArgsConstructor
public class CompanyContractProviderImpl implements CompanyContractProvider {

    /** User service */
    private final CompanyContractService companyContractService;

    /**
     * Find info template user dto
     *
     * @param username username
     * @return the template user dto
     */
    @Override
    public CompanyContractDTO getByUsername(String username) {
//        return companyContractService.getByUsername(username).orElse(null);
        return null;
    }

    @Override
    public CompanyContractDTO findInfo(Long userId) {
        return companyContractService.findInfo(userId);
    }

    @Override
    public List<CompanyContractDTO> findList() {
        return companyContractService.findList();
    }

    @Override
    public IPage<CompanyContractDTO> findPage(IPage<CompanyContractDTO> page, Map<String, Object> params) {
        return companyContractService.findPage(page, params);
    }

    @Override
    public void save(CompanyContractDTO userDTO) {
        //如果是新增，需要判断资方和借款方合同,是否已经存在
        CompanyContractDTO companyContractDb= companyContractService.findInfoByCompanyId(userDTO.getCompanyCapitalId(),userDTO.getCompanyBorrowerId());
        if(userDTO.getId() == null && companyContractDb != null){
            throw new BusinessException(ScfBaseCodes.COMPANY_BORROWER_EXIST);
        }
        //初始化金额
        userDTO.setRemainingBalance(userDTO.getTotalBalance());
        userDTO.setRemainingSubitemBalance(userDTO.getTotalBalance());
        companyContractService.save(userDTO);
    }
    /**
     * 修改分项额度和融资比例
     * @param userDTO
     */
    @Override
    public void updateBaseConfig(CompanyContractDTO userDTO){
        // 需要校验总额度是否小于已经使用额度
        //检查客户编辑时的额度，不可改的比多个交易对手额度之和，小
       int result= companyContractService.updateBaseConfig(userDTO);
       AssertUtils.isTrue(result>0,"修改失败");
    }

    @Override
    public void delete(List<Long> ids) {
        companyContractService.delete(ids);
    }

    @Override
    public CompanyContractDTO getByCompanyId(Long companyCapitalId, Long companyBorrowerId) {
        return CompanyContractServiceConverterWrapper.INSTANCE.dto( companyContractService.getByCompanyId(companyCapitalId,companyBorrowerId));
    }
    @Override
    public CompanyContractDTO getByBorrowCompanyId(Long companyBorrowerId) {
        return CompanyContractServiceConverterWrapper.INSTANCE.dto( companyContractService.getByBorrowCompanyId(companyBorrowerId));
    }
    /**
     * 修改客户的已分配分项额度，和剩余可分配分项额度
     * @param companyContract
     */
    @Override
    public int updateSubitemBalance(CompanyContractDTO companyContract){
        return companyContractService.updateSubitemBalance(companyContract);
    }
    @Override
    public int review(Map<String, Object> params){
        return companyContractService.review(params);
    }
}
