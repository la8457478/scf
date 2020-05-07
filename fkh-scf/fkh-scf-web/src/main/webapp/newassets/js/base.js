/**
 * Created by Administrator on 2017/11/3.
 */
//动态设置宽度
function initSize() {
    $(".publicLeftMenu").show();
    var menuWidth = $(".publicLeftMenu").css("width");
    // var height = document.body.scrollHeight;
    var height = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);
    $(".publicLeftMenu").css("height", height - 56 + "px");
    $(".publicRightMain").css("height", height - 60 + "px");
    menuWidth = menuWidth.split("px")[0];
    var width = document.body.scrollWidth;
    var rightWidth = width - menuWidth - 2;
    $("#monitorIframe").css("width", rightWidth - 41 + "px");
    if (width <= 1600) {
        $(".publicRightContainer").css("width", rightWidth - 41 + "px");
    } else {
        $(".publicRightContainer").css("width", rightWidth - 41 + "px");
    }
    $("#publicRightMain").css("width", rightWidth + "px");

}



//页面初始化加载
function initReady() {
    initSize();
    initBox();
    window.onresize = function () {
        initSize();
        initBox();
        //窗口变化加载数据
        initStatisticCharts(1);
        initStatisticCharts(2);
        initStatisticCharts(3);
    };
}
/*动态计算布局算法底部算法*/
function initBox() {
    var height = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage-134 + "px");
    $("#monitorIframe").css("height", height - 90 + "px");

    var newHeight = height - 134;
    $("#new_public_height").css("height", +newHeight + "px");
    $("#new_public_height3").css("height", +newHeight-20 + "px");

    $("#box").css("height", +newHeight + "px");
    $("#allmap").css("height", newHeight - 72 + "px");
    $("#new_sectorStructure").css("height", +height - 120 + "px");
    $("#new_public_height1").css("height", +newHeight - 50 + "px");


}

//在封装ajax跳转页面
function containerShow(url) {
    ajaxGet(url, function (response) {
        P.currentPage = url;
        P.hideDialog();
        $("#publicRightContainer").html(response);
    });
}

//在封装ajax跳转页面
function containerShow(url, type, projectName) {
    ajaxGet(url, function (response) {
        P.currentPage = url;
        P.hideDialog();
        $("#publicRightContainer").html(response);
        // if (ProjectDialog.getProjectName() && projectName) {
        //     $("#" + projectName).text(ProjectDialog.getProjectName());
        // }
    });
}

/**
 * 菜单选中效果
 */
function activeMySelf(url) {
    var currentLi = $(".menuList .menuListActives");

    //当前没有选中该菜单的时候再去选中该菜单
    if(currentLi.length <= 0 || currentLi.attr("url") != url) {
        var currentLi = $(".menuList li[url='" + url + "']");

        $(".menuList .menuListActives").removeClass("menuListActives");
        currentLi.addClass("menuListActives");

        var parentLi = currentLi.parent().parent();
        if(parentLi.is("li")) {
            $(".menuList .menuListActive").removeClass("menuListActive");
            parentLi.children("a").addClass("menuListActive");
        }
    }
}

//切换项目
/**
 * 1.优先跳转当前页面
 * ----当前页面不存在-----
 * a.在子菜单中
 *  a.1当前页面不存在则跳转主菜单下面的第一个页面
 *  a.2若第一个页面也不存在则跳转首页
 * b.在主菜单中
 *  b.1跳转首页
 */
