package com.fkhwl.scf.dao;

import org.apache.ibatis.annotations.Mapper;
import com.fkhwl.scf.entity.po.FlowNode;
import com.fkhwl.starter.common.base.BaseDao;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 流程节点 Dao 接口  </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.21 15:51
 */
@Mapper
public interface FlowNodeDao extends BaseDao<FlowNode> {

    FlowNode getPrevNode(Long currentFlowNodeId);

    FlowNode getNextNode(Long currentFlowNodeId);
}
