<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>


<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>审核记录</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<style>--%>
<%--    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}--%>
<%--    .configOil{margin-top: 25px;}--%>
<%--    .configOil span{margin-left: 30px;}--%>
<%--    .configOil span:first-child{margin-left: 0px;font-size: 14px;}--%>
<%--    .saveConfig{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}--%>
<%--</style>--%>
<%--<!--单据复核列表-->--%>
<%--<!--顶部导航-->--%>
<%--<div class="publicHeaderNav">--%>
<%--&lt;%&ndash;    <ul>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <li>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <a>用信申请管理</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <img src="newassets/imgs/icon_new_arrow_nav.png">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <a id="headerName">审核记录</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </li>&ndash;%&gt;--%>
<%--&lt;%&ndash;    </ul>&ndash;%&gt;--%>
<%--</div>--%>

<%--<!--顶部导航 end-->--%>
<%--<div class="clear"></div>--%>
<%--<!--公用导航模块-->--%>
<%--<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">--%>
<%--    <div class="publicNavMain projectMgmtForm">--%>
<%--        <form action="subjectClaimsOrder/listPage.json" name="searchForm"  autocomplete="off">--%>
<%--            &lt;%&ndash;<input id="creditApplyNo" name="creditApplyNo" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入订单号" style="width: 10%;">&ndash;%&gt;--%>
<%--            <select id="status" name="status" class="newPublicInput publicSwitchPlanProject" style="width: 10%;">--%>
<%--                <option value="-1">请选择审核状态</option>--%>
<%--                <option value="1">通过</option>--%>
<%--                <option value="2">不通过</option>--%>
<%--            </select>--%>
<%--            <input type="hidden" id="pageNo" name="pageNo" value="1">--%>
<%--            <div class="newPublicCustomSearchBtn" onclick="searchPage()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>--%>
<%--        &lt;%&ndash;<div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>&ndash;%&gt;--%>
<%--        </form>--%>
<%--    </div>--%>
<%--    <!--公用导航模块end-->--%>
<%--    <!--公用表格模块-->--%>
<%--    <div class="newPublicTable">--%>
<%--        <table>--%>
<%--            <colgroup>--%>
<%--                <col >--%>
<%--                <col >--%>
<%--                <col>--%>
<%--            </colgroup>--%>
<%--            <tbody>--%>
<%--            <tr>--%>
<%--                <th class="newPublicTableCenter">状态</th>--%>
<%--                <th class="newPublicTableCenter">审核意见</th>--%>
<%--                <th class="newPublicTableCenter">操作</th>--%>
<%--            </tr>--%>
<%--            </tbody>--%>
<%--            <tbody id="projectList"></tbody>--%>
<%--        </table>--%>
<%--        <div id="projectPageBar" class="pageBar"></div>--%>
<%--    </div>--%>
<%--    <!--公用表格模块end-->--%>
<%--</div>--%>

<%--<!--单据复核列表end-->--%>

<%--<div class="publicContainerShow batchRecheckShowContainer creditApplyContainer" style="display:none;overflow: hidden;">--%>
<%--    <div class="remarksContainer newRemarksContainer">--%>
<%--        <form id="applyBalanceForm" name="applyBalanceForm">--%>
<%--            <input id="subjectClaimsOrderIds" type="hidden" name="subjectClaimsOrderIds">--%>
<%--            <div class="configOil">--%>
<%--                <span>实际用信申请金额:</span>--%>
<%--                <span>--%>
<%--                    <input id="applyBalance" class="applyBalance" name="applyBalance" type="number" value=""/>--%>
<%--                </span>--%>
<%--            </div>--%>
<%--            <div style="clear: both;"></div>--%>
<%--            <div>--%>
<%--                <span class="saveConfig" onclick="saveCredisApply();">保存</span>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%--<script type="text/javascript">--%>

