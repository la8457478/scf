<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的额度</title>
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
<%--            <a>我的额度</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form action="project/page.json" name="searchForm" onsubmit="return searchProject();" autocomplete="off">
            <input type="hidden" id="page" name="page" value="1">
            <input id="counterpartyName" name="counterpartyName" type="text" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入交易对手名称" style="width: 20%;">
            <input id="projectNameSearch" class="newPublicInput"  name="projectName"  type="text"  placeholder="请输入项目名称" />
            <div class="newPublicCustomSearchBtn" onclick="searchProject()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>

            <div class="newPublicCustomResetBtn" onclick="document.searchForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>
        </form>
    </div>
    <!--公用导航模块end-->
    <p id="companyBalance" class="sumAmountColor">
    </p>

    <!--公用表格模块-->
    <div class="newPublicTable" style="margin-top: 0px;">
        <table>
            <colgroup>
                <col >
                <col >
                <col >
                <col >
                <col >
                <col >
                <col >
            </colgroup>
            <thead>
            <tr role="row">
                <th class="newPublicTableCenter">项目名称</th>
                <th class="newPublicTableCenter">交易对手</th>
                <th class="newPublicTableCenter">分项融资限额（元）</th>
                <th class="newPublicTableCenter">已用融资额度（元）</th>
                <th class="newPublicTableCenter">可提用融资余额（元）</th>
                <th class="newPublicTableCenter">未提用应收账款转让订单数</th>
                <th width="250px" class="newPublicTableCenter">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <tbody id="projectList"></tbody>
        </table>
        <div id="pageBar" class="pageBar"></div>
    </div>
    <!--公用表格模块end-->
</div>

</body>
</html>

<script type="text/javascript">
    var totalPages;
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        //回显条件
        Page.initFormData(document.searchForm);
        $("#companyBalance").html("总额度:"+PF.formatMoney(${companyContract.totalBalance})+"元,可用额度:"+ PF.formatMoney(${companyContract.remainingBalance})+"元")
        searchProject();
    });

    //搜索项目列表
    function searchProject() {
        if ("${sessionUser.authMap.PROJECT_LIST}" == "false") {
            $("#projectList").html("<tr><td colspan='7' align='center'>暂无权限！</td></tr>");
            $("#pageBar").hide();
            return false;
        }
        //刷新客户信息
        ajaxGet("companyContract/${companyContract.id}.json", function (resp) {
            if (resp.success) {
                $("#companyBalance").html("总额度:"+PF.formatMoney(resp.data.totalBalance)+"元,可用额度:"+ PF.formatMoney(resp.data.remainingBalance)+"元");
            }
        });

        var projectValidate = PF.projectValidate("keywords", "planName");
        if (projectValidate && document.searchForm) {
            var formData = P.formData(document.searchForm);
            ajaxPost("project/page.json", formData, function (resp) {
                parseInvoiceObjToHtml(resp);
            });
        }
        return false;
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var dataTableId = "projectList";
        var dataTablePageBarId = "pageBar";
        var html = "";
        var responseData = resp.data;
        if (responseData.records.length <= 0) {
            html = "<tr><td colspan='7' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }

        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            html += '<td class="newPublicTableCenter" title="' + item.projectName + '" >' + item.projectName + '</td>';
            html += '<td class="newPublicTableCenter"title="' + item.counterpartyName + '" >' + item.counterpartyName + '</td>';
            html += '<td class="newPublicTableCenter"title="' + item.subitemLimitBalance + '" >' + PF.formatMoney(item.subitemLimitBalance) + '</td>';
            html += '<td class="newPublicTableCenter"title="' + item.subitemUsedBalance + '" >' + PF.formatMoney(item.subitemUsedBalance)+ '</td>';
            html += '<td class="newPublicTableCenter"title="' + item.subitemRemainBalance + '" >' + PF.formatMoney(item.subitemRemainBalance) + '</td>';

            if ("${sessionUser.authMap.PROJECT_SUBJECT_CLAIMS_LIST}" == "true") {
                html += "<td class='newPublicTableCenter'><ul class='new_icon_pic'><li onclick='toSubjectClaimsOrderList(" + item.counterpartyId +","+ item.id +")'><img src='assets/imgs/operate/icon_view.png'/><span>查看("+item.subjectClaimsOrderCount+")</span></li>";
                html += "</ul></td>";
            }
            var json=JSON.stringify(item);

            html += "<td><ul class='new_icon_pic'>";
            if ("${sessionUser.authMap.PROJECT_PROGRAM_LIST}" == "true") {
                html += "<li onclick='toProjectDetail(" + item.id +")'><img src='assets/imgs/operate/icon_project.png'><span>项目详情</span></li>";
                html += "<li onclick='toProgramList(" + item.id + ")'><img src='assets/imgs/operate/icon_program.png'><span>计划</span></li>";
            }

            if ("${sessionUser.authMap.PROJECT_WAYBILL_LIST}" == "true") {
                html += "<li onclick='toWaybillList(" + item.id + ")'><img src='assets/imgs/operate/icon_no_user_waybill.png'><span>未转让运单</span></li>";
            }
            html += "</ul></td></tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.searchForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }

    /**
     * 跳转到项目详情
     */
    function toProjectDetail(projectId){
        Page.clickBtnToPage("project/projectDetail.html?projectId="+projectId,"查看项目详情");
    }

    /**
     * 跳转到项目-计划列表
     */
    function toProgramList(projectId){
        Page.clickBtnToPage("program/programList.html?projectId="+projectId,"计划列表");
    }

    /**
     * 跳转到项目-运单列表
     */
    function toWaybillList(projectId){
        P.setGlobalForm(1);
        Page.clickBtnToPage("waybillList.html?isTransferred=false&returnUrl=${url}&projectId="+projectId,"未转让运单列表");
    }

    /**
     * 跳转到-标的债券列表
     */
    function toSubjectClaimsOrderList(counterpartyId,projectId){
        Page.clickBtnToPage("subjectClaimsOrder.html?reviewStatus=0&counterpartyId="+counterpartyId+"&projectId="+projectId,"未提用应收账款转让订单列表");
        // Page.clickBtnToPage("subjectClaimsOrder.html?type=4&reviewStatus=0&counterpartyId="+counterpartyId,"未提用应收账款转让订单列表");
        <%--var url='${url}';--%>
        <%--P.setReturnUrlForm("subjectClaimsOrder",url)--%>
        <%--containerShow("subjectClaimsOrder.html?counterpartyId="+counterpartyId+"&returnUrl="+url);--%>
    }
</script>
