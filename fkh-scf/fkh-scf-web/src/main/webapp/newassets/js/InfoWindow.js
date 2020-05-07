
InfoWindow = {};

//查看弹窗车辆信息
InfoWindow.viewVehicle = function(entityId){
    InfoWindow.open(globalConfig.webPath + "/vehicle/viewVehicle.html?vehicleId=" + entityId, 750, 520, "车辆信息");
}

//查看终端信息
InfoWindow.viewTerminal = function(entityId){
    InfoWindow.openChildWindow(globalConfig.webPath + "/vehicle/viewTerminal.html?EntityID=" + entityId, 700, 370, "终端信息");
}

//查看角色
InfoWindow.viewRole = 	function(EntityId){
    InfoWindow.open(globalConfig.webPath + "/system/viewRole.html?EntityID=" + EntityId, 590, 580);
}

//查看部门信息
InfoWindow.viewDep = 	function(EntityId){
    InfoWindow.open(globalConfig.webPath + "/vehicle/viewDep.html?EntityID=" + EntityId, 650, 400);
}

//用户信息
InfoWindow.viewUser = 	function(EntityId){
    InfoWindow.open(globalConfig.webPath + "/user/viewUser.html?EntityID=" + EntityId, 690, 520);
}

//编辑路线窗口
InfoWindow.viewRoute = 	function(EntityId){
    InfoWindow.open(globalConfig.webPath + "/map/viewRoute.html?EntityID=" + EntityId, 620, 370);
}

//查看线段拐点编辑窗口
InfoWindow.viewSegment = 	function(EntityId){
    InfoWindow.openChildWindow(globalConfig.webPath + "/map/saveLineSegment.html?input=true&EntityID=" + EntityId, 720, 420);
}

//查看线段分段限速点编辑窗口
InfoWindow.viewRouteSegment = 	function(EntityId){
    InfoWindow.openChildWindow(globalConfig.webPath + "/map/viewRouteSegment.html?input=true&EntityID=" + EntityId, 620, 220);
}

//编辑权限信息
InfoWindow.editFuncInfo = function(EntityId){
    InfoWindow.open(globalConfig.webPath + "/system/viewFunc.html?EntityID=" + EntityId, 630, 290,"权限信息");
}

//编辑驾驶员信息
InfoWindow.editDriverInfo = 	function(EntityId){
    InfoWindow.open(globalConfig.webPath + "/vehicle/editDriverInfo.html?EntityID=" + EntityId, 720, 520,"驾驶员信息");
}

//查看驾驶员信息
InfoWindow.viewDriverInfo = 	function(driverId){
    InfoWindow.open(globalConfig.webPath + "/vehicle/viewDriverInfo.html?driverId=" + driverId, 720, 520);
}

//查看报警信息
InfoWindow.viewAlarm = 	function(alarmId){
    InfoWindow.open(globalConfig.webPath + "/alarm/viewAlarm.html?alarmId=" + alarmId, 500, 380,"报警处理");
}

//查看围栏信息
InfoWindow.viewMapArea = function (EntityId){
    InfoWindow.open(globalConfig.webPath + "/data/viewArea.html?EntityID=" + EntityId, 720,370);
}

//查看未绑定车辆信息
InfoWindow.viewUnbindVehicle = function (EntityId){
    InfoWindow.open(globalConfig.webPath + "/data/getUnbindVehicle.html?areaId=" + EntityId, 850,390);
}

//查看业户信息
InfoWindow.viewMember = function (EntityId){
    InfoWindow.open(globalConfig.webPath + "/vehicle/viewMember.html?EntityID=" + EntityId, 700,390);
}

//查看基础数据编辑信息
InfoWindow.viewBasicData = function (EntityId){
    InfoWindow.open(globalConfig.webPath + "/system/viewBasicData.html?EntityID=" + EntityId, 500,260);
}
//查看信息点播菜单编辑信息
InfoWindow.viewInfoMenu = function (EntityId){
    InfoWindow.open(globalConfig.webPath + "/system/viewInfoMenu.html?EntityID=" + EntityId, 500,220);
}

//查看给终端发布的信息
InfoWindow.viewInformation = function (EntityId){
    InfoWindow.open(globalConfig.webPath + "/system/viewInformation.html?EntityID=" + EntityId, 500,220);
}


InfoWindow.newBasicData= function (parentCode){
    InfoWindow.open(globalConfig.webPath + "/system/viewBasicData.html?parent=" + parentCode, 500,260);
}
/**
 * 打开选择车辆的窗口
 */
InfoWindow.selectVehicle = function()
{
	var url = globalConfig.webPath+"/vehicle/selectVehicle!view.html";
	InfoWindow.openChildWindow(url, 765,350,"选择车辆");
}

