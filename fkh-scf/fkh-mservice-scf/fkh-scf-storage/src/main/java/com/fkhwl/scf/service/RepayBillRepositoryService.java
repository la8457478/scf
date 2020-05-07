package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.po.RepayBill;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款订单-发起待出纳确认 服务接口 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.22 15:48
 */
public interface RepayBillRepositoryService extends BaseService<RepayBill> {
    IPage<RepayBillDTO> listPage(IPage<RepayBillDTO> page, Map<String, Object> params);

    /**
     * 获取交易对手，待审核的还款申请
     * @param counterpartyId
     * @return
     */
    List<RepayBillDTO> getCheckingByCounterpartyId(Long counterpartyId );
}

