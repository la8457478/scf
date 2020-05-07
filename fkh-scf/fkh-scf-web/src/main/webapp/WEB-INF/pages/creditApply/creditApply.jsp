<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用款申请管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .company{margin-top: 25px;}
    .company span{margin-left: 30px;}
    .newRemarksContainer a{display: inline-block;width: 110px;text-align: right;}
    .company span:first-child{margin-left: 0px;font-size: 14px;}
    .saveConfig{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
</div>
<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form action="creditApply/listPage.json" id="searchForm" name="searchForm" autocomplete="off">
            <c:if test="${type>3}">
                <input id="companyName" name="companyName" class="newPublicInput publicSwitchPlanProject" type="text"
                       placeholder="请输入客户名称" style="width: 140px;">
                <input id="creditApplyNo" name="creditApplyNo" class="newPublicInput publicSwitchPlanProject"
                       type="text" placeholder="请输入放款申请编号" style="width: 200px;">
            </c:if>
            <c:if test="${type<4}">
                <input id="creditApplyNo" name="creditApplyNo" class="newPublicInput publicSwitchPlanProject"
                       type="text" placeholder="请输入用款申请编号" style="width: 200px;">
            </c:if>
            <c:if test="${type==1}">
                <select id="status" name="status" class="newPublicInput publicSwitchPlanProject" style="width: 10%;">
                    <option value="-3" title="用款申请状态">用款申请状态</option>
                    <option value="1" title="运营审核中">运营审核中</option>
                    <option value="2" title="风控审核中">风控审核中</option>
                    <option value="3" title="财务审核中">财务审核中</option>
                    <option value="4" title="管理审核中">管理审核中</option>
                    <option value="5" title="出纳审核中">出纳审核中</option>
                    <option value="-1" title="已拒绝">已拒绝</option>
                </select>
            </c:if>
            <c:if test="${type!=1}">
                <input type="hidden" id="status" name="status">
            </c:if>

            <input type="hidden" name="type" value="${type}">
            <input type="hidden" id="isReviewRecord" name="isReviewRecord" value="${readOnly}">
            <div class="newPublicCustomSearchBtn" onclick="searchPage()"><img
                src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <div class="newPublicCustomSearchBtn" onclick="exportExcel()"><a>导出</a></div>
            <%--<div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>--%>
        </form>
    </div>
    <!--公用导航模块end-->
    <!--公用表格模块-->
    <div style="overflow-x: auto;overflow-y: auto;">
        <div class="newPublicTable">
            <table>
                <colgroup>
                    <c:if test="${type==1}">
                        <%--                    客户端-审核中--%>
                        <col width="100">
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col width="280">
                    </c:if>
                    <c:if test="${type==2}">
                        <%--                    客户端-已放款--%>
                        <col width="150">
                        <col>
                        <col>
                        <col>
                        <col width="150">
                        <col>
                        <col width="150">
                        <col width="280">
                    </c:if>
                    <c:if test="${type==3}">
                        <%--                    客户端-已结清--%>
                        <col width="150">
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col width="280">
                    </c:if>
                    <c:if test="${type>3&&type<9}">
                        <%--                    运营-审核--%>
                        <col width="150">
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                    </c:if>
                    <c:if test="${type==9}">
                        <%--                 运营-放款管理--%>
                        <col width="150">
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                        <col>
                    </c:if>
                </colgroup>
                <tbody>
                <tr>
                    <c:if test="${type<=3}">
                        <c:if test="${type==1}">
                            <th class="newPublicTableCenter" title="申请状态">申请状态</th>
                        </c:if>
                        <th class="newPublicTableCenter" title="用款申请编号">用款申请编号</th>
                        <th class="newPublicTableCenter" title="申请融资额度（元）">申请融资额度（元）</th>
                        <th class="newPublicTableCenter" title="可提用融资额度（元）">可提用融资额度（元）</th>
                        <th class="newPublicTableCenter" title="运单个数">运单个数</th>
                        <th class="newPublicTableCenter" title="申请转让时间">申请转让时间</th>
                        <c:if test="${type==1}">
                            <th>操作</th>
                        </c:if>
                        <c:if test="${type==2}">
                            <th class="newPublicTableCenter" title="融资款发放金额（元）">融资款发放金额（元）</th>
                            <th class="newPublicTableCenter" title="融资款发放时间">融资款发放时间</th>
                            <th>操作</th>
                        </c:if>
                        <c:if test="${type==3}">
                            <th class="newPublicTableCenter" title="融资款发放金额（元）">融资款发放金额（元）</th>
                            <th class="newPublicTableCenter" title="融资款发放时间">融资款发放时间</th>
                            <th class="newPublicTableCenter" title="还款金额（元）">还款金额（元）</th>
                            <th class="newPublicTableCenter" title="还款时间">还款时间</th>
                            <th>操作</th>
                        </c:if>
                    </c:if>
                    <c:if test="${type>3&&type<=9}">
                        <th class="newPublicTableCenter" title="放款申请编号">放款申请编号</th>
                        <th class="newPublicTableCenter" title="客户名称">客户名称</th>
                        <th class="newPublicTableCenter" title="交易对手">交易对手</th>
                        <th class="newPublicTableCenter" title="申请融资额度（元）">申请融资额度（元）</th>
                        <th class="newPublicTableCenter" title="应收账款转让金额（元）">应收账款转让金额（元）</th>
                        <c:if test="${type>3&&type<9}">
                            <th class="newPublicTableCenter" title="运单个数">运单个数</th>
                            <th class="newPublicTableCenter" title="申请放款时间">申请放款时间</th>
                            <th class="newPublicTableCenter" title="操作">操作</th>
                        </c:if>
                        <c:if test="${type==9}">
                            <th class="newPublicTableCenter" title="放款金额（元）">放款金额（元）</th>
                            <th class="newPublicTableCenter" title="放款时间">放款时间</th>
                        </c:if>
                    </c:if>
                </tr>
                </tbody>
                <tbody id="projectList"></tbody>
            </table>
            <div id="projectPageBar" class="pageBar"></div>
        </div>
    </div>
    <!--公用表格模块end-->
</div>

<!--客户信息表单-->

<div class="publicContainerShow batchRecheckShowContainer companyContainer" style="display:none;overflow: hidden;">
    <div class="remarksContainer newRemarksContainer">
        <form id="companyFrom" name="companyFrom">
            <input id="subjectClaimsOrderIds" type="hidden" name="subjectClaimsOrderIds">
            <div class="company">
                <a>客户名称：</a>
                <span class="companyBorrowerName">
                </span>
            </div>
            <div class="company">
                <a>审批书决议编号：</a>
                <span  class="contractNumber" ></span>

            </div>
            <div class="company">
                <a>合同期间：</a>
                <span  class="contractTime" ></span>

            </div>
            <div class="company">
                <a>总额度：</a>
                <span  class="totalBalance" ></span>

            </div>

            <div class="company">

            <a>可用额度：</a>
            <span  class="remainingBalance" ></span>
            </div>
            <div style="clear: both;"></div>
            <div>
<%--                <span class="saveConfig" onclick="closeCompany();">确定</span>--%>
            </div>
        </form>
    </div>
</div>

<!--信息表单-->

<div class="publicContainerShow batchRecheckShowContainer loanContainer" style="display:none;overflow: hidden;">
    <div class="remarksContainer newRemarksContainer">
        <form id="loanFrom" name="loanFrom">
            <div class="loan">
                <span>放款金额:</span>
                <span>
                    <input id="applyBalance" class="newPublicInput" name="applyBalance" type="text" value="" readonly="readonly"/>
                </span>
                <span>是否生效</span>
                <input style="width: 12%;margin-left: 0px;" class="newPublicInput"
                       type="text" id="isValid" name="isValid" readonly="readonly" placeholder="">
            </div>
            <div class="loan">
                <span>客户名称</span>
                <input style="width: 12%;margin-left: 0px;" class="newPublicInput"
                       type="text"  name="companyBorrowerName" readonly="readonly" placeholder="">
            </div>
            <div class="loan">
                <span>原可用额度</span>
                <input style="width: 12%;margin-left: 0px;" class="newPublicInput"
                       type="text" name="remainingBalance" readonly="readonly">

                <span>新可用额度</span>
                <%--                //TODO-LA:融资比例字段没有--%>
                <input type="text" class="newRemainingBalance" id="rate " name="rate" readonly="readonly">
            </div>
            <div class="loan">
                <span>交易对手</span>
                <input style="width: 12%;margin-left: 0px;" class="newPublicInput"
                       type="text" name="counterpartyName" readonly="readonly">
            </div>
            <div class="loan">
                <span>原分项额度</span>
                <input style="width: 12%;margin-left: 0px;" class="newPublicInput"
                       type="text" name="subitemRemainBalance" readonly="readonly">
                <span>新可用额度</span>
                <input style="width: 12%;margin-left: 0px;" class="newPublicInput"
                       type="text" name="newSubitemRemainBalance" readonly="readonly">
            </div>
            <div style="clear: both;"></div>
            <div>
                <span class="saveConfig" onclick="closeCompany();" style="background: #0071db !important;">确定</span>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<script type="text/javascript">

    var statusMap = {
        "-2":"已作废",
        "-1":"已拒绝",
        "1":"运营审核中",
        "2":"风控审核中",
        "3":"财务审核中",
        "4":"管理层审核中",
        "5":"出纳审核中",
        "6":"已放款",
        "7":"未还款",
        "8":"已还款"

    }
    //页面类型说明
    var typeMap = {
        //客户端
        1:"用款申请记录-审核中",
        2:"用款申请记录-已放款",
        3:"用款申请记录-已结清",
        //运营端
        4:"运营审核-放款申请审核",//运营审核
        5:"风控审核-放款申请审核",//风控审核
        6:"管理层-放款申请审核",//管理层审核
        7:"财务审核-放款申请审核",//财务放款
        8:"出纳审核-放款申请审核",//财务放款
        9:"出纳审核-放款管理"//财务放款

    }
    // var returnUrl ;
    var type = ${type};
    var status= -3;
    laydate.render({elem: '#startTime',type: 'datetime',theme: 'molv'});
    var totalPages;
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();

        var headerName=$("#headerName");
        var firstNavName=$("#firstNavName");
        var navNames = typeMap[type].split("-");
        firstNavName.text(navNames[0]);
        headerName.text(navNames[1]);
        if(${readOnly}){
            headerName.text("审核记录");
        }
        status=initStatus(type);
        $("#status").val(status);

        //处理搜索参数记住问题，含页码
        Page.initFormData(document.searchForm);
        searchPage();
    });
    /**
     * 根据来源，初始化状态
     * */
    function initStatus(type) {
        var status;
        if(type==1){
            //审核中
            status = -3;
        }else if(type==2){
            //已放款
            status = 6;
        }else if(type==3){
            //已还款 历史订单
            status = 8;
        }else if(type==4){
            //运营审核
            status = 1;
        }else if(type==5){
            //风控审核
            status = 2;
        }else if(type==6){
            //管理层审核
            status = 4;
        }else if(type==7){
            //财务审核
            status = 3;
        }else if(type==8){
            //出纳审核
            status = 5;
        }else if(type==9){
            //放款管理
            status = 6;
        }
        return status;
    }

    //搜索项目列表
    function searchPage() {
        if (!handleSearchListAuth()) {
            return false;
        }
        if (document.searchForm) {
            var formData = P.formData(document.searchForm);
            ajaxPost("creditApply/listPage.json", formData, function (resp) {
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
            if(type<=1){
                html = "<tr><td colspan='7' align='center'>暂无数据！</td></tr>";
            }else
            if(type<=2){
                html = "<tr><td colspan='8' align='center'>暂无数据！</td></tr>";
            }else
            if(type<=3){
                html = "<tr><td colspan='10' align='center'>暂无数据！</td></tr>";
            }
            else
            if(type==8){
                html = "<tr><td colspan='8' align='center'>暂无数据！</td></tr>";
            }else
            if(type == 9){
                html = "<tr><td colspan='7' align='center'>暂无数据！</td></tr>";
            }else{
                html = "<tr><td colspan='8' align='center'>暂无数据！</td></tr>";
            }

            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            var status = statusMap[item.status];
            html += '<tr>';
            if(type<=3){
                if(type==1) {
                    //客户端
                    html += '<td class="newPublicTableCenter" title="' + status + '" >' + status + '</td>';
                }
                html += '<td class="newPublicTableCenter" title="' + item.creditApplyNo + '" >' + item.creditApplyNo + '</td>';
                html += '<td class="newPublicTableCenter" title="' + PF.formatMoney(item.applyBalance) + '" >' + PF.formatMoney(item.applyBalance) + '</td>';
                html += '<td class="newPublicTableCenter" title="' + PF.formatMoney(item.canApplyBalance) + '" >' + PF.formatMoney(item.canApplyBalance) + '</td>';
                html += '<td class="newPublicTableCenter" title="' + item.waybillCount + '" >' +'<a href="#" onclick="toWaybillList('+item.id+')" class="tabs-close"><u>'+item.waybillCount+'</u></a>'  + '</td>';
                html += '<td class="newPublicTableCenter" title="' + P.long2Datetime(item.createTime) + '" >' + P.long2Datetime(item.createTime) +'</td>';
                if(type==1){
                // 审核中
                    html += "<td><ul class='new_icon_pic'>";
                    if ("${sessionUser.authMap.BORROWER_REVIEWING_CREDIT_APPLY_DETAIL}" == "true") {
                        html += "<li onclick='toBalanceDetail(" + item.id + ")'><img src='assets/imgs/operate/icon_apply_info.png'><span>用款申请详情</span></li>";
                    }
                    if (item.status == -1 && "${sessionUser.authMap.BORROWER_REVIEWING_CREDIT_REAPPLY}" == "true") {
                        html += "<li onclick='toReStartCreditApply(" + item.id+","+ item.counterpartyId + ")'><img src='assets/imgs/operate/icon_reapply.png'><span>重新申请用款</span></li>";
                    }
                    html += "</ul></td></tr>";

                }else {
                    //已放款
                    html += '<td class="newPublicTableCenter" title="' + PF.formatMoney(item.applyBalance) + '" >' + PF.formatMoney(item.applyBalance) + '</td>';
                    html += '<td class="newPublicTableCenter" title="' + P.long2Datetime(item.loanTime) + '" >' + P.long2Datetime(item.loanTime) +'</td>';
                    if(type==3){
                        //已结清
                        html += '<td class="newPublicTableCenter" title="' + PF.formatMoney(item.applyBalance) + '" >' + PF.formatMoney(item.applyBalance) + '</td>';
                        html += '<td class="newPublicTableCenter" title="' + P.long2Datetime(item.updateTime) + '" >' + P.long2Datetime(item.updateTime) +'</td>';
                    }
                    html += "<td ><ul class='new_icon_pic'>";
                    if ("${sessionUser.authMap.BORROWER_LOANED_CREDIT_APPLY_DETAIL}" == "true" || "${sessionUser.authMap.BORROWER_REPAYED_CREDIT_APPLY_DETAIL}" == "true") {
                        html += "<li onclick='toBalanceDetail(" + item.id +")'><img src='assets/imgs/operate/icon_apply_info.png'><span>用款申请详情</span></li>";
                    }
                    html += "</ul></td></tr>";
                }
            } else if (type > 3 && type <= 9) {
                html +=
                    '<td class="newPublicTableCenter" title="' + item.creditApplyNo + '" >' + item.creditApplyNo
                    + '</td>';
                html +=
                    '<td class="newPublicTableCenter" title="' + item.companyBorrowerName + '" >'
                    + '<a href="#" onclick="toCompanyDetail(' + item.companyBorrowerId + ')" ><u>'
                    + item.companyBorrowerName + '</u></a>' + '</td>';
                html +=
                    '<td class="newPublicTableCenter" title="' + item.counterpartyName + '" >'
                    + item.counterpartyName + '</td>';
                html +=
                    '<td class="newPublicTableCenter" title="' + PF.formatMoney(item.applyBalance) + '" >' + PF.formatMoney(
                    item.applyBalance) + '</td>';
                html +=
                    '<td class="newPublicTableCenter" title="' + PF.formatMoney(item.transferBalance) + '" >' + PF.formatMoney(
                    item.transferBalance) + '</td>';
                if (type == 9) {
                    html +=
                        '<td class="newPublicTableCenter" title="' + PF.formatMoney(item.applyBalance) + '" >' + PF.formatMoney(
                        item.applyBalance) + '</td>';
                    html +=
                        '<td class="newPublicTableCenter" title="' + P.long2Datetime(item.loanTime) + '" >' + P.long2Datetime(
                        item.loanTime) + '</td>';

                } else if (type != 9 && handleSearchWaybillAuth()) {
                    html +=
                        '<td class="newPublicTableCenter" title="' + item.waybillCount + '" >'
                        + '<a href="#" onclick="toWaybillList(' + item.id + ')" class="tabs-close"><u>'
                        + item.waybillCount + '</u></a>' + '</td>';
                    html +=
                        '<td class="newPublicTableCenter" title="' + P.long2Datetime(item.createTime) + '" >' + P.long2Datetime(
                        item.createTime) + '</td>';
                    html += "<td  class=\"newPublicTableCenter\" ><ul class='new_icon_pic'>";
                    //如果>=4 运营端，审核页面
                    if (handleReviewDetailAuth()) {
                        html += "<li onclick='toReview(" + item.id + "," + type + ")'><img src='assets/imgs/operate/icon_review_info.png'/><span>审核详情</span></li>";
                    }
                    html += "</ul></td></tr>";

                }
            }
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.searchForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }
    //查看申请金额详情
    function toBalanceDetail(creditApplyId) {

        Page.clickBtnToPage("balanceDetail.html?creditApplyId="+creditApplyId+"&type="+type,"查看用款申请详情");
        <%--var url = '${url}';--%>
        <%--P.setReturnUrlForm("balanceDetail",url);--%>
        <%--ajaxGet(, function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
    }
    // //查看债权订单
    // function toSubjectClaims(creditApplyId) {
    //     Page.clickBtnToPage("subjectClaimsOrder.html?creditApplyId="+creditApplyId+"&type="+type,"查看用款申请" );
    // }
    //关闭客户信息
    function closeCompany() {
        layer.closeAll();
    }
    //TODO-LA 2020.2.28:附件方法
    function toAttachment(creditApplyId) {

    }
    //客户信息弹窗
    function toCompanyDetail(companyId) {
        ajaxGet("companyContract/getByCompanyId.json?companyBorrowerId="+companyId, function (resp) {
            if (resp.success) {
                var data = resp.data;
                $("span.companyBorrowerName").html(data.companyBorrowerName);
                $("span.contractNumber").html(data.contractNumber);
                $("span.contractTime").html(P.long2Date(data.startTime)+"至"+P.long2Date(data.endTime));
                $("span.remainingBalance").html(PF.formatMoney(data.remainingBalance)+"元");
                $("span.totalBalance").html(PF.formatMoney(data.totalBalance)+"元");
                // $("#companyBorrowerName").val(data.companyBorrowerName);
                // $("#contractNumber").val(data.contractNumber);
                // $("#contractTime").val(P.long2Date(data.startTime)+"至"+P.long2Date(data.endTime));
                // $("#manageRate").val(data.manageRate);
                // $("#remainingBalance").val(data.remainingBalance);
                // $("#loanSuccessBalance").val(data.loanSuccessBalance);
                // $("#rate").val(data.manageRate);
                var objs = $(".companyContainer");
                var title = "客户信息" ;
                layer.open({
                       type: 1,
                       title: [title, 'font-size:16px;color:white;background-color:#0071db;'],
                       area: ['600px', '350px'],
                       closeBtn: 4,
                       content: objs
                   });
            } else {
                selfAlert(resp.message);
            }
        });
    }
    function toBill(creditApplyId) {
        Page.clickBtnToPage("accountBillDetail.html?creditApplyId="+creditApplyId,"查看账单详情")
        <%--var returnUrl = '${url}';--%>
        <%--ajaxGet("accountBillDetail.html?creditApplyId="+creditApplyId+"&returnUrl="+returnUrl, function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
    }
    function toModify(companyId, applyBalance,subitemRemainBalance,counterpartyName) {
        ajaxGet("companyContract/getByCompanyId.json?companyBorrowerId="+companyId, function (resp) {
            if (resp.success) {
                var data = resp.data;
                $(":input[name='companyBorrowerName']").attr("value",data.companyBorrowerName);
                $(":input[name='applyBalance']").attr("value",PF.formatMoney(applyBalance));
                $(":input[name='isValid']").attr("value","是");

                $(":input[name='remainingBalance']").attr("value",PF.formatMoney(data.remainingBalance));
                $(":input[name='newRemainingBalance']").attr("value",PF.formatMoney(data.remainingBalance-applyBalance));

                $(":input[name='counterpartyName']").attr("value",counterpartyName);
                $(":input[name='subitemRemainBalance']").attr("value",PF.formatMoney(subitemRemainBalance));
                $(":input[name='newSubitemRemainBalance']").attr("value",PF.formatMoney(subitemRemainBalance-applyBalance));
                // $("#companyBorrowerName").val(data.companyBorrowerName);
                // $("#contractNumber").val(data.contractNumber);
                // $("#contractTime").val(P.long2Date(data.startTime)+"至"+P.long2Date(data.endTime));
                // $("#manageRate").val(data.manageRate);
                // $("#remainingBalance").val(data.remainingBalance);
                // $("#loanSuccessBalance").val(data.loanSuccessBalance);
                // $("#rate").val(data.manageRate);
                var objs = $(".loanContainer");
                var title = "客户信息" ;
                layer.open({
                               type: 1,
                               title: [title, 'font-size:16px;color:white;background-color:#0071db;'],
                               area: ['600px', '350px'],
                               closeBtn: 4,
                               content: objs
                           });
            } else {
                selfAlert(resp.message);
            }
        });


        // var formData = {
        //     'creditApplyId':creditApplyId
        // }
        // ajaxPost("creditApply/loan.json", formData, function (resp) {
        //     if (resp.success) {
        //         selfAlert("放款成功!",function () {
        //             layer.closeAll();
        //             $("#status").val(5)
        //             searchPage();
        //         });
        //     } else {
        //         selfAlert(resp.message);
        //     }
        // });
    }
    function toReview(creditApplyId,type) {
        Page.clickBtnToPage("creditApplyDetail.html?creditApplyId="+creditApplyId+"&type="+type+"&readOnly=${readOnly}","查看审核详情")
        <%--var returnUrl = '${url}';--%>
        <%--P.setReturnUrlForm("creditApplyDetail",returnUrl);--%>
        <%--ajaxGet("creditApplyDetail.html?creditApplyId="+creditApplyId+"&type="+type+"&readOnly=${readOnly}", function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
        // if(type==7){
        //     //财务审核
        //     ajaxGet("reviewCreditApply.html?creditApplyId="+creditApplyId+"&type="+type+"&returnUrl="+returnUrl, function (resultHtml) {
        //         $(window.mainContainer).html(resultHtml);
        //     });
        // }else{
        //     //TODO-LA: 2020/3/1  审核页面显示按type 按钮不同。4 5 6 7
        //     //其他审核
        // }
    }
    function toWaybillList(creditApplyId) {
        if (handleSearchWaybillAuth()) {
            Page.clickBtnToPage("waybillList.html?type=${type}&&readOnly=${readOnly}&creditApplyId="+creditApplyId,"查看运单列表")
        }
        <%--      var returnUrl = '${url}';--%>
        <%--ajaxGet("waybillList.html?type=${type}&&readOnly=${readOnly}&creditApplyId="+creditApplyId+"&returnUrl="+returnUrl, function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
    }

    /**
     * 导出表格
     */
    function exportExcel(){
        // creditApply/listPage
        var form=$("#searchForm").serialize();
        window.open("creditApply/exportExcel.html?"+form);
    }

    /**
     * 重新发起用款申请
     * @param creditApplyId
     * @param counterpartyId
     */
    function toReStartCreditApply(creditApplyId,counterpartyId) {
        //校验是否全为异常运单
        ajaxGet("waybill/getReStartCreditApplyData.json?creditApplyId="+creditApplyId+"&counterpartyId="+counterpartyId, function (resp) {
            if (resp.success) {
                Page.clickBtnToPage("reStartCreditApply.html?creditApplyId="+creditApplyId+"&counterpartyId="+counterpartyId,"重新申请用款");
            } else {
                selfAlert(resp.message);
            }
        });
        <%--var returnUrl = '${url}';--%>
        <%--P.setReturnUrlForm("creditApplyDetail",returnUrl);--%>
        <%--ajaxGet("reStartCreditApply.html?creditApplyId="+creditApplyId+"&counterpartyId="+counterpartyId, function (resultHtml) {--%>
        <%--    $(window.mainContainer).html(resultHtml);--%>
        <%--});--%>
    }

    /**
     * 查看列表权限处理
     */
    function handleSearchListAuth() {
        <c:choose>
            <c:when test="${type<=3}">
                if ("${sessionUser.authMap.BORROWER_REVIEWING_CREDIT_APPLY_LIST}" == "false" &&
                    "${sessionUser.authMap.BORROWER_LOANED_CREDIT_APPLY_LIST}" == "false" &&
                    "${sessionUser.authMap.BORROWER_REPAYED_CREDIT_APPLY_LIST}" == "false") {
                    $("#projectList").html("<tr><td colspan='6' align='center'>暂无权限！</td></tr>");
                    $("#projectPageBar").hide();
                    return false;
                }
            </c:when>
            <c:when test="${type==8}">
                if ("${sessionUser.authMap.CASHIER_LOAN_LIST}" == "false") {
                    $("#projectList").html("<tr><td colspan='8' align='center'>暂无权限！</td></tr>");
                    $("#projectPageBar").hide();
                    return false;
                }
            </c:when>
            <c:otherwise>
                if ("${sessionUser.authMap.OPERATION_LOAN_LIST}" == "false" &&
                    "${sessionUser.authMap.RISK_LOAN_LIST}" == "false" &&
                    "${sessionUser.authMap.MGMT_LOAN_LIST}" == "false" &&
                    "${sessionUser.authMap.FINANCE_LOAN_LIST}" == "false" &&
                    "${sessionUser.authMap.CASHIER_LOAN_LIST}" == "false") {
                    $("#projectList").html("<tr><td colspan='7' align='center'>暂无权限！</td></tr>");
                    $("#projectPageBar").hide();
                    return false;
                }
            </c:otherwise>
        </c:choose>

        return true;
    }

    /**
     * 处理审核详情权限
     */
    function handleReviewDetailAuth() {
      if ("${sessionUser.authMap.OPERATION_LOAN_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.RISK_LOAN_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.MGMT_LOAN_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.FINANCE_LOAN_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.CASHIER_LOAN_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.OPERATION_RECORD_LOAN_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.RISK_RECORD_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.MGMT_RECORD_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.FINANCE_RECORD_LOAN_REVIEW_DETAIL}" == "true" ||
          "${sessionUser.authMap.CASHIER_RECORD_LOAN_REVIEW_DETAIL}" == "true") {
          return true;
      }

      return false;
    }

    /**
     * 处理查看放款申请已转让运单权限
     */
    function handleSearchWaybillAuth() {
        if ("${sessionUser.authMap.OPERATION_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.RISK_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.MGMT_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.FINANCE_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.CASHIER_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.OPERATION_RECORD_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.RISK_RECORD_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.MGMT_RECORD_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.FINANCE_RECORD_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.CASHIER_RECORD_LOAN_WAYBILL_LIST}" == "true" ||
            "${sessionUser.authMap.SUBJECT_CLAIMS_WAYBILL_LIST}" == "true") {
            return true;
        }

        return false;
    }
</script>
