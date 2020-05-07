(function(){
	if(typeof(P) == "undefined"){
		var P = {
			pW : '0',
			pH : '0',
			minWidth : '800',
			currentPage : null,
            historyPages: [],
			listPageUrl : null,
			currentTitle : null,
			current:0,
            scorePwd:22,//全局配置密码分数 可根据需求可配置修改
			monitorTimerId : null,
			returnProjectId : null,
            globalForm:null,
            returnForm:{},
			initPS : function(){
				this.pW = document.body.scrollWidth;
				var pH = document.documentElement.clientHeight;
				this.pH = 0 == pH ? document.body.scrollHeight : pH;},
			licenseP   : ["京","津","沪","渝","冀","豫","鲁","晋","陕","皖","苏","浙","鄂","湘","赣","闽",
			              "粤","桂","琼","川","贵","云","辽","吉","黑","蒙","甘","宁","青","新","藏"],
			licenseS   : ["A","B","C","D","E","F","G","H","J","K","L","M",
			              "N","P","Q","R","S","T","U","V","W","X","Y","Z"],
			cargoNum   : ["吨", "方", "件", "车", "个", "台", "箱"],
			carAxles   : ["不限","2轴","3轴", "4轴" ,"5轴", "6轴"],
			carLengths : ["不限","4米","4.2米","4.5米","6.2米","6.8米","7.2米","7.6米","8.2米","8.6米","9.6米","10米","10.5米",
				             "11米","11.7米","12.5米","13米","13.5米","14米","15米","16.5米","17米","17.5米","18米","20米","22米","24米"],
			carTypes   : ["不限", "半封闭", "半挂", "保温", "单车", "低板", "二拖三", "二拖四", "高栏", "高栏单桥", "高栏双桥",
			             "工程车", "后八轮或前", "后八轮或半", "集装箱", "冷藏", "零担", "笼子", "平板", "平板拖", "普通",
			             "起重", "前四后八", "全封闭", "斯太尔", "特种", "危险", "小车", "邮政", "油罐", "自卸", "自由厢板"],
			cargoTypes : ["普货","重货","泡货","整车","零担","设备","配件","电瓷","显像管","电器","烟叶","服装","棉纱","棉被",
			             "平板纸","医药","煤炭","矿产","钢铁","铁粉","建材","胶版","食品","粮食","饮料","危险品","烟花","化工",
			             "化肥农药","石油制品","轻工产品","牧产品","牲畜","渔产品","农产品","水果","蔬菜","木材","木方","竹片",
			             "桥车","驾驶室","特种货物","军用品","超宽设备","散装设备","罐装货物","其他"],
            cargoNumNo   : { 1:'吨', 2:"件", 3:"方", 4:"辆",5:"车", 6:"个", 7:"台", 8:"箱", 9:"车次"},
			loadLicenseP : function(id, defaultVal){
   				this.loadSelect(id, this.licenseP, defaultVal);
   			},
   			loadLicenseS : function(id, defaultVal){
    			this.loadSelect(id, this.licenseS, defaultVal);
    		},
    		loadCargoNum : function(id, defalutVal){
 				this.loadSelect(id, this.cargoNum, defalutVal);
 			},
    		loadAxleNum : function(id, defaultVal){
				this.loadSelect(id, this.carAxles, defaultVal);
			},
			loadCarType : function(id, defaultVal){
				this.loadSelect(id, this.carTypes, defaultVal);
			},
			loadCarLength : function(id, defaultVal){
				this.loadSelect(id, this.carLengths, defaultVal);
			},
			loadCargoType : function(id, defaultVal){
				this.loadSelect(id, this.cargoTypes, defaultVal);
			},
			loadSelect : function(id, array, defaultVal){
				var html = "";
				for(var i=0;i<array.length;i++){
					var itemVal = array[i];
					html += "<option value='" + itemVal + "' ";
					html += itemVal == defaultVal ? "selected='selected'>" : ">";
					html += itemVal + "</option>"
				}
				$(id).append(html);
			},
			loadSelectVal : function(id, array, defaultVal){
				var html = "";
				html += "<option value='"+defaultVal.key+"'>";
				html += defaultVal.val+"</option>";
				for(var i=0;i<array.length;i++){
					var item = array[i];
					html += "<option value='" + item.key + "' ";
					html += item.key == defaultVal.key ? "selected='selected'>" : ">";
					html += item.val + "</option>"
				}
				$(id).append(html);
			},
			isTop : function(){
				if (top.location != location){ top.location.href = location.href; }
			},
            isMobile : function(){
                return /(iPhone|iPad|iPod|iOS|android|Android)/i.test(navigator.userAgent);
            },
			isIELast8 : function(){
                var browser=navigator.appName;
                var b_version=navigator.appVersion;
                var ieStr = "Microsoft Internet Explorer";
                if(browser.indexOf(ieStr) != -1){
                    if(b_version.indexOf("MSIE 7.0") != -1 || b_version.indexOf("MSIE 8.0") != -1){
                    	return true;
					}
				}
				return false;
			},
			turnPageNo : function(){
				var pagePlugNo = $("#pagePlugNo").val();
				if(pagePlugNo && !isNaN(pagePlugNo)){
					var objPanel = document.forms[0];
					var formData = P.formData(objPanel);
					var requestUrl = P.listPageUrl || objPanel.action;
					if (requestUrl.indexOf("?") == -1) {
						requestUrl += "?";
					}
					var rightPanel = document.getElementById("indexRight");
					requestUrl += "&page.currentPage=" + pagePlugNo;
					if(!rightPanel){
						for(var p in formData){ requestUrl+="&"+p+"="+formData[p]; }
						window.location.href = requestUrl;
					}else{
						ajaxPost(requestUrl, formData, function(response) {
							$("#indexRight").html(response);
						});
					}
				}
			},

            insertAlertEle: function(){
                var obj = $("#loading");
                if(obj && obj.length){
                    return false;
                }

                var loadingImg = "newassets/imgs/loading.gif";
                var baseHref = window.location.href;
                baseHref = baseHref.substr(baseHref.indexOf("://")+3);
                var level = baseHref.split("/").length;

                if(level > 3 && baseHref.indexOf("#") == -1){
                    loadingImg = "../"+loadingImg;
                }

                var html = '<div id="loading">'+
                    '<div id="loadImg">'+
                    '<img src="'+loadingImg+'"/><br/>'+
                    '<span id="loadingMsg"></span>'+
                    '</div>'+
                    '<div id="alert" class="radius">'+
                    '<div id="alertTitle">提示</div>'+
                    '<div id="alertContent"></div>'+
                    '<div id="alertOk" class="bottomRadius">确认</div>'+
                    '</div>'+
                    '<div id="comfirm" class="radius">'+
                    '<div id="comfirmTitle">确认</div>'+
                    '<div id="comfirmContent"></div>'+
                    '<div style="height: 50px;">'+
                    '<div id="comfirmYes" class="blRadius">确认</div>'+
                    '<div id="comfirmCancel" class="brRadius">取消</div>'+
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '<div id="loadingBg"> </div>';

                $("body").append(html);

            },
            chkPass:function (pwd) {
                var nScore=0, nLength=0, nAlphaUC=0, nAlphaLC=0, nNumber=0, nSymbol=0, nMidChar=0, nRequirements=0, nAlphasOnly=0, nNumbersOnly=0, nUnqChar=0, nRepChar=0, nRepInc=0, nConsecAlphaUC=0, nConsecAlphaLC=0, nConsecNumber=0, nConsecSymbol=0, nConsecCharType=0, nSeqAlpha=0, nSeqNumber=0, nSeqSymbol=0, nSeqChar=0, nReqChar=0, nMultConsecCharType=0;
                var nMultMidChar=2,  nMultConsecAlphaUC=2, nMultConsecAlphaLC=2, nMultConsecNumber=2;
                var  nMultSeqAlpha=3, nMultSeqNumber=3, nMultSeqSymbol=3;
                var nMultLength=4, nMultNumber=4;
                var nMultSymbol=6;
                var nTmpAlphaUC="", nTmpAlphaLC="", nTmpNumber="", nTmpSymbol="";
                var sAlphas = "abcdefghijklmnopqrstuvwxyz";
                var sNumerics = "01234567890";
                var sSymbols = "~!@#$%^&*()";
                var sComplexity = "Too Short";//复杂度
                var nMinPwdLen = 8;
                if (document.all) { var nd = 0; } else { var nd = 1; }
                if (pwd) {
                    nScore = parseInt(pwd.length * nMultLength);
                    nLength = pwd.length;
                    var arrPwd = pwd.replace(/\s+/g,"").split(/\s*/);
                    var arrPwdLen = arrPwd.length;

                    /* 循环通过密码检查符号、数字、小写和大写模式是否匹配*/
                    for (var a=0; a < arrPwdLen; a++) {
                        if (arrPwd[a].match(/[A-Z]/g)) {
                            if (nTmpAlphaUC !== "") { if ((nTmpAlphaUC + 1) == a) { nConsecAlphaUC++; nConsecCharType++; } }
                            nTmpAlphaUC = a;
                            nAlphaUC++;
                        }
                        else if (arrPwd[a].match(/[a-z]/g)) {
                            if (nTmpAlphaLC !== "") { if ((nTmpAlphaLC + 1) == a) { nConsecAlphaLC++; nConsecCharType++; } }
                            nTmpAlphaLC = a;
                            nAlphaLC++;
                        }
                        else if (arrPwd[a].match(/[0-9]/g)) {
                            if (a > 0 && a < (arrPwdLen - 1)) { nMidChar++; }
                            if (nTmpNumber !== "") { if ((nTmpNumber + 1) == a) { nConsecNumber++; nConsecCharType++; } }
                            nTmpNumber = a;
                            nNumber++;
                        }
                        else if (arrPwd[a].match(/[^a-zA-Z0-9_]/g)) {
                            if (a > 0 && a < (arrPwdLen - 1)) { nMidChar++; }
                            if (nTmpSymbol !== "") { if ((nTmpSymbol + 1) == a) { nConsecSymbol++; nConsecCharType++; } }
                            nTmpSymbol = a;
                            nSymbol++;
                        }
                        /* 内部循环通过密码来检查重复字符*/
                        var bCharExists = false;
                        for (var b=0; b < arrPwdLen; b++) {
                            if (arrPwd[a] == arrPwd[b] && a != b) { /* repeat character exists */
                                bCharExists = true;
                                nRepInc += Math.abs(arrPwdLen/(b-a));
                            }
                        }
                        if (bCharExists) {
                            nRepChar++;
                            nUnqChar = arrPwdLen-nRepChar;
                            nRepInc = (nUnqChar) ? Math.ceil(nRepInc/nUnqChar) : Math.ceil(nRepInc);
                        }
                    }

                    /* 检查连续的alpha字符串模式(正向和反向) */
                    for (var s=0; s < 23; s++) {
                        var sFwd = sAlphas.substring(s,parseInt(s+3));
                        var sRev = sFwd.strReverse();
                        if (pwd.toLowerCase().indexOf(sFwd) != -1 || pwd.toLowerCase().indexOf(sRev) != -1) { nSeqAlpha++; nSeqChar++;}
                    }

                    /* 检查顺序数字字符串模式(正向和反向) */
                    for (var s=0; s < 8; s++) {
                        var sFwd = sNumerics.substring(s,parseInt(s+3));
                        var sRev = sFwd.strReverse();
                        if (pwd.toLowerCase().indexOf(sFwd) != -1 || pwd.toLowerCase().indexOf(sRev) != -1) { nSeqNumber++; nSeqChar++;}
                    }

                    /* 检查顺序符号字符串模式(正向和反向) */
                    for (var s=0; s < 8; s++) {
                        var sFwd = sSymbols.substring(s,parseInt(s+3));
                        var sRev = sFwd.strReverse();
                        if (pwd.toLowerCase().indexOf(sFwd) != -1 || pwd.toLowerCase().indexOf(sRev) != -1) { nSeqSymbol++; nSeqChar++;}
                    }

                    /* 根据使用情况和需求修改总分值 */
                    /* 普通点赋值*/
                    if (nAlphaUC > 0 && nAlphaUC < nLength) {
                        nScore = parseInt(nScore + ((nLength - nAlphaUC) * 2));

                    }
                    if (nAlphaLC > 0 && nAlphaLC < nLength) {
                        nScore = parseInt(nScore + ((nLength - nAlphaLC) * 2));

                    }
                    if (nNumber > 0 && nNumber < nLength) {
                        nScore = parseInt(nScore + (nNumber * nMultNumber));

                    }
                    if (nSymbol > 0) {
                        nScore = parseInt(nScore + (nSymbol * nMultSymbol));

                    }
                    if (nMidChar > 0) {
                        nScore = parseInt(nScore + (nMidChar * nMultMidChar));

                    }
                    /* 对不良行为的扣分*/
                    if ((nAlphaLC > 0 || nAlphaUC > 0) && nSymbol === 0 && nNumber === 0) {  // 字母
                        nScore = parseInt(nScore - nLength);

                    }
                    if (nAlphaLC === 0 && nAlphaUC === 0 && nSymbol === 0 && nNumber > 0) {  // 数字
                        nScore = parseInt(nScore - nLength);

                    }
                    if (nRepChar > 0) {  // 同一value不止一次存在
                        nScore = parseInt(nScore - nRepInc);

                    }
                    if (nConsecAlphaUC > 0) {  // 连续大写字母存在
                        nScore = parseInt(nScore - (nConsecAlphaUC * nMultConsecAlphaUC));

                    }
                    if (nConsecAlphaLC > 0) {  // 连续小写字母存在
                        nScore = parseInt(nScore - (nConsecAlphaLC * nMultConsecAlphaLC));

                    }
                    if (nConsecNumber > 0) {  // 连续数字存在
                        nScore = parseInt(nScore - (nConsecNumber * nMultConsecNumber));

                    }
                    if (nSeqAlpha > 0) {  // 连续的阿尔法字符串存在(3个或更多字符)
                        nScore = parseInt(nScore - (nSeqAlpha * nMultSeqAlpha));

                    }
                    if (nSeqNumber > 0) {
                        nScore = parseInt(nScore - (nSeqNumber * nMultSeqNumber));

                    }
                    if (nSeqSymbol > 0) {
                        nScore = parseInt(nScore - (nSeqSymbol * nMultSeqSymbol));
                    }
                    var arrChars = [nLength,nAlphaUC,nAlphaLC,nNumber,nSymbol];
                    var arrCharsIds = ["nLength","nAlphaUC","nAlphaLC","nNumber","nSymbol"];
                    var arrCharsLen = arrChars.length;
                    for (var c=0; c < arrCharsLen; c++) {
                        if (arrCharsIds[c] == "nLength") {
                            var minVal = parseInt(nMinPwdLen - 1);
                        } else {
                            var minVal = 0;
                        }
                        if (arrChars[c] == parseInt(minVal + 1)) {
                            nReqChar++;
                        }
                        else if (arrChars[c] > parseInt(minVal + 1)) {
                            nReqChar++;
                        }
                    }
                    nRequirements = nReqChar;
                    if (pwd.length >= nMinPwdLen) {
                        var nMinReqChars = 3;
                    } else {
                        var nMinReqChars = 4;
                    }
                    if (nRequirements > nMinReqChars) {
                        nScore = parseInt(nScore + (nRequirements * 2));
                    }
                    //复杂度 提示信息
                    if (nScore > 100) { nScore = 100; } else if (nScore < 0) { nScore = 0; }
                    if (nScore >= 0 && nScore < 20) { sComplexity = "Very Weak"; }
                    else if (nScore >= 20 && nScore < 40) { sComplexity = "Weak"; }
                    else if (nScore >= 40 && nScore < 60) { sComplexity = "Good"; }
                    else if (nScore >= 60 && nScore < 80) { sComplexity = "Strong"; }
                    else if (nScore >= 80 && nScore <= 100) { sComplexity = "Very Strong"; }
                    return nScore
                }
                else {
                    return nScore
                }
            },
            showLoading: function (msg) {
                this.insertAlertEle();
                if(msg) $("#loadingMsg").html(msg);
                else $("#loadingMsg").html('');
                $("#alert").hide();
                $("#comfirm").hide();
                $("#loading").show();
                $("#loadImg").show();
                $("#loadingBg").show();
            },

            hideDialog : function() {
                $("#alert").hide();
                $("#comfirm").hide();
                $("#loading").hide();
                $("#loadImg").hide();
                $("#loadingBg").hide();
            },

            showAlert : function () {
                $("#alert").show();
                $("#loading").show();
                $("#loadingBg").show();
                $("#comfirm").hide();
                $("#loadImg").hide();
            },

            showConfirm : function() {
                $("#comfirm").show();
                $("#loading").show();
                $("#loadingBg").show();
                $("#alert").hide();
                $("#loadImg").hide();
            },
            formData : function (obj) {
                var formData = {};
                for ( var i = 0; i < obj.length; i++) {
                    var element = obj[i];
                    var name = element.name;
                    if (null == name || "" == name) {
                        continue;
                    }
                    var value = element.value;
                    if (element.type == "radio") {
                        if (element.checked == true) {
                            formData[name] = value;
                        }
                    } else if (element.type == "checkbox") {
                        if (element.checked != true) {
                        	value = "";
                        }
                    }
                    if(value != ""){
                    	var oldValue = formData[name];
                    	oldValue = typeof(oldValue) == "undefined" ? "" : oldValue;
                    	if(oldValue){
                    		formData[name] = oldValue+","+value;
                    	}else{
                    		formData[name] = value;
                    	}
                    }
                }
                return formData;
            },
			/*timers VerificationTime获取验证码的参数*/
            timers : 0,
        	VerificationTime : 60,
			/*form提交组件 end*/
			/*验证码组件*/
			setTimes:function(obj){
                if(P.timers>0){
                    clearTimeout(P.timers);
                }
                if (P.VerificationTime == 0) {
                    obj.removeAttribute("disabled");
                    obj.style.cssText = "background:#0071db !important";
                    obj.value="获取验证码";
                    P.VerificationTime = 60;
                    return;
                } else {
                    obj.setAttribute("disabled",true);
                    obj.style.cssText = "background:#dbdcdc !important";
                    obj.value="" + P.VerificationTime + "秒后获取";
                    P.VerificationTime--;
                }
                P.timers = setTimeout(function() {P.setTimes(obj)},1000);
            },
            //添加字符串为空
            isNullStr :function (str){
                   return str == null?"":str;
            },
            isNullOrEmptyString : function(val){
                return typeof val == "undefined" || val == null || val == "";
            },
            long2Datetime : function(time){
                if(!time || time == "") return "";
                var newDate = new Date(time);
                return newDate.getFullYear()+"-"+P.prefixDate(newDate.getMonth()+1)+"-"+P.prefixDate(newDate.getDate())
                	+" "+P.prefixDate(newDate.getHours())+":"+P.prefixDate(newDate.getMinutes())+":"+P.prefixDate(newDate.getSeconds());
            },

            long2Date : function(time){
                if(!time || time == "") return "";

                var newDate = new Date(time);
                return newDate.getFullYear()+"-"+P.prefixDate(newDate.getMonth()+1)+"-"+P.prefixDate(newDate.getDate());
            },

            prefixDate : function(val){
                return val < 10 ? "0"+val : val;
            },

			getCityDetail : function(city){
            	var citys = city.split("#");
            	return citys.length > 1 ? citys[1] : citys[0];
			},getCityDetail1 : function(city){
                var citys = city.split("#");
                return citys.length > 1 ? citys[0] : citys[1];
            },
			convertMobileNo : function(mobileNo){
				return mobileNo && mobileNo.indexOf("M") == -1 ? mobileNo : "";
			},
            fixNull: function(val, defaultVal) {
				if(val == null || val == undefined) {
                    return defaultVal || '';
				}
				return val;
            },
			pushCurrentPage: function(url, data) {
				url = url || P.currentPage;
                P.historyPages.push({url: url, data: data});
            },
            popBackPage: function() {
				//移除当前页面
                P.historyPages.pop();
                //获取上层页面
                var backPage = P.historyPages.pop();
                P.historyPages.push(backPage);
                window.backToPage(backPage.url, backPage.data);
            },
			formatAmount : function(amount){
				return parseFloat(amount).toFixed(2);
			},
            formatPhoneNumber:function(phoneNumber){
			    var hiddenStr=phoneNumber.substring(3,7);
			    return phoneNumber.replace(hiddenStr,"****");
            },
            compareTime:function (start,end){
                if(start && start!="" && end && end!=""){
                    var startTime=new Date(start);
                    var endTime=new Date(end);
                    if(startTime.getTime()>endTime.getTime()){
                        return false;
                    }
                }
                return true;
             },
			//查看大图控件
            bigAlertPicture:function(pathImg){
                var html ='<div id="loadingBg2" style="display: none;">'+
                    ' <div class="loadingBgBox" >'+ '<div>'+
                    '<a class="rotatePic1 newRotatePic" id="rotatePic1">'+
                    '<img src="newassets/imgs/rotatePic.png">'+
                    '</a>'+
                    '</div>'+
                    '<img  class="target"  id="picTrues" />'+
                    '</div>'+
                    '</div>';
                $("body").append(html);
                P.current=0;
                var isFirefox;
                var target = $(".target");
                target.css({'transform':'rotate('+P.current+'deg)'}).css("zoom","0%");
                $("#picTrues").attr("src",pathImg).show();
                $("#loadingBg2").show();
                //阻止click冒泡事件
                $("#picTrues").click(function(event){
                    event.stopPropagation();
                });
                var w = 600;//设置最大宽度
                $(".target").each(function() {//如果有很多图片,使用each()遍历
                    //图片宽度
                    $(this).load(function(){
                        var img_w = $(this).width();
                        if (img_w > w) {//如果图片宽度超出指定最大宽度
                            $(this).css( {"width" : w,"height" : w+100});
                        }
                    });
                });
                if(isFirefox = navigator.userAgent.indexOf("Firefox")>0){
                    function handle(delta) {
                        var s = delta + ": ";
                        if (delta < 0) {
                            $("#picTrues").css("height", "300px").css("width", "300px");
                        }
                        else {
                            $("#picTrues").css("height", "600px").css("width", "600px");
                        }
                    }
                    function wheel(event) {
                        var delta = 0;
                        if (!event) event = window.event;
                        if (event.wheelDelta) {
                            delta = event.wheelDelta / 120;
                            if (window.opera) delta = -delta;
                        } else if (event.detail) {
                            delta = -event.detail / 3;
                        }
                        if (delta)
                            handle(delta);
                    }
                    window.addEventListener('DOMMouseScroll', wheel, false);
                }
                $("#loadingBg2").unbind().on("click","#rotatePic1", function(event) {
                    event.stopPropagation();
                    P.current = (P.current+90)%360;
                    $(this).css({'transform':'rotate('+0+'deg)'});
                    $(this).parent().parent().find("#picTrues").css({'transform':'rotate('+P.current+'deg)'});
                });
                $(document).on("mousewheel","#picTrues", function () {
                    $("#picTrues").css("overflow", "auto");
                    var _this = this;
                    var zoom = parseInt(this.style.zoom, 10) || 100;
                    zoom += event.wheelDelta / 12;
                    if(zoom >= 150){
                        return false;
                    }else if(zoom <= 30){
                        return false;
                    }else if(zoom > 0){
                        _this.style.zoom = zoom + '%';
                    }
                });
                $("#loadingBg2").on("click",function(e){
                    $(this).hide();
                    $("#loadingBg2").remove();
                });
            },
            //添加千分号 num 数值(Number或者String)  cent 要保留的小数位  isThousand 是否需要千分位 0:不需要,1:需要
            numbers: function (num,cent,isThousand) {
                if(num==0 || num==null || num==undefined || num=="undefined"){
                    return 0;
                }
                if(cent==undefined || cent=="undefined" ){
                    cent=2;
                }
                if(isThousand==undefined || isThousand=="undefined"){
                    isThousand=1;
                }
                num = num.toString().replace(/\$|\,/g,'');
                // 检查传入数值为数值类型
                if(isNaN(num))
                    num = "0";
                // 获取符号(正/负数)
                sign = (num == (num = Math.abs(num)));

                num = Math.floor(num*Math.pow(10,cent)+0.50000000001); // 把指定的小数位先转换成整数.多余的小数位四舍五入
                cents = num%Math.pow(10,cent);       // 求出小数位数值
                num = Math.floor(num/Math.pow(10,cent)).toString();  // 求出整数位数值
                cents = cents.toString();        // 把小数位转换成字符串,以便求小数位长度

                // 补足小数位到指定的位数
                while(cents.length<cent)
                    cents = "0" + cents;

                if(isThousand) {
                    // 对整数部分进行千分位格式化.
                    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
                        num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3));
                }

                if (cent > 0)
                    return (((sign)?'':'-') + num + '.' + cents);
                else
                    return (((sign)?'':'-') + num);
            },
            //记住上一页查询条件和分页，tier：层级
            setGlobalForm : function (tier){
                $("#pageNo").val($("#pageBar span.on").html());
                if (tier == 1) {
                    P.globalForm = P.formData(document.searchForm);
                } else if (tier == 2) {
                    if (!P.globalForm) {
                        P.globalForm = {};
                    }
                    P.globalForm.globalForm = P.formData(document.searchForm);
                }

            },
            //记住上页页面路径
            setReturnUrlForm : function (url,returnUrl){
                P.returnForm[url]=returnUrl;
            },
            //获取上页页面路径
            getReturnUrlForm : function (url){
               return  P.returnForm[url];
            },
            //截取时间换行
            stringTime:function(str,type){
                if(type == 1){
                    return str.substr(0,10)
                }else{
                    return str.substr(11,20)
                }
            },
            //根据不同的屏幕宽度判断显示字数 优化代码
            stringText:function(str) {
                    var width = document.body.clientWidth;
                    var num=0;
                    if(width >= 1901){
                        num=20;
                    }else if(width >= 1601 && width <= 1900){
                        num=10;
                    }else if(width >= 1441 && width <= 1600){
                        num=20;
                    }else if(width >= 1280 && width <= 1440){
                        num=15;
                    }else if(width<=1280){
                        num=7;
                    }
                    return   str.length > num ? str.substr(0, num)+"..." : str;
           },
            //添加出发地-目的地选择区域外隐藏
            mouseClickHide : function (obj,addressBox){
                $("#"+obj+"").on("click",function(e){
                    $(document).one("click", function () {
                        $("#"+addressBox+"").hide();
                    });
                    e.stopPropagation();
                    if(obj == "c_departureCity"){
                        $("#SA_2").hide();
                    }else if(obj == "c_arrivalCity"){
                        $("#SA_1").hide();
                    }else if(obj == "departureCity"){
                        $("#SA_4").hide();
                    }else if(obj == "arrivalCity"){
                        $("#SA_3").hide();
                    }
                });
                $("#"+addressBox+"").on("click",function(e){
                    e.stopPropagation();
                });
           }
		};

		window.P = P;
	}
})();


