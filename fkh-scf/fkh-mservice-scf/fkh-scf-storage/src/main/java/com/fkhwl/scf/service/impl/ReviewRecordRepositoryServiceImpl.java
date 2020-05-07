package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.ReviewRecordDTO;
import com.fkhwl.scf.entity.po.ReviewRecord;
import com.fkhwl.scf.dao.ReviewRecordDao;
import com.fkhwl.scf.service.ReviewRecordRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  服务接口实现类 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Slf4j
@Service
public class ReviewRecordRepositoryServiceImpl extends BaseServiceImpl<ReviewRecordDao, ReviewRecord> implements ReviewRecordRepositoryService {

    @Override
    public List<ReviewRecordDTO> listReviewHistory(Map<String, Object> params) {
        return baseMapper.listReviewHistory(params);
    }
}
