<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>还款管理</title>
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
<%--&lt;%&ndash;            <a>用信申请管理</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <img src="newassets/imgs/icon_new_arrow_nav.png">&ndash;%&gt;--%>
<%--            <a>还款管理</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form  id="searchForm" name="searchForm"  autocomplete="off">
            <input id="companyName" name="companyName" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入项目主体名称" style="width: 10%;">
            <input id="counterpartyName" name="counterpartyName" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入交易对手名称" style="width: 10%;">
            <input id="creditApplyNo" name="creditApplyNo" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入放款申请订单号" style="width: 10%;">
            <span>近期还款</span>
            <select id="repayDate" name="repayDate" class="newPublicInput publicSwitchPlanProject" style="width: 7%;">
                <option value="">全部还款计划</option>
                <option value="3">近3日还款</option>
                <option value="7">近7日还款</option>
                <option value="15">近15日还款</option>
            </select>
            <span>是否结清</span>
            <select id="repayStatus" name="repayStatus" class="newPublicInput publicSwitchPlanProject" style="width: 5%;">
                <option value="">全部</option>
                <option value="3">结清</option>
                <option value="0">未结清</option>
            </select>
            <span>是否到期</span>
            <select id="dueStatus" name="dueStatus" class="newPublicInput publicSwitchPlanProject" style="width: 5%;">
                <option value="">全部</option>
                <option value="1">到期</option>
                <option value="0">未到期</option>
            </select>
            <div class="newPublicCustomSearchBtn" onclick="searchPage()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <div class="newPublicCustomSearchBtn" onclick="exportExcel()"><a>导出明细</a></div>
            <%--<div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>--%>
        </form>
    </div>
    <!--公用导航模块end-->
    <!--公用表格模块-->
    <div class="newPublicTable">
        <table>
            <colgroup>
                <col  width="70">
                <col  width="150">
                <col  width="150">
                <col  width="150">
                <col  width="150">
                <col  width="150">
                <col  >
                <col  >
            </colgroup>
            <tbody>
            <tr>
                <th class="newPublicTableCenter">客户名称</th>
                <th class="newPublicTableCenter">交易对手名称</th>
                <th class="newPublicTableCenter">用款申请订单号</th>
                <th class="newPublicTableCenter">放款金额（元）</th>
                <th class="newPublicTableCenter">还款期限</th>
                <th class="newPublicTableCenter">操作</th>
            </tr>
            </tbody>
            <tbody id="list"></tbody>
        </table>

        <c:if test="${sessionUser.authMap.ACCOUNT_BILL_REPAY == true}">
            <div ><input type="button" class="newPublicBtn" onclick='toRepayBalance()' value="还款"></div>
        </c:if>
        <div id="projectPageBar" class="pageBar"></div>
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
        "-1":"还款拒绝",
        1:"未还款",
        2:"还款中",
        3:"已还款"
    };
    var dueStatusMap = {
        0 :"未到期",
        1 :"已到期",
    };
    laydate.render({elem: '#startTime',type: 'datetime',theme: 'molv'});
    var totalPages;
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        //回显条件
        Page.initFormData(document.searchForm);
        searchPage();
    });

    //搜索项目列表
    function searchPage() {

        if (${type==1}) {
            if ("${sessionUser.authMap.REPAY_LIST}" == "false") {
                $("#list").html("<tr><td colspan='10' align='center'>暂无权限！</td></tr>");
                $("#projectPageBar").hide();
                return false;
            }
        } else if (${type==2}) {
            if ("${sessionUser.authMap.ACCOUNT_BILL_LIST}" == "false") {
                $("#list").html("<tr><td colspan='10' align='center'>暂无权限！</td></tr>");
                $("#projectPageBar").hide();
                return false;
            }
        } else if (${type==3}) {
            if ("${sessionUser.authMap.ACCOUNT_BILL_LIST}" == "false") {
                $("#list").html("<tr><td colspan='10' align='center'>暂无权限！</td></tr>");
                $("#projectPageBar").hide();
                return false;
            }
        }

        if (document.searchForm) {
            var formData = P.formData(document.searchForm);
            ajaxPost("accountBill/listRepayPage.json", formData, function (resp) {
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
            html = "<tr><td colspan='10' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            var repayStatus = repayStatusMap[item.repayStatus];
            var dueStatus = dueStatusMap[item.dueStatus];
            html += '<tr>';
            // html+=  '<td class="newPublicTableCenter" ><input type="hidden" class="subjectClaimsOrderId" value="'+item.id+'"/></td>';
            html += '<td class="newPublicTableCenter" title="' + item.repayStatus + '" >' + repayStatus + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.dueStatus + '" >' + dueStatus + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.companyName + '" >' + item.companyName + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.counterpartyName + '" >' + item.counterpartyName + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.creditApplyNo + '" >' + item.creditApplyNo + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.billBalance + '" >' + PF.formatMoney(item.billBalance) + '</td>';
            html += '<td class="newPublicTableCenter" title="'+P.long2Datetime(item.repayDate)+'" class="newPublicTableCenter"><span class="time" >'+P.long2Datetime(item.repayDate)+'</span></td>';
            html += "<td  class=\"newPublicTableCenter\" ><ul class='new_icon_pic'>";
                //如果是账号或者历史订单
            if(${type==1}){
                if ("${sessionUser.authMap.REPAY_LIST}" == "true") {
                    //还款管理
                    if(item.repayStatus==2){
                        html += "<li onclick='reviewRepayBill(" + item.id +","+"true"+ ")'><img src='newassets/imgs/icon_config.png'><span>审核同意</span></li>";
                    }
                    html += "<li onclick='notify(" + item.id + ")'><img src='newassets/imgs/icon_tip.png'><span>提醒</span></li>";
                    html += "<li onclick='toDetail(" + item.id + ")'><img src='newassets/imgs/icon_account.png'><span>账单</span></li>";
                }

            }else if(${type==2}){
                //资方 账单管理 有提醒按钮
                if ("${sessionUser.authMap.ACCOUNT_BILL_MGMT}" == "true") {
                    html += "<li onclick='toDetail(" + item.id + ")'><img src='newassets/imgs/icon_tip.png'><span>账单</span></li>";
                    html += "<li onclick='notify(" + item.id + ")'><img src='newassets/imgs/icon_account.png'><span>提醒</span></li>";
                }
            }else if(${type==3}){
                //借款方 账单管理
                if ("${sessionUser.authMap.ACCOUNT_BILL_MGMT}" == "true") {
                    html += "<li onclick='toDetail(" + item.id + ")'><img src='newassets/imgs/icon_tip.png'><span>账单</span></li>";
                    //html += "<li onclick='toRepayBalance(" + item.id + ")'><span>还款</span></li>";
                }
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
    //还款页面
    function toRepayBalance() {
        Page.clickBtnToPage("repayBill.html","还款")
        <%--var returnUrl = "${url}";--%>
        <%--ajaxGet("repayBill.html?returnUrl="+returnUrl, function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
    }
    //查看账单详情
    function toDetail(accountBillId) {
        Page.clickBtnToPage("accountBillDetail.html?accountBillId="+accountBillId,"查看账单详情")
        <%--var returnUrl = "${url}";--%>
        <%--ajaxGet("accountBillDetail.html?accountBillId="+accountBillId+"&returnUrl="+returnUrl, function (resultHtml) {--%>
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
                    searchPage();
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }
    // //提醒功能
    // function reviewRepayBill(accountBillId,passStatus) {
    //     var formData = {
    //         'accountBillId':accountBillId,
    //         'passStatus':passStatus
    //     }
    //     ajaxPost("repayBill/reviewRepayBill.json", formData, function (resp) {
    //         if (resp.success) {
    //             selfAlert("审核成功!",function () {
    //                 layer.closeAll();
    //                 $("#status").val(5)
    //                 searchPage();
    //             });
    //         } else {
    //             selfAlert(resp.message);
    //         }
    //     });
    // }
    /**
     * 导出表格
     */
    function exportExcel(){
        var form=$("#searchForm").serialize();
        window.open("accountBill/exportExcel.html?"+form);
    }
</script>
