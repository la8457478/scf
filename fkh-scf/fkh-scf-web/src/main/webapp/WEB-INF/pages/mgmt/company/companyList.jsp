<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>企业管理</title>
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
<%--            <a>企业管理</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form action="company/listPage.json" name="reviewInvoiceForm" onsubmit="return searchReviewInvoices();" autocomplete="off">
            <c:choose>
                <c:when test="${userType == 1}">
                    <input type="hidden" id="companyType" name="companyType" value="1">
                </c:when>
                <c:otherwise>
                    <input type="hidden" id="companyType" name="companyType" value="2">
                </c:otherwise>
            </c:choose>
            <input id="searchCompanyName" name="companyName" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入企业公司名称" style="width: 200px;">
            <%--<select id="searchCompanyType" name="companyType" class="newPublicInput publicSwitchPlanProject" style="width: 10%;">
                <option value="">全部企业</option>
                <option value="1">资金方</option>
                <option value="2">借款方</option>
            </select>--%>
            <input type="hidden" id="pageNo" name="pageNo" value="1">
            <div class="newPublicCustomSearchBtn" onclick="searchReviewInvoices()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <%--<div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>--%>
            <c:if test="${sessionUser.authMap.COMPANY_ADD == true}">
                <div class="newPublicCustomSearchBtn" onclick="turnToDetail('')"><img src="newassets/imgs/icon_new_add.png"><a>注册企业</a></div>
            </c:if>
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
                <col >
            </colgroup>
            <tbody>
            <tr>
                <th >企业名称</th>
                <th >负责人</th>
                <th >企业类型</th>
                <th >注册时间</th>
                <th >操作</th>
            </tr>
            </tbody>
            <tbody id="projectList"></tbody>
        </table>
        <div id="projectPageBar" class="pageBar"></div>
    </div>
    <!--公用表格模块end-->
</div>

<!--单据复核列表end-->

<!--编辑角色-->
<div class="publicContainerShow batchRecheckShowContainer configOilCompany" style="display:none;overflow: hidden;">
    <div class="remarksContainer newRemarksContainer">
        <form id="roleForm" name="roleForm">
            <input id="selRoleId" type="hidden" name="id">
            <div class="configOil">
                <span>角色名称:</span>
                <span>
                    <input id="selRoleName" class="designatedConsumption" name="roleName" type="text" value=""/>
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
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        //回显条件
        Page.initFormData(document.searchForm);
        searchReviewInvoices();
    });

    //搜索项目列表
    function searchReviewInvoices() {
        if ("${sessionUser.authMap.COMPANY_LIST}" == "false") {
            $("#projectList").html("<tr><td colspan='6' align='center'>暂无权限！</td></tr>");
            $("#projectPageBar").hide();
            return false;
        }

        var projectValidate = PF.projectValidate("keywords", "planName");
        if (projectValidate && document.reviewInvoiceForm) {
            var formData = P.formData(document.reviewInvoiceForm);
            ajaxPost("company/listPage.json", formData, function (resp) {
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
            html = "<tr><td colspan='5' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            html += '<td title="' + item.companyName + '" >' + item.companyName + '</td>';
            html += '<td title="' + item.ownerName + '" >' + item.ownerName + '</td>';
            html += '<td title="' + convertCompanyType(item.companyType) + '" >' + convertCompanyType(item.companyType) + '</td>';
            html += '<td title="' + P.long2Datetime(item.establishingTime) + '" >' + P.long2Datetime(item.establishingTime) + '</td>';
            html += "<td><ul class='new_icon_pic'>";
            <%--if ("${sessionUser.authMap.COMPANY_ADD}" == "true") {--%>
                <%--html += "<li onclick='turnToDetail(" + item.id + ")'><span>编辑</span></li>";--%>
                <%--html += "<li onclick='deleteRole(" + item.id + ")'><span>删除</span></li>";--%>
            <%--} else {--%>
                <%--html += "<li onclick='turnToDetail(" + item.id + ")'><span>查看</span></li>";--%>
            <%--}--%>
            html += "<li onclick='turnToDetail(" + item.id + ")'><img src='assets/imgs/operate/icon_view.png'/><span>查看</span></li>";
            html += "</ul></td></tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.reviewInvoiceForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }

    //跳转注册企业或者详情页面
    function turnToDetail(companyId) {
        var readOnly = P.isNullOrEmptyString(companyId) ? false : true;
        var title=readOnly?"查看企业详情":"注册企业";
        Page.clickBtnToPage("companyDetail/"+companyId+".html?readOnly=" + readOnly,title)
        // ajaxGet("companyDetail/"+companyId+".html?readOnly=" + readOnly, function (resp) {
        //     $("#publicRightContainer").html(resp);
        // });
    }

    //删除
    function deleteRole(companyId) {
        selfConfirm("你确定要删除该企业吗!",function () {
            ajaxPost("company/delete/"+companyId+".json", null, function (resp) {
                if (resp.success) {
                    selfAlert("操作成功!",function () {
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
        var formData = P.formData(document.roleForm);
        if (P.isNullOrEmptyString(formData.roleName)) {
            selfAlert("请填写角色名称!");
            return false;
        }
        ajaxPost("company/saveOrUpdate.json", formData, function (resp) {
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

    function convertCompanyType(companyType){
        if (companyType == 1) {
            return "资金方";
        } else {
            return "借款方";
        }
    }

    function convertCompanyStatus(companyStatus){
        if (companyStatus == 1) {
            return "有效";
        } else {
            return "无效";
        }
    }
</script>
