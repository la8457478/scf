/**
 * 导航条的相关事件
 * by liaohong on 2016/09/09
 */
window.mainContainer = "#publicRightContainer";
window.backToPage = function (url, data) {
    ajaxPost(url, data, function (resultHtml) {
        $(window.mainContainer).html(resultHtml);
    });
};
window.backToPage = function (url) {
    ajaxGet(url, function (resultHtml) {
        $(window.mainContainer).html(resultHtml);
    });
};
window.silenceBackToPage = function (url) {
    silenceGet(url, function (resultHtml) {
        $(window.mainContainer).html(resultHtml);
    });
};
window.bindBackBtn = function (url) {
    $(".return").on("click", function () {
        window.backToPage(url);
    });
};
window.bindBackBtnReturn = function (url) {
    $("#return").on("click", function () {
          window.backToPage(url);
    });
};
window.bindBackBtns = function (url) {
    $("#home-page").on("click", function () {
        window.backToPage(url);
        $(".Navli li").removeClass("curentsa").eq(0).addClass("curentsa");
    });
};
window.bindBackBtnes = function (url) {
    $(".waybillsPage").on("click", function () {
        window.backToPage(url);
    });
};
window.bindBackToSetting = function(){
	$("#backToSettingBtn").on("click", function () {
        window.backToPage("home/setting.html");
    });
};
window.bindBackToTransport = function(){
    $(".return").on("click", function () {
        window.backToPage("home/transportingList.html");
    });
};

window.bindBackToRecheck = function(){
    $(".return").on("click", function () {
        window.backToPage("home/recheck.html");
    });
};

window.bindBackToTodo = function(){
    $(".return").on("click", function () {
        window.backToPage("home/todo.html");
    });
};
window.clickReturn = function(){
    $(".return").click();
};

