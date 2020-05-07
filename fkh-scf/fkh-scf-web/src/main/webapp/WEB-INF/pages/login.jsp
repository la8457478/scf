<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>金融监管平台-登录</title>
    <link type="text/css" rel="stylesheet" href="newassets/css/main.css">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <!--登录界面360浏览器默认打开极速模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta name="renderer" content="webkit">
	<!--[if IE]><script type="text/javascript" src="newassets/js/jquery.1.7.2.min.js"></script><![endif]-->
   <script type="text/javascript" src="newassets/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="newassets/js/fkhwl/md5_u.min.js"></script>
    <script src="newassets/js/fkhwl/validate.js"></script>
    <script src="newassets/js/fkhwl/fkh.js"></script>
    <script src="newassets/js/placeholders.js"></script>
    <style>
        /*全屏背景图片*/
        body{
            background: url('assets/imgs/logo/login_bg.png') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
            overflow:hidden;
        }
        *{font-family: "微软雅黑";}
        .ieActive{margin: 0 auto}
        #randomCode{line-height:44px;}
        .loginPage .inputNames{line-height: 44px;}
        .loginPage .inputName{line-height: 44px;}
        /**
           底部导航备案信息
        */
        .websiteText a{font-size: 12px;cursor: pointer;padding-top: 2px;color: #eeeeee36;}
        .websiteText img{position: relative;top:3px;padding-right: 3px;width: 16px;height: 16px;opacity: 0.6;}
        .websiteText  {position: absolute;  bottom: 10px;right: 15px;}
        .loginPage .loginMain{width: 770px;}
        .loginPage .formLogin{height: 410px;}
    </style>
</head>
<body>
<%--<input type="text" id="text">--%>
<%--<select id="select">--%>
    <%--<option value="1">1</option>--%>
    <%--<option value="2">2</option>--%>
    <%--<option value="3">3</option>--%>
<%--</select>--%>
    <!--登陆-->
    <div class="loginPage">

       	<div class="loginMain" id="loginMain"  style="display: none;">
            <%--<img class="icon_login_login" src="assets/imgs/logo/login_logo.png">--%>
                <div class="new_login_logo">
                    <img class="icon_new_fkh_log" src="assets/imgs/logo/login_logo.png" style="margin-top: 130px !important;">
                    <%--<img class="icon_new_fkh_log_text" src="newassets/imgs/logo_scf.png">--%>
                    <p id="copyright" style="bottom: 5px;">Copyright©2014-<span id="realYear">2020</span> 返空汇 All Rights Reserved</p>
                </div>
			<form method="post" name="loginForm" action="login.html" onsubmit="return validateUserLogin();" autocomplete="off">
               	<div class="formLogin">
                    <h4>用户登录</h4>
                    <input type="text" class="publicInput inputName focus" id="loginAccount" maxlength="11" name="loginAccount" autocomplete="off"  placeholder="输入账号或手机号">
                    <input type="password" class="publicInput inputNames focus" id="md5Passwd" placeholder="请输入密码">
                    <input type="hidden" name="loginPasswd" id="md5PasswdVal"/>
                    <div class="verificationCode">
                       <input type="text" class="publicInput inputNameOne focus" name="randomCode" id="randomCode"  placeholder="输入验证码">
                       <img class="imgs" id="pic_random_code" name="pic_random_code" onClick="changeValidateCode()">
                    </div>
                    <div style="clear: both;"></div>

                    <%--<div style="display: inline;margin-left: 35px;">--%>
                        <%--<span style="display: inline;float:right;margin-right:40px;" class="forgetPwd" id="cleanPasswdSpan">清除密码</span>--%>
                    <%--</div>--%>

                    <input type="hidden" name="loginType" value="2"/>
                    <input type="submit" style="height: 50px;border-radius:50px;background: #0071db;" class="publicInput inputNameSubmit" id="submitLogin" value="登录">
           	    </div>
           	</form>

<%--            <p id="copyright" style="display:none;bottom: 50px;">Copyright©2014-<span id="yearNow"></span> 返空汇 All Rights Reserved</p>--%>
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
    var myDate = new Date();
   var year =  myDate.getFullYear(); //获取完整的年份
    var copyRight = "Copyright©2014-"+year+" 返空汇 All Rights Reserved";
    $("#copyright").text(copyRight)
    $("#copyright").show();
    $("#realYear").html(new Date().getFullYear());


//    $(".icon_new_fkh_log").attr("src", "newassets/imgs/icon_new_fkh_log.png");
//    $(".icon_new_fkh_log_text").attr("src", "newassets/imgs/icon_new_fkh_log_text.png");

   $(".downLoadBtn").hover(function(){
       $(this).find("div").show();

   },function(){
       $(this).find("div").hide();

   });
    //防止出现在实时监控iframe中
    if(window != top) {
        top.location.reload();
    }

    $("#text").blur(function(){
        var length = $(this).val().length;
        if(length == 6){
            $("#select option[value='3']").hide();
        }else{
            $("#select option[value='3']").show();
        }
    });

    var oMain = document.getElementById('loginMain');
    (function(doc, win) {
        var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                recalc = function() {
                    $("#loginMain").show();
                    oMain.style.left = (document.documentElement.clientWidth - oMain.offsetWidth) / 2 + 'px';
                    oMain.style.top = (document.documentElement.clientHeight - oMain.offsetHeight) / 2 + 'px';
                };
        if(!doc.addEventListener) {
            return false;
        }
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);
    if (navigator.appName === 'Microsoft Internet Explorer') { //判断是否是IE浏览器
        if (navigator.userAgent.match(/Trident/i) && navigator.userAgent.match(/MSIE 8.0/i)) { //判断浏览器内核是否为Trident内核IE8.0
            $("#loginMain").show();
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
		changeValidateCode();

		//普通用户退出获取用户名与密码赋值给输入框框
   	 	if(localStorage.getItem('username')){
        	$("#loginAccount").val(localStorage.getItem('username'));
        }
		if(localStorage.getItem('pwdname')){
        	$("#md5Passwd").val(localStorage.getItem('pwdname'));
        }

        $("#cleanPasswdSpan").click(function () {
            localStorage.removeItem('username');
            localStorage.removeItem('pwdname');
            $("#loginAccount").val("");
            $("#md5Passwd").val("");
        });
	});
    /*function changeValidateCode() {
        var timenow = new Date().getTime();
        setTimeout(function(){
            $("#").attr("src", "randomCode.html?d="+timenow);
        },500);

    }*/
    function changeValidateCode() {
        var timenow = new Date().getTime();
        $("#pic_random_code").attr("src", "randomCode.html?d="+timenow);
    }

	$(function(){
		$(".focus").on('focus',function(){
              $(this).css('border',"1px solid #0071db");
          });
          $(".focus").on('blur',function(){
              $(this).css('border',"1px solid #e5ebf3");
          });

         //返回登陆
         $("#returnLogin").on('click',function(){
             $(".formLogin").show();
             $(".formForget").hide();
             $(".new_login_logo").css("height","410px");
         });

         $("#forgetPWDSpan").on('click',function(){
             $(".formLogin").hide();
             $(".formForget").show();
             $(".new_login_logo").css("height","455px");
         })
     });

	/********获取验证码*******/
    //定义时间60秒
	var VerificationTime=60;
 	function setTimes(obj) {
		if (VerificationTime == 0) {
            obj.removeAttribute("disabled");
            obj.value="获取验证码";
            VerificationTime = 60;
            return;
        } else {
        	if(VerificationTime == 60){
				var iphone = $("#iphone").val();
				if (isNull(iphone)) {
					selfAlert("请输入5~11位登录账号！", function() {
						$("#iphone").focus();
					});
					return false;
				}
				if (iphone.length < 5 || iphone.length > 11) {
					selfAlert("请输入5~11位登录账号！", function() {
						$("#iphone").focus();
					});
					return false;
				}
        		ajaxGetLoginPasswdValidateSmsCode(iphone);
        	}
            obj.setAttribute("disabled",true);
            obj.value="" + VerificationTime + "秒后获取";
            $("#phoneCode").css("width","180px");
            VerificationTime--;
        }
        setTimeout(function() {setTimes(obj)},1000)
    }

 	function forgotLoginPasswd(){
 		var validate = validateUserForgot();
 		if(validate){
 			var confirmPassword = $("#confirmPassword").val();
 			$("#forgotMd5Passwd").val(MD5(confirmPassword));
 			ajaxSubmit(document.forgotLoginPasswdForm, function(resp){
 				if(resp.success){
 					selfAlert("找回密码成功，请返回登录!");
 					document.forgotLoginPasswdForm.reset();
 				}else{
 					selfAlert(resp.message);
 				}
 			});
 		}
 		return false;
 	}
</script>
</html>
