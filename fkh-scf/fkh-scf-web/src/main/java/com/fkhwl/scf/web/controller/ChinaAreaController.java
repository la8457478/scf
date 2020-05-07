package com.fkhwl.scf.web.controller;

import com.fkhwl.scf.ChinaAreaProvider;
import com.fkhwl.scf.entity.dto.ChinaAreaDTO;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.support.AssertUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 行政区域controller
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/chinaArea")
@Api(value = "/chinaArea", tags = "行政区域接口")
public class ChinaAreaController extends BaseController {

    @Resource
    private ChinaAreaProvider chinaAreaProvider;

    /**
     * 根据父级id查询省市县行政区域
     */
    @ResponseBody
    @GetMapping(value = "/listAreaByParentId/{parentId}")
    @ApiOperation(value = "根据父级id查询省市县行政区域", notes = "根据父级id查询省市县行政区域")
    @ApiImplicitParam(name = "parentId",value = "父级行政区域id,省级区域的父级id为0", paramType = "path")
    public Result<List<ChinaAreaDTO>> listAreaByParentId(@PathVariable(value = "parentId") Long parentId) {
            AssertUtils.notNull(parentId, "参数错误");
            return StandardResult.succeed(chinaAreaProvider.listParentId(parentId));
    }
}
