package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.AccountBillProvider;
import com.fkhwl.scf.entity.dto.AccountBillRateDTO;
import com.fkhwl.scf.entity.form.RepayBillForm;
import com.fkhwl.scf.entity.vo.AccountBillListVO;
import com.fkhwl.scf.entity.vo.AccountBillRateVO;
import com.fkhwl.scf.entity.vo.AccountBillVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.enums.RepayStatus;
import com.fkhwl.scf.utils.ExcelUtil;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.web.excelExport.AccountBillClientExcel;
import com.fkhwl.scf.web.excelExport.AccountBillExcel;
import com.fkhwl.scf.web.util.PageData;
import com.fkhwl.scf.wrapper.AccountBillRateViewConverterWrapper;
import com.fkhwl.scf.wrapper.AccountBillViewConverterWrapper;
import com.fkhwl.scf.wrapper.RepayBillViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.util.BeanUtils;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.core.util.StringUtils;
import com.fkhwl.starter.core.util.Tools;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/accountBill")
@Api(value = "/accountBill", tags = "账单接口")
public class AccountBillController extends BaseController {

    @Resource
    private AccountBillProvider accountBillProvider;


    /**
     * 列表
     *
     * @param billNo      编号
     * @param repayStatus 还款状态
     * @param dueStatus   到期状态
     * @param pageNo      分页参数
     * @return 分页查询用户列表
     */
    @ResponseBody
    @PostMapping(value = "/listPage")
    @ApiOperation(value = "账单管理列表查询")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "companyName", value = "客户名称", paramType = "query"),
        @ApiImplicitParam(name = "counterpartyName", value = "交易对手名称", paramType = "query"),
        @ApiImplicitParam(name = "creditApplyNo", value = "时间编号", paramType = "query"),
        @ApiImplicitParam(name = "searchTimeType", value = "时间类型", paramType = "query"),
        @ApiImplicitParam(name = "beginTime", value = "开始时间", paramType = "query"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query"),
        @ApiImplicitParam(name = "dueDay", value = "N天到期", paramType = "query"),
        @ApiImplicitParam(name = "repayStatus", value = "还款状态1.未还款2.还款中 3已还款", paramType = "query"),
        @ApiImplicitParam(name = "dueStatus", value = "到期状态0.未到期.1已到期", paramType = "query"),
        @ApiImplicitParam(name = "graceStatus", value = "超期状态0.超期.1超期", paramType = "query")
    })
    public Result<IPage<AccountBillListVO>> page(Integer pageNo, String companyName, String counterpartyName, String creditApplyNo, Integer searchTimeType, String beginTime, String overTime,
                                                 Integer repayDate, Integer repayStatus, Integer dueStatus, Integer graceStatus) {
            Map<String, Object> params = initListParam(companyName, counterpartyName, creditApplyNo, searchTimeType, beginTime, overTime, repayDate, repayStatus, dueStatus, graceStatus);
            params.put("page", pageNo);
            IPage<AccountBillListVO> result = new PageData<>();
            if (params.get("errorQuery") != null) {
                //搜索条件问题
                return StandardResult.succeed(result);
            }
            result = accountBillProvider.listAccountBillListPage(Condition.getPage(params), params);
            return StandardResult.succeed(result);
    }

    private Map<String, Object> initListParam(String companyName, String counterpartyName, String creditApplyNo, Integer searchTimeType, String beginTime, String overTime, Integer repayDate,
                                              Integer repayStatus, Integer dueStatus, Integer graceStatus) {
        Map<String, Object> params = initOwnerIdParams();
        params.put("companyName", companyName);
        params.put("counterpartyName", counterpartyName);
        params.put("creditApplyNo", creditApplyNo);
        params.put("searchTimeType", searchTimeType);
        params.put("beginTime", StringUtils.isBlank(beginTime)?null:beginTime+" 00:00:00");
        params.put("overTime", StringUtils.isBlank(overTime)?null:overTime+" 00:00:00");
        params.put("repayStatus", repayStatus);
        params.put("dueStatus", dueStatus);
        if (Tools.isNotEmpty(repayDate)) {
            //如果有近期还款参数
            Date date = DateUtils.plusDays(DateUtils.now(), repayDate);
            params.put("repayDate", date);
            //如果查询条件配置错误，直接返回空数据  近期到期+结清，直接返回空数据
            if (repayStatus.equals(RepayStatus.ALREADY_REPAY.getValue())) {
                params.put("errorQuery",true);
            }

        }
        return params;
    }


    /**
     * 列表查询公共参数设定
     *
     * @param billNo
     * @param repayStatus
     * @param dueStatus
     * @return
     */
    @NotNull
    private Map<String, Object> initListParam(String billNo, Integer repayStatus, Integer dueStatus) {
        Map<String, Object> params = initOwnerIdParams();
        params.put("billNo", billNo);
        params.put("repayStatus", repayStatus);
        params.put("dueStatus", dueStatus);
        return params;
    }

    /**
     * 列表
     *
     * @param billNo      编号
     * @param repayStatus 还款状态
     * @param dueStatus   到期状态
     * @return 分页查询用户列表
     */
    @GetMapping(value = "/exportExcel")
    @ApiOperation(value = "导出账单明细", notes = "导出账单明细")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "billNo", value = "账单编号", paramType = "query"),
        @ApiImplicitParam(name = "repayStatus", value = "还款状态1.未还款2.还款中 3已还款", paramType = "query"),
        @ApiImplicitParam(name = "dueStatus", value = "逾期状态0.未到期.1已到期", paramType = "query")
    })
    public void exportExcel(HttpServletResponse response, HttpServletRequest request,  String companyName, String counterpartyName, String creditApplyNo, Integer searchTimeType, String beginTime, String overTime,
                            Integer repayDate, Integer repayStatus, Integer dueStatus, Integer graceStatus) {
        Map<String, Object> params = initListParam(companyName, counterpartyName, creditApplyNo, searchTimeType, beginTime, overTime, repayDate, repayStatus, dueStatus, graceStatus);
        IPage<AccountBillListVO> result = accountBillProvider.listAccountBillListPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)),params);
        //属性处理
        String currentTime = ToolsHelper.formatDate2Str(new Date());
        List<AccountBillListVO> waybillVOList = result.getRecords();
        List<AccountBillExcel> waybillExcelList = new ArrayList<>();
        List<AccountBillClientExcel> accountBillClientExcels = new ArrayList<>();
        if (!BaseValidate.errorList(waybillVOList)) {
            if(isBorrower(getSessionUser())){
                for (AccountBillVO item : waybillVOList) {
                    AccountBillClientExcel tmp = new AccountBillClientExcel();
                    BeanUtils.copy(item, tmp);
                    tmp.setRepayStatusStr(tmp.getRepayStatusToExcel());
                    tmp.setDueStatusStr(tmp.getDueStatusToExcel());
                    tmp.setGraceStatusStr(tmp.getGraceStatusExcel());
                    accountBillClientExcels.add(tmp);
                }
                if (!ExcelUtil.export(AccountBillClientExcel.class, accountBillClientExcels, response, "账单明细-" + currentTime, "账单明细", "exportExcel", request)) {
                    log.error("export excel error");
                    super.exportError(response,"导出异常");
                }
            }else if(isFounding(getSessionUser())){
                for (AccountBillVO item : waybillVOList) {
                    AccountBillExcel tmp = new AccountBillExcel();
                    BeanUtils.copy(item, tmp);
                    tmp.setRepayStatusStr(tmp.getRepayStatusToExcel());
                    tmp.setDueStatusStr(tmp.getDueStatusToExcel());
                    tmp.setGraceStatusStr(tmp.getGraceStatusExcel());
                    waybillExcelList.add(tmp);
                }
                if (!ExcelUtil.export(AccountBillExcel.class, waybillExcelList, response, "账单明细-" + currentTime, "账单明细", "exportExcel", request)) {
                    log.error("export excel error");
                    super.exportError(response,"导出异常");
                }
            }

        }else{
            super.exportError(response,"暂无数据");
        }

    }


    /**
     * 账单管理
     *
     * @param repayStatus 还款状态
     * @param dueStatus   到期状态
     * @param pageNo      分页参数
     * @return 分页查询用户列表
     */
    @ResponseBody
    @ApiOperation(value = "账单管理列表查询")
    @PostMapping(value = "/listRepayPage")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "companyName", value = "项目主体、客户名称", paramType = "query"),
        @ApiImplicitParam(name = "counterpartyName", value = "交易对手名称", paramType = "query"),
        @ApiImplicitParam(name = "creditApplyNo", value = "用款申请订单号0.未到期.1已到期", paramType = "query"),
        //        @ApiImplicitParam(name = "subjectClaimsOrderNo",value = "应收账款订单号", paramType = "query"),
        @ApiImplicitParam(name = "repayStatus", value = "还款状态1.未还款2.还款中 3已还款", paramType = "query"),
        @ApiImplicitParam(name = "dueStatus", value = "逾期状态0.未到期.1已到期", paramType = "query"),
        @ApiImplicitParam(name = "repayDate", value = "N天到期日查询条件", paramType = "query")
    })
    public Result<IPage<Map<String, Object>>> listRepayPage(Integer pageNo, String companyName, String counterpartyName, String creditApplyNo, String subjectClaimsOrderNo, Integer repayStatus,
                                                            Integer dueStatus, Integer repayDate) {
            Map<String, Object> params = initOwnerIdParams();
            params.put("companyName", companyName);
            params.put("counterpartyName", counterpartyName);
            params.put("creditApplyNo", creditApplyNo);
            params.put("subjectClaimsOrderNo", subjectClaimsOrderNo);
            params.put("repayStatus", repayStatus);
            params.put("dueStatus", dueStatus);
            params.put("page", pageNo);
            if (Tools.isNotEmpty(repayDate)) {
                //如果有近期还款参数
                Date date = DateUtils.plusDays(DateUtils.now(), repayDate);
                params.put("repayDate", date);

            }
            IPage<Map<String, Object>> result = accountBillProvider.listRepayPage(params);
            return StandardResult.succeed(result);
    }

    /**
     * 账单详情
     */
    @ResponseBody
    @GetMapping(value = "/getDetail")
    @ApiOperation(value = "还款管理列表查询")
    @ApiImplicitParam(name = "id", value = "账单id", paramType = "query")
    public Result<AccountBillVO> getDetail(Long id) {
            AccountBillVO accountBillVO = AccountBillViewConverterWrapper.INSTANCE.vo(accountBillProvider.getDetail(id));
            return StandardResult.succeed(accountBillVO);
    }

    /**
     * 提交还款申请
     */
    @ResponseBody
    @PostMapping(value = "/repayBill")
    @ApiOperation(value = "提交还款申请", notes = "传入accountBillRateForm")
    //    @ApiImplicitParams( {
    //        @ApiImplicitParam(name = "accountBillIds", value = "账单id:以逗号分隔", paramType = "query"),
    //        @ApiImplicitParam(name = "repayDateStr", value = "还款日期：2019-02-02", paramType = "query"),
    //        @ApiImplicitParam(name = "repayBalance", value = "还款金额：2019-02-02", paramType = "query")
    //    })

    public Result<String> repayBill(@ApiParam(value = "还款form实体", required = true) RepayBillForm repayBillForm) {
            ScfUserVO scfUserVO = getSessionUser();
            repayBillForm.setCreateUserId(scfUserVO.getId());
            repayBillForm.setOwnerId(scfUserVO.getOwnerId());
            accountBillProvider.repayBill(RepayBillViewConverterWrapper.INSTANCE.dto(repayBillForm));
            return StandardResult.succeed();
    }

    /**
     * 账单详情
     */
    @ResponseBody
    @PostMapping(value = "/notify")
    @ApiOperation(value = "账单提醒")
    @ApiImplicitParam(name = "accountBillId", value = "账单id", paramType = "query")
    public Result<String> notify(Long accountBillId) {
            ScfUserVO scfUserVO = getSessionUser();
            String result = accountBillProvider.notify(accountBillId,scfUserVO);
            if (Boolean.valueOf(result)) {
                //推送成功
                return StandardResult.succeed();
            } else {
                return StandardResult.failed();
            }
    }

    /**
     * 账单还款时，通过还款本金，计算需要还款的利息
     */
    @ResponseBody
    @PostMapping(value = "/calculateRate")
    @ApiOperation(value = "账单还款")
    @ApiImplicitParams( {
        @ApiImplicitParam(name = "repayBalance", value = "还款本金金额", paramType = "query"),
        @ApiImplicitParam(name = "repayDateStr", value = "还款日期", paramType = "query"),
        @ApiImplicitParam(name = "counterpartyId", value = "交易对手id", paramType = "query")
    })
    public Result<List<AccountBillRateVO>> calculateRate(BigDecimal repayBalance, String repayDateStr, Long counterpartyId) {
            List<AccountBillRateDTO> list = accountBillProvider.calculateRate(repayBalance, repayDateStr, counterpartyId, getSessionUser().getId(), getSessionUser().getOwnerId());
            return StandardResult.succeed(list.stream().map(AccountBillRateViewConverterWrapper.INSTANCE::vo).collect(Collectors.toList()));
    }
}
