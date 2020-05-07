package com.fkhwl.scf.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CreditApplyProvider;
import com.fkhwl.scf.ProgramProvider;
import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.WaybillCheckRecordProvider;
import com.fkhwl.scf.WaybillOperationHistoryProvider;
import com.fkhwl.scf.WaybillProvider;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.entity.vo.WaybillOperationHistoryVO;
import com.fkhwl.scf.entity.vo.WaybillVO;
import com.fkhwl.scf.enums.UnitEnum;
import com.fkhwl.scf.utils.Const;
import com.fkhwl.scf.utils.ExcelUtil;
import com.fkhwl.scf.utils.Tools;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.web.excelExport.WaybillExcel;
import com.fkhwl.scf.web.excelExport.WaybillShortExcel;
import com.fkhwl.scf.web.util.IPageData;
import com.fkhwl.scf.web.util.PageData;
import com.fkhwl.scf.web.util.Views;
import com.fkhwl.scf.wrapper.WaybillOperationHistoryViewConverterWrapper;
import com.fkhwl.scf.wrapper.WaybillViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.core.util.BeanUtils;
import com.fkhwl.starter.core.util.StringUtils;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.apache.poi.ss.formula.functions.T;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 运单controller
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/waybill")
@Api(value = "/waybill", tags = "运单接口")
public class WaybillController extends BaseController {

    @Resource
    private WaybillProvider waybillProvider;
    @Resource
    private CreditApplyProvider creditApplyProvider;

    @Resource
    private ProjectProvider projectProvider;

    @Resource
    private ProgramProvider programProvider;

    @Resource
    private WaybillOperationHistoryProvider operationHistoryProvider;

    @Resource
    private WaybillCheckRecordProvider waybillCheckRecordProvider;

    @Value("${fkh.openApi}")
    private String openApi;

	/**
	 * 分页获取运单列表
	 *
	 * @return the string
	 */
    @ResponseBody
	@PostMapping(value = "/listPage")
    @ApiOperation(value = "分页获取运单列表", notes = "分页获取运单列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "waybillNo",value = "运单号", paramType = "query"),
        @ApiImplicitParam(name = "projectId",value = "金融平台项目id",paramType = "query"),
        @ApiImplicitParam(name = "searchTimeType",value = "查询时间类型", paramType = "query"),
        @ApiImplicitParam(name = "pageNo",value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "subjectClaimsOrderId",value = "应收款转让表id", paramType = "query"),
        @ApiImplicitParam(name = "creditApplyId",value = "用款申请id", paramType = "query"),
        @ApiImplicitParam(name = "beginTime",value = "开始时间",  paramType = "query"),
        @ApiImplicitParam(name = "overTime",value = "结束时间",  paramType = "query"),
        @ApiImplicitParam(name = "isTransferred",value = "是否被转让", paramType = "query")
    })

    public Result<Map<String,Object>> listPage(String waybillNo, Long projectId, Integer searchTimeType, Integer pageNo, Long
        subjectClaimsOrderId, Long creditApplyId, String beginTime, String overTime, Boolean isTransferred) {
        try {
            Map<String, Object> params = initListParam(waybillNo, projectId, searchTimeType, subjectClaimsOrderId,
                creditApplyId, beginTime, overTime, isTransferred);
            List<Long> waybillIds=waybillProvider.listIdsByParams(params);
            params.put("page", pageNo);
            params.put("limit", 5);
            IPage<WaybillVO> result = waybillProvider.listPage(params).convert(WaybillViewConverterWrapper.INSTANCE::vo);
            IPageData<WaybillVO> tmp= new PageData<WaybillVO>().convertIPage(result);
            tmp.setData(waybillIds);
            Map<String,Object> data = new HashMap<>();
            data.put("data",tmp);
            if(!StringUtils.isEmpty(creditApplyId)){
               CreditApplyDTO creditApplyDTO =  creditApplyProvider.getDetail(creditApplyId);
               data.put("creditApplyStatus",creditApplyDTO.getStatus());
            }
            //查看满足搜索条件的所有运单id
            return StandardResult.succeed(data);
        } catch (Exception e) {
            log.error("AuthFunctionController listPage error: {}", e.getMessage());
            return StandardResult.failed(e.getMessage());
        }
    }