<%--    var statusMap = {--%>
<%--        1:"运营审核中",--%>
<%--        2:"风控审核中",--%>
<%--        3:"管理审核中",--%>
<%--        4:"待放款",--%>
<%--        5:"已放款",--%>
<%--        6:"未还款",--%>
<%--        7:"已还款"--%>
<%--    }--%>
<%--    //页面类型说明--%>
<%--    var typeMap = {--%>
<%--        //客户端--%>
<%--        1:"审核中",--%>
<%--        2:"已放款",--%>
<%--        3:"历史订单",--%>
<%--        //运营端--%>
<%--        4:"用信申请审核",//运营审核--%>
<%--        5:"用信申请审核",//风控审核--%>
<%--        6:"用信申请审核",//管理层审核--%>
<%--        7:"用信申请审核",//财务放款--%>
<%--        8:"放款管理"//财务放款--%>

<%--    }--%>
<%--    var type = ${type};--%>
<%--    laydate.render({elem: '#startTime',type: 'datetime',theme: 'molv'});--%>
<%--    var totalPages;--%>
<%--    P.pushCurrentPage();--%>
<%--    var heightPage = document.documentElement.clientHeight;--%>
<%--    $("#heightPage").css("height", heightPage - 160 + "px");--%>
<%--    $(document).ready(function () {--%>
<%--        Page.showPageTitleBindReturnUrl();--%>

<%--        var headerName=$("#headerName");--%>
<%--        var statusDom=$("#status");--%>
<%--        headerName.text(typeMap[type]);--%>
<%--        var status = -1;--%>
<%--        if(type==1){--%>
<%--            //审核中--%>
<%--             status = -1;--%>
<%--        }else if(type==2){--%>
<%--            //已放款--%>
<%--            status = -2;--%>
<%--        }else if(type==3){--%>
<%--            //已还款 历史订单--%>
<%--            status = 7;--%>
<%--        }else if(type==4){--%>
<%--            //运营审核--%>
<%--            status = 1;--%>
<%--        }else if(type==5){--%>
<%--            //风控审核--%>
<%--            status = 2;--%>
<%--        }else if(type==6){--%>
<%--            //管理层审核--%>
<%--            status = 3;--%>
<%--        }else if(type==7){--%>
<%--            //财务审核-放款--%>
<%--            status = 4;--%>
<%--        }else if(type==8){--%>
<%--            //财务管理-已放款--%>
<%--            status = 5;--%>
<%--        }--%>

<%--       var formData =  P.formData(document.searchForm)--%>
<%--        formData.status=status;--%>
<%--        //回显条件--%>
<%--        ajaxPost("creditApply/listPage.json?status="+status, formData, function (resp) {--%>
<%--            parseInvoiceObjToHtml(resp);--%>
<%--        });--%>
<%--    });--%>

<%--    //搜索项目列表--%>
<%--    function searchPage(status) {--%>
<%--        if (document.searchForm) {--%>
<%--            var formData = P.formData(document.searchForm);--%>
<%--            ajaxPost("creditApply/listPage.json", formData, function (resp) {--%>
<%--                parseInvoiceObjToHtml(resp);--%>
<%--            });--%>
<%--        }--%>
<%--        return false;--%>
<%--    }--%>

