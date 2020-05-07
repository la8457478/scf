<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    body{overflow-y:auto !important;}
    #licensePlateNo{padding-bottom: 5px;}
    #licensePlateNo span{font-size: 16px;font-weight: bold;}
    #adress img{float: left;}
    #adress span{
        display: inline-block;
        width: 235px;
        float: left;
    }
    #time{padding-bottom: 10px;}
    .goodsDetailsContainer{overflow: hidden;}
    #lngAndLatInfo{float: left;  width: 242px;  display: inline-block;}
    .mapListMain{width: 300px;height: 400px;background: white;overflow-x:hidden;position: absolute;right: 15px;z-index: 99;top:20px;overflow-y: auto;}
    .mapListMain ul li img{margin-left: 15px;position: relative;top:3px;}
    .mapListMain ul li{padding-top: 17px;clear: both;}
    .mapListMain ul li .mapTextMsg{float: left;}
    .sideIconMain{    position: absolute;  top: 40%;  width: 40px;  height: 80px;  background: white;  right: 315px;  z-index: 999999999;    text-align: center;  line-height: 80px;  cursor: pointer;}
    #mapListMainList li:last-child{padding-bottom: 60px;}
    .mapTextMsg{float: left;width: 210px;}
    .mapTextMsg p{padding-top: 3px;text-align: justify;}
    .mapTextMsg p a{color:#999999;}
    .waybillNumLine{display: inline-block;}
    /*@media screen and (max-width: 1550px) and (min-width: 100px){*/
        /*.newPublicHeader{height: 95px;}*/
        /*.lineMsg{float: left !important;*/
            /*width: 100%;*/
            /*margin-left: 20px;*/
            /*margin-top: -12px;}*/
        /*.waybillNumLine{display: block;}*/
        /*.publicDetailsModuleReturnBtn{margin-top: 0px;margin-right: 30px;}*/
    /*}*/

</style>
<script type="text/javascript" src="newassets/js/fkhwl/AngleUtils.js"></script>
<script type="text/javascript" src="newassets/js/jquery.page.js"  ></script>

<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a href="javascript:">运单管理</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a href="javascript:">查看行程</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>
<!--顶部导航 end-->

<div class="publicContainer" id="heightPage" style=" background: white; width: 100%;">
    <div class="publicMainContainerBox" style="margin-top: 0px;position: relative;">
        <!--出发地与目的地-->
        <div class="newPublicHeader">
            <%--暂时去掉文字模式 2019-10-15--%>
            <%--<div class="newPublicCustomGoodsBtn" id="changeModel" onclick="changeModel()">--%>
                <%--<a id="changeModel_text">文字模式</a>--%>
            <%--</div>--%>
           <div class="waybillNumLine">
                <span title="${waybill.waybillNo} - ${waybill.departureCity} -${waybill.arrivalCity}">${waybill.waybillNo} - ${waybill.departureCity}
                <img class="icon_new_arrow_city" src="newassets/imgs/icon_new_arrow.png">${waybill.arrivalCity}
            </span>
               <%--<select class="newPublicCustomSearchBtn" name="gpsType" id="gpsType" onchange="changeGpsType()" style="max-width: 280px;">--%>
                   <%--&lt;%&ndash;<option value="-1">系统轨迹</option>&ndash;%&gt;--%>
                   <%--&lt;%&ndash;<option value="5">短信定位</option>&ndash;%&gt;--%>
                   <%--&lt;%&ndash;<c:if test="${hasZhongJiaoAuth == 1}">&ndash;%&gt;--%>
                   <%--&lt;%&ndash;<option value="4">中交轨迹</option>&ndash;%&gt;--%>
                   <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
               <%--</select>--%>
                <input type="hidden" value="${waybill.gpsType}" id="gpsType">

               <span id = "noGPSTips" style="color: red"></span>
               <span id = "isThisPage" style="display: none">1</span>
           </div>

            <span id="messageWithoutRoute" style="color: red;font-size: 14px">该运单没有轨迹点</span>

            <div style="float: right;" class="lineMsg">
                <%--<span style="background-color: #00d340"></span>--%>
                <%--<span style="display: inline-block; margin-right: 36px; padding-left: 10px;">规划路线</span>--%>
                <span style="background-color: #0087ee"></span>
                <span style="display: inline-block; margin-right: 90px; padding-left: 10px;">行驶路线</span>
                <div class="publicDetailsModuleReturnBtn detailsMap return">返回</div>
            </div>
        </div>
        <!--出发地与目的地end-->
        <div class="clear"></div>
        <!--文字信息-->
        <div class="goodsDetailsContainer detailsMap"  style="margin-top: 0px;position: relative;clear:both;">
            <!--地图列表展示 start-->
            <%--<div class="mapListMain" id="mapListMainAllmap">--%>
                <%--<ul id="mapListMainList">--%>

                <%--</ul>--%>
            <%--</div>--%>
            <%--<div class="sideIconMain" id="sideIconMain" onclick="sideIconMain(this)">--%>
                <%--<img src="newassets/imgs/icon_ retract1.png">--%>
            <%--</div>--%>
            <!--地图列表展示 end-->
            <div id="allmap" class="mapContainerBox"></div>
        </div>

        <div id="infoWindowTemplate" style="display:none;">
            <style>
                #showTable {
                    text-align: center; /*让div内部文字居中*/
                    background-color: #fff;
                    width: 97%;
                    height: 600px;
                    margin: auto;
                    margin-top: 15px;
                    max-height: 600px;
                    overflow-y: auto;
                }

                #showTable td {
                    text-align: left; /*让div内部文字居中*/
                    font-size: 14px;
                }
            </style>
            <div id="carInfo">
                <div id="licensePlateNo">
                    <span>${waybill.licensePlateNo}</span>
                    <div id="time" style="display: inline-block;">
                        <a id="realTime" style="font-size: 13px"></a>
                    </div>
                </div>
                <div style="padding-bottom: 10px;">
                    <a style="font-weight: bold;color: #2889F1;" id="msg"></a>
                </div>
                <div id="adress">
                    <a style="float: left;width: 66px;">定位地址：</a>
                    <span id="dizhi" style="font-size: 13px"></span>
                </div>
                <div class="clear"></div>
                <div id="lngAndLat"  style="padding-top: 5px">
                    <a style="float: left;width: 66px;">经纬度：</a>
                    <span id="lngAndLatInfo" style="font-size: 13px"></span>
                </div>
                <div id="equip"  style="padding-top: 5px;padding-left: 66px;">
                    <span id="equipInfo" style="font-size: 13px"></span>
                </div>
                <%--<div id="sim"  style="padding-left: 28px;padding-top: 5px">--%>
                <%--<span id="simNo" style="font-size: 10px"></span>--%>
                <%--</div>--%>
            </div>
            <div id="showTextMessage" style="display: none">
                <div id="message" style="padding-top: 10px;padding-left: 10px;font-weight: bold">
                    <span style="font-size:18px;padding-left: 15px">${waybill.licensePlateNo}</span>
                    <span id="equipType" style="font-size:18px;padding-left: 15px"></span>
                    <span style="font-size:18px">车辆</span>
                    <span style="font-size:18px;padding-left: 15px">sim卡号:</span>
                    <span id="simNo" style="font-size:18px"></span>
                </div>
                <div id="showTable" class="newPublicTable" style="text-align: center">
                    <table width="200">
                        <colgroup>
                            <col width="20%">
                            <col width="20%">
                            <col width="40%">
                        </colgroup>
                        <tr>
                            <th >定位时间</th>
                            <th >经纬度</th>
                            <th >地址</th>
                        </tr>
                        <tbody id="allCarInfoList"></tbody>
                    </table>
                </div>
                <div id="tblSocialVehiclePageBar"  class="pageBar"></div>
            </div>


        </div>
        <!--地图信息 end-->
    </div>
