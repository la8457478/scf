package com.fkhwl.scf.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.fkhwl.scf.AccountBillProvider;
import com.fkhwl.scf.AuthRoleProvider;
import com.fkhwl.scf.ChinaAreaProvider;
import com.fkhwl.scf.CompanyContractProvider;
import com.fkhwl.scf.CompanyProvider;
import com.fkhwl.scf.CounterpartyProvider;
import com.fkhwl.scf.CreditApplyProvider;
import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.ScfUserConfigProvider;
import com.fkhwl.scf.SubjectClaimsOrderProvider;
import com.fkhwl.scf.WaybillCheckRecordProvider;
import com.fkhwl.scf.WaybillOperationHistoryProvider;
import com.fkhwl.scf.WaybillProvider;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;
import com.fkhwl.scf.entity.vo.AccountBillVO;
import com.fkhwl.scf.entity.vo.CreditApplyVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.entity.vo.WaybillVO;
import com.fkhwl.scf.enums.CreditApplyStatus;
import com.fkhwl.scf.enums.ScfConfigEnum;
import com.fkhwl.scf.web.config.SystemConfig;
import com.fkhwl.scf.web.util.Views;
import com.fkhwl.scf.wrapper.AccountBillViewConverterWrapper;
import com.fkhwl.scf.wrapper.CompanyViewConverterWrapper;
import com.fkhwl.scf.wrapper.CreditApplyViewConverterWrapper;
import com.fkhwl.scf.wrapper.WaybillViewConverterWrapper;
import com.fkhwl.starter.autoconfigure.exception.ValidationException;
import com.fkhwl.starter.core.support.AssertUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 跳转页面
 *
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Controller
public class HomeController extends BaseController {

    @Resource
    private AccountBillProvider accountBillProvider;
    @Resource
    private AuthRoleProvider authRoleProvider;

    @Resource
    private CounterpartyProvider counterpartyProvider;

    @Resource
    private CreditApplyProvider creditApplyProvider;

    @Resource
    private ChinaAreaProvider chinaAreaProvider;

    @Resource
    private WaybillProvider waybillProvider;

    @Resource
    private WaybillOperationHistoryProvider operationHistoryProvider;

    @Resource
    private WaybillCheckRecordProvider waybillCheckRecordProvider;

    @Resource
    private ScfUserConfigProvider scfUserConfigProvider;
    @Resource
    private CompanyContractProvider companyContractProvider;
    @Resource
    private CompanyProvider companyProvider;
    @Autowired
    private SystemConfig systemConfig;

    @Resource
    private SubjectClaimsOrderProvider subjectClaimsOrderProvider;

    @Resource
    private ProjectProvider projectProvider;

