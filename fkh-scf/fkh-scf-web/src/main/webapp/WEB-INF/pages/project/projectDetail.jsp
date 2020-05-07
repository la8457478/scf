<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        .projectDetailsPage .tableList tr:hover{background: none;}
        .projectDetailsPage .tableList td{line-height:45px;}
        .projectDetailsPage .tableList{width: 96%;margin: 0 auto;margin-top:IsFromClosedProject 15px;}
        .modifyRolePublic .confirmBtn{padding-left: 15px;  padding-right: 15px;  height: 30px;  border-radius: 5px;  color: white;  margin-left: 10px;margin-top: 15px;  background: #f86106;}
        .modifyRolePublic .publicInput{width: 150px;  height: 30px;  border-radius: 5px;  border: 1px solid #dddddd;}
        .projectDetailsPage .publicShow{display:none;}
        .projectDetailsPage .publicShow .selectPerson{width:100%;height:180px;overflow-x: auto;}
        .projectDetailsPage .personConfigure .personPublicBtn{margin-top: 15px;}
        .projectList .orangeBg{margin-top: 10px;}
        .selectPerson .ztree li a{vertical-align: inherit;}
        .publicContainers .publicTab .publicTitle span{height: 32px !important;}
        .publicContainers .publicTab .publicTitle span a{font-size: 16px;position: relative;top:-4px;}
        .publicTitle span{position: relative;width: 130px;   margin-left: 30px;   font-size: 16px;}
        .projectBox h4{border:none;padding-left: 30px;color:#666666;}
        .person .phone_value ul li a{color: #333333 ;}
        .newPublicHeader a{text-decoration:underline;color: #0071db;cursor:pointer;}
        #contractList li:first-child{margin-left: 0px !important;}
        .contractListMsg p{padding-top: 10px;}
        .contractListMsg p span{display: inline-block;width: 115px;text-align: right;}
        .contractListMsg p a{padding-left: 10px;}
        .contractListMsg p:last-child{padding-bottom: 10px;}
        .contractListMsgList p{padding-top: 10px;}
        .contractListMsgList p span{display: inline-block;width:115px;text-align: right;}
        .contractListMsgList input[type="text"]{width: 445px !important;}
        .contractListMsgItem input[type="text"]{width: 445px !important;}
        .addAbstractHead img{float: right;  position: relative;  right: 10px;  top: 5px;cursor: pointer;}
        .projectLogList p{  display: inline-block;  }
        #contractList li{text-align: center;max-width:140px;}
        #contractList li p{text-overflow: ellipsis;  overflow: hidden;  white-space: nowrap;}
        #contractList li:nth-child(5){margin-left: 0px !important;margin-top: 15px !important;}
        .personnalsTbody span{width: 170px !important;}
        .netWeightOnly_p img{cursor: pointer;}
        .userMessage_pic p i{display: inline-block;width: 80px}
    </style>
</head>
<body>
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a class="projectManagerText">我的额度</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>项目详情</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>
<div class="publicContainer publicContainers" id="heightPage" style="overflow-y: auto; background: white; width: 100%; height: 597px;">
    <div class="publicMainContainerBox publicMainContainerBoxs">
        <!--项目详情主题内容-->
        <div class="projectDetailsMain">
            <!--头部-->
            <div class="newPublicHeader">
                <span style="font-size: 16px;padding-left: 30px;">${project.projectName}</span>
                <div class="publicDetailsModuleReturnBtn return">返回</div>
                <input type="hidden" id="changeRoleType" value="1">
            </div>
            <div class="userMessage_pic">
                <p>
                    <i>创建人：</i>
                    <input class="newPublicAlertBtn new_public_blue_bg"  type="button" value="${createPart}">
                    <span style="padding-left: 0px;">${project.createUserName}<a></a></span>
                </p>
                <p>
                    <i>物资类型：</i>
                    <span>
                        <c:if test="${project.materialType==0}">普通物资</c:if>
                        <c:if test="${project.materialType==1}">汽车</c:if>
                        <c:if test="${project.materialType==2}">土石方</c:if>
                    </span>
                </p>
                <p>
                    <i>创建时间：</i><span><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${project.projectCreateTime}" type="both"/>   </span>

                </p>
            </div>
            <!--头部 end-->
            <!--人员配置-->
            <div class="" style="margin-top: 20px;margin-left: 20px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">发货方</a></div>
            <div class="companyBasicMessage">
                <p>
                    <span>公司名称：</span><a>${project.sendCompanyName}</a>
                </p>
                <p>
                    <span>负责人：</span><a>${project.sendDutyUserName}</a>
                </p>
            </div>
            <div class="" style="margin-top: 20px;margin-left: 20px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">承运方</a></div>
            <div class="companyBasicMessage">
                <p>
                    <span>公司名称：</span><a>${project.transportCompanyName}</a>
                </p>
                <p>
                    <span>负责人：</span><a>${project.transportDutyUserName}</a>
                </p>
            </div>
            <div class="" style="margin-top: 20px;margin-left: 20px;"><img src="newassets/imgs/icon_chart_line.png"><a style=" font-size: 18px;padding-left: 10px;">收货方</a></div>
            <div class="companyBasicMessage">
                <p>
                    <span>公司名称：</span><a>${project.consigneeCompanyName}</a>
                </p>
                <p>
                    <span>负责人：</span><a>${project.consigneeDutyUserName}</a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function(){
        Page.showPageTitleBindReturnUrl();
        // window.bindBackBtn("projectMgmt.html");
    });
</script>
</html>
