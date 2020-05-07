package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.po.CreditApplyDetail;
import com.fkhwl.scf.entity.po.SubjectClaimsOrder;
import com.fkhwl.scf.entity.vo.ReviewPageVo;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 标的债权订单 服务接口 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
public interface SubjectClaimsOrderRepositoryService extends BaseService<SubjectClaimsOrder> {

    IPage<SubjectClaimsOrderDTO> listPage(IPage<SubjectClaimsOrderDTO> page, Map<String, Object> params);

    List<ReviewPageVo> reviewPage(Map<String, Object> params);
/**
* 用款申请下所有应收账款的运单数
 * @param id
* @return: java.lang.Long
* @Author: liuan
* @Date: 2020/3/13 17:15
*/
    Long countWaybillCountByCreditApplyId(Long id);
}