    /**
     * 首页主体内容
     *
     * @return the string
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String toHome(Model model, HttpServletRequest req) {
        ScfUserVO user = super.getSessionUser();
        model.addAttribute("companyType",user.getCompanyType());
        return Views.HOME_PAGE;
    }

    /**
     * 首页主体内容
     *
     * @return the string
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpServletRequest req) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        //            TemplateUserDTO sessionAttrs = userDubboClient.findInfo(1L);
        final Map principal = (Map) subject.getPrincipal();
        ScfUserVO user = (ScfUserVO) principal.get("user");

        if (!isAdmin(user)) {
            //            OilUser oilUser = onlineUser.getOilUser();
            //            model.addAttribute("userBalancePwd", oilUser.getPassword());
            //            model.addAttribute("oilUser", oilUser);
            //            if (oilUser.getRoleId() == EnumUtils.OilUserRoleType.COMPANY.type) {//加油站负责人,公司管理
            //                OilSiteCompany company = oilSiteCompanyService.getCompanyByUserId(oilUser.getId());
            //                if (company != null) {
            //                    OilSite oilSite = new OilSite();
            //                    oilSite.setCompanyName(company.getCompanyName());
            //                    model.addAttribute("oilSite", oilSite);
            //                }
            //            } else if (oilUser.getRoleId() == EnumUtils.OilUserRoleType.SITE.type
            //                || oilUser.getRoleId() == EnumUtils.OilUserRoleType.OPERATOR.type) {//加油站负责人 //加油站操作人员
            //                OilSite oilSite = oilSiteService.get(onlineUser.getOilSiteId());
            //                model.addAttribute("oilSite", oilSite);
            //            }

            model.addAttribute("onlineUser", user);

            //            isFinancial = null == isFinancial ? 0 : 1;
            //            model.addAttribute("isFinancial", isFinancial);
        }

        return "profile/personalSettings";
    }
    //****************************************************运营端页面跳转*****************************************************

    /**
     * 跳转运单列表
     *
     * @return the string
     */
    @RequestMapping(value = "/waybillList")
    public String toWaybillListPage(Model model, HttpServletRequest req) {
        String projectId = req.getParameter("projectId");
        String subjectClaimsOrderId = req.getParameter("subjectClaimsOrderId");
        String creditApplyId = req.getParameter("creditApplyId");
        String isTransferred = req.getParameter("isTransferred");
        String returnUrl = req.getParameter("returnUrl");
        if (creditApplyId != null && !creditApplyId.isEmpty()) {
            CreditApplyDTO creditApplyDTO = creditApplyProvider.getDetail(Long.valueOf(creditApplyId));
            AssertUtils.notNull(creditApplyDTO, "用款申请不存在");
            CounterpartyDTO counterpartyDTO = counterpartyProvider.findInfo(creditApplyDTO.getCounterpartyId());
            AssertUtils.notNull(counterpartyDTO, "交易对手不存在");
            model.addAttribute("counterpartyName", counterpartyDTO.getCounterpartyName());
        }
        if (subjectClaimsOrderId != null && !subjectClaimsOrderId.isEmpty()) {
            SubjectClaimsOrderDTO subjectClaimsOrderDTO = subjectClaimsOrderProvider.getDetail(Long.valueOf(subjectClaimsOrderId));
            AssertUtils.notNull(subjectClaimsOrderDTO, "应收款转让订单不存在");
            model.addAttribute("counterpartyName", subjectClaimsOrderDTO.getCounterpartyName());
        }else if (projectId != null && !projectId.isEmpty()) {
            ProjectDTO projectDTO = projectProvider.getDetail(Long.valueOf(projectId));
            AssertUtils.notNull(projectDTO, "项目不存在");
            model.addAttribute("counterpartyName", projectDTO.getCounterpartyName());
        }

        model.addAttribute("projectId", projectId);
        model.addAttribute("creditApplyId", creditApplyId);
        model.addAttribute("subjectClaimsOrderId", subjectClaimsOrderId);
        model.addAttribute("isTransferred", isTransferred);
        model.addAttribute("returnUrl", returnUrl);
        model.addAttribute("type", req.getParameter("type") == null ? 4 : req.getParameter("type"));
        model.addAttribute("readOnly", req.getParameter("readOnly") == null ? Boolean.FALSE : Boolean.valueOf(req.getParameter("readOnly")));

        return Views.WAYBILL_LIST_PAGE;
    }

