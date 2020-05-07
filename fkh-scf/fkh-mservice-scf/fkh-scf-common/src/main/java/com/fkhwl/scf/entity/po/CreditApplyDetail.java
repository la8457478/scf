package com.fkhwl.scf.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fkhwl.starter.common.base.BasePO;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用款申请详情 实体类  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditApplyDetail extends BasePO<Long, CreditApplyDetail> {

    public static final String APPLY_BALANCE = "apply_balance";
    public static final String DIPOSIT_BALANCE = "diposit_balance";
    public static final String FACT_RATIO = "fact_ratio";
    public static final String IN_FACT_RATIO = "in_fact_ratio";
    public static final String CREDIT_APPLY_ID = "credit_apply_id";
    public static final String SUBJECT_CLAIMS_ORDER = "subject_claims_order";
    public static final String COUNTERPARTY_ID = "counterparty_id";
    private static final long serialVersionUID = 1L;

    /** 转让额度 */
    private BigDecimal transferBalance;
    /** 本次提用额 */
    private BigDecimal applyBalance;
    /** 本次提用额 */
    private BigDecimal remainTransferBalance;
    /** 质保金 */
    private BigDecimal dipositBalance;
    /** 实际比例 */
    private BigDecimal factRatio;
    /** 是否在比例内 */
    private Boolean inRuleRatio;
    /** 用款申请id */
    private Long creditApplyId;
    /** 债权用信id */
    private Long subjectClaimsOrderId;
    /** 交易对手id */
    private Long counterpartyId;

}
