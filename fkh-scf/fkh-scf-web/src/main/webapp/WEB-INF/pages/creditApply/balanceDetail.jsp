<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        .passButton{width: 120px;height: 30px;border-radius: 6px;background-color: #169BD5;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
        .configOil{margin-top: 25px;}
        .configOil span{margin-left: 30px;}
        .configOil span:first-child{margin-left: 0px;font-size: 14px;}
        .contract {
            padding-top: 15px;
        }

        .contract span {
            display: inline-block;
            width: 180px;
            text-align: right;
            padding-right: 10px;
        }

        .contract .newPublicInput {
            width: 270px;
        }

        #contractList li {
            text-align: center;
            max-width: 140px;
            margin-top: 15px;
        }

        #contractList li p {
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
            width: 105px;
        }

        #contractList li:nth-child(5) {
            margin-top: 15px !important;
        }

        /*#contractList li:first-child{margin-left: 0px !important;}*/
        .lineRoad p {
            padding-top: 15px;
        }

        .lineRoad span {
            display: inline-block;
            width: 75px;
            text-indent: 20px;
        }



        .uploadAgreement span {
            display: inline-block;
            width: 75px;
            text-indent: 20px;
            vertical-align: top;
        }


        .saveLineRoadMsg input {
            margin-top: 15px;
            margin-left: 60px;
            margin-bottom: 15px;
        }

        #uploadReceiptDialog input[type="text"] {
            width: 180px;
        }


        .uploadListFile label {
            width: 130px;
            text-align: right;
            display: inline-block;
        }


        .uploadListFileLabel p label {
            width: 130px;
            text-align: right;
            display: inline-block;
        }


        #contractLineList span {
            text-decoration: underline;
        }

        .historyListMsg li {
            width: 100%;
            height: 80px;
            border-bottom: 1px solid #ededed;
        }


        .historyListMsgMain p {
            text-align: justify;
            line-height: 20px;
            margin-top: 10px;
        }


        .operationMsgUser a {
            color: #a4a4a4;
        }

        .operationMsgUser span {
            color: #0071dc;
        }


        @media screen and (min-width: 1201px) and (max-width: 1420px) {
            .contract .newPublicInput {
                width: 200px;
            }
        }

        @media screen and (min-width: 10px) and (max-width: 1200px) {
            .contract .newPublicInput {
                width: 160px;
            }
        }

        @media screen and (min-width: 10px) and (max-width: 1430px) {
            #contractList li {
                margin-left: 45px !important;
            }
        }

        .contract div p {
            padding-top: 10px;
        }

        .contract p i {
            display: inline-block;
            width: 70px;
            text-align: right;
        }


        .projectMsg p {
            padding-top: 5px;
        }

        .projectMsg p i {
            display: inline-block;
            width: 70px;
            text-align: right;
        }

        @media screen and (min-width: 1201px) and (max-width: 1420px) {
            .contract .newPublicInput {
                width: 200px;
            }
        }

        @media screen and (min-width: 10px) and (max-width: 1200px) {
            .contract .newPublicInput {
                width: 160px;
            }
        }

        @media screen and (min-width: 10px) and (max-width: 1430px) {
            #contractList li {
                margin-left: 45px !important;
            }
        }
    </style>
</head>
<!--结算详情页面-->
<div class="paymentPage">

    <!--顶部导航-->
    <div class="publicHeaderNav">
<%--        <ul>--%>
<%--            <li>--%>
<%--                <a href="javascript:;" class="returnPay">运营审核</a>--%>
<%--                <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--                <a href="javascript:;" class="returnChild">放款申请审核</a>--%>
<%--                <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--                <a href="javascript:;" class="returnChild">审核详情</a>--%>
<%--                <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--                <a href="javascript:;" class="returnChild">申请金额详情</a>--%>
<%--            </li>--%>
<%--        </ul>--%>
    </div>
    <!--顶部导航 end-->
    <!--确认支付主题内容-->
    <div class="public_container" id="new_public_height3"
         style="width: 100%;background: white; border: 1px solid #e6e6ef; padding-bottom: 20px;">
        <div class="publicMainContainerBox publicMainContainerBoxs">
            <!--头部公用-->
            <div class="newPublicHeader">
                <span>用款申请详情</span>
                <div class="publicDetailsModuleReturnBtn returnPro returnChild return">返回</div>
            </div>
            <form  id="applyBalanceForm" name="applyBalanceForm" read>
                <div class="contract">
                    <input type="text" id="creditApplyId" hidden="hidden" name="creditApplyId" value="${creditApplyId}" class="newPublicInput"
                           placeholder="id">
                    <span>放款申请编号</span>
                    <a id="creditApplyNo"></a>
                </div>
                <div class="contract">
                    <span>本次可提用融资额度</span>
                    <a id="canApplyBalance"></a>

                </div>
                <div class="contract">
                    <span>本次应收账款有效转让金额</span>
                    <a id="transferBalance"></a>
                </div>
                <div class="contract">
                    <span>本次申请融资额度</span>
                    <a id="applyBalance"></a>
                </div>
                <div class="contract">
                    <span>应收账款到期日</span>
                    <a id="dueDate"></a>

                </div>
                <div class="contract">
                    <span>融资利率</span>
                    <a id="interestRate"></a>
                </div>
                <div class="contract">
                    <span>保理管理费</span>
                    <a id="manageBalance"></a>
                </div>
                <div class="contract">
                    <span>应收账款转让详情</span>
                    <input  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="toSubjectClaimsOrderList()"
                            value="查看">
                </div>
                <div class="contract">
                    <span>转让确认书</span>
                    <input  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="showAnxinContract('${creditApply.pdfPath}','${creditApply.signPdfPath}')"
                            value="查看转让确认书">
                </div>
