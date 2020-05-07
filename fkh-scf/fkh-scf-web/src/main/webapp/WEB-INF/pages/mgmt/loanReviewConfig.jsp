<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>放款审核配置</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil{margin-top: 25px;}
    .configOil span{margin-left: 30px;}
    .configOil span:first-child{margin-left: 0px;font-size: 14px;}
    .saveConfig{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
    #saveCreditRegulationConfig{
        width: 80px;
        height: 30px;
        background-color: #365ba9;
        color: #ffffff;
        border: 1px solid #365ba9!important;
        border-radius: 2px;
        text-align: center;
        cursor: pointer;
    }
    #scfUserConfigTable tr td:nth-child(2n - 1) {
        padding-left: 50px;
        text-align: left;
        width: 200px;
    }
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>系统管理</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>放款审核配置</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: #ffffff;overflow-y: auto;overflow-x: hidden;">
    <!--公用导航模块end-->
    <!--公用表格模块-->
    <div>

<c:choose>
    <c:when test="${sessionUser.authMap.LOAN_REVIEW_CONFIG_LIST == true}">
        <table id="scfUserConfigTable" style="line-height: 30px;margin-top: 15px;">
            <tbody>
            <tr>
                <td >
                    <i>运营审核抽查</i>
                </td>
                <td>
                    <img class="icon_check_picture1 LOAN_REVIEW_CONFIG_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                    <input id="LOAN_REVIEW_CONFIG_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                </td>
            </tr>
            <tr id="configValueTr" style="display: none">
                <td >
                    <i>审核核验比例</i>
                </td>
                <td>
                    <input id="configValue" type="text" class="nameInput" style="outline: none;width: 60px;height: 24px;">
                    <span>%</span>
                </td>
            </tr>
            </tbody>
        </table>
        <c:if test="${sessionUser.authMap.LOAN_REVIEW_CONFIG_EDIT == true}">
            <div style="padding-left: 50px;margin-top: 50px">
                <input onclick="saveOrUpdate();" type="button" id="saveCreditRegulationConfig" value="保存">
            </div>
        </c:if>
        </c:when>
        <c:otherwise>
            <span>暂无权限!</span>
        </c:otherwise>
    </c:choose>

    </div>
    <!--公用表格模块end-->
</div>

<!--单据复核列表end-->

</body>
</html>

<script type="text/javascript">
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        Page.showPageTitleBindReturnUrl();
        //回显条件
        ajaxGet("scfUserConfig/getConfig.json?configKey=LOAN_REVIEW_CONFIG_CHECK",function (resp) {
            parseInvoiceObjToHtml(resp);
        });
    });

    //切换开关
    function changeCheckClick(obj){
        var curnode=$(obj).siblings("input")[0];
        var val=$(curnode).val();
        if(val==1){
            $(obj).attr("src","newassets/imgs/icon_no.png");
            $(curnode).val(0);
            $("#configValueTr").hide();
        }else{
            $(obj).attr("src","newassets/imgs/icon_yes.png");
            $(curnode).val(1);
            $("#configValueTr").show();
        }
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var data = resp.data;
        if (data) {
            if (data.configStatus == 1) {//开启
                $("." + data.configKey).attr("src","newassets/imgs/icon_yes.png");
                $("#configValueTr").show();
            } else {//关闭
                $("." + data.configKey).attr("src","newassets/imgs/icon_no.png");
                $("#configValueTr").hide();
            }
            $("#" + data.configKey).val(data.configStatus);
            $("#" + data.configKey).attr("data-id",data.id == null ? 0 : data.id);
            $("#" + data.configKey).attr("data-configId",data.configId);
            $("#configValue").val(data.configValue);
        }
    }

    //保存
    function saveOrUpdate() {
        if (isNumberValueOutRange($("#configValue").val(), true, 1, 100)) {
            selfAlert("请填写1-100之间的整数审核核验比例!");
            return false;
        }
        selfConfirm("你确定要保存吗!",function () {
            var data = {};
            var idAndConfigIdAndConfigStatus = $("#LOAN_REVIEW_CONFIG_CHECK").attr("data-id") + "-" + $(
                "#LOAN_REVIEW_CONFIG_CHECK").attr("data-configId") + "-" + $("#LOAN_REVIEW_CONFIG_CHECK").val() + "-"
            + $("#configValue").val();
            data.idAndConfigIdAndConfigStatus = idAndConfigIdAndConfigStatus;
            ajaxPost("scfUserConfig/saveOrUpdate.json", data, function (resp) {
                if (resp.success) {
                    selfAlert("保存成功!",function () {
                        layer.closeAll();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        });

    }
</script>