</div>

<script type="text/javascript">
    <%--var currentGpsInfo = null;--%>
    <%--<c:if test="${!empty gpsInfo}">--%>
        <%--currentGpsInfo=${gpsInfo};--%>
    <%--</c:if>--%>
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");


    $("#allmap").css("height", heightPage - 240 + "px");
    $("#mapListMainAllmap").css("height", heightPage - 260 + "px");
    //必须进行初始化，否则会和线路规划页面引起冲突

    //TMS规划路径不显示接单点 add by wudq 2019/01/23
    var rushAddressPoint = null;
    var loadAddressPoint = null;
    var radius = null;//新增装卸货地围栏半径
    var arrivalAddressPoint = null;
    var gpsInfoPoint = null;
    var gpsInfoTime = null;
    var gpsInfoAdress = null;
    var realRoutePoints = null;
    var planRoutePoints = null;
    var map = null;
    //用作记录所有经纬度
    var realTimes = null;
    var realAdress = null;
    var num = 1;
    //记录街道信息
    var province = 0;
    var city = 0;
    var district = 0;
    var street = 0;
    var records = [];

    $(document).ready(function () {
        Page.showPageTitleBindReturnUrl();

        //初始化GPS类型下拉框
        initSelect();
        initMap();

        $("#mapListMainAllmap").show();
        $("#sideIconMain").show();
    });
    //初始化地图及轨迹信息
    function initMap(){
        map = new BMap.Map("allmap");    // 创建Map实例
        map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        var bottom_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 左上角，添加比例尺
        var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
        //添加控件和比例尺
        map.addControl(bottom_left_control);
        map.addControl(top_left_navigation);

        // if(currentGpsInfo != null){
        //     gpsInfoPoint = new BMap.Point(currentGpsInfo.longitude, currentGpsInfo.latitude);
        //     gpsInfoTime = currentGpsInfo.sendTime;
        //     gpsInfoAdress = currentGpsInfo.location;
        // }

        //使用地址
        geocoding("${departureCity}", "${arrivalCity}", function () {
            <c:if test="${!empty loadAddress}">
            loadAddressPoint = new BMap.Point(${loadAddress.centerLng}, ${loadAddress.centerLat});
            </c:if>
            radius = ${loadAddress.radius}
            <c:if test="${!empty arrivalAddress}">
            arrivalAddressPoint = new BMap.Point(${arrivalAddress.centerLng}, ${arrivalAddress.centerLat});
            </c:if>
            drawStart();
        });
    }

    //初始化下拉框
    function initSelect(){
        // $("#messageWithoutRoute").hide();
        <%--var gpsTypeList=[];--%>
        <%--<c:forEach items="${gpsTypeList}" var="item">--%>
        <%--var obj = {"gpsType": ${item.gpsType},"name": "${item.name}","count": ${item.count}};--%>
        <%--gpsTypeList.push(obj);--%>
        <%--</c:forEach>--%>
        <%--var html  = '';--%>
        <%--//用于避免页面上出现两个中交轨迹--%>
        <%--var shouldHideZhongjiao = true;--%>
        <%--if(gpsTypeList != null) {--%>
            <%--for (i = 0; i < gpsTypeList.length; i++) {--%>
                <%--if (i == 0) {--%>
                    <%--html +=  '<option value="' + gpsTypeList[i].gpsType + '" selected>' + gpsTypeList[i].name + '</option>';--%>
                <%--} else {--%>
                    <%--html += '<option value="' + gpsTypeList[i].gpsType + '">' + gpsTypeList[i].name + '</option>';--%>
                <%--}--%>
                <%--if(gpsTypeList[i].gpsType == 4){--%>
                    <%--//如果transportWay返回了中交轨迹类型，则--%>
                    <%--shouldHideZhongjiao = false;--%>
                <%--}--%>
            <%--}--%>
        <%--}--%>
        <%--<c:if test="${hasZhongJiaoAuth == 1}">--%>
        <%--if(shouldHideZhongjiao){--%>
            <%--html += '<option value="4">中交轨迹</option>';--%>
        <%--}--%>
        <%--</c:if>--%>
        if("${waybill.gpsType}"  == ""){
            //运单一种轨迹都没有的话，就隐藏下拉框
            // $("#gpsType").hide();
            //告知用户该运单没有轨迹
            $("#messageWithoutRoute").show();
        }else{
            // $("#gpsType").html(html);
            // $("#gpsType").show();
            $("#messageWithoutRoute").hide();
        }
    }
    //使用定位点
    function drawStart() {

        //设置中心点, 调整为最佳视野，防止规划路线回调两次bug
        map.setViewport([loadAddressPoint, arrivalAddressPoint]);

        //绘制关键点, 先设置中心点
        // var points = drawPoints();

        //获取实际路线，从派车后开始显示轨迹
        getRealRoute();
        // if(firstNode.length<1){
        //     //设置中心点, 调整为最佳视野，防止规划路线回调两次bug
        //     map.setViewport(points);
        // }
        //获取规划路线
        // getPlanRoute(function (points) {
        //     planRoutePoints = points;
        //     //进行绘制
        //     drawPlanRoutes(planRoutePoints);
        // });

    }
    function geocoding(loadAddress, arrivalAddress, callback) {
        // 创建地址解析器实例
        var myGeo = new BMap.Geocoder();
        // 将地址解析结果显示在地图上,并调整地图视野
        myGeo.getPoint(loadAddress, function (lPoint) {
            if (lPoint) {
                loadAddressPoint = lPoint;
            } else {
                console.log("装货地没有解析到结果");
            }

            myGeo.getPoint(arrivalAddress, function (aPoint) {
                if (aPoint) {
                    arrivalAddressPoint = aPoint;
                } else {
                    console.log("卸货地没有解析到结果");
                }
                callback();
            });
        });
    }

    function drawPoints() {
        var marker = null;
        var points = [];

        //为地图上的各种点制作弹框 by lcy 2018-08-14
        var sContent=$("#carInfo").html();
        var infoWindow = new BMap.InfoWindow(sContent,{width:310,height:0});
        var gc = new BMap.Geocoder();

        //用户配置装货地经纬点（或省出发地市区取的某个位置）装货地icon
        // marker = new BMap.Marker(loadAddressPoint, {icon: new BMap.Icon("newassets/imgs/icon_map_start.png", new BMap.Size(36, 36))});
        <c:if test="${!empty loadAddress}">
            marker = new BMap.Marker(loadAddressPoint, {icon: new BMap.Icon("newassets/imgs/icon_map_load.png", new BMap.Size(36, 72))});
            marker.setZIndex(100);
            map.addOverlay(marker);
            <%--showCarInfo(marker, "${loadAddress.centerAddress}", P.long2Datetime("${loadAddress.createTime}"), infoWindow);--%>
            points.push(loadAddressPoint);
            var oval = new BMap.Circle(loadAddressPoint, radius, {fillColor:"red",strokeColor: "red", strokeWeight: 1,  fillOpacity: 0.2,strokeOpacity: 0.2});
            map.addOverlay(oval);
        </c:if>

        //用户配置卸货地经纬点（或省目的地市区取的某个位置）卸货地icon
        // marker = new BMap.Marker(arrivalAddressPoint, {icon: new BMap.Icon("newassets/imgs/icon_map_end.png", new BMap.Size(36, 36))});
        <c:if test="${!empty arrivalAddress}">
            marker = new BMap.Marker(arrivalAddressPoint, {icon: new BMap.Icon("newassets/imgs/icon_map_arrival.png", new BMap.Size(36, 72))});
            marker.setZIndex(101);
            map.addOverlay(marker);
            <%--showCarInfo(marker, "${arrivalAddress.centerAddress}", P.long2Datetime("${arrivalAddress.createTime}"), infoWindow);--%>
            points.push(arrivalAddressPoint);
            var oval = new BMap.Circle(arrivalAddressPoint, radius, {fillColor:"red",strokeColor: "red", strokeWeight: 1,  fillOpacity: 0.2,strokeOpacity: 0.2});
            map.addOverlay(oval);
        </c:if>

        //车辆当前位置
        if (gpsInfoPoint) {
            //已完成运单不显示车辆图标
            var myIcon = new BMap.Icon("newassets/imgs/socail-icon-car.png", new BMap.Size(30, 36), {
                //小车图片
                //offset: new BMap.Size(0, -5),
                //图片的偏移量。为了是图片底部中心对准坐标点。
                imageOffset: new BMap.Size(0, 0)
            });
            marker = new BMap.Marker(gpsInfoPoint, {icon: myIcon});
            marker.setZIndex(105);
            //绘制车辆当前位置
            map.addOverlay(marker);
            showCarInfo(marker, gpsInfoAdress, P.long2Datetime(gpsInfoTime), infoWindow);
            points.push(gpsInfoPoint);
        }

        return points;
    }


    //绘制规划路线路线
    function drawPlanRoutes(planRoutePoints){
        if (!planRoutePoints) {
            return;
            //规划路线
        }
        if (planRoutePoints.length > 0) {
            var polyline = new BMap.Polyline(planRoutePoints, {
                strokeColor: "#00d340",
                strokeWeight: 6,
                strokeOpacity: 0
            });
            polyline.setStrokeOpacity(0);
            map.addOverlay(polyline);
        }
    }
    /**
     * bPoints 通过push showPoint动态计算地图大小
     * */
    var bPoints = new Array();
    var realAdress1;
    var realTimes1;
    var startRealRoutePoints;
    //绘制真实路线路线
    function drawRealRoutes(realRoutePoints,realAdress,realTimes){
        if (!realRoutePoints) {
            return;
        }
        if(realRoutePoints[0] != ""){
            startRealRoutePoints = realRoutePoints[0];//起点
        }else if(realAdress[0] !=""){
            realAdress1 = realAdress[0];
        }else if(!realTimes[0] != ""){
            realTimes1 = realTimes[0];
        }

        var endRealRoutePoints= realRoutePoints[realRoutePoints.length - 1];//终点；


        if (realAdress1 == "" || realAdress1 == null) {
            //防止有部分点地位为空时，从现有的经纬度调百度接口查地址
            var gc1 = new BMap.Geocoder();
            gc1.getLocation(startRealRoutePoints, setAdresses, {poiRadius: 100});
        }

        //真实路线
        if (realRoutePoints.length > 0) {
            var polyline = new BMap.Polyline(realRoutePoints, {
                strokeColor: "#0087ee",
                strokeWeight: 6,
                strokeOpacity: 0
            }); //创建弧线对象
            polyline.setStrokeOpacity(0);
            //短信定位不需要将真实点连接起来
            if($("#gpsType").val()!=5){
                map.addOverlay(polyline);
            }
            //添加车辆实际运行点 by lcy
            if (realRoutePoints.length > 0) {
                // var data=realRoutePoints;
                var data = realRoutePoints;
                var showpoints = [];
                var myIcon = new BMap.Icon("newassets/imgs/route_point.png", new BMap.Size(14, 14), {
                    //offset: new BMap.Size(0, -5),
                    //图片的偏移量。为了是图片底部中心对准坐标点。
                    imageOffset: new BMap.Size(0, 0)
                });
                var sContent = $("#carInfo").html();
                var infoWindow = new BMap.InfoWindow(sContent, {width: 310, height: 0});
                var gc = new BMap.Geocoder();

                var marker = null;
                var adress = "";
                var time = "";
                var showPoint = [];

                for (var i = 0, j = 0; i < data.length; i += num, j++) {
                    showpoints[j] = data[i];
                    showPoint = new BMap.Point(showpoints[j].lng, showpoints[j].lat);
                    marker = new BMap.Marker(showPoint, {icon: myIcon});
                    marker.setZIndex(104);
                    map.addOverlay(marker);
                    adress = realAdress[i];
                    time = P.long2Datetime(realTimes[i]);
                    //暂时去掉文字模式 2019-10-15
                    // showRealPoints.push(showPoint);
                    // showRealAdress.push(adress);
                    // showRealTimes.push(time);
                    showCarInfo(marker, adress, time, infoWindow, gc);
                    // $("#infoWindowTemplate")[0].innerHTML=sContentTemp;
                    // $("#carInfo").hide();
                    // $("#showTextMessage").show()
                    bPoints.push(showPoint);
                }
                setZoom(bPoints);
            }
        }

    }
    /**
     * bPoints 通过push showPoint动态计算地图大小 -1 让计算出的地图层级缩小
     * */
    function setZoom(bPoints) {
        var view = map.getViewport(eval(bPoints));
        var mapZoom = view.zoom;
        var centerPoint = view.center;
        map.centerAndZoom(centerPoint,mapZoom);
    }