function switchProject() {

    //更新我的钱包页面，项目余额
    var personnalText = $("#personnalText").html();
    if(personnalText) {
        if(personnalText == '个人资料') {
            H.profile();
        } else if(personnalText == '我的钱包') {
            H.financeManager();
        } else if(personnalText == '交易流水') {
            var fromtype=$("#fromtype").val()
            PF.showProjectTradeLogDialog(fromtype);
        } else if(personnalText == '余额回查') {
            PF.showProjectBalanceCheckLogDialog();
        }

        ajaxGet("menus.html",function (response) {
            $(".menuList").html(response);
        });

        //刷新车辆召集-抢单司机右上角数字
        getListTmsWaybillCarMessage();
        return;
    }


    var simpleUrl;
    if(P.currentPage) {
        simpleUrl = P.currentPage.substr(0, P.currentPage.indexOf(".html")+5);
    }

    var currentLi;
    var mainMenu = false;

    if(simpleUrl == "tmsstatisticss/details.html" || simpleUrl == "home/statistics.html") {
        currentLi = $(".menuList li[url='home/statistics.html']");
        mainMenu = true;
    } else if(simpleUrl == "home/thirdService.html") {
        currentLi = $(".menuList li[url='home/thirdService.html']");
        mainMenu = true;
    } else if(simpleUrl == "home.html") {
        currentLi = $(".menuList li[url='home.html']");
        mainMenu = true;
    }
    else if(simpleUrl == "profile.html") {
        H.financeManager();
        return;
    }
    else if(simpleUrl == "monitor.html" || simpleUrl == "monitorAdmin.html") {
        currentLi = $(".menuList li[url='monitor.html']");
        mainMenu = true;
    } else {
        var currentLi = $(".menuList .menuListActives");
        if(currentLi.length <= 0) {
            var currentLi = $(".menuList .menuListActive").parent();
            mainMenu = true;
        }
    }
    var currentUrl;
    //主菜单的tag
    var tag;

    if(currentLi.length > 0) {
        currentUrl = currentLi.attr("url");
        if(mainMenu) {
            tag = currentLi.attr("tag");
        } else {
            tag = currentLi.parent().parent().attr("tag");
        }
    }

    ajaxGet("menus.html",function (response) {
        $(".menuList").html(response);
        //清除首页默认选中样式
        $(".menuList ul li a").removeClass("menuListActive");

        if(currentUrl) {
            if(!mainMenu) {
                var currentLi = $(".menuList li[url='" + currentUrl + "']");

                //尝试获取主菜单下的第一个子菜单
                if(currentLi.length <= 0 && tag) {
                    currentLi = $(".menuList li[tag='" + tag + "'] ul li :first");
                }

                if(currentLi.length > 0) {
                    var parentLi = currentLi.parent().parent();

                    //需要刷新页面
                    if(parentLi.attr("reloadMenu")) {
                        parentLi.children("a").trigger("click");
                        currentLi.trigger("click");

                    //不需要刷新页面
                    } else {
                        parentLi.children("a").trigger("click");
                        currentLi.addClass("menuListActives");
                    }

                //主菜单权限不在了
                } else {
                    //页面不存在则跳转首页
                    selfAlert("当前无权限", function() {
                        currentLi = $(".menuList li:first");
                        currentLi.children("a").trigger("click");
                    });
                }
            } else {
                $(".menuList li[url='" + currentUrl + "'] a").addClass("menuListActive");
            }
        } else {
            //和项目无关 不用处理
        }

        //刷新车辆召集-抢单司机右上角数字
        getListTmsWaybillCarMessage();
    });
}


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//首页图表所需要的数据
var indexPageData;
//所有数据
function initCharts() {
    if (indexPageData) {
        renderIndexPage(indexPageData);
    } else {
        var params = 'tmsstatisticss/allNewForIndex.json?unit=1&type=2&time=dates';
        silenceGet(params, function (data) {
            if (data.success && !data.success) {
                selfAlert(data.message);
                return;
            }
            indexPageData = data;
            renderIndexPage(indexPageData);
        });
    }
}

