package com.fkhwl.fkhserver.openapi.controller;

import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.entity.form.ProjectForm;
import com.fkhwl.scf.wrapper.ProjectViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.rest.base.BaseController;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目 控制器
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.24 10:27
 */
@RestController
@RequestMapping("/")
@Api(value = "项目", tags = "项目接口")
@Slf4j
public class ProjectController extends BaseController {

    @Reference(retries = 1, timeout = 50000)
    private ProjectProvider projectProvider;

    /**
     * 新增
     *
     * @param project 参数
     * @return void
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "推送项目", notes = "传入project")
    public Result<Void> save(@Valid @RequestBody ProjectForm project) {
        projectProvider.saveOrUpdate(ProjectViewConverterWrapper.INSTANCE.dto(project));
        return ok();
    }

}
