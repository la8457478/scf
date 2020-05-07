package com.fkhwl.scf.utils;

import com.isharing.commons.date.DateTimeUtils;
import com.isharing.commons.encrypt.DigestUtils;
import com.isharing.commons.encrypt.des.TDESDigestUtils;
import com.isharing.commons.string.RandomUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ToolsHelper {


	public static final String TIME_FIRST = " 00:00:01";
	public static final String TIME_LAST = " 23:59:59";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_TIME_FRIST_TIME_FORMAT = "yyyy-MM-dd"+TIME_FIRST;
	public static final String DATE_TIME_LAST_TIME_FORMAT = "yyyy-MM-dd"+TIME_LAST;
	public static final String DATE_TIME_TIMES_FORMAT = "yyyyMMddHHmmss";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


	/**
	 * 是否为空字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str){
		return null == str || "".equals(str) ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}

	/**
	 * 返回替换正则最后的值
	 * @return String
	 */
	public static String replaceLast(String text, String regex, String replacement) {
		return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
	}

	/**
	 * 截取2级城市名
	 * @return String
	 */
	public static String subCityName2(String cityName){
		String[] newD = cityName.split("-");
		if(newD.length > 2){
			cityName = newD[0]+"-"+newD[1];
		}
		return cityName;
	}


	/**
	 * 获取系统当前时间
	 * @return Date
	 */
	public static Date getCurrentDate(){
		return getCurrentCalendar().getTime();
	}

	/**
	 * 返回本月的最前时间 yyyy-MM-dd 00:00:01
	 * @return String
	 */
	public static String getMonthFristTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentDate());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getDayFristTime(calendar.getTime());
	}

	/**
	 * 返回本月的自后时间 yyyy-MM-dd 23:59:59
	 * @return String
	 */
	public static String getMonthLatTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentDate());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return getDayLastTime(calendar.getTime());
	}

	/**
	 * 返回时间的开始值 00:00:01
	 * @return String
	 */
	public static String getDayFristTime(Date date){
		date = null == date ? getCurrentDate() : date;
		return DateTimeUtils.formatDateTime(date, DATE_TIME_FRIST_TIME_FORMAT);
	}

	/**
	 * 返回时间的最后值 23:59:59
	 * @return String
	 */
	public static String getDayLastTime(Date date){
		date = null == date ? getCurrentDate() : date;
		return DateTimeUtils.formatDateTime(date,DATE_TIME_LAST_TIME_FORMAT);
	}

	/**
	 * 格式化小时时间 yyyyMMddHHmmss
	 * @return Date
	 */
	public static Date formatQueryTime(String strDate){
		Date date = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_TIMES_FORMAT);
			date = format.parse(strDate);
		}catch(Exception e){}
		return date;
	}

	/**
	 * 当前月的第一天
	 * @return void
	 */
	public static String getMonthFristDay(Date date){
		date = null == date ? getCurrentDate() : date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return DateTimeUtils.formatDateTime(calendar.getTime(), DATE_FORMAT);
	}

	/**
	 * 当前月的最后一天
	 * @return void
	 */
	public static String getMonthLastDay(Date date){
		date = null == date ? getCurrentDate() : date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return DateTimeUtils.formatDateTime(calendar.getTime(), DATE_FORMAT);
	}

	/**
	 * 格式化 年月日(2015-01-01)
	 * @return Date
	 */
	public static Date formatStr2Date(String strDate){
		Date date = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			date = format.parse(strDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null == date ? new Date() : date;
	}


	/**
	 * 格式化 年月日(2015-01-01)
	 * @return Date
	 */
	public static Date formatStr2DateTime(String strDate){
		Date date = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
			date = format.parse(strDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null == date ? new Date() : date;
	}

	/**
	 * 格式化UTC格式时间："2019-08-24T14:32:08.549+08:00"
	 * @return Date
	 */
	public static Date formatStr2DateWithUTC(String strDate){
		Date date = null;
		try{
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			date = format.parse(strDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null == date ? new Date() : date;
	}

	/**
	 * 格式化 年月日(2015-01-01)
	 * @return String
	 */
	public static String formatDate2Str(Date date){
		date = null == date ? getCurrentDate() : date;
		return DateTimeUtils.formatDateTime(date, DATE_FORMAT);
	}

	/**
	 * 格式化 年月日日分秒(2015-01-01 12:12:12)
	 * @return
	 */
	public static String formatDate2StrLong(Date date){
		date = null == date ? getCurrentDate() : date;
		return DateTimeUtils.formatDateTime(date, DATE_TIME_FORMAT);
	}


	/**
	 * 获取当前年的第一天的第一秒
	 * @return String
	 */
	public static String getYearFirstDayTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return DateTimeUtils.formatDateTime(calendar.getTime(), "yyyy-MM-dd 00:00:01");
	}

	/**
	 * 获取当前年的最后一天最后一秒
	 * @return String
	 */
	public static String getYearLastDayTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		return DateTimeUtils.formatDateTime(calendar.getTime(), "yyyy-MM-dd 23:59:59");
	}

	/**
	 * 检查信息部货源是否过期
	 * @return boolean=true 已过期
	 */
	public static boolean checkWaybillLoadingTime(Date loadingTime){
		Calendar invalidDate = Calendar.getInstance();
		invalidDate.setTime(loadingTime);

		Calendar nowCalendar = Calendar.getInstance();
		if(invalidDate.before(nowCalendar)){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}

	/**
	 * 获取系统当前时间
	 * @return Calendar
	 */
	public static Calendar getCurrentCalendar(){
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZoneSH = TimeZone.getTimeZone("Asia/Shanghai");
		calendar.setTimeZone(timeZoneSH);

		return calendar;
	}

	/**
	 * 获取几个月前的时间
	 * @param d
	 * @param month
	 * @return
	 */
	public static Date getMonthBefore(Date d, int month){
		Calendar now =Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.MONTH,now.get(Calendar.MONTH)-month);
		return now.getTime();
	}

    /**
     * 得到几年前的时间
     * @param d
     * @param year
     * @return
     */
    public static Date getYearBefore(Date d, int year){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.YEAR,now.get(Calendar.YEAR)-year);
        return now.getTime();
    }


	/**
	 *  获取两个日期相差的月数
	 * @param d1    较大的日期
	 * @param d2    较小的日期
	 * @return  如果d1>d2返回 月数差 否则返回0
	 */
	public static int getMonthDiff(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
		int yearInterval = year1 - year2;
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
		if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;
		// 获取月数差值
		int monthInterval =  (month1 + 12) - month2  ;
		if(day1 < day2) monthInterval --;
		monthInterval %= 12;
		return yearInterval * 12 + monthInterval;
	}

	/**
	 * date2比date1多的天数
	 * @param date1：开始日期
	 * @param date2：结束日期
	 * @return
	 */
	public static int getDayDiff(Date date1,Date date2)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 != year2)   //同一年
		{
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++)
			{
				if(i%4==0 && i%100!=0 || i%400==0)    //闰年
				{
					timeDistance += 366;
				}
				else    //不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2-day1) ;
		}
		else    //不同年
		{
			return day2-day1;
		}
	}


	/**
	 * 在给定的日期加上或减去指定月份后的日期
	 *
	 * @param sourceDate 原始时间
	 * @param month      要调整的月份，向前为负数，向后为正数
	 * @return
	 */
	public static Date stepMonth(Date sourceDate, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.MONTH, month);

		return c.getTime();
	}


	/**
	 * 在给定的日期加上或减去指定天数后的日期
	 *
	 * @param sourceDate 原始时间
	 * @param day      要调整的天数，向前为负数，向后为正数
	 * @return
	 */
	public static Date stepDate(Date sourceDate, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.DATE, day);

		return c.getTime();
	}

	/**
	 * 在给定的日期加上或减去指定秒数后的日期
	 *
	 * @param sourceDate 原始时间
	 * @param seconds    要调整的秒数，向前为负数，向后为正数
	 * @return
	 */
	public static Date stepSecond(Date sourceDate, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.SECOND, seconds);

		return c.getTime();
	}


	/**
	 * 生成找回密码的验证码
	 * @return String
	 */
	public static String generateValidateCode(){
		return RandomUtils.generateRndNumberString(6);
	}


	/**
	 * 获取验证码过期时间
	 * @param minute 多少分钟后过期
	 * @return
	 */
	public static Date getValidateTimeout(int minute){
		Calendar calendar = getCurrentCalendar();
		calendar.add(Calendar.MINUTE, minute);

		return calendar.getTime();
	}

	/**
	 * 校验忘记密码验证码是否过期
	 * @return boolean
	 */
	public static boolean checkValidateTimeout(Date timeout){
		if(null != timeout && timeout.getTime() < getCurrentDate().getTime()){
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	/**
	 * 为注册用户随机生成的密码
	 * @return String
	 */
	public static String randomRegisterPassword(){
		return RandomUtils.generateRndNumberString(Const.RANDOM_PASSWORD_LENGTH);
	}

	/**
	 * 生成普通用户密码 password = md5(盐值password盐值)
	 * @return String
	 */
	public static String generatePassword(String password){
		return DigestUtils.encode(Const.USER_SALT+password+Const.USER_SALT, DigestUtils.ALGORITHM.MD5);
	}

	/**
	 * 生成普通用户密码 password = md5(盐值password盐值) * 2
	 * @return String
	 */
	public static String generatePassword2(String password){
		String onePass = generatePassword(password);
		return generatePassword(onePass);
	}

	/**
	 * 生成管理员密码 password = md5(盐值password盐值)
	 * @return String
	 */
	public static String generateAdminPwd(String password){
		return DigestUtils.encode(Const.ADMIN_SALT+password+Const.ADMIN_SALT, DigestUtils.ALGORITHM.MD5);
	}

	/**
	 * 生成管理员密码 password = md5(盐值password盐值) * 2
	 * @return String
	 */
	public static String generateAdminPwd2(String password){
		String onePass = generateAdminPwd(password);
		return generateAdminPwd(onePass);
	}

	/**
	 * 加密生成token,带当前时间戳
	 * @return String
	 */
	public static String generateToken(long userId, int userType) throws Exception{
		return encryptRequestToken(userId, userType, System.currentTimeMillis());
	}

	/**
	 * 生成用户的APIkey
	 * @return String
	 */
	public static String generateApiKey(){
		return RandomUtils.generateMixString(32);
	}

	/**
	 * 生成用户的SecretTKey
	 * @return String
	 */
	public static String generateSecretKey(){
		return RandomUtils.generateMixString(32);
	}

	/**
	 * 加密生成token
	 * @return String
	 */
	public static String encryptRequestToken(long userId, int userType, long timeout) throws Exception{
		StringBuffer buffer = new StringBuffer();
		buffer.append("USER_").append(userId).append("_TYPE_").append(userType).append("_TIMEOUT_").append(timeout);
		return TDESDigestUtils.encrypt(Const.GENERATE_TOKEN_KEY, buffer.toString());
	}

	/**
	 * 解密请求token
	 * @return String
	 */
	public static String decryptRequestToken(String token) throws Exception{
		return TDESDigestUtils.decrypt(Const.GENERATE_TOKEN_KEY, token);
	}

	/**
	 * 解密请求的token，并格式化为：[用户ID，用户类型，用户登陆时间]
	 * @return String[]
	 */
	public static String[] decryptAndFormatToken(String token) throws Exception{
		token = decryptRequestToken(token);
		String[] formatArray = token.split("_");
		String[] newArray =  new String[3];
		if(formatArray.length == 6){
			newArray[0] = formatArray[1];
			newArray[1] = formatArray[3];
			newArray[2] = formatArray[5];
		}
		return newArray;
	}

	/**
	 * 截取地域名称，只取省一级
	 * @return String
	 */
	public static String subOneCityName(String cityName){
		if(null == cityName) return "";

		int indexOf = cityName.indexOf("-");
		if(indexOf != -1){
			cityName = cityName.substring(0, indexOf);
		}

		return cityName;
	}

	/**
	 * 截取地域名称，只取省市两级
	 * @return String
	 */
	public static String subTwoCityName(String cityName){
		if(null == cityName) return "";

		int lastIndex = cityName.lastIndexOf("-");
		if(cityName.indexOf("-") != lastIndex){
			cityName = cityName.substring(0, lastIndex);
		}

		return cityName;
	}

	/**
	 * 截取地域名称，减一级
	 * @return String
	 */
	public static String subCityName(String cityName){
		if(null == cityName) return "";

		int indexOf = cityName.indexOf("-");
		int lastIndex = cityName.lastIndexOf("-");
		if(indexOf != lastIndex){
			cityName = cityName.substring(0, lastIndex);
		}else if(indexOf != -1){
			cityName = cityName.substring(0, indexOf);
		}

		return cityName;
	}

	/**
	 * 将字符串替换替换成字符串List(去重复)
	 * @return List<String>
	 */
	public static List<String> removeReplaceString(String str, String charAt){
		List<String> strList = new ArrayList<String>();
		if(null != str && !"".equals(str)){
			String[] tempArray = str.split(charAt);
			if(0 != tempArray.length){
				for(String item : tempArray){
					if((null != item && !"".equals(item)) && !strList.contains(item)){
						strList.add(item);
					}
				}
			}
		}
		return strList;
	}

	/**
	 * 将字符串替换替换成字符串List(含重复)
	 * @return List<String>
	 */
	public static List<String> removeReplaceStringContainRepetition(String str, String charAt){
		List<String> strList = new ArrayList<>();
		if(null != str && !"".equals(str)){
			String[] tempArray = str.split(charAt);
			if(0 != tempArray.length){
				for(String item : tempArray){
					if((null != item && !"".equals(item))){
						strList.add(item);
					}
				}
			}
		}
		return strList;
	}

	/**
	 * 将字符串替换替换成Long List(去重复)
	 * @return List<long>
	 */
	public static List<Long> removeReplaceLong(String str, String charAt){
		List<Long> strList = new ArrayList<Long>();
		if(null != str && !"".equals(str)){
			String[] tempArray = str.split(charAt);
			if(0 != tempArray.length){
				for(String item : tempArray){
					if((null != item && !"".equals(item)) && !strList.contains(item)){
						try{
							strList.add(Long.valueOf(item));
						}catch(Exception e){}
					}
				}
			}
		}
		return strList;
	}

	public static String listToString(List<String> list, String append){
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0;i<list.size();i++){
			String str = list.get(i);
			stringBuffer.append(str);
			if(i+1 < list.size()){
				stringBuffer.append(append);
			}
		}
		return stringBuffer.toString();
	}

	public static void removeListValue(List<Long> objects, long removeValue){
		if(null == objects || 0 == objects.size()) return;
		Iterator<Long> iter = objects.iterator();
        while(iter.hasNext()){
            long item = iter.next();
            if(item == removeValue){
                iter.remove();
            }
        }
	}

	/**
	 * 处理用户数据
	 * @param valName 获取值的键名
	 * @param list 获取值的数据集
	 * @return
	 */
	public static HashMap<String, Object> executeUserData(String valName, List<HashMap<String, Object>> list){
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(null != list && list.size() > 0){
			for(HashMap<String, Object> row : list){
				result.put(row.get("userId").toString(), row.get(valName));
			}
		}
		return result;
	}

	/**
	 * 把object的值赋值到map
	 * @param src
	 * @param dest
	 */
	public static void copyToMap(Object src, Map<String, Object> dest, String... ignores) {
		if(src == null) {
			return;
		}
		Set<String> ignoreSet = new HashSet<>();
		if(ignores != null && ignores.length > 0) {
			for (String ignore : ignores) {
				ignoreSet.add(ignore);
			}
		}
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(src.getClass());
		for (PropertyDescriptor pd : pds) {
			String key = pd.getName();
			if(ignoreSet.contains(key)) {
				continue;
			}
			if(pd.getReadMethod() != null){
				Object value = ReflectionUtils.invokeMethod(pd.getReadMethod(), src);
				dest.put(key, value);
			}
		}
	}

	/**
	 * 格式化 年月日(从0开始到23，分别顺序代表一天中的每个小时区域)
	 * @return String
	 */
	public static Integer formatDate2TimeRangeNo(Date date){
		date = null == date ? getCurrentDate() : date;
		String timeString = DateTimeUtils.formatDateTime(date, "HH");
		return Integer.valueOf(timeString);
	}


    /**
     * 修正数据为默认值
     * @param str
     * @return
     */
    public static String fixData(String str){
        return str!=null?str:"";
    }

    /**
     * 修正数据为默认值
     * @param str
     * @return
     */
    public static BigDecimal fixData(BigDecimal str){
        return str!=null?str:BigDecimal.ZERO;
    }
    /**
     * 修正数据为默认值
     * @param object
     * @return
     */
//    public static Date fixData(Date object){
//        return object!=null?object:getHistoryFirstDateTime();
//    }
    /**
     * 修正数据为默认值
     * @param object
     * @return
     */
    public static Integer fixData(Integer object){
        return object!=null?object:0;
    }
    /**
     * 修正数据为默认值
     * @param object
     * @return
     */
    public static Long fixData(Long object){
        return object!=null?object:0L;
    }
//    public static Object fixData(Object object){
//        if(object!=null){
//            return object;
//        }else if(object instanceof Integer){
//            return 0;
//        }else if(object instanceof Long){
//            return 0L;
//        }else if(object instanceof Double){
//            return 0D ;
//        }else if(object instanceof BigDecimal){
//            return BigDecimal.ZERO;
//        }else if(object instanceof String){
//            return "";
//        }else{
//            return object;
//        }
//    }
	public static void main(String[] args) {
		System.err.println(ToolsHelper.formatStr2DateWithUTC("2019-08-24T14:32:08.549+08:00"));
	}

    /**
     * 把日期增加或减少.正数往后推,负数往前移动
     * @param date     the date
     * @param dayCount the day count
     * @return Integer date
     */
    public static Date dateAddDay(Date date, int dayCount) {
        Date result = null;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, dayCount);//把日期往后增加一天.整数往后推,负数往前移动
            result = calendar.getTime();   //这个时间就是日期往后推一天的结果
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 把日期增加或减少.正数往后推,负数往前移动
     * @param date     the date
     * @param monthCount the day count
     * @return Integer date
     */
    public static Date dateAddMonth(Date date, int monthCount) {
        Date result = null;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, monthCount);//把日期往后增加一天.整数往后推,负数往前移动
            result = calendar.getTime();   //这个时间就是日期往后推一天的结果
        } catch (Exception e) {
        }
        return result;
    }
}
