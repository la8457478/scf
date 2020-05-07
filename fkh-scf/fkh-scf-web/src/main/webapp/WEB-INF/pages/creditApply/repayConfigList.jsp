<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>收款管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil{margin-top: 4px;}
    .configOil span{margin-left: 10px;}
    .configOil a{display: inline-block;width: 100px;text-align: right;padding-right: 10px;}
    .configOil span:first-child{margin-left: 0px;font-size: 14px;}
    .saveConfig{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
    .note{
        position:absolute;line-height:20px;padding:3px 5px;    top: 20px;
        left: 5px;
    }
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
        <form action="repayBill/page.json" id="searchForm" name="searchForm" autocomplete="off">
            <c:if test="${readOnly==null}">
                <input id="companyBorrowerName" name="companyBorrowerName"
                       class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入客户名称"
                       style="width: 10%;">
            </c:if>

            <select id="searchTimeType" name="searchTimeType" class="newPublicInput publicSwitchPlanProject"
                    style="width: 10%;">
                <option value="1">按还款时间</option>
                <option value="2">按处理时间</option>
            </select>
            <input style="width:8%;margin-left: 0px;" class="newPublicInput newPublicInputOne NewTimeInput" type="text" id="beginTime" name="beginTime" readonly="readonly"  placeholder="开始时间">
            <input style="width:8%;margin-left: 0px;" class="newPublicInput newPublicInputOne NewTimeInput" type="text" id="overTime" name="overTime" readonly="readonly"  placeholder="结束时间">
            <c:if test="${readOnly==null}">
                <span>收款处理</span>
                <select id="handleStatus" name="handleStatus" class="newPublicInput publicSwitchPlanProject" style="width: 7%;">
                    <option value="">全部</option>
                    <option value="2">已处理</option>
                    <option value="1" selected>未处理</option>
                </select>
            </c:if>
            <c:if test="${readOnly!=null}">
                <span>收款状态</span>
                <select id="status" name="status" class="newPublicInput publicSwitchPlanProject" style="width: 7%;">
                    <option value="">全部</option>
                    <option value="-1">已拒绝</option>
                    <option value="3">已确认还款</option>
                    <option value="2">未处理</option>
                </select>
            </c:if>

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

                <c:if test="${readOnly==null}">
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                </c:if>
                <c:if test="${readOnly!=null}">
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                </c:if>
            </colgroup>
            <tbody>
            <tr>
                <c:if test="${readOnly==null}">
                    <th class="newPublicTableCenter" title="收款状态">收款状态</th>
                    <th class="newPublicTableCenter" title="客户名称">客户名称</th>
                    <th class="newPublicTableCenter" title="本次还款总额(元)">本次还款总额(元)</th>
                    <th class="newPublicTableCenter" title="本次还款本金(元)">本次还款本金(元)</th>
                    <th class="newPublicTableCenter" title="本次还款利息(元)">本次还款利息(元)</th>
                    <th class="newPublicTableCenter" title="本次还款宽限利息(元)">本次还款宽限利息(元)</th>
                    <th class="newPublicTableCenter" title="本次还款逾期利息(元)">本次还款逾期利息(元)</th>
                    <th class="newPublicTableCenter" title="还款时间">还款时间</th>
                    <th class="newPublicTableCenter" title="处理时间">处理时间</th>
                    <th class="newPublicTableCenter" title="操作">操作</th>
                </c:if>
                <c:if test="${readOnly!=null}">
                    <th class="newPublicTableCenter" title="收款状态">收款状态</th>
                    <th class="newPublicTableCenter" title="交易对手">交易对手</th>
                    <th class="newPublicTableCenter" title="本次还款总额(元)">本次还款总额(元)</th>
                    <th class="newPublicTableCenter" title="本次还款本金(元)">本次还款本金(元)</th>
                    <th class="newPublicTableCenter" title="本次还款利息(元)">本次还款利息(元)</th>
                    <th class="newPublicTableCenter" title="本次还款宽限利息(元)">本次还款宽限利息(元)</th>
                    <th class="newPublicTableCenter" title="本次还款逾期利息(元)">本次还款逾期利息(元)</th>
                    <th class="newPublicTableCenter" title="还款时间">还款时间</th>
                    <th class="newPublicTableCenter" title="处理时间">处理时间</th>
                    <th class="newPublicTableCenter" title="处理意见">处理意见</th>
                </c:if>
            </tr>
            </tbody>
            <tbody id="list"></tbody>
        </table>
        <div id="projectPageBar" class="pageBar"></div>
    </div>
    <!--公用表格模块end-->
</div>
<div class="publicContainerShow batchRecheckShowContainer handleContainer" style="display:none;overflow: hidden;">
    <div class="remarksContainer handleContainerD">
        <form id="handleReceiveForm" name="handleReceiveForm">
            <input id="repayBillId" hidden="hidden" name="repayBillId" class="newPublicInput publicSwitchPlanProject" type="text" style="width: 10%;">
            <div class="configOil">
                <a>处理结果：</a>
                <span>
                    <input   id="receive"  class="receive"  name="receive" type="radio" value="1"  />已确认到账
                </span>
                <span>
                        <input id="notReceive" class="receive"  name="receive" type="radio" value="0"  />未到账拒绝
                </span>
            </div>
            <div class="configOil">
                <a>本金：</a>
                <span class="repayBalance"><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></span>
            </div>
            <div class="configOil">
                <a>利息：</a>
                <span class="interestRateBalance"><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></span>
            </div>
            <div class="configOil">
                <a>宽限期利息：</a>
                <span class="graceRateBalance"><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></span>
            </div>
            <div class="configOil">
                <a>逾期利息：</a>
                <span class="overdueRateBalance"><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" /></span>
            </div>
            <div class="configOil">
                <a>还款总额</a>
                <span class="repayAllBalance"><fmt:formatNumber type="number" value="0" pattern="###,##0.00" maxFractionDigits="2" />
                      </span>
            </div>
            <div  class="configOil" style="position:relative;">
                    <textarea id="reviewReason" name="reviewReason" style="margin-left: 0px;width: 350px;margin-top: 20px;position: relative;z-index: 999;background: transparent;resize:none;outline: none;"  rows="3" onfocus="document.getElementById('note').style.display='none'"  onblur="if(value=='')document.getElementById('note').style.display='block'" placeholder="">
                    </textarea>
                <div id="note" class="note">
                    <font color="#777">请填写处理意见</font>
                </div>
            </div>
            <div style="clear: both;"></div>
            <div>
                <span class="saveConfig" onclick="reviewRepayBill();" style="background: #0071db;">确定</span>
            </div>
        </form>
    </div>
</div>
<!--单据复核列表end-->
</body>
</html>

<script type="text/javascript">

    var repayStatusMap = {
        "-1":"已拒绝",
        2:"未处理",
        3:"已确认收款"
    };
    var dueStatusMap = {
        0 :"未到期",
        1 :"已到期",
    };
    laydate.render({elem: '#beginTime',type: 'date',theme: 'molv'});
    laydate.render({elem: '#overTime',type: 'date',theme: 'molv'});
    var totalPages;
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        //回显条件
        ajaxPost("repayBill/page.json", P.formData(document.searchForm), function (resp) {
            parseInvoiceObjToHtml(resp);
        });
    });

    //搜索项目列表
    function searchPage() {
        if (document.searchForm) {
            var formData = P.formData(document.searchForm);
            ajaxPost("repayBill/page.json", formData, function (resp) {
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
            var repayStatus = repayStatusMap[item.status];
            html += '<tr>';

            if(!'${readOnly}') {
                html +=
                    '<td class="newPublicTableCenter" title="' + repayStatus + '" >'
                    +repayStatus + '</td>';
                html +=
                    '<td class="newPublicTableCenter" title="' + item.companyBorrowerName + '" >'
                    + item.companyBorrowerName + '</td>';
            }else{
                html +=
                    '<td class="newPublicTableCenter" title="' + repayStatus + '" >'
                    +repayStatus + '</td>';
                html +=
                    '<td class="newPublicTableCenter" title="' + item.counterpartyName + '" >'
                    + item.counterpartyName + '</td>';
            }
            html += '<td class="newPublicTableCenter" title="' + PF.formatMoney(item.repayBalance+item.interestRateBalance+item.overdueRateBalance+item.graceRateBalance) + '" >'
                    + PF.formatMoney(item.repayBalance+item.interestRateBalance+item.overdueRateBalance+item.graceRateBalance) + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.repayBalance + '" >' + PF.formatMoney(item.repayBalance) + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.interestRateBalance + '" >' + PF.formatMoney(item.interestRateBalance) + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.graceRateBalance + '" >' + PF.formatMoney(item.graceRateBalance)+'</td>';
            html += '<td class="newPublicTableCenter" title="' + item.overdueRateBalance + '" >' + PF.formatMoney(item.overdueRateBalance) + '</td>';
            html += '<td class="newPublicTableCenter" title="'+P.long2Date(item.repayDate)+'" class="newPublicTableCenter"><span class="time" >'+P.long2Date(item.repayDate)+'</span></td>';
            if(item.status==2){
                html += '<td class="newPublicTableCenter"class="newPublicTableCenter"></td>';
            }else{
                html += '<td class="newPublicTableCenter" title="'+P.long2Datetime(item.updateTime)+'" class="newPublicTableCenter"><span class="time" >'+P.long2Datetime(item.updateTime)+'</span></td>';

            }
            //借款方 账单管理
            // html += "<li onclick='toDetail(" + item.id + ")'><span>账单</span></li>";

            if('${readOnly}'!='true'){
                if(item.status==2|| item.status==1){
                    var json=JSON.stringify(item);
                    html += "<td><ul class='new_icon_pic'><li onclick='toReviewRepayBill(" + item.id + ",\"" + json.replaceAll("\"","\\\"")+ "\")'>" +
                            "<img src='assets/imgs/operate/icon_config.png'><span>收款处理</span></li>";
                }else{
                    html += '<td class="newPublicTableCenter" title="" ></td>';
                }
            }else{
                var reviewReason =item.reviewReason==null?"":item.reviewReason;
                html += '<td class="newPublicTableCenter" title="' + reviewReason + '" >' + reviewReason + '</td>';
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

    //新增或修改弹窗
    function toReviewRepayBill(repayBillId, json) {
        $("#reviewReason").val("");
        $(".receive:checked").attr("checked",false);
        document.getElementById('note').style.display='block'  ;
        var item=jQuery.parseJSON(json);
        $("span.repayAllBalance").html( PF.formatMoney(item.repayBalance+item.interestRateBalance+item.overdueRateBalance+item.graceRateBalance) );
        $("span.repayBalance").html(PF.formatMoney(item.repayBalance));
        $("span.graceRateBalance").html(PF.formatMoney(item.graceRateBalance));
        $("span.interestRateBalance").html(PF.formatMoney(item.interestRateBalance));
        $("span.overdueRateBalance").html(PF.formatMoney(item.overdueRateBalance));
        $("#repayBillId").val(repayBillId);
        // $("#companyBorrowerName").val(data.companyBorrowerName);
        // $("#contractNumber").val(data.contractNumber);
        // $("#contractTime").val(P.long2Date(data.startTime)+"至"+P.long2Date(data.endTime));
        // $("#manageRate").val(data.manageRate);
        // $("#remainingBalance").val(data.remainingBalance);
        // $("#loanSuccessBalance").val(data.loanSuccessBalance);
        // $("#rate").val(data.manageRate);
        var objs = $(".handleContainer");
        var title = "收款处理" ;
        layer.open({
                       type: 1,
                       title: [title, 'font-size:16px;color:white;background-color:#0071db;'],
                       area: ['400px', '300px'],
                       closeBtn: 4,
                       content: objs
                   });
    }
    //确认收到还款
    function reviewRepayBill() {
        var repayAllBalance=  $("span.repayAllBalance").text();
        var receive = $(".receive:checked").val();
        if(receive==null){
            selfAlert("请确认到账或拒绝!");
            return false;
        }
        var reviewReason = $("#reviewReason").val();
        if (isLengthOutRange(reviewReason, true, 5, 100)) {
            selfAlert("请填写5-100字审核理由!");
            return false;
        }

        selfConfirm(receive=="1"?"是否确认到账!<br/>"+repayAllBalance+"元":"是否拒绝到账!<br/>"+repayAllBalance+"元"
            ,function () {
                var repayBillId  =  $("#repayBillId").val();
                var receive = $(".receive:checked").val();
                var formData = {
                    'repayBillId':repayBillId,
                    'passStatus':receive,
                    "reviewReason":reviewReason
                }
                ajaxPost("repayBill/reviewRepayBill.json", formData, function (resp) {
                    if (resp.success) {
                        selfAlert("审核成功!",function () {
                            layer.closeAll();
                            $("#status").val(5)
                            searchPage();
                        });
                    } else {
                        selfAlert(resp.message);
                    }
                });
            },function () {
                // reviewRepayBill(repayBillId,2)
            });


    }
    /**
     * 导出表格
     */
    function exportExcel(){
        var form=$("#searchForm").serialize();
        <c:if test="${readOnly!=null}">
            form+='&from=1';
         </c:if>
        window.open("repayBill/exportExcel.html?"+form);
    }
</script>
