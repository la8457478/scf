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
 * <p>Description: 流程节点 实体类  </p>
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
public class FlowNode extends BasePO<Long, FlowNode> {

    public static final String FLOW_NODE_NAME = "flow_node_name";
    public static final String FLOW_ID = "flow_id";
    public static final String ROLE_ID = "role_id";
    public static final String REMARK = "remark";
    public static final String PREV_NODE_ID = "prev_node_id";
    public static final String NEXT_NODE_ID = "next_node_id";
    public static final String FLOW_TYPE_ID = "flow_type_id";
    public static final String LIMIT_BALANCE = "limit_balance";
    public static final String STATUS = "status";
    private static final long serialVersionUID = 1L;

    /** 节点名称 */
    private String flowNodeName;
    /** 流程id */
    private Long flowId;
    /** 审批的角色id：type为单人时存储。为多人是存0 */
    private Long roleId;
    /** 备注 */
    private String remark;
    /** 前一个节点:若是初始节点为0 */
    private Long prevNodeId;
    /** 后一个节点:若是结尾节点为0 */
    private Long nextNodeId;
    /** 节点类型Id */
    private Long flowTypeId;
    /** 限制可跳过金额:默认为0。 */
    private BigDecimal limitBalance;
    /** 对应的状态 */
    private Integer status;

}
