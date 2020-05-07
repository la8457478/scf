package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;

import java.math.BigDecimal;

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
 * <p>Description: 金融产品:暂定 视图实体 (根据业务需求添加字段) </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.06 15:45
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FinancialProductVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "利率")
    private BigDecimal interestRate;
    @ApiModelProperty(value = "还款周期 月")
    private Integer repayTerm;
    @ApiModelProperty(value = "还款方式")
    private Integer repayType;
}
