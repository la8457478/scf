package com.fkhwl.scf.service;

import com.fkhwl.scf.entity.po.FlowNode;
import com.fkhwl.starter.mybatis.service.BaseService;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 流程节点 服务接口 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.21 15:51
 */
public interface FlowNodeRepositoryService extends BaseService<FlowNode> {

    /**
    * 获取下一个流程节点
     * @param currentFlowNodeId
    * @return: com.fkhwl.scf.entity.po.FlowNode
    * @Author: liuan
    * @Date: 2020/3/21 20:13
    */
    FlowNode getNextNode(Long currentFlowNodeId);
    /**
     * 获取上一个流程节点
     * @param currentFlowNodeId
     * @return: com.fkhwl.scf.entity.po.FlowNode
     * @Author: liuan
     * @Date: 2020/3/21 20:13
     */
    FlowNode getPrevNode(Long currentFlowNodeId);
}