function selfAlert(html, fun) {
	//页面提示去掉错误代码 add by sj 2017.03.10
	html=html&&html.indexOf("错误代码[")>-1&&html.indexOf("]:")>-1 ? html.substring(html.indexOf("]:")+2) : html;

    P.insertAlertEle();

	$("#alertContent").html(html);
	if (fun) {
		$("#alertOk").unbind().bind("click", function() {
			fun();
            P.hideDialog();
		});
	} else {
		$("#alertOk").unbind().bind("click", function() {
            P.hideDialog();
		});
	}
    P.showAlert();
}

function selfConfirm(html, okFun, cancelFun) {
    P.insertAlertEle();

	$("#comfirmContent").html(html);
	if (okFun) {
		$("#comfirmYes").unbind().bind("click", function() {
            P.hideDialog();
			if (typeof (cancelFun) == "object") {
				okFun(cancelFun);
			} else {
				okFun();
			}
		});
	} else {
		$("#comfirmYes").unbind().bind("click", function() {
            P.hideDialog();
		});
	}
	if (cancelFun && typeof (cancelFun) == "function") {
		$("#comfirmCancel").unbind().bind("click", function() {
			cancelFun();
            P.hideDialog();
		});
	} else {
		$("#comfirmCancel").unbind().bind("click", function() {
            P.hideDialog();
		});
	}
    P.showConfirm();
}

