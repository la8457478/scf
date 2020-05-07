package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AccountBillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.scf.entity.vo.AccountBillListVO;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单 服务接口 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.28 13:57
 */
public interface AccountBillRepositoryService extends BaseService<AccountBill> {

    IPage<AccountBillDTO> listPage(IPage<AccountBillDTO> page, Map<String, Object> params);

    IPage<AccountBillDTO> listPageForRepay( IPage<AccountBillDTO> page, Map<String, Object> params);

    IPage<Map<String, Object>> listRepayPage(IPage<Map<String,Object>> page, Map<String, Object> params);

    IPage<AccountBillListVO> listAccountBillListPage(IPage<AccountBillListVO> page,Map<String, Object> params);

    /**
    * 获取交易对手下所有账单未还金额
     * @param counterpartyId
    * @return: java.math.BigDecimal
    * @Author: liuan
    * @Date: 2020/4/7 10:31
    */
    Map<String, Object> getSumRemainRepayBalance(Long counterpartyId);

    /**
    * 账单还款时金额变化
     * @param id
     * @param repayBalance
     * @param interestRateBalance
     * @param graceRateBalance
     * @param overdueRateBalance
     * @param createTime
    * @return: int
    * @Author: liuan
    * @Date: 2020/4/13 10:50
    */
    int updateRepayBalance(Long id, BigDecimal repayBalance, BigDecimal interestRateBalance, BigDecimal graceRateBalance, BigDecimal overdueRateBalance, Date repayDate);
}

