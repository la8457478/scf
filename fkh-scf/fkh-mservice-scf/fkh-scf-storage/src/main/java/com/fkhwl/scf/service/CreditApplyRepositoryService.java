package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  服务接口 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.27 10:54
 */
public interface CreditApplyRepositoryService extends BaseService<CreditApply> {

    IPage<CreditApplyDTO> listPage(IPage<CreditApplyDTO> page, Map<String, Object> params);
    IPage<CreditApplyListDTO> listCreditApplyPage(IPage<CreditApplyListDTO> page, Map<String, Object> params);


    /**
    * 审核详情
     * @param creditApplyId
    * @return: com.fkhwl.scf.entity.dto.CreditApplyListDTO
    * @Author: liuan
    * @Date: 2020/3/12 16:00
    */
    CreditApplyReviewDTO getReviewDetail(Long creditApplyId, Long companyCapitalId);

    /**
     * 合计金额
     *
     * @param params
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: liuan
     * @Date: 2020/4/1 10:48
     */
    Map<String, Object> sumBalance(Map<String, Object> params);
}

