<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        .contract {
            padding-top: 15px;
        }

        .contract span {
            display: inline-block;
            width: 120px;
            text-align: left;
        }

        .contract .newPublicInput {
            width: 270px;
        }

        .line_contract {
            width: 98%;
            height: 1px;
            background: #e7e6eb;
            margin: 0 auto;
            margin-top: 25px;
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


        .uploadImgFilePic #uploadImgFile {
            position: absolute;
            top: 0px;
            left: 0px;
            width: 100%;
            height: 100%;
            opacity: 0;
            filter: alpha(opacity=0);
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

        #applyBalanceTip{
            width: 350px;
            margin-left: 10px;
            text-align: left;
        }
        .contract a{display: inline-block;width: 170px;text-align: right;}
        .contract span{width: 120px;margin-left: 5px;}
        #contractList li:first-child{margin-left: 0px !important;}
    </style>
</head>

<!--结算详情页面-->
<div class="paymentPage">

    <!--顶部导航-->
    <div class="publicHeaderNav">
<%--        <ul>--%>
<%--            <li>--%>
<%--                <a href="javascript:;" class="returnPay">我的额度</a>--%>
<%--                <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--                <a href="javascript:;" class="returnChild">应收账款转让</a>--%>
<%--                <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--                <a href="javascript:;" class="returnChild">发起用款申请</a>--%>
<%--            </li>--%>
<%--        </ul>--%>
    </div>
    <!--项目上传合同文件弹出框 start-->
    <jsp:include page="../includes/uploadContractDialog.jsp"></jsp:include>
    <!--顶部导航 end-->
    <!--确认支付主题内容-->
    <div class="public_container" id="new_public_height3"
         style="width: 100%;background: white; border: 1px solid #e6e6ef; padding-bottom: 20px;">
        <div class="publicMainContainerBox publicMainContainerBoxs">
            <!--头部公用-->
            <div class="newPublicHeader">
                <span>发起用款申请</span>
                <div  class="publicDetailsModuleReturnBtn returnPro returnChild return">返回
                </div>
            </div>
            <form  id="applyBalanceForm" name="applyBalanceForm">
                <p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 16px;padding-left: 10px;" id="counterpartyName"></a></p>
                <%--<div  id=""></div>--%>
                <div id="counterpartyBalance" class="sumAmountColor" style="top: 0px;padding-left: 33px;">

                </div>
                <div class="contract">
                    <input type="text" id="subjectClaimsOrderIds" hidden="hidden" name="subjectClaimsOrderIds" value="${subjectClaimsOrderIds}" class="newPublicInput"
                           placeholder="id">

                    <a>本次可提用融资额度：</a>
                    <span id = "canApplyBalance" ></span>
                    <a>本次应收账款有效转让金额：</a>
                    <span id = "transferBalance" ></span>
                </div>
                <div class="contract">
                    <a>本次申请融资金额：</a>
                    <input style="margin-left: 5px;width: 200px;" type="text" id="applyBalance" name="applyBalance" class="newPublicInput"
                           placeholder="请输入本次申请融资金额" oninput="calculateManageBalance()"  onblur="calculateManageBalance()" maxlength="15">
                    <span id="applyBalanceTip" style="color: red;"></span>
                </div>

                <div class="contract">
                    <a>应收账款到期日：</a>
                    <span id = "dueDate" ></span>

                    <a>融资利率：</a>
                    <span  id = "interestRate" ></span>

                </div>

                <div class="contract">
                    <a>保理管理费：</a>
                    <span   id = "manageBalance" ></span>
                </div>
                <%--<div class="line_contract"></div>--%>