    /**
     * 跳转债权订单列表
     *
     * @return the string
     */
    @RequestMapping(value = "/subjectClaimsOrder", method = RequestMethod.GET)
    public String toSubjectClaimsOrderPage(Model model, HttpServletRequest req) {


        //从哪个页面跳转过来的类型  用款申请的类型 1审核，2放款 3历史订单 ，4、我的额度-项目列表
        model.addAttribute("type", req.getParameter("type"));
        model.addAttribute("counterpartyId", req.getParameter("counterpartyId"));
        model.addAttribute("projectId", req.getParameter("projectId"));
        Long counterpartyId = req.getParameter("counterpartyId") == null ? null : Long.valueOf(req.getParameter("counterpartyId"));
        if (counterpartyId != null) {
            CounterpartyDTO counterparty = counterpartyProvider.findInfo(counterpartyId);
            model.addAttribute("subitemLimitBalance", counterparty.getSubitemLimitBalance());
            model.addAttribute("counterpartyName", counterparty.getCounterpartyName());
            model.addAttribute("subitemRemainBalance", counterparty.getSubitemRemainBalance());
        }
        model.addAttribute("creditApplyId", req.getParameter("creditApplyId"));
        model.addAttribute("reviewStatus", req.getParameter("reviewStatus"));

        //如果没有用信和交易id，则不是跳转过来的。
        model.addAttribute("hiddenReturn", req.getParameter("creditApplyId") != null ||req.getParameter("counterpartyId") != null);
        model.addAttribute("hiddenCounterpary", req.getParameter("counterpartyId") != null||req.getParameter("creditApplyId")!=null);
        model.addAttribute("type", req.getParameter("type") == null ? 4 : req.getParameter("type"));
        model.addAttribute("readOnly", req.getParameter("readOnly") == null ? Boolean.FALSE : Boolean.valueOf(req.getParameter("readOnly")));
        String url = req.getRequestURI() + "?" + req.getQueryString();
        model.addAttribute("url", url);
        String returnUrl = req.getParameter("returnUrl");
        model.addAttribute("returnUrl", returnUrl);
        return Views.SUBJECT_CLAIMS_ORDER_PAGE;
    }

    /**
     * 发起用款申请页面
     *
     * @return the string
     */
    @RequestMapping(value = "/startCreditApply", method = RequestMethod.GET)
    public String tostartCreditApply(Model model, HttpServletRequest req) {
        //从哪个页面跳转过来的类型  用款申请的类型 1审核，2放款 3历史订单
        String subjectClaimsOrderIds = req.getParameter("subjectClaimsOrderIds");
        model.addAttribute("subjectClaimsOrderIds", subjectClaimsOrderIds);
        model.addAttribute("counterpartyId", req.getParameter("counterpartyId"));
        model.addAttribute("counterpartyName", req.getParameter("counterpartyName"));
        model.addAttribute("subitemLimitBalance", req.getParameter("subitemLimitBalance"));
        model.addAttribute("subitemRemainBalance", req.getParameter("subitemRemainBalance"));
        model.addAttribute("transferBalance", req.getParameter("transferBalance"));
        model.addAttribute("canApplyBalance", req.getParameter("canApplyBalance"));
        //如果没有用信的id，则不是跳转过来的。
        String returnUrl = req.getParameter("returnUrl");
        model.addAttribute("returnUrl", returnUrl);
        return Views.START_CREDIT_APPLY_PAGE;
    }

    /**
     * 跳转用款申请列表
     *
     * @param type 客户端 1.审核中 2.已放款 3历史订单   运营端 4.运营审核 5风控审核 6管理层审核 7财务审核
     * @return the string
     */
    @RequestMapping(value = "/creditApply", method = RequestMethod.GET)
    public String toCreditApplyPage(Model model, HttpServletRequest req) {
        Integer type = Integer.valueOf(req.getParameter("type"));
        String readOnly = req.getParameter("readOnly");//审核记录：只读
        model.addAttribute("type", type);
        model.addAttribute("readOnly", readOnly == null ? Boolean.FALSE : Boolean.valueOf(readOnly));
        String url = req.getRequestURI() + "?" + req.getQueryString();
        model.addAttribute("url", url);
        return Views.CREDIT_APPLY_PAGE;
    }

