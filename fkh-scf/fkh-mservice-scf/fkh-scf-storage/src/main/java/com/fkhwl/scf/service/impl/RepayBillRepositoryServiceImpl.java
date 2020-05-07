package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.RepayBillDao;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.po.RepayBill;
import com.fkhwl.scf.service.RepayBillRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款订单-发起待出纳确认 服务接口实现类 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.22 15:48
 */
@Slf4j
@Service
public class RepayBillRepositoryServiceImpl extends BaseServiceImpl<RepayBillDao, RepayBill> implements RepayBillRepositoryService {
    @Override
    public IPage<RepayBillDTO> listPage(IPage<RepayBillDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page,params);
    }
    @Override
    public List<RepayBillDTO> getCheckingByCounterpartyId(Long counterpartyId ){
        return baseMapper.getCheckingByCounterpartyId( counterpartyId);
    }
}
