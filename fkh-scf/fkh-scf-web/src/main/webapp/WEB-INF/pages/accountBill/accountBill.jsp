<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账单管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil{margin-top: 25px;}
    .configOil span{margin-left: 30px;}
    .configOil span:first-child{margin-left: 0px;font-size: 14px;}
    .saveConfig{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
    <ul>
        <li>
            <a>账单管理</a>
        </li>
    </ul>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm" style="height: 110px;">
        <form action="accountBill/listPage.json" name="searchForm"  id="searchForm"  autocomplete="off">
            <div>
                <c:if test="${type==2}">
                    <input id="companyName" name="companyName" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入客户名称" style="width: 12%;">
                </c:if>
                <input id="counterpartyName" name="counterpartyName" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入交易对手名称" style="width: 16%;">
                <input id="creditApplyNo" name="creditApplyNo" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入放款申请订单号" style="width: 12%;">
                <select id="searchTimeType" name="searchTimeType" class="newPublicInput publicSwitchPlanProject" style="width: 10%;">
                    <option value="1">按转让日</option>
                    <option value="2">按到期日</option>
                </select>
                <input style="width:12%;margin-left: 0px;" class="newPublicInput newPublicInputOne NewTimeInput" type="text" id="beginTime" name="beginTime" readonly="readonly"  placeholder="开始时间">
                <input style="width:12%;margin-left: 0px;" class="newPublicInput newPublicInputOne NewTimeInput" type="text" id="overTime" name="overTime" readonly="readonly"  placeholder="结束时间">
            </div>

            <div style="position: relative;top:-20px;">
                <span style="padding-left: 15px;">近期还款</span>
                <select id="repayDate" name="repayDate" class="newPublicInput publicSwitchPlanProject" style="width: 6%;">
                    <option value="">全部</option>
                    <option value="1">1天到期</option>
                    <option value="3">3天到期</option>
                    <option value="7">7天到期</option>
                </select>
                <span style="padding-left: 15px;">是否结清</span>
                <select id="repayStatus" name="repayStatus" class="newPublicInput publicSwitchPlanProject" style="width: 6%;">
                    <option value="">全部</option>
                    <option value="3">结清</option>
                    <option value="1">未结清</option>
                </select>
                <span style="padding-left: 15px;">是否到期</span>
                <select id="dueStatus" name="dueStatus" class="newPublicInput publicSwitchPlanProject" style="width: 6%;">
                    <option value="">全部</option>
                    <option value="0">未到期</option>
                    <option value="1">到期</option>
                    <option value="2">已超期</option>
                </select>
                <input type="hidden" id="pageNo" name="pageNo" value="1">

                <div class="newPublicCustomSearchBtn" onclick="searchPage()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
                <div class="newPublicCustomSearchBtn" onclick="exportExcel()"><a>导出</a></div>
                <%--<div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>--%>
            </div>

        </form>
    </div>
    <!--公用导航模块end-->
    <!--公用表格模块-->
    <div style="overflow-x: auto;overflow-y: auto;">
    <div class="newPublicTable" style="width: 1800px;">
        <table>
            <colgroup>
                <c:if test="${type==2}">
                   <col width="150" >
                </c:if>
                <col  width="150" >
                <col   width="120">
                <col   width="120">
                <col  width="150" >
                <col  width="120" >
                <col   width="120">
                <col  width="120" >
                <col  width="120" >
                <col  width="120" >
                <col  width="120" >
                <col  width="120" >
                <col   width="120">
                <col   width="120">
                <col  width="120" >
                <col  width="120" >
                <c:if test="${type==2 && sessionUser.authMap.ACCOUNT_BILL_MGMT == true}">

                <col  width="200">
                </c:if>

    </colgroup>
            <tbody>
            <tr>
            <c:if test="${type==2}">

            <th class="newPublicTableCenter">客户名称</th>
            </c:if>
                <th class="newPublicTableCenter">交易对手公司名称</th>
                <th class="newPublicTableCenter">放款申请订单号</th>
                <th class="newPublicTableCenter">账单号</th>
                <th class="newPublicTableCenter">融资款发放金额（元）</th>
                <th class="newPublicTableCenter">申请转让日</th>
                <th class="newPublicTableCenter">应收账款到期日</th>
                <th class="newPublicTableCenter">保理管理费（元）</th>
                <th class="newPublicTableCenter">利息（元）</th>
                <th class="newPublicTableCenter">宽限利息（元）</th>
                <th class="newPublicTableCenter">逾期利息（元）</th>
                <th class="newPublicTableCenter">待还款（元）</th>
                <th class="newPublicTableCenter">已还款（元）</th>
                <th class="newPublicTableCenter">是否结清</th>
                <th class="newPublicTableCenter">是否到期</th>
                <th class="newPublicTableCenter">是否超宽限期</th>
                <c:if test="${type==2 && sessionUser.authMap.ACCOUNT_BILL_MGMT == true}">

                <th class="newPublicTableCenter">操作</th>
                </c:if>
            </tr>
            </tbody>
            <tbody id="list"></tbody>
        </table>
        <c:if test="${sessionUser.authMap.ACCOUNT_BILL_REPAY == true}">
            <div ><input style="margin-left: 0px;margin-top: 10px;" type="button" class="newPublicBtn" onclick='toRepayBalance()' value="还款"></div>
        </c:if>
        <div id="projectPageBar" class="pageBar"></div>
    </div>
    </div>
    <!--公用表格模块end-->
</div>

<!--单据复核列表end-->

<div class="publicContainerShow batchRecheckShowContainer creditApplyContainer" style="display:none;overflow: hidden;">
    <div class="remarksContainer newRemarksContainer">
        <form id="applyBalanceForm" name="applyBalanceForm">
            <input id="subjectClaimsOrderIds" type="hidden" name="subjectClaimsOrderIds">
            <div class="configOil">
                <span>实际用款申请金额:</span>
                <span>
                    <input id="applyBalance" class="applyBalance" name="applyBalance" type="number" value=""/>
                </span>
            </div>
            <div style="clear: both;"></div>
            <div>
                <span class="saveConfig" onclick="saveCredisApply();">保存</span>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<script type="text/javascript">

    var repayStatusMap = {
        3:"是",
        1:"否",
        2:"否",
      '-1':"否"
    };
    //是否到期
    var dueStatusMap = {
        0 :"否",
        1 :"是",
        2:"是",
    };
    //是否超宽限期0.未到期 1已到期，在宽限期内 2 已超宽限期
    var graceStatusMap = {
        0 :"否",
        1 :"否",
        2:"是",
    };
    laydate.render({elem: '#beginTime',type: 'date',theme: 'molv'});
    laydate.render({elem: '#overTime',type: 'date',theme: 'molv'});
    var totalPages;
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        //回显条件
        Page.initFormData(document.searchForm);
        ajaxPost("accountBill/listPage.json", P.formData(document.searchForm), function (resp) {
            parseInvoiceObjToHtml(resp);
        });
    });

    //搜索项目列表
    function searchPage() {
        if (document.searchForm) {
            var formData = P.formData(document.searchForm);
            ajaxPost("accountBill/listPage.json", formData, function (resp) {
                parseInvoiceObjToHtml(resp);
            });
        }
        return false;
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var dataTableId = "list";
        var dataTablePageBarId = "projectPageBar";

        var html = "";

        var responseData = resp.data;
        if (responseData.records.length <= 0) {
            if(${type==2}) {
                html = "<tr><td colspan='17' align='center'>暂无数据！</td></tr>";
            }else{
                html = "<tr><td colspan='16' align='center'>暂无数据！</td></tr>";
            }
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            var repayStatus = repayStatusMap[item.repayStatus];
            var dueStatus = dueStatusMap[item.dueStatus];
            var graceStatus = graceStatusMap[item.dueStatus];
            html += '<tr>';
            // html+=  '<td class="newPublicTableCenter" ><input type="hidden" class="subjectClaimsOrderId" value="'+item.id+'"/></td>';
            <c:if test="${type==2}">

            html += '<td class="newPublicTableCenter" title="' + item.companyName + '" >' + item.companyName + '</td>';
            </c:if>
            html += '<td class="newPublicTableCenter" title="' + item.counterpartyName + '" >' + item.counterpartyName + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.creditApplyNo + '" >' + item.creditApplyNo + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.billNo + '" >' + item.billNo + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.billBalance + '" >' + PF.formatMoney(item.billBalance) + '</td>';
            html += '<td class="newPublicTableCenter" title="' +  P.long2Date(item.applyTransferDate)+ '" >' + P.long2Date(item.applyTransferDate) + '</td>';
            html += '<td class="newPublicTableCenter" title="' +  P.long2Date(item.repayDate) + '" >' + P.long2Date(item.repayDate) + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.manageBalance + '" >' + PF.formatMoney(item.manageBalance) +  '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.interestRateBalance + '" >' + PF.formatMoney(item.interestRateBalance?item.interestRateBalance:0) +  '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.graceRateBalance + '" >' + PF.formatMoney(item.graceRateBalance?item.graceRateBalance:0) +  '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.overdueRateBalance + '" >' + PF.formatMoney(item.overdueRateBalance?item.overdueRateBalance:0) +  '</td>';
            html += '<td class="newPublicTableCenter" title="' + PF.formatMoney(parseFloat(item.billBalance)-parseFloat(item.repayBalance)) + '" >' + PF.formatMoney(parseFloat(item.billBalance)-parseFloat(item.repayBalance)) +  '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.repayBalance + '" >' + PF.formatMoney(item.repayBalance) +  '</td>';
            html += '<td class="newPublicTableCenter" title="' + repayStatus + '" >' + repayStatus + '</td>';
            html += '<td class="newPublicTableCenter" title="' + dueStatus + '" >' + dueStatus + '</td>';
            html += '<td class="newPublicTableCenter" title="' + graceStatus + '" >' + graceStatus + '</td>';
            if(${type==2 && sessionUser.authMap.ACCOUNT_BILL_MGMT == true}){
                html += "<td  class=\"newPublicTableCenter\" ><ul class='new_icon_pic'>";
                // html += "<li onclick='toDetail(" + item.id + ")'><span>账单</span></li>";
                if (item.repayStatus != 3) {
                    html += "<li onclick='notify(" + item.id + ")'><img src='newassets/imgs/icon_messages_01.png'><span style='color: #ff5176;'>提醒</span></li>";
                }
                html += "</ul></td>";
            }else{
                // html += "<li onclick='toDetail(" + item.id + ")'><span>详情</span></li>";
            }
            html += "</tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.searchForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }
    //查看账单详情
    function toDetail(accountBillId) {
        Page.clickBtnToPage("accountBillDetail.html?accountBillId="+accountBillId,"查看账单详情");
        <%--var returnUrl = "${returnUrl}";--%>
        <%--ajaxGet("accountBillDetail.html?accountBillId="+accountBillId+"&returnUrl="+returnUrl, function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
    }
    //还款页面
    function toRepayBalance(accountBillId) {
        Page.clickBtnToPage("repayBill.html?accountBillId="+accountBillId+"&type=2","还款");
        // ajaxGet("repayBill.html?accountBillId="+accountBillId+"&type=2", function (resultHtml) {
        //     $(window.mainContainer).html(resultHtml);
        // });
    }

    function toWaybillList(subjectClaimsOrderId) {
        Page.clickBtnToPage("waybillList.html?subjectClaimsOrderId="+subjectClaimsOrderId,"查看运单列表");
        <%--var returnUrl = "${returnUrl}";--%>
        <%--ajaxGet("waybillList.html?subjectClaimsOrderId="+subjectClaimsOrderId+"&returnUrl="+returnUrl, function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
    }
    //提醒功能
    function notify(accountBillId) {
        var formData = {
            'accountBillId':accountBillId
        }
        ajaxPost("accountBill/notify.json", formData, function (resp) {
            if (resp.success) {
                selfAlert("提醒成功!",function () {
                    layer.closeAll();
                    $("#status").val(5)
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }
    /**
     * 导出表格
     */
    function exportExcel(){
        var form=$("#searchForm").serialize();
        window.open("accountBill/exportExcel.html?"+form);
    }
</script>