    /**
     * 跳转用款申请审核
     *
     * @param type 运营端 4.运营审核 5风控审核 6管理层审核 7财务审核
     * @return the string
     */
    @RequestMapping(value = "/creditApplyDetail", method = RequestMethod.GET)
    public String toReviewCreditApply(Model model, HttpServletRequest req) {
        Integer type = Integer.valueOf(req.getParameter("type"));
        Long creditApplyId = Long.valueOf(req.getParameter("creditApplyId"));
        String url = req.getRequestURI() + "?" + req.getQueryString();
        ScfUserVO scfUserVO = getSessionUser();
        CreditApplyReviewDTO creditApplyReviewDTO ;
        if (isFounding(getSessionUser())) {
            //如果是资方账户
             creditApplyReviewDTO = creditApplyProvider.getReviewDetail(creditApplyId, scfUserVO.getCompanyId());
        } else {
            CompanyContractDTO companyContractDTO = companyContractProvider.getByBorrowCompanyId(scfUserVO.getCompanyId());
            //如果是借款方
             creditApplyReviewDTO = creditApplyProvider.getReviewDetail(creditApplyId, companyContractDTO.getCompanyCapitalId());
        }
        String attachment = creditApplyReviewDTO.getAttachment();
        String readOnly = req.getParameter("readOnly");
        model.addAttribute("attachment", JSONArray.parse(attachment));
        model.addAttribute("type", type);
        model.addAttribute("url", url);
        model.addAttribute("returnUrl", req.getParameter("returnUrl"));
        model.addAttribute("creditApplyReview", creditApplyReviewDTO);
        model.addAttribute("creditApplyId", creditApplyId);
        model.addAttribute("counterpartyId", creditApplyReviewDTO.getCounterpartyId());
        //不显审核同意和拒绝按钮
        model.addAttribute("readOnly", Boolean.valueOf(readOnly) || !scfUserVO.getRoleId().equals(creditApplyReviewDTO.getRoleId()));
        //显示资金审批单下载
        model.addAttribute("showReviewBill", creditApplyReviewDTO.getStatus().equals(CreditApplyStatus.UNDER_CASHIER_LOAN.getValue())&&scfUserVO.getRoleId().equals(creditApplyReviewDTO.getRoleId()));
        model.addAttribute("returnUrl", req.getParameter("returnUrl"));

        return Views.CREDIT_APPLY_DETAIL_PAGE;
    }

    /**
     * 跳转用款申请审核
     *
     * @param type 运营端 4.运营审核 5风控审核 6管理层审核 7财务审核
     * @return the string
     */
    @RequestMapping(value = "/balanceDetail", method = RequestMethod.GET)
    public String toBalanceDetail(Model model, HttpServletRequest req) {
        Integer type = Integer.valueOf(req.getParameter("type"));
        model.addAttribute("type", type);
        Long creditApplyId = Long.valueOf(req.getParameter("creditApplyId"));
        String url = req.getRequestURI() + "?" + req.getQueryString();
        //        CreditApplyVO creditApply= CreditApplyViewConverterWrapper.INSTANCE.vo(creditApplyProvider.getDetail(creditApplyId));
        model.addAttribute("url", url);
        model.addAttribute("returnUrl", req.getParameter("returnUrl"));
        model.addAttribute("creditApplyId", creditApplyId);
        CreditApplyVO creditApply= CreditApplyViewConverterWrapper.INSTANCE.vo(creditApplyProvider.getDetail(creditApplyId));
        CounterpartyDTO counterpartyDTO =  counterpartyProvider.findInfo(creditApply.getCounterpartyId());
        model.addAttribute("creditApply", creditApply);
        model.addAttribute("attachment", JSONArray.parse(creditApply.getAttachment()));
        model.addAttribute("interestRate", counterpartyDTO.getInterestRate());
        model.addAttribute("counterpartyId", creditApply.getCounterpartyId());
        return Views.BALANCE_DETAIL_PAGE;
    }


    /**
     * 账单列表
     *
     * @return the string
     */
    @RequestMapping(value = "/accountBill", method = RequestMethod.GET)
    public String toAccountBillPage(Model model, HttpServletRequest req) {
        String returnUrl = req.getRequestURI() + "?" + req.getQueryString();
        model.addAttribute("url", returnUrl);
        ScfUserVO userVO = getSessionUser();
        model.addAttribute("roleId", userVO.getRoleId());
        if (isFounding(userVO)) {
            model.addAttribute("type", 2);
        } else {
            model.addAttribute("type", 3);

        }
        return Views.ACCOUNT_BILL__PAGE;
    }

