<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
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
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>管理菜单</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>菜单管理</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form action="authRole/listPage.json" name="reviewInvoiceForm" onsubmit="return searchReviewInvoices();" autocomplete="off">
            <input id="roleName" name="roleName" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入菜单名称" style="width: 20%;">
            <input type="hidden" id="pageNo" name="pageNo" value="1">
            <input type="hidden" id="funcType" name="funcType" value="1">
            <div class="newPublicCustomSearchBtn" onclick="searchReviewInvoices()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>
            <div class="newPublicCustomGoodsBtn" onclick="editRole('','')"><img src="newassets/imgs/icon_new_add.png"><a>新增菜单</a></div>
        </form>
    </div>
    <!--公用导航模块end-->
    <!--公用表格模块-->
    <div class="newPublicTable">
        <table>
            <colgroup>
                <col >
                <col >
            </colgroup>
            <tbody>
            <tr>
                <th class="newPublicTableCenter">菜单名称</th>
                <th class="newPublicTableCenter">地址</th>
                <th class="newPublicTableCenter">操作</th>
            </tr>
            </tbody>
            <tbody id="projectList"></tbody>
        </table>
        <div id="projectPageBar" class="pageBar"></div>
    </div>
    <!--公用表格模块end-->
</div>

<!--单据复核列表end-->

<!--编辑菜单-->
<div class="publicContainerShow batchRecheckShowContainer configOilCompany" style="display:none;overflow: hidden;">
    <div class="remarksContainer newRemarksContainer">
        <form id="funcForm" name="funcForm">
            <input id="selFuncId" type="hidden" name="id">
            <input type="hidden" name="funcType" value="1">
            <div class="configOil">
                <span>菜单名称:</span>
                <span>
                    <input id="selFuncName" class="designatedConsumption" name="funcName" type="text" value=""/>
                </span>
            </div>
            <div class="configOil">
                <span>菜单地址:</span>
                <span>
                    <input id="selFuncUrl" class="designatedConsumption" name="funcUrl" type="text" value=""/>
                </span>
            </div>
            <div style="clear: both;"></div>
            <div>
                <span class="saveConfig" onclick="saveOrUpdate();">保存</span>
            </div>
        </form>
    </div>
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
        //回显条件
        ajaxPost("authFunction/listPage.json", P.formData(document.reviewInvoiceForm), function (resp) {
            parseInvoiceObjToHtml(resp);
        });
    });

    //搜索项目列表
    function searchReviewInvoices() {
        var projectValidate = PF.projectValidate("keywords", "planName");
        if (projectValidate && document.reviewInvoiceForm) {
            var formData = P.formData(document.reviewInvoiceForm);
            ajaxPost("authFunction/listPage.json", formData, function (resp) {
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
            html = "<tr><td colspan='3' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            html += '<td title="' + item.funcName + '" >' + item.funcName + '</td>';
            html += '<td title="' + item.funcUrl + '" >' + item.funcUrl + '</td>';
            html += "<td><ul class='new_icon_pic'><li onclick='editRole(" + item.id + ",\"" + item.funcName
                + "\")'><img src='assets/imgs/operate/icon_edit.png'/><span>编辑</span></li>";
            html += "<li onclick='deleteRole(" + item.id + ")'><img src='assets/imgs/operate/icon_delete.png'/><span>删除</span></li>";
            html += "</ul></td></tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.reviewInvoiceForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }

    //新增或修改弹窗
    function editRole(roleId, funcName) {
        var objs = $(".configOilCompany");
        var title = roleId == '' ? "新增菜单" : "编辑菜单";
        layer.open({
                       type: 1,
                       title: [title, 'font-size:16px;color:white;background-color:#0071db;'],
                       area: ['400px', '200px'],
                       closeBtn: 4,
                       content: objs
                   });
        $("#selFuncId").val(roleId);
        $("#selFuncName").val(funcName);
    }

    //删除
    function deleteRole(funcId) {
        selfConfirm("你确定要删除该菜单吗!",function () {
            var params = {"funcId" : funcId};
            ajaxPost("authFunction/delete.json", params, function (resp) {
                if (resp.success) {
                    selfAlert("保存成功!",function () {
                        layer.closeAll();
                        $("#pageNo").val($("span.on").html());
                        searchReviewInvoices();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        });
    }

    //保存
    function saveOrUpdate() {
        var formData = P.formData(document.funcForm);
        if (P.isNullOrEmptyString(formData.funcName)) {
            selfAlert("请填写菜单名称!");
            return false;
        }
        ajaxPost("authFunction/saveOrUpdate.json", formData, function (resp) {
            if (resp.success) {
                selfAlert("保存成功!",function () {
                    layer.closeAll();
                    $("#pageNo").val($("span.on").html());
                    searchReviewInvoices();
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }
</script>
