<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
    .autoSumBoxList i {
        display: inline-block;
        width: 6px;
        height: 6px;
        border-radius: 6px;
        background:#000;
        position: relative;
        top: -3px;
    }
    .showPlanAlert span {
        display: inline-block;
        width: 80px;
        font-size: 14px;
        line-height: 30px;
        color: #444444;
        text-align: right;
    }
</style>
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>我的额度</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a class="return">计划列表</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>计划详情</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>
<div class="showPlanAlert" id="heightPage" style="overflow-y: auto; background: white; width: 100%; height: 349px;">
    <div class="newPublicHeader">
        <span style="font-size: 16px;padding-left: 30px;width: auto;" id="newPlanNameTextTitle">
            计划详情
        </span>
        <div class="publicDetailsModuleReturnBtn return">返回</div>
        <input type="hidden" id="changeRoleType" value="1">
    </div>
    <div class="showPlanAlertMain" style="margin-left: 1%;">
        <form id="showplanDetail" action="" name="showPlanForm" method="post">
            <div class="">
                <table style="display:inline-block;position: relative;">
                    <tr>
                        <td style="position: relative;line-height:45px;">
                            <p class="waybillMessage" style="position: relative;margin-left:0px;top:0px;padding-bottom: 5px;padding-top: 8px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 16px;padding-left: 10px;">基础信息</a></p>
                            <input type="hidden" id="planIdHidden"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>计划名称</span>
                            <input type="text" autocomplete="off" class="projectName publicTextMessage" value="${program.programName}">
                            <span>关联线路</span>
                            <c:if test="${empty program.lineName }">
                                <input type="text" autocomplete="off" class="lineName publicTextMessage" value="无线路">
                            </c:if>
                            <c:if test="${not empty program.lineName }">
                                <input type="text" autocomplete="off" class="lineName publicTextMessage" value="${program.lineName}">
                            </c:if>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>出发地</span>
                            <input type="text" autocomplete="off" class="departureCity publicTextMessage"  value="${program.departureCity}">
                            <span>装货地</span>
                            <input type="text" autocomplete="off" class="loadAddress publicTextMessage"  value="${program.loadAddress}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>目的地</span>
                            <input type="text" autocomplete="off" class="arrivalCity publicTextMessage"  value="${program.arrivalCity}">
                            <span>卸货地</span>
                            <input type="text" autocomplete="off" class="arrivalAddress publicTextMessage"  value="${program.arrivalAddress}">
                        </td>
                    </tr>
                </table>
                    <table>
                        <tr>
                            <td>
                                <p class="waybillMessage" style="position: relative;margin-left:0px;top:0px;padding-bottom: 5px;padding-top: 8px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 16px;padding-left: 10px;">计划信息</a></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>货物名称</span>
                                <input type="text" autocomplete="off" class="gradeNo publicTextMessage"  value="${program.modelType}">
                                <span>计划数量</span>
                                <input name="programNo" type="text" autocomplete="off"
                                       class="programNo publicTextMessage" value="${program.programNo}">
                                <span class="unitsInfo" style="width:0px;">${program.units}</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span>包装形式</span>
                                <input type="text" autocomplete="off" class="packagedForm publicTextMessage" value="${program.packagedForm}">
                            </td>
                        </tr>
