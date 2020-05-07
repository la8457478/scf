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
    .saveCounterparty{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain counterpartyMgmtForm">
        <form action="counterparty/page.json" name="counterpartyForm" onsubmit="return searchScfCounterparty();" autocomplete="off">
            <input type="hidden" id="page" name="page" value="1">
            <input type="hidden" id="companyBorrowerId" name="companyBorrowerId" value="${companyBorrowerId}">
            <input id="counterpartyNameSearch" class="newPublicInput"  name="counterpartyName"  type="text"  placeholder="请输入交易对手名称" />
            <div class="newPublicCustomSearchBtn" onclick="searchScfCounterparty()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <div class="newPublicCustomResetBtn" onclick="document.counterpartyForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>
            <c:if test="${sessionUser.authMap.COUNTERPARTY_ADD == true}">
                <div class="newPublicCustomSearchBtn" style="width: 120px;" onclick="toCounterpartyDetail(${companyBorrowerId},${companyContract.id})"><img src="newassets/imgs/icon_new_add.png"><a>新增交易对手</a></div>
            </c:if>
            <div class="publicDetailsModuleReturnBtn return">返回</div>
        </form>
    </div>
    <!--公用导航模块end-->
    <div id="statisticsInfo">
        <p style="font-size:14px;color: #4e4e4e;">${companyContract.companyBorrowerName}</p>
        <p id="companyContractMoneyInfo" class="sumAmountColor"></p>
    </div>
    <!--公用表格模块-->
    <div class="newPublicTable" style="margin-top: 0px;">
        <table>
            <colgroup>
                <col >
                <col >
                <col >
                <col >
            </colgroup>
            <thead>
            <tr role="row">
                <th>交易对手</th>
                <th>分项融资限额(元)</th>
                <th>应收账款转让余额(元)</th>
                <th>已提用融资款额度(元)</th>
                <th>可提用融资余额(元)</th>

                <%--<th>分项额度</th>--%>
                <%--<th>回款核销额</th>--%>
                <%--<th>已转应收帐款额</th>--%>
                <%--<th>累计放款额</th>--%>
                <%--<th>可用余额</th>--%>

                <%--<th>分项已使用额</th>--%>
                <%--<th>融资比例</th>--%>

                <%--<th>管理费率</th>--%>
                <%--<th>融资利率</th>--%>
                <%--<th>账期</th>--%>

                <%--<th>宽限期</th>--%>
                <%--<th>逾期利率</th>--%>
                <%--<th>宽限期利率</th>--%>
                <th>审核状态</th>
                <th>操作</th>

            </tr>
            </thead>
            <tbody>
            </tbody>
            <tbody id="counterpartyList"></tbody>
        </table>
        <div id="counterpartyPageBar" class="pageBar"></div>
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
        $("#companyContractMoneyInfo").html("总额度 "+PF.formatMoney(${companyContract.totalBalance})+" 元,可用额度: "+PF.formatMoney(${companyContract.remainingBalance})+"元");
        //显示页面标题,并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        //回显条件
        Page.initFormData(document.counterpartyForm);
        ajaxPost("counterparty/page.json", P.formData(document.counterpartyForm), function (resp) {
            parseInvoiceObjToHtml(resp);
        });
    });

    //搜索项目列表
    function searchScfCounterparty() {
        // var counterpartyValidate = PF.counterpartyValidate("keywords", "counterpartyName");
        if (document.counterpartyForm) {
            var formData = P.formData(document.counterpartyForm);
            ajaxPost("counterparty/page.json", formData, function (resp) {
                parseInvoiceObjToHtml(resp);
            });
        }
        return false;
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var dataTableId = "counterpartyList";
        var dataTablePageBarId = "counterpartyPageBar";

        var html = "";

        //更新“客户信息”
        var companyContract = resp.data.companyContract;
        if (companyContract != null) {
            $("#companyContractMoneyInfo").html("总额度 "+PF.formatMoney(companyContract.totalBalance)+" 元,可用额度: "+PF.formatMoney(companyContract.remainingBalance)+"元");
        }

        var responseData = resp.data.counterpartyPage;
        if (responseData.records.length <= 0) {
            html = "<tr><td colspan='7' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            html += '<td title="' + item.counterpartyName + '" >' + item.counterpartyName + '</td>';
            html += '<td title="' + PF.formatMoney(item.subitemLimitBalance) + '" >' + PF.formatMoney(item.subitemLimitBalance) + '</td>';
            html += '<td title="' + PF.formatMoney(item.hadReceivableBalance - item.returnedTransferBalance) + '" >' + PF.formatMoney(parseFloat((item.hadReceivableBalance - item.returnedTransferBalance).toFixed(2)))+ '</td>';
            html += '<td title="' + PF.formatMoney(item.subitemUsedBalance) + '" >' + PF.formatMoney(item.subitemUsedBalance) + '</td>';
            html += '<td title="' + PF.formatMoney(item.subitemRemainBalance) + '" >' + PF.formatMoney(item.subitemRemainBalance) + '</td>';

            // html += '<td title="' + item.subitemRemainBalance + '" >' + item.subitemRemainBalance + '</td>';
            // html += '<td title="' + item.subitemUsedBalance + '" >' + item.subitemUsedBalance + '</td>';
            // html += '<td title="' + item.ruleRatio + '" >' + item.ruleRatio+"%" + '</td>';
            // html += '<td title="' + item.manageRate + '" >' + item.manageRate+"%" + '</td>';
            // html += '<td title="' + item.interestRate + '" >' + item.interestRate+"%" + '</td>';
            // html += '<td title="' + item.paymentDays + '" >' + item.paymentDays + '</td>';
            // html += '<td title="' + item.paymentDays + '" >' + item.graceDays + '</td>';
            // html += '<td title="' + item.overdueRate + '" >' + item.overdueRate+"%" + '</td>';
            // html += '<td title="' + item.graceRate + '" >' + item.graceRate+"%" + '</td>';

            html += '<td title="' + showStatus(item.status) + '" >' + showStatus(item.status)+ '</td>';

            html += "<td><ul class='new_icon_pic'>";

            html += "<li onclick='toCounterpartyUpdate(" + item.id + ",4)'><img src='assets/imgs/operate/icon_view.png'><span>查看</span></li>";
            if ("${sessionUser.authMap.COUNTERPARTY_REVIEW}" == "true") {
                if (item.status == 0 || item.status == 2) {
                    html += "<li onclick='toCounterpartyReview(" + item.id + ")'><img src='assets/imgs/operate/icon_review.png'/><span>审核</span></li>";
                }
            }
            if ("${sessionUser.authMap.COUNTERPARTY_ADD}" == "true"){
                html += "<li onclick='toCounterpartyUpdate(" + item.id + ",2)'><img src='assets/imgs/operate/icon_edit.png'><span>编辑</span></li>";
            }
            html += "</ul></td>";
            html += "</tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.counterpartyForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }

    //新增或修改弹窗
    function editCounterparty(roleId, roleName,json) {
        var item=jQuery.parseJSON(json)
        ajaxGet("counterparty/findAllParentList.json", function (resp) {
                var list="<option value='0'>----------请选择父级配置----------</option>";
                var oilSites=resp.data;
                for(var i=0;i<oilSites.length;i++){
                    if(oilSites[i].id==item.parentId){
                        list+='<option value="'+oilSites[i].id+'" title="'+oilSites[i].configDesc+'" selected>'+oilSites[i].configDesc+'</option>';
                    }else{
                        list+='<option value="'+oilSites[i].id+'" title="'+oilSites[i].configDesc+'">'+oilSites[i].configDesc+'</option>';
                    }
                }
                $("#parentId").html(list);
                showEditView(item);
        });
    }
    function showEditView(item) {
        var objs = $(".configOilCompany");
        var title = item.id? "编辑配置" : "新增配置";
        layer.open({
            type: 1,
            title: [title, 'font-size:16px;color:white;background-color:#0071db;'],
            area: ['400px', '300px'],
            closeBtn: 4,
            content: objs
        });

        /** 父级Id */
        /** 配置键 */
        /** 配置值 */
        /** 常量描述 */
        /** 配置是否生效 */
        $("#configId").val(item.id);
        $("#configKey").val(item.configKey);
        $("#configValue").val(item.configValue);
        $("#configDesc").val(item.configDesc);
        $("#configStatus").val(item.configStatus);
    }

    //删除
    function deleteCounterparty(roleId) {
        selfConfirm("你确定要删除该配置吗!",function () {
            var params = {"counterpartyId" : roleId};
            ajaxPost("counterparty/delete.json", params, function (resp) {
                if (resp.success) {
                    selfAlert("保存成功!",function () {
                        layer.closeAll();
                        $("#page").val($("span.on").html());
                        searchScfCounterparty();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        });
    }

    /**
     * 跳转到项目-新增交易对手
     */
    function toCounterpartyDetail(companyBorrowerId,companyContractId){
        Page.clickBtnToPage("counterparty/counterpartyDetail.html?companyBorrowerId="+companyBorrowerId+"&companyContractId="+companyContractId,"新增交易对手");
    }
    /**
     * 跳转到项目-修改交易对手
     */
    function toCounterpartyUpdate(id,todo){
        var info="查看交易对手";
        if(todo==2){
            info="修改交易对手";
        }
        Page.clickBtnToPage("counterparty/counterpartyUpdate.html?id="+id+"&todo="+todo,info);
    }
    /**
     * 跳转到客户-修改客户合同
     */
    function toCounterpartyReview(id){
        Page.clickBtnToPage("counterparty/counterpartyReview.html?id="+id,"审核交易对手");
    }
</script>
