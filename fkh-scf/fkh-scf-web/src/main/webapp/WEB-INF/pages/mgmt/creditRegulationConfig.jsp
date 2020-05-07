<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用信规则配置</title>
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
        text-align: right;
        width: 160px;
    }
    #scfUserConfigTable img{position: relative;top:5px;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>系统管理</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>用信规则配置</a>--%>
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
            <c:when test="${sessionUser.authMap.CREDIT_REGULATION_LIST == true}">
                <table id="scfUserConfigTable" style="line-height: 30px;margin-top: 15px;">
                    <tbody>
                    <tr>
                        <td>
                            <i>是否验证实际承运人：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 TRANSPORTER_CREDIT_INVESTIGATION_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="TRANSPORTER_CREDIT_INVESTIGATION_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i>是否验证实际承运车辆：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 TRANSPORTER_VEHICLE_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="TRANSPORTER_VEHICLE_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i>是否验证运单发货方：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 WAYBILL_SEND_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="WAYBILL_SEND_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i>是否验证运单收货方：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 WAYBILL_CONSIGNEE_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="WAYBILL_CONSIGNEE_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i>是否验证运单起止点：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 WAYBILL_LOCATION_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="WAYBILL_LOCATION_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i>是否验证运单运输轨迹：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 WAYBILL_TRACK_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="WAYBILL_TRACK_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i>是否验证运单时间节点：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 WAYBILL_TIME_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="WAYBILL_TIME_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i>是否验证运单单据数据：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 WAYBILL_DATA_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="WAYBILL_DATA_CHECK" type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i>是否验证运单合同：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 WAYBILL_CONTRACT_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="WAYBILL_CONTRACT_CHECK"  type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <i>是否验证运价信息：</i>
                        </td>
                        <td>
                            <img class="icon_check_picture1 WAYBILL_PRICE_CHECK" onclick="changeCheckClick(this)" src="newassets/imgs/icon_no.png">
                            <input id="WAYBILL_PRICE_CHECK"  type="hidden" class="nameInput" style="width: 200px;">
                        </td>
                    </tr>
                    </tbody>
                </table>

                <c:if test="${sessionUser.authMap.CREDIT_REGULATION_EDIT == true}">
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
        ajaxGet("scfUserConfig/listConfig.json?configKey=CREDIT_REGULATION_CHECK_LIST",function (resp) {
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
        }else if(val==0){
            $(obj).attr("src","newassets/imgs/icon_yes.png");
            $(curnode).val(1);
        }
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var responseData = resp.data;
        if (responseData.length > 0) {
            $.each(responseData, function (index, item) {
                if (item.configStatus == 1) {//开启
                    $("." + item.configValue).attr("src","newassets/imgs/icon_yes.png");
                } else {//关闭
                    $("." + item.configValue).attr("src","newassets/imgs/icon_no.png");
                }
                $("#" + item.configValue).val(item.configStatus);
                $("#" + item.configValue).attr("data-id",item.id == null ? 0 : item.id);
                $("#" + item.configValue).attr("data-configId",item.configId);
            });
        }
    }

    //保存
    function saveOrUpdate() {
        selfConfirm("你确定要保存吗!",function () {
            var data = {};
            var idAndConfigIdAndConfigStatus = "";
            $("#scfUserConfigTable input").each(function (i, item) {
                idAndConfigIdAndConfigStatus += $(item).attr("data-id") + "-" + $(item).attr("data-configId") + "-" + $(item).val() + "-0" + ",";
            });
            idAndConfigIdAndConfigStatus = idAndConfigIdAndConfigStatus.substring(0, idAndConfigIdAndConfigStatus.length - 1);
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