//    var point1 = new BMap.Point(117.9494556938458,28.46061897505298)
//    var circle = new BMap.Circle(point1,3000,{strokeColor:"red", strokeWeight:2, strokeOpacity:0.5,fillColor: "red"}); //创建圆
//    map.addOverlay(circle);
//    console.log(circle)

    function sleep(delay) {
        var start = (new Date()).getTime();
        while ((new Date()).getTime() - start < delay) {
            continue;
        }
    }
    function getRealRoute() {
        var gpsType = $("#gpsType").val();
        if (P.isNullOrEmptyString(gpsType)) {
            return false;
        }
        <%--var postTemplate = {--%>
            <%--"waybillId": "${waybill.thirdId}",--%>
            <%--"vehicleId": "${carInfoId}",--%>
            <%--"startTime": "${gpsStartTime}",--%>
            <%--"endTime": "${gpsEndTime}",--%>
            <%--"maxRows": "1000",--%>
            <%--"gpsType": gpsType,--%>
            <%--"pageNo": 1--%>
        <%--};--%>

        var totalPages = 1; //轨迹点总页数
        var gpsInfos;       //每次分页中获得的所有信息
        var points = [];    //每次分页中获得的轨迹点
        var times = [];     //每次分页中获得的轨迹时间
        var adress = [];    //每次分页中获得的轨迹点地址
        P.showLoading();
        var waybillStatus = "3";
        //清空文字模式的地址，时间，轨迹点
        //暂时去掉文字模式
        // showRealAdress = [];
        // showRealTimes = [];
        // showRealPoints = [];
        $.post("waybill/listPageTrackPointInfo/${waybill.thirdId}/"+gpsType+".json", null, function (resp) {
            if(resp.success) {
                if (resp.data.gps != null) {
                    totalPages = resp.data.gps.pageinfo.totalPages;
                    gpsInfos = resp.data.gps.gpsInfos;
                    var totalResults = resp.data.gps.pageinfo.totalResults;
                    var newGpsType = $("#gpsType").val();
                    //只有中交轨迹需要提示
                    if(gpsInfos.length == 0 && 4 == gpsType){
                        $("#noGPSTips").html("该运单无中交轨迹");
                    }
                    var gpsinfo = gpsInfos[0];
                    var lastGpsInfo = gpsInfos[gpsInfos.length-1];
                    var  currentPage = resp.data.gps.pageinfo.currentPage;
                    if (gpsinfo != '' && gpsinfo != null) {
                        //设置页面基本信息
                        var stepNum = resp.data.num
                        setBasicInfo(gpsinfo,stepNum,true);
                        //设置需要绘制的轨迹点
                        handleRealRoute(gpsInfos,points,times,adress);
                        //短信定位点绘制规划路径
                        if(gpsinfo.fromType==5){
                            handleRealRouteByMessage(gpsInfos)
                        }

                        if(waybillStatus >= 3){
                            if(currentPage == totalPages && newGpsType == gpsType){
                                addWaybillCompleteMarker(gpsInfos[gpsInfos.length-1],pointArrList(gpsInfos[gpsInfos.length-1],gpsinfo.fromType))
                            }
                        }
                    }
                }
                /**
                 $.ajaxSetup({
                async : false
                });
                 */
                queryGPSByPage(1,totalPages,points,times,adress,waybillStatus,gpsType,lastGpsInfo);
            }
            P.hideDialog();
        });
    }

    /**
     * 逐页查看定位点
     *
     *  */
    function queryGPSByPage(i,totalPages,points,times,adress,waybillStatus,gpsType,lastGpsInfo) {
        i = i + 1;
        var newGpsType = $("#gpsType").val();
        var flag = $("#isThisPage").html();
        if(i <= totalPages && !P.isNullOrEmptyString(newGpsType) && newGpsType == gpsType && flag != undefined){
            var gpsInfos = [];
            var postData = {"pageNo": i, "maxRows": 1000};
            $.post("waybill/listPageTrackPointInfo/${waybill.thirdId}/"+gpsType+".json", postData, function (resp) {
                if (resp.data.gps != null) {
                    gpsInfos = resp.data.gps.gpsInfos;
                    var gpsinfo = resp.data.gps.gpsInfos[0];
                    var newLastGpsInfo = gpsInfos[gpsInfos.length-1];
                    var  totalPages = resp.data.gps.pageinfo.totalPages;
                    var  currentPage = resp.data.gps.pageinfo.currentPage;
                    var newGpsType = $("#gpsType").val();
                    var newGpsInfos =[];
                    newGpsInfos.push(lastGpsInfo);
                    var newGpsInfos1 = newGpsInfos.concat(gpsInfos);

                    queryGPSByPage(i,totalPages,points,times,adress,waybillStatus,gpsType,newLastGpsInfo);
                    //设置需要绘制的轨迹点
                    handleRealRoute(newGpsInfos1,points,times,adress);
                    if(waybillStatus >= 3){
                        if(currentPage == totalPages && newGpsType == gpsType){
                            addWaybillCompleteMarker(gpsInfos[gpsInfos.length-1],pointArrList(gpsInfos[gpsInfos.length-1],gpsinfo.fromType))
                        }
                    }
                    newGpsInfos1 = "";
                    gpsInfos = "";
                    gpsinfo = "";
                }
            });
        }

    }
    function pointArrList(gpsinfo,fromType){
        var  pointsArr= "";
        if(fromType== 4) {
            var tmp = wgs84togcj02(gpsinfo.longitude, gpsinfo.latitude);
            var p = gcj02tobd09(tmp[0], tmp[1]);
            pointsArr = (new BMap.Point(p[0].toFixed(8).substring(0,p[0].toFixed(8).length-2), p[1].toFixed(8).substring(0,tmp[1].toFixed(8).length-2)));
        }else{
            pointsArr = (new BMap.Point(gpsinfo.longitude.toFixed(8).substring(0,gpsinfo.longitude.toFixed(8).length-2),
                    gpsinfo.latitude.toFixed(8).substring(0,gpsinfo.latitude.toFixed(8).length-2)));
        }
        return pointsArr;
    }
    /**
    * 绘制短信定位轨迹可行走路线
    * */
    function handleRealRouteByMessage(gpsInfos){
        var lng=[];
        var lat=[];
        for(var i = 0; i <gpsInfos.length; i ++){
            lng.push(gpsInfos[i].longitude);
            lat.push(gpsInfos[i].latitude);
        }
        var pointLen = lng.length;
        var wayPoint = [];
        var allPoint = [];
        for(var i = 0; i < pointLen; i++){
            wayPoint[i] = new BMap.Point(lng[i], lat[i]);//轨迹点
            allPoint[i] = new BMap.Point(lng[i], lat[i]);//轨迹点
        }
        var startPoint = wayPoint[0];//起点
        var endPoint = wayPoint[wayPoint.length - 1];//终点
        wayPoint.pop();
        wayPoint.shift();
        var routePolicy = [BMAP_DRIVING_POLICY_LEAST_TIME,BMAP_DRIVING_POLICY_LEAST_DISTANCE,BMAP_DRIVING_POLICY_AVOID_HIGHWAYS]; //三种驾车策略：最少时间，最短距离，避开高速
        var driving = new BMap.DrivingRoute(map, {
            renderOptions:{
                map: map,
                autoViewport: true
            }
            ,policy: routePolicy[0]
        });
        // if(wayPoint.length == 0){
            driving.search(startPoint, endPoint);
        // }else{
        //     driving.search(startPoint,endPoint, {waypoints: wayPoint});//waypoints绘制途经点
        // }
        driving.setMarkersSetCallback(function(routes){
            map.removeOverlay(routes[0].marker);
            map.removeOverlay(routes[routes.length-1].marker);
            for(var m=1;m<routes.length-1;m++){
                var mm=routes[m].Nm;
                map.removeOverlay(mm)
            }
        });
        /*
         * 修改默认颜色
         * */
        driving.setSearchCompleteCallback(function(){
            var plan = driving.getResults().getPlan(0);
            for(var i=0;i<plan.getNumRoutes();i++){
                var pts =plan.getRoute(i).getPath();
                var polyline = new BMap.Polyline(pts,{ strokeColor: "#44b5c7", strokeWeight: 5, strokeOpacity: 1 });
                map.addOverlay(polyline);
            }
        });
    }
    /*
    * 设置页面基本信息：由于轨迹点的类型，sim号，都相同，因此取第一个点的轨迹来进行全页面的设置
    *   1.设置轨迹点显示递增数num
    *   2.设置轨迹设备类型
    *   3.设置页面sim号码
    *   4.设置地图层级缩放
    *   5.设置运单起点
    *   isTrue:第一个点需要设置起点
    * */
    function setBasicInfo(gpsinfo,stepNum,isTrue){
//        console.log(gpsinfo)
        var equipType = gpsinfo.fromType;
        if (stepNum != null) {
            num = stepNum;
        }
        if (equipType == 1) {
            equipType = "GPS";
        } else if (equipType == 3) {
            equipType = "云镜";
        } else if (equipType == 4) {
            equipType = "中交";
        }else if (equipType == 5) {
            equipType = "短信定位";
        } else if (equipType == 6) {
            equipType = "载重感知"
        } else {
            equipType = "客户端";
        }
        var simNo = gpsinfo.simNo;
        simNo = null == simNo || "null" == simNo || "" == simNo ? "" : simNo;
        var equip = "定位由" + equipType + "设备";
        equip += " " + simNo + "上报";
        $("#equip").text(equip);
        $("#equipType").text(equipType);
        $("#simNo").text(simNo);
        //设置中心位置为实际运行轨迹的第一个点
        var firstNode=[];
//        firstNode.push(new BMap.Point(gpsinfo.longitude, gpsinfo.latitude));
//        map.setViewport(firstNode);
//        map.setZoom(12);//缩放为市级
        var pointsArr="";
        if(gpsinfo.fromType== 4) {
            var tmp = wgs84togcj02(gpsinfo.longitude, gpsinfo.latitude);
            var p = gcj02tobd09(tmp[0], tmp[1]);
             pointsArr = (new BMap.Point(p[0].toFixed(8).substring(0,p[0].toFixed(8).length-2), p[1].toFixed(8).substring(0,tmp[1].toFixed(8).length-2)));
        }else{
             pointsArr = (new BMap.Point(gpsinfo.longitude.toFixed(8).substring(0,gpsinfo.longitude.toFixed(8).length-2),
                    gpsinfo.latitude.toFixed(8).substring(0,gpsinfo.latitude.toFixed(8).length-2)));
        }
        if(isTrue){
            addStartMarker(gpsinfo,pointsArr);
        }

    }

    /*
   *   1.设置需要绘制真实路径的所有点
   *   2.设置需要在页面显示为圆点的轨迹点
   *   3.绘制真实轨迹点
   * */
    function  handleRealRoute(gpsInfos,points,times,adress) {
        if (gpsInfos != null && gpsInfos.length > 0) {
            points = [];
            times = [];
            adress = [];
            for (var j = 0; j < gpsInfos.length; j++) {
                var gpsType = $("#gpsType").val();
                if(gpsType != gpsInfos[j].fromType){
                    continue;
                }
                if(gpsInfos[0].fromType==4) {
                    var tmp = wgs84togcj02(gpsInfos[j].longitude, gpsInfos[j].latitude);
                    var p = gcj02tobd09(tmp[0], tmp[1]);
                    points.push(new BMap.Point(p[0].toFixed(8).substring(0,p[0].toFixed(8).length-2), p[1].toFixed(8).substring(0,tmp[1].toFixed(8).length-2)));
                }else{
                    points.push(new BMap.Point(gpsInfos[j].longitude.toFixed(8).substring(0,gpsInfos[j].longitude.toFixed(8).length-2),
                                               gpsInfos[j].latitude.toFixed(8).substring(0,gpsInfos[j].latitude.toFixed(8).length-2)));
                }

                times.push(gpsInfos[j].sendTime);
                adress.push(gpsInfos[j].location);
            }
            realAdress = adress;
            realTimes = times;
            realRoutePoints = points;
            //进行绘制
            drawRealRoutes(realRoutePoints, realAdress, realTimes);
        }

    }
    function getPlanRoute(callback) {
        //默认最短距离
        var options = {
            "renderOptions": {"map": null, "autoViewport": true}, "policy": 1,
            onSearchComplete: function (drivingRouteResult) {
                var points = [];
                var plan = drivingRouteResult.getPlan(0);
                var routeNum = plan.getNumRoutes();

                for (var i = 0; i < routeNum; i++) {
                    var path = plan.getRoute(i).getPath();
                    points = points.concat(path);
                }
                callback(points);
            }
        };
        //方案1，绘制两段路线。
//        if(rushAddressPoint) {
//            var driving = new BMap.DrivingRoute(map, options);
//            driving.search(rushAddressPoint, loadAddressPoint);
//        }
//        var driving = new BMap.DrivingRoute(map, options);
//        driving.search(loadAddressPoint, arrivalAddressPoint);

        //方案2，采用途径点
        var driving = new BMap.DrivingRoute(map, options);
        if (rushAddressPoint) {
            driving.search(rushAddressPoint, arrivalAddressPoint, {waypoints: [loadAddressPoint]});
        } else {
            driving.search(loadAddressPoint, arrivalAddressPoint);
        }
    }

    function editRoutePage(waybillId) {
        var url = "waybills/editRoute.json?waybillId=" + waybillId;
        ajaxGet(url, function (resp) {
            if (resp != '' && resp != null) {
                $(window.mainContainer).html(resp);
            }
        });
    }

    function showCarInfo(marker, adress, time, infoWindow, gc,msg,type, log) {
        marker.addEventListener("click", function () {
            this.openInfoWindow(infoWindow);
            var point = this.getPosition();
            if (adress == "" || adress == null) {     //防止有部分点地位为空时，从现有的经纬度调百度接口查地址
                gc.getLocation(point, setAdress, {poiRadius: 100});
            }
            point = "(" + point.lng + "," + point.lat + ")";
            $("#lngAndLatInfo").text(point);
            if (time != null || time != "") {
                $("#realTime").text(time);
            }
            $("#dizhi").text(adress);
            if(type == 1){
                $("#msg").html(msg);
                $("#msg").show();
            }
            if (log) {
                setBasicInfo(log);
                $("#equip").css("color","#7b7777");
            }
            // else if(type == 2){
            //      $("#msg").html("运单号已生成："+msg);
            //      $("#msg").show();
            // }
            else{
                $("#msg").hide();
            }
        });
        // this.openInfoWindow(infoWindow);
        //图片加载完毕重绘infowindow
        // document.getElementById('icon').onload = function (){
        infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
    }

    function setAdress(rs) {
        var addComp = rs.addressComponents;
        province = addComp.province;//获取省份
        city = addComp.city;//获取城市
        district = addComp.district;//区
        street = addComp.street;//街
        $("#dizhi").text(city + district + street);
        realAdress1 =city + district + street;
    }
    function setAdresses(rs) {
        var addComp = rs.addressComponents;
        province = addComp.province;//获取省份
        city = addComp.city;//获取城市
        district = addComp.district;//区
        street = addComp.street;//街
        realAdress1 =city + district + street;
    }

    //模拟点
    var datas
    if (datas != "" && datas != null) {
      var  gpsInfos1 = datas.data.gps.gpsInfos;
        //设置需要绘制的轨迹点
        handleRealRoute(gpsInfos1);
    }


    <%--function initWaybillRecord() {--%>
        <%--<c:if test="${not empty records && records.size() > 0}">--%>
            <%--<c:forEach items="${records}" var="item">--%>
                <%--var obj = {"waybillId": ${item.waybillId},"longitude": ${item.longitude},"latitude": ${item.latitude},"location": "${item.location}","content": "${item.content}"--%>
                    <%--,"type": ${item.type},"fromType": ${item.fromType},"sendTime": ${item.sendTime}};--%>
                <%--records.push(obj);--%>
            <%--</c:forEach>--%>
        <%--markRecord(records);--%>
        <%--</c:if>--%>
        <%--if (records.length == 0) {--%>
            <%--$("#mapListMainAllmap").hide();--%>
            <%--$("#sideIconMain").hide();--%>
        <%--} else {--%>
            <%--$("#mapListMainAllmap").show();--%>
            <%--$("#sideIconMain").show();--%>
        <%--}--%>
    <%--}--%>

    function markRecord(records){
        var abnormalData = [];
        var stopCarData = [];
        var startWaybill;
        var completeGoods;
        var startCorral;
        var outCorral;
        var waybillComplete;
        mapMessageList(records);
        var gpsType = $("#gpsType").val();
        if(gpsType == 1){
        // 1:运单生成 2:上传装货凭证 3:电子围栏离装货地 4:电子围栏进入卸货地  5:运单完成（上传收货凭证/出卸货电子围栏） 6:异常停车  7:其他异常预警
        for (var i = 0; i < records.length; i++) {
            var log = records[i]

            var type = log.type;
            if (type == 1) {
                startWaybill = log
            } else if (type == 2) {
                completeGoods = log;
            } else if (type == 3) {
                startCorral = log;
            } else if (type == 4) {
                outCorral = log
            } else if (type == 5) {
                waybillComplete = log
            } else if (type == 6) {
                if (!log.location || log.location == 'null') {
                    continue
                }
                stopCarData.push(log)
            } else {
                if (!log.location || log.location == 'null') {
                    continue
                }
                abnormalData.push(log)
            }
        }
//        addStartMarker(startWaybill)
        addAbnormalMarker(abnormalData)
        addStopCar(stopCarData)
        addStartCorralMarker(startCorral)
        addOutCorralMarker(outCorral)
        addCompleteGoodsMarker(completeGoods)
//        addWaybillCompleteMarker(waybillComplete)
        }

    }

    function addAbnormalMarker(abnormalData){
        if (!abnormalData) {
            return
        }
        var stopIcon = "newassets/imgs/icon_map_abnormal.png";
        var sContent = $("#carInfo").html();
        var gc = new BMap.Geocoder();
        for (var i=0;i<abnormalData.length;i++){
            var item=abnormalData[i];
            var point = new BMap.Point(item.longitude, item.latitude);
            var abnormalMarker = new BMap.Marker(point, {
                icon: new BMap.Icon(stopIcon, new BMap.Size(26, 34)),
                offset: new BMap.Size(-2, -4),
                imageOffset: new BMap.Size(0, 0),
                imageSize: new BMap.Size(48, 56)
            });
            abnormalMarker.setZIndex(105);
            map.addOverlay(abnormalMarker);
            var infoWindow = new BMap.InfoWindow(sContent, {width: 310, height: 0});
            var content = item.content
            if (item.type == 7) {
                content = '车辆设备离线，时长' + item.content
            }
            showCarInfo(abnormalMarker, abnormalData[i].location, P.long2Datetime(abnormalData[i].sendTime), infoWindow,gc,content,1, item);
        }
    }

    function addStopCar(stopCarData){
        if (!stopCarData) {
            return
        }
        var stopCarIcon = "newassets/imgs/icon_map_stop.png";
        var sContent = $("#carInfo").html();
        var gc = new BMap.Geocoder();
        for (var i=0;i<stopCarData.length;i++){
            var item=stopCarData[i];

            var point = new BMap.Point(item.longitude, item.latitude);
            var stopCarIconMarker = new BMap.Marker(point, {
                icon: new BMap.Icon(stopCarIcon, new BMap.Size(26, 34)),
                offset: new BMap.Size(-2, 1),
                imageOffset: new BMap.Size(0, 0),
                imageSize: new BMap.Size(48, 56)
            });
            map.addOverlay(stopCarIconMarker);
            stopCarIconMarker.setZIndex(105);
            var infoWindow = new BMap.InfoWindow(sContent, {width: 310, height: 0});
            var content = '车辆异常停车，时长' + stopCarData[i].content
            showCarInfo(stopCarIconMarker, stopCarData[i].location, P.long2Datetime(stopCarData[i].sendTime), infoWindow,gc,content,1, item);
        }
    }
    /**
    * 添加运单已生成 生成图标
    * */