function selfConfirmTitle(html,titleDesc,okBtnDesc, okFun, cancelFun) {
    P.insertAlertEle();
    if (okFun) {
        $("#comfirmTitle").text(titleDesc);
        $("#comfirmYes").text(okBtnDesc);
        $("#comfirmYes").unbind().bind("click", function() {
            P.hideDialog();
            if (typeof (cancelFun) == "object") {
                okFun(cancelFun);
            } else {
                okFun();
            }
        });
    } else {
        $("#comfirmYes").unbind().bind("click", function() {
            P.hideDialog();
        });
    }
    if (cancelFun && typeof (cancelFun) == "function") {
        $("#comfirmCancel").unbind().bind("click", function() {
            cancelFun();
            P.hideDialog();
        });
    } else {
        $("#comfirmCancel").unbind().bind("click", function() {
            P.hideDialog();
        });
    }
    $("#comfirmContent").html(html);
    P.showConfirm();
}
function selfConfirm2(html, okFun) {
    P.insertAlertEle();

	$("#comfirmContent").html(html);
	if (okFun) {
		$("#comfirmYes").unbind().bind("click", function() {
			okFun();
		});
	} else {
		$("#comfirmYes").unbind().bind("click", function() { P.hideDialog(); });
	}

	$("#comfirmCancel").unbind().bind("click", function() { P.hideDialog(); });
    P.showConfirm();
}

