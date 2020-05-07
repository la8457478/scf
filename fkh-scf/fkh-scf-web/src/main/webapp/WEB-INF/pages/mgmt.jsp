<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>金融监管平台-登录</title>
    <link type="text/css" rel="stylesheet" href="newassets/css/main.css">
	<!--登录界面360浏览器默认打开极速模式-->
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<!--[if IE]><script type="text/javascript" src="newassets/js/jquery1.7.2.min.js"></script><![endif]-->
	<script type="text/javascript" src="newassets/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="newassets/js/fkhwl/md5_a.min.js"></script>
    <script src="newassets/js/fkhwl/validate.js"></script>
    <script src="newassets/js/fkhwl/fkh.js"></script>
	<script src="newassets/js/placeholders.js"></script>
	<style>
		/**
        底部导航备案信息
        */
		.websiteText a{font-size: 12px;cursor: pointer;padding-top: 2px;color: #eeeeee36;}
		.websiteText img{position: relative;top:3px;padding-right: 3px;width: 16px;height: 16px;opacity: 0.6;}
		.websiteText  {position: absolute;  bottom: 10px;right: 15px;}
	</style>
</head>
<body style="background: #5c97bd;">
    <div class="managerLoginPage loginPage">
	    <div class="loginMain" id="loginMain" style="width: 770px;">
	        <div class="loginLogo">
				<img src="newassets/imgs/icon_login_login.png">
	        </div>
	        <!--管理员登陆内容-->
			<form method="post" id="loginForm" name="loginForm" action="login.html" onsubmit="return validateMgmtLogin();" autocomplete="off">
	            <div class="formLogin" style="margin-left: 200px;">
	                <h4>欢迎您，管理员</h4>
	                <input id="loginAccount" name="loginAccount" class="publicInput managerName focus" type="text" placeholder="输入用户名">
	                <input id="md5Passwd" class="publicInput managerPwd focus" type="password" placeholder="请输入密码">
	                <input type="hidden" name="loginPasswd" id="md5PasswdVal"/>
	                <div class="verificationCode">
	                    <input name="randomCode" id="randomCode" class="publicInput managerCode focus" type="text" placeholder="输入验证码">
	                    <img class="imgs" id="pic_random_code" name="pic_random_code" onClick="changeValidateCode()">
	                </div>
               		<input type="hidden" name="loginType" value="1"/>
	                <input type="submit" class="publicInput inputNameSubmit" id="submitLogin" value="登录">
	            </div>
	        </form>
			<div class="clear"></div>
	        <p class="fkfFooter">Copyright©2014-<span id="yearNow"></span> 返空汇 All Rights Reserved</p>
	    </div>
	</div>
	<!--底部备案信息 start -->
	<div class="websiteText">
		<a target="_blank"><img src="newassets/imgs/copy_rignt.png" style="display: inherit;">蜀ICP备14021396号</a>
		<a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=51019002002335" target="_blank"><img src="newassets/imgs/icon-gongan-beian.png" style="display: inherit;">川公网安备 51019002002335号</a>
		<a href="https://www.fkhwl.com/templets/1/fhkcms/images/fkh-zzdx.png" target="_blank" >增值电信业务经营许可证 B2-20150863</a>
	</div>
	<!--底部备案信息 end -->
</body>
<script type="text/javascript">
    $("#realYear").html(new Date().getFullYear());
	var oMain = document.getElementById('loginMain');
	(function(doc, win) {
		var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
				recalc = function() {
					$("#loginMain").show();
					oMain.style.left = (document.documentElement.clientWidth - oMain.offsetWidth) / 2 + 'px';
					oMain.style.top = (document.documentElement.clientHeight - oMain.offsetHeight) / 2.5 + 'px';
				};
		if(!doc.addEventListener) {
			return false;
		}
		win.addEventListener(resizeEvt, recalc, false);
		doc.addEventListener('DOMContentLoaded', recalc, false);
	})(document, window);
	if (navigator.appName === 'Microsoft Internet Explorer') { //判断是否是IE浏览器
		if (navigator.userAgent.match(/Trident/i) && navigator.userAgent.match(/MSIE 8.0/i)) { //判断浏览器内核是否为Trident内核IE8.0
			$(".loginPage .loginMain").css("position","static").css("margin","0 auto");
			$(".loginMain").css({marginTop:"180px"});
			$("#loginMain").show();

		}
	}
	$(document).ready(function(){
		$('input, textarea').placeholder();
		$('input[type=password]').placeholder();
		var infoTip = "${message}";
		if(infoTip.length > 0 && infoTip != 'null'){
			$("#message").html(infoTip);
		}
		if(infoTip != ""){
			selfAlert(infoTip);
		}
		//管理员用户退出获取用户名与密码赋值给输入框框 
	   	if(localStorage.getItem('adminName')){
		    $("#loginAccount").val(localStorage.getItem('adminName'));
		}
		if(localStorage.getItem('adminPasswd')){
	     	$("#md5Passwd").val(localStorage.getItem('adminPasswd'));
	   	}
		changeValidateCode();
        yearNow();
	});
    function yearNow() {
        var yearNow=window.document .getElementById ("yearNow");
        var year=new Date();
        yearNow.innerHTML=year.getFullYear();
    }
	function changeValidateCode() {
		var timenow = new Date().getTime();
		$("#pic_random_code").attr("src", "randomCode.html?d="+timenow);
	}
	
	$(function(){
		$(".focus").on('focus',function(){
              $(this).css('border',"1px solid #009dc7");
          });
          $(".focus").on('blur',function(){
              $(this).css('border',"1px solid #e5ebf3");
          });
          
         //返回登陆
         $("#returnLogin").on('click',function(){
             $(".formLogin").show();
             $(".formForget").hide();
         });
         $(".forgetPwd").on('click',function(){
             $(".formLogin").hide();
             $(".formForget").show();
         })
     });
      
	/********获取验证码*******/
    //定义时间60秒
	var VerificationTime=60;
 	function setTimes(obj) {
		if (VerificationTime == 0) {
            obj.removeAttribute("disabled");
            obj.value="免费获取验证码";
            VerificationTime = 60;
            return;
        } else {
            obj.setAttribute("disabled",true);
            obj.value="" + VerificationTime + "秒后获取";
            VerificationTime--;
        }
        setTimeout(function() {setTimes(obj)},1000)
    }
</script>
</html>
