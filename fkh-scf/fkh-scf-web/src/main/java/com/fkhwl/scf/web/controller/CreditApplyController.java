package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CounterpartyProvider;
import com.fkhwl.scf.CreditApplyProvider;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.form.CreditApplyForm;
import com.fkhwl.scf.entity.vo.CreditApplyVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.utils.ExcelUtil;
import com.fkhwl.scf.utils.FileUploadHelper;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.web.config.SystemConfig;
import com.fkhwl.scf.web.excelExport.CreditApplyExcel;
import com.fkhwl.scf.web.excelExport.CreditApplyListExcel;
import com.fkhwl.scf.web.excelExport.CreditApplyLoanExcel;
import com.fkhwl.scf.web.excelExport.CreditApplyOverExcel;
import com.fkhwl.scf.web.excelExport.LoanListExcel;
import com.fkhwl.scf.wrapper.CreditApplyViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.util.BeanUtils;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
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
@RequestMapping("/creditApply")
@Api(value = "/creditApply", tags = "用款申请相关接口")
@AllArgsConstructor
public class CreditApplyController extends BaseController {

    @Resource
    private CreditApplyProvider creditApplyProvider;
    @Resource
    private CounterpartyProvider counterpartyProvider;
    private SystemConfig systemConfig;


    /**
     *列表
     *
     * @param creditApplyNo 编号
     * @param pageNo 分页参数
     * @return 分页查询用户列表
     */
    @ResponseBody
    @PostMapping(value = "/listPage")
    @ApiOperation(value = "分页查询用款申请列表", notes = "分页查询用款申请列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "creditApplyNo",value = "用款申请编号", paramType = "query"),
        @ApiImplicitParam(name = "pageNo",value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "status",value = "用款申请状态", paramType = "query"),
        @ApiImplicitParam(name = "isReviewRecord",value = "是否是审核记录", paramType = "query")
    })
	public Result<IPage<CreditApplyListDTO>> page(Integer pageNo, String creditApplyNo,Integer status,String companyName, Boolean isReviewRecord) {
            Map<String, Object> params = initListParam(creditApplyNo, status, companyName,isReviewRecord);
            params.put("page", pageNo);
            IPage<CreditApplyListDTO> result = creditApplyProvider.listCreditApplyPage(params);
            return StandardResult.succeed(result);
	}

    @NotNull
    private Map<String, Object> initListParam(String creditApplyNo, Integer status,String companyName, Boolean isReviewRecord) {
        Map<String, Object> params = initOwnerIdParams();
        params.put("creditApplyNo", creditApplyNo);
        params.put("status", status);
        params.put("companyName", companyName);
        params.put("isReviewRecord", isReviewRecord);
        if(isFounding(getSessionUser())){
            params.put("roleId", getSessionUser().getRoleId());
        }
        return params;
    }

    /**
     * 导出列表
     *
     * @param creditApplyNo 编号
     * @return 分页查询用户列表
     */
    @GetMapping(value = "/exportExcel")
    @ApiOperation(value = "导出用款申请列表", notes = "导出用款申请列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "creditApplyNo",value = "用款申请编号", paramType = "query"),
        @ApiImplicitParam(name = "status",value = "用款申请状态", paramType = "query")
    })
    public void exportExcel(HttpServletResponse response, HttpServletRequest request, String creditApplyNo,String companyName, Integer status,Integer type, Boolean isReviewRecord) {
        Map<String, Object> params = initListParam(creditApplyNo, status, companyName,isReviewRecord);
        IPage<CreditApplyListDTO> result = creditApplyProvider.listCreditApplyPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)),params);
        String currentTime= ToolsHelper.formatDate2Str(new Date());

        List<CreditApplyListDTO> waybillVOList=result.getRecords();
        if(type==null || BaseValidate.errorList(waybillVOList)){
            super.exportError(response,"暂无数据");
        }
          if(type==1){
              List<CreditApplyExcel> waybillExcelList=new ArrayList<>();
              for(CreditApplyListDTO item:waybillVOList){
                  CreditApplyExcel tmp=new CreditApplyExcel();
                  BeanUtils.copy(item,tmp);
                  tmp.setStatusStr(tmp.getStatusName());
                  waybillExcelList.add(tmp);
              }
              if (!ExcelUtil.export(CreditApplyExcel.class, waybillExcelList, response, "用款申请记录-"+currentTime, "用款申请-审核中", "exportExcel", request)) {
                  log.error("export excel error");
                  super.exportError(response,"导出异常");
              }
          }  else if(type==2){
              List<CreditApplyLoanExcel> waybillExcelList=new ArrayList<>();
              for(CreditApplyListDTO item:waybillVOList){
                  CreditApplyLoanExcel tmp=new CreditApplyLoanExcel();
                  BeanUtils.copy(item,tmp);
                  waybillExcelList.add(tmp);
              }
              if (!ExcelUtil.export(CreditApplyLoanExcel.class, waybillExcelList, response, "用款申请记录-"+currentTime, "用款申请-已放款", "exportExcel", request)) {
                  log.error("export excel error");
                  super.exportError(response,"导出异常");
              }
          } else if(type==3){
              List<CreditApplyOverExcel> waybillExcelList=new ArrayList<>();
              for(CreditApplyListDTO item:waybillVOList){
                  CreditApplyOverExcel tmp=new CreditApplyOverExcel();
                  BeanUtils.copy(item,tmp);
                  tmp.setApplyBalanceReturn(tmp.getLoanBalance());
                  waybillExcelList.add(tmp);
              }
              if (!ExcelUtil.export(CreditApplyOverExcel.class, waybillExcelList, response, "用款申请记录-"+currentTime, "用款申请-已结清", "exportExcel", request)) {
                  log.error("export excel error");
                  super.exportError(response,"导出异常");
              }
          } else if(type==9){//放款管理
            List<LoanListExcel> waybillExcelList=new ArrayList<>();
            for(CreditApplyListDTO item:waybillVOList){
                LoanListExcel tmp=new LoanListExcel();
                BeanUtils.copy(item,tmp);
                waybillExcelList.add(tmp);
            }
            if (!ExcelUtil.export(LoanListExcel.class, waybillExcelList, response, "放款订单-"+currentTime, "放款订单", "exportExcel", request)) {
                log.error("export excel error");
                super.exportError(response,"导出异常");
            }
        }else{
            List<CreditApplyListExcel> waybillExcelList=new ArrayList<>();
            for(CreditApplyListDTO item:waybillVOList){
                CreditApplyListExcel tmp=new CreditApplyListExcel();
                BeanUtils.copy(item,tmp);
                waybillExcelList.add(tmp);
            }
            if (!ExcelUtil.export(CreditApplyListExcel.class, waybillExcelList, response, "放款申请订单-"+currentTime, "放款申请订单", "exportExcel", request)) {
                log.error("export excel error");
                super.exportError(response,"导出异常");
            }
        }
    }
    /**
     * 导出审批单
     *
     * @param creditApplyNo 编号
     * @return 分页查询用户列表
     */
    @GetMapping(value = "/exportReviewBill")
    @ApiOperation(value = "导出审批单", notes = "导出审批单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "reviewBillUrl",value = "用款申请编号", paramType = "query"),
    })
    public void exportReviewBill(HttpServletResponse response, HttpServletRequest request, String reviewBillUrl) {

        try {
            String reviewUrl = systemConfig.getReviewBillPath() + reviewBillUrl;
            ExcelUtil.export(response, request, "资金审批单-" + DateUtils.formatDate(new Date()), new XSSFWorkbook(new FileInputStream(reviewUrl)));
        } catch (FileNotFoundException e) {
            log.error("not find reviewBill file fileName:{}",reviewBillUrl);
            super.exportError(response, "未找到审批单");
        } catch (IOException e) {
            log.error("export reviewbill file error：{}",reviewBillUrl);
            super.exportError(response, "下载资金审批单异常异常");
        }
        //        if(type!=null && type==9){
//            //放款管理
//
//        }else{
//            List<CreditApplyListExcel> waybillExcelList=new ArrayList<>();
//            if(!BaseValidate.errorList(waybillVOList)){
//                for(CreditApplyListDTO item:waybillVOList){
//                    CreditApplyListExcel tmp=new CreditApplyListExcel();
//                    BeanUtils.copy(item,tmp);
//                    waybillExcelList.add(tmp);
//                }
//            }else{
//                super.exportError(response,"暂无数据");
//            }
//            if (!ExcelUtil.export(CreditApplyListExcel.class, waybillExcelList, response, "放款申请订单-"+currentTime, "放款申请订单", "exportExcel", request)) {
//                log.error("export excel error");
//                super.exportError(response,"导出异常");
//            }
//        }
    }
    /**
     *列表
     *
     * @return 分页查询用户列表
     */
    @ResponseBody
    @GetMapping(value = "/getDetail/{creditApplyId}")
    @ApiOperation(value = "id查询用款申请详情", notes = "分页查询用款申请列表")
    @ApiImplicitParam(name = "creditApplyId",value = "用款申请id", paramType = "path")
    public Result<CreditApplyVO> getDetail(@PathVariable("creditApplyId") Long creditApplyId) {
            CreditApplyVO creditApply= CreditApplyViewConverterWrapper.INSTANCE.vo(creditApplyProvider.getDetail(creditApplyId));
            return StandardResult.succeed(creditApply);
    }
    /**
     * 创建用信
     *
     */
    @PostMapping("/create")
    @ApiOperation(value = "新增或修改", notes = "传入creditApplyForm")
    @ResponseBody
    public Result<String> create(@ApiParam(value = "用款申请form实体", required = true)CreditApplyForm creditApplyForm) {
          CreditApplyDTO creditApplyDTO =  CreditApplyViewConverterWrapper.INSTANCE.dto(creditApplyForm);
            ScfUserVO scfUserVO = getSessionUser();
            creditApplyDTO.setOwnerId(scfUserVO.getParentId());
            creditApplyDTO.setCreateUserId(scfUserVO.getId());
            creditApplyProvider.createCreditApply(creditApplyDTO,creditApplyForm.getSubjectClaimsOrderIds());
            return StandardResult.succeed();
    }


    /**
     * 用款申请审核
     *
     */
    @ResponseBody
    @PostMapping(value = "/review")
    @ApiOperation(value = "用款申请审核", notes = "用款申请审核")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "creditApplyId",value = "用款申请id", required = true, paramType = "query"),
        @ApiImplicitParam(name = "passStatus",value = "同意或者拒绝", required = true, paramType = "query"),
        @ApiImplicitParam(name = "reviewReason",value = "审核意见", required = true, paramType = "query"),
        @ApiImplicitParam(name = "hasCharge",value = "是否生效", required = true, paramType = "query")
    })
    public Result<String> review(Long creditApplyId, Boolean passStatus, String reviewReason,Boolean hasCharge) throws Exception {
            ScfUserVO scfUserVO= getSessionUser();
            String reviewBillUrl="";
            Map<String,Object> map =   creditApplyProvider.review(creditApplyId,passStatus,reviewReason,scfUserVO.getId(),hasCharge,scfUserVO.getParentId(),scfUserVO.getCompanyId(),systemConfig.getReviewBillUrl());
          if(map!=null){
              reviewBillUrl = map.get("fileName").toString();
              FileUploadHelper.saveUploadFile(map.get("fileName").toString(),
                  systemConfig.getReviewBillPath(),
                  null, null, (byte[]) map.get("excelBytes"));
          }
            return StandardResult.succeed(reviewBillUrl);
    }

    /**
     * 重新发起用款申请
     *
     */
    @PostMapping("/reCreate")
    @ApiOperation(value = "重新发起用款申请", notes = "传入creditApplyForm")
    @ResponseBody
    public Result<String> reCreate(@ApiParam(value = "用款申请form实体", required = true)CreditApplyForm creditApplyForm) {
            CreditApplyDTO creditApplyDTO =  CreditApplyViewConverterWrapper.INSTANCE.dto(creditApplyForm);
            ScfUserVO scfUserVO = getSessionUser();
            creditApplyDTO.setOwnerId(scfUserVO.getParentId());
            creditApplyDTO.setCreateUserId(scfUserVO.getId());
            creditApplyProvider.reCreateCreditApply(creditApplyDTO);
            return StandardResult.succeed();
    }
}
