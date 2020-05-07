package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.ReviewRecordProvider;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ReviewRecordDTO;
import com.fkhwl.scf.service.ProjectService;
import com.fkhwl.scf.service.ReviewRecordService;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewRecordProviderImpl implements ReviewRecordProvider {
    private  final ReviewRecordService reviewRecordService;

    @Override
    public List<ReviewRecordDTO> listReviewHistory(Map<String, Object> params) {
        return reviewRecordService.listReviewHistory(params);
    }
}