InfoWindow.selectDep = function()
{
	var url = globalConfig.webPath+"/vehicle/selectDep!view.html";
	InfoWindow.openChildWindow(url, 565,350,"选择部门");
}

//历史轨迹
InfoWindow.showHistory = function(vehicleId,plateNo){
	window.parent.frames.historSelectPlateNo = plateNo;
	InfoWindow.openCommandWindow('HISTORY_ROUTE',vehicleId,'历史轨迹','icon-route');
}

//单独监控
InfoWindow.showSingleMonitor = function(vehicleId, plateNo){
	window.parent.frames.historSelectPlateNo = plateNo;
	InfoWindow.openCommandWindow('REAL_MONITOR',vehicleId,'单独监控['+plateNo+']','icon-greencar');
}

//车辆详情
InfoWindow.showVehicleDetail = function(vehicleId, plateNo){
	var commandPath = globalConfig.webPath;
	var url = commandPath + "/rightClick.html?pageName=viewVehicleInfo&";
	url += "input=true&vehicleId="+vehicleId;
	if(window.parent.frames.addTab){
        window.parent.frames.addTab("["+plateNo+"]车辆信息",url,"icon-greencar");
	}else{
        window.frames.addTab("["+plateNo+"]车辆信息",url,"icon-greencar");
	}
}

//车辆报警
InfoWindow.showVehicleWarning = function(vehicleId, plateNo){
	var commandPath = globalConfig.webPath;
	var url = commandPath + "/rightClick.html?pageName=viewVehicleWarning&";
	url += "input=true&vehicleId="+vehicleId;
	window.parent.frames.addTab("["+plateNo+"]报警信息",url,"icon-alarm");
}