function renderIndexPage(data) {
    function renderHtml() {
        if (data.resp) {//有总数据
            $("#projectTon").html(data.resp.bTotalTransportation);
            $("#projectTonTimes").html(data.resp.bTotalTransportationTimes);
            $("#hairGoods").html(data.resp.bSendWeight);
            $("#hairGoodsTimes").html(data.resp.bSendWeightTimes);
            $("#collectGoods").html(data.resp.bRecevieWeight);
            $("#collectGoodsTimes").html(data.resp.bRecevieWeightTimes);
            $("#truckGoods").html(data.resp.bTotalLoadingVehicle);
            $("#transport").html(data.resp.bSendGoWeight);
            $("#transportTimes").html(data.resp.bSendGoWeightTimes);
            $("#difference").html((data.resp.bDiffAmountScale > 0 ? parseFloat(data.resp.bDiffAmountScale).toFixed(2) : "0.00"));//修正可能出现的负数
            var freight = data.resp.pOilCardAmount + data.resp.pEtcCardAmount + data.resp.pCashPayAmount;
            if(freight) {
                freight = parseFloat(freight).toFixed(2);
            }
            $("#freight").html(freight || 0);//已经支付的运费
            $("#card").html(data.resp.pOilCardAmount);
            $("#ETC").html(data.resp.pEtcCardAmount);
            $("#bill").html(data.resp.pCashPayAmount);//话费，暂时没有
        } else {
            $("#projectTon").html(0);
            $("#projectTonTimes").html(0);
            $("#hairGoods").html(0);
            $("#hairGoodsTimes").html(0);
            $("#collectGoods").html(0);
            $("#collectGoodsTimes").html(0);
            $("#truckGoods").html(0);
            $("#transport").html(0);
            $("#transportTimes").html(0);
            $("#difference").html("0.00");
            $("#freight").html(0);//已经支付的运费
            $("#card").html(0);
            $("#ETC").html(0);
            $("#bill").html(0);//话费，暂时没有
        }

        $("#totalHairGoodsTimes").html(data._totalHairGoodsTimes);
        $("#todayHairGoodsTimes").html(data._hairGoodsTimes);
    }

    //首页图表
    function renderChart(dataArr) {
        $("#chartContainer").highcharts({
            title: {
                text: null
            },
            subtitle: {
                text: null
            },
            lang: {
                noData: "暂时没有数据!"
            },
            plotOptions: {
                series: {
                    lineWidth: 3,
                    marker: {
                        radius: 4,  //曲线点半径，默认是4
                        symbol: 'circle' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                    }
                }
            },
            yAxis: {
                title: {
                    text: null
                },
                labels: {format: '{value:.,0f}'},
                plotLines: [{
                    value: 0,
                    color: '#808080'
                }]
            },
            xAxis: {
                categories: dataArr.charTime
            },
            tooltip: {
                valueSuffix: null
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                borderWidth: 0,
                x: 100,
                y: 0
            },
            credits: {
                enabled: false
            },

            colors: ['#7494e7', '#54a453'],
            series: [{
                name: '货源条数',
                data: dataArr.charData
            }]
        });
    }

    //初始化并缓存图表所需的数据
    if (!data._chartData) {
        data._totalHairGoodsTimes = 0;
        data._hairGoodsTimes = 0;

        var map23data = {};

        if (data.mapData) {//有地图数据
            var senderList = data.mapData[22];

            for (var i = 0; senderList && i < senderList.length; i++) {
                var key = new Date(senderList[i].date).Format("M.dd");
                map23data[key] = senderList[i].allCarCount || 0;
            }
        }

        var charTime = [];
        var charData = [];
        var currentTime = new Date().getTime();

        var totalHairGoodsTimes = 0;
        for (var i = 14; i >= 0; i--) {
            var dateStr = new Date(currentTime - i * 24 * 60 * 60 * 1000).Format("M.dd");

            charTime.push(dateStr);
            var hairGoodsTimes = map23data[dateStr] || 0;
            charData.push(hairGoodsTimes);
            totalHairGoodsTimes += hairGoodsTimes;

            if (i == 0) {
                data._totalHairGoodsTimes = totalHairGoodsTimes;
                data._hairGoodsTimes = hairGoodsTimes;
            }
        }

        data._chartData = {charTime: charTime, charData: charData};
    }

    renderHtml();
    renderChart(data._chartData);
}


//首页图表所需要的数据
var statisticPageData;
//所有数据
function initStatisticCharts(index) {
    if (statisticPageData) {
        renderStatisticPage(statisticPageData, index);
    } else {
        if (typeof validateTime == 'undefined') {
            return;
        }
        getAllNew(index);
    }
}
//图片上传
var _URL = window.URL || window.webkitURL;
(function($){
    $.newFileUpload = function(){};
    $.newFileUpload.prototype.init = function(opt, callback){
        this.s = $.extend({
            "inputObj": $(''),
            "previewContiner": $(''),
            "imgobj": {},
            "formData": new FormData()
        },opt||{});
        this.handle();
        this.callback = callback;
    }
    $.newFileUpload.prototype.handle = function(){
        var _this = this;
        _this.s.inputObj.unbind("change").bind("change",function(){
            var file, img, imgName, src, src2, reader;
            var files = this.files[0];
            if(null == files || undefined == files){
                return false;
            }
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
                src = _URL.createObjectURL(file);//把这个url放到上面展示
                img = document.createElement("img");
                img.src = src;
                img.width = 93;//设置图片宽
                img.height = 85;//设置图片高<br>
                _this.s.previewContiner.html(img);
                // $(this).next().hide();
                _this.reader = new FileReader();
                _this.reader.onload = function (e) {
                    _this.s.imgobj[imgName] = this.result;//存储图片到对象中
                }
                _this.reader.readAsDataURL(file);

                if(_this.callback){
                    _this.callback(src);
                }
            }
        })
    }
})($);