<%--                <span style="display: inline-block; width: 170px;text-align: right;float: left;margin-top: 45px;">上传附件：</span>--%>
<%--                &lt;%&ndash;<p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">合同附件</a></p>&ndash;%&gt;--%>
<%--                <div class="" style="margin-top: 0px;margin-bottom: 40px;    float: left;">--%>
<%--                    <input type="hidden" id="uploadNewFile" value="0">--%>
<%--                    <ul id="contractList">--%>
<%--                        <li id="uploadPorjectContractButton" style="margin-left: 60px;  display: inline-block;">--%>
<%--                            <div class="newTicket" style="margin-left: 20px;">--%>
<%--                                <div style="width: 120px;height: 85px;cursor:pointer;margin-top: 0px;" class="uploadFiles" onclick="uploadPorjectContract()">--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </div>--%>

                <div style="clear: both;"></div>
                <div class="saveLineRoadMsg">
                    <input onclick="saveCredisApply()" id="saveCredisApplyButton" class="newPublicAlertBtn new_public_gray_bg" type="button" disabled="disabled"
                           value="发起用款申请">
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var contractInfos=[];

    // laydate.render({elem: '#dueDate',type: 'date',theme: 'molv'});
    var  counterpartyName ;
    var  subitemLimitBalance ;
    var  subitemRemainBalance;
    var  transferBalance ;
    var  canApplyBalance;
    var manageRate;
    var form ={};
