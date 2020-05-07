<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        .passButton{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
        .configOil{margin-top: 25px;}
        .configOil span{margin-left: 30px;}
        .configOil span:first-child{margin-left: 0px;font-size: 14px;}
        .contract {
            padding-top: 15px;
        }

        .contract span {
            display: inline-block;
            width: 150px;
            text-align: right;
        }

        .contract .newPublicInput {
            width: 270px;
        }

        #contractList li {
            text-align: center;
            max-width: 140px;
            margin-top: 15px;
        }

        #contractList li p {
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
            width: 105px;
        }

        #contractList li:nth-child(5) {
            margin-top: 15px !important;
        }

        /*#contractList li:first-child{margin-left: 0px !important;}*/
        .lineRoad p {
            padding-top: 15px;
        }

        .lineRoad span {
            display: inline-block;
            width: 75px;
            text-indent: 20px;
        }



        .uploadAgreement span {
            display: inline-block;
            width: 75px;
            text-indent: 20px;
            vertical-align: top;
        }

        .saveLineRoadMsg {
            width: 100%;
            text-align: center;
        }

        .saveLineRoadMsg input {
            margin-top: 15px;
            margin-left: 60px;
            margin-bottom: 15px;
        }

        #uploadReceiptDialog input[type="text"] {
            width: 180px;
        }


        .uploadListFile label {
            width: 130px;
            text-align: right;
            display: inline-block;
        }


        .uploadListFileLabel p label {
            width: 130px;
            text-align: right;
            display: inline-block;
        }


        #contractLineList span {
            text-decoration: underline;
        }

        .historyListMsg li {
            width: 100%;
            height: 80px;
            border-bottom: 1px solid #ededed;
        }


        .historyListMsgMain p {
            text-align: justify;
            line-height: 20px;
            margin-top: 10px;
        }


        .operationMsgUser a {
            color: #a4a4a4;
        }

        .operationMsgUser span {
            color: #0071dc;
        }


        @media screen and (min-width: 1201px) and (max-width: 1420px) {
            .contract .newPublicInput {
                width: 200px;
            }
        }

        @media screen and (min-width: 10px) and (max-width: 1200px) {
            .contract .newPublicInput {
                width: 160px;
            }
        }

        @media screen and (min-width: 10px) and (max-width: 1430px) {
            #contractList li {
                margin-left: 45px !important;
            }
        }

        .contract div p {
            padding-top: 10px;
        }

        .contract p i {
            display: inline-block;
            width: 70px;
            text-align: right;
        }


        .projectMsg p {
            padding-top: 5px;
        }

        .projectMsg p i {
            display: inline-block;
            width: 70px;
            text-align: right;
        }

        @media screen and (min-width: 1201px) and (max-width: 1420px) {
            .contract .newPublicInput {
                width: 200px;
            }
        }

        @media screen and (min-width: 10px) and (max-width: 1200px) {
            .contract .newPublicInput {
                width: 160px;
            }
        }

        @media screen and (min-width: 10px) and (max-width: 1430px) {
            #contractList li {
                margin-left: 45px !important;
            }
        }
        .boxHistory{margin-bottom: 30px;}
        .publicDetailsModuleContainerHeader{line-height: 36px !important;}
         .contract a{display: inline-block;width: 200px;}
        .textarea{
            width:350px;height:80px;position:absolute;background:none;z-index:9
        }
        .note{
            position:absolute;line-height:20px;padding:3px 5px;    top: 0px;
            left: 40px;
        }
    </style>
</head>
<!--结算详情页面-->
<div class="paymentPage">

    <!--顶部导航-->
    <div class="publicHeaderNav">
    </div>
    <!--顶部导航 end-->
    <!--确认支付主题内容-->
    <div class="public_container" id="new_public_height"
         style="width: 100%;background: white; border: 1px solid #e6e6ef; padding-bottom: 20px; overflow-y: auto;">
        <div class="publicMainContainerBox publicMainContainerBoxs">
            <!--头部公用-->
            <div class="newPublicHeader">
                <span>审核详情</span>
                <div class="publicDetailsModuleReturnBtn returnPro returnChild return"  >返回
                </div>
            </div>
            <form  id="applyBalanceForm" name="applyBalanceForm" read>
                <p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">审核信息</a></p>

                <div class="contract">
                    <input type="text" id="subjectClaimsOrderIds" hidden="hidden" name="subjectClaimsOrderIds" value="${subjectClaimsOrderIds}" class="newPublicInput"
                           placeholder="id">

                    <span>放款申请编号：</span>
                    <a >${creditApplyReview.creditApplyNo}</a>
