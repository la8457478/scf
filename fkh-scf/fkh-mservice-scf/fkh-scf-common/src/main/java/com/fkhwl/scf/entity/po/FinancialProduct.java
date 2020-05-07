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

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 金融产品:暂定 实体类  </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.06 15:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FinancialProduct extends BasePO<Long, FinancialProduct> {

    public static final String INTEREST_RATE = "interest_rate";
    public static final String REPAY_TERM = "repay_term";
    private static final long serialVersionUID = 1L;

    /** 利率 */
    private BigDecimal interestRate;
    /** 还款周期 月 */
    private Integer repayTerm;
    /** 还款方式 */
    private Integer repayType;

}