$(document).ready(function () {
    //显示页面标题并绑定返回按钮的跳转地址
    Page.showPageTitleBindReturnUrl();
        //公用布局模块方法调用
        initReady();
        counterpartyName = '${counterpartyName}';
        subitemLimitBalance = '${subitemLimitBalance}';
        subitemRemainBalance = '${subitemRemainBalance}';

        setCounterpartyInfo(counterpartyName,subitemLimitBalance,subitemRemainBalance);
        transferBalance = '${transferBalance}';
        canApplyBalance = '${canApplyBalance}';
        if (parseFloat(canApplyBalance) <= parseFloat(subitemRemainBalance)) {
            $("#applyBalanceTip").html("本次可申请最大额度：" + PF.formatMoney(parseFloat(canApplyBalance)) + "元");
        } else {
            $("#applyBalanceTip").html("本次可申请最大额度：可提用融资余额值" + PF.formatMoney(parseFloat(subitemRemainBalance)) + "元");
        }
        setSubjectClaimsOrder(transferBalance,canApplyBalance);
        if ("${reStartCreditApply}" == "true") {
            var dueDate = P.long2Date(${dueDate});
            $("#dueDate").html(dueDate);
            form.dueDate = dueDate;
            $("#interestRate").html(PF.formatMoney(parseFloat("${interestRate}"))+"%");
            manageRate = "${manageRate}";
        } else {
            var formData = {
                "subjectClaimsOrderIds":"${subjectClaimsOrderIds}",
                "counterpartyId":'${counterpartyId}'
            }
            ajaxPost("subjectClaimsOrder/calculate.json", formData, function (resp) {
                if (resp.success) {
                    var data =resp.data;
                    $("#dueDate").html(P.long2Date(data.dueDate));
                    form.dueDate =P.long2Date(data.dueDate);
                    $("#interestRate").html(data.interestRate+"%");
                    manageRate = data.manageRate;

                } else {
                    selfAlert(resp.message);
                }
            });
        }
    });
    function setCounterpartyInfo(counterpartyName,subitemLimitBalance,subitemRemainBalance) {
        $("#counterpartyName").html(counterpartyName);
        $("#counterpartyBalance").html("分项融资限额："+PF.formatMoney(parseFloat(subitemLimitBalance))+"元,可提用融资余额："+PF.formatMoney(parseFloat(subitemRemainBalance))+"元");
    }
    function setSubjectClaimsOrder(transferBalance,canApplyBalance) {
        form.transferBalance=transferBalance;
        form.canApplyBalance=canApplyBalance;
        $("#transferBalance").html(PF.formatMoney(parseFloat(transferBalance)));
        $("#canApplyBalance").html(PF.formatMoney(parseFloat(canApplyBalance)));
    }
    function calculateManageBalance() {
       var applyBalance =  $("#applyBalance").val();
        // if (isValueOutRange(parseFloat(applyBalance), true, 0.01, Math.min(subitemRemainBalance,canApplyBalance))) {
        //     selfAlert("本次申请融资额度超过最大额度！");
        //     return false;
        // }
        // if (P.isNullOrEmptyString(applyBalance) || !isAmount(applyBalance)) {
        //     selfAlert("请填写最多两位小数的申请金额!");
        //     return false;
        // }
        if (isAmount(applyBalance)) {
            var manageBalance =   applyBalance * manageRate/100;
            $("#manageBalance").html(PF.formatMoney(parseFloat(manageBalance)));
            form.manageBalance=manageBalance;
            form.applyBalance=applyBalance;

            if (parseFloat(applyBalance)  >= 10) {
                $("#saveCredisApplyButton").attr("class","newPublicAlertBtn new_public_blue_bg");
                $("#saveCredisApplyButton").removeAttr("disabled");
            } else {
                $("#saveCredisApplyButton").attr("class","newPublicAlertBtn new_public_gray_bg");
                $("#saveCredisApplyButton").attr("disabled", true);
            }
        } else {
            if (parseFloat(applyBalance)  >= 10) {
                $("#saveCredisApplyButton").attr("class","newPublicAlertBtn new_public_blue_bg");
                $("#saveCredisApplyButton").removeAttr("disabled");
            } else {
                $("#saveCredisApplyButton").attr("class","newPublicAlertBtn new_public_gray_bg");
                $("#saveCredisApplyButton").attr("disabled", true);
            }
            $("#manageBalance").html("0.00");
        }
    }

    //上传合同文件地址
    function uploadPorjectContract(){
        //限制当前合同个数为10个
        if (contractInfos.length >= 10) {
            selfAlert("请最多上传10个附件!");
            return false;
        }

        var obj = $(".uploadContractDialogContainer");
        layer.open({
                       type: 1,
                       title: ['上传合同文件信息', 'font-size:16px;color:white;background-color:#0070db;'],
                       area: ['450px', '280px'],
                       closeBtn: 4,
                       content: obj
                   });
        $("#upMessage").html("");
        $("#textfield").val("");
        $("#file").val("");
    }
    //保存合同文件地址
    function updatePorjectContract(name,path){
        var file={
            name:name,
            path:path
        };
        $("#uploadNewFile").val(1);
        contractInfos.push(file);
        selfAlert("保存合同成功!");
        var html='<li style="margin-left: 50px;  display: inline-block;"><a href="'+path+'" target="_blank"><img src="newassets/imgs/icon_pdf.png"></a>';
        html+='<p><a title="'+name+'">'+name+'</a></p></li>';
        if($("#contractList li").size()>1){
            $("#contractList>li:last").prev().after(html);
        }else{
            $("#contractList").html(html+$("#contractList").html());
        }
        layer.closeAll();
        if($("#contractList li").size()>=21){
            $("#uploadPorjectContractButton").hide();
        }

    }
    //保存
    function saveCredisApply() {
        var formData = P.formData(document.applyBalanceForm);
        if (!validateParams (formData)) {
            return false;
        }
        // formData.attachment=JSON.stringify(contractInfos);
        form.attachment = JSON.stringify(contractInfos);
        form.subjectClaimsOrderIds =  formData.subjectClaimsOrderIds;

        if ("${reStartCreditApply}" == "true") {//重新发起用款申请
            form.counterpartyId = "${counterpartyId}";
            form.id = "${creditApplyId}";
            ajaxPost("creditApply/reCreate.json", form, function (resp) {
                if (resp.success) {
                    selfAlert("保存成功!",function () {
                        layer.closeAll();
                        $("#pageNo").val($("span.on").html());
                        Page.returnPage();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        } else {//首次发起用款申请
            ajaxPost("creditApply/create.json", form, function (resp) {
                if (resp.success) {
                    selfAlert("保存成功!",function () {
                        layer.closeAll();
                        $("#pageNo").val($("span.on").html());
                        Page.returnPage();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        }
    }

    /**
     * 校验参数
     */
    function validateParams(formData) {
        if (isValueOutRange(parseFloat(formData.applyBalance), true, 10, Math.min(subitemRemainBalance,canApplyBalance))) {
            selfAlert("本次申请融资额度超过最大额度！");
            return false;
        }
        if (P.isNullOrEmptyString(formData.applyBalance) || !isAmount(formData.applyBalance)) {
            selfAlert("请填写最多两位小数的申请金额!");
            return false;
        }
        return true;
    }
</script>
