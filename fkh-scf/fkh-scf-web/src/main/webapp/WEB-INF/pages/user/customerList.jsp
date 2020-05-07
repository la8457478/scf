<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>顾客管理</title>
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
<%--            <a>顾客管理</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form action="authRole/listPage.json" name="reviewInvoiceForm" onsubmit="return searchReviewInvoices();" autocomplete="off">
            <input id="roleName" name="roleName" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入顾客名称" style="width: 20%;">
            <input type="hidden" id="pageNo" name="pageNo" value="1">
            <input type="hidden" id="userType" name="userType" value="3">
            <div class="newPublicCustomSearchBtn" onclick="searchReviewInvoices()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>
            <div class="newPublicCustomGoodsBtn" onclick="editCustomer('','','','')"><img src="newassets/imgs/icon_new_add.png"><a>新增顾客</a></div>
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
                <th style="text-align:left;">姓名</th>
                <th style="text-align:center;">手机号码</th>
                <th style="text-align:center;">登录帐号</th>
                <th style="text-align:center;width:150px;">注册时间</th>
                <th>操作</th>
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
            <input id="selId" type="hidden" name="id">
            <input type="hidden" name="userType" value="3">
            <div class="configOil">
                <span>用户姓名:</span>
                <span>
                    <input id="selNickName" class="designatedConsumption" name="nickName" type="text" value=""/>
                </span>
            </div>
            <div class="configOil">
                <span>手机号码:</span>
                <span>
                    <input id="selUserMobileNo" class="designatedConsumption" name="userMobileNo" type="text" maxlength="11"  value=""/>
                </span>
            </div>
            <div class="configOil">
                <span>登录帐号:</span>
                <span>
                    <input id="selUsername" class="designatedConsumption" name="username" type="text" maxlength="11" value=""/>
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
        ajaxPost("scfUser/listPage.json", P.formData(document.reviewInvoiceForm), function (resp) {
            parseInvoiceObjToHtml(resp);
        });
    });

    //搜索客户列表
    function searchReviewInvoices() {
        if (document.reviewInvoiceForm) {
            var formData = P.formData(document.reviewInvoiceForm);
            ajaxPost("scfUser/listPage.json", formData, function (resp) {
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
            html += '<td title="' + item.nickName + '" >' + item.nickName + '</td>';
            html += '<td title="' + item.userMobileNo + '" >' + item.userMobileNo + '</td>';
            html += '<td title="' + item.username + '" >' + item.username + '</td>';
            html += '<td style="width:120px;text-align: center;">'+P.long2Datetime(item.createTime)+'</td>';
            html += "<td><ul class='new_icon_pic'><li onclick='editCustomer(" + item.id + ",\"" + item.nickName + "\",\"" + item.userMobileNo + "\",\"" + item.username + "\")'><img src='assets/imgs/operate/icon_edit.png'/><span>编辑</span></li>";
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
    function editCustomer(userId, nickName, userMobileNo, username) {
        var objs = $(".configOilCompany");
        var title = userId == '' ? "新增菜单" : "编辑菜单";
        layer.open({
                       type: 1,
                       title: [title, 'font-size:16px;color:white;background-color:#0071db;'],
                       area: ['400px', '300px'],
                       closeBtn: 4,
                       content: objs
                   });
        $("#selId").val(userId);
        $("#selNickName").val(nickName);
        $("#selUserMobileNo").val(userMobileNo);
        $("#selUsername").val(username);
    }

    //删除
    function deleteRole(funcId) {
        selfConfirm("你确定要删除该菜单吗!",function () {
            var params = {"funcId" : funcId};
            ajaxPost("scfUser/delete.json", params, function (resp) {
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
        if (P.isNullOrEmptyString(formData.nickName)) {
            selfAlert("请填写客户姓名!");
            return false;
        }
        if (P.isNullOrEmptyString(formData.userMobileNo) || formData.userMobileNo.length != 11) {
            selfAlert("请填写11位的手机号码!");
            return false;
        }
        if (P.isNullOrEmptyString(formData.username)) {
            selfAlert("请填写登录账号!");
            return false;
        }
        //TODO-LA: 2020/3/9 默认使用888888
        formData.userPassword = MD5("888888");
        ajaxPost("scfUser/saveOrUpdate.json", formData, function (resp) {
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
