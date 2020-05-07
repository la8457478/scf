package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.po.Flow;
import com.fkhwl.scf.dao.FlowDao;
import com.fkhwl.scf.service.FlowRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 流程表 服务接口实现类 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.21 15:51
 */
@Slf4j
@Service
public class FlowRepositoryServiceImpl extends BaseServiceImpl<FlowDao, Flow> implements FlowRepositoryService {

}
