/**
 *
 */
package com.fkhwl.scf.web.util;

/**
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 11:13
 * @Description:
 */
public class Errors {


	public static final String ERROR_KEY = "errorMsg";
	public static final String ERROR_MESSAGE = "message";
	public static final String OPTION_ERROR = "操作失败，请联系系统管理员";
	public static final String UPDATE_APPCONFIG_ERROR = "积分类型原因不要随意修改,请联系管理员";

	public static final String ERROR_VALIDATE_CODE = "验证码错误!";
	public static final String ERROR_USER_NAME_NOT_FOUND = "用户名不存在或错误!";
	public static final String SHIPPER_ALREADY_ACTIVATE = "货主已经被激活过!";
	public static final String ERROR_USER_NAME_ALREADY_EXIST = "用户名已存在!";
	public static final String ERROR_USER_PASSWORD = "用户登陆密码错误!";
	public static final String ERROR_LOGIN_ERROR = "登陆异常，请联系管理员!";

	public static final String ERROR_CAR_INFO_ALREADY_EXIST = "该车牌号已经存在!";
	public static final String ERROR_CAR_INFO_STATUS_ERROR = "车辆状态不正确!";
	public static final String ERROR_APPVERSION_ADD_ERROR = "创建版本信息失败!";
	public static final String ERROR_CARGO_ADD_ERROR = "发布货源信息失败!";

	public static final String ERROR_SHIPPER_ADD_ERROR = "创建货主信息失败!";
	public static final String ERROR_DRIVER_ADD_ERROR = "创建驾驶员信息失败!";
	public static final String ERROR_CONSIGNEE_ADD_ERROR = "创建收货人信息失败!";
	public static final String ERROR_FREIGHT_DEPT_ADD_ERROR = "创建信息部信息失败!";

	public static final String ERROR_EMPTY_CARINFO_ADD_ERROR = "发布空车信息失败!";
	public static final String ERROR_ROUTE_SUBSCRIPTION_ADD_ERROR = "创建订阅路线失败!";
	public static final String ERROR_SYS_MSG_ADD_ERROR = "发布系统消息失败!";
	public static final String ERROR_WAYBILL_CAR_INFO_ADD_ERROR = "运单添加驾驶员失败!";
	public static final String ERROR_WAYBILL_FREIGHT_DEPT_ADD_ERROR = "运单添加信息部失败!";

	public static final String ERROR_USER_AUDITOR_STATUS = "审核状态错误";

	public static final String ERROR_AD_RED_ENVELOPE_CAN_NOT_UPDATE_BEGIN = "活动即将开始或已开始，不能进行修改";
	public static final String ERROR_AD_RED_ENVELOPE_CAN_NOT_UPDATE_END = "活动已结束，不能进行修改";
	public static final String ERROR_AD_RED_ENVELOPE_NOT_FOUND = "未找到该活动";
	public static final String ERROR_AD_RED_ENVELOPE_CAN_NOT_DELETE_BEGIN = "活动已经开始，不能删除";
	public static final String ERROR_AD_RED_ENVELOPE_CAN_NOT_DELETE_END = "活动已结束，不能删除";


	public static final String ROLE_SAVE_ERROR = "保存角色信息失败!";

	public static final String PUSH_TO_APP_ERROR = "推送失败，请稍后重试![CODE=";
	public static final String PUSH_TO_NOT_FOUND_APP_ERROR = "推送失败，没有找到应用程序，请先创建应用程序!";

	public static final String MOBILE_NUMBER_ALREADY_REGISTER = "手机号码已存在!";

}
