package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.po.RepayBill;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款订单-发起待出纳确认 Dao 接口  </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.22 15:48
 */
@Mapper
public interface RepayBillDao extends BaseDao<RepayBill> {

    IPage<RepayBillDTO> listPage(@Param("page") IPage<RepayBillDTO> page,
                               @Param("map") Map<String, Object> params);

    List<RepayBillDTO> getCheckingByCounterpartyId(Long counterpartyId );
}
