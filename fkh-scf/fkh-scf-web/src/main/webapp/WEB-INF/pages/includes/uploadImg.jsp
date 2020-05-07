<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .uploadImg {
        width: 155px;
        height: auto;
        text-align: center;
        position: relative;
        float: left;
        margin-left:70px;
        margin-top: 20px;
    }
    .getCode {
        width: 105px;
        height: 30px;
        font-size: 13px;
        background: #F86106;
        border-radius: 0.5rem;
        color: white;
        float: right;
        margin-top: 0.4rem;
        margin-right: 36px;
    }
    .uploadImg_pic {
        width: 100%;
        height: 120px;
        background: url(newassets/imgs/icon_upload.png) no-repeat;
        background-size: 100% 100%;
    }
    .uploadImg a {
        font-size: 16px;
        color: #0071db;
        padding-top: 15px;
        display: inline-block;
    }
    .messageTxt{
        margin-top:50px;
    }
    .upload {
        position: absolute;
        width: 100%;
        height: 120px;
        z-index: 99999;
        opacity: 0;
        cursor: pointer;
        left: 0px;
    }
    .uploadImg_pic img {
        width: 100%;
        height: 120px;
        /*display: none;*/
    }
    .btnBox {
        width: 100%;
        height: auto;
        background: white;
        overflow: hidden;
        padding-bottom: 25px;
        cursor: pointer;
    }
    .nextBtn {
        width: 80%;
        height: 42px;
        background: #F86106;
        display: block;
        margin: 0 auto;
        margin-top: 50px;
        font-size: 18px;
        color: white;
        border-radius: 0.5rem;
        cursor: pointer;
    }

    .helpText{display: inline-block;width: 100px;height: 40px;float: right;}
    .selectImgCar {
        display: inline-block;
    }
    .submitFooterBtn {
        width: 100%;
        height: 2.75rem;

        background: white;
        text-align: center;
        overflow: hidden;
        padding-bottom: 25px;
        cursor: pointer;
    }
    .upDataImg {
        width: 100%;
        height:200px;
        background: white;

    }
    .submitFooterBtn input {
        width: 40%;
        height: 42px;
        background: #F86106;
        border-radius: 0.5rem;
        color: white;
        font-size: 18px;
        margin-left: 15px;
        cursor: pointer;
    }
    .publicSelect{width: auto;height: 25px;}
    #licensePlateNoLetter{margin-left: 5px;}
    #carNumber{margin-left: 5px;}
    .returnBtn{background: white !important;border:1px solid #f86106;color: #f86106 !important;}
    .searchHeader {  width: 100%;  height:45px;  line-height:45px;  padding-top: 20px;  background: white;  }
    .searchBtn {
        height: 30px;
        padding-left:15px;
        padding-right:15px;
        background: #F86106;
        color: white;
        border-radius: 2px;
        font-size: 0.6rem;
        margin-left: 0.8rem;
        vertical-align: middle;
        float: left;
    }
    .searchTitle {
        width: 100%;
        height: 25px;
        background: #ebeff3;
        font-size: 14px;
        line-height: 25px;
        color: #6e7278;
    }
    .searchTitle span {
        float: left;
        margin-left: 0.7rem;
    }
    .companyList ul li {
        width: 100%;
        height: 40px;
        line-height: 40px;
        border-bottom: 1px solid #eff2f4;
        font-size: 15px;
    }
    .companyList {
        width: 100%;
        height: 400px;
        overflow: auto;
    }
    .companyList ul li span {
        float: left;
        display: inline-block;
        margin-left: 0.7rem;
        width: 300px;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
    }
    .searchTitle a {
        float: right;
        margin-right:120px;
    }
    .companyList ul li a {
        float: right;
        margin-right:50px;
        color: black;
    }
    .icon_add {
        width: 65px;
        height: 65px;
        float: right;
        margin-right: 20px;
    }
    .addCompanyMessage ul li {
        width: 100%;
        height: 40px;
        border-bottom: 1px solid #dce7f3;
        font-size: 14px;
        line-height:40px;
    }
    .addCompanyMessage ul li span {
        display: inline-block;
        width: 120px;
        text-align: left;
        padding-left: 0.6rem;
        font-size: 14px;
    }
    .addCompanyShow{position: relative;}
    .submitCompanyMsg{position: absolute;bottom: 20px;}
    .publicShowLoadList ul li {
        width: 100%;
        height: 40px;
        border-bottom: 1px solid #dce7f3;
        font-size: 13px;
        line-height: 40px;
    }
    .publicShowLoadList ul li span {
        display: inline-block;
        width: 120px;
        text-align: left;
        padding-left: 15px;
        font-size: 13px;
    }
    .publicShowLoadList ul li a {
        font-size: 13px;
        color: black;
    }
    .publicBtnName {
        background: none !important;
        border: 1px solid #f86106 !important;
        color: #f86106 !important;
    }
    .icon_selectsName{margin-left: 0px;}
    .phoneBlurBtnBox{position: absolute;bottom: 20px;}
    .serverText {
        width: 100%;
        height: 2.4rem;
        text-align: center;
        font-size: 15px;
        color: #f86106;
    }
    .serverText span {
        display: block;
        padding-bottom: 0.2rem;
        padding-top: 0.5rem;
    }
    .serverText img {
        position: relative;
        top: 0.1rem;
    }
    .serverText a {
        color: #f86106;
        font-size: 17px;
    }
</style>
<div>

</div>
<script type="text/javascript">
    //图片上传
    var _URL = window.URL || window.webkitURL;
    (function($){
        $.fileUpload = function(){};
        $.fileUpload.prototype.init = function(opt){
            this.s = $.extend({
                                  "inputObj": $(''),
                                  "previewContiner": $(''),
                                  "imgobj": {},
                                  "formData": new FormData()
                              },opt||{});
            this.handle();
        };
        $.fileUpload.prototype.handle = function(){
            var _this = this;
            _this.s.inputObj.unbind("change").bind("change",function(){
                var file, img, imgName, src;
                var _this_ = $(this);
                var files = this.files[0];

                //判断图片类型
                if(!isImage(files.type)){
                    selfAlert("请选择jpg、png、jpeg格式的图片!");
                    return false
                }
                //判断文件图片大小
                var size = Math.floor(files.size/1024);
                if (size > 10000) {
                    selfAlert("上传文件不得超过10M!");
                    return false;
                }
                if ((file = this.files[0])) {
                    //把图片内容放到formdata中保存，key/value形式
                    _this.s.formData.append(imgName, file);//formData对象
                    //获取img的src用于展示
                    src = _URL.createObjectURL(file);
                    img = document.createElement("img");
                    _this_.next("div").find("img").attr("src",src).show();
                    $(this).prev().hide();
                }
                var id = _this_.attr("id");
                var type = _this_.attr("data-fileType");
                var nextId = _this_.next().next().attr("id");
                var dataForm ={
                    fileType:type
                };
                P.showLoading();
                $.ajaxFileUpload({
                     url:"file/uploadFile.html",
                     secureuri:false,
                     fileElementId:id,
                     dataType: 'text',
                     data:dataForm,
                     success: function (data, status){
                         if(data.indexOf("success") == 0){
                             data = data.substring(7);
                             $("#" + nextId).val(data);
                             P.hideDialog();
                         }else{
                             selfAlert(data);
                             $("#upMessage").html(data);
                             // P.hideDialog();
                         }
                     },
                     error: function (data, status, e){
                         if(data.responseText.indexOf("success") == 0){
                             data = data.responseText.substring(7);
                             $("#" + nextId).val(data);
                             P.hideDialog();
                         }else{
                             selfAlert(data);
                             $("#upMessage").html(data);
                         }
                     }
                 });
                //判断图片类型
                if(!isImage(files.type)){
                    selfAlert("请选择jpg、png、jpeg格式的图片!");
                    return false
                }
            })
        }
    })($);
</script>