//当点击命令按钮或菜单时，弹出命令发送窗口
InfoWindow.openCommandWindow = function(command, vehicleId, title, icon)
{
	var commandPath = globalConfig.webPath;
    if(command=="CALL_NOW")
	{
		 //点名
		var url = commandPath + "/rightClick.html?pageName=callTerminal&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 320, 115, title);
	}else if(command == "TERMINAL_MENU")
	{
		//信息点播菜单设置
		var url = commandPath + "/rightClick.html?pageName=viewTerminalMenu&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 400, title);
	}else if(command == "TERMINAL_CONFIG")
	{
		//终端参数配置和查询
		var url = commandPath + "/rightClick.html?pageName=terminalParamTemplate&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 720, 500, title);
	}else if(command == "TERMINAL_TEXT")
	{
		//发送调度信息
		var url = commandPath + "/rightClick.html?pageName=sendText&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 620, 300, title);
	}else if(command == "LISTEN_TERMINAL")
	{
		//发送监听命令
		var url = commandPath + "/rightClick.html?pageName=listenTerminal&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 480, 220, title);
	}else if(command == "TERMINAL_EVENT")
	{
		//终端事件设置
		var url = commandPath + "/rightClick.html?pageName=eventConfig&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 400, title);
	}else if(command == "OVER_SPEED_CONFIG")
	{
		//超速设置
		var url = commandPath + "/rightClick.html?pageName=overSpeedConfig&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 240, title);
	}else if(command == "TIRED_CONFIG")
	{
		//终端疲劳驾驶设置
		var url = commandPath + "/rightClick.html?pageName=tiredConfig&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 260, title);
	}else if(command == "SEND_INFORMATION")
	{
		//信息服务下发
		var url = commandPath + "/rightClick.html?pageName=sendInformation&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 350, title);
	}else if(command == "MEDIA_UPLOAD")
	{
		//多媒体文件提取
		var url = commandPath + "/rightClick.html?pageName=mediaUpload&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 400, title);
	}else if(command == "MEDIA_SEARCH")
	{
		//多媒体检索
		var url = commandPath + "/rightClick.html?pageName=mediaSearch&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 820, 500, title);
	}else if(command == "AUDIO_RECORDER")
	{
		//录音
		var url = commandPath + "/rightClick.html?pageName=audioRecorder&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 320, title);
	}else if(command == "PHONE_BOOK")
	{
		//设置电话本
		var url = commandPath + "/rightClick.html?pageName=phoneBook&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 820, 400, title);
	}else if(command == "TAKE_PICTURE")
	{
		//拍照
		var url = commandPath + "/rightClick.html?pageName=takePicture&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 600, 520, title);
	}else if(command == "SEND_QUESTION")
	{
		//提问下发
		var url = commandPath + "/rightClick.html?pageName=sendQuestion&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 400, title);
	}else if(command == "TEMP_TRACK")
	{
		//临时位置跟踪
		var url = commandPath + "/rightClick.html?pageName=tempTrack&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 250, title);
	}else if(command == "TERMINAL_AREA")
	{
		//围栏下发窗口
		var url = commandPath + "/rightClick.html?pageName=sendMapArea&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 820, 550, title);
	}else if(command == "TERMINAL_ROUTE")
	{
		//配置终端线路
		var url = commandPath + "/rightClick.html?pageName=sendRoute&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 820, 550, title);
	}
	else if(command == "VEHICLE_RECORDER")
	{
		//行驶记录仪下发窗口
		var url = commandPath + "/rightClick.html?pageName=vehicleRecorder&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 820, 580, title);
	}else if(command == "VEHICLE_RECORDER_2012")
	{
		//行驶记录仪下发窗口
		var url = commandPath + "/rightClick.html?pageName=vehicleRecorder.html?ver=2012";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 820, 580, title);
	}else if(command == "PLATFORM_COMMAND")
	{
		//809平台命令下发窗口
		var url = commandPath + "/rightClick.html?pageName=sendPlatformCmd&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 370, title);
	}else if(command == "WIRELESS_UPDATE")
	{
		//无线升级窗口
		var url = commandPath + "/rightClick.html?pageName=wirelessUpdate&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 570, title);
	}else if(command == "CONTROL_TERMINAL")
	{
		//终端控制窗口
		var url = commandPath + "/rightClick.html?pageName=controlTerminal&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 320, 190, title);
	}else if(command == "TRANSPARENT_SEND")
	{
		//透明传输命令下发窗口
		var url = commandPath + "/rightClick.html?pageName=transparentSend&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 520, 350, title);
	}else if(command == "DOOR_CONTROL")
	{
		//车门控制
		var url = commandPath + "/rightClick.html?pageName=doorControl&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 420, 180, title);
	}
	else if(command == "VEHICLE_INFO")
	{
        //车辆信息
		var plateNo = title.split("[")[1];
		plateNo = plateNo.substr(0, plateNo.length-1);
        InfoWindow.showVehicleDetail(vehicleId, plateNo);

		/** 使用新的车辆详情 delete  by liaohong on 2018-07-20
        var url = commandPath + "/rightClick.html?pageName=viewVehicleInfo&";
        url += "input=true&vehicleId="+vehicleId;
		//window.frames.addTab("车辆信息"+vehicleId,url);
		//InfoWindow.open(url, 720, 450, title);
		 */
	}
	else if(command == "HISTORY_ROUTE")
	{
        // var url = commandPath + "/rightClick.html?pageName=hisroute&";
        // url += "input=true&vehicleId="+vehicleId;
		title = '历史轨迹';
		 if(typeof(addTab) == "undefined"){
			 window.parent.frames.addTab(title,url,icon);
		 }else{
			 addTab(title,url,icon);
		 }
	}
	else if(command == "REAL_MONITOR")
	{
		 //单独监控
		 var mapPath = window.frames.globalConfig.mapPath;
		 var url = commandPath + "/"+ mapPath;
		 if(mapPath.indexOf("?") >= 0){
			 url += "&vehicleId=" + vehicleId;
		 }else{
			 url += "vehicleId=" + vehicleId;
		 }

		if(typeof(addTab) == "undefined"){
			 window.parent.frames.addTab(title,url,icon);
		 }else{
			 addTab(title,url,icon);
		 }
	}
	else if(command == "BIND_VEHICLE")
	{
		var url = commandPath + "/rightClick.html?pageName=getBindMapArea&";
        url += "input=true&vehicleId="+vehicleId;
		InfoWindow.open(url, 820, 550, title);
	}
}

/**
 * 在弹出窗口的基础上继续弹出子窗口
 */
InfoWindow.openChildWindow = function(url, width, height, title, option){
	/**
	 * if(window.parent && window.parent.openIFrameChildWindow) { if(!title)
	 * title = "编辑窗口"; window.parent.openIFrameChildWindow(url, width, height,
	 * title); return; }
	 */
	
    newoption = "width = "+width+",height="+height+",left="+(window.screen.width-width)/2+",scrollbars=yes,location=no,top="+(window.screen.height-height)/2 ;
	if (option!=null || option != "")
	{
		newoption += ","+option;                                                                     
	}               
	window.open(url, "", newoption);

}

InfoWindow.open = function(url, width, height, title, option){

	if(window.openIFrameWindow)
	{
		if(!title)
			title = "编辑窗口";
		window.openIFrameWindow(url, width, height,  title);
		return;
	}

    newoption = "width = "+width+",height="+height+",left="+(window.screen.width-width)/2+",scrollbars=yes,location=no,top="+(window.screen.height-height)/2 ;
	if (option!=null || option != "")
	{
		newoption += ","+option;                                                                     
	}               
	window.open(url, "", newoption);

}

InfoWindow.showCallDialog = function(obj,id,type){
    var url = "../proxy.htm?driverId="+id+"&type="+type;
    window.open(url,"tag",'width=360,height=500,top=200,left=500,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}