function clearSession(){
    selfConfirm("您确定要退出吗?", function(){
		window.location.href="logout.html";
	});
	return false;
}

function ajaxSearch(obj) {
	var formData = P.formData(obj);
	ajaxPost(obj.action, formData, function(response) {
		if(ajaxTimeout(response)) return;
		$("#mian-container").html(response);
	});

	return false;
}

//静默获取，不loading，不报错
function silenceGet(url, successCallBack) {
	$.ajax({
		url : url, type : "GET", cache : false,
		success : function(response) {
			if(ajaxTimeout(response)) return;
			successCallBack(response);
		},
		error : function(error) { }
	});
}


//静默获取，不loading，不报错
function silencePost(url, data, successCallBack, errorCallBack) {
    $.ajax({
	   url : url, data : data, type : "POST", cache : false,
	   success : function(response) {
		   if(ajaxTimeout(response)) return;
		   successCallBack(response);
	   },
	   error : function(error) {
		   if (errorCallBack) {
			   errorCallBack(error);
		   }
	   }
   });
}

function ajaxGet(url, successCallBack, errorCallBack) {
    P.showLoading();
	$.ajax({
		url : url, type : "GET", cache : false,
		success : function(response) {
			if(ajaxTimeout(response)) return;
            P.hideDialog(); successCallBack(response);
			$('input, textarea').placeholder();
		},
		error : function(error) {
			if (!errorCallBack) {
				var errorMsg = "网络连接错误!";
				if(error.status == 404){ errorMsg = "页面未找到!"; }
				//selfAlert(errorMsg);
			} else { P.hideDialog(); errorCallBack(error); }
		}
	});
}

