package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.scf.dao.CreditApplyDao;
import com.fkhwl.scf.service.CreditApplyRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  服务接口实现类 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.27 10:54
 */
@Slf4j
@Service
public class CreditApplyRepositoryServiceImpl extends BaseServiceImpl<CreditApplyDao, CreditApply> implements CreditApplyRepositoryService {

    @Override
    public IPage<CreditApplyDTO> listPage(IPage<CreditApplyDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page,params);
    }
    @Override
    public IPage<CreditApplyListDTO> listCreditApplyPage(IPage<CreditApplyListDTO> page, Map<String, Object> params) {
        return baseMapper.listCreditApplyPage(page,params);
    }

    @Override
    public CreditApplyReviewDTO getReviewDetail(Long creditApplyId, Long companyCapitalId) {
        return baseMapper.getReviewDetail(creditApplyId,companyCapitalId);    }

    @Override
    public Map<String, Object> sumBalance(Map<String, Object> params) {
        return baseMapper.sumBalance(params);      }

}
