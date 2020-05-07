package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fkhwl.scf.SubjectClaimsOrderProvider;
import com.fkhwl.scf.entity.vo.SubjectClaimsOrderVO;
import com.fkhwl.scf.utils.ExcelUtil;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.web.excelExport.SubjectClaimsOrderExcel;
import com.fkhwl.scf.wrapper.SubjectClaimsOrderViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.util.BeanUtils;
import com.fkhwl.starter.core.util.Tools;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 标的债权订单 控制器
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
@Slf4j
@Controller
@RequestMapping("/subjectClaimsOrder")
@Api(value = "/subjectClaimsOrder", tags = "应收款转让信息相关接口")
public class SubjectClaimsOrderController extends BaseController {

        @Resource
        private SubjectClaimsOrderProvider subjectClaimsOrderProvider;


    /**
     * 标的债权订单列表
     *
     * @param pageNo 分页参数
     * @param counterpartyId 交易对手Id
     * @param subjectClaimsOrderNo 标的债权订单号
     * @param creditApplyId 用款申请id
     * @return 分页查询用户列表
     */
    @ResponseBody
    @PostMapping(value = "/listPage")
    @ApiOperation(value = "分页查询应收款转让列表", notes = "分页查询应收款转让列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNo",value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "counterpartyId",value = "交易对手id", paramType = "query"),
        @ApiImplicitParam(name = "subjectClaimsOrderNo",value = "应收帐款转让订单号", paramType = "query"),
        @ApiImplicitParam(name = "creditApplyId",value = "用款申请id", paramType = "query"),
        @ApiImplicitParam(name = "reviewStatus",value = "审核状态", paramType = "query")
    })
	public Result<IPage<SubjectClaimsOrderVO>> page(Integer pageNo,Long counterpartyId,String subjectClaimsOrderNo,Long creditApplyId, Integer reviewStatus,Long projectId) {
            if(Tools.isEmpty(counterpartyId)&&Tools.isEmpty(creditApplyId)){
                //既不是菜单进入也不是其他页面跳转的
                return StandardResult.succeed(new Page<>());
            }
            Map<String, Object> params = initListParam(counterpartyId, subjectClaimsOrderNo, creditApplyId, reviewStatus,projectId);
            params.put("page", pageNo);
            IPage<SubjectClaimsOrderVO> result = subjectClaimsOrderProvider.listPage(params).convert(SubjectClaimsOrderViewConverterWrapper.INSTANCE::vo);
            return StandardResult.succeed(result);
	}

    /**
     * 列表查询公共参数设定
     * @param counterpartyId
     * @param subjectClaimsOrderNo
     * @param creditApplyId
     * @param projectId
     * @return
     */
    @NotNull
    private Map<String, Object> initListParam(Long counterpartyId, String subjectClaimsOrderNo, Long creditApplyId, Integer reviewStatus, Long projectId) {
        Map<String, Object> params = initOwnerIdParams();
        params.put("counterpartyId", counterpartyId);
        params.put("projectId", projectId);
        if(counterpartyId!=null&&creditApplyId==null){
            reviewStatus=0;
        }
        params.put("creditApplyId", creditApplyId);
        params.put("subjectClaimsOrderNo", subjectClaimsOrderNo);
        params.put("reviewStatus", reviewStatus);
        return params;
    }

    /**
     * 标的债权订单列表
     *
     * @param counterpartyId 交易对手Id
     * @param subjectClaimsOrderNo 标的债权订单号
     * @param creditApplyId 用款申请id
     * @return 分页查询用户列表
     */
    @GetMapping(value = "/exportExcel")
    @ApiOperation(value = "导出应收账款转让订单明细", notes = "导出应收账款转让订单明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "counterpartyId",value = "交易对手id", paramType = "query"),
        @ApiImplicitParam(name = "subjectClaimsOrderNo",value = "应收帐款转让订单号", paramType = "query"),
        @ApiImplicitParam(name = "creditApplyId",value = "用款申请id", paramType = "query")
    })
    public void exportExcel(HttpServletResponse response, HttpServletRequest request, Long counterpartyId, String subjectClaimsOrderNo, Long creditApplyId, Integer reviewStatus,Long projectId) {
        Map<String, Object> params = initListParam(counterpartyId, subjectClaimsOrderNo, creditApplyId, reviewStatus, projectId);
        IPage<SubjectClaimsOrderVO> result = subjectClaimsOrderProvider.listPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)),params).convert(SubjectClaimsOrderViewConverterWrapper.INSTANCE::vo);
        String currentTime= ToolsHelper.formatDate2Str(new Date());
        List<SubjectClaimsOrderVO> waybillVOList=result.getRecords();
        if(BaseValidate.errorList(waybillVOList)){
            super.exportError(response,"暂无数据");
        }
        List<SubjectClaimsOrderExcel> waybillExcelList=new ArrayList<>();
        if(!BaseValidate.errorList(waybillVOList)){
            for(SubjectClaimsOrderVO item:waybillVOList){
                SubjectClaimsOrderExcel tmp=new SubjectClaimsOrderExcel();
                BeanUtils.copy(item,tmp);
                waybillExcelList.add(tmp);
            }
        }
        if (!ExcelUtil.export(SubjectClaimsOrderExcel.class, waybillExcelList, response, "应收账款转让订单-"+currentTime, "应收账款转让订单明细", "exportExcel", request)) {
            log.error("export excel error");
            super.exportError(response,"导出excel错误");
        }
    }

    /**
     * 计算发起用款申请率的 应收账款到期日，融资利率
     *
     * @param subjectClaimsOrderIds 标的债权订单号
     * @return 分页查询用户列表
     */
    @ResponseBody
    @RequestMapping(value = "/calculate")
    public Result<Map<String ,Object>> calculate(String subjectClaimsOrderIds,Long counterpartyId) {
           Map<String ,Object> result = subjectClaimsOrderProvider.calculate(subjectClaimsOrderIds,counterpartyId);
            return StandardResult.succeed(result);
    }
}
