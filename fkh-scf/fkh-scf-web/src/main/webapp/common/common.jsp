<%@ page language="java" pageEncoding="UTF-8"%>
<%
   String ApplicationPath = request.getContextPath() ;
   String imgPath =  ApplicationPath+"/newassets/imgs";
   String cssPath =  ApplicationPath+"/newassets/css";
   String jsPath = ApplicationPath+"/newassets/js";
%>
<html>
<head>
	<meta http-equiv="Expires" content="0" />  
	<meta http-equiv="Cache-Control" content="no-cache, no-store" /> 
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<script>
	//全局的javascript配置
    var globalConfig = {webPath : "<%=ApplicationPath%>"};
    if(window.top.location.href.indexOf("192.168.") == -1 &&
       window.top.location.href.indexOf("webgis") == -1){
        globalConfig.webPath = "../";
    }
	</script>
	<link rel="stylesheet" type="text/css" href="<%=cssPath%>/FormTable.css" />
	<link rel="stylesheet" type="text/css" href="<%=cssPath%>/button.css" />
	<link rel="stylesheet" type="text/css" href="<%=jsPath%>/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=jsPath%>/easyUI/themes/icon.css">
	<script type="text/javascript" src="<%=jsPath%>/jquery/jquery1.8.min.js"></script>
	<script type="text/javascript" src="<%=jsPath%>/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=jsPath%>/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=jsPath%>/InfoWindow.js"></script>
   	<script type="text/javascript" src="<%=jsPath%>/utility.js"></script>
   	<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.form.js" ></script> 
    <script type="text/javascript" src="<%=jsPath%>/jquery/jquery.validate.js"></script><!--表单数据验证-->
    <script type="text/javascript" src="<%=jsPath%>/jquery/jquery.metadata.js" charset="UTF-8"></script><!--表单数据验证-->
	<script type="text/javascript">
		$(document).ready(function(){
			$.ajaxSetup({ cache: false });
	      	//判断消息提示是否有内容，如果有内容就显示，否则就隐藏
		  	var messageTip = $(".MessageFromServer");
		  	if(messageTip){
		  		var htmlContent = messageTip.html();
				if(!htmlContent || htmlContent.length == 0)
					$(".MessageFromServer").hide();
			}
	  	});
	</script>
