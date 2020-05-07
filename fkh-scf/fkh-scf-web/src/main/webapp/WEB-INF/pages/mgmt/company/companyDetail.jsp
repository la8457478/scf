<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>企业管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil span{margin-left: 30px;}
    .configOil span:first-child{margin-left: 0px;font-size: 14px;}
    .companyMgmtForm{
        width: 100%;
        line-height: 30px;
        background: white;
        border-radius: 3px;
    }
    /*.companyMgmtForm input{*/
        /*width: 300px;*/
        /*height: 25px;*/
    /*}*/
    /*.companyMgmtForm div{*/
        /*width: 500px;*/
        /*height: 25px;*/
        /*text-align: center;*/
        /*margin-top: 10px;*/
    /*}*/
    #saveRegisterCompany{
        width: 80px;
        height: 30px;
        background-color: #169BD5;
        color: #ffffff;
        border: 1px solid #169BD5!important;
        border-radius: 2px;
        text-align: center;
        cursor: pointer;
    }
    #companyMgmtForm table {
        border-collapse: separate;
        border-spacing: 10px;
    }
    #projectList tr td:nth-child(2n-1) {
        padding-left: 35px;
        text-align: right;
    }
    #projectList input {
        width: 200px;
        padding-left: 5px;
        outline: none;
        height: 28px;
        border:1px solid #ccc;
    }
    #projectList span{padding-left: 20px;}
    #projectList select{height: 28px;outline: none;border:1px solid #ccc;width: 100px;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>企业信息管理</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${companyId == null}">--%>
