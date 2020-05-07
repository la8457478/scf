package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.po.Company;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.scf.service.CompanyContractRepositoryService;
import com.fkhwl.scf.service.CompanyContractService;
import com.fkhwl.scf.service.CompanyRepositoryService;
import com.fkhwl.scf.wrapper.CompanyContractServiceConverterWrapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2020.01.10 17:27
 */
@Service
@AllArgsConstructor
public class CompanyContractServiceImpl implements CompanyContractService {

    /** User repository service */
    private final CompanyContractRepositoryService companyContractRepositoryService;
    /** User repository service */
    private final CompanyRepositoryService companyRepositoryService;

    /**
     * Find info optional
     *
     * @param id id
     * @return the optional
     */
    @Override
    public CompanyContractDTO findInfo(Long id) {
        return CompanyContractServiceConverterWrapper.INSTANCE.dto(companyContractRepositoryService.getById(id));
    }

    @Override
    public CompanyContractDTO findInfoByCompanyId(Long companyCapitalId,Long companyBorrowerId) {
        return CompanyContractServiceConverterWrapper.INSTANCE.dto(companyContractRepositoryService.findInfoByCompanyId( companyCapitalId, companyBorrowerId));
    }
    /**
     * Find list list
     *
     * @return the list
     */
    @Override
    public List<CompanyContractDTO> findList() {
        List<CompanyContract> list = companyContractRepositoryService.list();
        return list.stream().map(CompanyContractServiceConverterWrapper.INSTANCE::dto).collect(Collectors.toList());
    }
    /**
     * Find page page
     *
     * @param page   page
     * @param params params
     * @return the page
     */
    @Override
    public IPage<CompanyContractDTO> findPage(IPage<CompanyContractDTO> page, Map<String, Object> params) {
        return companyContractRepositoryService.findPage(page, params);
    }

    /**
     * Save or update *
     *
     * @param companyContractDTO user dto
     */
    @Override
    public void save(CompanyContractDTO companyContractDTO) {
       Company company =  companyRepositoryService.getById(companyContractDTO.getCompanyBorrowerId());
        CompanyContract scfConfig = CompanyContractServiceConverterWrapper.INSTANCE.po(companyContractDTO);
        scfConfig.setCompanyBorrowerName(company.getCompanyName());
        scfConfig.setStatus(0);
        companyContractRepositoryService.save(scfConfig);
    }
    /**
     * 修改管理费率、利率、总额度等非计算类基本信息和配置
     * @param companyContractDTO
     */
    @Override
    public int updateBaseConfig(CompanyContractDTO companyContractDTO){
        CompanyContract scfConfig = CompanyContractServiceConverterWrapper.INSTANCE.po(companyContractDTO);
        return companyContractRepositoryService.updateBaseConfig(scfConfig);
    }
    /**
     * Delete *
     *
     * @param ids ids
     */
    @Override
    public void delete(List<Long> ids) {
        companyContractRepositoryService.removeByIds(ids);
    }

    @Override
    public CompanyContract getByCompanyId(Long companyCapitalId, Long companyBorrowerId) {
        return companyContractRepositoryService.getOne(new LambdaQueryWrapper<CompanyContract>().eq(CompanyContract::getCompanyCapitalId,companyCapitalId).eq(CompanyContract::getCompanyBorrowerId,companyBorrowerId));
    }
    @Override
    public CompanyContract getByBorrowCompanyId(Long companyBorrowerId) {
        return companyContractRepositoryService.getOne(new LambdaQueryWrapper<CompanyContract>().eq(CompanyContract::getCompanyBorrowerId,companyBorrowerId));
    }
    /**
     * 修改客户的已分配分项额度，和剩余可分配分项额度
     */
    @Override
    public int updateSubitemBalance(CompanyContractDTO companyContractDTO){
        CompanyContract scfConfig = CompanyContractServiceConverterWrapper.INSTANCE.po(companyContractDTO);
        return companyContractRepositoryService.updateSubitemBalance(scfConfig);
    }

    @Override
    public int  review(Map<String, Object> params){
        return companyContractRepositoryService.review(params);
    }

}
