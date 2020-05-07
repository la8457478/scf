<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String ApplicationPath = request.getContextPath();
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--  指示IE使用最高内核宣染，或者使用Chrome内核宣染-->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <!--  如果可以使用webkit内核宣染 end-->
    <meta name="renderer" content="webkit">
    <!--指示界面手机界面禁止缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="newassets/css/main.css">
    <link rel="stylesheet" type="text/css" href="newassets/css/header.css">
    <link type="text/css" rel="stylesheet" href="newassets/jedate/skin/jedate.css">
    <link type="text/css" rel="stylesheet" href="newassets/css/zTreeStyle.css">
    <link type="text/css" rel="stylesheet" href="newassets/css/selectArea.css">
    <link type="text/css" rel="stylesheet" href="newassets/css/jquery.page.css">
    <title>金融监管平台-首页</title>
    <style>
        .childrenMenuList ul li{text-align: center;line-height: 57px;color: white;}
    </style>
</head>
<body>
<!--公用header-->
<div class="new_publicHeader">
    <img src="assets/imgs/logo/top_bg.png" style="height: 57px;">
    <c:if test="${empty companyType}">
        <img id="topLogsImg" src="assets/imgs/logo/login_logo.png" style="position: absolute;left: 20px;top: 10px;">
    </c:if>
    <c:if test="${companyType==1}">
        <img id="topLogsImg" src="assets/imgs/logo/logo2.png" style="position: absolute;left: 20px;top: 10px;">
    </c:if>

    <c:if test="${companyType==2}">
        <img id="topLogsImg" src="assets/imgs/logo/logo1.png" style="position: absolute;left: 20px;top: 10px;">
    </c:if>
    <div class="childrenMenuList">
        <ul>
            <li style="width: 140px;border-left:1px solid #1c2550;">
                ${sessionUser.nickName }
            </li>
            <li onclick="H.profile();" style="width: 100px;border-left:1px solid #1c2550;">
                个人资料
            </li>
            <li style="width: 100px;border-left:1px solid #1c2550;" onclick="H.logout();">
                <span>退出</span>
            </li>
        </ul>
    </div>
</div>
<div class="new_menu_publicContainer">
    <div class="publicLeftMenu" style="position: absolute;display: none;">
        <%--<div class="userMain">--%>
            <%--<img src="newassets/imgs/icon_bgs_1.png">--%>
            <%--<div class="userMessage">--%>
                <%--<div class="userMessageText" style="    float: left;display: block;overflow: hidden;width: 110px;white-space: nowrap;text-overflow: ellipsis;">--%>
                    <%--<span style="padding-left: 10px;" title="${sessionUser.nickName}">${sessionUser.nickName}</span>--%>
                <%--</div>--%>

                <%--<div class="leftAnimate" onclick="animateShowHide(this,1)" style="width:35px;">--%>
                    <%--<img title="收起"  id="animateShowHides" src="newassets/imgs/icon_left_animate.png">--%>
                <%--</div>--%>
                <%--<a class="welcomeText" style="float: right;left: 8px;padding-left: 0px;">欢迎您！</a>--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="menuList">
            <%@ include file="includes/menus.jsp"%>
        </div>
    </div>
    <!--公用header end-->
    <div class="publicRightMain" id="publicRightMain">
        <div class="publicRightContainer" id="publicRightContainer"></div>
    </div>
</div>


<%--<div class="changeLoginPasswdPanel" style="display: none;">--%>
    <%--<form action="scfUser/modifyLoginPwd.json" name="loginPasswdForm" id="loginPasswdForm" onsubmit="return PF.changeLoginPasswd();">--%>
        <%--<div class="modifyPassWord">--%>
            <%--<table>--%>
                <%--<tr>--%>
                    <%--<td style="width:100px;">--%>
                        <%--<span><img src="newassets/imgs/Required.png">旧密码：</span>--%>
                    <%--</td>--%>
                    <%--<td><input type="password" id="usedPwd" class="pwdInput" placeholder="请输入旧密码" maxlength="20"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<span><img src="newassets/imgs/Required.png">新密码：</span>--%>
                    <%--</td>--%>
                    <%--<td><input type="password" id="newPwd" class="pwdInput" placeholder="请输入新密码" maxlength="20"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<span><img src="newassets/imgs/Required.png" >确认密码：</span>--%>
                    <%--</td>--%>
                    <%--<td><input style="display:none"><input type="password" id="confirmPwd" class="pwdInput" placeholder="请输入确认密码" maxlength="20"></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td colspan="2" align="center">--%>
                        <%--<br/>--%>
                        <%--<input type="hidden" name="newPassword" id="md5Passwd"/>--%>
                        <%--<input type="hidden" name="oldPassword" id="oldMd5Passwd"/>--%>
                        <%--<input type="hidden" name="id" id="id" value="${sessionUser.id }"/>--%>
                        <%--<input class="publicBtn new_public_blue_bg" type="submit"  value="保存"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</form>--%>
