package com.fkhwl.scf.entity.po;

import com.fkhwl.scf.entity.base.ScfBasePO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 标的债权订单 实体类  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SubjectClaimsOrder extends ScfBasePO<Long, SubjectClaimsOrder> {

    public static final String CLAIMS_ORDER_NO = "claims_order_no";
    public static final String COUNTERPARTY_NAME = "counterparty_name";
    public static final String TRANSFER_BALANCE = "transfer_balance";
    public static final String WAYBILL_BALANCE = "waybill_balance";
    public static final String WAYBILL_COUNT = "waybill_count";
    public static final String FINACIAL_PRODUCT_NAME = "finacianl_product_name";
    public static final String REVIEW_STATUS = "review_status";
    private static final long serialVersionUID = 1L;

    /** 标的债权订单号 */
    private String subjectClaimsOrderNo;
    /** 交易对手 */
    private String counterpartyName;
    /** 转让金额 */
    private BigDecimal transferBalance;
    /** 本次可提 */
    private BigDecimal canApplyBalance;
    /** 运单个数 */
    private Integer waybillCount;
    /** 金融产品名称 */
    private String financialProductName;

    //交易对手Id
    private Long counterpartyId;
    //项目Id
    private Long projectId;
        //金融产品id
        private Long financialProductId;
    /** 审核状态：-1.运营审核不通过，0.初始化，1.运营审核通过 */
    private Integer reviewStatus;

}
