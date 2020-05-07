package com.fkhwl.scf.entity.po;

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
 * <p>Description: 流程表 实体类  </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.21 15:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Flow extends BasePO<Long, Flow> {

    public static final String FLOW_NAME = "flow_name";
    public static final String REMARK = "remark";
    public static final String COMPANY_CAPITAL_ID = "company_capital_id";
    private static final long serialVersionUID = 1L;

    /** 流程名称 */
    private String flowName;
    /** 备注 */
    private String remark;
    /** 资方公司Id */
    private Long companyCapitalId;
    /** 流程对应的实体名称 */
    private String flowClass;

}