    /**
     * 跳转账单详情
     *
     * @return the string
     */
    @RequestMapping(value = "/accountBillDetail", method = RequestMethod.GET)
    public String toAccountBillDetailPage(Model model, HttpServletRequest req) throws ValidationException {
        AccountBillVO accountBill=new AccountBillVO();
        if(null!=req.getParameter("accountBillId")){
            Long accountBillId = Long.parseLong(req.getParameter("accountBillId").toUpperCase());
             accountBill = AccountBillViewConverterWrapper.INSTANCE.vo(accountBillProvider.getDetail(accountBillId));

        }else if(null!=req.getParameter("creditApplyId")){
            Long creditApplyId = Long.parseLong(req.getParameter("creditApplyId").toUpperCase());
            accountBill=  AccountBillViewConverterWrapper.INSTANCE.vo(accountBillProvider.getByCreditApplyId(creditApplyId));
        }
        model.addAttribute("accountBill", accountBill);
        String returnUrl = req.getParameter("returnUrl");
        model.addAttribute("returnUrl", returnUrl);
        return Views.ACCOUNT_BILL_DETAIL_PAGE;
    }

    /**
     * 跳转还款管理
     *
     * @return the string
     */
    @RequestMapping(value = "/repayList", method = RequestMethod.GET)
    public String toRepayListPage(Model model, HttpServletRequest req) throws ValidationException {
        String returnUrl = req.getRequestURI() + "?" + req.getQueryString();
        model.addAttribute("url", returnUrl);
        model.addAttribute("type", 1);
        return Views.REPAY_LIST_PAGE;
    }

    /**
     * 跳转收款管理
     *
     * @return the string
     */
    @RequestMapping(value = "/repayConfigList", method = RequestMethod.GET)
    public String toRepayConfiListPage(Model model, HttpServletRequest req) throws ValidationException {
        String returnUrl = req.getRequestURI() + "?" + req.getQueryString();
        model.addAttribute("url", returnUrl);
        model.addAttribute("readOnly", req.getParameter("readOnly"));
        return Views.REPAY_CONFIG_LIST_PAGE;
    }

    /**
     * 跳转债权订单列表
     * 跳转还款申请界面
     *
     * @return the string
     */
    @RequestMapping(value = "/repayBill", method = RequestMethod.GET)
    public String toRepayBalancePage(Model model, HttpServletRequest req) throws ValidationException {
        //        Long accountBillId = Long.parseLong(req.getParameter("accountBillId").toString());
        //        AccountBillVO accountBill = AccountBillViewConverterWrapper.INSTANCE.vo(accountBillProvider.getDetail(accountBillId));
        //        model.addAttribute("accountBillId", accountBillId);
        //        model.addAttribute("remainRepayBalance", accountBill.getRemainRepayBalance());
        String returnUrl = req.getParameter("returnUrl");
        model.addAttribute("returnUrl", returnUrl);
        return Views.REPAY_BILL_PAGE;
    }

    /**
     * 单据详情
     *
     * @return the string
     */
    @RequestMapping(value = "/billDataDetail/{waybillId}")
    public String toBillDataDetailPage(@PathVariable("waybillId") Long waybillId, boolean isOperationReview, Long creditApplyId, Model model) {
        WaybillVO waybillVO = WaybillViewConverterWrapper.INSTANCE.vo(waybillProvider.getDetail(waybillId));
        AssertUtils.notNull(waybillVO, "运单信息错误");

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

        model.addAttribute("waybillId", waybillId);
        String imageBaseUrl =   systemConfig.getImageBaseUrl();
        model.addAttribute("imageBaseUrl", imageBaseUrl);

        return Views.BILL_DATA_DETAIL_PAGE;
    }

