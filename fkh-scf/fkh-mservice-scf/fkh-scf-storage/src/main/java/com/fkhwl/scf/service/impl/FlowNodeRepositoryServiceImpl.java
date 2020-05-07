package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.po.FlowNode;
import com.fkhwl.scf.dao.FlowNodeDao;
import com.fkhwl.scf.service.FlowNodeRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 流程节点 服务接口实现类 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.21 15:51
 */
@Slf4j
@Service
public class FlowNodeRepositoryServiceImpl extends BaseServiceImpl<FlowNodeDao, FlowNode> implements FlowNodeRepositoryService {

    @Override
    public FlowNode getNextNode(Long currentFlowNodeId) {
        return baseMapper.getNextNode(currentFlowNodeId);
    }

    @Override
    public FlowNode getPrevNode(Long currentFlowNodeId) {
        return baseMapper.getPrevNode(currentFlowNodeId);
    }
}