<%--                    <input type="text" id="creditApplyNo" name="creditApplyNo" value="${creditApplyReview.creditApplyNo}" class="newPublicInput"--%>
<%--                           placeholder="申请编号">--%>
                </div>
                <div class="contract">
                    <span style="float: left;">客户名称：</span>
                    <a style="width: 300px;">${creditApplyReview.companyBorrowerName}</a>
                    <%--                    <input type="text" id="creditApplyNo" name="creditApplyNo" value="${creditApplyReview.creditApplyNo}" class="newPublicInput"--%>
                    <%--                           placeholder="申请编号">--%>
                </div>
                <div style="clear:both;"></div>
                <div class="contract">
                    <span>总额度：</span>
                    <a class="totalBalance"></a>
                    <span>可用额度：</span>
                    <a class="remainingBalance"></a>
                </div>

                <div class="contract">
                    <span>交易对手：</span>
                    <a >${creditApplyReview.counterpartyName}</a>
                </div>
                <div class="contract">
                    <span>分项限额：</span>
                    <a class="subitemLimitBalance"></a>
                    <span>可提用融资余额：</span>
                    <a class="subitemRemainBalance" ></a>
                </div>
                <div class="contract" style="position: relative;">
                    <span>申请融资金额：</span>
                    <a class="applyBalance"></a>
                    <input style="position: absolute;left: 245px;top:8px;" onclick="toSubjectClaimsOrderList(${creditApplyId},${counterpartyId})" class="newPublicAlertBtn new_public_blue_bg" type="button"
                           value="查看"　id="searchFinancingBtn">
                    <span style="width: 150px;">本次转让应收账款金额：</span>
                    <a class="transferBalance"></a>
                </div>
                <div class="contract">
                    <span>申请转让日期：</span>
                    <a class ="createTime"></a>
                    <span>融资利率：</span>
                    <a class="interestRate">${creditApplyReview.interestRate}%</a>
                </div>

                <div class="contract">
                    <span>应收账款到期日：</span>
                    <a class="dueDate"></a>
                    <span>保理管理费：</span>
                    <a class="manageBalance"></a>
                </div>
                <div class="contract"  style="position: relative;">
                    <span>宽限期：</span>
                    <a class="graceDate"></a>
                </div>
                <div class="contract" style="position: relative;">
<%--                    <c:if test="${creditApplyReview.status==1}">--%>
                    <span>放款申请校验结果：</span>
                        <a></a>
                        <input style="position: absolute;left: 155px;top:10px;" onclick="toLoanValidation(${creditApplyReview.id})" class="newPublicAlertBtn new_public_blue_bg" type="button"
                               value="查看">
<%--                    </c:if>--%>
                    <span>转让确认书：</span>
                    <input style="position: absolute;left: 513px;top:10px;"  onclick="showAnxinContract('${creditApplyReview.pdfPath}','${creditApplyReview.signPdfPath}')" class="newPublicAlertBtn new_public_blue_bg" type="button"
                           value="查看">
                </div>
                <c:if test="${showReviewBill}">

                <div class="contract"  style="position: relative;">
                    <span>资金审批单：</span>
                    <input style="position: absolute;left: 155px;top:10px;"  onclick="showReviewBill('${creditApplyReview.reviewBillUrl}')" class="newPublicAlertBtn new_public_blue_bg" type="button"
                           value="下载">

                </div>
                <div class="contract">
                        <span>转账账户：</span>
                        <a class="bankInfo"></a>
                </div>
                </c:if>
                <c:if test="${type == 7 && creditApplyReview.status==3}">
                <div class="contract">
                    <span>保理管理费是否已支付：</span>
                    <input <c:if test="${readOnly}"> disabled="disabled"</c:if> id="hasCharge"  class="designatedConsumption"  name="designatedConsumption" type="radio" value="0" <c:if test="${!creditApplyReview.hasCharge}"> checked </c:if>  />未支付
                    <input  <c:if test="${readOnly}"> disabled="disabled"</c:if>  id="notHasCharge" class="designatedConsumption"  name="designatedConsumption" type="radio" value="1"  <c:if test="${creditApplyReview.hasCharge}"> checked </c:if>  />已支付
                </div>
                </c:if>
                <p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">审核意见</a></p>
                <div class="boxHistory">
                    <div class="boxHistory">
                        <div class="boxHistoryList">
                            <ul id="historyUl" style="margin-left: 40px; margin-top: 20px;">
                                <li></li>
                            </ul>
                        </div>
                    </div>
                </div>