//    var startWaybill = {
//        startWaybill:startRealRoutePoints,
//        realTimes:realTimes1,
//        longitude:realAdress1
//    }

//    addStartMarker(startWaybill)
    function addStartMarker(startWaybill,pointsArr){
        if (!startWaybill) {
            return
        }
        var startWaybillIcon = "newassets/imgs/icon_map_start1.png";
        var sContent = $("#carInfo").html();
        var gc = new BMap.Geocoder();
        var point = new BMap.Point(pointsArr.lng, pointsArr.lat);
        var startWaybillIconMarker = new BMap.Marker(point, {icon: new BMap.Icon(startWaybillIcon, new BMap.Size(29, 38)), offset: new BMap.Size(-2, 1), imageOffset: new BMap.Size(0, 0), imageSize: new BMap.Size(48, 56)});
        map.addOverlay(startWaybillIconMarker);
        startWaybillIconMarker.setZIndex(105);
        var infoWindow = new BMap.InfoWindow(sContent, {width: 310, height: 0});
        showCarInfo(startWaybillIconMarker, startWaybill.location, P.long2Datetime(startWaybill.sendTime), infoWindow,gc,1, startWaybill);
    }
    /**
     * 添加装货地 生成图标
     * */
    function addCompleteGoodsMarker(completeGoods){
        if (!completeGoods) {
            return
        }
        if(completeGoods.longitude<1 && completeGoods.latitude<1){
            return
        }
        var completeGoodsIcon = "newassets/imgs/icon_map_goods.png";
        var sContent = $("#carInfo").html();
        var gc = new BMap.Geocoder();
        var point = new BMap.Point(completeGoods.longitude, completeGoods.latitude);
        var completeGoodsIconMarker = new BMap.Marker(point, {icon: new BMap.Icon(completeGoodsIcon, new BMap.Size(29, 38)), offset: new BMap.Size(-2, 1), imageOffset: new BMap.Size(0, 0), imageSize: new BMap.Size(48, 56)});
        map.addOverlay(completeGoodsIconMarker);
        completeGoodsIconMarker.setZIndex(105);
        var infoWindow = new BMap.InfoWindow(sContent, {width: 310, height: 0});
        showCarInfo(completeGoodsIconMarker, completeGoods.location, P.long2Datetime(completeGoods.sendTime), infoWindow,gc,completeGoods.content,1, completeGoods);
    }
    /**
     * 出入围栏点 生成图标
     * */
    function addStartCorralMarker(startCorral){
        if (!startCorral) {
            return
        }
        var startCorralIcon = "newassets/imgs/icon_corral.png";
        var sContent = $("#carInfo").html();
        var gc = new BMap.Geocoder();
        var point = new BMap.Point(startCorral.longitude, startCorral.latitude);
        var startCorralIconMarker = new BMap.Marker(point, {icon: new BMap.Icon(startCorralIcon, new BMap.Size(20, 22)), offset: new BMap.Size(-2, 1), imageOffset: new BMap.Size(0, 0), imageSize: new BMap.Size(48, 56)});
        map.addOverlay(startCorralIconMarker);
        startCorralIconMarker.setZIndex(105);
        var infoWindow = new BMap.InfoWindow(sContent, {width: 310, height: 0});
        showCarInfo(startCorralIconMarker, startCorral.location, P.long2Datetime(startCorral.sendTime), infoWindow,gc,startCorral.content,1, startCorral);
    }
    /**
     * 进入围栏 生成图标
     * */
    function addOutCorralMarker(outCorral){
        if (!outCorral) {
            return
        }
        var outCorralIcon = "newassets/imgs/icon_corral.png";
        var sContent = $("#carInfo").html();
        var gc = new BMap.Geocoder();
        var point = new BMap.Point(outCorral.longitude, outCorral.latitude);
        var outCorralIconMarker = new BMap.Marker(point, {icon: new BMap.Icon(outCorralIcon, new BMap.Size(20, 22)), offset: new BMap.Size(-2, 1), imageOffset: new BMap.Size(0, 0), imageSize: new BMap.Size(48, 56)});
        map.addOverlay(outCorralIconMarker);
        outCorralIconMarker.setZIndex(105);
        var infoWindow = new BMap.InfoWindow(sContent, {width: 310, height: 0});
        showCarInfo(outCorralIconMarker, outCorral.location, P.long2Datetime(outCorral.sendTime), infoWindow,gc,outCorral.content,1, outCorral);
    }
    /**
     * 运单已完成 生成图标
     * */
    function addWaybillCompleteMarker(waybillComplete,points){
        if (!waybillComplete) {
            return
        }
        var waybillCompleteIcon = "newassets/imgs/icon_map_end1.png";
        var sContent = $("#carInfo").html();
        var gc = new BMap.Geocoder();

        var point = new BMap.Point(points.lng, points.lat);
        var waybillCompleteIconMarker = new BMap.Marker(point, {icon: new BMap.Icon(waybillCompleteIcon, new BMap.Size(29, 38)), offset: new BMap.Size(-2, 1), imageOffset: new BMap.Size(0, 0), imageSize: new BMap.Size(48, 56)});
        map.addOverlay(waybillCompleteIconMarker);
        waybillCompleteIconMarker.setZIndex(105);
        var infoWindow = new BMap.InfoWindow(sContent, {width: 310, height: 0});
        showCarInfo(waybillCompleteIconMarker, waybillComplete.location, P.long2Datetime(waybillComplete.sendTime), infoWindow,gc,1, waybillComplete);
    }
    /**
     * 点击展开列表
     * */
    var isFlag = true;
    function sideIconMain(obj){
          if(isFlag){
              isFlag = false;
              $("#mapListMainAllmap").animate({right:"-310px"});
              $(obj).animate({right:"0px"});
              $(obj).find("img").attr("src","newassets/imgs/icon_open1.png");
          }else{
              $("#mapListMainAllmap").animate({right:"15px"});
              $(obj).animate({right:"315px"});
              $(obj).find("img").attr("src","newassets/imgs/icon_ retract1.png");
              isFlag = true;
          }
    }

    /**
     * 渲染列表
     * */
    function mapMessageList(dataAlert){
     var html= "";
     $.each(dataAlert,function(i,item){
         /*
         *  校验如果没有街道地址的，不显示在右侧列表中。
         *  类型为1.运单生成,2.上传装货凭证,5.运单完成 这三个点必须绘制列表，但是不一定有街道地址。所以无需校验这三个点
         *  同时由于电子围栏的离开装货地3，进入卸货地4，并不需要在右侧列表显示街道地址（地图上会用经纬度去反解析街道地址）。所以只要有数据，就显示出来
         *  剩下需要校验是否有街道地址的类型有：6:异常停车 7:其他异常预警。
         * */
             if(item.type == 6 || item.type == 7){
                     if (!item.location || item.location == 'null') {
                     return;
                 }
             }
             html+= '<li>';
             html+= '<div style="float: left;width: 50px;margin-right: 15px;text-align: center;"><img src="newassets/imgs/'+convertWaybillImg(item.type)+'"></div>';
             html+= '<div class="mapTextMsg">';
             html+= '<p>';
             var content = item.content
             if (item.type == 6) {
                 content = '运单车辆在['+item.location+']处异常停车，时长' + item.content
             } else if (item.type == 7) {
                 content = '运单车辆在['+item.location+']处设备离线，时长' + item.content
             }
             html+= '<span>'+content+'</span>';
             html+= '</p>';
             html+= '<p>';
             html+= '<a>'+P.long2Datetime(item.sendTime)+'</a>';
             html+= '</p>';
             html+= '</div>';
             html+= '</li>';
        })
        $("#mapListMainList").html(html);
    }
    function convertWaybillImg(status){
        // 1:运单生成 2:上传装货凭证 3:电子围栏离装货地 4:电子围栏进入卸货地  5:运单完成（上传收货凭证/出卸货电子围栏） 6:异常停车  7:其他异常预警
        switch(status){
            case 1 : return "icon_map_new_start1.png";
            case 2 : return "icon_map_goods.png";
            case 3 : return "icon_corral.png";
            case 4 : return "icon_corral.png";
            case 5 : return "icon_map_new_end1.png";
            case 6 : return "icon_map_stop.png";
            case 7 : return "icon_map_abnormal.png";
            default: return "";
        }
    }
</script>
