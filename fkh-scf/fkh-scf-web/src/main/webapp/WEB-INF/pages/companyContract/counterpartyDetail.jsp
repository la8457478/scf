<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        .contract{padding-top: 15px;}
        .contract span{display: inline-block;width: 85px;text-align: right;}
        .contract .newPublicInput{width: 270px;}
        .line_contract{width: 98%;height: 1px;background:#e7e6eb;margin: 0 auto;margin-top: 25px;}
        #contractList li{text-align: center;max-width:140px;margin-top: 15px;}
        #contractList li p{text-overflow: ellipsis;  overflow: hidden;  white-space: nowrap;width: 105px;}
        #contractList li:nth-child(5){margin-top: 15px !important;}
        /*#contractList li:first-child{margin-left: 0px !important;}*/
        .lineRoad p{padding-top: 15px;}
        .lineRoad span{display: inline-block;width: 75px;text-indent: 20px;}
        .lineRoadWidth{width: 350px;}
        #SA_3{top: 30px !important;  left: 78px !important;}
        #SA_4{top: 66px !important;  left: 78px !important;}
        .uploadAgreement{padding-top: 15px;}
        .uploadAgreement span{display: inline-block;width: 75px;text-indent: 20px;vertical-align: top;}
        .saveLineRoadMsg{width: 100%;text-align: center;}
        .saveLineRoadMsg input{  margin-top: 15px;  margin-left: 60px;  margin-bottom: 15px;}
        #uploadReceiptDialog{ font-size: 20px; font-weight: bold;}
        #uploadReceiptDialog input[type="text"]{ width: 180px;}
        .uploadImgFilePic{width: 93px;height: 64px;position: relative;}
        .uploadImgFilePic #uploadImgFile{position: absolute;top:0px;left: 0px;width: 100%;height: 100%;opacity: 0;filter: alpha(opacity=0);}
        .uploadListFile label{width: 130px;text-align: right;display: inline-block;}
        .uploadListFile{margin-top: 20px;}
        .uploadListFileLabel p label{width: 130px;text-align: right;display: inline-block;}
        .uploadImgFilePic {  width: 93px;  margin-left: 2%;  text-align: center;  position: relative;  height: 85px;  border: 2px solid #0071db;  background: url(newassets/imgs/icon_imgs_add.png) no-repeat;  background-position: center;  }
        #contractLineList span{text-decoration: underline;}
        .historyListMsg li{width: 100%;height: 80px;border-bottom:1px solid #ededed;}
        .historyListMsgMain{width: 95%;margin: 0 auto;}
        .historyListMsgMain p{text-align: justify;line-height: 20px;margin-top: 10px;}
        .operationMsgUser{margin-top: 10px;}
        .operationMsgUser a{color: #a4a4a4;}
        .operationMsgUser span{color: #0071dc;}
        .showMsgRigth{float: right;text-decoration: underline;color: #f80001;cursor: pointer;}
        .historyListMsg{max-height: 430px;overflow-y: auto;}
        @media screen and (min-width:1201px) and (max-width:1420px) {
            .contract .newPublicInput{width: 200px;}
        }
        @media screen and (min-width:10px) and (max-width:1200px) {
            .contract .newPublicInput{width: 160px;}
        }
        @media screen and (min-width:10px) and (max-width:1430px) {
            #contractList li{margin-left: 45px !important;}
        }
        .contract div p{padding-top: 10px;}
        .contract p i{display: inline-block;width: 70px;text-align: right;}
        #transportCompanyInputUlList{left: 15px;  width: 350px;}
        .projectMsg p{padding-top: 5px;}
        .projectMsg p i{display: inline-block;width: 70px;text-align: right;}
        @media screen and (min-width:1201px) and (max-width:1420px) {
            .contract .newPublicInput{width: 200px;}
        }
        @media screen and (min-width:10px) and (max-width:1200px) {
            .contract .newPublicInput{width: 160px;}
        }
        @media screen and (min-width:10px) and (max-width:1430px) {
            #contractList li{margin-left: 45px !important;}
        }
        #transportCompanyIdInputUlList{width: 270px;left: 32px;}
        .contract span {
            display: inline-block;
            width: 150px;
            text-align: right;
            padding-left: 30px;
        }
    </style>
</head>
<body>
<!--顶部导航-->
<div class="publicHeaderNav">
</div>
<!--项目上传合同文件弹出框 start-->
<jsp:include page="../includes/uploadContractDialog.jsp"></jsp:include>
<!--项目上传合同文件 end-->
<div id="heightPage" style="overflow-y: auto;background: white;width: 100%;">
    <div class="newPublicHeader">
        <c:if test="${empty counterpartyDTO}">
            <span style="font-size: 16px;padding-left: 30px;">新增交易对手</span>
        </c:if>
        <c:if test="${not empty counterpartyDTO}">
            <c:if test="${todo==3}">
                <span style="font-size: 16px;padding-left: 30px;">审核交易对手</span>
            </c:if>
            <c:if test="${todo==2}">
                <span style="font-size: 16px;padding-left: 30px;">修改交易对手</span>
            </c:if>
            <c:if test="${todo==4}">
                <span style="font-size: 16px;padding-left: 30px;">查看交易对手</span>
            </c:if>
        </c:if>
        <div class="publicDetailsModuleReturnBtn return">返回</div>
        <input type="hidden" id="changeRoleType" value="1">
    </div>

    <form name="editCounterpartyForm">
        <input type="hidden" id="id" name="id" value="${counterpartyDTO.id}"  >
        <input type="hidden" id="companyContractId" name="companyContractId" value="${companyContractId}"  >
        <input type="hidden" id="companyBorrowerId" name="companyBorrowerId" value="${companyBorrowerId}">
        <%--        <p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">项目信息</a></p>--%>
        <%--        <div class="contract">--%>
        <%--            <div class="" style="padding-left: 40px;margin-top: 20px;margin-bottom: 40px;">--%>
        <%--                <input type="hidden" id="project" value="${project.id}">--%>
        <%--                <p><i>项目信息：</i><a id="projectName">${project.projectName}</a></p>--%>
        <%--                <p><i>发货方：</i><a id="sendCompanyName">${project.sendCompanyName}</a></p>--%>
        <%--                <p><i>承运方：</i><a id="transportCompanyName">${project.transportCompanyName}</a></p>--%>
        <%--                <p><i>收货方：</i><a id="consigneeCompanyName">${project.consigneeCompanyName}</a></p>--%>
        <%--                <p><i>物资类型：</i><a id="materialType">${project.materialType==0?"普通":(project.materialType==1?"汽车":"短途运输")}</a></p>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--<p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">基本信息</a></p>--%>
        <%--        <div class="contract">--%>
        <%--            <span>合同甲方</span>--%>
        <%--            <select class="newPublicSelect newPublicSelect1 publicSelectSwitchTimeProject" id="companyBorrowerId" name="companyBorrowerId" onchange="setCompanyBorrowerName()" style="width:15%  !important;">--%>
        <%--                <option value='0'>----------请选择借款方企业----------</option>--%>
        <%--            </select>--%>
        <%--        </div>--%>
        <div class="contract">
            <span>公司名称</span>
            <input type="text" id="counterpartyName" name="counterpartyName"  class="newPublicInput" placeholder="请输入公司名称" value="${counterpartyDTO.counterpartyName}"
            <c:if test="${not empty counterpartyDTO}">
                   readonly
            </c:if>
            >
        </div>

        <div class="contract">
            <span>分项限额</span>
            <input type="text" id="subitemLimitBalance" name="subitemLimitBalance"  class="newPublicInput"  placeholder="请输入分项限额" value="${counterpartyDTO.subitemLimitBalance}">
            <label style="padding-left: 3px;">元</label>
            <%--<c:choose>--%>
            <%--<c:when test="${empty counterpartyDTO}">--%>
            <%--<b>最大可分配限额 :${companyContract.remainingSubitemBalance}元</b>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--<b>最大可分配限额 :${companyContract.remainingSubitemBalance + counterpartyDTO.subitemLimitBalance}元</b>--%>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
        </div>
        <div class="contract">
            <span>融资利率</span>
            <input type="text" id="interestRate" name="interestRate"  class="newPublicInput" placeholder="请输入融资利率" value='<fmt:formatNumber type="number" value="${counterpartyDTO.interestRate}" pattern="###,##0.00" maxFractionDigits="2"/>'>
            <label style="padding-left: 3px;">%</label>
        </div>
        <div class="contract">
            <span>保理管理费率</span>
            <input type="text" id="manageRate" name="manageRate"  class="newPublicInput" placeholder="请输入保理管理费率" value='<fmt:formatNumber type="number" value="${counterpartyDTO.manageRate}" pattern="###,##0.00" maxFractionDigits="2"/>' >
            <label style="padding-left: 3px;">%</label>
        </div>
        <div class="contract">
            <span>最高融资比例</span>
            <input type="text" id="ruleRatio" name="ruleRatio"  class="newPublicInput"  placeholder="请输入最高融资比例" value="${counterpartyDTO.ruleRatio}">
            <label style="padding-left: 3px;">%</label>
        </div>
        <div class="contract">
            <span>应收账款账期</span>
            <input type="text" id="paymentDays" name="paymentDays"  class="newPublicInput"  placeholder="请输入应收账款账期天数" value="${counterpartyDTO.paymentDays}">
            <label style="padding-left: 3px;">天</label>
        </div>
        <div class="contract">
            <span>宽限期</span>
            <input type="text" id="graceDays" name="graceDays"  class="newPublicInput"  placeholder="请输入宽限期天数" value="${counterpartyDTO.graceDays}">
            <label style="padding-left: 3px;">天</label>
        </div>

        <div class="contract">
            <span>宽限期利率</span>
            <input type="text" id="graceRate" name="graceRate"  class="newPublicInput" placeholder="请输入宽限期利率" value='<fmt:formatNumber type="number" value="${counterpartyDTO.graceRate}" pattern="###,##0.00" maxFractionDigits="2"/>' >
            <label style="padding-left: 3px;">%</label>
        </div>
        <div class="contract">
            <span>逾期利率</span>
            <input type="text" id="overdueRate" name="overdueRate"  class="newPublicInput" placeholder="请输入逾期利率"value='<fmt:formatNumber type="number" value="${counterpartyDTO.overdueRate}" pattern="###,##0.00" maxFractionDigits="2"/>' >
            <label style="padding-left: 3px;">%</label>
        </div>
        <div class="contract">
            <span>保理合同编号</span>
            <input type="text" id="factorContractNo" name="factorContractNo"  class="newPublicInput" placeholder="请输入保理合同编号" value="${counterpartyDTO.factorContractNo}" maxlength="50">
        </div>
        <div class="contract">
            <span>保理额度核准明细表编号</span>
            <input type="text" id="factorLimitCheckListNo" name="factorLimitCheckListNo"  class="newPublicInput" placeholder="请输入保理额度核准明细表编号" value="${counterpartyDTO.factorLimitCheckListNo}" maxlength="50">
        </div>
        <div class="contract">
            <span>保理服务协议编号</span>
            <input type="text" id="factorServiceAgreementNo" name="factorServiceAgreementNo"  class="newPublicInput" placeholder="请输入保理服务协议编号" value="${counterpartyDTO.factorServiceAgreementNo}" maxlength="50">
        </div>
        <div class="contract">
            <span>应收账款转让合同编号</span>
            <input type="text" id="transferBalanceContractNo" name="transferBalanceContractNo"  class="newPublicInput" placeholder="请输入应收账款转让合同编号" value="${counterpartyDTO.transferBalanceContractNo}" maxlength="50">
        </div>
        <div class="contract">
            <span>基础合同名称</span>
            <input type="text" id="baseContractName" name="baseContractName"  class="newPublicInput" placeholder="请输入基础合同名称" value="${counterpartyDTO.baseContractName}" maxlength="50">
        </div>
        <div class="contract">
            <span>基础合同编号</span>
            <input type="text" id="baseContractNo" name="baseContractNo"  class="newPublicInput" placeholder="请输入基础合同编号" value="${counterpartyDTO.baseContractNo}" maxlength="50">
        </div>
        <div class="line_contract"></div>
        <p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">合同附件</a></p>
        <div class="" style="margin-top: 20px;margin-bottom: 40px;">
            <ul id="contractList">
                <c:if test="${not empty counterpartyDTO}">
                    <c:forEach items="${counterpartyDTO.contractInfoList}" var="item">
                        <li style="margin-left: 50px;  display: inline-block;"><a href="${item.path}" target="_blank"><img src="newassets/imgs/icon_pdf.png"></a>
                            <p><a title="${item.name}">${item.name}</a></p>
                        </li>
                    </c:forEach>
                </c:if>

                <c:if test="${todo == 1 ||todo == 2}">
                    <li id="uploadPorjectContractButton" style="margin-left: 60px;  display: inline-block;">
                        <div class="newTicket" style="margin-left: 20px;">
                            <div style="width: 120px;height: 85px;cursor:pointer;margin-top: 0px;" class="uploadFiles" onclick="uploadPorjectContract()">
                            </div>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>

        <c:if test="${todo==3 && sessionUser.authMap.COUNTERPARTY_REVIEW == true}">
            <div class="saveLineRoadMsg">
                <input  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="reviewCounterparty(true)"
                        value="同意">
                    <%--<input  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="reviewCounterparty(false)"--%>
                    <%--value="拒绝">--%>
            </div>
        </c:if>
        <c:if test="${todo == 1 || todo == 2}">
            <input type="hidden" id="uploadNewFile" value="0">
            <div class="saveLineRoadMsg">
                <input onclick="saveOrUpdate()" class="newPublicAlertBtn new_public_blue_bg" type="button" value="保存">
            </div>
        </c:if>

    </form>
</div>
</body>
<script>
    var contractInfos=[];
    $(document).ready(function(){
        //显示页面标题,并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        <%--window.bindBackBtn("counterparty/counterpartyList.html?companyBorrowerId="+${companyBorrowerId});--%>
        <c:if test="${not empty counterpartyDTO}">
        <c:forEach items="${counterpartyDTO.contractInfoList}" var="item">
        addContractFile("${item.name}","${item.path}");
        </c:forEach>
        </c:if>
        <c:if test="${todo==3 or todo==4}">
        $("input").attr("readonly",true);
        </c:if>
    });
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage-160 + "px");

    //上传合同文件地址
    function uploadPorjectContract(){
        var obj = $(".uploadContractDialogContainer");
        layer.open({
            type: 1,
            title: ['上传合同文件信息', 'font-size:16px;color:white;background-color:#0070db;'],
            area: ['450px', '280px'],
            closeBtn: 4,
            content: obj
        });
        $("#upMessage").html("");
        $("#textfield").val("");
        $("#file").val("");
    }
    //保存合同文件地址
    function updatePorjectContract(name,path){
        addContractFile(name,path);
        $("#uploadNewFile").val(1);
        selfAlert("保存合同成功!");
        var html='<li style="margin-left: 50px;  display: inline-block;"><a href="'+path+'" target="_blank"><img src="newassets/imgs/icon_pdf.png"></a>';
        html+='<p><a title="'+name+'">'+name+'</a></p></li>';
        if($("#contractList li").size()>1){
            $("#contractList>li:last").prev().after(html);
        }else{
            $("#contractList").html(html+$("#contractList").html());
        }
        layer.closeAll();
        if($("#contractList li").size()>=21){
            $("#uploadPorjectContractButton").hide();
        }

    }

    /**
     * 添加合同名称和链接
     * @param name
     * @param path
     */
    function addContractFile(name,path){
        var file={
            name:name,
            path:path
        };
        contractInfos.push(file);
    }

    //保存主体合同：含文件地址
    function saveOrUpdate() {
        var formData = P.formData(document.editCounterpartyForm);
        if (!validateParams (formData)) {
            return false;
        }
        formData.status = 0;
        formData.contractLink=JSON.stringify(contractInfos);
        var url=formData.id?"counterparty/updateBaseConfig.json":"counterparty/save.json";
        // formData.contractInfos=contractInfos;
        ajaxPost(url, formData, function (resp) {
            if (resp.success) {
                selfAlert("保存成功!",function () {
                    layer.closeAll();
                    $("#page").val($("span.on").html());
                    window.clickReturn();
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }

    //借款方企业列表
    function selectCompanyList() {
        ajaxGet("company/list.json?companyType=2", function (resp) {
            var list="<option value='0'>----------请选择借款方企业----------</option>";
            var oilSites=resp.data;
            for(var i=0;i<oilSites.length;i++){
                // if(oilSites[i].id==item.parentId){
                //     list+='<option value="'+oilSites[i].id+'" title="'+oilSites[i].configDesc+'" selected>'+oilSites[i].configDesc+'</option>';
                // }else{
                list+='<option value="'+oilSites[i].id+'" title="'+oilSites[i].companyName+'">'+oilSites[i].companyName+'</option>';
                // }
            }
            $("#companyBorrowerId").html(list);
        });
    }
    //审核客户合同
    function reviewCounterparty(isPass) {
        var formData = {};
        // formData.contractInfos=contractInfos;
        formData.id=$("#id").val();
        formData.status=isPass?1:2;
        var info=isPass?"同意":"拒绝";
        selfConfirm("是否"+info+"该交易对手信息的创建",function () {
            ajaxPost("counterparty/review.json", formData, function (resp) {
                if (resp.success) {
                    selfAlert("保存成功!",function () {
                        layer.closeAll();
                        $("#page").val($("span.on").html());
                        window.clickReturn();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        })

    }


    /**
     * 校验参数
     */
    function validateParams(formData) {
        if (isLengthOutRange(formData.counterpartyName, true, 2, 30)) {
            selfAlert("请填写2-30位的公司名称!");
            return false;
        }

        if (${empty counterpartyDTO}) {
            if (!isCargoNumber10_2(formData.subitemLimitBalance) || isValueOutRange(formData.subitemLimitBalance, true, 0, ${companyContract.remainingSubitemBalance}) || beginWithZero(formData.subitemLimitBalance)) {
                selfAlert("请设置交易对手分项限额!");
                return false;
            }
        } else {
            if (!isCargoNumber10_2(formData.subitemLimitBalance) || isValueOutRange(formData.subitemLimitBalance, true, 0, ${companyContract.remainingSubitemBalance + counterpartyDTO.subitemLimitBalance}) || beginWithZero(formData.subitemLimitBalance)) {
                selfAlert("请设置交易对手分项限额!");
                return false;
            }
        }

        if (isSmall2Number2OutRange(formData.interestRate, true)) {
            selfAlert("请填写0.01-99.99的融资利率!");
            return false;
        }

        if (isSmall2Number2OutRange(formData.manageRate, true)) {
            selfAlert("请填写0.01-99.99的保理管理费率!");
            return false;
        }

        if (isSmall2Number2OutRange(formData.ruleRatio, true)) {
            selfAlert("请填写0.01-99.99的最高融资比例!");
            return false;
        }

        if (isNumberValueOutRange(formData.paymentDays, true, 1, 99999)) {
            selfAlert("请填写1-99999的应收账款账期!");
            return false;
        }

        if (isNumberValueOutRange(formData.graceDays, true, 1, 99999)) {
            selfAlert("请填写1-99999的宽限期!");
            return false;
        }

        if (isSmall2Number2OutRange(formData.graceRate, true)) {
            selfAlert("请填写0.01-99.99的宽限期利率!");
            return false;
        }

        if (isSmall2Number2OutRange(formData.overdueRate, true)) {
            selfAlert("请填写0.01-99.99的逾期利率!");
            return false;
        }

        if (isLengthOutRange(formData.factorContractNo, true, 1, 50)) {
            selfAlert("请填写保理合同编号!");
            return false;
        }

        if (isLengthOutRange(formData.factorLimitCheckListNo, true, 1, 50)) {
            selfAlert("请填写保理额度核准明细表编号!");
            return false;
        }

        if (isLengthOutRange(formData.factorServiceAgreementNo, true, 1, 50)) {
            selfAlert("请填写保理服务协议编号!");
            return false;
        }

        if (isLengthOutRange(formData.transferBalanceContractNo, true, 1, 50)) {
            selfAlert("请填写应收账款转让合同编号!");
            return false;
        }

        if (isLengthOutRange(formData.baseContractName, true, 1, 50)) {
            selfAlert("请填写基础合同名称!");
            return false;
        }

        if (isLengthOutRange(formData.baseContractNo, true, 1, 50)) {
            selfAlert("请填写基础合同编号!");
            return false;
        }

        if(contractInfos.length<=0||0==$("#uploadNewFile").val()){
            selfAlert("请上传新的附件!");
            return;
        }

        return true;
    }
</script>
</html>
