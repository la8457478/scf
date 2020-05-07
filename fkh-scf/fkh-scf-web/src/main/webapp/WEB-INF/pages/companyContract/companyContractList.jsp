<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil{margin-top: 25px;}
    .configOil span{margin-left: 30px;}
    .configOil span:first-child{margin-left: 0px;font-size: 14px;}
    .saveCompanyContract{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain companyContractMgmtForm">
        <form action="companyContract/page.json" name="companyContractForm" onsubmit="return searchCompanyContract();" autocomplete="off">
            <input type="hidden" id="page" name="page" value="1">
            <input  class="newPublicInput"  name="companyBorrowerName"  type="text"  placeholder="请输入客户名称" />
            <div class="newPublicCustomSearchBtn" onclick="searchCompanyContract()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>

            <div class="newPublicCustomResetBtn" onclick="document.companyContractForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>

            <c:if test="${sessionUser.authMap.COMPANY_CONTRACT_ADD == true}">
                <div class="newPublicCustomSearchBtn" onclick="toCompanyContractDetail()"><img src="newassets/imgs/icon_new_add.png"><a>新增客户</a></div>
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
            </colgroup>
            <thead>
            <tr role="row">
                <th>客户名称</th>
                <th>合同编号</th>
                <th>合同期间</th>
                <%--<th>管理费率</th>--%>
                <%--<th>融资利率</th>--%>
                <%--<th>年化利率</th>--%>
                <th>总额度(元)</th>
                <th>已提用融资款额度(元)</th>
                <th>可用额度(元)</th>
                <th>状态</th>
                <%--<th>企业已归还金额</th>--%>
                <%--<th>未还：在途</th>--%>
                <th width="330px">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <tbody id="companyContractList"></tbody>
        </table>
        <div id="companyContractPageBar" class="pageBar"></div>
    </div>
    <!--公用表格模块end-->
</div>

<!--单据复核列表end-->

</body>
</html>

<script type="text/javascript">
    var totalPages;
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        //显示标题
        Page.showPageTitle();
        //回显条件
        Page.initFormData(document.companyContractForm);
        searchCompanyContract();
    });

    //搜索项目列表
    function searchCompanyContract() {
        if ("${sessionUser.authMap.COMPANY_CONTRACT_LIST}" == "false") {
            $("#companyContractList").html("<tr><td colspan='8' align='center'>暂无权限！</td></tr>");
            $("#companyContractPageBar").hide();
            return false;
        }

        var companyContractValidate = PF.projectValidate("keywords", "planName");
        if (companyContractValidate && document.companyContractForm) {
            var formData = P.formData(document.companyContractForm);
            ajaxPost("companyContract/page.json", formData, function (resp) {
                parseInvoiceObjToHtml(resp);
            });
        }
        return false;
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var dataTableId = "companyContractList";
        var dataTablePageBarId = "companyContractPageBar";

        var html = "";
        var responseData = resp.data;

        if (responseData.records.length <= 0) {
            html = "<tr><td colspan='8' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            html += '<td title="' + item.companyBorrowerName + '" >' + item.companyBorrowerName + '</td>';
            html += '<td title="' + item.contractNumber + '" >' + item.contractNumber + '</td>';
            html += '<td title="' + P.long2Date(item.startTime)+'至'+P.long2Date(item.endTime)+ '" >' + P.long2Date(item.startTime)+'至'+P.long2Date(item.endTime) + '</td>';
            // html += '<td title="' + item.manageRate + '" >' + item.manageRate + '</td>';
            // html += '<td title="' + item.interestRate + '" >' + item.interestRate + '</td>';
            html += '<td title="' + PF.formatMoney(item.totalBalance) + '" >' + PF.formatMoney(item.totalBalance) + '</td>';
            html += '<td title="' + PF.formatMoney(item.needReturnBalance) + '" >' + PF.formatMoney(item.needReturnBalance) + '</td>';
            html += '<td title="' + PF.formatMoney(item.remainingBalance) + '" >' + PF.formatMoney(item.remainingBalance) + '</td>';
            // html += '<td title="' + PF.formatMoney(item.returnedBalance) + '" >' + PF.formatMoney(item.returnedBalance) + '</td>';
            // html += '<td title="' + PF.formatMoney(item.needReturnBalance) + '" >' + PF.formatMoney(item.needReturnBalance) + '</td>';
            html += '<td title="' + showStatus(item.status) + '" >' + showStatus(item.status)+ '</td>';
            // var json=JSON.stringify(item);
            html += "<td><ul class='new_icon_pic'>";

            html += "<li onclick='toCompanyContractUpdate(" + item.id + ",4)'><img src='assets/imgs/operate/icon_view.png'/><span>查看</span></li>";
            if (item.status == 0 && "${sessionUser.authMap.COUNTERPARTY_ADD}" == "true") {
                html += "<li onclick='toCompanyContractUpdate(" + item.id + ",2)'><img src='assets/imgs/operate/icon_edit.png'/><span>编辑</span></li>";
            }

            if (item.status == 0 && "${sessionUser.authMap.COMPANY_CONTRACT_REVIEW}" == "true") {
                html += "<li onclick='toCompanyContractReview(" + item.id + ")'><img src='assets/imgs/operate/icon_review.png'/><span>审核</span></li>";
            }

            if ("${sessionUser.authMap.COUNTERPARTY_LIST}" == "true") {
                html += "<li onclick='toCounterpartyList(" + item.companyBorrowerId + ")'><img src='assets/imgs/operate/icon_counterparty.png'><span>交易对手</span></li>";
            }
            html += "</ul></td></tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.companyContractForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }

    //新增或修改弹窗
    function editCompanyContract(roleId, roleName,json) {
        var item=jQuery.parseJSON(json)
        ajaxGet("companyContract/findAllParentList.json", function (resp) {
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
    function deleteCompanyContract(roleId) {
        selfConfirm("你确定要删除该配置吗!",function () {
            var params = {"companyContractId" : roleId};
            ajaxPost("companyContract/delete.json", params, function (resp) {
                if (resp.success) {
                    selfAlert("保存成功!",function () {
                        layer.closeAll();
                        $("#page").val($("span.on").html());
                        searchCompanyContract();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        });
    }

    //保存
    function saveOrUpdate() {
        var formData = P.formData(document.editCompanyContractForm);
        if (P.isNullOrEmptyString(formData.configKey)) {
            selfAlert("请填写配置key名称!");
            return false;
        }
        ajaxPost("companyContract/saveOrUpdate.json", formData, function (resp) {
            if (resp.success) {
                selfAlert("保存成功!",function () {
                    layer.closeAll();
                    $("#page").val($("span.on").html());
                    searchCompanyContract();
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }

    /**
     * 跳转到客户-新增客户合同
     */
    function toCompanyContractDetail(){
        Page.clickBtnToPage("companyContract/companyContractDetail.html?from=2","新增客户合同");
    }

    /**
     * 跳转到客户-修改客户合同
     */
    function toCompanyContractUpdate(id,todo){
        var info="查看客户合同";
        if(todo==2){
            info="修改客户合同";
        }
        Page.clickBtnToPage("companyContract/companyContractUpdate.html?id="+id+"&todo="+todo,info);
    }

    /**
     * 跳转到客户-修改客户合同
     */
    function toCompanyContractReview(id){
        Page.clickBtnToPage("companyContract/companyContractReview.html?id="+id,"审核客户合同");
    }
    /**
     * 跳转到项目-计划列表
     */
    function toCounterpartyList(companyBorrowerId){
        Page.clickBtnToPage("counterparty/counterpartyList.html?companyBorrowerId="+companyBorrowerId,"交易对手");
    }


    /*保留小数（四舍五入）data:要保留的数，val:保留的位数*/
    function ToFixed(data,val){
        var numbers = '';
        // 保留几位小数后面添加几个0
        for (var i = 0; i < val; i++) {
            numbers += '0';
        }
        var s = 1 + numbers;
        // 如果是整数需要添加后面的0
        var spot = "." + numbers;
        // Math.round四舍五入
        //  parseFloat() 函数可解析一个字符串，并返回一个浮点数。
        var value = Math.round(parseFloat(data) * s) / s;
        // 从小数点后面进行分割
        var d = value.toString().split(".");
        if (d.length == 1) {
            value = value.toString() + spot;
            return value;
        }
        if (d.length > 1) {
            if (d[1].length < 2) {
                value = value.toString() + "0";
            }
            return value;
        }
    }
</script>
