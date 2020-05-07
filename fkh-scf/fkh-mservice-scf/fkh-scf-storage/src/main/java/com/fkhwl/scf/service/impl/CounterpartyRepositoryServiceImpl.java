package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.CounterpartyDao;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.service.CounterpartyRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  服务接口实现类 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.26 11:21
 */
@Slf4j
@Service
public class CounterpartyRepositoryServiceImpl extends BaseServiceImpl<CounterpartyDao, Counterparty> implements CounterpartyRepositoryService {

    @Override
    public List<CounterpartyDTO> listByCompanyId(Long companyBorrowerId) {
        return baseMapper.listByCompanyId(companyBorrowerId);
    }
    @Override
    public IPage<CounterpartyDTO> findPage(IPage<CounterpartyDTO> page, Map<String, Object> params) {
        return baseMapper.findPage(page, params);
    }

    @Override
    public List<CounterpartyDTO> listByParams(Map<String, Object> params) {
        return baseMapper.listByParams(params);
    }
    /**
     * 修改分项额度和融资比例
     * @param counterparty
     */
    @Override
    public  int updateBaseConfig(Counterparty counterparty){
         return baseMapper.updateBaseConfig(counterparty);
    }

    @Override
    public int review(Map<String, Object> params){
        return baseMapper.review(params);
    }

    @Override
    public CounterpartyDTO getByParams(Map<String, Object> params) {
        return baseMapper.getByParams(params);
    }

    @Override
    public int updateLoanBalance(Long id, BigDecimal applyBalance, BigDecimal transferBalance) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("applyBalance",applyBalance);
        map.put("transferBalance",transferBalance);

        return baseMapper.updateLoanBalance(map);
    }

    @Override
    public int updateRepayBalance(Long counterpartyId, BigDecimal repayBalance, BigDecimal repayTransferBalance) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",counterpartyId);
        map.put("repayBalance",repayBalance);
        map.put("repayTransferBalance",repayTransferBalance);

        return baseMapper.updateRepayBalance(map);
    }
}
