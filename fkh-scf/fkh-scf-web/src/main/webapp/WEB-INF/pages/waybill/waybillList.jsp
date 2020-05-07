<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>运单管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil span{margin-left: 30px;}
    .configOil span:first-child{margin-left: 0px;font-size: 14px;}
    .confirmAssessResult{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
    #waybillForm table {width: 300px;line-height: 22px;}
    .newPublicTable table td{line-height: 22px !important; }
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
    <%--    <ul>--%>
    <%--        <li>--%>
    <%--            <c:if test="${subjectClaimsOrderId==null&&creditApplyId==null}">--%>
    <%--                <a>运单管理</a>--%>
    <%--            </c:if>--%>
    <%--            <c:if test="${subjectClaimsOrderId!=null}">--%>
    <%--                <a>用款申请</a>--%>
    <%--                <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
    <%--                <a>已转让运单</a>--%>
    <%--            </c:if>--%>
    <%--            <c:if test="${creditApplyId!=null}">--%>
    <%--                <a>用信申请管理</a>--%>
    <%--                <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
    <%--                <a>用信申请运单</a>--%>
    <%--            </c:if>--%>
    <%--        </li>--%>
    <%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form action="waybill/listPage.json" id="searchForm" name="searchForm" onsubmit="return searchReviewInvoices();" autocomplete="off">
            <input type="hidden" id="isTransferred" name="isTransferred" value="${isTransferred}">
            <input type="hidden" id="projectId" name="projectId" value="${projectId}">
            <input type="hidden" id="creditApplyId" name="creditApplyId" value="${creditApplyId}"/>
            <input type="hidden" id="subjectClaimsOrderId" name="subjectClaimsOrderId" value="${subjectClaimsOrderId}"/>
            <%--<select id="searchProjectId" name="projectId" class="newPublicInput publicSwitchPlanProject" style="width: 10%;">--%>
            <%--<option value="">请选择项目</option>--%>
            <%--</select>--%>
            <input id="searchWaybillNo" name="waybillNo" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入运单号" style="width: 10%;">
            <select id="searchTimeType" name="searchTimeType" class="newPublicInput publicSwitchPlanProject" style="width: 10%;">
                <option value="1">按派车时间</option>
                <option value="2">按发货时间</option>
                <option value="3">按收货时间</option>
                <option value="4">按复核时间</option>
            </select>
            <input type="hidden" id="pageNo" name="pageNo" value="1">
            <input style="width: 12%;margin-left: 0px;" class="newPublicInput newPublicInputOne NewTimeInput" type="text" id="beginTime" name="beginTime" readonly="readonly"  placeholder="请选择开始时间">
            <input style="width: 12%;margin-left: 0px;" class="newPublicInput newPublicInputOne NewTimeInput" type="text" id="overTime" name="overTime" readonly="readonly"  placeholder="请选择结束时间">
            <div class="newPublicCustomSearchBtn" onclick="searchReviewInvoices()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
            <div class="newPublicCustomSearchBtn" onclick="exportExcel()" style="width: 110px;"><a>导出详细明细</a></div>
            <div class="newPublicCustomSearchBtn" onclick="exportExcelShort()" style="width: 110px;"><a>导出简要明细</a></div>
            <%--<div class="newPublicCustomResetBtn" onclick="document.searchForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>--%>
            <div class="publicDetailsModuleReturnBtn return"<c:if test="${hiddenReturn}"> hidden ="hidden"   </c:if>  >返回</div>

        </form>
    </div>

    <c:if test="${not empty counterpartyName}">
        <p style="margin-top: 10px;margin-bottom: -5px;}">${counterpartyName}</p>
    </c:if>
    <!--公用导航模块end-->
    <!--公用表格模块-->
    <div style="overflow-x: auto;overflow-y: auto;">
        <div class="newPublicTable" style="width: 1800px;">
            <table>
                <colgroup>
                    <col width="50">
                    <col width="150">
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col >
                    <col width="200">
                    <col width="460">
                </colgroup>
                <tbody>
                <tr>
                    <th class="newPublicTableCenter">状态</th>
                    <th class="newPublicTableCenter">运单号</th>
                    <th class="newPublicTableCenter">计划名称</th>
                    <th class="newPublicTableCenter">线路</th>
                    <th class="newPublicTableCenter">货物名称</th>
                    <th class="newPublicTableCenter">车辆信息</th>
                    <th class="newPublicTableCenter">驾驶员</th>
                    <th class="newPublicTableCenter">发货信息</th>
                    <th class="newPublicTableCenter">收货信息</th>
                    <th class="newPublicTableCenter">结算金额(元)</th>
                    <th class="newPublicTableCenter" id="waybillTime">派车时间</th>
                    <th class="newPublicTableCenter">操作</th>
                </tr>
                </tbody>
                <tbody id="projectList"></tbody>
            </table>
            <div id="pageBar" class="pageBar"></div>
            <div class="newPublicCustomGoodsBtn" onclick="generateSubjectClaimsOrder()" id="generateSubjectClaimsOrderBtn"><a>生成已转让应收账款</a></div>
        </div>
        <!--公用表格模块end-->
    </div>

</div>

<!--单据复核列表end-->

<!--编辑角色-->
<div class="publicContainerShow batchRecheckShowContainer configOilCompany" style="display:none;overflow: hidden;">
    <div class="remarksContainer newRemarksContainer">
        <form id="waybillForm" name="waybillForm">
            <table>
                <tr>
                    <td>承运人证照有效性</td>
                    <td style="color: #0BBF38">有效</td>
                </tr>
                <tr>
                    <td>运单数据完善程度</td>
                    <td style="color: #0BBF38">有效</td>
                </tr>
                <tr>
                    <td>运单轨迹点情况</td>
                    <td style="color: #0BBF38">有效</td>
                </tr>
                <tr>
                    <td>装卸货单据有效性</td>
                    <td style="color: #0BBF38">有效</td>
                </tr>
                <tr>
                    <td>结算数据有效性</td>
                    <td style="color: #0BBF38">有效</td>
                </tr>
                <tr>
                    <td>运价有效性</td>
                    <td style="color: #0BBF38">有效</td>
                </tr>
            </table>
            <div style="clear: both;"></div>
            <div>
                <span class="confirmAssessResult">确定</span>
            </div>
        </form>
    </div>
</div>

<!--运输合同 start-->
<div class="source-contract" style="display:none;" id="show_contract">
    <div class="SetOut" style="text-align: center;">
        <iframe id="contractIframe" style="overflow-y: auto;width: 796px;height:500px;border:none;" src=""></iframe>
    </div>
</div>
<!--运输合同 end-->
</body>
</html>

<script type="text/javascript">
    var waybillIds = "";
    var isOperationReview = "${type}" == "4" && "${readOnly}" == "false" && "${sessionUser.authMap.OPERATION_LOAN_WAYBILL_MARK}" == "true";
    laydate.render({elem: '#beginTime',type: 'datetime',theme: 'molv'});
    laydate.render({elem: '#overTime',type: 'datetime',theme: 'molv'});
    var totalPages;
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        Page.showPageTitleBindReturnUrl();
        //回显条件
        Page.initFormData(document.searchForm);
        loadProjectData();
        ajaxPost("waybill/listPage.json", P.formData(document.searchForm), function (resp) {
            parseInvoiceObjToHtml(resp);
        });

        $(".confirmAssessResult").click(function () {
            layer.closeAll();
        });

        //控制"生成应收款转让按钮"显示
        if (!P.isNullOrEmptyString($("#subjectClaimsOrderId").val()) || !P.isNullOrEmptyString($("#creditApplyId").val())  || ${sessionUser.authMap.SUBJECT_CLAIMS_WAYBILL_ADD == false}) {
            $("#generateSubjectClaimsOrderBtn").hide();
        }
    });

    //搜索项目列表
    function searchReviewInvoices() {
        var projectValidate = PF.projectValidate("keywords", "planName");
        if (projectValidate && document.searchForm) {
            var formData = P.formData(document.searchForm);
            ajaxPost("waybill/listPage.json", formData, function (resp) {
                parseInvoiceObjToHtml(resp);
            });
        }
        return false;
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var dataTableId = "projectList";
        var dataTablePageBarId = "pageBar";

        var html = "";
        var responseData = resp.data.data;
        var waybillIdList = responseData.data;
       var creditApplyStatus= resp.data.creditApplyStatus;
        waybillIds = "";
        if (waybillIdList != null && waybillIdList.length > 0) {
            $.each(waybillIdList, function (index, item) {
                waybillIds += item + ",";
            });
            waybillIds = waybillIds.substring(0, waybillIds.length - 1);
        }
        if (responseData.records.length <= 0) {
            html = "<tr><td colspan='12' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            html += convertWaybillStatus(item.waybillStatus);
            html += '<td class="newPublicTableCenter" title="' + item.waybillNo + '" >' + item.waybillNo + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.programName + '" >' + item.programName + '</td>';
            html += '<td class="newPublicTableCenter" title="'+ item.departureCity + "->" + item.arrivalCity + '" >' + item.departureCity + "->" + item.arrivalCity + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.cargoType + '" >' + item.cargoType + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.licensePlateNo + '" >' + item.licensePlateNo + '</td>';
            html += '<td class="newPublicTableCenter" title="' + item.driverMobileNo + " " + item.driverName + '" >' + item.driverMobileNo + " " + item.driverName + '</td>';

            if (item.unit == 1) {//只有吨有“皮毛净”
                html += '<td  >'
                        + "<p>毛重: "+ item.grossWeightBySend + P.cargoNumNo[item.unit] + "</p>"
                        + "<p>皮重: "+ item.tareWeightBySend + P.cargoNumNo[item.unit] + "</p>"
                        + "<p>净重: "+ item.netWeightBySend + P.cargoNumNo[item.unit] + "</p>";
                '</td>';
                html += '<td>'
                        + "<p>毛重: "+ item.receiveGrossWeight + P.cargoNumNo[item.unit] + "</p>"
                        + "<p>皮重: "+ item.receiveTareWeight + P.cargoNumNo[item.unit] + "</p>"
                        + "<p>净重: "+ item.receiveNetWeight + P.cargoNumNo[item.unit] + "</p>";
            } else {
                html += '<td  >'
                        + "<p>净重: "+ item.netWeightBySend + P.cargoNumNo[item.unit] + "</p>";
                '</td>';
                html += '<td>'
                        + "<p>净重: "+ item.receiveNetWeight + P.cargoNumNo[item.unit] + "</p>";
            }
            '</td>';
            var invoiceMoney = P.isNullOrEmptyString(item.invoiceMoney) ? 0 : item.invoiceMoney;
            html += '<td class="newPublicTableCenter" title="' + PF.formatMoney(invoiceMoney) + '" >' + PF.formatMoney(invoiceMoney) + '</td>';
            var showTime = item.waybillCreateTime;
            $("#waybillTime").html("派车时间");
            if ($("#searchTimeType").val() == 2) {
                showTime = item.loadingTime;
                $("#waybillTime").html("发货时间");
            } else if ($("#searchTimeType").val() == 3) {
                showTime = item.receiveTime;
                $("#waybillTime").html("收货时间");
            }else if ($("#searchTimeType").val() == 4) {
                showTime = item.billPassTime;
                $("#waybillTime").html("复核时间");
            }
            html += '<td class="newPublicTableCenter" title="' + P.long2Datetime(showTime) + '" >' + P.long2Datetime(showTime) + '</td>';
            html += "<td><ul class='new_icon_pic'><li onclick='turnToDetail(" + item.id+")'><img src='assets/imgs/operate/icon_info.png'><span>详情</span></li>";
            html += "<li onclick='turnToWaybillRouteInfo(" + item.id+")'><img src='assets/imgs/operate/icon_gps.png'><span>行程</span></li>";
            html += "<li onclick='turnToBillDetail(" + item.id+")'><img src='assets/imgs/operate/icon_bill.png'><span>单据凭证</span></li>";
            html += '<li onclick="showContract(\''+item.pdfPath+'\')"><img src=\'assets/imgs/operate/icon_contract.png\'><span>运输合同</span></li>';
            html += '<li onclick="showCfcaContract(1,'+item.id+',\''+item.cfcaPdfPath+'\')"><img src=\'assets/imgs/operate/icon_cfca.png\'><span>CFCA存证</span></li>';
            // html += "<li onclick='viewAssessResult(" + item.id + ")'><span>评定结果</span></li>";
            if (isOperationReview && item.waybillStatus == 0&&creditApplyStatus==1) {
                html += "<li onclick='signExceptionWaybill(" + item.id + ")'><img src='assets/imgs/operate/icon_error.png'><span style='color:#fc5477;'>异常</span></li>";
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

    //转换标的债券id为状态
    function convertWaybillStatus(waybillStatus) {
        if (waybillStatus == -1) {
            return '<td title="异常" style="color:#ff371e">异常</td>';
        }else{
            return '<td title="正常" style="color:#2da50b">正常</td>';
        }
    }

    //跳转运单页面
    function turnToDetail(waybillId) {
        Page.clickBtnToPage("waybill/waybillDetail/"+waybillId+".html?&creditApplyId=${creditApplyId}&isOperationReview="+isOperationReview
            ,"查看运单详情");
        P.setGlobalForm(2);
        <%--ajaxGet("waybill/waybillDetail/"+waybillId+".html?&creditApplyId=${creditApplyId}&isOperationReview="+isOperationReview, function (resp) {--%>
        <%--    P.setGlobalForm(2);--%>
        <%--    $("#publicRightContainer").html(resp);--%>
        <%--});--%>
    }

    //跳转运输线路
    function turnToWaybillRouteInfo(waybillId) {
        Page.clickBtnToPage("waybill/waybillRouteInfo/"+waybillId+".html?creditApplyId=${creditApplyId}&isOperationReview="+isOperationReview,"查看行程")
        P.setGlobalForm(2);
        <%--ajaxGet("waybill/waybillRouteInfo/"+waybillId+".html?creditApplyId=${creditApplyId}&isOperationReview="+isOperationReview, function (resp) {--%>
        <%--    P.setGlobalForm(2);--%>
        <%--    $("#publicRightContainer").html(resp);--%>
        <%--});--%>
    }

    //跳转单据详情
    function turnToBillDetail(waybillId) {
        Page.clickBtnToPage("billDataDetail/"+waybillId+".html?creditApplyId=${creditApplyId}&isOperationReview="+isOperationReview,"查看单据详情")
        P.setGlobalForm(2);
        <%--ajaxGet("billDataDetail/"+waybillId+".html?creditApplyId=${creditApplyId}&isOperationReview="+isOperationReview, function (resp) {--%>
        <%--    P.setGlobalForm(2);--%>
        <%--    $("#publicRightContainer").html(resp);--%>
        <%--});--%>
    }

    //查看评定结果弹窗
    function viewAssessResult() {
        var objs = $(".configOilCompany");
        layer.open({
                       type: 1,
                       title: ["评定结果", 'font-size:16px;color:white;background-color:#0071db;'],
                       area: ['400px', '300px'],
                       closeBtn: 4,
                       content: objs
                   });
    }

    function generateSubjectClaimsOrder(){
        if (P.isNullOrEmptyString(waybillIds)) {
            selfAlert("暂无未生成应收账款转让的运单数据");
            return false;
        }

        selfConfirm("你确定要生成应收账款转让吗!",function () {
            var params = {
                waybillIds : waybillIds,
                projectId : "${projectId}"
            };
            ajaxPost("waybill/generateSubjectClaimsOrder.json", params, function (resp) {
                if (resp.success) {
                    selfAlert("应收账款已转让成功！",function () {
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

    /**
     * 显示合同
     * @param type
     * @param waybillId
     */
    function showContract(pdfPath) {
        if (!P.isNullOrEmptyString(pdfPath) && pdfPath != 'null') {
            // 电子签章的项目，查看电子合同
            pdfPath = pdfPath.replace(".pdf", ".png");
            pdfPath = pdfPath.replace("http://img.", "https://img.");   //测试环境
            pdfPath = pdfPath.replace("http://dev.", "https://dev.");   //正式环境
            var html = "<img src='"+pdfPath+"'>";
            layer.open({
                           title: ['查看合同详情', 'font-size:16px;color:white;background-color:#0070db;'],
                           type: 1,
                           area: ['950px', '100%'],
                           content: html
                       });
        } else {
            selfAlert("暂无合同!");
        }
    }

    /**
     * 显示合同或调度单弹窗
     * @param type
     * @param waybillId
     */
    function showCfcaContract(type, waybillId, pdfPath) {
        // if (pdfPath && pdfPath != 'null') {
        // 电子签章的项目，查看电子合同
        // pdfPath = pdfPath.replace(".pdf", ".png");
        pdfPath = pdfPath.replace("http://img.", "https://img.");   //测试环境
        pdfPath = pdfPath.replace("http://dev.", "https://dev.");   //正式环境

        var html ='<div style="overflow: hidden;width: 100%;height: 100%;"><iframe src="'+pdfPath+'" width="100%" height="100%"></iframe></div>';
        layer.open({
                       title: ['查看CFCA存证', 'font-size:16px;color:white;background-color:#0070db;'],
                       type: 1,
                       area: ['950px', '100%'],
                       content: html
                   });
        // }
        // else {
        //     // 不需要签章的项目查看合同
        //     ajaxGet("transporting/getContactOrDispatcherUrl.json?waybillId=" + waybillId + "&type=" + type, function (resp) {
        //         if (resp != '' && resp != null) {
        //             if (type == 1) {
        //                 $("#contractIframe").attr("src", resp.data.openUrl);
        //                 var htmlContainer = $("#show_contract");
        //                 PF.layerSelfAert("查看合同详情", "800", "610", htmlContainer);
        //             } else if (type == 2) {
        //                 $("#dispatcherIframe").attr("src", resp.data.openUrl);
        //                 var dispatcherContent = $("#show_dispathcer");
        //                 PF.layerSelfAert("查看调度单", "710", "400", dispatcherContent);
        //             }
        //         }
        //     });
        // }
    }
    /**
     * 加载项目信息
     */
    function loadProjectData() {
        var params = {
            page : 1,
            limit : 99999999
        };
        ajaxPost("project/page.json", params, function (resp) {
            if (resp.success) {
                layer.closeAll();
                var data = resp.data.records;
                if (data.length > 0) {
                    var html = '<option value="">请选择项目</option>';
                    $.each(data, function (index, item) {
                        html += '<option value="'+item.id+'">'+item.projectName+'</option>';
                    });
                    $("#searchProjectId").html(html);
                }
            } else {
                selfAlert(resp.message);
            }
        });
    }

    /**
     * 导出详情明细表格
     */
    function exportExcel(){
        var form=$("#searchForm").serialize();
        window.open("waybill/exportExcel.html?"+form);
    }

    /**
     * 导出精简明细表格
     */
    function exportExcelShort(){
        var form=$("#searchForm").serialize();
        window.open("waybill/exportExcelShort.html?"+form);
    }
    /**
     * 标记异常运单
     */
    function signExceptionWaybill(waybillId){
        selfConfirm("你确定标记该运单为异常运单吗!",function () {
            ajaxGet("waybill/signException/"+waybillId+".json", function (resp) {
                if (resp.success) {
                    selfAlert("操作成功！",function () {
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

    /**
     * 删除运单
     */
    function deleteWaybill(waybillId){
        selfConfirm("你确定删除该异常运单吗!",function () {
            ajaxGet("waybill/delete/"+waybillId+".json", function (resp) {
                if (resp.success) {
                    selfAlert("操作成功！",function () {
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

</script>