function renderStatisticPage(data, index) {
    function renderHtml() {
        if (data.resp) {//有总数据
            $("#projectTon").html(data.resp.bTotalTransportation);
            $("#projectTonTimes").html(data.resp.bTotalTransportationTimes);
            $("#hairGoods").html(data.resp.bSendWeight);
            $("#hairGoodsTimes").html(data.resp.bSendWeightTimes);
            $("#collectGoods").html(data.resp.bRecevieWeight);
            $("#collectGoodsTimes").html(data.resp.bRecevieWeightTimes);
            $("#truckGoods").html(data.resp.bTotalLoadingVehicle);
            $("#transport").html(data.resp.bSendGoWeight);
            $("#transportTimes").html(data.resp.bSendGoWeightTimes);
            $("#difference").html((data.resp.bDiffAmountScale > 0 ? parseFloat(data.resp.bDiffAmountScale).toFixed(2) : "0.00"));//修正可能出现的负数
            var freight = data.resp.pOilCardAmount + data.resp.pEtcCardAmount + data.resp.pCashPayAmount;
            if(freight) {
                freight = parseFloat(freight).toFixed(2);
            }
            $("#freight").html(freight || 0);//已经支付的运费
            $("#card").html(data.resp.pOilCardAmount);
            $("#ETC").html(data.resp.pEtcCardAmount);
            $("#bill").html(data.resp.pCashPayAmount);//现金
        } else {
            $("#projectTon").html(0);
            $("#projectTonTimes").html(0);
            $("#hairGoods").html(0);
            $("#hairGoodsTimes").html(0);
            $("#collectGoods").html(0);
            $("#collectGoodsTimes").html(0);
            $("#truckGoods").html(0);
            $("#transport").html(0);
            $("#transportTimes").html(0);
            $("#difference").html("0.00");
            $("#freight").html(0);//已经支付的运费
            $("#card").html(0);
            $("#ETC").html(0);
            $("#bill").html(0);//现金
        }
    }

    function renderChart(obj, dataArr) {
        $(obj).highcharts({
            title: {
                text: null
            },
            subtitle: {
                text: null
            },
            lang: {
                noData: "暂时没有数据!"
            },
            plotOptions: {
                series: {
                    lineWidth: 2,
                    marker: {
                        radius: 3,  //曲线点半径，默认是4
                        symbol: 'circle' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                    }
                }
            },
            yAxis: {
                title: {
                    text: null
                },
                labels: {format: '{value:.,0f}'},
                plotLines: [{
                    value: 0,
                    color: '#808080'
                }]
            },
            xAxis: {
                categories: dataArr.charTime
            },
            tooltip: {
                valueSuffix: null,
                shared: true
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                borderWidth: 0,
                x: 100,
                y: 0
            },
            credits: {

                enabled: false

            },
            series: [{
                color: dataArr.colorArr,
                name: dataArr.text1,
                data: dataArr.charData
            }, {
                color: dataArr.colorArr1,
                name: dataArr.text2,
                data: dataArr.charData1
            }]
        });
    }

    //统计分析图表
    function renderChart1(dataArr) {
        dataArr.text1 = ["发货量"];
        dataArr.text2 = ["收货量"];
        dataArr.colorArr = "#eb385e";
        dataArr.colorArr1 = "#3a91f9";

        renderChart($("#chartContainer1"), dataArr);
    }

    function renderChart2(dataArr) {
        dataArr.text1 = ["运费统计"];
        dataArr.colorArr = "#f9bb00";

        renderChart($(".statisticsMainPublicChart").eq(1), dataArr);
    }

    function renderChart3(dataArr) {
        dataArr.text1 = ["装车量"];
        dataArr.colorArr = "#00c587";

        renderChart($(".statisticsMainPublicChart").eq(2), dataArr);
    }

    //初始化并缓存图表所需的数据
    if (!data._chartData1) {

        var map22data = {}; //发货数据
        var map23data = {}; //收货数据

        if (data.mapData) {//有地图数据
            var senderList = data.mapData[22];

            for (var i = 0; senderList && i < senderList.length; i++) {
                var key = new Date(senderList[i].date).Format("M.dd");
                map22data[key] = senderList[i].sendNetWeight || 0;
                map23data[key] = senderList[i].receiveNetWeight || 0;
            }
        }

        var charTime = [];
        var charData = [];
        var charData1 = [];
        var currentTime = new Date().getTime();

        for (var i = 14; i >= 0; i--) {
            var dateStr = new Date(currentTime - i * 24 * 60 * 60 * 1000).Format("M.dd");

            charTime.push(dateStr);
            charData.push(map22data[dateStr] || 0);
            charData1.push(map23data[dateStr] || 0);
        }

        data._chartData1 = {charTime: charTime, charData: charData, charData1: charData1};
    }

    if (!data._chartData2) {
        var map34data = {};

        if (data.mapData) {//有地图数据
            var moneyList = data.mapData[34];

            for (var i = 0; moneyList && i < moneyList.length; i++) {
                var key = new Date(moneyList[i].date).Format("M.dd");
                map34data[key] = moneyList[i].allCount || 0;
                if (map34data[key] <= 0) {//过滤为0的数据
                    map34data[key] = 0;
                }
            }
        }

        var charTime = [];
        var charData = [];
        var currentTime = new Date().getTime();

        for (var i = 14; i >= 0; i--) {
            var dateStr = new Date(currentTime - i * 24 * 60 * 60 * 1000).Format("M.dd");

            charTime.push(dateStr);
            charData.push(map34data[dateStr] || 0);

        }

        data._chartData2 = {charTime: charTime, charData: charData};
    }

    if (!data._chartData3) {

        var map28data = {};

        if (data.mapData) {//有地图数据
            var loadingList = data.mapData[28];

            for (var i = 0; loadingList && i < loadingList.length; i++) {
                var key = new Date(loadingList[i].date).Format("M.dd");
                map28data[key] = loadingList[i].allCarCount || 0;
            }
        }

        var charTime = [];
        var charData = [];
        var currentTime = new Date().getTime();

        for (var i = 14; i >= 0; i--) {
            var dateStr = new Date(currentTime - i * 24 * 60 * 60 * 1000).Format("M.dd");

            charTime.push(dateStr);
            charData.push(map28data[dateStr] || 0);
        }

        data._chartData3 = {charTime: charTime, charData: charData};
    }

    renderHtml();
    if (index == 1) {
        renderChart1(data._chartData1);
    } else if (index == 2) {
        renderChart2(data._chartData2);
    } else if (index == 3) {
        renderChart3(data._chartData3);
    } else {
        //刷新全部
        renderChart1(data._chartData1);
        renderChart2(data._chartData2);
        renderChart3(data._chartData3);
    }
}
//jquery mousewheel插件
var types = ['DOMMouseScroll', 'mousewheel'];
$.event.special.mousewheel = {
    setup: function () {
        if (this.addEventListener) {
            for (var i = types.length; i;) {
                this.addEventListener(types[--i], handler, false);
            }
        } else {
            this.onmousewheel = handler;
        }
    },
    teardown: function () {
        if (this.removeEventListener) {
            for (var i = types.length; i;) {
                this.removeEventListener(types[--i], handler, false);
            }
        } else {
            this.onmousewheel = null;
        }
    }
};
$.fn.extend({
    mousewheel: function (fn) {
        return fn ? this.bind("mousewheel", fn) : this.trigger("mousewheel");
    },
    unmousewheel: function (fn) {
        return this.unbind("mousewheel", fn);
    }
});
function handler(event) {
    var orgEvent = event || window.event, args = [].slice.call(arguments, 1), delta = 0, returnValue = true, deltaX = 0, deltaY = 0;
    event = $.event.fix(orgEvent);
    event.type = "mousewheel";
    // Old school scrollwheel delta
    if (event.originalEvent.wheelDelta) {
        delta = event.originalEvent.wheelDelta / 120;
    }
    if (event.originalEvent.detail) {
        delta = -event.originalEvent.detail / 3;
    }
    // New school multidimensional scroll (touchpads) deltas
    deltaY = delta;
    // Gecko
    if (orgEvent.axis !== undefined && orgEvent.axis === orgEvent.HORIZONTAL_AXIS) {
        deltaY = 0;
        deltaX = -1 * delta;
    }
    // Webkit
    if (orgEvent.wheelDeltaY !== undefined) {
        deltaY = orgEvent.wheelDeltaY / 120;
    }
    if (orgEvent.wheelDeltaX !== undefined) {
        deltaX = -1 * orgEvent.wheelDeltaX / 120;
    }
    // Add event and delta to the front of the arguments
    args.unshift(event, delta, deltaX, deltaY);
    return $.event.handle.apply(this, args);
}







