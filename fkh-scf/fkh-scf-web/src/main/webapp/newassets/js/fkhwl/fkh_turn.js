/**
 * Created by liaohong on 2016/8/16.
 */

/*************权限菜单跳转*****************/
function PermissionsMenu(){
    containerShow("settings/permissions.html");
}
/*************货主公司logo跳转*****************/
function shipperCompanyLogoMgmt(){
    containerShow("settings/shipperCompanyLogoMgmt.html");
}
/*************找车管理跳转*****************/
function findVehicleMgmt(){
    containerShow("settings/findVehicleMgmt.html");
}
/***********角色管理跳转************/
function manageRole(){
    containerShow("settings/roleManage.html");
}
/*****设置操作日志跳转***********/
function operationLog(){
    containerShow("settings/operationLog.html");
}
/********设置车辆信息管理跳转***********/
function VehicleManage(){
    containerShow("settings/vehicleMgmt.html");
}
/*******终端信息管理跳转*******/
function terminalInformation(){
    containerShow("settings/terminalMgmt.html");
}
/*******数据字典管理跳转**********/
function dataDictionaryMgmt(){
    containerShow("settings/dataDictionary.html");
}
function carrierCompanyMgmt(){
    containerShow("settings/carrierCompanyList.html");
}
function systemSetting(){
	containerShow("settings/systemSetting.html");
}
//
function scheduleList(){
//	containerShow("settings/scheduleList.html");
	var text = $("#scheduleList").show();
	$("#mian-container").html(text);
}
function userSysSetting(){
	containerShow("settings/userSysSetting.html");
}
function alarmConfig(){
	containerShow("settings/alarmConfig.html")
}
/***********业户管理跳转************/
function ownerProperty(){
    containerShow("settings/ownerProperty.html");
}
function returnfn(){
    $("#SettingReturn").on("click",function(){
        containerShow("home/setting.html");
    });
}