//    public Result<Map<String, Object>> listPage(String waybillNo, Long projectId, Integer searchTimeType, Integer pageNo, Long
//        subjectClaimsOrderId, Long creditApplyId, String beginTime, String overTime, Boolean isTransferred) {
//        try {
//            Map<String, Object> result = new HashMap<>();
//            Map<String, Object> params = initListParam(waybillNo, projectId, searchTimeType, subjectClaimsOrderId,
//                creditApplyId, beginTime, overTime, isTransferred);
//            //查看满足搜索条件的所有运单id
//            result.put("waybillIds", waybillProvider.listIdsByParams(params));
//            params.put("page", pageNo);
//            params.put("limit", 5);
//            result.put("waybillPage", waybillProvider.listPage(params).convert(WaybillViewConverterWrapper.INSTANCE::vo));
//            return StandardResult.succeed(result);
//        } catch (Exception e) {
//            log.error("AuthFunctionController listPage error: {}", e.getMessage());
//            return StandardResult.failed(e.getMessage());
//        }
//    }

    /**
     * 列表查询公共参数设定
     * @param waybillNo
     * @param projectId
     * @param searchTimeType
     * @param subjectClaimsOrderId
     * @param creditApplyId
     * @param beginTime
     * @param overTime
     * @param isTransferred
     * @return
     */
    @NotNull
    private Map<String, Object> initListParam(String waybillNo, Long projectId, Integer searchTimeType,
                                              Long subjectClaimsOrderId, Long creditApplyId, String beginTime,
                                              String overTime, Boolean isTransferred) {
        Map<String, Object> params = new HashMap<>();
        params.put("waybillNo", waybillNo);
        params.put("projectId", projectId);
        params.put("searchTimeType", searchTimeType);
        //fix-la: 新增标的债权订单id。
        params.put("subjectClaimsOrderId", subjectClaimsOrderId);
        //fix-la:新增的用款申请id
        params.put("creditApplyId", creditApplyId);
        params.put("beginTime", beginTime);
        params.put("overTime", overTime);
        params.put("isTransferred", isTransferred);
        return params;
    }

    // 导出运单明细
    @GetMapping("/exportExcel")
    @ApiOperation(value = "导出运单明细", notes = "导出运单明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "waybillNo",value = "运单号", paramType = "query"),
        @ApiImplicitParam(name = "projectId",value = "金融平台项目id",paramType = "query"),
        @ApiImplicitParam(name = "searchTimeType",value = "查询时间类型", paramType = "query"),
        @ApiImplicitParam(name = "subjectClaimsOrderId",value = "应收款转让表id", paramType = "query"),
        @ApiImplicitParam(name = "creditApplyId",value = "用款申请id", paramType = "query"),
        @ApiImplicitParam(name = "beginTime",value = "开始时间",  paramType = "query"),
        @ApiImplicitParam(name = "overTime",value = "结束时间",  paramType = "query"),
        @ApiImplicitParam(name = "isTransferred",value = "是否被转让", paramType = "query")
    })
    public void exportExcel(HttpServletResponse response, HttpServletRequest request,String waybillNo, Long projectId,
                            Integer searchTimeType,  Long subjectClaimsOrderId, Long creditApplyId, String beginTime, String overTime, Boolean isTransferred) {
        Map<String, Object> params = initListParam(waybillNo, projectId, searchTimeType, subjectClaimsOrderId,
            creditApplyId, beginTime, overTime, isTransferred);
        IPage<WaybillVO> result = waybillProvider.listPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)),params).convert(WaybillViewConverterWrapper.INSTANCE::vo);
        String currentTime= ToolsHelper.formatDate2Str(new Date());
        List<WaybillVO> waybillVOList=result.getRecords();
        List<WaybillExcel> waybillExcelList=new ArrayList<>();
        if(!BaseValidate.errorList(waybillVOList)){
            for(WaybillVO item:waybillVOList){
                WaybillExcel tmp=new WaybillExcel();
                BeanUtils.copy(item,tmp);
                tmp.setUnit(UnitEnum.getUnitName(item.getUnit()));
                waybillExcelList.add(tmp);
            }
        }else{
            super.exportError(response,"暂无数据");
        }
        if (!ExcelUtil.export(WaybillExcel.class, waybillExcelList, response, "运单详情明细-"+currentTime, "运单详情明细", "exportExcel", request)) {
            log.error("export excel error");
            super.exportError(response,"导出异常");
        }
    }

    // 导出运单明细
    @GetMapping("/exportExcelShort")
    @ApiOperation(value = "导出运单明细", notes = "导出运单明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "waybillNo",value = "运单号", paramType = "query"),
        @ApiImplicitParam(name = "projectId",value = "金融平台项目id",paramType = "query"),
        @ApiImplicitParam(name = "searchTimeType",value = "查询时间类型", paramType = "query"),
        @ApiImplicitParam(name = "subjectClaimsOrderId",value = "应收款转让表id", paramType = "query"),
        @ApiImplicitParam(name = "creditApplyId",value = "用款申请id", paramType = "query"),
        @ApiImplicitParam(name = "beginTime",value = "开始时间",  paramType = "query"),
        @ApiImplicitParam(name = "overTime",value = "结束时间",  paramType = "query"),
        @ApiImplicitParam(name = "isTransferred",value = "是否被转让", paramType = "query")
    })
    public void exportExcelShort(HttpServletResponse response, HttpServletRequest request,String waybillNo, Long projectId,
                            Integer searchTimeType,  Long subjectClaimsOrderId, Long creditApplyId, String beginTime,String overTime, Boolean isTransferred) {
        Map<String, Object> params = initListParam(waybillNo, projectId, searchTimeType, subjectClaimsOrderId,
            creditApplyId, beginTime, overTime, isTransferred);
        IPage<WaybillVO> result = waybillProvider.listPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)),params).convert(WaybillViewConverterWrapper.INSTANCE::vo);
        String currentTime= ToolsHelper.formatDate2Str(new Date());
        List<WaybillVO> waybillVOList=result.getRecords();
        List<WaybillShortExcel> waybillExcelList=new ArrayList<>();
        if(!BaseValidate.errorList(waybillVOList)){
            for(WaybillVO item:waybillVOList){
                WaybillShortExcel tmp=new WaybillShortExcel();
                BeanUtils.copy(item,tmp);
                waybillExcelList.add(tmp);
            }
        }else{
            super.exportError(response,"暂无数据");
        }
        if (!ExcelUtil.export(WaybillShortExcel.class, waybillExcelList, response, "运单明细-"+currentTime, "运单精简明细", "exportExcel", request)) {
            log.error("export excel error");
            super.exportError(response,"导出异常");
        }
    }
    /**
     * 跳转运单详情
     *
     * @return the string
     */
    @GetMapping(value = "/waybillDetail/{waybillId}")
    public String toWaybillDetailPage(@PathVariable("waybillId")Long waybillId, boolean isOperationReview, Long creditApplyId, Model model) {
        WaybillVO waybillVO = WaybillViewConverterWrapper.INSTANCE.vo(waybillProvider.getDetail(waybillId));
        AssertUtils.notNull(waybillVO, "运单信息错误");
        ProjectDTO project = projectProvider.getDetail(waybillVO.getProjectId());
        ProgramDTO programDTO = programProvider.getDetail(waybillVO.getProgramId());

        //生成运单查阅记录
        if (isOperationReview) {
            ScfUserVO sessionUser = super.getSessionUser();
            WaybillCheckRecordDTO waybillCheckRecordDTO = new WaybillCheckRecordDTO();
            waybillCheckRecordDTO.setWaybillId(waybillVO.getId());
            waybillCheckRecordDTO.setCheckUserId(sessionUser.getId());
            waybillCheckRecordDTO.setCheckOwnerId(sessionUser.getParentId());
            waybillCheckRecordDTO.setCreditApplyId(creditApplyId);
            waybillCheckRecordProvider.save(waybillCheckRecordDTO);
        }

        //处理出发地和目的地
        model.addAttribute("departureCity",Tools.splitCityToPoint(waybillVO.getDepartureCity())[0]);
        model.addAttribute("departurePoint",Tools.splitCityToPoint(waybillVO.getDepartureCity())[1]);
        model.addAttribute("arrivalCity",Tools.splitCityToPoint(waybillVO.getArrivalCity())[0]);
        model.addAttribute("arrivalPoint",Tools.splitCityToPoint(waybillVO.getArrivalCity())[1]);

        model.addAttribute("waybill",waybillVO);
        model.addAttribute("project",project);
        model.addAttribute("program", programDTO);

        return Views.WAYBILL_DETAIL_PAGE;
    }

    /**
     * 运单行程
     *
     * @return the string
     */
    @GetMapping(value = "/waybillRouteInfo/{waybillId}")
    public String toWaybillRouteInfoPage(@PathVariable("waybillId")Long waybillId, boolean isOperationReview, Long creditApplyId, Model model) {
        WaybillVO waybillVO = WaybillViewConverterWrapper.INSTANCE.vo(waybillProvider.getDetail(waybillId));
        AssertUtils.notNull(waybillVO, "运单信息不存在");

        //生成运单查阅记录
        if (isOperationReview) {
            ScfUserVO sessionUser = super.getSessionUser();
            WaybillCheckRecordDTO waybillCheckRecordDTO = new WaybillCheckRecordDTO();
            waybillCheckRecordDTO.setWaybillId(waybillVO.getId());
            waybillCheckRecordDTO.setCheckUserId(sessionUser.getId());
            waybillCheckRecordDTO.setCheckOwnerId(sessionUser.getParentId());
            waybillCheckRecordDTO.setCreditApplyId(creditApplyId);
            waybillCheckRecordProvider.save(waybillCheckRecordDTO);
        }

        model.addAttribute("waybill",waybillVO);
        //处理出发地和目的地
        model.addAttribute("departureCity",Tools.splitCityToPoint(waybillVO.getDepartureCity())[1]);
        model.addAttribute("arrivalCity",Tools.splitCityToPoint(waybillVO.getArrivalCity())[1]);

        //查询关键点信息
        /*HttpHeaders headers = new HttpHeaders();
        headers.add("scfKey", "338d9bf31f6078886fac171f8b1f9e39");
        String requestUrl = openApi + "waybillTms/getWaybillKeyPoint/" + waybillVO.getThirdId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, requestEntity, String.class);
        log.error("requestUrl: {}, response: \r\n{}", requestUrl, new Object[]{requestUrl, JSONObject.toJSONString(responseEntity)});
        if (com.fkhwl.starter.core.util.Tools.isNotBlank(responseEntity.getBody())) {
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            if ((Boolean) jsonObject.get("success")) {
                log.error("revoke getWaybillKeyPoint success");
                JSONObject data = (JSONObject)jsonObject.get("data");
                model.addAttribute("gpsInfo", data.get("gpsInfo"));
                model.addAttribute("gpsTypeList", data.get("gpsTypeList"));
                model.addAttribute("loadAddress", data.get("loadAddress"));
                model.addAttribute("arrivalAddress", data.get("arrivalAddress"));
                model.addAttribute("records", data.get("records"));
            } else {
                log.error("revoke getWaybillKeyPoint fail");
            }
        }*/

        return Views.WAYBILL_ROUTE_INFO_PAGE;
    }

    /**
     * 获取运单详情
     *
     */
    @ResponseBody
    @GetMapping(value = "/getDetail/{waybillId}")
    @ApiOperation(value = "获取运单详情", notes = "获取运单详情")
    @ApiImplicitParam(name = "waybillId",value = "运单id", paramType = "path")
    public Result<Map<String, Object>> getDetail(@PathVariable("waybillId") Long waybillId) {
        try {
            AssertUtils.notNull(waybillId, "参数错误");
            Map<String, Object> result = new HashMap<>();

            WaybillVO waybillVO = WaybillViewConverterWrapper.INSTANCE.vo(waybillProvider.getDetail(waybillId));
            AssertUtils.notNull(waybillVO, "运单信息错误");
            ProgramDTO program = programProvider.getDetail(waybillVO.getProgramId());
            result.put("waybill", waybillVO);
            result.put("program", program);

            return StandardResult.succeed(result);
        } catch (Exception e) {
            log.error("WaybillController getDetail error: {}", e.getMessage());
            return StandardResult.failed(e.getMessage());
        }
    }

    /**
     *  查询轨迹点
     * @param waybillId 运单id
     * @param maxRows   the max rows
     * @param pageNo    the page no
     * @param gpsType   1.OBD设备、2.客户端、3.云镜、4.中交 、5.短信定位、6.载重感知
     * @return ajax result
     */
    @ResponseBody
    @PostMapping(value="/listPageTrackPointInfo/{waybillId}/{gpsType}")
    @ApiOperation(value = "查询轨迹点", notes = "查询轨迹点")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "waybillId",value = "运单id", paramType = "path"),
        @ApiImplicitParam(name = "gpsType",value = "查询轨迹类型",paramType = "path"),
        @ApiImplicitParam(name = "pageNo",value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "maxRows",value = "分页大小", paramType = "query")
    })
    public Result<Map<String, Object>> listPageTrackPointInfo(@PathVariable(value = "waybillId") Long waybillId, @PathVariable(value = "gpsType") Integer gpsType,
                                                              Integer pageNo, Integer maxRows){
        try {
            if (pageNo == null) {
                pageNo = 1;
            }
            if (maxRows == null) {
                maxRows = 1000;
            }
            Map<String, Object> result = new HashMap<>();
            //查询轨迹点信息
            HttpHeaders headers = new HttpHeaders();
            headers.add("scfKey", "338d9bf31f6078886fac171f8b1f9e39");
            String requestUrl =  openApi + "waybillTms/listPageWaybillTrackPoint/" + waybillId + "/" + gpsType + "?pageNo=" +
                pageNo + "&pageSize=" + maxRows;
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, requestEntity, String.class);
            log.error("requestUrl: {}, response: \r\n{}", requestUrl, new Object[]{requestUrl, JSONObject.toJSONString(responseEntity)});
            if (com.fkhwl.starter.core.util.Tools.isNotBlank(responseEntity.getBody())) {
                JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
                log.error("waybill key point info: {}", jsonObject);
                if ((Boolean) jsonObject.get("success")) {
                    log.error("revoke listPageWaybillTrackPoint success");
                    JSONObject data = (JSONObject)jsonObject.get("data");
                    result.put("num", data.get("num"));
                    result.put("gps", data.get("gps"));
                } else {
                    log.error("revoke listPageWaybillTrackPoint fail");
                }
            }
            return StandardResult.succeed(result);
        } catch (Exception e) {
            log.error("AuthRoleController listPageTrackPointInfo error: {}", e.getMessage());
            return StandardResult.failed(e.getMessage());
        }
    }

    /**
     * 生成应收款转让
     *
     * @return the string
     */
    @ResponseBody
    @PostMapping(value = "/generateSubjectClaimsOrder")
    @ApiOperation(value = "生成应收款转让", notes = "生成应收款转让")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "projectId",value = "金融平台项目id",paramType = "query"),
        @ApiImplicitParam(name = "waybillIds",value = "运单id,多个之间使用英文逗号分隔", paramType = "query")
    })
    public Result<Void> generateSubjectClaimsOrder(String waybillIds, Long projectId) {
            AssertUtils.notNull(waybillIds, "参数错误");
            List<Long> waybillIdList = Tools.removeReplaceLong(waybillIds, Const.COMMA_CHAR);
            AssertUtils.notEmpty(waybillIdList, "参数错误");
            Map<String, Object> params = new HashMap<>();
            params.put("waybillIds", waybillIdList);
            params.put("projectId", projectId);
            params.put("ownerId", getSessionUser().getParentId());
            params.put("userId", getSessionUser().getId());
            params.put("newCreate", Boolean.TRUE);
            waybillProvider.generateSubjectClaimsOrder(params);
            return StandardResult.succeed();
    }

    /**
     * 获取运单操作历史
     *
     * @return the string
     */
    @ResponseBody
    @GetMapping(value = "/listWaybillOperationHistory/{waybillId}")
    @ApiOperation(value = "获取运单操作历史", notes = "获取运单操作历史")
    @ApiImplicitParam(name = "waybillId",value = "运单id", paramType = "path")
    public Result<List<WaybillOperationHistoryVO>> listWaybillOperationHistory(@PathVariable("waybillId") Long waybillId) {
            List<WaybillOperationHistoryVO> result = operationHistoryProvider.listByWaybillId(waybillId).stream().map
                (WaybillOperationHistoryViewConverterWrapper.INSTANCE::vo).collect(Collectors.toList());
            return StandardResult.succeed(result);
    }

    /**
     * 标记异常运单
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/signException/{waybillId}")
    @ApiOperation(value = "标记异常运单", notes = "标记异常运单")
    @ApiImplicitParam(name = "deptId",value = "运单id", required = true, paramType = "path")
    public Result<Void> signException(@PathVariable(value = "waybillId") Long waybillId) {
            AssertUtils.notNull(waybillId, "参数错误");
            Map<String, Object> params = new HashMap<>();
            params.put("waybillId", waybillId);
            params.put("waybillStatus", -1);
            WaybillDTO waybillDTO =  waybillProvider.getDetail(waybillId);
              //如果已经是异常了
            AssertUtils.isTrue(waybillDTO.getWaybillStatus()!=-1,"该运单已被处理");
            waybillProvider.updateByParams(params);
            return StandardResult.succeed();
    }

    /**
     * 删除运单
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{waybillId}")
    @ApiOperation(value = "删除运单", notes = "删除运单")
    @ApiImplicitParam(name = "deptId",value = "运单id", required = true, paramType = "path")
    public Result<Void> delete(@PathVariable(value = "waybillId") Long waybillId) {
            AssertUtils.notNull(waybillId, "参数错误");
            waybillProvider.delete(waybillId);
            return StandardResult.succeed();
    }


    /**
     * 查询发起用款申请页面的信息
     *
     */
    @ResponseBody
    @GetMapping(value = "/getReStartCreditApplyData")
    @ApiOperation(value = "获取运单详情", notes = "获取运单详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "creditApplyId",value = "运单号", paramType = "query"),
        @ApiImplicitParam(name = "counterpartyId",value = "开始时间",  paramType = "query")
    })
    public Result<Map<String, Object>> getReStartCreditApplyData(HttpServletRequest req) {
            Long creditApplyId = Long.valueOf(req.getParameter("creditApplyId"));
            Long counterpartyId = Long.valueOf(req.getParameter("counterpartyId"));
            Map<String, Object> reStartCreditApplyData = waybillProvider.getReStartCreditApplyData(creditApplyId, counterpartyId);
            return StandardResult.succeed(reStartCreditApplyData);

    }
}