function ajaxGetAndCheck(url, successCallBack, errorCallBack) {
    P.showLoading();
    $.ajax({
        url : url, type : "GET", cache : false,
        success : function(response) {
            if(ajaxTimeout(response)) return;
            P.hideDialog();
            if(response.success){
                successCallBack(response);
            }else{
                selfAlert(response.message);
			}
        },
        error : function(error) {
            if (!errorCallBack) {
                var errorMsg = "网络连接错误!";
                if(error.status == 404){ errorMsg = "页面未找到!"; }
                //selfAlert(errorMsg);
            } else { P.hideDialog(); errorCallBack(error); }
        }
    });
}

function ajaxPost(url, data, successCallBack, errorCallBack) {
    P.showLoading();
	$.ajax({
		url : url, data : data, type : "POST", cache : false,
		success : function(response) {
			if(ajaxTimeout(response)) return;
            P.hideDialog();
			successCallBack(response);
			if(Page){
			    Page.ajaxInit(data,response);
            }
		},
		error : function(error) {
			if (!errorCallBack) {
				var errorMsg = "网络连接错误!";
				if(error.status == 404){ errorMsg = "页面未找到!"; }
				//selfAlert(errorMsg);
			} else { P.hideDialog(); errorCallBack(error); }

            if(Page){
                Page.ajaxInit(data,response);
            }
		}
	});
}

