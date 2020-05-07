package com.fkhwl.scf.web.controller;

import com.fkhwl.scf.ReviewRecordProvider;
import com.fkhwl.scf.entity.dto.ReviewRecordDTO;
import com.fkhwl.scf.entity.vo.ReviewRecordVO;
import com.fkhwl.scf.wrapper.ReviewRecordViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 用款申请 控制器
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
@Slf4j
@Controller
@RequestMapping("/reviewRecord")
@Api(value = "/reviewRecord", tags = "审核意见相关接口")
public class ReviewRecordController extends BaseController {

        @Resource
        private ReviewRecordProvider reviewRecordProvider;


    /**
     *列表
     *
     * @return 查询用款申请审核记录列表
     */
    @ResponseBody
    @GetMapping(value = "/listReviewHistory")
    @ApiOperation(value = "查询用款申请审核意见历史", notes = "查询用款申请审核意见历史")
    @ApiImplicitParam(name = "creditApplyId",value = "用款申请id", paramType = "query")
	public Result<List<ReviewRecordVO>> reviewHistory(Long creditApplyId) {
            Map<String, Object> params = new HashMap<>();
            params.put("creditApplyId", creditApplyId);
            params.put("flowId", 1);
            List<ReviewRecordDTO> result = reviewRecordProvider.listReviewHistory(params);
            return StandardResult.succeed(result.stream().map(ReviewRecordViewConverterWrapper.INSTANCE::vo).collect(Collectors.toList()));
	}


}
