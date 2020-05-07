package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.SubjectClaimsOrderDao;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.po.SubjectClaimsOrder;
import com.fkhwl.scf.entity.vo.ReviewPageVo;
import com.fkhwl.scf.service.SubjectClaimsOrderRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 标的债权订单 服务接口实现类 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
@Slf4j
@Service
public class SubjectClaimsOrderRepositoryServiceImpl extends BaseServiceImpl<SubjectClaimsOrderDao, SubjectClaimsOrder> implements SubjectClaimsOrderRepositoryService {

    @Override
    public IPage<SubjectClaimsOrderDTO> listPage(IPage<SubjectClaimsOrderDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page,params);
    }

    @Override
    public List<ReviewPageVo> reviewPage(Map<String, Object> params) {
        return baseMapper.reviewPage(params);
    }

    @Override
    public Long countWaybillCountByCreditApplyId(Long creditApplyId) {
        return baseMapper.countWaybillCountByCreditApplyId(creditApplyId);    }
}