<%--    //解析单据对象为html--%>
<%--    function parseInvoiceObjToHtml(resp) {--%>
<%--        var dataTableId = "projectList";--%>
<%--        var dataTablePageBarId = "projectPageBar";--%>

<%--        var html = "";--%>
<%--        var responseData = resp.data;--%>
<%--        if (responseData.records.length <= 0) {--%>
<%--            html = "<tr><td colspan='3' align='center'>暂无数据！</td></tr>";--%>
<%--            $("#" + dataTableId).html(html);--%>
<%--            $("#" + dataTablePageBarId).hide();--%>
<%--            return false;--%>
<%--        }--%>
<%--        $.each(responseData.records, function (index, item) {--%>
<%--            var status = statusMap[item.status];--%>
<%--            html += '<tr>';--%>
<%--            if(type<4){--%>
<%--                html += '<td class="newPublicTableCenter" title="' + item.status + '" >' + status + '</td>';--%>
<%--            }--%>
<%--            // html+=  '<td class="newPublicTableCenter" ><input type="hidden" class="subjectClaimsOrderId" value="'+item.id+'"/></td>';--%>

<%--            html += '<td class="newPublicTableCenter" title="' + item.creditApplyNo + '" >' + item.creditApplyNo + '</td>';--%>
<%--           if(type==8){--%>
<%--               html += '<td class="newPublicTableCenter" title="' + item.applyBalance + '" >' + PF.formatMoney(item.applyBalance) + '</td>';--%>
<%--           }--%>
<%--            html += '<td class="newPublicTableCenter" title="' + item.applyBalance + '" >' + PF.formatMoney(item.applyBalance) + '</td>';--%>
<%--            html += '<td class="newPublicTableCenter" title="' + item.canApplyBalance + '" >' + PF.formatMoney(item.canApplyBalance) + '</td>';--%>
<%--            if(type==8){--%>
<%--                html += '<td class="newPublicTableCenter" title="' + item.updateTime + '" >' + P.long2Datetime(item.updateTime) +'</td>';--%>
<%--            }else{--%>
<%--                html += '<td class="newPublicTableCenter" title="' + item.waybillCount + '" >' +'<a href="#" onclick="toWaybillList('+item.id+')" class="tabs-close"><u>'+item.waybillCount+'</u></a>'  + '</td>';--%>
<%--            }--%>
<%--            html += "<td  class=\"newPublicTableCenter\" ><ul class='new_icon_pic'>";--%>
<%--            if (type == 1) {--%>
<%--                html += "<li onclick='toSubjectClaims(" + item.id + "," + type + ")'><span>查看标的债权</span></li>";--%>
<%--                html += "<li onclick='toAttachment(" + item.id + ")'><span>附件</span></li>";--%>
<%--            } else if (type == 2 || type == 3) {--%>
<%--                //如果是2已放款或者3历史订单--%>
<%--                //TODO-LA: 2020/3/1: 账单单独管理，不确定是否删除--%>
<%--                html += "<li onclick='toBill(" + item.id + ")'><span>账单</span></li>";--%>
<%--                html += "<li onclick='toSubjectClaims(" + item.id + "," + type + ")'><span>查看标的债权</span></li>";--%>
<%--                html += "<li onclick='toAttachment(" + item.id + ")'><span>附件</span></li>";--%>
<%--            } else if (type == 8) {--%>
<%--                //如果是7  放款管理页面--%>
<%--                html += "<li onclick='toModify(" + item.id + ")'><span>修改</span></li>";--%>

<%--            }else if(type>=4){--%>
<%--                //如果>=4 运营端，审核页面--%>
<%--                html += "<li onclick='toReview(" + item.id+","+ type + ")'><span>审核</span></li>";--%>
<%--                html += "<li onclick='toSubjectClaims(" + item.id+","+ type + ")'><span>查看标的债权</span></li>";--%>
<%--                html += "<li onclick='toAttachment(" + item.id + ")'><span>附件</span></li>";--%>
<%--            }--%>

<%--            html += "</ul></td></tr>";--%>
<%--        });--%>
<%--        $("#" + dataTablePageBarId).show();--%>
<%--        $("#" + dataTableId).html(html);--%>
<%--        $("#publicTable1 tr:even").css("background", "#f2f2f2");--%>
<%--        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.searchForm, parseInvoiceObjToHtml);--%>
<%--        //记住总的页数--%>
<%--        totalPages = responseData.pages;--%>
<%--    }--%>
<%--    //查看债权订单--%>
<%--    function toSubjectClaims(creditApplyId) {--%>
<%--        Page--%>
<%--        ajaxGet("subjectClaimsOrder.html?creditApplyId="+creditApplyId+"&type="+type, function (resultHtml) {--%>
<%--            $(window.mainContainer).html(resultHtml);--%>
<%--        });--%>
<%--    }--%>
<%--    function toAttachment(creditApplyId) {--%>

<%--    }--%>
<%--    function toBill(creditApplyId) {--%>

<%--    }--%>
<%--    function toModify(creditApplyId) {--%>
<%--        var formData = {--%>
<%--            'creditApplyId':creditApplyId--%>
<%--        }--%>
<%--        ajaxPost("creditApply/loan.json", formData, function (resp) {--%>
<%--            if (resp.success) {--%>
<%--                selfAlert("放款成功!",function () {--%>
<%--                    layer.closeAll();--%>
<%--                    $("#status").val(5)--%>
<%--                    searchPage();--%>
<%--                });--%>
<%--            } else {--%>
<%--                selfAlert(resp.message);--%>
<%--            }--%>
<%--        });--%>
<%--    }--%>
<%--    function toReview(creditApplyId,type) {--%>
<%--        ajaxGet("reviewCreditApply.html?creditApplyId="+creditApplyId+"&type="+type, function (resultHtml) {--%>
<%--            $(window.mainContainer).html(resultHtml);--%>
<%--        });--%>
<%--        if(type==7){--%>
<%--            //财务审核--%>
<%--            ajaxGet("reviewCreditApply.html?creditApplyId="+creditApplyId+"&type="+type, function (resultHtml) {--%>
<%--                $(window.mainContainer).html(resultHtml);--%>
<%--            });--%>
<%--        }else{--%>
<%--            //TODO-LA: 2020/3/1  审核页面显示按type 按钮不同。4 5 6 7--%>
<%--            //其他审核--%>
<%--        }--%>
<%--    }--%>
<%--    function toWaybillList(creditApplyId) {--%>
<%--        var returnUrl = "${returnUrl}";--%>
<%--        ajaxGet("waybillList.html?creditApplyId="+creditApplyId+"&returnUrl="+returnUrl, function (resultHtml) {--%>
<%--            $(window.mainContainer).html(resultHtml);--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>