    /**
     * 跳转客户列表
     *
     * @return the string
     */
    @RequestMapping(value = "/customerMgmt")
    public String toCustomerPageMgmt() {

        return Views.CUSTOMER_LIST_PAGE;
    }

    /**
     * 跳转审核记录列表
     *
     * @param type 运营端 4.运营审核 5风控审核 6管理层审核 7财务审核
     * @return the string
     */
    @RequestMapping(value = "/reviewRecordList", method = RequestMethod.GET)
    public String toReviewRecordListPage(Model model, HttpServletRequest req) {

        return Views.REVIEW_RECORD_LIST_PAGE;
    }


    /**
     * 跳转放款配置页面
     *
     * @return the string
     */
    @RequestMapping(value = "/loanReviewConfig", method = RequestMethod.GET)
    public String toLoanReviewConfigPage() {

        return Views.LOAN_REVIEW_CONFIG_PAGE;
    }

    /**
     * 重新发起用款申请-跳转到发起用款申请页面
     *
     * @return the string
     */
    @RequestMapping(value = "/reStartCreditApply", method = RequestMethod.GET)
    public String toReStartCreditApplyPage(Model model, HttpServletRequest req) {
        Long creditApplyId = Long.valueOf(req.getParameter("creditApplyId"));
        Long counterpartyId = Long.valueOf(req.getParameter("counterpartyId"));
        CounterpartyDTO counterparty = counterpartyProvider.findInfo(counterpartyId);
        AssertUtils.notNull(counterparty, "交易对手不存在");

        //根据用款申请id查询发起用款申请页面的信息
        Map<String, Object> reStartCreditApplyData = waybillProvider.getReStartCreditApplyData(creditApplyId, counterpartyId);
        model.addAttribute("dueDate", reStartCreditApplyData.get("dueDate"));
        model.addAttribute("transferBalance", reStartCreditApplyData.get("transferBalance"));
        model.addAttribute("canApplyBalance", reStartCreditApplyData.get("canApplyBalance"));
        model.addAttribute("counterpartyName", counterparty.getCounterpartyName());
        model.addAttribute("subitemLimitBalance", counterparty.getSubitemLimitBalance());
        model.addAttribute("subitemRemainBalance", counterparty.getSubitemRemainBalance());
        model.addAttribute("ruleRatio", counterparty.getRuleRatio());
        model.addAttribute("manageRate", counterparty.getManageRate());
        model.addAttribute("interestRate", counterparty.getInterestRate());
        model.addAttribute("creditApplyId", creditApplyId);
        model.addAttribute("counterpartyId", counterpartyId);
        model.addAttribute("reStartCreditApply", Boolean.TRUE);
        //如果没有用信的id，则不是跳转过来的。
        String returnUrl = req.getParameter("returnUrl");
        model.addAttribute("returnUrl", returnUrl);
        return Views.START_CREDIT_APPLY_PAGE;
    }
    //****************************************************后台页面跳转*****************************************************

    /**
     * 跳转角色列表_后台
     *
     * @return the string
     */
    @RequestMapping(value = "/authRoleMgmt")
    public String toAuthRolePageMgmt(Model model, HttpServletRequest req) {

        return Views.AUTH_ROLE_LIST_PAGE_MGMT;
    }

    /**
     * 跳转菜单列表_后台
     *
     * @return the string
     */
    @RequestMapping(value = "/menuFuncMgmt")
    public String toMenuFuncPageMgmt(Model model, HttpServletRequest req) {

        return Views.MENUFUNC_LIST_PAGE_MGMT;
    }


    /**
     * 跳转权限列表_后台
     *
     * @return the string
     */
    @RequestMapping(value = "/authFuncMgmt")
    public String toAuthFuncPageMgmt(Model model, HttpServletRequest req) {

        return Views.AUTH_FUNC_LIST_PAGE_MGMT;
    }

