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
 * <p>Description: 节点类型 实体类  </p>
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
public class FlowNodeType extends BasePO<Long, FlowNodeType> {

    public static final String TYPE = "type";
    public static final String AMOUNT = "amount";
    public static final String REMARK = "remark";
    private static final long serialVersionUID = 1L;

    /** 0单人审核 1多人无序 2多人有序 */
    private String type;
    /** 多人审核人数，单人为0 */
    private Integer amount;
    /** 备注 */
    private String remark;

}
