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
        .contract .newPublicInput{width: 290px;}
        .line_contract{width: 98%;height: 1px;background:#e7e6eb;margin: 0 auto;margin-top: 25px;}
        #contractList li{text-align: center;max-width:140px;margin-top: 15px;}
        #contractList li p{text-overflow: ellipsis;  overflow: hidden;  white-space: nowrap;width: 105px;}
        #contractList li:nth-child(5){margin-top: 15px !important;}
        /*#contractList li:first-child{margin-left: 0px !important;}*/
        .lineRoad p{padding-top: 15px;}
        .lineRoad span{display: inline-block;width: 75px;text-indent: 20px;}
        .uploadAgreement span{display: inline-block;width: 75px;text-indent: 20px;vertical-align: top;}
        .saveLineRoadMsg{width: 100%;text-align: center;}
        .saveLineRoadMsg input{  margin-top: 15px;  margin-left: 60px;  margin-bottom: 15px;}
        #uploadReceiptDialog input[type="text"]{ width: 180px;}
        .uploadImgFilePic #uploadImgFile{position: absolute;top:0px;left: 0px;width: 100%;height: 100%;opacity: 0;filter: alpha(opacity=0);}
        .uploadListFile label{width: 130px;text-align: right;display: inline-block;}
        .uploadListFileLabel p label{width: 130px;text-align: right;display: inline-block;}
        #contractLineList span{text-decoration: underline;}
        .historyListMsg li{width: 100%;height: 80px;border-bottom:1px solid #ededed;}
        .historyListMsgMain p{text-align: justify;line-height: 20px;margin-top: 10px;}
        .operationMsgUser a{color: #a4a4a4;}
        .operationMsgUser span{color: #0071dc;}
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

        .contract span {
            display: inline-block;
            width: 100px;
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

        <c:if test="${todo==1}">
            <span style="font-size: 16px;padding-left: 30px;">新增客户</span>
        </c:if>
        <c:if test="${todo==2}">
            <span style="font-size: 16px;padding-left: 30px;">修改客户合同</span>
        </c:if>
        <c:if test="${todo==3}">
            <span style="font-size: 16px;padding-left: 30px;">审核客户合同</span>
        </c:if>
        <c:if test="${todo==4}">
            <span style="font-size: 16px;padding-left: 30px;">查看客户合同</span>
        </c:if>
        <c:if test="${from==2}">
            <div class="publicDetailsModuleReturnBtn return">返回</div>
        </c:if>
        <input type="hidden" id="changeRoleType" value="1">
    </div>

    <form name="editCompanyContractForm">
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
        <%--<p style="margin-left: 20px;margin-top: 15px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">合同摘要</a></p>--%>
    <p style="margin-left: 20px;margin-top: 15px;font-size: 14px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">客户信息</a></p>

    <div class="contract">
            <span>
                <%--<i style="color: red">*</i>--%>
                客户名称
            </span>

            <c:if test="${not empty companyContract}">
                <input type="hidden" id="id" name="id"   value="${companyContract.id}">
                <input type="hidden" id="companyBorrowerId" name="companyBorrowerId"   value="${companyContract.companyBorrowerId}">
                <input type="text" name="companyBorrowerName"  class="newPublicInput"  value="${companyContract.companyBorrowerName}" readonly>
            </c:if>

            <c:if test="${empty companyContract}">
                <input type="hidden" id="companyBorrowerName" name="companyBorrowerName" value=""  >
                <select class="newPublicSelect newPublicSelect1 publicSelectSwitchTimeProject" id="companyBorrowerId" name="companyBorrowerId" onchange="setCompanyBorrowerName()" style="width:290px;">
                    <option value='0'>请选择客户企业</option>
                </select>
            </c:if>
        </div>
        <div class="contract">
            <span>审批书决议编号</span>
            <input type="text" id="contractNumber" name="contractNumber"  class="newPublicInput" placeholder="请输入审批书决议编号" value="${companyContract.contractNumber}">
        </div>
        <a></a>
<%--        <div class="contract">--%>
<%--            <span><i style="color: red">*</i>管理费率</span>--%>
<%--            <input type="text" id="manageRate" name="manageRate"  class="newPublicInput" placeholder="请输入管理费率" value="${companyContract.manageRate}">--%>
<%--            <label style="padding-left: 3px;">%</label>--%>
<%--        </div>--%>
<%--        <div class="contract">--%>
<%--            <span><i style="color: red">*</i>利率</span>--%>
<%--            <input type="text" id="interestRate" name="interestRate"  class="newPublicInput" placeholder="请输入利率" value="${companyContract.interestRate}">--%>
<%--            <label style="padding-left: 3px;">%</label>--%>
<%--        </div>--%>

            <div class="contract">
            <span>合同期间</span>
            <input style="width: 135px;"  class="newPublicInput newPublicInputOne publicTimeShow" id="dateStartTime" value="
<fmt:formatDate pattern="yyyy-MM-dd" value="${companyContract.startTime}" type="both"/>"
            <c:if test="${todo==1 or todo==2}">
                   onclick="$.jeDate('#dateStartTime',{trigger:false,isTime:true,multiPane:true,onClose:false,format:'YYYY-MM-DD'})"

            </c:if>
                   name="startTime" type="text" placeholder="请选择合同开始时间" readonly>
            <span style="width: 10px;padding-left: 0px;">至</span>
            <input class="newPublicInput newPublicInputOneTwo publicTimeShow"  id="dateEndTime" value="
<fmt:formatDate pattern="yyyy-MM-dd" value="${companyContract.endTime}" type="both"/>"
            <c:if test="${todo==1 or todo==2}">
                   onclick="$.jeDate('#dateEndTime',{trigger:false,isTime:true,multiPane:true,onClose:false,format:'YYYY-MM-DD'})"

            </c:if>
    name="endTime" type="text" placeholder="请选择合同结束时间" readonly style="margin-left: 0px;width: 135px;">

        </div>
            <div class="contract">
                <span>总额度</span>
                <input type="text" id="totalBalance" name="totalBalance"  class="newPublicInput"  placeholder="请输入总额度" value="${companyContract.totalBalance}" maxlength="15">
                <span style="padding-left: 5px;width: auto;">元</span>
            </div>
            <div class="contract">
                <span>客户开户银行</span>
                <input type="text" id="bankName" name="bankName"  class="newPublicInput" placeholder="请输入客户开户银行" value="${companyContract.bankName}" maxlength="30">
            </div>
            <div class="contract">
                <span>开户银行卡号</span>
                <input type="text" id="bankAccountNo" name="bankAccountNo"  class="newPublicInput" placeholder="请输入开户银行卡号" value="${companyContract.bankAccountNo}" maxlength="19">
            </div>
            <div class="contract">
                <span>支行名称</span>
                <input type="text" id="branchBankName" name="branchBankName"  class="newPublicInput" placeholder="请输入支行名称" value="${companyContract.branchBankName}" maxlength="19">
            </div>
            <div class="contract">
                <span>一级项目经理</span>
                <input type="text" id="projectMgName" name="projectMgName"  class="newPublicInput" placeholder="请输入一级项目经理" value="${companyContract.projectMgName}" maxlength="20">
            </div>
            <div class="contract">
                <span>二级项目经理</span>
                <input type="text" id="projectSecondMgName" name="projectSecondMgName"  class="newPublicInput" placeholder="请输入二级项目经理" value="${companyContract.projectSecondMgName}" maxlength="20">
            </div>
            <div class="contract">
                <span>部门经理</span>
                <input type="text" id="departmentManager" name="departmentManager"  class="newPublicInput" placeholder="请输入部门经理" value="${companyContract.departmentManager}" maxlength="20">
            </div>
            <div class="contract">
                <span>经办部门</span>
                <input type="text" id="departmentName" name="departmentName"  class="newPublicInput" placeholder="请输入经办部门" value="${companyContract.departmentName}" maxlength="20">
            </div>
        <div class="line_contract"></div>
        <p style="margin-left: 20px;margin-top: 15px;font-size: 14px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">合同附件</a></p>
        <div class="" style="margin-top: 20px;margin-bottom: 40px;">
            <ul id="contractList">
                <c:if test="${not empty companyContract}">
                    <c:forEach items="${companyContract.contractInfoList}" var="item">
                        <li style="margin-left: 50px;  display: inline-block;"><a href="${item.path}" target="_blank"><img src="newassets/imgs/icon_pdf.png"></a>
                            <p><a title="${item.name}">${item.name}</a></p>
                        </li>
                    </c:forEach>
                </c:if>

                <c:if test="${todo==1 or todo==2}">
                <li id="uploadPorjectContractButton" style="margin-left: 60px;  display: inline-block;">
                    <div class="newTicket" style="margin-left: 20px;">
                        <div style="width: 120px;height: 85px;cursor:pointer;margin-top: 0px;" class="uploadFiles" onclick="uploadPorjectContract()">
                        </div>
                    </div>
                </li>
                </c:if>
            </ul>
        </div>


        <c:if test="${todo==3}">
        <div class="saveLineRoadMsg">
            <input  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="reviewCompanyContract(true)"
                    value="同意">
<%--            <input  class="newPublicAlertBtn new_public_blue_bg" type="button" onclick="reviewCompanyContract(false)"--%>
<%--                    value="拒绝">--%>
        </div>
        </c:if>
        <c:if test="${(todo==1 || todo == 2) && sessionUser.authMap.COUNTERPARTY_ADD == true}">
        <div class="saveLineRoadMsg">
            <input type="hidden" id="uploadNewFile" value="0">
            <input onclick="saveOrUpdate()" class="newPublicAlertBtn new_public_blue_bg" type="button" value="保存">
        </div>
        </c:if>
    </form>
</div>
</body>
<script>
    var contractInfos=[];
    $(document).ready(function(){
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        // window.bindBackBtn("companyContractMgmt.html");
        selectCompanyList();
        <c:if test="${not empty companyContract}">
        <c:forEach items="${companyContract.contractInfoList}" var="item">
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
     * 设置借款方企业名称
     * @param name
     * @param path
     */
    function setCompanyBorrowerName(){
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
        var formData = P.formData(document.editCompanyContractForm);
        if (!validateParams (formData)) {
            return false;
        }
        // formData.contractInfos=contractInfos;
        formData.contractLink=JSON.stringify(contractInfos);
        var url=formData.id?"companyContract/updateBaseConfig.json":"companyContract/saveOrUpdate.json";
        ajaxPost(url, formData, function (resp) {
            if (resp.success) {
                selfAlert("保存成功!",function () {
                    layer.closeAll();
                    $("#page").val($("span.on").html());
                    <c:if test="${from==2}">
                        //从客户列表进入，返回客户列表
                        Page.returnPage();
                    </c:if>
                    <c:if test="${empty from}">
                        //从菜单进入，刷新菜单
                        Page.reloadCurrentPage();
                    </c:if>
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }

    //借款方企业列表
    function selectCompanyList() {
        ajaxGet("company/list.json?companyType=2&hasContract=true", function (resp) {
            var list="<option value='0'>请选择客户企业</option>";
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

    //保存摘要
    function  updatePorjectSummary(){
        if(!validatePorjectContract()){
            return ;
        }
        var summary=createSummary();
        var action = "projects/updateProjectSummary.json";
        ajaxPost(action,{summary:summary},function(result){
            if(result.success == true){
                selfAlert("合同保存成功!",function () {
                    ajaxPost("settings/contractMgmt.html", "", function (resultHtml) {
                        $("#publicRightContainer").html(resultHtml);
                    });
                });
                layer.closeAll();
                //更新页面

            } else {
                selfAlert("合同保存失败! " + result.message);
            }
        });
    }

    function createSummary(){
        var projectSummary={};
        projectSummary.consignName=$("#consignName").val();
        projectSummary.transportName=$("#transportName").val();
        projectSummary.transportCompanyId=$("#transportCompanyId").val();
        projectSummary.goodName=$("#goodName").val();
        projectSummary.goodPrice=$("#goodPrice").val();
        projectSummary.taxNumber=$("#taxNumber").val();
        projectSummary.address=$("#address").val();
        projectSummary.phoneNumber=$("#phoneNumber").val();
        projectSummary.bankName=$("#bankName").val();
        projectSummary.taxRate=$("#taxRate").val();
        projectSummary.bankAccountNumber=$("#bankAccountNumber").val();
        projectSummary.contractNumber=$("#contractNumber").val();
        projectSummary.calculationType=$("#calculationType").val();
        projectSummary.priceHandleType=$("#priceHandleType").val();
        projectSummary.contractInfos=contractInfos;
        //磅差信息
        if(projectSummary.calculationType=="5"){
            projectSummary.contractPoundInfoJson=createContractInfo();
        }
        var summary= JSON.stringify(projectSummary);
        return summary;
    }

    //审核客户合同
    function reviewCompanyContract(isPass) {
        var formData = {};
        // formData.contractInfos=contractInfos;
        formData.id=$("#id").val();
        formData.status=isPass?1:2;
        var info=isPass?"同意":"拒绝";
        selfConfirm("是否"+info+"该客户信息的创建",function () {
            ajaxPost("companyContract/review.json", formData, function (resp) {
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
        if (formData.companyBorrowerId == 0) {
            selfAlert("请选择客户企业!");
            return false;
        }
        if (P.isNullOrEmptyString(formData.contractNumber)) {
            selfAlert("请填写审批书决议编号!");
            return false;
        }

        if (P.isNullOrEmptyString(formData.startTime)) {
            selfAlert("请选择合同开始时间!");
            return false;
        }

        if (P.isNullOrEmptyString(formData.endTime)) {
            selfAlert("请选择合同结束时间!");
            return false;
        }

        if (formData.startTime >= formData.endTime) {
            selfAlert("合同结束时间必须晚于合同开始时间!");
            return false;
        }

        if (P.isNullOrEmptyString(formData.totalBalance) || parseFloat(formData.totalBalance) <= 0 || !isSmall12Number2(formData.totalBalance)) {
            selfAlert("请设置客户总额度!");
            return false;
        }

        if (isLengthOutRange(formData.bankName, true, 2, 30)) {
            selfAlert("请填写2-30字客户开户银行名称!");
            return false;
        }

        if (isNumberLengthOutRange(formData.bankAccountNo, true, 16, 19)) {
            selfAlert("请填写16-19位的开户银行卡号!");
            return false;
        }
        if (isChineseCharLengthOutRange(formData.branchBankName, true, 2, 20)) {
            selfAlert("请填写2-20字中文支行名称!");
            return false;
        }

        if (isChineseCharLengthOutRange(formData.projectMgName, true, 2, 20)) {
            selfAlert("请填写2-20字中文一级项目经理!");
            return false;
        }

        if (isChineseCharLengthOutRange(formData.projectSecondMgName, true, 2, 20)) {
            selfAlert("请填写2-20字中文二级项目经理!");
            return false;
        }

        if (isChineseCharLengthOutRange(formData.departmentManager, true, 2, 20)) {
            selfAlert("请填写2-20字中文部门经理!");
            return false;
        }

        if (isLengthOutRange(formData.departmentName, true, 2, 20)) {
            selfAlert("请填写2-20字经办部门!");
            return false;
        }

        if(contractInfos.length<=0 || 0==$("#uploadNewFile").val()){
            selfAlert("请上传新的附件!");
            return false;
        }

        return true;
    }
</script>
</html>
