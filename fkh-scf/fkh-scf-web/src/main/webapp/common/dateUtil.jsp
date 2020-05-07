<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=jsPath%>/jquery/css/jquery.datetimepicker.css"/>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.datetimepicker.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var option = {
		format:'Y-m-d',
		timepicker:false
 	};				   
 	$('.datepicker').datetimepicker(option);
	$('.datepicker').attr("readonly", true);  //日期不能手动录入
 	option.timepicker = true;
 	option.format='Y-m-d H:i:s';
 	option.step = 10;
  	$('.datetimepicker').datetimepicker(option);
       $('.datetimepicker').attr("readonly", true);  //日期不能手动录入
	});
</script>