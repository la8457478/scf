package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.AccountBillDao;
import com.fkhwl.scf.entity.dto.AccountBillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.scf.entity.vo.AccountBillListVO;
import com.fkhwl.scf.service.AccountBillRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单 服务接口实现类 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.28 13:57
 */
@Slf4j
@Service
public class AccountBillRepositoryServiceImpl extends BaseServiceImpl<AccountBillDao, AccountBill> implements AccountBillRepositoryService {

    @Override
    public IPage<AccountBillDTO> listPage(IPage<AccountBillDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page,params);
    }

    @Override
    public IPage<AccountBillDTO> listPageForRepay(IPage<AccountBillDTO> page, Map<String, Object> params) {
        return baseMapper.listPageForRepay(page,params);
    }

    @Override
    public IPage<Map<String, Object>> listRepayPage(IPage<Map<String,Object>> page, Map<String, Object> params) {
        return baseMapper.listRepayPage(page,params);
    }

    @Override
    public IPage<AccountBillListVO> listAccountBillListPage(IPage<AccountBillListVO> page,Map<String, Object> params) {
        return baseMapper.listAccountBillListPage(page,params);
    }

    @Override
    public Map<String, Object> getSumRemainRepayBalance(Long counterpartyId) {
        return baseMapper.getSumRemainRepayBalance(counterpartyId);
    }

    @Override
    public int updateRepayBalance(Long id, BigDecimal repayBalance, BigDecimal interestRateBalance, BigDecimal graceRateBalance, BigDecimal overdueRateBalance, Date repayDate) {
       Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("repayBalance",repayBalance);
        map.put("interestRateBalance",interestRateBalance);
        map.put("graceRateBalance",graceRateBalance);
        map.put("overdueRateBalance",overdueRateBalance);
        map.put("repayDate",repayDate);
        return baseMapper.updateRepayBalance(map);
    }
}