<%--</div>--%>
</body>


<script type="text/javascript">

    var oparent;
    var noReadTask1, noReadMessage1;

    function testingMessage(resp){
        //ajax获取的最新值
        var noReadTask =  resp.data.noReads.noReadTask;
        var noReadMessage = resp.data.noReads.noReadCount;
        //记录的值
        if (noReadTask1 == null || noReadMessage1 == null) {
            noReadTask1 = document.cookie=noReadTask;
            noReadMessage1 = document.cookie=noReadMessage;
        } else {// 比较
            if(noReadTask > noReadTask1 || noReadMessage > noReadMessage1){
                $("#alertMessage").animate({marginRight:"0px"});
            }
        }
    }

</script>

<script src="newassets/js/jquery-1.11.0.min.js"></script>
<script src="newassets/js/page.js"></script>
<script src="newassets/js/base.js"></script>
<script type="text/javascript" src="newassets/jedate/jquery.jedate.js"></script>
<script src="newassets/js/highcharts.js"></script>
<script src="newassets/js/no-data-to-display.src.js"></script>


<script type="text/javascript" src="newassets/js/fkhwl/fkh.js"></script>
<script type="text/javascript" src="newassets/js/fkhwl/validate.js"></script>
<%--<script type="text/javascript" src="newassets/js/changeProject.js"></script>--%>
<script type="text/javascript" src="newassets/js/layer/layer.js"></script>
<script type="text/javascript" src="newassets/js/framework.js"></script>
<script type="text/javascript" src="newassets/js/fkhwl/fkh_page.js"></script>
<script type="text/javascript" src="newassets/js/new_base.js"></script>
<script type="text/javascript" src="newassets/js/luhmCheck.js"></script>
<script type="text/javascript" src="newassets/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="newassets/js/ajaxfileupload.js"></script>
<%--<script type="text/javascript" src="newassets/js/utility.js"></script>--%>
<script type="text/javascript" src="newassets/js/placeholders.js"></script>
<script type="text/javascript" src="newassets/js/fkhwl/md5_u.min.js"></script>
<script type="text/javascript" src="newassets/laydate/laydate.js"></script>
<script type="text/javascript" src="${baiduMapUrl}"></script>
<script type="text/javascript" src="newassets/js/historyRoute.js"></script>

<script type="text/javascript">
    var globalConfig = {webPath : "<%=ApplicationPath%>"};
    if(window.top.location.href.indexOf("192.168.") == -1 &&
       window.top.location.href.indexOf("webgis") == -1){
        globalConfig.webPath = "../";
    }
</script>

<script type="text/javascript">
    //处理页面的跳转，返回问题
    //设计：存储list,页面名称和链接地址
    var pageMapList=[];
    //加载一级页面和链接地址。

    //返回按钮，回退一级
</script>

<%--<script type="text/javascript" src="newassets/js/fkhwl/selectArea.min.js"></script>--%>

