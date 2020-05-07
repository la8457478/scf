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
 * <p>Description: 账单 视图实体 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.28 13:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountBillListVO extends AccountBillVO {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "客户名称")
    private String companyName;

    @ApiModelProperty(value = "客户id")
    private String companyBorrowerId;

    @ApiModelProperty(value = "交易对手名称")
    private String counterpartyName;

    private String creditApplyNo;

    //申请转让日")
    private Date applyTransferDate;

    private BigDecimal manageBalance;


    private Long ownerId;
    private Long createUserId;

}