window.H = {

    closeMsg: function (obj) { //关闭消息弹窗
        $(obj).parent().parent().remove();
        return false;
    },
    setting : function(obj){ //设置
        var location = window.top.location.href;
        if(location.indexOf("index.html") != -1){ //首页
            ajaxGet("home/setting.html", function (resultHtml) {
                $(".Navli li").removeClass("curentsa");
                $(window.mainContainer).html(resultHtml);
            });
        }else{ //实时监控页
            window.open("index.html#turnToSetting", "_self");
        }
    },
    todo: function () { //待办事项
        ajaxGet("home/message.html", function (resultHtml) {
            $(".Navli li").removeClass("curentsa");
            $(window.mainContainer).html(resultHtml);
        });
    },
    profile: function () { //个人资料
        containerShow("profile.html");
        $("#personnalText").text("个人资料");
        $(".publicChildrenMenu").hide();
        H.newMenuActive();
    },
    financeManager:function(){ //我的钱包
        ajaxGet("profile.html",function(response) {
            $(window.mainContainer).html(response);
            P.hideDialog();
            $(".new_public_tab span").eq(1).click();
            $("#personnalText").text("我的钱包");
            $(".publicChildrenMenu").hide();
            H.newMenuActive();
            // P.currentPage = "home/profile.html";
        });
    },
    returnProject:function(){ //返回创建项目
    	$(".returnProject").on("click", function() {
    		$.ajax({
    			url : "home/projectMgmt.html",
    			type : 'get',
    			success : function(data) {
    				$("#main-container").html(data);
    			}
    		});
    	});
    },
    newMenuActive:function(){
        $(".menuList ul li a").removeClass("menuListActive");
        $(".menuList ul li ul").slideUp(500);
        $(".menuList ul li ul li").removeClass("menuListActives");
    },
    logout: function () { //用户退出
        selfConfirm("您确定要退出登录吗?", function () {
            //普通用户退出
            window.location.href = "logout.html";
        });
    },
    initNavigation: function (defaultIdx) { //初始化导航条
        if (defaultIdx) {
            if (defaultIdx == 1) { //实时监控页
                $(".Navli li").each(function (index) {
                    var path = $(this).attr('url');
                    if (path == "monitor.html" || path == "monitorAdmin.html") {
                        $("#UL li:eq(" + index + ")").addClass("curentsa");

                    }
                 });
                //修改bug实时监控消息中心不能跳转
                $("#meaasgeIcon").bind("click", function () {
                	window.open("index.html#home/message.html", "_self");
                });
//                $(".headPic").on('click', function () {
//                    window.open("index.html#user/profile.html", "_self");
//                });
                $(".Navli li").click(function () {
                    var path = $(this).attr('url');
                    if (path == "monitor.html" || path == "monitorAdmin.html") {
                        return;
                    } //当前页返回
                    window.open("index.html#" + path, "_self");
                });
                    //点击跳转至待办
                    $("#messageCenterShowLoad_down").parent().click(function () {
                         window.open("index.html#home/todo.html?tab=0", "_self");
                     });
                    //点击跳转至消息
                    $("#messageCenterShowLoads_down").parent().click(function () {
                         window.open("index.html#home/todo.html?tab=1", "_self");
                     });

            }else{

            }
        } else {
            //实时监控中，绑定右下角消息提示的点击
            //点击跳转至待办
            $("#messageCenterShowLoad_down").parent().click(function () {
                window.backToPage("home/todo.html?tab=0");
                $("#UL li").removeClass("curentsa");
                P.nowSubUrl = 'home/todo.html';
                $("#UL li[url='home/todo.html']").addClass("curentsa");
             });
            //点击跳转至消息
            $("#messageCenterShowLoads_down").parent().click(function () {
                window.backToPage("home/todo.html?tab=1");
                $("#UL li").removeClass("curentsa");
                P.nowSubUrl = 'home/todo.html';
                $("#UL li[url='home/todo.html']").addClass("curentsa");
             });

            $("#UL li").bind("click", function () {
                var index = $(this).index();
                H.turnNavigation($(this));
                // if(index == 7){
                //     $("#messageRadius").show();
                // }else{
                //     $("#messageRadius").hide();
                // }
            });
            //适配触屏设备

            $("#UL li").bind("touchstart", function () {
                H.turnNavigation($(this));
            });

            //给消息图标绑定事件 -->>修改BUG图标绑定事件
            $("#meaasgeIcon").bind("click", function () {
                H.todo();
            });


            //是否由实时监控页跳回
            var href = window.location.href;
            var isMonitorBack = href.split("#").length > 1 && href.split("#")[1] != "";
            var url = window.location.href.split("#")[1];
            if (isMonitorBack) {
                var obj = $("#UL li[url='" + url + "']");
                if(url.indexOf("?") == -1){
                    if (url == "home/message.html") {
                        H.todo();
                    } else if (url == "user/profile.html") {
                        H.profile();
                    } else {
                        if(url == "turnToSetting"){
                            H.setting();
                        }else if(url == "turnToProfile"){
                            H.profile();
                        }else if(url == "turnToFinance"){
                            H.financeManager();
                        }else{
                            H.turnNavigation(obj);
                        }
                    }
                }else{
                    //跳转到消息中心的消息详情
                    if(url.indexOf("home/message") != -1){
                        H.todo();
                    }else if(url.indexOf("home/todoList") != -1){
                        window.backToPage(url);
                        //显示当前模块背景色
                        $("#UL li").removeClass("curentsa");
                        P.nowSubUrl = 'home/todo.html';
                        $("#UL li[url='home/todo.html']").addClass("curentsa");
                    }
                }
            } else {
                if(url&&url.indexOf("home/todoList") != -1){
                     window.backToPage(url);
                }else{
                    H.turnNavigation();
                }
            }

        }

        //初始化监控页的父级容器样式
        $(".header").parent().parent().css({overflow: "visible", display: "block"});
        $(".Navlistata,.NavMore").on("click", function (e) {
            $(".Navli").toggleClass("visibles");
            $(document).one("click", function () {
                $(".Navli").removeClass("visibles");
            });
            e.stopPropagation();
        });
    },
    turnNavigation: function (obj, params) { //跳转到导航页
        if (typeof(obj) == "number") {
            obj = $("#UL li:eq(" + obj + ")");
        }
        if (typeof(obj) == "string") {
            obj = $("#UL li[url='" + obj + "'");
        }

        if (!obj) {
            obj = $("#UL li:eq(0)");
        }

        var nowSubUrl = obj.attr("url");
        //console.log(nowSubUrl);
        if (nowSubUrl == "monitor.html" || nowSubUrl == "monitorAdmin.html") { //实时监控
            var newUrl = obj.attr("url");
            newUrl = params ? newUrl + "?" + params : newUrl;
            window.open(newUrl, "_self");
            return;
        }

        $("#UL li").removeClass("curentsa");
        ajaxGet(nowSubUrl, function (resultHtml) {
            $("#publicRightMain").html(resultHtml);
        });
        P.nowSubUrl = nowSubUrl;
        obj.addClass("curentsa");
    }
};

