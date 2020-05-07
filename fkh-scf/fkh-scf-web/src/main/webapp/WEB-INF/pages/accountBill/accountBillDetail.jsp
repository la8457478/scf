<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--结算详情页面-->
<div class="paymentPage">

    <!--顶部导航-->
    <div class="publicHeaderNav">
    </div>
    <!--顶部导航 end-->
    <!--确认支付主题内容-->
    <div class="public_container" id="new_public_height3" style="width: 100%;background: white; border: 1px solid #e6e6ef; padding-bottom: 20px;">
        <div class="publicMainContainerBox publicMainContainerBoxs">
            <!--头部公用-->
            <div class="newPublicHeader">
                <span>账单详情</span>
                <div class="publicDetailsModuleReturnBtn returnPro returnChild return">返回</div>
            </div>
                <!--确认支付信息-->
                <div class="messagePayInvoices detail">
                    <p>
                        <span>账单编号：</span><i style="padding-left: 10px;" class="companyMessage">${accountBill.billNo}</i>
                    </p>
                    <!-- 付款到易极付账户 -->
                        <p>
                            <span>账单金额：</span><i style="padding-left: 10px;" id="billBalance" name="billBalance"><fmt:formatNumber type="number" value="${accountBill.billBalance}" pattern="###,##0.00" maxFractionDigits="2" /></i> &nbsp;&nbsp;<a href="#" onclick="toWaybillList('${accountBill.creditApplyId}')">${accountBill.waybillCount}笔明细</a>
                        </p>
                            <p>
                                <span>还款金额：</span><i style="padding-left: 10px;" id="repayBalance" name="repayBalance"><fmt:formatNumber type="number" value="${accountBill.repayBalance}" pattern="###,##0.00" maxFractionDigits="2" /></i>
                            </p>
                            <p>
                                <span>剩余待还：</span><i style="padding-left: 10px;" id="remainRepayBalance" name="bankCardId"><fmt:formatNumber type="number" value="${accountBill.remainRepayBalance}" pattern="###,##0.00" maxFractionDigits="2" /></i>
                            </p>
                    <!-- 付款到平安银行子账户 -->
                            <p>
                                <span>记账周期：</span><i style="padding-left: 10px;" id="billTerm" name="billTerm"><fmt:formatDate value="${accountBill.repayDate}" pattern="yyyy-MM-dd "/>至<fmt:formatDate value="${accountBill.repayDate}" pattern="yyyy-MM-dd "/></i>
                            </p>
                    <p>
                        <span>出账日：</span><i style="padding-left: 10px;" id="billDate" name="billDate"><fmt:formatDate value="${accountBill.repayDate}" pattern="yyyy-MM-dd "/></i>
                    </p>
                    <p>
                        <span>最后还款日：</span><i style="padding-left: 10px;" class="repayDate" id="payMoney" name="repayDate"><fmt:formatDate value="${accountBill.repayDate}" pattern="yyyy-MM-dd "/></i>
                    </p>
                    <p>
                        <%--<span></span>--%>
                            <%--<input type="button" id="confirmPayInvoices" class="publicBtn new_public_blue_bg" value="确定支付">--%>
                    <%--</p>--%>
                </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        //公用布局模块方法调用
        initReady();
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
    });

    function toWaybillList(creditApplyId) {
        Page.clickBtnToPage("waybillList.html?type=${type}&&readOnly=true&creditApplyId="+creditApplyId,"查看运单列表")
    }

</script>

