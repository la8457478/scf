package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  用款申请 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.27 10:54
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreditApplyVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "用款申请编号")
    private String creditApplyNo;
    @ApiModelProperty(value = "本次申请提用金额")
    private BigDecimal applyBalance;
    @ApiModelProperty(value = "本次可提用金额")
    private BigDecimal canApplyBalance;
    @ApiModelProperty(value = "运单个数")
    private Integer waybillCount;
    @ApiModelProperty(value = "附件路径（暂定，可能需要修改）")
    private String attachment;
    @ApiModelProperty(value = "应收账款id")
    private Long counterpartyId;
    @ApiModelProperty(value = "到期日")
    private Date dueDate;
    @ApiModelProperty(value = "利息金额")
    private BigDecimal interestBalance;
    @ApiModelProperty(value = "有效转让金额")
    private BigDecimal transferBalance;
    @ApiModelProperty(value = "放款金额")
    private BigDecimal loanBalance;
    @ApiModelProperty(value = "实际比例")
    private BigDecimal factRatio;
    @ApiModelProperty(value = "管理金额")
    private BigDecimal manageBalance;
    //当前所在节点id
    private Long flowNodeId;
    //所属流程id
    private Long flowId;
    private Date createTime;
    /** 宽限日 */
    private Date graceDate;
    //服务费是否已支付
    private Boolean hasCharge;
    @ApiModelProperty(value = "已查阅运单条数")
    private Integer checkedWaybillCount;
    private Long  passLastFlowNode;
    @ApiModelProperty(value = "未签章的合同地址")
    private String pdfPath;
    @ApiModelProperty(value = "盖章后的合同地址")
    private String signPdfPath;
    private Date loanTime;
    private String reviewBillUrl;
    @ApiModelProperty(value = "需要查阅的运单数量")
    private Integer needCheckWaybillCount;

}