/*
* 油气平台公司下拉框渲染通用方法
* */
function queryCompanyOption() {
    if(document.getElementById("companyId")){
    $.post("oilSiteCompanys/company/option.json", function (resp) {
            var optionCompany="<option value='-1'>请选择加油站公司</option>";
            var allCompany=resp.data.allCompany;
            for(var i=0;i<allCompany.length;i++){
                optionCompany += '<option value="'+allCompany[i].id+'">'+allCompany[i].companyName+'</option>';
            }
            $("#companyId").html(optionCompany);
    });
    }
}

/**
 * 个人中心的相关事件
 * by liaohong on 2016/09/09
 */
window.PF = {
    turnToProfile: function (pageName,type) { //跳转到个人资料相关页
        var url='turnTo/profile.html?pageName=' + pageName;
        if(type!=null&&type!=""){
            url+="&type="+type;
        }
        ajaxGet(url, function (resultHtml) {
            $(window.mainContainer).html(resultHtml);
            P.pushCurrentPage("turnTo/profile.html?pageName=" + pageName);
        });
    },
    /*
     * 四舍五入
     * 参数说明：
     * number：要格式化的数字
     * decimals：保留几位小数
     * dec_point：小数点符号
     * thousands_sep：千分位符号
     * var num=number_format(1234567.089, 2, ".", ",");//1,234,567.09
     * */
    formatMoneyUp:function(number, decimals){

        var dec_point=".";
        var thousands_sep=","
        decimals = decimals && decimals > 0 && decimals <= 20 ? decimals : 2;
        number = (number + '').replace(/[^0-9+-Ee.]/g, '');
        var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
            s = '',
            toFixedFix = function (n, prec) {
                var k = Math.pow(10, prec);
                return '' + Math.ceil(n * k) / k;
            };

        s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
        var re = /(-?\d+)(\d{3})/;
        while (re.test(s[0])) {
            s[0] = s[0].replace(re, "$1" + sep + "$2");
        }

        if ((s[1] || '').length < prec) {
            s[1] = s[1] || '';
            s[1] += new Array(prec - s[1].length + 1).join('0');
        }
        return s.join(dec);
},
    /*
     * 直接舍去
     * 参数说明：
     * number：要格式化的数字
     * decimals：保留几位小数
     * dec_point：小数点符号
     * thousands_sep：千分位符号
     * var num=number_format(1234567.089, 2, ".", ",");//1,234,567.08
     * */
    //添加三位小数
    formatMoney:function(num,type) {
         if(type == 3){
             return (num.toFixed(type) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
         }else{
             return (num.toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
         }
    },

    //公用搜索验证特殊字符方法
    projectValidate:function(obj1,obj2,obj3){
        var projectName = $("#"+obj1+"").val();
        if(!isNull(projectName)){
            if(obj1=="projectName"){
                if(!isCompanyName(projectName)){
                    selfAlert("不能输入特殊符号!",function(){$("#"+obj1+"").focus();});
                    return false;
                }
            }else{
                if(!isPassWordsCar(projectName)){
                    selfAlert("不能输入特殊符号!",function(){$("#"+obj1+"").focus();});
                    return false;
                }
            }
        }
        var companyName = $("#"+obj2+"").val();
        if(!isNull(companyName)){
            if(!isPassWords(companyName)){
                selfAlert("不能输入特殊符号!",function(){$("#"+obj2+"").focus();});
                return false;
            }
        }
        var publicValidate = $("#"+obj3+"").val();
        if(!isNull(publicValidate)){
            if(!isPassWords(publicValidate)){
                selfAlert("不能输入特殊符号!",function(){$("#"+obj3+"").focus();});
                return false;
            }
        }
        return true;
    },
    changeLoginPasswd: function () { //修改登录密码
        var validate = validateChangeLoginPasswd();
        if (validate) {
            var md5Passwd = $("#newPwd").val();
            var oldMd5Passwd = $("#usedPwd").val();
            $("#md5Passwd").val(MD5(md5Passwd));
            $("#oldMd5Passwd").val(MD5(oldMd5Passwd));
            ajaxSubmit(document.loginPasswdForm, function (resp) {
                if (resp.success) {
                    selfAlert("修改成功!", function () {
                        document.loginPasswdForm.reset();
                        layer.closeAll();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        }
        return false;
    },
    imgPicRotateFn:function(){
        current = (current+90)%360;
    	var imgPicRotate = $(this).prev();
        imgPicRotate.css({'transform':'rotate('+current+'deg)'});
    },
    //封装layerSelfAert弹出框
    layerSelfAert:function(title,width,height,htmlContainer){
		 layer.open({
	         type: 1,
	         title: [title, 'font-size:16px;color:white;background-color:#0070db;'],
	         area: [width+'px', height+'px'],
	         closeBtn: 4,
	         content: htmlContainer
	     });
	 },
	 //封装点击页面其他地方隐藏搜索框
	 documentHide:function(obj1,obj2){
		 $("#"+obj1).on("click", function (e) {
 	        $(document).one("click", function () {
 	            $("#"+obj2).hide();
 	        });
 	        e.stopPropagation();
 	      });
	 },
	 transformation:function(num){
		   var strOutput = "";
			  var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';
			  num += "00";
			  var intPos = num.indexOf('.');
			  if (intPos >= 0)
			    num = num.substring(0, intPos) + num.substr(intPos + 1, 2);
			  strUnit = strUnit.substr(strUnit.length - num.length);
			  for (var i=0; i < num.length; i++)
			    strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);
			    return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");
	 },
    NoToChinese:function(num){
           	if (!/^\d*(\.\d*)?$/.test(num)) {alert("Number is wrong!"); return "Number is wrong!";}
           	var AA = ["零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"];
           	var BB = ["", "拾", "佰", "仟", "?", "?|", "点", ""];
           	var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = "";
           	for (var i = a[0].length - 1; i >= 0; i--) {
           	switch (k) {
           	case 0: re = BB[7] + re; break;
           	case 4: if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$").test(a[0]))
           	re = BB[4] + re; break;
           	case 8: re = BB[5] + re; BB[7] = BB[5]; k = 0; break;
           	}
           	if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) re = AA[0] + re;
           	if (a[0].charAt(i) != 0) re = AA[a[0].charAt(i)] + BB[k % 4] + re; k++;
           	}

           	if (a.length > 1) //加上小数部分(如果有小数部分)
           	{
           	re += BB[6];
           	for (var i = 0; i < a[1].length; i++) re += AA[a[1].charAt(i)];
           	}
           	return re;

    }
};

String.prototype.replaceAll = function(s1,s2){
    return this.replace(new RegExp(s1,"gm"),s2);
}

Array.prototype.Contains = function(element) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == element) {
            return true;
        }
    }
    return false;
};
/*
 * start首页私有模块
 */
window.home = {
    //消息显示红点
    showTipCount:function(resp){
        var noReadTask = resp.data.noReads.noReadTask;
        var noReadMessage = resp.data.noReads.noReadCount;
        var noAnswerPhoneCount = resp.data.noReads.noAnswerPhoneCount;

        if(noReadTask > 0){
            $("#todoHintId").show();
        }else {
            $("#todoHintId").hide();
        }
        if(noReadMessage > 0){
            $("#messageHintId").show();
        }else {
            $("#messageHintId").hide();
        }

        if(noAnswerPhoneCount > 0){
            $("#callHintId").show();
        }else {
            $("#callHintId").hide();
        }
    },
    //头像菜单
    handMenu:function (){
        $(".childrenMenuList ul li").hover(function(){
            $(this).addClass("personMenuActive");
            var ullistNum =  $(".publicChildrenMenu span").size();
            var index = 0;
            //动态设置高度菜单高度
            if(ullistNum == 1){
                $(this).find("div").stop().show().animate({ height:"70px" }, 300);
                index = 0;
            }else if(ullistNum == 2){
                $(this).find("div").stop().show().animate({ height:"120px" }, 300);
                index = 1;
            }else if(ullistNum == 3){
                $(this).find("div").stop().show().animate({ height:"170px" }, 300);
                index = 2;
            }else{
                $(this).find("div").stop().show().animate({ height:"222px" }, 300);
                index = 3;
            }
          //  $(".publicChildrenMenu span").eq(index).css("border-top","1px solid #e6e6ef");
        },function(){
            $(this).removeClass("personMenuActive");
            $(this).find("div").stop().animate({ height:"0px" },300);
        });
    },
    //实时监控跳转
    //公用左侧菜单效果
    //公用左侧菜单效果
    iframeShow:function(obj, params){
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
}
/*****end首页私有模块*******/
