package com.fkhwl.fkhserver.openapi.controller;

import com.fkhwl.scf.WaybillProvider;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.PushWaybillDTO;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;
import com.fkhwl.scf.entity.form.PushWaybillForm;
import com.fkhwl.scf.wrapper.ProgramViewConverterWrapper;
import com.fkhwl.scf.wrapper.ProjectViewConverterWrapper;
import com.fkhwl.scf.wrapper.WaybillOperationHistoryViewConverterWrapper;
import com.fkhwl.scf.wrapper.WaybillViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.rest.base.BaseController;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author:QIBIN NIE
 * @Description:
 * @Date:Created on 2019-1-21 15:58.
 * @Modified By:
 */
@RestController
@RequestMapping("/waybill")
@Api(value = "/waybill", description = "运单信息相关接口")
@Slf4j
public class WaybillController extends BaseController {

    @Reference(retries = 1, timeout = 50000)
    private WaybillProvider waybillProvider;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "推送运单信息接口", notes = "传入WaybillForm")
    public Result pushWaybill(@ApiParam(value = "查询参数(按需传入)", required = true) @RequestBody PushWaybillForm pushWaybillForm) {
        try {
            //转换成DTO
            WaybillDTO waybillDTO = WaybillViewConverterWrapper.INSTANCE.dto(pushWaybillForm.getWaybillForm());
            ProgramDTO programDTO = ProgramViewConverterWrapper.INSTANCE.dto(pushWaybillForm.getProgramForm());
            ProjectDTO projectDTO = ProjectViewConverterWrapper.INSTANCE.dto(pushWaybillForm.getProjectForm());
            log.error("pushWaybill:{}", waybillDTO.getWaybillNo());
            List<WaybillOperationHistoryDTO> operationHistoryDTOList = pushWaybillForm.getOperationHistoryFormList().stream().map
                (WaybillOperationHistoryViewConverterWrapper.INSTANCE::dto).collect(Collectors.toList());

            PushWaybillDTO pushWaybillDTO = PushWaybillDTO.builder().waybillDTO(waybillDTO).programDTO(programDTO).projectDTO(projectDTO)
                .operationHistoryDTOList(operationHistoryDTOList).build();
            //TODO-LA: 2020/3/3 匹配用信规则和CFCA保单生成之后 最后保存运单、项目、计划数据
            waybillProvider.pushWaybill(pushWaybillDTO);
            return this.ok();
        } catch (Exception e) {
            log.error("WaybillController pushWaybill, error: {}", e.getMessage());
            return this.fail(e.getMessage());
        }
    }

}
