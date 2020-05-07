package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;
import com.fkhwl.starter.common.enums.DeleteEnum;

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
 * <p>Description: 主体合同：资方与借款方签订的合同 数据传输实体 (根据业务需求添加字段) </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.03 15:12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompanyContractDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "资方企业ID")
    private Long companyCapitalId;
    @ApiModelProperty(value = "借款方企业ID")
    private Long companyBorrowerId;
    @ApiModelProperty(value = "借款方企业ID：合同主体名称")
    private String companyBorrowerName;
    @ApiModelProperty(value = "合同编号")
    private String contractNumber;
    @ApiModelProperty(value = "合同开始时间")
    private Date startTime;
    @ApiModelProperty(value = "合同结束时间")
    private Date endTime;
    @ApiModelProperty(value = "管理费率")
    private BigDecimal manageRate;
    @ApiModelProperty(value = "利率")
    private BigDecimal interestRate;
    @ApiModelProperty(value = "总额度")
    private BigDecimal totalBalance;
    @ApiModelProperty(value = "合同文件链接地址")
    private String contractLink;
    @ApiModelProperty(value = "企业所有已审核放款成功的总金额：累计")
    private BigDecimal loanSuccessBalance;
    @ApiModelProperty(value = "可用余额")
    private BigDecimal remainingBalance;
    @ApiModelProperty(value = "企业已归还金额：累计")
    private BigDecimal returnedBalance;
    @ApiModelProperty(value = "未还,在途,还需要归还的金额：累计")
    private BigDecimal needReturnBalance;
    @ApiModelProperty(value = "可分配的分项额度：总额度-已分配的分享额度总和")
    private BigDecimal remainingSubitemBalance;

    @ApiModelProperty(value = "已分配的分享额度总和：交易对手的分享额度总和")
    private BigDecimal hadSubitemBalance;

    @ApiModelProperty(value = " 状态：0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）")
    private Integer status;

    @ApiModelProperty(value = "开户银行户主名称")
    private String bankAccountName;
    @ApiModelProperty(value = "客户开户银行")
    private String bankName;
    @ApiModelProperty(value = "开户银行卡号")
    private String bankAccountNo;
    @ApiModelProperty(value = "一级项目经理")
    private String projectMgName;
    @ApiModelProperty(value = "二级项目经理")
    private String projectSecondMgName;
    @ApiModelProperty(value = "部门经理")
    private String departmentManager;
    @ApiModelProperty(value = "经办部门")
    private String departmentName;
    private String  branchBankName;
    private DeleteEnum deleted;
    private Date createTime;
    private Date updateTime;
}
