<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>

    body{overflow-y:auto !important;}
    .box{ position: relative;overflow: hidden;    width: 100%;  background: white;  border: 1px solid #e6e6ef;}
    .scroll-content{ position: absolute; left: 0; top: 0; width: 100%;}
    .scroll-bg{ display: none; width: 5px; height: 100%; border-left: 1px solid #e1e1e1; border-right: none; border-top: none; border-bottom: none; background-color: #f2f2f2; position: absolute; _zoom: 1; top: 0; right: 0; cursor: pointer;}
    .scroll-bg:hover{ border-color:#bfbfbf; background-color:#a1a1a1}
    .scroll-bar{z-index: 20; position: absolute; width: 5px; height: 15px;border-radius: 3px; background:rgba(0,0,0,.7); overflow: hidden;left: 0px; cursor: pointer;}
    .newTable table{    font-size: 12px;color: #333333;background: white;border-width: 1px;border-color: #dedede;border-collapse: collapse;}
    .newTable table td{
        color: #555;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
        height: 36px;
        font-size: 12px;
        border-width: 1px;
        line-height: 36px;
        text-indent: 14px;
        border-style: solid;
        border-color: #dddddd;
    }
    #historyUl li:last-child{padding-bottom: 15px;}
    .newTable i{padding-right: 5px;font-size: 15px;color: #252525;}
    .carMessagesText p{padding-top: 14px;}
    .carMessagesText p span{display: inline-block;width: 90px;font-size: 15px;}
    .carMessagesText span{color: black;}
    .newTable p a{    display: inline-block;  width: 100px;  font-size: 16px !important;}
    @media screen and (max-width: 1330px) and (min-width: 100px){
        .carMessagesLeft{width: 100% !important;border-bottom:1px solid #ccc}
        .transportContainer .carMessages{border:none;height: 300px;}
        .carMessagesText{border:none !important;}
    }
</style>
<!--运输合同 start-->
<div class="source-contract" style="display:none;" id="show_contract">
    <div class="SetOut" style="text-align: center;">
        <iframe id="contractIframe" style="overflow-y: auto;width: 796px;height:500px;border:none;" src=""></iframe>
    </div>
</div>
<!--运输合同 end-->
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>跟单作业</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>查看运单详情</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>
<!--顶部导航 end-->
<div class="box" id="box" style="width:100%;overflow: auto;min-width:950px;">
    <div class="scroll-content">
        <div class="publicMainContainerBox publicMainContainerBoxs">
            <!--导航-->
            <div class="newPublicHeader">
                <span><span style="color: #7c8792;padding-left: 0px !important;"></span>运单详情</span>
                <div class="publicDetailsModuleReturnBtn return returnProject">返回</div>
                <input type="hidden" name="projectName" id="invoiceProjectName" value="">
            </div>
            <!--主体内容-->
            <div class="transportContainer" >
                <%--<h4>运单详情</h4>--%>
                <div class="clear"></div>
                <!--运输状态-->
                <p class="waybillMessage" style="position: relative;margin-left: 20px;top:15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">运单详情</a>

                    <div class="transportStatus">

                        <div class="transportStep" style="padding-left:30px">
                <p>
                    <a><i style="padding-left: 5px;font-size: 15px;padding-right: 10px;">订单号：${waybill.waybillNo }</i> ${departureCity }&nbsp${departurePoint }</a><img src="newassets/imgs/icon_new_arrow.png"><a>${arrivalCity }&nbsp${arrivalPoint }</a>
                </p>
                <ul>
                    <li><img src="newassets/imgs/icon_status01.png">
                        <p><a >计划生成</a></p>
                        <p><fmt:formatDate value="${program.createTime }" pattern="yyyy-MM-dd"/></p>
                        <p><fmt:formatDate value="${program.createTime }" pattern="HH:mm:ss"/></p>
                    </li>
                    <li style="width:auto;">
                        <img class="statusArrow" src="newassets/imgs/status_arrow.png">
                    </li>

                    <!-- 已收货 or 已完成 -->
                        <li><img src="newassets/imgs/icon_status02.png">
                            <p><a>生成运单</a></p>
                            <p><fmt:formatDate value="${waybill.createTime }" pattern="yyyy-MM-dd"/></p>
                            <p><fmt:formatDate value="${waybill.createTime }" pattern="HH:mm:ss"/></p>
                        </li>
                        <li style="width:auto;"><img class="statusArrow" src="newassets/imgs/status_arrow.png"></li>
                        <li><img src="newassets/imgs/icon_status03_1.png">
                            <p><a>开始运输</a></p>
                            <p><fmt:formatDate value="${waybill.loadingTime }" pattern="yyyy-MM-dd"/></p>
                            <p><fmt:formatDate value="${waybill.loadingTime }" pattern="HH:mm:ss"/></p>
                        </li>
                        <li style="width:auto"><img class="statusArrow" src="newassets/imgs/status_arrow.png"></li>
                        <li><img src="newassets/imgs/icon_status05_3.png">
                            <p><a>已完成</a></p>
                            <p><fmt:formatDate value="${waybill.receiveTime }" pattern="yyyy-MM-dd"/></p>
                            <p><fmt:formatDate value="${waybill.receiveTime }" pattern="HH:mm:ss"/></p>
                        </li>
                </ul>
            </div>
        </div>
        <!--车辆与货物信息-->
        <div class="carMessages" style="padding-bottom: 70px;">
            <div  class="carMessagesLeft" style="width: 50%;">
                <div class="carMessagesText" style="border-right:1px solid #dddddd;">
                    <p><img src="newassets/imgs/icon_chart_line.png"><a>运单信息</a></p>
                    <div class="newTable" style="float: left;margin-left: 30px;width: 100%;">
                        <p>
                            <span>车辆信息：</span>
                            <i>${waybill.licensePlateNo }</i>
                            <i>类型${waybill.carType }</i>
                            <i>车轴${waybill.axleNum }</i>
                            <i>车长${waybill.carLength }</i>
                            <i>核载${waybill.cargoTonnage }</i>
                        </p>
                        <p>
                            <span>司机信息：</span>
                            <i>${waybill.driverName }</i>
                            <i>${waybill.driverMobileNo }</i>
                        </p>
                        <%--<p>--%>
                            <%--<span>收款人：</span>--%>
                            <%--<i>${waybill.acceptOwner}</i>--%>
                        <%--</p>--%>
                        <p>
                            <span style="float: left;">货物名称：</span>
                            <i title="${waybill.cargoType}" style="width: 300px;display: inline-block;float: left;margin-top: 2px;">${waybill.cargoType}</i>
                        </p>
                        <div class="clear"></div>
                        <p>
                            <span>行程轨迹：</span>
                            <i style="color: #017ff8;text-decoration: underline;cursor:pointer;" onclick="showWaybillRoute('${waybill.id}')">查看行程</i>
                        </p>

                        <%--<p>
                            <span style="vertical-align: top;">评价：</span>
                            <c:if test="${not empty rating}">
                                <c:choose>
                                    <c:when test="${rating.ratingLevel eq 5}">
                                        <i style="vertical-align: top;"><img style="vertical-align: top;" src="newassets/imgs/icon_star05.png"></i>
                                    </c:when>
                                    <c:when test="${rating.ratingLevel eq 4}">
                                        <i style="vertical-align: top;"><img style="vertical-align: top;" src="newassets/imgs/icon_star04.png"></i>
                                    </c:when>
                                    <c:when test="${rating.ratingLevel eq 3}">
                                        <i style="vertical-align: top;"><img style="vertical-align: top;" src="newassets/imgs/icon_star03.png"></i>
                                    </c:when>
                                    <c:when test="${rating.ratingLevel eq 2}">
                                        <i style="vertical-align: top;"><img style="vertical-align: top;" src="newassets/imgs/icon_star02.png"></i>
                                    </c:when>
                                    <c:when test="${rating.ratingLevel eq 1}">
                                        <i style="vertical-align: top;"><img style="vertical-align: top;" src="newassets/imgs/icon_star01.png"></i>
                                    </c:when>
                                </c:choose>
                                <i style="display: inline-block;width: 50%;font-size:14px;" title="${rating.ratingContent}">${rating.ratingContent}</i>
                            </c:if>
                            <c:if test="${empty rating}">
                                <i>未评</i>
                            </c:if>
                        </p>--%>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>

            <div id="" class="carMessagesLeft" style="width: 35%;">
                <div class="carMessagesText" style="padding-left: 10px;">
                    <p><img src="newassets/imgs/icon_chart_line.png"><a>其他信息</a></p>
                    <div class="newTable" style="margin-left: 30px">
                        <p>
                            <span>项目名称：</span>
                            <i>${waybill.projectName}</i>
                        </p>
                        <p>
                            <span>计划名称：</span>
                            <i>${waybill.programName }</i>
                        </p>
                        <p>
                            <span>发货方：</span>
                            <i>${project.sendCompanyName}</i>
                        </p>
                        <p>
                            <span>收货方：</span>
                            <i>${project.consigneeCompanyName}</i>
                        </p>
                        <p>
                            <span>运输合同：</span>
                            <c:if test="${empty waybill.pdfPath}">
                                <i style="color: #017ff8;text-decoration: underline;">未签订</i>
                            </c:if>
                            <c:if test="${not empty waybill.pdfPath }">
                                <i style="color: #017ff8;text-decoration: underline;" onclick="showContract('${waybill.pdfPath}')">已签订</i>
                            </c:if>
                        </p>
                        <p>
                            <span>运输备注：</span>
                            <i>${waybill.remark}</i>
                        </p>
                    </div>

                </div>
            </div>

            <div id="zhushi">

            </div>
        </div>
        <div class="carMessagesLeft" style="width: 100%;">
            <div class="clear"></div>
            <div class="carMessagesText" style="border-bottom:1px solid #dddddd;padding-bottom: 30px;">
                <p><img src="newassets/imgs/icon_chart_line.png"><a>单据信息</a></p>
                <div class="newTable" style="float: left;margin-left: 30px;width: 100%;">
                    <c:if test="${waybill.unit eq 1}">
                        <p>
                            <span>发货皮重：</span>
                            <a><fmt:formatNumber pattern="#.###" value="${waybill.tareWeightBySend}"/><i class="unitName"></i></a>
                            <span style="padding-left: 20px;">发货毛重：</span>
                            <a><fmt:formatNumber pattern="#.###" value="${waybill.grossWeightBySend}"/><i class="unitName"></i></a>
                            <span style="padding-left: 20px;">发货净重：</span>
                            <a><fmt:formatNumber pattern="#.###" value="${waybill.netWeightBySend}"/><i class="unitName"></i></a>
                        </p>
                        <p>
                            <span>收货皮重：</span>
                            <a><fmt:formatNumber pattern="#.###" value="${waybill.receiveTareWeight}"/><i class="unitName"></i></a>
                            <span style="padding-left: 20px;">收货毛重：</span>
                            <a><fmt:formatNumber pattern="#.###" value="${waybill.receiveGrossWeight}"/><i class="unitName"></i></a>
                            <span style="padding-left: 20px;">收货净重：</span>
                            <a><fmt:formatNumber pattern="#.###" value="${waybill.receiveNetWeight}"/><i class="unitName"></i></a>
                        </p>
                    </c:if>
                    <c:if test="${waybill.unit ne 1}">
                        <p>
                            <span>发货数量：</span>
                            <a><fmt:formatNumber pattern="#.###" value="${waybill.netWeightBySend}"/><i class="unitName"></i></a>
                        </p>
                        <p>
                            <span>收货数量：</span>
                            <a><fmt:formatNumber pattern="#.###" value="${waybill.receiveNetWeight}"/><i class="unitName"></i></a>
                        </p>
                    </c:if>
                    <p>
                        <span>结算金额：</span>
                        <i><c:if test="${empty waybill or empty waybill.invoiceMoney}">0.00</c:if><c:if test="${not empty waybill and not empty waybill.invoiceMoney}"><fmt:formatNumber value="${waybill.invoiceMoney}" pattern="#,##0.00"/> </c:if>元</i>
                    </p>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <!--操作历史-->
        <div class="boxHistory"style="margin-left: 20px;clear: both;">
            <div class="publicDetailsModuleContainerHeader">
                <img src="newassets/imgs/icon_chart_line.png">
                <span style="font-size: 18px">操作历史</span>
            </div>
            <div class="boxHistory">
                <div class="boxHistoryList" style="overflow-y: auto;width: 50%;margin-bottom: 20px;">
                    <ul id="historyUl"> </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="scroll-bg">
    <div class="scroll-bar"></div>
</div>
</div>

<div class="see waybillDetails"> </div>

<script type="text/javascript">
    $(document).ready(function(){
        Page.showPageTitleBindReturnUrl();

        $(".unitName").text(P.cargoNumNo['${waybill.unit}']);

        ajaxLoadBillHistory(${waybill.id});
    });

    initReady();

    <%--ajaxLoadBillHistory(${waybill.id});--%>

    function strToDatetime(str) {
        var newstr = "";
        var tmp = str.substring(0, 4);
        newstr += tmp + "-";
        tmp = str.substring(4, 6);
        newstr += tmp + "-";
        tmp = str.substring(6, 8);
        newstr += tmp + " ";
        tmp = str.substring(8, 10);
        newstr += tmp + ":";
        tmp = str.substring(10, 12);
        newstr += tmp + ":";
        tmp = str.substring(12, 14);
        newstr += tmp;
        return newstr;
    }
    function ajaxLoadBillHistory(){
        ajaxGet("waybill/listWaybillOperationHistory/${waybill.id}.json", function(resp){
            if(!resp.success){
                selfAlert(resp.message);
                return;
            }
            if(resp.data && resp.data.length > 0){
                var list = resp.data;
                var html = "";
                for(var i=0;i<list.length;i++){
                    var item = list[i];
                    html += "<li><span><a>●</a>"+P.long2Datetime(item.handleTime);
                    html += "</span><p>" + item.handleDesc + "</p></li>";
                }
                $("#historyUl").html(html);
            }else{
                $("#historyUl").html("<li>暂无历史记录</li>");
            }

        });
    }
    /*********************   add by sj 2017.02.09  start*********************/

    //公用布局模块方法调用
    function showWaybillRoute(waybillId) {
        var url= "waybill/waybillRouteInfo/" + waybillId + ".json";
        //页面层
        ajaxGet(url, function(resp) {
            if(resp != '' && resp != null){
                layer.open({
                               title: ['查看轨迹详情', 'font-size:16px;color:white;background-color:#0070db;'],
                               type: 1,
                               area: ['1430px', '100%'],
                               content: resp,
                               end:function(){
                                   $(".publicDetailsModuleReturnBtn ").show();
                               }
                           });
                $(".publicDetailsModuleReturnBtn ").hide();
                $("#heightPage").css("height","auto");

            }
        });
    }

    /**
     * 显示合同或调度单弹窗
     * @param type
     * @param waybillId
     */
    function showContract( pdfPath) {
        pdfPath = pdfPath.replace("http://img.", "https://img.");   //测试环境
        pdfPath = pdfPath.replace("http://dev.", "https://dev.");   //正式环境

        var html ='<iframe src="'+pdfPath+'" width="100%" height="100%"></iframe>';
        layer.open({
                       title: ['查看合同', 'font-size:16px;color:white;background-color:#0070db;'],
                       type: 1,
                       area: ['950px', '100%'],
                       content: html
                   });
    }
</script>
