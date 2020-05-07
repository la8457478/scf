package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.ReviewRecordDTO;
import com.fkhwl.scf.service.ReviewRecordRepositoryService;
import com.fkhwl.scf.service.ReviewRecordService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/3/16
 */
@Service
@AllArgsConstructor
public class ReviewRecordServiceImpl implements ReviewRecordService {
    private final ReviewRecordRepositoryService reviewRecordRepositoryService;
    @Override
    public List<ReviewRecordDTO> listReviewHistory(Map<String, Object> params) {
        return reviewRecordRepositoryService.listReviewHistory(params);
    }
}
