package com.fkhwl.scf.entity.po;

import com.fkhwl.scf.entity.base.ScfBasePO;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  用款申请 实体类  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.27 10:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditApply extends ScfBasePO<Long, CreditApply> {

    public static final String STATUS = "status";
    public static final String CREDIT_APPLY_NO = "credit_apply_no";
    public static final String APPLY_TRANSFER_BALANCE = "apply_balance";
    public static final String CAN_APPLY_TRANSFER_BALANCE = "can_apply_balance";
    public static final String WAYBILL_COUNT = "waybill_count";
    public static final String ATTACHMENT = "attachment";
    public static final String CHECKED_WAYBILL_COUNT = "checked_waybill_count";
    private static final long serialVersionUID = 1L;

    /** 状态 1:"运营审核中",2:"风控审核中",3:"管理审核中",4:"待放款",5:"已放款",6:"未还款",7:"已还款" */
    private Integer status;
    /** 用款申请编号 */
    private String creditApplyNo;
    /** 转让金额 */
    private BigDecimal applyBalance;
    /** 应转让金额 */
    private BigDecimal canApplyBalance;
    /** 运单个数 */
    private Integer waybillCount;
    /** 附件路径（暂定，可能需要修改） */
    private String attachment;
    /** 应收账款id */
    private Long counterpartyId;
    /** 到期日 */
    private Date dueDate;
    /** 宽限日 */
    private Date graceDate;
    /** 利息金额 */
    private BigDecimal interestBalance;
    //有效转让金额
    private BigDecimal transferBalance;
    //放款金额
    private BigDecimal loanBalance;
    //实际比例
    private BigDecimal factRatio;
    //当前所在节点id
    private Long flowNodeId;
    //所属流程id
    private Long flowId;
    /** 管理金额 */
    private BigDecimal manageBalance;
    /** 已查阅运单条数 */
    private Integer checkedWaybillCount;
    //服务费是否已支付
    private Boolean hasCharge;
    /** 未签章的合同地址")*/
    private String pdfPath;
    /** 盖章后的合同地址")*/
    private String signPdfPath;
    //放款时间
    private Date loanTime;
    //流程到达过的最后一个节点
    private Double passLastFlowNodeId;

    private String reviewBillUrl;
    //本次提用后融资金额
    private BigDecimal afterThisBalance;
    /** 需要查阅的运单数量 */
    private Integer needCheckWaybillCount;

}
