<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统配置管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil{margin-top: 25px;}
    .configOil span{margin-left: 30px;}
    .configOil span:first-child{margin-left: 0px;font-size: 14px;}
    .saveProgram{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>我的额度</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>计划列表</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain programMgmtForm">
        <form action="program/page.json" name="programForm" onsubmit="return searchScfProgram();" autocomplete="off">
            <input type="hidden" id="page" name="page" value="1">
            <input type="hidden" id="projectId" name="projectId" value="${projectId}">
            <input id="programNameSearch" class="newPublicInput"  name="programName"  type="text"  placeholder="请输入计划名称" />
            <div class="newPublicCustomSearchBtn" onclick="searchScfProgram()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <div class="newPublicCustomResetBtn" onclick="document.programForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>
            <div class="publicDetailsModuleReturnBtn return">返回</div>
        </form>
    </div>
    <!--公用导航模块end-->
    <!--公用表格模块-->
    <div class="newPublicTable">
        <table>
            <colgroup>
                <col >
                <col >
                <col >
                <col >
            </colgroup>
            <thead>
            <tr role="row">
                <th>计划名称</th>
                <th>货物名称</th>
                <th>出发地</th>
                <th>目的地</th>
                <th width="130px">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <tbody id="programList"></tbody>
        </table>
        <div id="programPageBar" class="pageBar"></div>
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
        Page.showPageTitleBindReturnUrl();
        // window.bindBackBtn("projectMgmt.html");
        //回显条件
        Page.initFormData(document.searchForm);
        ajaxPost("program/page.json", P.formData(document.programForm), function (resp) {
            parseInvoiceObjToHtml(resp);
        });
    });

    //搜索项目列表
    function searchScfProgram() {
        // var programValidate = PF.programValidate("keywords", "programName");
        if (document.programForm) {
            var formData = P.formData(document.programForm);
            ajaxPost("program/page.json", formData, function (resp) {
                parseInvoiceObjToHtml(resp);
            });
        }
        return false;
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var dataTableId = "programList";
        var dataTablePageBarId = "programPageBar";

        var html = "";
        var responseData = resp.data;
        if (responseData.records.length <= 0) {
            html = "<tr><td colspan='5' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            // 货物名称	运费单价	货值单价
            html += '<td title="' + item.programName + '" >' + item.programName + '</td>';
            html += '<td title="' + item.modelType + '" >' + item.modelType + '</td>';
            html += '<td title="' + item.departureCity + '" >' + item.departureCity + '</td>';
            html += '<td title="' + item.arrivalCity + '" >' + item.arrivalCity + '</td>';

            var json=JSON.stringify(item);
            html += "<td><ul class='new_icon_pic'><li onclick='toProgramDetail(" + item.projectId +","+ item.id + ")'><img src='assets/imgs/operate/icon_view.png'/><span>查看</span></li>";
            html += "</ul></td></tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.programForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }

    /**
     * 跳转到项目-计划详情
     */
    function toProgramDetail(projectId,programId){
        Page.clickBtnToPage("program/programDetail.html?projectId="+projectId+"&programId="+programId,"查看计划详情");
    }
</script>