<%--                    <a>注册企业</a>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <a>企业详情</a>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<div class="newPublicHeader">
    <span style="font-size: 16px;padding-left: 30px;width: auto;" id="newPlanNameTextTitle">
        <c:if test="${readOnly == false}">
            注册企业
        </c:if>
        <c:if test="${readOnly}">
            企业详情
        </c:if>
    </span>
    <div class="publicDetailsModuleReturnBtn return">返回</div>
    <input type="hidden" id="changeRoleType" value="1">
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: white;overflow-y: auto;overflow-x: hidden;">
    <div class="companyMgmtForm">
        <form name="companyMgmtForm" autocomplete="off" id="companyMgmtForm">
            <input type="hidden" id="id" name="id">
            <table>
                <tbody id="projectList">
                    <tr>
                        <td>企业名称：</td>
                        <td><input name="companyName" placeholder="请填写企业公司名称" class="publicVoucherInput" maxlength="30"></td>
                    </tr>
                    <tr>
                        <td>企业简称：</td>
                        <td><input name="shortCompanyName" placeholder="请填写企业公司简称" class="publicVoucherInput" maxlength="30"></td>
                    </tr>
                    <tr>
                        <td>企业类型：</td>
                        <td>
                            <c:choose>
                                <c:when test="${userType == 1}">
                                    <input type="hidden" id="companyType" name="companyType" value="1">
                                    <input type="text" readonly="readonly" value="资金方" class="publicVoucherInput">
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" id="companyType" name="companyType" value="2">
                                    <input type="text" readonly="readonly" value="借款方" class="publicVoucherInput">
                                </c:otherwise>
                            </c:choose>

                        </input></td>
                    </tr>
                    <tr>
                        <td>登录账号：</td>
                        <td><input name="ownerLoginName" id="ownerLoginName" placeholder="请设置企业登录账号" class="publicVoucherInput" maxlength="20"></td>
                    </tr>
                    <tr>
                        <td>负责人：</td>
                        <td><input name="ownerName" id="ownerName" placeholder="请填写负责人姓名" class="publicVoucherInput" maxlength="20"></td>
                    </tr>
                    <tr>
                        <td>公司地址：</td>
                        <td>
                            <input type="hidden" id="provinceName" name="provinceName">
                            <input type="hidden" id="cityName" name="cityName">
                            <input type="hidden" id="areaName" name="areaName">
                            <select id="provinceId" name="provinceId">
                                <option value="">请选择省</option>
                                <c:forEach items="${provinceList}" var="item">
                                    <option value="${item.id}">${item.province}</option>
                                </c:forEach>
                            </select>
                            <select id="cityId" name="cityId">
                                <option value="">请选择市</option>
                            </select>
                            <select id="areaId" name="areaId" style="display: none">
                                <option value="">请选择区县</option>
                            </select>
                            <input name="companyAddress" placeholder="请填写企业详地址" class="publicVoucherInput" maxlength="30">
                        </td>
                    </tr>
                    <tr>
                        <td>营业执照号码：</td>
                        <td><input name="businessLicenseNo" placeholder="请填写营业执照号码" class="publicVoucherInput" maxlength="15"></td>
                    </tr>
                    <tr>
                        <td>法人姓名：</td>
                        <td><input name="legalPerson" placeholder="请填写法人姓名" class="publicVoucherInput" maxlength="20"></td>
                    </tr>
                    <tr>
                        <td>法人身份证号码：</td>
                        <td><input name="idCardNo" placeholder="请填写身份证号码" class="publicVoucherInput" maxlength="18"></td>
                    </tr>
                    <tr>
                        <td>法人手机号码：</td>
                        <td><input name="userMobileNo" placeholder="请填写法人手机号码" class="publicVoucherInput" maxlength="11"></td>
                    </tr>
                    <tr>
                        <td>公司电话：</td>
                        <td><input name="companyTel" placeholder="请填写公司电话" class="publicVoucherInput" maxlength="11"></td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>企业注册类型：</td>--%>
                        <%--<td>--%>
                            <%--<input name="registerType" placeholder="请填写企业注册类型" class="publicVoucherInput" maxlength="30">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td>经营范围：</td>
                        <td><input name="businessScope" placeholder="请填写经营范围" class="publicVoucherInput" maxlength="100"></td>
                    </tr>
                    <tr>
                        <td>注册资本：</td>
                        <td><input name="registeredCapital" placeholder="请填写注册资本" class="publicVoucherInput" maxlength="8"><span style="padding-left: 10px;">万</span></td>
                    </tr>
                    <tr>
                        <td>成立时间：</td>
                        <td><input id="establishingTime" name="establishingTimeStr" placeholder="请选择成立时间" class="publicVoucherInput" readonly></td>
                    </tr>
                    <tr>
                        <td>备注信息：</td>
                        <td><input name="remark" placeholder="" class="publicVoucherInput" maxlength="30"></td>
                    </tr>
                </tbody>
            </table>
            <div class="driverRegisterMain">
                <div class="uploadImg uploadImg1">
                    <div class="uploadImg_pic">
                        <input accept="image/jpeg,image/jpg,image/png" type="file" name="file" class="upload" id="upload_driverCarPicture" size="1" data-fileType="3" onclick="initPictureUpload();">
                        <div id="businessLicenseNoDiv" class="publicImgsShow"><img></div>
                        <input type="hidden" name="businessLicensePicture" id="businessLicensePicture">
                    </div>
                    <a>营业执照</a>
                </div>
                <div class="uploadImg uploadImg2">
                    <div class="uploadImg_pic">
                        <input accept="image/jpeg,image/jpg,image/png" type="file" name="file" class="upload" id="upload_driverCarPictureBack" size="1" data-fileType="4" onclick="initPictureUpload();">
                        <div id="businessLicensePictureDiv" class="publicImgsShow"><img></div>
                        <input type="hidden" name="idCardPicture" id="idCardPicture">
                    </div>
                    <a>法人身份证</a>
                </div>
            </div>
            <c:if test="${sessionUser.authMap.COMPANY_ADD == true && readOnly == false}">
                <div style="clear: both;padding-left: 200px;">
                    <input onclick="saveOrUpdate();" id="saveRegisterCompany" value="注册">
                </div>
            </c:if>
        </form>
    </div>

    <!--公用导航模块end-->
    <jsp:include page="../../includes/uploadImg.jsp"></jsp:include>
</div>


<!--单据复核列表end-->
</body>
</html>

