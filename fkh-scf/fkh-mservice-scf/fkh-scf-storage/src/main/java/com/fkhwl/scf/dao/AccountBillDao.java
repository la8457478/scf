package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AccountBillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.scf.entity.vo.AccountBillListVO;
import com.fkhwl.scf.entity.vo.AccountBillVO;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单 Dao 接口  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.28 13:57
 */
@Mapper
public interface AccountBillDao extends BaseDao<AccountBill> {

    IPage<AccountBillDTO> listPage(@Param("page") IPage<AccountBillDTO> page, @Param("map") Map<String, Object> params);

    /**
     * 查找需要还款的账单列表，逾期》宽限期》即将到期
     * @param page
     * @param params
     * @return
     */
    IPage<AccountBillDTO> listPageForRepay(@Param("page") IPage<AccountBillDTO> page, @Param("map") Map<String, Object> params);

    IPage<Map<String, Object>> listRepayPage(@Param("page") IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);

    IPage<AccountBillListVO> listAccountBillListPage(@Param("page") IPage<AccountBillListVO> page,@Param("map")Map<String, Object> params);

    /**
    * 获取交易对手下所有账单未还金额:
     * @param counterpartyId
    * @return: java.math.BigDecimal
    * @Author: liuan
    * @Date: 2020/4/7 10:32
    */
    Map<String,Object> getSumRemainRepayBalance(Long counterpartyId);

    /**
     * 账单还款时金额变化
     *
     * @return: int
     * @Author: liuan
     * @Date: 2020/4/13 10:51
     */
    int updateRepayBalance(@Param("map") Map<String ,Object> map);
}
