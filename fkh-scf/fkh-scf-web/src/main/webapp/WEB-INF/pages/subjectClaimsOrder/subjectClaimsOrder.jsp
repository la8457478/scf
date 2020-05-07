<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>标的债权管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil span{margin-left: 30px;}
    .configOil span:first-child{margin-left: 0px;font-size: 14px;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>用款申请</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form action="subjectClaimsOrder/listPage.json" id="subjectClaimsOrderForm" name="searchForm" onsubmit="return searchReviewInvoices();" autocomplete="off">

            <c:if test="${!hiddenCounterpary}">
                <select class="newPublicSelect newPublicSelect1 publicSelectSwitchTimeProject" id="counterpartyId"   name="counterpartyId"  value="${counterpartyId}" onchange="queryCounterpartyName()" style="width:15%  !important;">
                    <option value='-1'>请选择交易对手</option>
                </select>
            </c:if>
            <c:if test="${hiddenCounterpary}">
                <input id="counterpartyId" name="counterpartyId" hidden="hidden" class="newPublicInput publicSwitchPlanProject" value="${counterpartyId}">
                <input id="projectId" name="projectId" hidden="hidden" class="newPublicInput publicSwitchPlanProject" value="${projectId}">
            </c:if>
            <input id="subjectClaimsOrderNo" name="subjectClaimsOrderNo" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入应收账款转让订单号" style="width: 200px;">
            <input id="creditApplyId" name="creditApplyId" hidden="hidden" class="newPublicInput publicSwitchPlanProject" value="${creditApplyId}" style="width: 10%;">

            <input type="hidden" id="pageNo" name="pageNo" value="1">
            <input type="hidden" hidden="hidden" id="reviewStatus" name="reviewStatus" value="${reviewStatus}">
            <div class="newPublicCustomSearchBtn" onclick="searchSubjectClaimsOrder()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <div class="newPublicCustomSearchBtn" onclick="exportExcel()"><a>导出</a></div>
            <%--<div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>--%>
            <div class="publicDetailsModuleReturnBtn return" <c:if test="${!hiddenReturn}"> hidden ="hidden" </c:if>  >返回</div>

        </form>
    </div>
    <!--公用导航模块end-->
    <p id="counterpartyName">
    </p>
    <p id="counterpartyBalance"class="sumAmountColor" style="top: 0px;">
    </p>

    <!--公用表格模块-->
    <div class="newPublicTable">

        <table>
            <colgroup>
                <c:if test="${reviewStatus == 0}" >
                    <col width="70">
                </c:if>
                <col >
                <col >
                <col >
                <col >
                <col >
            </colgroup>
            <tbody>
            <tr>
                <c:if test="${reviewStatus == 0}" >
                    <th class="newPublicTableCenter"></th>
                </c:if>
                <th class="newPublicTableCenter">应收账款转让订单号</th>
                <th class="newPublicTableCenter">交易对手</th>
                <th class="newPublicTableCenter">本次可提用融资额度（元）</th>
                <th class="newPublicTableCenter">该订单应收账款转让金额（元）</th>
                <th class="newPublicTableCenter">运单个数</th>
<%--                <th class="newPublicTableCenter">是否有CFCA保全记录</th>--%>

            </tr>
            </tbody>
            <tbody id="projectList"></tbody>
        </table>

        <c:if test="${reviewStatus == 0 && (sessionUser.authMap.SUBJECT_CLAIMS_CREDIT_APPLY == true || sessionUser.authMap.CREDIT_APPLY == true)}">
            <div id="batchToOutdiv" >
                <div style="margin-left: 0px;margin-top: 10px;" class="newPublicCustomGoodsBtn" id="startCreditApply" onclick="startCreditApply()"><a>发起用款申请</a></div>
            </div>
        </c:if>
        <div id="projectPageBar" class="pageBar"></div>
    </div>
    <!--公用表格模块end-->
</div>


</body>
</html>

<script type="text/javascript">
    laydate.render({elem: '#startTime',type: 'datetime',theme: 'molv'});
    var totalPages;
    var  counterpartyName ;
    var  subitemLimitBalance ;
    var  subitemRemainBalance;
    var transferBalance = 0;
    var canApplyBalance = 0;
    var checkIds = [];
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        Page.showPageTitleBindReturnUrl();
        var creditApplyId = "${creditApplyId}";
        var counterpartyId = "${counterpartyId}";
        if(counterpartyId!=null&&counterpartyId!=''){
              counterpartyName = '${counterpartyName}';
              subitemLimitBalance = '${subitemLimitBalance}';
              subitemRemainBalance = '${subitemRemainBalance}';
            setCounterpartyInfo(counterpartyName,subitemLimitBalance,subitemRemainBalance);
        }else{
            $("#counterpartyName").html("");
            $("#counterpartyBalance").html("");
        }
        initBlankTable();
        <c:if test="${hiddenCounterpary}">
            //回显条件
            Page.initFormData(document.searchForm);
            searchSubjectClaimsOrder();
        </c:if>
        <c:if test="${!hiddenCounterpary}">
            queryCounterpartyName();
        </c:if>
    });
    function initBlankTable() {
        var dataTableId = "projectList";
        var dataTablePageBarId = "projectPageBar";
        var html = "";
        <c:if test="${reviewStatus == 0}" >
        html = "<tr><td colspan='6' align='center'>暂无数据！</td></tr>";
        </c:if>
        <c:if test="${reviewStatus != 0}" >
        html = "<tr><td colspan='5' align='center'>暂无数据！</td></tr>";
        </c:if>
        $("#" + dataTableId).html(html);
        $("#" + dataTablePageBarId).hide();
    }
    function setCounterpartyInfo(counterpartyNameParams,subitemLimitBalanceParams,subitemRemainBalanceParams) {
        counterpartyName = counterpartyNameParams;
        subitemLimitBalance =subitemLimitBalanceParams;
        subitemRemainBalance =subitemRemainBalanceParams;
        $("#counterpartyName").html(counterpartyName);
        $("#counterpartyBalance").html("分项融资限额："+PF.formatMoney(parseFloat(subitemLimitBalance))+"元,可提用融资余额："+PF.formatMoney(parseFloat(subitemRemainBalance))+"元");
    }
    //搜索项目列表
    function searchSubjectClaimsOrder() {
        //清空勾选内容
        transferBalance = 0;
        canApplyBalance = 0;
        checkIds = [];
        <%--if ("${sessionUser.authMap.SUBJECT_CLAIMS_WAYBILL_LIST}" == "false" && "${sessionUser.authMap.CREDIT_APPLY_WAYBILL_LIST}" == "false") {--%>
            <%--$("#projectList").html("<tr><td colspan='7' align='center'>暂无权限！</td></tr>");--%>
            <%--$("#projectPageBar").hide();--%>
            <%--return false;--%>
        <%--}--%>
        var creditApplyId = "${creditApplyId}";
        if (document.searchForm) {
            var formData = P.formData(document.searchForm);
            var counterpartyId = "${counterpartyId}";
            if(counterpartyId==null||counterpartyId=='') {
                if (formData.counterpartyId != null && formData.counterpartyId != "" && formData.counterpartyId
                    != "0") {
                    ajaxGet("counterparty/getDetail.json?id=" + formData.counterpartyId, function (resp) {
                        if (resp.data != null) {
                            setCounterpartyInfo(resp.data.counterpartyName, resp.data.subitemLimitBalance,
                                                resp.data.subitemRemainBalance)
                        } else {
                            $("#counterpartyName").html("");
                            $("#counterpartyBalance").html("");
                        }
                    });
                } else {
                    $("#counterpartyName").html("");
                    $("#counterpartyBalance").html("");
                }
            }else{
                formData.counterpartyId = counterpartyId;
                ajaxGet("counterparty/getDetail.json?id=" + formData.counterpartyId, function (resp) {
                    if (resp.success) {
                        $("#counterpartyBalance").html("分项融资限额："+PF.formatMoney(parseFloat(resp.data.subitemLimitBalance))+"元,可提用融资余额："+PF.formatMoney(parseFloat(resp.data.subitemRemainBalance))+"元");
                    }
                });
            }
            ajaxPost("subjectClaimsOrder/listPage.json?creditApplyId="+creditApplyId, formData, function (resp) {
                parseInvoiceObjToHtml(resp);
            });

        }
        return false;
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var dataTableId = "projectList";
        var dataTablePageBarId = "projectPageBar";
        var html = "";
        var responseData = resp.data;
        if (responseData.records.length <= 0) {
            <c:if test="${reviewStatus == 0}" >
            html = "<tr><td colspan='6' align='center'>暂无数据！</td></tr>";
            </c:if>
            <c:if test="${reviewStatus != 0}" >
            html = "<tr><td colspan='5' align='center'>暂无数据！</td></tr>";
            </c:if>
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            <c:if test="${reviewStatus == 0}" >//初始化才显示勾选框
            if (checkIds.indexOf(item.id + "") > -1) {
                html+=  '<td class="newPublicTableCenter"><input type="hidden" class="subjectClaimsOrderId" value="'+item.id+'"/><input type="checkbox" class="subjectClaimsOrder" transferBalance="'+item.transferBalance+'"  canApplyBalance="'+item.canApplyBalance+'" value="'+item.id+'" name="subjectClaimsOrderAll" onclick="selectSubjectClaimsOrder(this);" checked></td>';
            } else {
                html+=  '<td class="newPublicTableCenter"><input type="hidden" class="subjectClaimsOrderId" value="'+item.id+'"/><input type="checkbox" class="subjectClaimsOrder" transferBalance="'+item.transferBalance+'"  canApplyBalance="'+item.canApplyBalance+'" value="'+item.id+'" name="subjectClaimsOrderAll" onclick="selectSubjectClaimsOrder(this);"></td>';
            }
            </c:if>
            html += '<td class="newPublicTableCenter" title="' + item.subjectClaimsOrderNo + '" >' + item.subjectClaimsOrderNo + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.counterpartyName + '" >' + item.counterpartyName + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.canApplyBalance + '" >' + PF.formatMoney(item.canApplyBalance) + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.transferBalance + '" >' + PF.formatMoney(item.transferBalance) + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.waybillCount + '" >' +'<a onclick="toWaybillList('+item.id+')" class="tabs-close">'+item.waybillCount+'</a>'  + '</td>';
            html += "</tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.searchForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }

    function queryCounterpartyName() {
        var counterpartyId=$("#counterpartyId").val();
        if($("#counterpartyId").find("option").length>1){
            return;
        }
        $.post("counterparty/listByCompanyId.json", function (resp) {
            var lis="";
            var options=resp.data;
            $("#counterpartyId").html('<option value="">请选择交易对手</option>');
            for(var i=0;i<options.length;i++){
                if('${counterpartyId}'==options[i].id){
                    $("#counterpartyId").append("<option selected value='"+options[i].id+"'>"+options[i].counterpartyName+"</option>");
                }else{
                    $("#counterpartyId").append("<option value='"+options[i].id+"'>"+options[i].counterpartyName+"</option>");
                }
            }
            //异步加载搜索条件后，再回显条件，并搜索列表
            Page.initFormData(document.searchForm);
            if($("#counterpartyId").val()>0){
                searchSubjectClaimsOrder();
            }
            // $("ul[name='siteNameUl']").html(lis);
        })
    }
    function toWaybillList(subjectClaimsOrderId) {
        if (handleSearchWaybillAuth()) {
            var creditApplyId = $("#creditApplyId").val();
            Page.clickBtnToPage("waybillList.html?&type=${type}&readOnly=${readOnly}&subjectClaimsOrderId="+subjectClaimsOrderId+"&creditApplyId="+creditApplyId,"运单列表");
        }
        <%--var returnUrl = "${url}";--%>
        <%--ajaxGet("waybillList.html?subjectClaimsOrderId="+subjectClaimsOrderId+"&returnUrl="+returnUrl, function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
    }
    function startCreditApply() {
        // var checks= $("input[name='subjectClaimsOrderAll']");
        //  var checkIds =[];
        // var transferBalance=0;
        // var canApplyBalance=0;
        //  $.each(checks, function (index, item) {
        //      var check =  item.checked;
        //      if(check){
        //          transferBalance += parseFloat( item.attributes['transferBalance'].value);
        //          canApplyBalance +=   parseFloat(item.attributes['canApplyBalance'].value);
        //          var id =item.value;
        //          checkIds.push(id)
        //      }
        //
        //  });
         if(checkIds.length==0){
             selfAlert("请选择应收账款转让订单！");
             return ;
         }
        var subjectClaimsOrderIds = checkIds.join(",");
        transferBalance = transferBalance.toFixed(2);
        canApplyBalance = canApplyBalance.toFixed(2);
        <%--var returnUrl = "${url}";--%>
         counterpartyId=$("#counterpartyId").val()==""?"${counterpartyId}":$("#counterpartyId").val();
        Page.clickBtnToPage("startCreditApply.html?subjectClaimsOrderIds="+subjectClaimsOrderIds+"&transferBalance="+transferBalance
                            +"&canApplyBalance="+canApplyBalance+"&counterpartyName="+encodeURIComponent(counterpartyName)
                            +"&subitemLimitBalance="+subitemLimitBalance+"&subitemRemainBalance="+subitemRemainBalance
                            +"&counterpartyId="+counterpartyId,"发起用款申请");
        P.setGlobalForm(2);


        // ajaxGet("startCreditApply.html?subjectClaimsOrderIds="+subjectClaimsOrderIds+"&returnUrl="+returnUrl+"&transferBalance="+transferBalance+"&canApplyBalance="+canApplyBalance+"&counterpartyName="+counterpartyName+"&subitemLimitBalance="+subitemLimitBalance+"&subitemRemainBalance="+subitemRemainBalance+"&counterpartyId="+counterpartyId, function (resultHtml) {
        //     $(window.mainContainer).html(resultHtml);
        // });
    }
    /**
     * 导出表格
     */
    function exportExcel(){
        var form=$("#subjectClaimsOrderForm").serialize();
        window.open("subjectClaimsOrder/exportExcel.html?"+form);
    }



    /**
     * 处理查看放款申请已转让运单权限
     */
    function handleSearchWaybillAuth() {
        if ("${sessionUser.authMap.OPERATION_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.RISK_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.MGMT_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.FINANCE_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.CASHIER_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.OPERATION_RECORD_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.RISK_RECORD_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.MGMT_RECORD_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.FINANCE_RECORD_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.CASHIER_RECORD_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.SUBJECT_CLAIMS_WAYBILL_LIST}" == "true") {
            return true;
        }

        return false;
    }

    /**
     * 勾选应收款转让订单
     */
    function selectSubjectClaimsOrder(item) {
        var check = item.checked;
        var id = item.value;
        var index = checkIds.indexOf(id);
        if (check) {//选中
            if (index <= -1) {
                checkIds.push(id);
                transferBalance += parseFloat(item.attributes['transferBalance'].value);
                canApplyBalance += parseFloat(item.attributes['canApplyBalance'].value);
            }
        } else {//去掉选中
            if (index > -1) {
                checkIds.splice(index, 1);
                transferBalance -= parseFloat(item.attributes['transferBalance'].value);
                canApplyBalance -= parseFloat(item.attributes['canApplyBalance'].value);
            }
        }
    }
</script>