function ajaxSubmit(obj, callback) {
	var formData = P.formData(obj);
	ajaxPost(obj.action, formData, function(response) {
		if(ajaxTimeout(response)) return;
		if (response.success) {
			if(!callback){
				selfAlert("操作成功!", function() { containerShow(P.currentPage); });
			}else{
				callback(response);
			}
		} else {
			if(!callback){
				selfAlert(response && response.message ? response.message : "服务器错误!");
			}else{
				callback(response);
			}
		}
	});
	return false;
}

function ajaxSubmitJson(obj){
	showLoading();
	var formData = P.formData(obj);
	var jsonData = JSON.stringify(formData);
	ajaxPost(obj.action, {jsonData: jsonData}, function(response) {
		if(ajaxTimeout(response)) return;
		if (response.success) {
			selfAlert("操作成功!", function() { containerShow(P.currentPage); });
		} else {
			selfAlert(response && response.message ? response.message : "服务器错误!");
		}
	});
	return false;
}

function ajaxTimeout(response){
	if (response && typeof(response) == "string" && response.toLowerCase() == "timeout"){
        var br = '<br/>'
        window.parent.frames.selfAlert("登录超时，请重新登录!"+br+"3秒自动关闭！", function(){window.top.location.href="login.html"; });
        setTimeout(function(){
            window.top.location.href="login.html";
        },3000);
        return true;
	}
	return false;
}

