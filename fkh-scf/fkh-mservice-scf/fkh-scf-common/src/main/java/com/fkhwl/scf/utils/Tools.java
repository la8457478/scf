package com.fkhwl.scf.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2020年03月02日 17:34
 * @Description:
 */
public class Tools {
    /**
     * 切分运单的城市名称和地名
     * @param cityName the city name
     * @return [city, point]
     */
    public static String[] splitCityToPoint(String cityName){
        if(com.fkhwl.starter.core.util.Tools.isBlank(cityName)){
            return new String[]{"",""};
        }else{
            String[] array = cityName.split("#");
            if(array.length >= 2){
                return new String[]{array[0],array[1]};
            }else if(array.length == 1){
                return new String[]{array[0],""};
            }
        }

        return new String[]{"",""};
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

    /**
     * 解决数位过多，double显示为科学计数法问题
     * @param point
     * @param doubleVal
     * @return
     */
    public static String formatDoubleToString(int point, Double doubleVal){
        BigDecimal bigDecimal = BigDecimal.valueOf(null == doubleVal ? 0.0 : doubleVal);
        String data = bigDecimal.setScale(point, BigDecimal.ROUND_HALF_UP).toString();
        return data;
    }
    public static String digitCapital(double n) {
        String fraction[] = {"角", "分"};
        String digit[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String unit[][] = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};

        String head = n < 0 ? "负" : "";
        // 如果是负数取绝对值
        n = Math.abs(n);
        String s = "";
        BigDecimal bigDecimal = new BigDecimal(Double.valueOf(n).toString());
        String nStr = bigDecimal.toString();
        // 小数部分
        String[] split = nStr.split("\\.");
        if (split.length > 1) {
            // 小数点为特殊符号，在分割时需进行转义
            String decimalStr = split[1];
            if (decimalStr.length() > 2) {
                decimalStr = decimalStr.substring(0, 2);
            }
            // 将小数部分转换为整数
            Integer integer = Integer.valueOf(decimalStr);
            String p = "";
            for (int i = 0; i < decimalStr.length() && i < fraction.length; i++) {
                p = digit[integer % 10] + fraction[decimalStr.length() - i - 1] + p;
                integer = integer / 10;
            }
            s = p.replaceAll("(零.)+", "") + s;
        }
        if (s.length() < 1) {
            s = "整";
        }
        int integerPart = (int)Math.floor(n);
        // 整数部分
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

}