<%--                        <tr>--%>
<%--                            <td>--%>
<%--                                <span>运输单价</span>--%>
<%--                                <input type="text" style="width:160px;" autocomplete="off" id="unitPrice"--%>
<%--                                       class="unitPrice publicTextMessage" name="unitPrice" placeholder="请填写运输单价"--%>
<%--                                       onkeyup="if(isNaN(value))execCommand('undo')"--%>
<%--                                       onafterpaste="if(isNaN(value))execCommand('undo')" maxlength="8" value="<fmt:formatNumber type="number" value="${program.unitPrice}" pattern="###,##0.00" maxFractionDigits="2" />">--%>
<%--                                <a id="unitPriceNT" style="padding-left: 4px;">元/${program.units}</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
                        <tr>
                            <td>
                                <span>计划周期</span>
                                <input name="programStartDateStr" style="width:150px;" type="text"
                                       class="planStartDate publicTextMessage" value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${program.programStartDate}" type="both"/>'>
                                至&nbsp;
                                <input name="programEndDateStr" style="width:150px;" type="text"
                                       class="planEndDate publicTextMessage"
                                value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${program.programEndDate}" type="both"/>'>

                            </td>
                        </tr>

                        <%--<tr>--%>
                            <%--<td>--%>
                                <%--<div class="new_ulListBox">--%>
                                    <%--<span >派车运量</span>--%>
                                    <%--<input name="freightVolume" id="freightVolume" value="${program.freightVolume}" autocomplete="off" type="text" style="width: 155px">--%>
                                    <%--<a id="freightVolumeUnits">${program.units}</a>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    </table>
            </div>
            <div class="clear"></div>
        </form>
    </div>
