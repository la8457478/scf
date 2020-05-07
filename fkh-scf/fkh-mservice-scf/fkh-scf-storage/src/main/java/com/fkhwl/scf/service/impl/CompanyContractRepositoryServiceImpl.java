package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.CompanyContractDao;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.scf.service.CompanyContractRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 主体合同：资方与借款方签订的合同 服务接口实现类 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.03 15:12
 */
@Slf4j
@Service
public class CompanyContractRepositoryServiceImpl extends BaseServiceImpl<CompanyContractDao, CompanyContract> implements CompanyContractRepositoryService {
    @Override
    public IPage<CompanyContractDTO> findPage(IPage<CompanyContractDTO> page, Map<String, Object> params) {
        return baseMapper.findPage(page, params);
    }
    @Override
    public  CompanyContract findInfoByCompanyId(Long companyCapitalId,Long companyBorrowerId){
        return baseMapper.findInfoByCompanyId( companyCapitalId,companyBorrowerId);
    }
    /**
     * 修改分项额度和融资比例
     * @param companyContract
     */
    @Override
    public int updateBaseConfig(CompanyContract companyContract){
       return baseMapper.updateBaseConfig(companyContract);
    }
    /**
     * 修改客户的已分配分项额度，和剩余可分配分项额度
     * @param companyContract
     */
    @Override
    public int updateSubitemBalance(CompanyContract companyContract){
       return baseMapper.updateSubitemBalance(companyContract);
    }
    @Override
    public int review(Map<String, Object> params){
        return baseMapper.review(params);
    }

    @Override
    public int updateLoanBalance(Long id, BigDecimal applyBalance) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("applyBalance",applyBalance);
        return baseMapper.updateLoanBalance(map);
    }

    @Override
    public int updateRepayBalance(Long id, BigDecimal repayBalance) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("repayBalance",repayBalance);
        return baseMapper.updateRepayBalance(map);    }
}