/**
 * 表格各行换色
 */
function tableBgChanger(){
    // $(".publicTable tr:even").addClass("even");
}


/**
 * 获取忘记支付密码的短信验证码
 */
function ajaxGetPayPasswdValidateSmsCode(){
	ajaxGet("ajax/getForgotPayPasswdSmsCode.json", function(resp){
		if(resp.success){
			selfAlert("发送成功!");
		}else{
			selfAlert(resp.message);
		}
	});
}

/**
 * 获取忘记登录密码的短信验证码
 */
function ajaxGetLoginPasswdValidateSmsCode(mobileNo){
	ajaxGet("ajax/getForgotLoginPasswdSmsCode.json?mobileNo="+mobileNo, function(resp){
		if(resp.success){
			selfAlert("发送成功!");
		}else{
			selfAlert(resp.message);
		}
	});
}

/**
 * 跳转到呼叫页面
 * @param obj
 * @param id
 * @param type
 */
function showCallDialog(obj,id,type){
    // webSocketLogin(wsPath,selfUserId);
    var url = "proxy.htm?driverId="+id+"&type="+type;
    window.open(url,"tag",'width=360,height=500,top=200,left=500,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
}

/**
 * @param Date
 * 将Date格式转换成如：2018-09-26 15:58:25
 *  by lcy
 */
function dateToStr(datetime){

    var year = datetime.getFullYear();
    var month = datetime.getMonth()+1;//js从0开始取
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minutes = datetime.getMinutes();
    var second = datetime.getSeconds();

    if(month<10){
        month = "0" + month;
    }
    if(date<10){
        date = "0" + date;
    }
    if(hour <10){
        hour = "0" + hour;
    }
    if(minutes <10){
        minutes = "0" + minutes;
    }
    if(second <10){
        second = "0" + second ;
    }

    var time = year+"-"+month+"-"+date+" "+hour+":"+minutes+":"+second;
// alert(time);
    return time;
}

/**
 * 登录到websocket
 * @param webSocketPath
 * @param userId
 */
var wsPath;
var selfUserId;
var count=0;
function webSocketLogin(webSocketPath,userId){
    count = count + 1;
	if(webSocketPath!='undefined'){
        wsPath=webSocketPath;
	}
	if(userId!='undefined'){
        selfUserId=userId;
	}
    var ws = new WebSocket("wss://" + wsPath + "/agoraWebsocket/1/" + selfUserId+"/0");
    ws.onopen = function () {
        console.log("打开连接! 登录");
        console.log("wss://" + wsPath + "/agoraWebsocket/1/" + selfUserId+"/0");
        ws.send("hello");
        heartCheck.start();
    };
    ws.onmessage = function (evt) {
        var result = $.parseJSON(evt.data);
        console.log("用户发起了呼叫");
        if(result.type==1){
//                join(result.channel,result.channelKey,result.appId);
        }else if(result.type==2){
            joinAgoraChannel(result.userId,result.channel,result.channelKey);
        }
        heartCheck.reset();

    };
    ws.onclose = function (evt) {
        console.log("连接关闭!");
        if(count< 3){
            webSocketLogin(wsPath,selfUserId);
		}

    };
    ws.onerror = function (evt) {
        console.log("连接失败!");
        if(count< 3){
            webSocketLogin(wsPath,selfUserId);
        }
    };

    /**
	 * 心跳重连
     */
    var heartCheck = {
        timeout: 30000,//60ms
        timeoutObj: null,
        reset: function(){
            clearTimeout(this.timeoutObj);
            this.start();
        },
        start: function(){
            this.timeoutObj = setInterval(function(){
                ws.send("HeartBeat");
            }, this.timeout)
        }
    }

}

String.prototype.strReverse = function() {
    var newstring = "";
    for (var s=0; s < this.length; s++) {
        newstring = this.charAt(s) + newstring;
    }
    return newstring;
};

/**
 * 被叫 弹出呼叫页面
 * @param driverId
 * @param channel
 * @param channelKey
 */
function joinAgoraChannel(driverId,channel,channelKey){
    var url = "proxy.htm?driverId="+driverId+"&type=1&channel="+channel+"&channelKey="+encodeURIComponent(channelKey);
    window.open(url,"tag",'width=360,height=500,top=200,left=500,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');

//	    var url = "agoraCall/toCall.html?driverId=" + driverId +"&type=1&channel="+channel+"&channelKey="+encodeURIComponent(channelKey);
//        window.open (url,'newwindow','width='+200+',height=500,top=200,left=500,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
}



(function(){
    if(typeof(Page) == "undefined"){
        //页面跳转和标题相关处理，点击页面切换按钮，跳转到下一个页面，封装ajax跳转页面，记录跳转情况
        //解决跳转后，保存搜索条件问题,保存页码问题
        var Page = {
            pageList:[],
            isFromReturnBtn:false,//是否从返回按钮跳转的，用于判断是否初始化formData，搜索条件
            //点击一级菜单切换页面
            clickMenuToPage :function(url,pageTitle) {
                Page.pageList=[];
                var page={};
                page.title=pageTitle.trim();
                page.url=url;
                Page.pageList.push(page);
                ajaxGet(url, function (response) {
                    P.currentPage = url;
                    P.hideDialog();
                    $("#publicRightContainer").html(response);
                });
            },
            //点击子菜单切换页面
            clickChildMenuToPage :function(url,pageTitle,parentTitle) {
                Page.pageList=[];
                var page={};
                page.title=parentTitle.trim();
                Page.pageList.push(page);

                var page={};
                page.title=pageTitle.trim();
                page.url=url;
                Page.pageList.push(page);
                ajaxGet(url, function (response) {
                    P.currentPage = url;
                    P.hideDialog();
                    $("#publicRightContainer").html(response);
                });
            },
            /**
             * 点击普通的按钮切换页面
             * @param url
             * @param pageTitle
             */
            clickBtnToPage :function(url,pageTitle) {
                var page={};
                page.title=pageTitle.trim();
                page.url=url;
                Page.pageList.push(page);
                ajaxGet(url, function (response) {
                    P.currentPage = url;
                    P.hideDialog();
                    $("#publicRightContainer").html(response);
                });
            },
            //点击左边主菜单时使用：只显示页面标题
            showPageTitle :function() {
                var curryTitle="";
                var html="<ul>";
                for (var i = 0; i < Page.pageList.length; i++) {
                    if(i != 0){
                        html+="<li><img src=\"newassets/imgs/icon_new_arrow_nav.png\"></li>";
                    }
                    html+=" <li><a>"+Page.pageList[i].title+"</a></li>";
                    if(i==Page.pageList.length-1){
                        curryTitle=Page.pageList[i].title;
                    }
                }
                html+="</ul>";
                $(".publicHeaderNav").html(html);
                return curryTitle;
            },
            //显示页面标题并绑定返回按钮的跳转地址
            showPageTitleBindReturnUrl :function() {
                var curryTitle=Page.showPageTitle();
                if($(".return")){
                    $(".return").unbind().on("click", function () {
                        //返回按钮需要绑定上一个页面的地址
                        Page.returnPage();
                    });
                }

                return curryTitle;
            },
            //返回上一个页面
            returnPage :function() {
                Page.isFromReturnBtn=true;//标记从返回按钮跳转的。
                //返回按钮需要绑定上一个页面的地址
                var page=Page.pageList[Page.pageList.length-2];
                if(page&&page.url){
                    window.backToPage(page.url);
                    //点击返回按钮时，需要去掉当前列表中的最后一个页面对象
                    Page.pageList.pop();
                }
            },
            //重新加载当前页面
            reloadCurrentPage :function() {
                //返回按钮需要绑定上一个页面的地址
                var page=Page.pageList[Page.pageList.length-1];
                if(page){
                    window.backToPage(page.url);
                }
            },
            //静默加载：重新加载当前页面
            reloadCurrentPageBySilence :function() {
                //返回按钮需要绑定上一个页面的地址
                var page=Page.pageList[Page.pageList.length-1];
                if(page){
                    window.silenceBackToPage(page.url);
                }
            },
            //返回当前页的page对象
            getCurrentPage :function() {
                //返回按钮需要绑定上一个页面的地址
                return Page.pageList[Page.pageList.length-1];
            },
            //初始化搜索条件和分页页码
            initFormData :function(objForm) {
                if(Page.isFromReturnBtn){
                    Page.isFromReturnBtn=false;
                    var page=Page.getCurrentPage();
                    if(page&& page.formDataInfo){
                        page.isFromReturnBtn=true;
                        //初始化formData
                        $.each(page.formDataInfo, function(key, value) {
                            if(key=='pageNo'){
                                //判断是否存在pageNo
                                if($("input[name='pageNo']") && $("input[name='pageNo']").val()){
                                    $("input[name='pageNo']").val(value);
                                }else{
                                    //如果不存在
                                    $(objForm).append('<input type="hidden" id="pageNo" name="pageNo" value="'+value+'">')
                                }
                            }else{
                                //input 输入框
                                if($("input[name='"+key+"']")){
                                    $("input[name='"+key+"']").val(value);
                                }
                                //select
                                if($("select[name='"+key+"']")){
                                    //select 选择框
                                    $("select[name='"+key+"']").val(value);
                                }

                            }
                        })
                    }
                }
            },
            //搜索条件后，保存搜索条件，还原页码（因为页数保存问题需要还原，不然会使用上一次的页码）
            ajaxPageInit :function(objForm,index) {
                var page=Page.getCurrentPage();
                if(!page){
                    return;
                }
                //记录当前搜索条件,包含记录当前页码（固定名称pageNo）
                page.formDataInfo = P.formData(objForm);
                page.formDataInfo.pageNo=index;
                //如果存在
                if($("input[name='pageNo']") && $("input[name='pageNo']").val()){
                    $("input[name='pageNo']").val(1);
                }else{
                    //如果不存在
                    $(objForm).append('<input type="hidden" id="pageNo" name="pageNo" value="1">')
                }
            },
            //请求后，就还原一次页码，
            ajaxInit :function(formDataInfo,resp) {
                var page=Page.getCurrentPage();
                if(!page){
                    return;
                }
                if(page.isFromReturnBtn){
                    //是返回后初始化数据的
                    //标记已经处理
                    page.isFromReturnBtn=false;
                    var isRight=true;
                    var index=formDataInfo.pageNo?formDataInfo.pageNo:1;
                    if(resp && resp.data && resp.data.records){
                        //判断当前页，是否有数据
                        //当前页>1，，而且没有数据，则需要查询第一页。
                        //不查询上一页，因为可能存在上一页也没有的情况
                        if (resp.data.records.length <=0 && index > 1 ) {
                            isRight=false;
                        }
                    }
                    if(isRight){
                        //记录当前搜索条件,包含记录当前页码（固定名称pageNo）
                        page.formDataInfo = formDataInfo;
                        page.formDataInfo.pageNo=index;
                        //如果存在
                        if($("input[name='pageNo']") && $("input[name='pageNo']").val()){
                            $("input[name='pageNo']").val(1);
                        }
                    }else{
                        page.formDataInfo = formDataInfo;
                        page.formDataInfo.pageNo=1;
                        Page.isFromReturnBtn=true;
                        Page.reloadCurrentPageBySilence();
                    }
                }

            }
        }
        window.Page = Page;
    }
})();