</div>
<div class="showAlert" style="display: none;">
    <table>
        <tbody>
        <tr>
            <td style="line-height: 5px;vertical-align: initial;">
                <div class="planMsgAlert" style="    width: 100%;text-align: center; margin-top: 10px;margin-left: 80px;"></div>
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" style="display:block;margin:0 auto;margin-top: 30px;" class="newPublicAlertBtn new_public_blue_bg" value="确定" onclick="toViewPlanQrCode1()">
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script>
    var planId;
    var projectId;
    var contacter;
    $(document).ready(function(){
        Page.showPageTitleBindReturnUrl();
        <%--window.bindBackBtn("program/programList.html?projectId=${projectId}");--%>
        <%--showPlan(${planId});--%>
        $("#showplanDetail input").attr("readonly", "readonly");
    });
    /**
     * 显示 计划详情弹出框 并填充数据
     * 2017-04-12 16:02 dong4j modified
     * 添加 出发地 目的地
     * 去掉 抬头
     * 用 class 选择对象
     * @param id
     */
    function showPlan(id) {
        $("#planIdHidden").val(id);

        document.showPlanForm.reset();//清空数据
        $("#statisticsBar").attr("data", "0").css("width", "0%"); //重置统计条。
        //隐藏查看二维码按钮
        $("#showQrcodeBtn").hide();
        var title = "查看计划详情";
        var htmlContainer = $(".showPlanAlert");
        // 控制按钮显示，是否可用--start
        $("#showplanStartDate").attr("disabled", true);
        $("#showplanEndDate").attr("disabled", true);
        $("#carLengthBegin").attr("disabled", true);
        $("#carLengthEnd").attr("disabled", true);
        $("#carType").attr("disabled", true);
        //控制按钮显示，是否可用--end
        $("#planId").val(id);
        // showEdit();//不需要修改
        // PF.layerSelfAert(title, "720", "580", htmlContainer);
        ajaxGet("program/" + id, function (resp) {
            if (validateAjaxSuccess(resp)) {
                if (resp.data.isShowQrCode == 1) {
                    $("#showQrcodeBtn").show();
                }

                var project = resp.data.project;
                projectId = project.projectId;
                planId = project.id;

                contacter = project.contacter;

                $(".departureCity").val(project.departureCity);
                $(".loadAddress").val(project.loadAddressValue);
                $(".arrivalCity").val(project.arrivalCity);
                $(".arrivalAddress").val(project.arrivalAddressValue);
                $(".projectName").val(project.programName);
                if (project.lineName != null && project.lineName != "") {
                    $(".lineName").val(project.lineName);
                } else {
                    $(".lineName").val("无线路");
                }

                if(project.messageSwitch ==1){
                    $("#checkbox").attr('src','newassets/imgs/icon_yes.png');
                    $("#checkbox").data('istrue',false);
                    $("#messageSwitch").val(1);
                }
                <c:if test="${projectMaterialType==0 }">
                $(".gradeNo").val(project.modelTypeValue);
                //由于查看计划货物品类文字可能过多添加title提示文字
                $(".gradeNo").attr("title", project.modelTypeValue);
                $(".packagedForm").val(project.packagedFormValue);
                $(".cargoPrice").val(project.cargoPrice === 0 ? "" : Number(project.cargoPrice).toFixed(2));
                $(".cargoPrice").removeAttr('placeholder');
                $(".programNo").val(project.programNo);
                $(".planEndDate").val(P.long2Datetime(project.programEndDate));
                $(".planStartDate").val(P.long2Datetime(project.programStartDate));
                $(".totalCargo").text(project.totalCargo);
                $(".countWaybillCar").text(project.countWaybillCar);
                $(".unitPrice").val(P.numbers(project.unitPrice,2));
                $("#freightVolume").val(Number(project.freightVolume).toFixed(2));

                //司机填单
                var driverFill = project.driverFill;
                if(driverFill == 0){
                    $('#driverFill_pic').attr('src','newassets/imgs/icon_no.png');
                }else if(driverFill == 1){
                    $('#driverFill_pic').attr('src','newassets/imgs/icon_yes.png');
                }

                var tempUnits = project.units === null ? "吨" : project.units;
                $(".unitsInfo").text(tempUnits);
                $("#unitPriceNT").html('元/' + tempUnits);
                $("#freightVolumeUnits").text(tempUnits);
                if(tempUnits == "吨") {
                    $("#allowValueText").text("Kg");
                }else {
                    $("#allowValueText").html(tempUnits);
                }
                var programNo = parseFloat($(".programNo").val());
                var totalCargo = parseFloat($(".totalCargo").text());

                //剩余数不能小于0； update by sj 2017.02.10
                if (tempUnits == "吨") {
                    $(".remainCargo").text(programNo > totalCargo ? (programNo - totalCargo).toFixed(2) : 0);
                } else {
                    $(".remainCargo").text(programNo > totalCargo ? (programNo - totalCargo) : 0);
                }

                var number = programNo > 0 ? parseInt((totalCargo / programNo) * 100) : 100;
                number > 100 ? number = 100 : number;
                $("#statisticsBar").attr("data", number);
                $("#textstatisticText").text(number + "%");
                //修改BUG动态计算统计信息数字；设置marginRight位置xu
                var textstatisticText = parseInt($("#textstatisticText").text());
                if (textstatisticText.toString().length == 3) {
                    $("#textstatisticText").css("marginRight", "15px");
                } else if (textstatisticText.toString().length == 5) {
                    $("#textstatisticText").css("marginRight", "10px");
                } else {
                    $("#textstatisticText").css("marginRight", "25px");
                }
                //显示当前计划信息。统计条。
                var a = parseInt($(".chartsOnes").attr("data"));
                $(".strip").html(a + '%');
                $(".charts").each(function (i, item) {
                    var a = parseInt($(item).attr("data"));
                    $(item).animate({width: a + "%"}, 2000);
                });

                $("#carType").html("<option>"+project.carType+"</option>");
                $("#cargoDesc").val(project.cargoDesc);
                convertCarLength(project.carLength);

                var poundKey = project.poundKey;
                var upType = project.upType;
                var isDeductPound = project.isDeductPound;
                $("#poundKey").val(poundKey);
                if(poundKey == 3){//不扣磅差
                    $("#poundType").text("不扣磅差");
                    $(".poundKey1").hide();
                    $(".poundKey2").hide();
                    $(".poundKey3").show();
                    if (project.poundValue == -1) {
                        $("#sendOrReceive").text("以发货方过磅信息为准");
                        $(".poundLesser").hide();
                    }else {
                        $("#sendOrReceive").text("以收货方过磅信息为准");
                        $(".poundLesser").show();
                        var poundLesser = project.poundLesser;
                        if (poundLesser == 0){
                            $("#poundLesser").text("以磅差选择方式为准");
                        }else if(poundLesser == 1){
                            $("#poundLesser").text("以较小过磅值为准");
                        }
                    }
                    $("#ladderPound").hide();
                }else if (poundKey == 1){//扣磅差比
                    $("#poundType").text("扣磅差比");
                    $(".poundKey1").show();
                    $(".poundKey2").hide();
                    $(".poundKey3").hide();
                    $("#poundKey1").val(project.poundValue);
                    $("#ladderPound").hide();
                }else if(poundKey == 2){//扣磅差值
                    $("#poundType").text("扣磅差值");
                    $(".poundKey1").hide();
                    $(".poundKey3").hide();
                    $(".poundKey2").show();
                    $("#ladderPound").hide();
                    $("#allowValue").val(project.poundValue);
                }else if (poundKey == 4){//不过磅
                    $("#poundType").text("不过磅");
                    $(".poundKey1").hide();
                    $(".poundKey3").hide();
                    $(".poundKey2").hide();
                    $("#ladderPound").hide();
                }else if (poundKey == 5){//扣磅差阶梯值
                    $("#poundType").text("扣磅差值");
                    $(".poundKey1").hide();
                    $(".poundKey3").hide();
                    $(".poundKey2").hide();
                    $(".poundKey5").show();
                    $("#ladderPound").show();
                    if (upType==1) {
                        $("#upType").text("以发货净重为准,");
                    }else {
                        $("#upType").text("以收货净重为准,");
                    }
                    if (isDeductPound == 0){
                        $("#isDeductPound").text("不扣磅差");
                    }else {
                        $("#isDeductPound").text("扣实际磅差");
                    }
                    var autoSumBoxListHtml="";
                    var downLevel = project.downLevel;
                    for(var i = 0;i<downLevel.length;i++){
                        var upperLimitStr = "";
                        if(downLevel[i].upperLimit == -1){
                            upperLimitStr = "无上限";
                        }else {
                            upperLimitStr = downLevel[i].upperLimit;
                        }
                        //亏吨以收货或发货为准
                        var computationBaseStr;
                        if(downLevel[i].computationBase == 1){
                            computationBaseStr = "以发货净重计算";
                        }else {
                            computationBaseStr = "以收货净重计算";
                        }
                        var poundKeyStr;
                        //扣磅差
                        if(downLevel[i].poundVaule == -2){
                            poundKeyStr = "不扣磅差";
                        }else if(downLevel[i].poundVaule == -1){
                            poundKeyStr = "扣实际磅差";
                        }else {
                            poundKeyStr = "扣固定磅差"+ downLevel[i].poundVaule + "Kg";
                        }
                        var isFineStr;
                        if(downLevel[i].fine == -1){//不罚款
                            isFineStr = "不罚罚款";
                        }else {
                            isFineStr = "要罚款" + downLevel[i].fine +"元/Kg";
                        }
                        autoSumBoxListHtml+="<li>\n" +
                            "<i></i>\n" +
                            "<a> 第" +
                            ""+TransformToChinese(i+1)+"" +
                            "阶梯:" +
                            " "+downLevel[i].lowerLimit+"" +
                            "Kg <  磅差  ≤ " +
                            ""+upperLimitStr+""+
                            "Kg：" +
                            ""+computationBaseStr+","+"" +
                            ""+poundKeyStr+","+"" +
                            ""+isFineStr+"" +
                            "</a>\n" +
                            "</li>";
                    }
                    $(".autoSumBoxList ul").html(autoSumBoxListHtml);
                }else if(poundKey == 6){
                    $("#poundType").text("扣磅差比");
                    $(".poundKey1").hide();
                    $(".poundKey3").hide();
                    $(".poundKey2").hide();
                    $(".poundKey5").show();
                    $("#ladderPound").show();
                    if (upType==1) {
                        $("#upType").text("以发货净重为准,");
                    }else {
                        $("#upType").text("以收货净重为准,");
                    }
                    if (isDeductPound == 0){
                        $("#isDeductPound").text("不扣磅差");
                    }else {
                        $("#isDeductPound").text("扣实际磅差");
                    }
                    var autoSumBoxListHtml="";
                    var downLevel = project.downLevel;
                    for(var i = 0;i<downLevel.length;i++){
                        var upperLimitStr = "";
                        if(downLevel[i].upperLimit == -1){
                            upperLimitStr = "无上限";
                        }else {
                            upperLimitStr = downLevel[i].upperLimit;
                        }
                        //亏吨以收货或发货为准
                        var computationBaseStr;
                        if(downLevel[i].computationBase == 1){
                            computationBaseStr = "以发货净重计算";
                        }else {
                            computationBaseStr = "以收货净重计算";
                        }
                        var poundKeyStr;
                        //扣磅差
                        if(downLevel[i].poundVaule == -2){
                            poundKeyStr = "不扣磅差";
                        }else if(downLevel[i].poundVaule == -1){
                            poundKeyStr = "扣实际磅差";
                        }else {
                            poundKeyStr = "扣固定磅差" + downLevel[i].poundVaule + "Kg";
                        }
                        var isFineStr;
                        if(downLevel[i].fine == -1){//不罚款
                            isFineStr = "不罚罚款";
                        }else {
                            isFineStr = "要罚款" + downLevel[i].fine +"元/Kg";
                        }
                        autoSumBoxListHtml+="<li>\n" +
                            "<i></i>\n" +
                            "<a> 第" +
                            ""+TransformToChinese(i+1)+"" +
                            "阶梯:" +
                            " "+downLevel[i].lowerLimit+"" +
                            "‰ <  磅差  ≤ " +
                            ""+upperLimitStr+""+
                            "‰：" +
                            ""+computationBaseStr+","+"" +
                            ""+poundKeyStr+","+"" +
                            ""+isFineStr+"" +
                            "</a>\n" +
                            "</li>";
                    }
                    $(".autoSumBoxList ul").html(autoSumBoxListHtml);
                }

                if (poundKey == "3") { //不扣磅差
                    $("#poundValueSelect").val(project.poundValue);
                    $("#poundValueSelect").show();
                    $("#poundValue").hide();
                    $("#poundTips").hide();
                    if (isSelf) { //无车隐藏以某一方为准
                        $("#poundValueSelect").hide();
                    } else { //非无车显示以某一方为准
                        $("#poundValueSelect").show();
                    }
                } else if (poundKey == 1 || poundKey == 2) { //扣磅差比或扣磅差值
                    var poundValue = project.poundValue;
                    $("#poundValue").val(poundValue);
                    if (tempUnits == "吨") {
                        if (poundKey == 1) {
                            $("#poundTips").html("‰(千分比)");
                        } else {
                            $("#poundTips").html("Kg");
                        }
                    } else if (tempUnits != "车") {
                        $("#poundTips").html(tempUnits);
                    }
                    $("#poundValue").show();
                    $("#poundTips").show();
                    $("#poundValueSelect").hide();
                } else if (poundKey == 5){//扣阶梯值磅差

                }else { //不过磅
                    $("#poundValue").val("");
                    $("#poundTips").html("");
                    $("#poundTips").hide();
                    $("#poundValue").hide();
                    $("#poundValueSelect").hide();
                }
                </c:if>
                <c:if test="${projectMaterialType==2 }">
                $("#earthWorkGoodType").val(project.modelTypeValue);
                $("#earthWorkGoodType").attr("title", project.modelTypeValue);
                $("#earthWorkUnitPrice").val(Number(project.unitPrice).toFixed(2));
                $("#earthWorkUnitPriceNT").html("元/" + project.units);
                </c:if>
                // $("#cargoNT").html(project.freight);
            } else {
            }
        })
    }

    function convertCarLength(val) {

        if (val == "不限") {
            $("#carLengthBegin").html('<option>不限</option>');
            $("#carLengthEnd").html('<option>不限</option>');
            // $("#carLengthEnd").val("不限");
        } else {

            // $("#carLengthBegin").val();
            // $("#carLengthEnd").val(val.split("~")[1]);
            $("#carLengthBegin").html('<option>'+val.split("~")[0]+'</option>');
            $("#carLengthEnd").html('<option>'+val.split("~")[1]+'</option>');

        }

    }

    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage-160 + "px");
</script>
