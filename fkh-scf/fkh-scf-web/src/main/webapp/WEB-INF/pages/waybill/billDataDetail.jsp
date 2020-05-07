<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
    <style>
        .publicBillBtn{display: inline-block;margin-left: 15px;}
        .billMainContainer{width: 98%;margin: 0 auto;}
        .publicLeftMsg{padding-left: 15px;}
        .publicLeftMsg a{padding-left: 1px;display: inline-block;width: 90px;}
        .publicLeftMsg label{margin-right: 40px;padding-left: 4px;}
        .publicLeftMsg span{display: inline-block;width: 68px;text-align: right;}
        #reviewInvoiceBillLoad{ width: 85%;  display: block;  margin: 0 auto;  height: 95px;  margin-top: 15px;}
        .textarea{
            width:180px;height:24px;position:absolute;background:none;z-index:9;outline: none;resize: none;  border: 1px solid #e2e2e2;text-indent: 0px;}
        .note{
            position:absolute;line-height:20px;padding:3px 5px;
        }
        #reviewInvoiceBillLoad{ resize: none; outline: none;}
        .new_uploadPicImgsBox{margin-left: 18px;}
        .autoSumBoxList span{display: inline-block;width: 6px !important;height: 6px !important;border-radius:6px;background: #017FF8;position: relative;top:-3px;}
        .autoSumBoxList i {
            display: inline-block;
            width: 6px;
            height: 6px;
            border-radius: 6px;
            background: #000;
            position: relative;
            top: -3px;
        }
        .autoSumBoxList ul li{padding-top: 5px;}
        .heightPagenotHead{margin-left:15px;}
    </style>
</head>
<body>
<!--不通过原因弹窗-->
<div class="financialReviewNoPassDialog payDialog publicHide" style="display: none;">
    <form autocomplete="off">
        <textarea id="reviewInvoiceBillLoad" maxlength="20"></textarea>
        <br />
        <input class="publicBtn new_public_blue_bg" style="margin-top: 0px;" onclick="reviewInvoiceBillLoadNot()" type="button" value="确定">
    </form>
</div>
<!--查看图片弹框详情-->
<div id="loadingBg2" style="display: none;">
    <div class="loadingBgBox">
        <div>
            <a class="rotatePic1 newRotatePic" id="rotatePic1">
                <img src="newassets/imgs/rotatePic.png">
            </a>
            <img id="deletePicImgs" class="project delPublicBtn" style="cursor:pointer;"  src="newassets/imgs/icon_delete_pic.png">
        </div>
        <img  class="target"  id="picTest">
    </div>
</div>
<!--查看图片弹框详情end-->


<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>运单管理</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>凭证信息</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>
<!--顶部导航 end-->
<div class="publicDetailsModule" style="padding-bottom: 0px;">
    <div class="newPublicHeader">
        <span id="waybillNo">运单号：</span>
        <span id="programName">计划名：</span>
        <div  class="publicDetailsModuleReturnBtn return returnProject">返回</div>

    </div>
    <div class="billMainContainer" id="heightPagenotHead" style="overflow-y: auto; background: white; width: 100%;overflow-x: hidden; ">
        <div class="heightPagenotHead">
            <div class="publicDetailsModuleContainerHeader">
                <img src="newassets/imgs/icon_chart_line.png">
                <span>车辆信息</span>
            </div>
            <p class="publicLeftMsg">
                <span style="text-align: left;">车牌号：</span>
                <a id="licensePlateNo" style="width: auto;"></a>
                <span>姓名：</span>
                <a id="driverName" style="width: auto;"></a>
                <span>手机号：</span>
                <a id="driverMobileNo"></a>
            </p>
            <%--<p id="reason_box" class="publicLeftMsg" style="display: none;margin-left: -13px;margin-top: 10px;">--%>
                <%--<span style="width: 80px;">不通过理由：</span>--%>
                <%--<a id="reason" style="width: auto;"></a>--%>
            <%--</p>--%>
            <div class="publicDetailsModuleContainerHeader">
                <img src="newassets/imgs/icon_chart_line.png">
                <span>发货单据</span>
            </div>
            <!--发货单据：重量单据HTML-->
                <p class="publicLeftMsg publicTon">
                    <span>发货毛重：</span>
                    <input class="newPublicAlertInput publicVoucherInput" id="sendGrossWeight" name="sendGrossWeight" type="text"><label>吨</label>
                    <span>发货皮重：</span>
                    <input class="newPublicAlertInput publicVoucherInput" id="sendTareWeight" name="sendTareWeight"  type="text"><label>吨</label>
                    <span>发货净重：</span>
                    <input class="newPublicAlertInput publicVoucherInput" id="sendNetWeight" name="sendNetWeight" type="text"><label>吨</label>
                </p>
            <!--发货单据：通用单据HTML-->
                <p class="publicLeftMsg publicPiece" style="display: none">
                    <span>发货数量：</span>
                    <input class="newPublicAlertInput publicVoucherInput" id="sendNetWeightx" name="sendNetWeight" type="text"><label class="unit_name"></label>
                </p>
            <div id="uploadSendInvoice_box" style=" width:100%;margin-top: 15px;">
                <div class="newMainPicBox">
                    <div id="pictureBox" class="pictureBox" style="position: relative;overflow: hidden;height:auto;"></div>
                </div>

                <div id="pictureTitle" class="pictureTitle newPictureTitle">
                    <input type="hidden" value="1">
                    <ul>
                    </ul>
                    <div id="addPicImg" onclick="addPicShowLoadBill(this,1)" class="icon_addpicImg new_icon_addpicImg">
                        <img style="width: 24px;height:24px;" src="newassets/imgs/icon_add_img_01.png">
                        <span>添加图片</span>
                    </div>
                </div>
                <div class="clear"></div>
                <div class="uploadPicImgsBox new_uploadPicImgsBox">
                    <form name="fileForm" id="fileForm" enctype="multipart/form-data" method="post">
                        <div class="addPicShowLoadBox newAddPicShowLoadBox">
                            <h4 style="padding-bottom: 0px;">添加图片</h4>
                            <input type="text" name="textfield" id="textfield" placeholder="请选择图片上传" class="txts" style="border-radius:3px;">
                            <input type="button" class="btn" style="margin-left:5px;border-radius:3px;" value="浏览...">
                            <input type="file" name="file" class="file" id="file" size="28" onChange="document.getElementById('textfield').value=this.value">
                            <input type="hidden" id="tempUrl" class="text" style="width: 380px">
                        </div>
                        <div class="btnBoxUpload newBtnBoxUpload">
                            <input type="hidden" name="imageType" id="imageType" value="1">
                            <input type="button" value="确定"  class="newPublicAlertBtn new_public_blue_bg" onClick="ajaxFileUpload(this,1)">
                            <input type="button" value="关闭"  class="newPublicAlertBtn closeBtnUploads borderColorBoxBtn" onClick="coloseUploadBill(this)">
                        </div>
                        <div class="clear"></div>
                        <div class="upLoadingtext" style="padding-top: 5px;">
                            <img id="upLoading" src="newassets/imgs/loading.gif" style="display: none; width: 92px;height: 60px;">
                            <span id="upMessage" class="msg"></span>
                        </div>
                    </form>
                </div>
            </div>
            <div class="clear"></div>
            <div class="publicDetailsModuleContainerHeader">
                <img src="newassets/imgs/icon_chart_line.png">
                <span>收货单据</span>
            </div>
            <!--收货单据：重量单据HTML-->
                <p class="publicLeftMsg publicTon">
                    <span>收货毛重：</span>
                    <input class="newPublicAlertInput publicVoucherInput" id="receiveGrossWeight" name="receiveGrossWeight"  type="text"><label>吨</label>
                    <span>收货皮重：</span>
                    <input class="newPublicAlertInput publicVoucherInput" id="receiveTareWeight" name="receiveTareWeight"  type="text"><label>吨</label>
                    <span>收货净重：</span>
                    <input class="newPublicAlertInput publicVoucherInput" id="receiveNetWeight" name="receiveNetWeight"  type="text"><label>吨</label>
                </p>
            <!--收货单据：通用单据HTML-->
                <p class="publicLeftMsg publicPiece" style="display: none">
                    <span>收货数量：</span>
                    <input class="newPublicAlertInput publicVoucherInput" id="receiveNetWeightx" name="receiveNetWeight" type="text"><label class="unit_name"></label>
                </p>

            <div id="uploadReceiveInvoice_box" style="width:100%; margin-top: 15px;">
                <div class="newMainPicBox">
                    <div id="pictureBox_sign" class="pictureBox" style="position: relative;overflow: hidden;height:auto;"></div>
                </div>

                <div id="pictureTitle_sign" class="pictureTitle newPictureTitle">
                    <input type="hidden" value="2">
                    <ul>

                    </ul>
                    <div id="addPicImg_sign" onclick="addPicShowLoadBill(this,2)" class="icon_addpicImg new_icon_addpicImg">
                        <img style="width: 24px;height:24px;" src="newassets/imgs/icon_add_img_01.png">
                        <span>添加图片</span>
                    </div>
                </div>
                <div class="clear"></div>
                <div class="uploadPicImgsBox new_uploadPicImgsBox">
                    <form name="fileForm" id="fileForm_sign" enctype="multipart/form-data" method="post">
                        <div class="addPicShowLoadBox newAddPicShowLoadBox">
                            <h4 style="padding-bottom: 0px;">添加图片</h4>
                            <input type="text" name="textfield" id="textfield_sign" placeholder="请选择图片上传" class="txts" style="border-radius:3px;">
                            <input type="button" class="btn" style="margin-left:5px;border-radius:3px;" value="浏览...">
                            <input type="file" name="file" class="file" id="file_sign" size="28" onChange="document.getElementById('textfield_sign').value=this.value">
                            <input type="hidden" id="tempUrl_sign" class="text" style="width: 380px">
                        </div>
                        <div class="btnBoxUpload newBtnBoxUpload">
                            <input type="hidden" name="imageType" id="imageType_sign" value="1">
                            <input type="button" value="确定"  class="newPublicAlertBtn new_public_blue_bg" onClick="ajaxFileUpload(this,2)">
                            <input type="button" value="关闭"  class="newPublicAlertBtn closeBtnUploads borderColorBoxBtn" onClick="coloseUploadBill(this)">
                        </div>
                        <div class="clear"></div>
                        <div class="upLoadingtext" style="padding-top: 5px;">
                            <img id="upLoading_sign" src="newassets/imgs/loading.gif" style="display: none; width: 92px;height: 60px;">
                            <span id="upMessage_sign" class="msg"></span>
                        </div>
                    </form>
                </div>
            </div>
            <div class="publicDetailsModuleContainerHeader">
                <img src="newassets/imgs/icon_chart_line.png">
                <span>运费信息</span>
            </div>
            <p class="publicLeftMsg">
                <span>运费单价：</span>
                <input id="unitPrice" name="unitPrice" class="newPublicAlertInput publicVoucherInput" type="hidden">
                <input id="unitPrice_show" class="newPublicAlertInput publicVoucherInput" type="text"><label>元</label>
                <span>货值单价：</span>
                <input id="valuePrice" name="valuePrice" class="newPublicAlertInput publicVoucherInput" type="text"><label>元</label>
                <span>结算金额：</span>
                <input id="totalPricex" name="totalPrice" class="newPublicAlertInput publicVoucherInput" type="text" style="background: white;"><label>元</label>
            </p>
            <div id="paymentDetail" style="display: none;">
                <p  class="publicLeftMsg" style="padding-top: 15px;">
                    <span>支付油卡：</span>
                    <input value="0" id="oilCardAmount" name="oilCardAmount"  class="newPublicAlertInput publicVoucherInput" type="text"><label>元</label>
                    <span>支付气卡：</span>
                    <input value="0" id="gasCardAmount" name="gasCardAmount"  class="newPublicAlertInput publicVoucherInput" type="text"><label>元</label>
                    <span>充值ETC：</span>
                    <input value="0" id="etcCardAmount" name="etcCardAmount"  class="newPublicAlertInput publicVoucherInput" type="text"><label>元</label>
                    <span>押金：</span>
                    <input value="0" id="depositAmount" name="depositAmount" class="newPublicAlertInput publicVoucherInput" type="text" style="background: white;"><label>元</label>
                </p>
                <p  class="publicLeftMsg" style="padding-top: 15px;">
                    <span>已用授信：</span>
                    <input readonly class="newPublicAlertInput publicVoucherInput" value="0" id="oilUsedCredit" /><label>元</label>
                </p>
                <p  class="publicLeftMsg" style="padding-top: 15px;">
                    <span>应付总额：</span>
                    <a style="font-size:18px;color: red;width: auto;" id="needPayAmount"></a>
                </p>
            </div>

            <%--<div class="publicDetailsModuleContainerHeader">
                <img src="newassets/imgs/icon_chart_line.png">
                <span>磅差信息</span>
            </div>
            <p class="publicLeftMsg">
                <span>磅差方式：</span>
                <a id="poundName1"></a>
            </p>
            <p class="publicLeftMsg" style="padding-top: 15px;">
                <span id="poundName2"></span>
                <input  id="poundValue" class="newPublicAlertInput publicVoucherInput" type="text"><label id="poundValueUnit">‰(千分比)</label>
            </p>
            <div style="margin-left: 13px;" id="ladderPound">
                <p style="font-weight: bold;padding-bottom: 10px;">涨吨</p>
                <label id="upTypeLabel">以收货净重为准</label>
                <label id="isDeductPoundLabel">不扣磅差</label>
                <p style="font-weight: bold;    padding-top: 10px;padding-bottom: 10px;">亏吨</p>
                <div class="autoSumBoxList">
                    <ul>
                        <li>
                            <i></i>
                            <a> 第一阶梯， 0Kg <  磅差  ≤ 无上限 Kg：</a>
                        </li>
                    </ul>
                </div>
            </div>--%>
            <!--发货凭证-->

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
</body>
<script>
    $(document).ready(function(){
        Page.showPageTitleBindReturnUrl();
        //加载单据
        initReady();
        ajaxLoadBillData();
        $("input").attr("readOnly", true);
        ajaxLoadBillHistory(${waybill.id});
    });

    //currentType:1、单据完善 2、单据复核 3、单据查看
    var  currentType=3;


    var currentPoundValue = 0;
    //加载运单信息
    function ajaxLoadBillData(){
        ajaxGet("waybill/getDetail/${waybillId}.json", function (resp) {
            if (!resp.success) {
                selfAlert(resp.message);
                return false;
            }

            imageUrlPrefix = '${imageBaseUrl}';
            var billData = resp.data.waybill;
            var programData = resp.data.program;

            $("#waybillNo").append(billData.waybillNo);
            $("#programName").append(billData.programName);
            $("#driverName").html(billData.driverName);
            $("#licensePlateNo").html(billData.licensePlateNo);
            $("#driverMobileNo").html(billData.driverMobileNo);

            $("#unit").val(billData.unit);

            if (billData.unit == 1) {
                $(".publicTon").show();
                $(".publicPiece").hide();
            } else {
                $(".publicPiece").show();
                $(".publicTon").hide();
            }
            var unitName = P.cargoNumNo[billData.unit];
            $(".unit_name").html(unitName);
            $("#unitPrice").val(billData.unitPrice);
            $("#unitPrice_show").val(P.numbers(billData.unitPrice,2,0));
            $("#valuePrice").val(billData.valuePrice);
            $("#oilUsedCredit").val(billData.oilUsedCredit==undefined?0:billData.oilUsedCredit);
            var poundName = getPoundName(programData.poundKey);
            $("#poundName1").html(poundName);
            $("#poundName2").html(poundName + "：");
            if(unitName == "吨"){
                unitName = "Kg";
            }
            //扣磅差比可以修改
            if(programData.poundKey==1){
                $("#poundValue").val(programData.poundValue)
                $("#poundValueUnit").html("‰(千分比)")
            }else if(programData.poundKey==2){//扣磅差值可以修改
                $("#poundValue").val(programData.poundValue)
                $("#poundValueUnit").html(unitName)
            }else{
                $("#poundName2").remove();
                $("#poundValue").remove();
                $("#poundValueUnit").remove();
            }
            var downLevel = programData.poundValue;
            var poundValue = programData.poundValue;
            currentPoundValue = programData.poundValue;
            var poundKey = programData.poundKey;
            var upType = programData.upType;
            var isDeductPound = programData.isDeductPound;
            var autoSumBoxListHtml="";
            var b = '------------------------------------------------------------';
            if(poundKey == 3){//不扣磅差
                $("#poundType").text("不扣磅差");
                $(".poundKey1").hide();
                $(".poundKey2").hide();
                $(".poundKey3").show();
                if (poundValue == -1) {
                    $("#sendOrReceive").text("以发货方过磅信息为准");
                }else {
                    $("#sendOrReceive").text("以收货方过磅信息为准");
                }
                $("#ladderPound").hide();
            }else if (poundKey == 1){//扣磅差比
                $("#poundType").text("扣磅差比");
                $(".poundKey1").show();
                $(".poundKey2").hide();
                $(".poundKey3").hide();
                $("#ladderPound").hide();
            }else if(poundKey == 2){//扣磅差值
                $("#poundType").text("扣磅差值");
                $(".poundKey1").hide();
                $(".poundKey3").hide();
                $(".poundKey2").show();
                $("#ladderPound").hide();
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
                    $("#upTypeLabel").text("以发货净重为准,");
                }else {
                    $("#upTypeLabel").text("以收货净重为准,");
                }
                if (isDeductPound == 0){
                    $("#isDeductPoundLabel").text("不扣磅差");
                }else {
                    $("#isDeductPoundLabel").text("扣实际磅差");
                }
                var autoSumBoxListHtml="";
                var downLevel = poundValue;
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
                    $("#upTypeLabel").text("以发货净重为准,");
                }else {
                    $("#upTypeLabel").text("以收货净重为准,");
                }
                if (isDeductPound == 0){
                    $("#isDeductPoundLabel").text("不扣磅差");
                }else {
                    $("#isDeductPoundLabel").text("扣实际磅差");
                }
                var autoSumBoxListHtml="";
                var downLevel = poundValue;
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
                        poundKeyStr = "扣固定磅差" + downLevel[i].poundVaule +"Kg";
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
            $("#allowDifference").val(billData.allowDifference);
            $("#allowDifferenceAmount").val(billData.allowDifferenceAmount);
            $("#allowDifferenceVal").val(billData.allowDifferenceVal);
            $("#upType").val(resp.data.upType);
            $("#poundValueArray").val(JSON.stringify(poundValue));
            $("#isDeductPound").val(resp.data.isDeductPound);
            //单据凭证
            var uploadSendInvoice = billData.uploadSendInvoice==null ? "":billData.uploadSendInvoice;
            var uploadSendInvoiceArray = uploadSendInvoice.split(",");
            for(var i=0; i<uploadSendInvoiceArray.length; i++){
                if(uploadSendInvoiceArray[i] == "" || uploadSendInvoiceArray[i] ==null){
                    break;
                }
                var imgUrl = imageUrlPrefix + uploadSendInvoiceArray[i];
                var smallImgHtml = '<li onclick="voucherImgDialog_new(this,' + (i+1) + ',1)" ><img src="' + imgUrl + '"></li>';
                $("#pictureTitle ul").append(smallImgHtml);
                var sourceImg = imgUrl.replace("thumbnail/thumbnail_", "images/waybill/");
                if(imgUrl.indexOf("thumbnail/thumbnail_")<0){
                    sourceImg = imgUrl.replace("thumbnail/", "images/waybill/");
                    sourceImg = sourceImg.replace("thumbnail_", "");
                }
                var imgsBig = '<img class="target" onmousedown="imgDrag(this)"  style="position: absolute;" src="' + sourceImg + '">';
                $("#pictureBox").append(imgsBig);
                //  $("#pictureTitle ul li").eq(0).addClass("pictureActive");
            }

            var uploadReceiveInvoice = billData.uploadReceiveInvoice==null ? "":billData.uploadReceiveInvoice;
            var uploadReceiveInvoiceArray = uploadReceiveInvoice.split(",");
            for(var i=0; i<uploadReceiveInvoiceArray.length; i++){
                if(uploadReceiveInvoiceArray[i] == ""|| uploadReceiveInvoiceArray[i] ==null){
                    break;
                }
                var imgUrl = imageUrlPrefix + uploadReceiveInvoiceArray[i];
                var smallImgHtml = '<li onclick="voucherImgDialog_new(this,' + (i+1) + ',2)" ><img src="' + imgUrl + '"></li>';
                $("#pictureTitle_sign ul").append(smallImgHtml);
                var sourceImg = imgUrl.replace("thumbnail/thumbnail_", "images/waybill/");
                if(imgUrl.indexOf("thumbnail/thumbnail_")<0){
                    sourceImg = imgUrl.replace("thumbnail/", "images/waybill/");
                    sourceImg = sourceImg.replace("thumbnail_", "");
                }
                var imgsBig = '<img class="target" onmousedown="imgDrag(this)"  style="position: absolute;" src="' + sourceImg + '">';
                $("#pictureBox_sign").append(imgsBig);
                // $("#pictureTitle_sign ul li").eq(0).addClass("pictureActive");
            }
            baseRender("sendInvoice", uploadSendInvoice);
            baseRender("receiveInvoice", uploadReceiveInvoice);
            //currentType:1、单据完善 2、单据复核 3、单据查看
            var currentType=3;


            if(currentType==3){//单据查看页面需要数据回显
                renderViewDate(billData);
                //因为新烨和敬业推送数据后，没有凭证图片，不显示
                if(!billData.uploadSendInvoice){
                    $("#uploadSendInvoice_box").remove();
                }
                if(!billData.uploadReceiveInvoice){
                    $("#uploadReceiveInvoice_box").remove();
                }
            }

        });
    }
    $("#unitPrice_show").on("keyup",function(){
        $("#unitPrice").val($(this).val());
    });

    function getPoundName(poundKey) {
        switch (poundKey) {
            case 1: return "扣磅差比";
            case 2: return "扣磅差值";
            case 3: return "不扣磅差";
            case 4: return "不过磅";
            case 5: return "扣磅差阶梯值";
            case 6: return "扣磅差阶梯比";
            default: return "";
        }
    }

    /**
     * 回显数据
     */
    function renderReviewDateBase(billData) {
        baseRender("sendGrossWeight", P.numbers(billData.grossWeightBySend,2,0));
        baseRender("sendTareWeight", P.numbers(billData.tareWeightBySend,2,0));
        if (billData.unit == 1) {
            baseRender("sendNetWeight", P.numbers(billData.netWeightBySend,2,0));
        } else {
            baseRender("sendNetWeightx", P.numbers(billData.netWeightBySend,2,0));
        }

        baseRender("receiveGrossWeight", P.numbers(billData.receiveGrossWeight,2,0));
        baseRender("receiveTareWeight", P.numbers(billData.receiveTareWeight,2,0));

        baseRender("totalPricex", billData.invoiceMoney);
        baseRender("oilCreditLimit", billData.oilCreditLimit);
        if (billData.unit == 1) {
            baseRender("receiveNetWeight", P.numbers(billData.receiveNetWeight,2,0));
        } else {
            baseRender("receiveNetWeightx", P.numbers(billData.receiveNetWeight,2,0));
        }

        var currentType=3;
        if(currentType==2){
            //复核页面显示totalPrice
            $("#needPayAmount").html(billData.invoiceMoney + "元");
        }else{
            //查看页面显示totalPrice
            $("#needPayAmount").html(billData.cashPayAmount + "元");
        }
    }

    function baseRender(htmlId, data) {
        $("#"+ htmlId).val(data);
    }
    /**
     * 单据查看页面，回显数据
     */
    function renderViewDate(billData) {
        renderReviewDateBase(billData);

        baseRender("oilCardAmount", billData.oilCardAmount);
        baseRender("gasCardAmount", billData.gasCardAmount);
        baseRender("etcCardAmount", billData.etcCardAmount);
        baseRender("depositAmount", billData.depositAmount);
    }

    function ajaxLoadBillHistory(){
        ajaxGet("waybill/listWaybillOperationHistory/${waybillId}.json", function(resp){
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

    /**
     * 修改布局
     * */
    initNotHeader();
    function initNotHeader(){
        var heightPage = document.documentElement.clientHeight;
        $("#heightPagenotHead").css("height", heightPage - 180 + "px");
    }


    /**
     * 查看大图
     * */
    var index = 0;
    var current = 0;
    var imageUrlPrefix='${imageBaseUrl}';
    var imgDelType;
    //初始化调用
    initPage(currentType);
    function initPage(currentType){
        if(currentType == 1){
            $("#saveBillShow").show();
        }else if(currentType == 2){
            $("#saveBillShows").show();
            $("#addPicImg").remove();
            $("#addPicImg_sign").remove();
            $("#paymentDetail").show();
            $("#deletePicImgs").remove();
            $("#saveBillBtn").remove();
            $("#note").remove();
        }else if(currentType == 3){
            $("#saveBillShows").remove();
            $("#addPicImg").remove();
            $("#addPicImg_sign").remove();
            $("#paymentDetail").show();
            $("#deletePicImgs").remove();
            $("#countInvoice").remove();
            $("#saveBillBtn").remove();
            $("#note").remove();
        }
    }
    //点击小图放大
    function voucherImgDialog_new(obj,i,type){
        imgDelType = $(obj).parent().prev().val();
        current = 0;
        index = $(obj).index();
        i=index;
        var target = $(".target");
        $(obj).addClass("pictureActive").siblings().removeClass("pictureActive");
        $("#pictureBox img").hide().eq(i).show();
        target.css({'transform':'rotate('+current+'deg)'}).css("zoom","0%");
        $("#pictureBox").css("overflow","hidden");
        target.css({'top':'0','left':'0px'});
        if(type == 1){
            var imgs = $("#pictureBox img").hide().eq(i).attr("src");
        }else{
            var imgs = $("#pictureBox_sign img").hide().eq(i).attr("src");
        }

        $("#loadingBg2").show();
        $("#picTest").attr("src",imgs);
        $("#picTest").show();
        $("#loadingBg2").on("click",function(e){
            $(this).hide();
            $("#picTest").hide();
        });
        //阻止click冒泡事件
        $("#picTest").click(function(event){
            event.stopPropagation();
        });
        //设置默认图片大小
        $(".target").css("height", "500px").css("width", "500px");
    }
    function addPicShowLoadBill(obj,type){
        $(obj).parent().siblings(".uploadPicImgsBox").show();
        if(type == 1){
            imgShowLoadBox();
        }else{
            imgShowLoadBox_sign();
        }
    }
    function coloseUploadBill(obj){
        $(obj).parent().parent().parent().hide();
    }
    function imgShowLoadBox_sign(){
        $("#textfield_sign").val("");
        $("#upMessage_sign").html('');
        $("#file_sign").val("");
    }
    function imgShowLoadBox(){
        $("#textfield").val("");
        $("#upMessage").html('');
        $("#file").val("");

    }

    //图片放大缩小
    $("#loadingBg2").unbind().on("click",".rotatePic1", function(event) {
        event.stopPropagation();
        current = (current+90)%360;
        $(this).css({'transform':'rotate('+0+'deg)'});
        $(this).parent().parent().find(".target").css({'transform':'rotate('+current+'deg)'});
        $("#deletePicImgs").css({'transform':'rotate('+0+'deg)'});
    });
</script>
</html>