<%--                <div class="line_contract"></div>--%>
<%--                <p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">附件</a></p>--%>
<%--                <div class="" style="margin-top: 20px;margin-bottom: 40px;">--%>
<%--                    <ul id="contractList">--%>
<%--                        <c:forEach items="${attachment}" var="item">--%>
<%--                            <li style="margin-left: 50px;  display: inline-block;"><a href="${item.path}" target="_blank"><img src="newassets/imgs/icon_pdf.png"></a>--%>
<%--                                <p><a title="${item.name}">${item.name}</a></p>--%>
<%--                            </li>--%>
<%--                        </c:forEach>--%>
<%--                        <c:if test="${from==3}">--%>
<%--                            <c:if test="${empty attachment||attachment.size()<20}">--%>
<%--                                <li id="uploadPorjectContractButton" style="margin-left: 60px;  display: inline-block;">--%>
<%--                                    <div class="newTicket" style="margin-left: 20px;">--%>
<%--&lt;%&ndash;                                        <div style="width: 120px;height: 85px;cursor:pointer;margin-top: 0px;" class="uploadFiles" onclick="uploadPorjectContract()">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--                                    </div>--%>
<%--                                </li>--%>
<%--                            </c:if>--%>
<%--                        </c:if>--%>
<%--                    </ul>--%>
<%--                </div>--%>
                <c:if test="${!readOnly}">
                    <div class="saveLineRoadMsg">

                        <c:choose>
                            <c:when test="${type == 8}">
                                <input  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="review(true,this)"
                                        value="已办结">
                                <input style="background: #d9011b !important;border:none !important;"  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="review(false,this)"
                                       value="未办结">
                            </c:when>
                            <c:otherwise>
                                <input  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="review(true,this)"
                                        value="同意">
                                <input style="background: #d9011b !important;border:none !important;"  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="review(false,this)"
                                       value="拒绝">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
    <!--审核弹出框-->
    <div class="publicContainerShow batchRecheckShowContainer reviewReasonContainer" style="display:none;overflow: hidden;">
        <div class="remarksContainer newRemarksContainer" style="margin-top: 10px;">
            <form id="reviewReasonForm" name="reviewReason">
                <input id="passStatus" name="passStatus" hidden="hidden" class="newPublicInput publicSwitchPlanProject" type="textarea" style="width: 10%;">
                <div class="configOil" style="position:relative;">
                    <input id="reviewReason" class="reviewReason" name="reviewReason" type="hidden" value=""/>
                    <textarea onfocus="document.getElementById('note').style.display='none'" onblur="if(value=='')document.getElementById('note').style.display='block'"  id="reviewReasonTextArea" class="reviewReason" style="resize:none;background: transparent;position: relative;z-index: 999;"></textarea>
                    <div id="note" class="note">
                        <font color="#777">请输入放款申请审核意见</font>
                    </div>
                </div>
                <div>
                    <span class="passButton" onclick="saveReviewReason();" style="background: #0071db;" id="passButton"></span>
                </div>
            </form>
        </div>
    </div>
    <!--放款校验结果表单-->
    <div class="publicContainerShow batchRecheckShowContainer loanValidationContainer" style="display:none;overflow: hidden;">
        <div class="remarksContainer newRemarksContainer">
            <form id="loanValidationFrom" name="loanValidationFrom">
                <div class="contract">
                    <span>交易对手：</span>
                    <a class="counterpartyName"></a>
                </div>
                <div class="contract">
                    <span>分项限额：</span>
                    <a style="width: auto;" class="subitemLimitBalance"></a>元
                </div>
                <div class="contract">
                    <span>规定融资比例：</span>
                    <a class="ruleRatio"></a>
                </div>
                <div class="contract">
                    <span>本次提用后融资额度：</span>
                    <a style="width: auto;" class="afterThisBalance"></a>元
                </div>
                <div class="contract">
                    <span>实际融资比例：</span>
                    <a class="factRatio"></a>
                </div>
                <div class="contract">
                    <span>是否有逾期：</span>
                    <a class="hasDue"></a>
                </div>
                <div class="contract">
                    <span>是否满足放款条件：</span>
                    <input <c:if test="${creditApplyReview.status!=1 || sessionUser.authMap.OPERATION_LOAN_REVIEW == false}"> disabled="disabled" checked </c:if> id="notValid"  class="enough"  name="enough" type="radio" value="1"  />是
                        <input <c:if test="${creditApplyReview.status!=1 || sessionUser.authMap.OPERATION_LOAN_REVIEW == false}"> disabled="disabled"</c:if>id="valid" class="enough"  name="enough" type="radio" value="0" <c:if test="${creditApplyReview.status==1}" >checked</c:if> />否
                </div>
                <div class="contract">
                    <span>本次已核验运单个数:</span>
                    <a class="checkedWaybillCount"></a>
                </div>
                <div style="clear: both;"></div>
                <div>
                    <span class="passButton" onclick="closeCompany();" style="background: #0071db !important;">确定</span>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    laydate.render({elem: '#dueDate',type: 'date',theme: 'molv'});
    //本页url;
    var url ;
    //上页url
    //     var returnUrl;
        $(document).ready(function () {
            //显示页面标题并绑定返回按钮的跳转地址
            Page.showPageTitleBindReturnUrl();
            $(".totalBalance").html(PF.formatMoney(parseFloat('${creditApplyReview.totalBalance}')));
            $(".applyBalance").html(PF.formatMoney(parseFloat('${creditApplyReview.applyBalance}')));
            $(".remainingBalance").html(PF.formatMoney(parseFloat('${creditApplyReview.remainingBalance}')));
            $(".subitemRemainBalance").html(PF.formatMoney(parseFloat('${creditApplyReview.subitemRemainBalance}')));
            $(".subitemLimitBalance").html(PF.formatMoney(parseFloat('${creditApplyReview.subitemLimitBalance}')));
            $(".transferBalance").html(PF.formatMoney(parseFloat('${creditApplyReview.transferBalance}')));
            $(".manageBalance").html(PF.formatMoney(parseFloat('${creditApplyReview.manageBalance}')));
            $(".interestRate").html(parseFloat('${creditApplyReview.interestRate}').toFixed(2)+"%");
            $(".createTime").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${creditApplyReview.createTime}" type="both"/>');
            $(".dueDate").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${creditApplyReview.dueDate}" type="both"/>');
            $(".graceDate").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${creditApplyReview.graceDate}" type="both"/>');
            $(".bankInfo").html('${creditApplyReview.bankName}'+","+'${creditApplyReview.bankAccountNo}');

            <%-- returnUrl='${returnUrl}';--%>
            <%--url='${url}';--%>
            $(".contract:input").attr("readonly","readonly")
            ajaxLoadBillHistory(${creditApplyReview.id})
        //公用布局模块方法调用
        initReady();

        //处理查看放款申请融资详情权限
        handleSearchFinancingAuth();
    });

    // 异步审核意见
    function ajaxLoadBillHistory(creditApplyId){
        var passStatusMap ={
            true:"同意",
            false:"拒绝"
        }

        ajaxGet("reviewRecord/listReviewHistory.json?creditApplyId="+creditApplyId, function(resp){
            if(!resp.success){
                selfAlert(resp.message);
                return;
            }
            if(resp.data && resp.data.length > 0){
                var list = resp.data;
                var html = "";
                for(var i=0;i<list.length;i++){
                    var item = list[i];
                    if (item.flowId == 1) {//只显示“用信申请审核审批流”
                        html += "<li><span><a>"+item.flowNodeName + "-" + item.createUserName +":"+passStatusMap[item.passStatus]+"</a>&nbsp&nbsp"+ P.long2Datetime(item.createTime);
                        html += "</span><p>" + item.reviewReason + "</p></li>";
                    }
                }
                $("#historyUl").html(html);
            }else{
                $("#historyUl").html("<li>暂无历史记录</li>");
            }

        });
    }
    function setCounterpartyInfo(counterpartyName,subitemLimitBalance,subitemRemainBalance) {
        $("#counterpartyName").html(counterpartyName);
        $("#counterpartyBalance").html("分项金额："+PF.formatMoney(parseFloat(subitemLimitBalance))+"元,剩余可用额度："+PF.formatMoney(parseFloat(subitemRemainBalance))+"元");
    }
    //校验窗口
    function toLoanValidation() {
        var objs = $(".loanValidationContainer");
        $(".counterpartyName").html("${creditApplyReview.counterpartyName}");
        $(".ruleRatio").html(parseFloat('${creditApplyReview.ruleRatio}')+"%");
        $(".subitemLimitBalance").html(PF.formatMoney(parseFloat("${creditApplyReview.subitemLimitBalance}")));
        $(".afterThisBalance").html(PF.formatMoney(parseFloat("${creditApplyReview.afterThisBalance}")));
        $(".factRatio").html((parseFloat("${creditApplyReview.factRatio}")*100).toFixed(2)+"%");
        var hasDue =${creditApplyReview.hasDue}?"是":"否"
        $(".hasDue").html(hasDue);
        var checkWaybillCount = "${creditApplyReview.checkedWaybillCount}" + "/" + "${creditApplyReview.needCheckWaybillCount}";
        $("#loanValidationFrom .checkedWaybillCount").html(checkWaybillCount);

        //TODO-LA: 2020/3/24 需要判断记录查阅运单数才能判断
        <%--var inRule =${creditApplyReview.hasDue}&&${creditApplyReview.ruleRatio}>${creditApplyReview.factRatio}?"是":"否";--%>
        // $(":input[name='inRule']").attr("value",inRule);

        layer.open({
                       type: 1,
                       title: ["放款申请校验结果", 'font-size:16px;color:white;background-color:#0071db;'],
                       area: ['500px', '450px'],
                       closeBtn: 0,
                       content: objs
                   });
    }
    <%--//查看申请金额详情--%>
    <%--function toBalanceDetail(creditApplyId) {--%>
    <%--    Page.clickBtnToPage("balanceDetail.html?creditApplyId="+creditApplyId+"&type="+type,)--%>
    <%--    var url = '${url}';--%>
    <%--    P.setReturnUrlForm("balanceDetail",url);--%>
    <%--    ajaxGet("balanceDetail.html?creditApplyId="+creditApplyId+"&type="+type, function (resultHtml) {--%>
    <%--        $(window.mainContainer).html(resultHtml);--%>
    <%--    });--%>
    <%--}--%>
    /**
     * 跳转到-标的债券列表
     */
    function toSubjectClaimsOrderList(creditApplyId,counterpartyId){
        var reviewStatus = 1;
        if('${creditApplyReview.status}'<0 ){
            reviewStatus = -1;
        }
        Page.clickBtnToPage("subjectClaimsOrder.html?&type=${type}&readOnly=${readOnly}&reviewStatus="+reviewStatus+"&creditApplyId="+creditApplyId+"&counterpartyId="+counterpartyId,"查看申请融资金额")
        <%--var url='${url}';--%>
        <%--P.setReturnUrlForm("subjectClaimsOrder",url)--%>
        <%--containerShow("subjectClaimsOrder.html?reviewStatus=1&creditApplyId="+creditApplyId+"&counterpartyId="+counterpartyId);--%>
    }
    function review(pass,obj) {
        if (!validateReviewPassCondition(pass)) {
            return false;
        }

        //清空内容
        $("#reviewReason").val("");
        $("#reviewReasonTextArea").val("");
        var objs = $(".reviewReasonContainer");
        $("#passStatus").val(pass);
        var passButton = pass?"同意":"拒绝" ;

        $("#passButton").html(passButton);
        layer.open({
                       type: 1,
                       title: ["审核意见", 'font-size:16px;color:white;background-color:#0071db;'],
                       area: ['500px', '350px'],
                       closeBtn: 4,
                       content: objs
                   });
//        if(pass){
//            $(".passButton").css("background","#169BD5");
//        }else{
//            $(".passButton").css("background","#d9001b");
//        }
    }

    //保存审核
    function saveReviewReason() {
        var formData = P.formData(document.reviewReason);
        formData.reviewReason = $("#reviewReasonTextArea").val();
        if (isLengthOutRange(formData.reviewReason, true, 5, 100)) {
            selfAlert("请填写5-100字审核理由!");
            return false;
        }
        var designatedConsumption = $(".designatedConsumption:checked").val();
        var enough = $(".enough:checked").val();
        var passStatus = $("#passStatus").val();
        var creditApplyId = '${creditApplyId}';
        formData.creditApplyId=creditApplyId;
        formData.hasCharge=designatedConsumption;
        //满足放款条件
        formData.enough=enough;
        ajaxPost("creditApply/review.json", formData, function (resp) {
            if (resp.success) {
                selfAlert("审核成功!",function () {
                    layer.closeAll();
                    if( passStatus=="true"&&${creditApplyReview.status}==5&&resp.code==2000){
                        window.open("creditApply/exportReviewBill.html?reviewBillUrl="+resp.data);
                    }
                    //确认下returnPage 是否正常，确认后，即可删除debugger
                    Page.returnPage();

                    <%--returnPage("${returnUrl}");--%>
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }




    /**
     * 处理查看放款申请融资详情权限
     */
    function handleSearchFinancingAuth() {
        if ("${sessionUser.authMap.OPERATION_LOAN_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.RISK_LOAN_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.MGMT_LOAN_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.FINANCE_LOAN_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.CASHIER_LOAN_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.OPERATION_RECORD_LOAN_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.RISK_RECORD_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.MGMT_RECORD_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.FINANCE_RECORD_LOAN_FINANCING_DETAIL}" == "true" ||
            "${sessionUser.authMap.CASHIER_RECORD_LOAN_FINANCING_DETAIL}" == "true") {
            $("#searchFinancingBtn").show();
        } else {
            $("#searchFinancingBtn").hide();
        }
    }

    //验证审核通过的条件
    function validateReviewPassCondition(pass) {
        if (pass) {
            if ("${type}" == "4") {
                if($(".enough:checked").val()==0){
                    selfAlert("未满足放款条件!");
                    return false;
                }
                if("${sessionUser.authMap.OPERATION_LOAN_FINANCING_DETAIL}" == "true" ){
                    if ("${creditApplyReview.checkedWaybillCount}" < "${creditApplyReview.needCheckWaybillCount}") {
                        selfAlert("请至少查阅${creditApplyReview.needCheckWaybillCount}条运单!");
                        return false;
                    }
                }
            }

            if ("${creditApplyReview.status}" == "3" && $("input[name=designatedConsumption]:checked").val() != 1) {
                selfAlert("未支付保理管理费!");
                return false;
            }
        }

        return true;
    }

    console.log(123)
    /**
     * 显示合同
     * @param type
     * @param waybillId
     */
    function showAnxinContract(pdfPath,signPdfPath) {
        if(signPdfPath){
            pdfPath=signPdfPath;
        }
        pdfPath = pdfPath.replace("http://img.", "https://img.");   //测试环境
        pdfPath = pdfPath.replace("http://dev.", "https://dev.");   //正式环境

        var html ='<div style="overflow: hidden;width: 100%;height: 100%;"><iframe src="'+pdfPath+'" width="100%" height="100%"></iframe></div>';
        layer.open({
                       title: ['转让确认书', 'font-size:16px;color:white;background-color:#0070db;'],
                       type: 1,
                       area: ['950px', '100%'],
                       content: html
                   });
    }
    /**
     * 显示合同
     * @param type
     * @param waybillId
     */
    function showReviewBill(url) {
        window.open("creditApply/exportReviewBill.html?reviewBillUrl="+url);;
    }
</script>