//自己封装一个scrolling插件
(function ($) {
    $.scrolling = function (obj) {
        var $scrollContent = obj.find(".scroll-content"),
            $scrollBg = obj.find(".scroll-bg"),
            $scrollBar = obj.find(".scroll-bar"),
            clientH = obj.height(), //可见区域高度
            winH = $scrollContent.height(), //滚动区域高度
            atH = Math.floor(clientH * .75), //每次滚动为可见区域3/4
            num = Math.ceil(winH / atH) - 1, ///可以滚动的次数
            pos = 0, //当前区域值
            barH = Math.floor(clientH / num), ///bar高度
            barAtH = Math.floor((clientH - barH) / (num - 1)), ///滚动条每次滚动高度

            maxH = clientH - barAtH, //滚动条y最大值
            barContentH = (winH - clientH) / maxH; ///滚动条对应右侧滚动长度

        $scrollBar.height(barH);//设置滚动条高度

        //判断是否显示滚动条
        if (winH <= clientH) {
            return false;
        } else {
            $scrollBg.show();
        }

        //鼠标滚轮
        obj.bind('mousewheel', function (e, deta) {
            if (deta < 0) { //向上滚动
                if (pos > num - 2)return false;
                pos++;
            } else { //向上滚动
                if (pos < 1)return false;
                pos--;
            }

            $scrollContent.stop(true, false).animate({"top": -pos * atH}, 300);
            $scrollBar.stop(true, false).animate({"top": pos * barAtH}, 300);

            return false;
        });

        //滚动条、内容区域滚动
        var changeScroll = function (y) {
            $scrollBar.css({'top': y + "px"});
            $scrollContent.css({'top': -y * barContentH + "px"}, 100);
        }

        //滚动条拖拽
        obj.find(".scroll-bar").mousedown(function (e) {
            var position = $(this).position();
            var y = e.pageY - position.top;

            $(document).bind("mousemove", function (ev) {
                $(this).bind('selectstart', function () {
                    return false;
                });
                var _y = ev.pageY - y;
                if (_y <= 0 || _y >= maxH)return false;
            });

        });

        $(document).mouseup(function () {
            $(this).unbind("mousemove");
        });

        //阻止点击父级往上冒泡
        $scrollBar.click(function () {
            return false;
        })

        //点击滚动条父级
        $scrollBg.click(function (e) {
            var y = (e.pageY - $(this).offset().top - barH / 2);
            if (y < 0) {
                y = 0;
            } else if (y > maxH) {
                y = maxH;
            }
            changeScroll(y)
        })

    };
})(jQuery)
//封装上传图片
function getBase64Image(img) {
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, img.width, img.height);
    var ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
    var dataURL = canvas.toDataURL("image/" + ext);
    return dataURL;
}
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
    }
    $.fileUpload.prototype.handle = function(){
        var _this = this;
        _this.s.inputObj.unbind("change").bind("change",function(){
            var file, img, imgName, src, src2, reader;
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
                src = _URL.createObjectURL(file);//把这个url放到上面展示
                img = document.createElement("img");
                img.src = src;
                img.width = 93;//设置图片宽
                img.height = 70;//设置图片高<br>
                _this.s.previewContiner.html(img);
               // $(this).next().hide();
                _this.reader = new FileReader();
                _this.reader.onload = function (e) {
                    _this.s.imgobj[imgName] = this.result;//存储图片到对象中
                }
                _this.reader.readAsDataURL(file);
            }
        })
    }
})($);

function initLayout(){
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage-160 + "px");
}
//获取真实金额
function getRealAmount(item) {
    var amount= 0;
    var orderType=item.orderType;
    if(orderType == 1||orderType == 2 || orderType == 3||orderType == 7||orderType == 8){
        amount =  item.amount==null?0:item.amount;
    }else {
        amount = item.gasAmount==null?0:item.gasAmount;
    }
    return amount;
}



