package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.RepayBillProvider;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.vo.RepayBillVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.utils.ExcelUtil;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.web.excelExport.PayeeBillExcel;
import com.fkhwl.scf.web.excelExport.RepayBillExcel;
import com.fkhwl.scf.wrapper.RepayBillViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.util.BeanUtils;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.core.util.StringUtils;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import lombok.extern.slf4j.Slf4j;

/**
 * 账单 控制器
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
@Slf4j
@Controller
@RequestMapping("/repayBill")
@Api(value = "/repayBill", tags = "账单接口")
public class RepayBillController extends BaseController {

        @Resource
        private RepayBillProvider repayBillProvider;



    /**
     * 还款订单确认审核
     *
     */
    @ResponseBody
    @PostMapping(value = "/reviewRepayBill")
    @ApiOperation(value = "审核还款确认")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "repayBillId", value = "还款申请ID", paramType = "query"),
    })
    public Result<String> reviewRepayBill(Long repayBillId,Boolean passStatus,String reviewReason) {
            repayBillProvider.reviewRepayBill(repayBillId,passStatus,getSessionUser().getCompanyId(),reviewReason);
            return StandardResult.succeed();
    }

    /**
     * 检查是否存在正在审核的还款申请
     *
     */
    @ResponseBody
    @PostMapping(value = "/checkHadRepay")
    @ApiOperation(value = "检查是否存在正在审核的还款申请")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "counterpartyId", value = "交易对手id", paramType = "query"),
    })
    public Result<Map<String,Object>> checkHadRepay(Long counterpartyId) {
            return StandardResult.succeed(repayBillProvider.checkHadRepayAndRemainBalance(counterpartyId));
    }
    /**
     * 收款列表
     *
     * @param params 分页参数
     * @return 分页查询项目列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询收款列表")
    @ResponseBody
    public Result<IPage<RepayBillVO>> page(@RequestParam Map<String, Object> params) {
       this.initParams(params);
        IPage<RepayBillDTO> page = repayBillProvider.listPage(Condition.getPage(params), params);
        IPage<RepayBillVO> result = page.convert(RepayBillViewConverterWrapper.INSTANCE::vo);
        return StandardResult.succeed(result);
    }

    public void initParams(Map<String, Object> params) {
        super.fixPageNo(params);
        params.putAll(initOwnerIdParams());
        ScfUserVO sessionUser = getSessionUser();
        params.put("companyId", sessionUser.getCompanyId());
        if (params.get("searchTimeType") != null && params.get("overTime") != null&& !params.get("overTime").equals("")) {
            params.put("overTime", params.get("overTime").toString() + " 23:59:59");
        }
    }
    /**
     * 收款列表
     *
     * @param params 分页参数
     * @return 分页查询项目列表
     */
    @GetMapping("/exportExcel")
    @ApiOperation(value = "导出收款列表")
    public void exportExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) {
        this.initParams(params);
        IPage<RepayBillDTO> page = repayBillProvider.listPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)), params);
        IPage<RepayBillVO> result = page.convert(RepayBillViewConverterWrapper.INSTANCE::vo);
        //属性处理
        String currentTime = ToolsHelper.formatDate2Str(new Date());
        List<RepayBillVO> waybillVOList = result.getRecords();
        if("1".equals(params.get("from"))){
            //借款方导出还款记录
            List<RepayBillExcel> waybillExcelList = new ArrayList<>();
            if (!BaseValidate.errorList(waybillVOList)) {
                for (RepayBillVO item : waybillVOList) {
                    RepayBillExcel tmp = new RepayBillExcel();
                    BeanUtils.copy(item, tmp);
                    tmp.setStatusStr(tmp.getStatusToExcel());
                    tmp.setRepayDateStr(DateUtils.formatDate(item.getRepayDate()));
                    waybillExcelList.add(tmp);
                }
            }else{
                super.exportError(response,"暂无数据");
            }
            if (!ExcelUtil.export(RepayBillExcel.class, waybillExcelList, response, "还款明细-" + currentTime, "还款明细", "exportExcel", request)) {
                log.error("export excel error");
                super.exportError(response,"导出异常");
            }
        }else{
            //资金方导出收款记录
            List<PayeeBillExcel> waybillExcelList = new ArrayList<>();
            if (!BaseValidate.errorList(waybillVOList)) {
                for (RepayBillVO item : waybillVOList) {
                    PayeeBillExcel tmp = new PayeeBillExcel();
                    BeanUtils.copy(item, tmp);
                    tmp.setTotalBalance(tmp.getRepayBalance().add(tmp.getInterestRateBalance()).add(tmp.getOverdueRateBalance()).add(tmp.getGraceRateBalance()));
                    waybillExcelList.add(tmp);
                }
            }else{
                super.exportError(response,"暂无数据");
            }
            if (!ExcelUtil.export(PayeeBillExcel.class, waybillExcelList, response, "收款明细-" + currentTime, "收款明细", "exportExcel", request)) {
                log.error("export excel error");
                super.exportError(response,"导出异常");
            }
        }
    }
}