    /**
     * 跳转企业列表
     *
     * @return the string
     */
    @RequestMapping(value = "/companyMgmt")
    public String toCompanyPageMgmt(Model model, HttpServletRequest req) {
        ScfUserVO sessionUser = getSessionUser();
        model.addAttribute("userType", sessionUser.getUserType());

        return Views.COMPANY_LIST_PAGE_MGMT;
    }

    /**
     * 跳转企业详情
     *
     * @return the string
     */
    @GetMapping(value = "/companyDetail/{companyId}")
    public String toCompanyDetailPageMgmt(@PathVariable(value = "companyId") Long companyId, Model model, HttpServletRequest req) {
        ScfUserVO sessionUser = getSessionUser();
        model.addAttribute("companyId", companyId);
        model.addAttribute("userType", sessionUser.getUserType());
        model.addAttribute("provinceList", chinaAreaProvider.listParentId(0L));
        model.addAttribute("readOnly", req.getParameter("readOnly"));

        return Views.COMPANY_DETAIL_PAGE_MGMT;
    }

    /**
     * 跳转系统配置列表_后台
     *
     * @return the string
     */
    @RequestMapping(value = "/scfConfigMgmt", method = RequestMethod.GET)
    public String toScfConfigPageMgmt(Model model, HttpServletRequest req) {

        return Views.CONFIG_LIST_PAGE_MGMT;
    }

    /**
     * 跳转组织架构列表
     *
     * @return the string
     */
    @RequestMapping(value = "/departmentMgmt")
    public String toDepartmentPageMgmt(Long ownerId, Model model, HttpServletRequest req) {
        model.addAttribute("ownerId", ownerId);

        return Views.DEPARTMENT_LIST_PAGE_MGMT;
    }

    /**
     * 用信规则配置
     *
     * @return the string
     */
    @RequestMapping(value = "/creditRegulationConfigMgmt")
    public String toCreditRegulationConfigPageMgmt(Long ownerId, Model model, HttpServletRequest req) {
        model.addAttribute("ownerId", ownerId);

        return Views.CREDIT_REGULATION_CONFIG_PAGE_MGMT;
    }


    //****************************************************项目管理页面跳转*****************************************************

    /**
     * 跳转项目管理-项目列表
     *
     * @return the string
     */
    @RequestMapping(value = "/projectMgmt", method = RequestMethod.GET)
    public String toProjectPageMgmt(Model model, HttpServletRequest req) {
        String url = req.getRequestURI() + "?" + req.getQueryString();
       ScfUserVO scfUserVO  =  getSessionUser();
      Long companyId =  scfUserVO.getCompanyId();
        CompanyContractDTO companyContract= companyContractProvider.getByBorrowCompanyId(companyId);
        model.addAttribute("url", url);
        model.addAttribute("companyContract", companyContract);


        return Views.PROJECT_LIST_PAGE;
    }

    /**
     * 跳转项目管理-项目列表
     *
     * @return the string
     */
    @RequestMapping(value = "/companyContractMgmt", method = RequestMethod.GET)
    public String toCompanyContractPageMgmt(Model model, HttpServletRequest req) {

        ScfUserVO scfUserVO = getSessionUser();
        //TODO-LA: 2020/3/27 审核客户权限判断，后面可能会加入审核流程里。
        AuthRoleDTO role = authRoleProvider.getDetail(scfUserVO.getRoleId());
        model.addAttribute("showReview", role.getRoleName().equals("风险经理"));
        return Views.COMPANY_CONTRACT_LIST_PAGE;
    }
    /**
     * 跳转账单提醒电话号码
     *
     * @return the string
     */
    @RequestMapping(value = "/accounNotifyMobileNos", method = RequestMethod.GET)
    public String toAccountNosMgmt(Model model, HttpServletRequest req) {

        ScfUserVO scfUserVO = getSessionUser();
        CompanyDTO companyDTO = companyProvider.getDetail(scfUserVO.getCompanyId());
        model.addAttribute("company", CompanyViewConverterWrapper.INSTANCE.vo(companyDTO));
        return Views.ACCOUN_NOTIFY_MOBILE_NOS;
    }
}