<%--                <div class="line_contract"></div>--%>
<%--                <p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">合同附件</a></p>--%>
<%--                <div class="" style="margin-top: 20px;margin-bottom: 40px;">--%>
<%--                    <ul id="contractList">--%>
<%--                        <c:forEach items="${attachment}" var="item">--%>
<%--                            <li style="margin-left: 50px;  display: inline-block;"><a href="${item.path}" target="_blank"><img src="newassets/imgs/icon_pdf.png"></a>--%>
<%--                                <p><a title="${item.name}">${item.name}</a></p>--%>
<%--                            </li>--%>
<%--                        </c:forEach>--%>
<%--                        <c:if test="${from==3}">--%>
<%--                            <c:if test="${empty attachment||attachment.size()<20}">--%>
<%--                                <li id="uploadPorjectContractButton" style="margin-left: 60px;  display: inline-block;">--%>
<%--                                    <div class="newTicket" style="margin-left: 20px;">--%>
<%--                                    </div>--%>
<%--                                </li>--%>
<%--                            </c:if>--%>
<%--                        </c:if>--%>
<%--                    </ul>--%>
<%--                </div>--%>
            </form>
        </div>
    </div>

    <!--审核弹出框-->

    <div class="publicContainerShow batchRecheckShowContainer reviewReasonContainer" style="display:none;overflow: hidden;">
        <div class="remarksContainer newRemarksContainer">
            <form id="reviewReasonForm" name="reviewReason">
                <input id="passStatus" name="passStatus" hidden="hidden" class="newPublicInput publicSwitchPlanProject" type="textarea" style="width: 10%;">
                <div class="configOil">
                    <input id="reviewReason" class="reviewReason" name="reviewReason" type="textarea" value=""/>
                </div>
                <div>
                    <span class="passButton" onclick="saveReviewReason();"></span>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    <c:if test="${from==3}">
    laydate.render({elem: '#dueDate',type: 'date',theme: 'molv'});
    </c:if>
    //本页url;
    var url ;
    //上页url
        var returnUrl;
        var repayTypeMap = {
            1:"先息后本，按月付息",
            2:"到期一次行还本付息",
            3:"等额本息",
            4:"等额本金"
        };

        $(document).ready(function () {

            //显示页面标题并绑定返回按钮的跳转地址
            Page.showPageTitleBindReturnUrl();
                $("#creditApplyNo").html(${creditApply.creditApplyNo});
                $("#applyBalance").html(PF.formatMoney(parseFloat(${creditApply.applyBalance})));
                $("#canApplyBalance").html(PF.formatMoney(parseFloat(${creditApply.canApplyBalance})));
                $("#transferBalance").html(PF.formatMoney(parseFloat(${creditApply.transferBalance})));
                $("#manageBalance").html(PF.formatMoney(parseFloat(${creditApply.manageBalance})));
                $("#dueDate").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${creditApply.dueDate}" type="both"/>');
                $("#interestRate").html(parseFloat(${interestRate})+"%");
            <%-- returnUrl='${returnUrl}';--%>
            <%--url='${url}';--%>
            $(".contract:input").attr("readonly","readonly")
        //公用布局模块方法调用
             initReady();
    });
    /**
     * 跳转到-标的债券列表
     */
    function toSubjectClaimsOrderList(){
        var creditApplyId =   $(":input[name='creditApplyId']").val();
        Page.clickBtnToPage("subjectClaimsOrder.html?counterpartyId=${counterpartyId}&creditApplyId="+creditApplyId,"查看应收账款转让详情")
        <%--var url='${url}';--%>
        <%--P.setReturnUrlForm("subjectClaimsOrder",url)--%>
        <%--containerShow("subjectClaimsOrder.html?creditApplyId="+creditApplyId);--%>
    }

    /**
     * 显示合同或调度单弹窗
     * @param type
     * @param waybillId
     */
    function showAnxinContract(pdfPath,signPdfPath) {
        // if (pdfPath && pdfPath != 'null') {
        // 电子签章的项目，查看电子合同
        // pdfPath = pdfPath.replace(".pdf", ".png");
        pdfPath=signPdfPath?signPdfPath:pdfPath;
        pdfPath = pdfPath.replace("http://img.", "https://img.");   //测试环境
        pdfPath = pdfPath.replace("http://dev.", "https://dev.");   //正式环境

        var html ='<div style="overflow: hidden;width: 100%;height: 100%;"><iframe src="'+pdfPath+'" width="100%" height="100%"></iframe></div>';
        layer.open({
                       title: ['转让确认书', 'font-size:16px;color:white;background-color:#0070db;'],
                       type: 1,
                       area: ['950px', '100%'],
                       content: html
                   });
    }
</script>