<script type="text/javascript">
    $(document).ready(function(){
        setTimeout(function(){
            $(".menuList ul li a").eq(0).click();
        })
        /*ProjectDialog.setFrom(document.selectProjectForm);*/
        <%--ProjectDialog.setProjectId(${onlineUser.globalProjectId});--%>
        <%--ProjectDialog.setProjectName('${onlineUser.globalProject.projectName}');--%>
        $("#switchProjectDiv").click(function () {
            ProjectDialog.showSelectProjectDialog(1);
        });
        //菜单调用私有模块方法home
        home.handMenu();
        <%--ProjectDialog.callback (function(projectName){--%>
            <%--$("#viewProjectName").html(ProjectDialog.getProjectName());--%>
            <%--$("#viewProjectName").attr("title",ProjectDialog.getProjectName());--%>
            <%--switchProject();--%>
        <%--});--%>

        <%--if(ProjectDialog.getProjectName() != ""){--%>
            <%--$("#viewProjectName").html(ProjectDialog.getProjectName());--%>
            <%--$("#viewProjectName").attr("title",ProjectDialog.getProjectName());--%>
            <%--&lt;%&ndash;ProjectDialog.setCurrentProjectInfo('${onlineUser.globalProject.sendCompanyName}','${onlineUser.globalProject.transportCompanyName}','${onlineUser.globalProject.consigneeCompanyName}');&ndash;%&gt;--%>
        <%--}--%>

        //修改运输公司消息显示布局
        if($(".publicMessage").length == 1){
            $(".publicMessage").css("margin-top","42px");
        }

        initReady();

        //跳转个人中心-我的钱包
        if("${recharge}" === "1") {
            containerShow('profile.html?isFinancial=1',"get");
        } else if ("${target}" == 'weightEdit') {
            //
            H.newMenuActive();
            var currentLi = $(".menuList li[url='home/${target}.html']");
            var parentLi = currentLi.parent().parent();
            parentLi.children("a").trigger("click");
            currentLi.trigger("click");
        } else {
            containerShow('home.html',"get");
        }

        //加载未读代办和消息
//        getMessageRadius();

        <%--P.globalProjectType = "${onlineUser.globalProjectType}";--%>

//        getListWaybillCarMessage();

        //点击跳转至待办
        $("#messageCenterShowLoad_down").parent().click(function () {
            layer.closeAll();
            containerShow("home/todo.html");
            H.newMenuActive();
        });
        //点击跳转至消息
        $("#messageCenterShowLoads_down").parent().click(function () {
            layer.closeAll();
            containerShow("messages/toMessageList.html");
            H.newMenuActive();
        });
    });
   //移除来否则IE报错

    //公用左侧菜单展开与收缩效果
    var flag = true;
    var width = document.body.scrollWidth;
    function animateShowHide(obj,type){
        if(flag){
            $("#menuMessageTipCount").css({
                top:"10px",
                left:"33px"
            });
            $(".userMessageText").hide();
            $(".viewProjectNameText").hide();
            $(obj).parent().parent().parent().css("width","60px");
            $(".menuList ul li").css("overflow","hidden");
            $(".menuList ul li ul").slideUp(500);
            $(".userMessage img").css("margin-left","4px");
            $(".newSelectProjectContainer img").css("margin-left","4px");
            $(obj).find("img").attr("src","newassets/imgs/icon_right_animate.png");
            $("#animateShowHides").attr("title","展开");
            //动态计算布局方法
            initSize();
            //必须重新渲染图表
            initStatisticCharts(1);
            initStatisticCharts(2);
            initStatisticCharts(3);
            flag = false;
        }else{
            if(width <= 1024){
                $(obj).parent().parent().parent().css("width","180px");
            }else{
                $(obj).parent().parent().parent().css("width","200px");
            }
            $(".userMessageText").show();
            $(".viewProjectNameText").show();
            $("#menuMessageTipCount").css({
                top:"20px",
                left:"170px"
            });
            $(".userMessage img").css("margin-left","15px");
            $(".newSelectProjectContainer img").css("margin-left","15px");
            $(".menuList ul li").css("overflow","inherit");
            $(obj).find("img").attr("src","newassets/imgs/icon_left_animate.png");
            $("#animateShowHides").attr("title","收起");
            //动态计算布局方法
            initSize();
            //必须重新渲染图表
            initStatisticCharts(1);
            initStatisticCharts(2);
            initStatisticCharts(3);
            flag = true;
//            var w= $(".godChartMainLeft").width();
//            $("#chartContainer").highcharts().setSize(w-20,false);
        }
    }
        <c:forEach items="${menus}" var="menu">
             if("${menu.funcName}" == "首页"){
                 $(".menuList ul li a img[data=1]").siblings("imgsMenu").remove();
             }
        </c:forEach>
    //公用左侧菜单效果
    function menuListShow(obj,type){

        //清空记住分页
        P.globalForm = null;
        var path = $(obj).parent().attr('url');
        var _this = $(obj);
        var _thisCss = _this.siblings('ul').css('display');
        var datsType = $(obj).attr("data");
        if(datsType == 2){
            _this.addClass("menuListActive");
        }else{
            _this.next().addClass("avtiveNames");
        }
        _this.parent().siblings().find("a").removeClass("menuListActive");
        _this.parent().siblings().find("a").next().removeClass("avtiveNames");
        $(obj).find(".imgsMenu").attr("src","assets/imgs/menu/icon_memu02.png");
        _this.parent().siblings().find("a").find(".imgsMenu").attr("src","assets/imgs/menu/icon_memu01.png");
        $(".userMessageText").show();
        $(".viewProjectNameText").show();
        //$.when( $(".publicLeftMenu").animate({ width:220 }, 500) ).then(function(){   initSize();});
        $(".userMessage img").css("margin-left","15px");
        $(".newSelectProjectContainer img").css("margin-left","15px");
        if(width <= 1024){
            $(".publicLeftMenu").css("width","180px");
        }else{
            $(".publicLeftMenu").css("width","200px");
        }
        $("#menuMessageTipCount").css({
            top:"20px",
            left:"170px"
        });
        $("#animateShowHides").attr("src","newassets/imgs/icon_left_animate.png");
        $("#animateShowHides").attr("title","收起");
        //动态计算布局方法
        initSize();
        if(!flag) {
             //必须重新渲染图表
            if(type != 2){
                initStatisticCharts(1);
                initStatisticCharts(2);
                initStatisticCharts(3);
            }
        }
        flag = true;

        if(_thisCss == 'none'){
            _this.siblings('ul').slideDown(500).children('li');
            if(_this.siblings('ul').css('display') == 'block'){
                _this.parent('li').siblings('li').children('ul').slideUp(500);
                _this.parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
                _this.parent('li').siblings('li').children('ul').find("ul").slideUp(500);
                _this.parent('li').siblings('li').children('ul').find("a").removeClass('inactives');
            }
        }else{
            _this.removeClass('inactives');
            //控制自身菜单下子菜单隐藏
            _this.siblings('ul').slideUp(500);
            _this.find(".imgsMenu").attr("src","assets/imgs/menu/icon_memu01.png");

        }
        if(path == "undefined" || path == null){
            return false;
        }else{
            // containerShow(path,"get");
            Page.clickMenuToPage(path,$(obj).text())
            $(".menuList ul li ul").slideUp(500);
            $(".menuList ul li ul li").removeClass("menuListActives");
        }
    }


    //公用左侧菜单效果
    function iframeShow(obj, params){
        //清空记住分页
        P.globalForm = null;
        //实时监控页不显示消息提醒
        $("#alertMessage").hide();

        if (typeof(obj) == "string") {
            obj = $(".menuList li[url='" + obj + "'] a");
        }
        var url = $(obj).parent().attr('url');
        if (url == "monitor.html" || url == "monitorAdmin.html") { //实时监控
            url = params ? url + "?" + params : url;
        }

        $(".menuList ul li ul").slideUp(500);
        $(".menuList ul li ul li").removeClass("menuListActives");
        flag = true;
        $("#animateShowHides").attr("src","newassets/imgs/icon_left_animate.png");
        $(".menuList ul li a").removeClass("menuListActive");
        $(".publicLeftMenu").css("width","200px");
        $(".userMessageText").css("display","block");
        $(".viewProjectNameText").show();

        $(".userMessage img").css("margin-left","15px");
        $(".newSelectProjectContainer img").css("margin-left","15px");
        $(obj).addClass("menuListActive");
        P.showLoading();
        $("#publicRightContainer").html("<iframe id='monitorIframe' style='border:1px solid #e6e6ef;margin-top: 15px;' src='" + url + "'></iframe>");
        setTimeout(function(){
            P.hideDialog();
        },1000);

        initReady();
    }
    //子菜单效果
    function menuListShowChildren(obj,projectNameId){

        //清空记住分页
        P.globalForm = null;
        $("#alertMessage").show();

        $(obj).addClass("menuListActives");
        $(obj).find("a").find("img").attr("src","assets/imgs/menu/icon_check.png");
        $(obj).siblings().removeClass("menuListActives");
        $(obj).siblings().find("a").find("img").attr("src","assets/imgs/menu/icon_checked.png");
        $(obj).parent().parent().siblings().children('ul').find("li").removeClass("menuListActives").find("a").find("img").attr("src","assets/imgs/menu/icon_checked.png");
        //子菜单跳转对应的url
        var path = $(obj).attr('url');
        if(path == "undefined" || path == null){
            return false;
        }else{
            // containerShow(path,"get",projectNameId);
            Page.clickChildMenuToPage(path,$(obj).text(),$(obj).parent().parent().children(":first").text().trim());
        }

    }
    //顶部待办事项个人资料信息跳转
    function newNavShowLoad(obj,index){
        var path = $(obj).attr('url');
        if(path == "undefined" || path == null){
            return false;
        }else{
            containerShow(path,"get");
            H.newMenuActive();
        }
    }

    function getMessageRadius(){
        silenceGet("messages/noReadCount.json", function (resp) {
            home.showTipCount(resp);
        });
        setTimeout(getMessageRadius, 30*1000);
    }

    function searchSelectProject(){
        var projectSearch =  PF.projectValidate("projectName");
        if(projectSearch){
            ajaxSubmit(document.selectProjectForm, function(resp){
                ProjectDialog.parseSelectProjectObjToHtml(resp);
            });
        }

        return false;
    }


    //***********************************************修改密码 begin******************************************************
    /**
     * 修改密码弹出层
     */
//    function showChangeLoginPasswd(){
//        var obj = $(".changeLoginPasswdPanel");
//        layer.open({
//           type: 1,
//           title: ['修改登录密码', 'font-size:16px;color:white;background-color:#0070db;'],
//           area: ['400px', '235px'],
//           closeBtn:4,
//           content: obj
//       });
//    }




</script>

</html>
