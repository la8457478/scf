/**
 *
 */
package com.fkhwl.scf.validate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName: ParametersValidate
 * @Description: 验证API参数值
 * @author http://liaohong.me
 * @date 2014年9月29日 上午10:56:59
 */
public class BaseValidate {


	/**
	 * 检查对象是否为空 List.size() == 0
	 * @return boolean
	 */
	public static boolean errorObj(Object object){
		return null == object ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * 检查数组是否空或size==0
	 * @return boolean
	 */
	public static boolean errorList(List<?> list){
		return null == list || list.size() == 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * 检查集合是否空或size==0
	 * @return boolean
	 */
	public static boolean errorSet(Set<?> set){
		return null == set || set.size() == 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * 判断是否为null或者>=0
	 * @return boolean
	 */
	public static boolean errorInteger(Integer value){
		return null == value || 0 >= value;
	}

	/**
	 * 判断是否为null或者<=0
	 * @return boolean
	 */
	public static boolean errorFloat(Float value){
		return null == value || 0 >= value;
	}

	/**
	 * 判断是否为null或者<=0
	 * @return boolean
	 */
	public static boolean errorDouble(Double value){
		return null == value || 0 >= value;
	}


	/**
	 * 判断是否为null或者<=0
	 * @return boolean
	 */
	public static boolean errorDouble(BigDecimal value){
		return null == value || 0 >= value.doubleValue();
	}

	/**
	 * @param value 整型值
	 * @param minValue 最小值=-1 不验证
	 * @param maxValue 最大值=-1 不验证
	 * @return boolean
	 */
	public static boolean errorInteger(Integer value, int minValue, int maxValue){
		if(minValue == -1 && maxValue == -1)
			return null == value ? Boolean.TRUE : Boolean.FALSE;
		if(minValue == -1 && maxValue != -1)
			return null == value ? Boolean.TRUE : (value > maxValue ? Boolean.TRUE : Boolean.FALSE);
		if(minValue != -1 && maxValue == -1)
			return null == value ? Boolean.TRUE : (value > minValue ? Boolean.FALSE : Boolean.TRUE);
		if(minValue == 0 && null != value && value == -1){
			return Boolean.FALSE;
		}
		return null == value ? Boolean.TRUE : (minValue <= value && value <= maxValue ? Boolean.FALSE : Boolean.TRUE);
	}

	/**
	 * 验证 long 型字段
	 * @param value
	 * @return boolean
	 */
	public static boolean errorLong(Long value){
		return null == value || 0L >= value ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * 检查字符串是否为null 或 ""
	 * @return boolean
	 */
	public static boolean errorString(String str){
		return errorString(str, Boolean.FALSE, -1, -1);
	}

	/**
	 * 检查是否超过最大长度
	 * @return boolean
	 */
	public static boolean errorStrMax(String str, int maxLength){
		return errorString(str, Boolean.TRUE, 0, maxLength);
	}

	/**
	 * 验证 varchar/text 型字段
	 * @param str 字符串参数
	 * @param minLength 长度
	 * @return boolean
	 */
	public static boolean errorStrMin(String str, int minLength){
		return errorString(str, Boolean.FALSE, minLength, -1);
	}

	/**
	 * 验证 varchar/text 型字段
	 * @param str 字符串参数
	 * @param minLength 最小长度=-1 不验证
	 * @param maxLength 最大长度=-1 不验证
	 * @return boolean
	 */
	public static boolean errorString(String str, int minLength, int maxLength){
		return errorString(str, Boolean.FALSE, minLength, maxLength);
	}

	/**
	 * 验证字符串
	 * @param str 字符串参数
	 * @param allowEmpty 是否允许为空字符串
	 * @param minLength 最小长度
	 * @param maxLength 最大长度
	 * @return boolean
	 */
	public static boolean errorString(String str, boolean allowEmpty, int minLength, int maxLength){
		if(allowEmpty){
			if(null == str || "".equals(str))
				return false;
		}else{
			if(-1 == minLength && (null == str || "".equals(str))){
				return true;
			}
			if(null == str){
				return true;
			}
		}
		if(-1 != maxLength && maxLength < minLength){
			return true;
		}
		boolean error = false;
		int length = str.length();
		if(-1 != minLength && -1 != maxLength){
            error = minLength > length || length > maxLength;
		}else if(-1 != minLength && -1 == maxLength){
            return minLength > length;
		}else if(-1 == minLength && -1 != maxLength){
            return maxLength < length;
		}else{
			error = false;
		}

		return error;
	}

	/**
	 * 验证中国大陆手机号
	 * @return boolean
	 */
	public static boolean errorMobileNo(String mobileNo){
		String regex = "1[3|4|5|6|7|8|9][0-9]{9}";
		if(null == mobileNo || "".equals(mobileNo) || mobileNo.length() != 11) {
			return true;
		}
		return !mobileNo.matches(regex);
	}

	/**
	 * 验证中国大陆车牌号
	 * @return boolean
	 */
	public static boolean errorLicensePlateNo(String licensePlateNo){
		String regex = "^[\u4e00-\u9fa5]{1}[A-Z]{1}-[A-Z_0-9]{5,6}$";
		if(null == licensePlateNo || "".equals(licensePlateNo)) {
			return true;
		}
		return !licensePlateNo.matches(regex);
	}
	public static boolean errorLicensePlateNoWithNoSymbol(String licensePlateNo){
		String regex = "^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5,6}$";
		if(null == licensePlateNo || "".equals(licensePlateNo)) return true;
		return !licensePlateNo.matches(regex);
	}

	/**
	 * 验证中国大陆手机及座机号
	 * 7~-位座机号
	 * @return boolean
	 */
	public static boolean errorPhoneNo(String phoneNo){
		String regex = "^(1[3|4|5|6|7|8|9][0-9]{9})$|^((\\d{7,8})|[0]{1}(\\d{2,3})-(\\d{7,8})|[0]{1}(\\d{9,11}))$";
		if(null == phoneNo || "".equals(phoneNo)) {
			return true;
		}
		return !phoneNo.matches(regex);
	}

	public static boolean errorChinaseName(String str){
		if(null == str || "".equals(str)){
			return true;
		}
		return !str.matches("^[\\u4e00-\\u9fa5]{2,10}");
	}

	public static boolean errorChianse(String str){
		if(null == str || "".equals(str)) {
			return true;
		}
		return !str.matches("^[a-zA-Z0-9\\u4e00-\\u9fa5]+$");
	}

	public static boolean errorCharOrNumber(String str){
		if(null == str || "".equals(str)){
			return true;
		}
		return !str.matches("^[a-zA-Z0-9-]+$");
	}

	public static boolean errorWaybillNo(String str){
		if(!errorCharOrNumber(str)){
			return errorString(str, 5, 20);
		}
		return false;
	}

	/**
	 * 验证是否为数字支持小数
	 * @return boolean
	 */
	public static boolean errorNumber(String str){
		if(!errorString(str)){
			String regx = "[1-9][0-9]+.[0-9]{0,}";
			return !str.matches(regx);
		}
		return Boolean.TRUE;
	}

	/**
	 * 验证密码，强密码（数字+字母+特殊字符）
	 * @param str
	 * @return
	 */
	public static boolean errorPassword(String str){
		String regx="^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~()/,.{};:'?<>!@#$%^&*_])[\\da-zA-Z~()/,.{};:'?<>!@#$%^&*_]+$";
		return !str.matches(regx);
	}


	/**
	 * Is positive integer boolean.
	 * 判断是否为正整数
	 * @param orginal the orginal
	 * @return the boolean
	 */
	public static boolean isPositiveInteger(String orginal) {
		return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
	}

	/**
	 * Is negative integer boolean.
	 * 判断是否为负整数
	 * @param orginal the orginal
	 * @return the boolean
	 */
	public static boolean isNegativeInteger(String orginal) {
		return isMatch("^-[1-9]\\d*", orginal);
	}

	/**
	 * Is whole number boolean.
	 * 判断是否为一个合法的整数
	 * @param orginal the orginal
	 * @return the boolean
	 */
	public static boolean isWholeNumber(String orginal) {
		return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);
	}

	/**
	 * Is positive decimal boolean.
	 * 判断是否为一个正小数
	 * @param orginal the orginal
	 * @return the boolean
	 */
	public static boolean isPositiveDecimal(String orginal) {
//		return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);//0.06  01.901  30. 0. 异常
		return isMatch("\\+{0,1}\\d*\\.\\d{1,}", orginal);//01.901 也是正确
	}

	/**
	 * Is negative decimal boolean.
	 * 判断是否为负小数
	 * @param orginal the orginal
	 * @return the boolean
	 */
	public static boolean isNegativeDecimal(String orginal) {
		return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);
	}

	/**
	 * Is decimal boolean.
	 * 判断是否为合法的小数
	 * @param orginal the orginal
	 * @return the boolean
	 */
	public static boolean isDecimal(String orginal) {
		return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
	}

	/**
	 * Is real number boolean.
	 * 判断是否为一个合法的数
	 * @param orginal the orginal
	 * @return the boolean
	 */
	public static boolean isRealNumber(String orginal) {
		return isWholeNumber(orginal) || isDecimal(orginal);
	}

	/**
	 * Is real positive number boolean.
	 * 判断是否为一个正确的正数(包括整数和小数)
	 * @param orginal the orginal
	 * @return the boolean
	 */
	public static boolean isRealPositiveNumber(String orginal){
		return isPositiveInteger(orginal) || isPositiveDecimal(orginal);
	}

	private static boolean isMatch(String regex, String orginal) {
		if (orginal == null || orginal.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum   = pattern.matcher(orginal);
		return isNum.matches();
	}

	public static boolean isRealName(String orginalVal){
		if(orginalVal == null || orginalVal.length() == 0){
			return false;
		}
		String regex = "^[\\u4e00-\\u9fa5]{2,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher isRealName = pattern.matcher(orginalVal);
		return isRealName.matches();
	}

	/**
	 * 验证整数位和小数位
	 * @param target 待验证
	 * @param num1 整数位
	 * @param num2 小数位
	 * @return
	 */
	public static boolean  isCargoNumber(String target,Integer num1,Integer num2){
		String regex="";
		if(num2!=0) {
			regex = "^\\d{1," + num1 + "}(\\.\\d{1," + num2 + "})?$";
		}else{
			regex="^\\d{1,"+num1+"}?$";
		}
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(target).matches();
	}


	/**
	 * 验证是否合法身份证号
	 * @param idCardNo
	 * @return
	 */
	//没有校验生日，使用com.fkhwl.fkhserver.commons.util.IdCardUtil
	@Deprecated
	public static boolean errorIdCardNo(String idCardNo) {
		if(idCardNo == null || idCardNo.length() == 0) {
			return true;
		}
		if(!ID_CARD_PATTERN.matcher(idCardNo).matches()) {
			return true;
		} else if(!CITY_CODE_MAP.containsKey(idCardNo.substring(0, 2))) {
			return true;
		} else {
			if(idCardNo.length() == 18) {
				int sum = 0;
				/*
				 * 如果18位身份证，验证最后一位校验和 校验算法∑(ai×Wi)(mod 11)
				 */
				for(int i = 0; i < idCardNo.length() - 1; i++) {
					int ai =  Integer.parseInt(idCardNo.substring(i, i+1));
					int wi = FACTOR[i];
					sum += ai * wi;
				}
				String last =PARITY[sum % 11];
				String idCardLast = idCardNo.substring(idCardNo.length() - 1).toUpperCase();

				if(!last.equals(idCardLast)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static boolean isMoreThanZero(String val){
		double num=isNotBlank(val)? Double.parseDouble(val):0D;
		return isRealPositiveNumber(val)|| num==0;
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static final Pattern ID_CARD_PATTERN = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");

	/**
	 * 身份证号地区代码地映射
	 */
	public static final Map<String, String> CITY_CODE_MAP = new HashMap<String, String>();
	public static final int[] FACTOR = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
	public static final String[] PARITY={"1","0","X","9","8","7","6","5","4","3","2"};
	static {
		CITY_CODE_MAP.put("11", "北京");
		CITY_CODE_MAP.put("12", "天津");
		CITY_CODE_MAP.put("13", "河北");
		CITY_CODE_MAP.put("14", "山西");
		CITY_CODE_MAP.put("15", "内蒙古");
		CITY_CODE_MAP.put("21", "辽宁");
		CITY_CODE_MAP.put("22", "吉林");
		CITY_CODE_MAP.put("23", "黑龙江");
		CITY_CODE_MAP.put("31", "上海");
		CITY_CODE_MAP.put("32", "江苏");
		CITY_CODE_MAP.put("33", "浙江");
		CITY_CODE_MAP.put("34", "安徽");
		CITY_CODE_MAP.put("35", "福建");
		CITY_CODE_MAP.put("36", "江西");
		CITY_CODE_MAP.put("37", "山东");
		CITY_CODE_MAP.put("41", "河南");
		CITY_CODE_MAP.put("42", "湖北");
		CITY_CODE_MAP.put("43", "湖南");
		CITY_CODE_MAP.put("44", "广东");
		CITY_CODE_MAP.put("45", "广西");
		CITY_CODE_MAP.put("46", "海南");
		CITY_CODE_MAP.put("50", "重庆");
		CITY_CODE_MAP.put("51", "四川");
		CITY_CODE_MAP.put("52", "贵州");
		CITY_CODE_MAP.put("53", "云南");
		CITY_CODE_MAP.put("54", "西藏 ");
		CITY_CODE_MAP.put("61", "陕西");
		CITY_CODE_MAP.put("62", "甘肃");
		CITY_CODE_MAP.put("63", "青海");
		CITY_CODE_MAP.put("64", "宁夏");
		CITY_CODE_MAP.put("65", "新疆");
		CITY_CODE_MAP.put("71", "台湾");
		CITY_CODE_MAP.put("81", "香港");
		CITY_CODE_MAP.put("82", "澳门");
		CITY_CODE_MAP.put("91", "国外");
	}

	/**
	 * 判断时间格式是否正确
	 * @param str
	 * @return
	 */
	public static boolean errorDate(String str, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try{
			Date date = formatter.parse(str);
			return str.equals(formatter.format(date));
		}catch(Exception e){
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println(errorPassword("@@@@@@@@@1@@@@q@"));
    }
}

