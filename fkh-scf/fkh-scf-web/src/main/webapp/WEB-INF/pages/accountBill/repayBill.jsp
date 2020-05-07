<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
    .contract{padding-top: 15px;}
    .contract span{display: inline-block;width: 100px;text-align: right;}
    .contract .newPublicInput{width: 270px;}
    #contractList li{text-align: center;max-width:140px;margin-top: 15px;}
    #contractList li p{text-overflow: ellipsis;  overflow: hidden;  white-space: nowrap;width: 105px;}
    #contractList li:nth-child(5){margin-top: 15px !important;}
    /*#contractList li:first-child{margin-left: 0px !important;}*/
    .lineRoad p{padding-top: 15px;}
    .lineRoad span{display: inline-block;width: 75px;text-indent: 20px;}
    .uploadAgreement span{display: inline-block;width: 75px;text-indent: 20px;vertical-align: top;}
    .saveLineRoadMsg{width: 100%;text-align: center;}
    .saveLineRoadMsg input{  margin-top: 15px;  margin-left: 60px;  margin-bottom: 15px;}
    #uploadReceiptDialog input[type="text"]{ width: 180px;}
    .uploadImgFilePic #uploadImgFile{position: absolute;top:0px;left: 0px;width: 100%;height: 100%;opacity: 0;filter: alpha(opacity=0);}
    .uploadListFile label{width: 130px;text-align: right;display: inline-block;}
    .uploadListFileLabel p label{width: 130px;text-align: right;display: inline-block;}
    #contractLineList span{text-decoration: underline;}
    .historyListMsg li{width: 100%;height: 80px;border-bottom:1px solid #ededed;}
    .historyListMsgMain p{text-align: justify;line-height: 20px;margin-top: 10px;}
    .operationMsgUser a{color: #a4a4a4;}
    .operationMsgUser span{color: #0071dc;}
    @media screen and (min-width:1201px) and (max-width:1420px) {
        .contract .newPublicInput{width: 200px;}
    }
    @media screen and (min-width:10px) and (max-width:1200px) {
        .contract .newPublicInput{width: 160px;}
    }
    @media screen and (min-width:10px) and (max-width:1430px) {
        #contractList li{margin-left: 45px !important;}
    }
    .contract div p{padding-top: 10px;}
    .contract p i{display: inline-block;width: 70px;text-align: right;}
    .projectMsg p{padding-top: 5px;}
    .projectMsg p i{display: inline-block;width: 70px;text-align: right;}
    @media screen and (min-width:1201px) and (max-width:1420px) {
        .contract .newPublicInput{width: 200px;}
    }
    @media screen and (min-width:10px) and (max-width:1200px) {
        .contract .newPublicInput{width: 160px;}
    }
    @media screen and (min-width:10px) and (max-width:1430px) {
        #contractList li{margin-left: 45px !important;}
    }
    .selectAlert span{display: inline-block;width: 80px;text-align: right;padding-right: 5px;}
    .selectAlert a{display: inline-block;width: 80px;text-align: left;}

</style>
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
                <span>还款</span>
                <div class="publicDetailsModuleReturnBtn returnPro returnChild  return">返回</div>
            </div>
                <!--确认支付信息-->

            <form name="editCompanyContractForm">
                <div class="contract">
                    <span>交易对手</span>

<%--                    <c:if test="${not empty companyContract}">--%>
<%--                        <input type="hidden" id="id" name="id"   value="${companyContract.id}">--%>
<%--                        <input type="hidden" id="companyBorrowerId" name="companyBorrowerId"   value="${companyContract.companyBorrowerId}">--%>
<%--                        <input type="text" name="companyBorrowerName"  class="newPublicInput"  value="${companyContract.companyBorrowerName}" readonly>--%>
<%--                    </c:if>--%>

<%--                    <c:if test="${empty companyContract}">--%>
                        <input type="hidden" id="companyBorrowerName" name="companyBorrowerName" value=""  >
                        <select class="newPublicSelect newPublicSelect1 publicSelectSwitchTimeProject" id="counterpartyId" name="counterpartyId" onchange="checkHadRepay()" style="width:15%  !important;">
                            <option value='0'>------请选择交易对手------</option>
                        </select>
<%--                    </c:if>--%>
                </div>
                <span><i id="sumRemainRepayBalance" style="color: red;padding-left: 120px;"></i></span>

                <div class="contract">
                    <span>本次还款金额</span>
                    <input id="repayBalanceInput" class="repayBalance newPublicInput" name="repayBalance"  placeholder="请输入本次还款金额" value="">
                    <input type="button"  class="newPublicAlertBtn new_public_blue_bg" onclick="calculateRate()" value="计算">
                </div>


                <div class="contract">
                    <span>还款日期</span>
                    <input style=""  class="newPublicInput newPublicInputOne publicTimeShow" id="repayDateStr"  value="" name="startTime" type="text" placeholder="请选择还款日期" readonly>
                </div>

                <div class="contract">
                    <span>还款本金</span>
                    <i  style="padding-left: 10px;"  name="bankCardId" id ="repayBalance"><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></i>
                    <label style="padding-left: 3px;">元</label>
                </div>
                <div class="contract">
                    <span>利息</span>
                    <i  style="padding-left: 10px;" name="bankCardId" id ="interestRateBalance" ><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></i>
                    <label style="padding-left: 3px;">元</label>
                </div>
                <div class="contract">
                    <span>宽限利息</span>
                    <i  style="padding-left: 10px;"  name="bankCardId" id ="graceRateBalance" ><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></i>
                    <label style="padding-left: 3px;">元</label>
                </div>
                <div class="contract">
                    <span>逾期利息</span>
                    <i  style="padding-left: 10px;" name="bankCardId" id ="overdueRateBalance" ><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></i>
                    <label style="padding-left: 3px;">元</label>
                </div>
                <div class="contract">
                    <span>还款总额</span>
                    <i  style="padding-left: 10px;"  name="bankCardId" id ="repayBalanceTotal"><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></i>
                    <label style="padding-left: 3px;">元</label>
                </div>
                <div class="contract "style="display: none;">
                    <span>还款账单</span>
                    <input type="hidden" id="accountBillIds" name="accountBillIds"  class="newPublicInput"  >
                </div>

                <div class="contract" style="display: none;">
                    <span>还款账单信息</span>
                    <input type="hidden" id="accountBillBalanceInfo" name="accountBillBalanceInfo"  class="newPublicInput" >
                </div>
                <div class="saveLineRoadMsg">
                    <input type="hidden" id="uploadNewFile" value="0">
                    <input type="button"  id="confirmPayInvoices" class="newPublicAlertBtn new_public_blue_bg" onclick="saveRepay()" value="确认还款">
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var sumRemainRepayBalance ;
    $(document).ready(function(){
        //公用布局模块方法调用
        initReady();
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();

        selectCompanyList();
    });
    /**
     * 确认还款
     */
    function saveRepay() {
        if(!validate()){
            return ;
        }
        var accountBillBalanceInfo=$("#accountBillBalanceInfo").val();
        // if (P.isNullOrEmptyString(accountBillBalanceInfo)) {
            if(!validate()){
                return;
            }
            var counterpartyId= $("#counterpartyId").val();
            var repayBalanceInput= $("#repayBalanceInput").val();
            var repayDateStr= $("#repayDateStr").val();
            var formData={
                "counterpartyId":counterpartyId,
                "repayDateStr":repayDateStr,
                "repayBalance":repayBalanceInput,
            }
            $.post("accountBill/calculateRate.json",formData, function (resp) {
                if(resp.code==4000){
                    selfAlert(resp.message);
                    return false;
                }
                var lis="";
                var options=resp.data;
                // 还款金额：本金加利息")
                var repayBalance=0;
                // 利息")
                var interestRateBalance=0;
                // 宽限期利息")
                var graceRateBalance=0;
                // 逾期利息")
                var overdueRateBalance=0;
                // 还款总额")
                var repayBalanceTotal=0;

                var accountBillIds="";
                var accountBillBalanceInfo="";
                for(var i=0;i<options.length;i++){
                    repayBalance+=options[i].repayBalance;
                    repayBalanceTotal+=options[i].repayBalance+options[i].interestRateBalance+options[i].overdueRateBalance+options[i].graceRateBalance;

                    interestRateBalance+=options[i].interestRateBalance;
                    overdueRateBalance+=options[i].overdueRateBalance;
                    graceRateBalance+=options[i].graceRateBalance;
                    accountBillIds+= i!=0?","+options[i].accountBillId:options[i].accountBillId;
                    var info=options[i].accountBillId+","+options[i].repayBalance+","+
                             options[i].interestRateBalance+","+options[i].graceRateBalance+","+options[i].overdueRateBalance;
                    accountBillBalanceInfo+= i!=0?";"+info:info;
                }

                $("#repayBalance").text(PF.formatMoney(repayBalance));
                $("#interestRateBalance").text(PF.formatMoney(interestRateBalance));
                $("#graceRateBalance").text(PF.formatMoney(graceRateBalance));
                $("#overdueRateBalance").text(PF.formatMoney(overdueRateBalance));
                $("#repayBalanceTotal").text(PF.formatMoney(repayBalanceTotal));

                $("#accountBillIds").val(accountBillIds);
                $("#accountBillBalanceInfo").val(accountBillBalanceInfo);
                // $("ul[name='siteNameUl']").html(lis);
                var repayBalance = $("#repayBalanceInput").val();
                var accountBillIds =$("#accountBillIds").val();
                var repayDateStr =$("#repayDateStr").val();
                var counterpartyId= $("#counterpartyId").val();
                var interestRateBalance =$("#interestRateBalance").text();
                var graceRateBalance =$("#graceRateBalance").text();
                var overdueRateBalance = $("#overdueRateBalance").text();
                var repayBalanceTotal = $("#repayBalanceTotal").text();
                var formData={
                    "accountBillIds":accountBillIds,
                    "repayDateStr":repayDateStr,
                    "repayBalance":repayBalance,
                    "interestRateBalance":interestRateBalance,
                    "graceRateBalance":graceRateBalance,
                    "overdueRateBalance":overdueRateBalance,
                    "accountBillBalanceInfo":accountBillBalanceInfo,
                    "counterpartyId":counterpartyId,
                    "repayBalanceTotal":repayBalanceTotal
                };
                var repayBalanceTotal = $("#repayBalanceTotal").text();
                var repayBalance = $("#repayBalance").text();
                var html="";
                html+="<div class='selectAlert'><p><span>本次还款:</span><a>"+repayBalance+"</a></p>";
                html+="<p><span>还款本金:</span><a>"+repayBalance+"</a></p>";
                html+="<p><span>利息:</span><a>"+interestRateBalance+"</a></p>";
                html+="<p><span>宽限利息:</span><a>"+graceRateBalance+"</a></p>";
                html+="<p><span>逾期利息:</span><a>"+overdueRateBalance+"</a></p>";
                html+="<p><span>还款总额:</span><a>"+repayBalanceTotal+"</a></p></div>";
                selfConfirm(html,function () {
                    ajaxPost("accountBill/repayBill.json", formData, function (resp) {
                        if (resp.success) {
                            selfAlert("还款成功!",function () {
                                Page.returnPage();
                                <%--var returnUrl=  '${returnUrl}';--%>
                                <%--if(returnUrl){--%>
                                <%--    window.backToPage(returnUrl);--%>
                                <%--}else{--%>
                                <%--    window.backToPage("accountBill.html");--%>
                                <%--}                --%>
                            });
                        } else {
                            selfAlert(resp.message);
                        }
                    });
                })
            })
            // selfAlert("请计算利息!");
            // return false;
        <%--}else{--%>

        <%--var repayBalance = $("#repayBalanceInput").val();--%>
        <%--var accountBillIds =$("#accountBillIds").val();--%>
        <%--var repayDateStr =$("#repayDateStr").val();--%>
        <%--var counterpartyId= $("#counterpartyId").val();--%>
        <%--var interestRateBalance =$("#interestRateBalance").text();--%>
        <%--var graceRateBalance =$("#graceRateBalance").text();--%>
        <%--var overdueRateBalance = $("#overdueRateBalance").text();--%>
        <%--var repayBalanceTotal = $("#repayBalanceTotal").text();--%>


        <%--var formData={--%>
        <%--    "accountBillIds":accountBillIds,--%>
        <%--    "repayDateStr":repayDateStr,--%>
        <%--    "repayBalance":repayBalance,--%>
        <%--    "interestRateBalance":interestRateBalance,--%>
        <%--    "graceRateBalance":graceRateBalance,--%>
        <%--    "overdueRateBalance":overdueRateBalance,--%>
        <%--    "accountBillBalanceInfo":accountBillBalanceInfo,--%>
        <%--    "counterpartyId":counterpartyId,--%>
        <%--    "repayBalanceTotal":repayBalanceTotal--%>
        <%--};--%>
        <%--var repayBalanceTotal = $("#repayBalanceTotal").text();--%>
        <%--var repayBalance = $("#repayBalance").text();--%>
        <%--var html="<div class='selectAlert'><span>本次还款:</span>"+repayBalance+"<br/>";--%>
        <%--html+="<span>还款本金:</span>"+repayBalance+"<br/>";--%>
        <%--html+="<span>利息:</span>"+interestRateBalance+"<br/>";--%>
        <%--html+="<span>宽限利息:</span>"+graceRateBalance+"<br/>";--%>
        <%--html+="<span>逾期利息:</span>"+overdueRateBalance+"<br/>";--%>
        <%--html+="<span>还款总额:</span>"+repayBalanceTotal+"<br/></div>";--%>
        <%--selfConfirm(html,function () {--%>
        <%--    ajaxPost("accountBill/repayBill.json", formData, function (resp) {--%>
        <%--        if (resp.success) {--%>
        <%--            selfAlert("还款成功!",function () {--%>
        <%--                var returnUrl=  '${returnUrl}';--%>
        <%--                if(returnUrl){--%>
        <%--                    window.backToPage(returnUrl);--%>
        <%--                }else{--%>
        <%--                    window.backToPage("accountBill.html");--%>
        <%--                }                });--%>
        <%--        } else {--%>
        <%--            selfAlert(resp.message);--%>
        <%--        }--%>
        <%--    });--%>
        <%--})--%>
        <%--}--%>
    }

    /**
     * 确认还款
     */
    function saveRepay22() {

        var repayBalance = $("#repayBalanceInput").val();
        var accountBillIds =$("#accountBillIds").val();
        var repayDateStr =$("#repayDateStr").val();
        var counterpartyId= $("#counterpartyId").val();
        var interestRateBalance =$("#interestRateBalance").text();
        var graceRateBalance =$("#graceRateBalance").text();
        var overdueRateBalance = $("#overdueRateBalance").text();
        var repayBalanceTotal = $("#repayBalanceTotal").text();


        var formData={
            "accountBillIds":accountBillIds,
            "repayDateStr":repayDateStr,
            "repayBalance":repayBalance,
            "interestRateBalance":interestRateBalance,
            "graceRateBalance":graceRateBalance,
            "overdueRateBalance":overdueRateBalance,
            "accountBillBalanceInfo":accountBillBalanceInfo,
            "counterpartyId":counterpartyId,
            "repayBalanceTotal":repayBalanceTotal
        };
        var repayBalanceTotal = $("#repayBalanceTotal").text();
        var repayBalance = $("#repayBalance").text();
        var html="";
        html+="<div class='selectAlert'><p><span >本次还款:</span><a>"+repayBalance+"</a></p>";
        html+="<p><span>还款本金:</span><a>"+repayBalance+"</a></p>";
        html+="<p><span>利息:</span><a>"+interestRateBalance+"</a></p>";
        html+="<p><span>宽限利息:</span><a>"+graceRateBalance+"</a></p>";
        html+="<p><span>逾期利息:</span><a>"+overdueRateBalance+"</a></p>";
        html+="<p><span>还款总额:</span><a>"+repayBalanceTotal+"</a></p></div>";
        selfConfirm(html,function () {
            ajaxPost("accountBill/repayBill.json", formData, function (resp) {
                if (resp.success) {
                    selfAlert("还款成功!",function () {
                        Page.returnPage();
                        <%--var returnUrl=  '${returnUrl}';--%>
                        <%--if(returnUrl){--%>
                        <%--    window.backToPage(returnUrl);--%>
                        <%--}else{--%>
                        <%--    window.backToPage("accountBill.html");--%>
                        <%--}                --%>
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        })
    }
    //借款方交易对手
    function selectCompanyList() {
        $.post("counterparty/listByCompanyId.json", function (resp) {
            var lis="";
            var options=resp.data;
            $("#counterpartyId").html('<option value="">请选择交易对手</option>');
            for(var i=0;i<options.length;i++){
                $("#counterpartyId").append("<option value='"+options[i].id+"'>"+options[i].counterpartyName+"</option>");
            }
            // $("ul[name='siteNameUl']").html(lis);
        })
    }

    /**
     * 检查是否已经有该交易对手的还款申请
     */
    function checkHadRepay() {
        var counterpartyId= $("#counterpartyId").val();
        if(counterpartyId){
            ajaxPost("repayBill/checkHadRepay.json", {"counterpartyId":counterpartyId}, function (resp) {
                if (resp.success) {
                    $.jeDate("#repayDateStr", {
                        onClose : true,
                        theme : {
                            bgcolor : "#00A1CB",
                            color : "#ffffff",
                            pnColor : "#00CCFF"
                        },
                        format : 'YYYY-MM-DD',
                        minDate:resp.data.billDate? P.long2Datetime(resp.data.billDate): P.long2Datetime(new Date(0)),
                        maxDate : $.nowDate({DD:0})
                    });
                    sumRemainRepayBalance = resp.data.sumRemainRepayBalance?parseFloat(resp.data.sumRemainRepayBalance):0;
                    $("#sumRemainRepayBalance").text("本次最多还款"+PF.formatMoney(sumRemainRepayBalance)+"元")
                    if(resp.data.hasRepayBill){
                        selfAlert("该交易对手已发起还款申请!");
                        $("#counterpartyId").val(0)
                    }
                } else {
                    selfAlert(resp.message);
                }
            });
        }
    }
    function validate() {
        var counterpartyId= $("#counterpartyId").val();
        if(counterpartyId==""){
            selfAlert("请选择交易对手");
            return false;
        }
        var repayBalanceInput= $("#repayBalanceInput").val();
        if (P.isNullOrEmptyString(repayBalanceInput)) {
            selfAlert("请填写还款金额!");
            return false;
        }
        if (parseFloat(repayBalanceInput) <= 0) {
            selfAlert("还款金额必须大于0!");
            return false;
        }
        if (!isAmount(parseFloat(repayBalanceInput))) {
            selfAlert("请填写正确的还款金额!");
            return false;
        }

        var repayDateStr= $("#repayDateStr").val();
        if(repayDateStr==""){
            selfAlert("请选择还款日期");
            return false;
        }
        if (isValueOutRange(parseFloat(repayBalanceInput), true, 0.01, sumRemainRepayBalance)) {
            selfAlert("本次还款金额超过最大额度！");
            return false;
        }
        return true;
    }
    // /**
    //  * 开启提交按钮
    //  * @param isOk
    //  */
    // function openSubmit(isOk) {
    //     if(isOk){
    //     }else{
    //         var flag = true;
    //         var counterpartyId= $("#counterpartyId").val();
    //         if(counterpartyId==""){
    //             flag= false;
    //         }
    //         var repayBalanceInput= $("#repayBalanceInput").val();
    //         if (P.isNullOrEmptyString(repayBalanceInput)) {
    //             flag=  false;
    //         }
    //         if (parseFloat(repayBalanceInput) <= 0) {
    //             flag=  false;
    //         }
    //         if (!isAmount(parseFloat(repayBalanceInput))) {
    //             flag=  false;
    //         }
    //
    //         var repayDateStr= $("#repayDateStr").val();
    //         if(repayDateStr==""){
    //             flag= false;
    //         }
    //         if (isValueOutRange(parseFloat(repayBalanceInput), true, 0.01, sumRemainRepayBalance)) {
    //             flag= false;
    //         }
    //         if(flag){
    //         }else{
    //         }
    //     }
    // }

    //计算本金，利息，对应的账单列表
    function calculateRate(save) {
        if(!validate()){
            return;
        }
        var counterpartyId= $("#counterpartyId").val();
        var repayBalanceInput= $("#repayBalanceInput").val();
        var repayDateStr= $("#repayDateStr").val();
        var formData={
            "counterpartyId":counterpartyId,
            "repayDateStr":repayDateStr,
            "repayBalance":repayBalanceInput,
        }
        $.post("accountBill/calculateRate.json",formData, function (resp) {
            if(resp.code==4000){
                selfAlert(resp.message);
                return false;
            }

            var lis="";
            var options=resp.data;
            // 还款金额：本金加利息")
            var repayBalance=0;
            // 利息")
            var interestRateBalance=0;
            // 宽限期利息")
            var graceRateBalance=0;
            // 逾期利息")
            var overdueRateBalance=0;
            // 还款总额")
            var repayBalanceTotal=0;

            var accountBillIds="";
            var accountBillBalanceInfo="";
            for(var i=0;i<options.length;i++){
                repayBalance+=options[i].repayBalance;
                repayBalanceTotal+=options[i].repayBalance+options[i].interestRateBalance+options[i].overdueRateBalance+options[i].graceRateBalance;

                interestRateBalance+=options[i].interestRateBalance;
                overdueRateBalance+=options[i].overdueRateBalance;
                graceRateBalance+=options[i].graceRateBalance;
                accountBillIds+= i!=0?","+options[i].accountBillId:options[i].accountBillId;
                var info=options[i].accountBillId+","+options[i].repayBalance+","+
                         options[i].interestRateBalance+","+options[i].graceRateBalance+","+options[i].overdueRateBalance;
                accountBillBalanceInfo+= i!=0?";"+info:info;
            }

            $("#repayBalance").text(PF.formatMoney(repayBalance));
            $("#interestRateBalance").text(PF.formatMoney(interestRateBalance));
            $("#graceRateBalance").text(PF.formatMoney(graceRateBalance));
            $("#overdueRateBalance").text(PF.formatMoney(overdueRateBalance));
            $("#repayBalanceTotal").text(PF.formatMoney(repayBalanceTotal));

            $("#accountBillIds").val(accountBillIds);
            $("#accountBillBalanceInfo").val(accountBillBalanceInfo);
            // $("ul[name='siteNameUl']").html(lis);
            if(save){
                saveRepay22();
            }

        })
    }

</script>

