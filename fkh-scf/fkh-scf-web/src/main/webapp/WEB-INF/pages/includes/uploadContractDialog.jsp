<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .uploadContractDialogContainer{


        display: none;
        margin-top: 18px;
        position: relative;
    }
    .uploadContractDialogContainer .file {
        position: absolute;
        left: 20px;
        cursor: pointer;
        height: 25px;
        filter: alpha(opacity:0);
        opacity: 0;
        width: 280px;
        border: 1px solid red;
    }
    .txts{float: left;    height: 25px;  border: 1px solid #cdcdcd;  width: 180px;  font-size: 12px;}
    .btn {
        background-color: #FFF;
        height: 27px;
        width: 70px;
        float: none;
        border: 1px solid #ccc !important;
    }
    #upMessage{text-align:center;color: red;}
</style>
<div class="publicContainerShow uploadContractDialogContainer " ondragstart="return false">
	<form name="fileForm" id="fileForm" enctype="multipart/form-data" method="post">
		<input type="hidden" name="tempUrl" id="tempUrl" value="">
		<div style="margin: 20px 10%;">
			请选择要上传的合同文件(支持格式:pdf):<br><br>
            <input type="file" name="file" class="file" accept="application/pdf" id="file" size="28" onChange="changeName(this)">
			<input type="text" name="textfield" id="textfield" class="txts" placeholder="请选择pdf文件上传">
            <input type="button" style="    margin-left: 10px;margin-right: 5px;" class="fileBrowse btn" value="浏览...">
		</div>
		<div id="upMessage">
		</div>
		<div style="width: 100%;text-align: center;margin-top: 50px;">
			<input type="hidden" name="fileType" id="fileType" value="1">
			<input type="button" value="确定"  style="height: 28px;" class="newPublicAlertBtn new_public_blue_bg" onClick="ajaxFileUpload(this);">
			<input style="margin-left: 40px;" type="button" value="关闭" class="newPublicAlertBtn closeBtnUploads borderColorBoxBtn" onClick="coloseUpload()">
		</div>
    </form>
    <input type="hidden" id="fileName">
</div>
<script type="text/javascript">
	function submitUpload(){
    	var tempUrl = $("#tempUrl").val();
		if(isNull(tempUrl)){
			$("#upMessage").html("请选择要上传的合同文件!");
			return false;;
		}
        var fileName = $("#fileName").val();
		updatePorjectContract(fileName,tempUrl);
		layer.closeAll();
		return false;
	}

	function coloseUpload(){
		//frameElement.api.close();
		layer.closeAll();
	}

	function checkUploadFile(){
     	var val = $("#file").val();
       	if(val == ''){
			$("#upMessage").html("请选择要上传的合同文件!");
            return false;
        }
        var stuff = val.toLowerCase().replace(/.+\./, "");
        stuff = stuff.toLowerCase();
        if(stuff != 'pdf'){
        	$("#upMessage").html('文件类型不匹配，请选择pdf格式文件!');
           return false;
        }
    	return true;
    }
	function ajaxFileUpload(obj){

		if(!checkUploadFile()){
			return false;
		}
		$("#upLoading").show();
        $(obj).attr("disabled",true);
		var fileType = $("#fileType").val();
		$.ajaxFileUpload({
			url:'file/uploadFile.html',
			secureuri:false,
			fileElementId:'file',
			dataType: 'html',
			data:{id:1,fileType:fileType},
			success: function (data, status){
				$("#upLoading").hide();
				if(data.indexOf("success") == 0){
                    $("#upMessage").show();
					$("#upMessage").html("上传成功!");
                    $(obj).attr("disabled",false);
					data = data.substring(7);
					$("#tempUrl").val(data);
                    var fileName = $("#fileName").val();
					$("#tempImg").attr("src",data);
					$("#tempLink").attr("href",data);
					$("#tempFileName").val(fileName);
					$("#thPanel").show();
					submitUpload();
				}else{
					$("#upMessage").html(data);
				}
			},
			error: function (data, status, e){
				$("#upMessage").html(e);
				$("#upLoading").hide();
                if(data.responseText.indexOf("success") == 0){
                    $("#upMessage").show();
                    $("#upMessage").html("上传成功!");
                    $(obj).attr("disabled",false);
                    var  pathPdf = data.responseText.substring(7);
                    $("#tempUrl").val(pathPdf);
                    var fileName = $("#fileName").val();
                    $("#tempImg").attr("src",pathPdf);
                    $("#tempLink").attr("href",pathPdf);
                    $("#tempFileName").val(fileName);
                    $("#thPanel").show();
                    submitUpload();
                }else{
                    $("#upMessage").html(data);
                }

			}
		});

		return false;
	}
    function changeName(obj){
        var fileName = getFileName($(obj).val());
        $("#fileName").val(fileName);
        $("#textfield").val($(obj).val());
    }
    function getFileName(o){
        var pos=o.lastIndexOf("\\");
        return o.substring(pos+1);
    }
</script>