<script type="text/javascript">
    var selCityId = 0;
    var selAreaId = 0;
    var companyId = "${companyId}";
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        // //返回按钮
        // $(".return").on("click", function () {
        //     window.backToPage("companyMgmt.html");
        // });

        //获取企业详情
        if (!P.isNullOrEmptyString(companyId)) {
            getDetail(companyId);

            //设置主账号不能修改
            $("#ownerLoginName").attr("readOnly", true);
            $("#ownerName").attr("readOnly", true);
        }
        //初始化图片上传
        initPictureUpload();

        //省市县联动
        $("#provinceId").change(function () {
            var provinId = $(this).val();
            if (P.isNullOrEmptyString(provinId)) {
                $("#cityId").html('<option value="">请选择市</option>');
                $("#cityId").val('');
                $("#areaId").html('<option value="">请选择区域</option>');
                $("#areaId").val('');
                $("#areaId").hide();
                return false;
            }
            ajaxGet("chinaArea/listAreaByParentId/" + provinId + ".json", function (resp) {
                if (resp.success) {
                    var data = resp.data;
                    var html = '<option value="">请选择市</option>';
                    var isSelected = false;
                    $.each(data, function (i, item) {
                        if (selCityId == item.id) {
                            isSelected = true;
                            html += '<option value="'+item.id+'" selected>'+item.city+'</option>';
                        } else {
                            html += '<option value="'+item.id+'" >'+item.city+'</option>';
                        }
                    });
                    $("#cityId").html(html);

                    if (isSelected) {
                        $("#cityId").trigger("change");
                    }

                    $("#areaId").html('<option value="">请选择区域</option>');
                    $("#areaId").hide();
                } else {sumAmountColor
                    selfAlert(resp.message);
                }
            });
        });

        //省市县联动
        $("#cityId").change(function () {
            var cityId = $(this).val();
            if (P.isNullOrEmptyString(cityId)) {
                $("#areaId").html('<option value="">请选择区域</option>');
                $("#areaId").val('');
                $("#areaId").hide();
                return false;
            }
            ajaxGet("chinaArea/listAreaByParentId/" + cityId + ".json", function (resp) {
                if (resp.success) {
                    var data = resp.data;
                    var html = '<option value="">请选择区域</option>';
                    if (data.length > 0) {
                        $.each(data, function (i, item) {
                            if (selAreaId == item.id) {
                                html += '<option value="'+item.id+'" selected>'+item.area+'</option>';
                            } else {
                                html += '<option value="'+item.id+'" >'+item.area+'</option>';
                            }
                        });
                        $("#areaId").html(html);
                        $("#areaId").show();
                    } else {
                        $("#areaId").hide();
                    }
                } else {
                    selfAlert(resp.message);
                }
            });
        });

        if (${readOnly == true}) {
            $("#companyMgmtForm input").attr("readOnly",true);
            $("#companyMgmtForm select").attr("disabled",true);
            $("#upload_driverCarPicture").hide();
            $("#upload_driverCarPictureBack").hide();
        } else {
            laydate.render({elem: '#establishingTime',type: 'datetime',theme: 'molv'});
        }
    });

    //获取企业详情
    function getDetail(companyId) {
        ajaxGet("company/getDetail/"+companyId+".json", function (resp) {
            if (resp.success) {
                var data = resp.data;
                for(var i in data) {
                    if (i == "businessLicensePicture" || i == "idCardPicture") {//图片
                        $("input[name="+i+"]").val(data[i]);
                        if (!P.isNullOrEmptyString(data[i])){
                            $("#" + i).prev("div").find("img").attr("src", "${imageBaseUrl}" + data[i]);
                        }
                    } else if (i == "establishingTime") {//注册时间
                        $("#establishingTime").val(P.long2Datetime(data[i]));
                    } else {//其他属性
                        $("input[name="+i+"]").val(data[i]);
                    }
                    selCityId = data.cityId;
                    selAreaId = data.areaId;
                }

                $("#provinceId").val(data.provinceId).trigger("change");
                $(".publicImgsShow").find("img").click(function(){
                    var dataPic =  $(this).attr("src");
                    var sourceImg = dataPic.replace("thumbnail/thumbnail_", "images/company/");
                    if(dataPic.indexOf("thumbnail/thumbnail_")<0){
                        sourceImg = sourceImg.replace("thumbnail/", "images/company/");
                        sourceImg = sourceImg.replace("thumbnail_", "");
                    }
                    P.bigAlertPicture(sourceImg);
                });

            } else {
                selfAlert(resp.message);
            }
        });
    }

    //保存
    function saveOrUpdate() {
        var formData = P.formData(document.companyMgmtForm);

        if (!validateParams (formData)) {
            return false;
        }
        formData.ownerLoginName = formData.ownerLoginName.toLowerCase();
        formData.idCardNo = formData.idCardNo.toUpperCase();
        formData.businessLicenseNo = formData.businessLicenseNo.toUpperCase();
        //注册企业时：设置企业主行号默认密码
        if (P.isNullOrEmptyString(companyId)) {
            formData.userPassword = MD5("888888");
        }
        formData.businessLicensePicture = formData.businessLicensePicture.replace("${imageBaseUrl}","");
        formData.idCardPicture = formData.idCardPicture.replace("${imageBaseUrl}","");
        console.log(formData);
        ajaxPost("company/saveOrUpdate.json", formData, function (resp) {
            if (resp.success) {
                selfAlert("保存成功!",function () {
                    layer.closeAll();
                    Page.returnPage();
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }


    /**
     * 校验参数
     */
    function validateParams(formData) {
        if (isLengthOutRange(formData.companyName, true, 2, 30)) {
            selfAlert("请填写2-30字企业名称!");
            return false;
        }
        if (isLengthOutRange(formData.shortCompanyName, true, 2, 30)) {
            selfAlert("请填写2-30字企业简称!");
            return false;
        }

        if (isLoginAccountAndLengthOutRange(formData.ownerLoginName, true, 4, 20)) {
            selfAlert("请设置4-20位英文+数字登录账号!");
            return false;
        }

        if (isChineseCharLengthOutRange(formData.ownerName, true, 2, 20)) {
            selfAlert("请填写2-20字中文负责人姓名!");
            return false;
        }

        if (P.isNullOrEmptyString(formData.provinceId)) {
            selfAlert("请选择省!");
            return false;
        }
        formData.provinceName = $("#provinceId :selected").html();

        if (P.isNullOrEmptyString(formData.cityId)) {
            selfAlert("请选择市!");
            return false;
        }
        formData.cityName = $("#cityId :selected").html();

        if ($("#areaId").css("display") != "none") {
            if (P.isNullOrEmptyString(formData.areaId)) {
                selfAlert("请选择区县!");
                return false;
            } else {
                formData.areaName = $("#areaId :selected").html();
            }
        } else {
            formData.areaId = 0;
            formData.areaName = "";
        }

        if (isLengthOutRange(formData.companyAddress, true, 5, 30)) {
            selfAlert("请填写5-30字公司地址!");
            return false;
        }

        if (isCharOrNumberLengthOutRange(formData.businessLicenseNo, true, 15, 15)) {
            selfAlert("请填写15位营业执照号码!");
            return false;
        }

        if (isChineseCharLengthOutRange(formData.legalPerson, true, 2, 20)) {
            selfAlert("请填写2-20字中文法人姓名!");
            return false;
        }
        if (isChineseCharLengthOutRange(formData.legalPerson, true, 2, 20)) {
            selfAlert("请填写2-20字中文法人姓名!");
            return false;
        }
        if (P.isNullOrEmptyString(formData.idCardNo) || !idCodeValid(formData.idCardNo.toUpperCase())) {
            selfAlert("请填写法人身份证号码!");
            return false;
        }

        if (P.isNullOrEmptyString(formData.userMobileNo) || !isMobileNoLogin(formData.userMobileNo)) {
            selfAlert("请填写11位法人手机号码!");
            return false;
        }

        if (P.isNullOrEmptyString(formData.companyTel) || !isPhoneNumber(formData.companyTel)) {
            selfAlert("请填写8-11位公司电话!");
            return false;
        }

        // if (isLengthOutRange(formData.registerType, true, 2, 30)) {
        //     selfAlert("请填写2-30字企业注册类型!");
        //     return false;
        // }

        if (isLengthOutRange(formData.businessScope, true, 2, 100)) {
            selfAlert("请填写企业经营范围!");
            return false;
        }

        if (P.isNullOrEmptyString(formData.registeredCapital) || !isNumber8(formData.registeredCapital) || formData.registeredCapital == 0) {
            selfAlert("请填写企业注册资本!");
            return false;
        }

        if (P.isNullOrEmptyString(formData.establishingTimeStr)) {
            selfAlert("请选择公司成立时间!");
            return false;
        }

        if(P.isNullOrEmptyString(formData.businessLicensePicture)){
            selfAlert("请上传营业执照!");
            return false;
        }

        if(P.isNullOrEmptyString(formData.idCardPicture)){
            selfAlert("请上传法人身份证!");
            return false;
        }

        if (isLengthOutRange(formData.remark, false, 2, 30)) {
            selfAlert("请填写2-30字备注信息!");
            return false;
        }

        return true;
    }

    /**
     * 初始化图片上传
     */
    function initPictureUpload(){
        var d5 = new $.fileUpload();
        d5.init({"inputObj":$('#upload_driverCarPicture'),"previewContiner":$('#businessLicenseNoDiv')});

        var d6 = new $.fileUpload();
        d6.init({"inputObj":$('#upload_driverCarPictureBack'),"previewContiner":$('#businessLicensePictureDiv')});
    }

</script>
