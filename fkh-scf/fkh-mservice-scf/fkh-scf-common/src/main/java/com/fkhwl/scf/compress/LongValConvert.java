package com.fkhwl.scf.compress;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: com.fkhwl.domain.rest.resp</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: liaohong
 * emali: liaohong@fkhwl.com
 * version: 1.0
 * date: 2018-9-14 11:00
 * updatetime:
 * reason:
 */
public class LongValConvert {

    private static final int ENCODE_LENGTH = 11;

    /**
     * 支持最大位数
     */
    private static final int LONG_MAX_LENGTH = 18;

    /**
     * 最大支持的值
     */
    private static final long MAX_LONG_SUPPORT = 999999999;

    /**
     * 初始化 62 进制数据，索引位置代表字符的数值，比如 A代表10，z代表61等
     */
    private static String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static int scale = chars.length();

    public static String convertIntToString9(long val){
        if(val > MAX_LONG_SUPPORT){
            return String.valueOf(val);
        }
        return String.format("%09d", val);
    }

    /**
     * 将10进制的数字转换为62进制的字符串
     * @param num
     * @return
     */
    public static String convert10To62(long num){
        StringBuilder sb = new StringBuilder();
        while (num > scale - 1) {
            // 对scale进行求余，然后将余数追加至 sb 中，由于是从末位开始追加的，因此最后需要反转（reverse）字符串
            int remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));
            num = num / scale;
        }
        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        // 暂不考虑左侧位补零
        //return StringUtils.leftPad(value, ENCODE_LENGTH, '0');
        return sb.reverse().toString();
    }

    /**
     * 将62进制的字符转换为10进制的数字
     * @param str
     * @return
     */
    public static long convert62To10(String str) {
        // 将 0 开头的字符串进行替换
        str = str.replaceAll("^0*", "");
        long num = 0;
        for (int i = 0; i < str.length(); i++) {
            // 查找字符的索引位置
            int index = chars.indexOf(str.charAt(i));
            // 索引位置代表字符的数值
            num += (long) (index * (Math.pow(scale, str.length() - i - 1)));
        }

        return num;
    }


    /**
     * 对运单车辆图片进行拼接
     * @param group
     * @param peId
     * @param waybillId
     * @param imageType
     * @return
     */
    public static String encodeWaybillPEImageName(int group, long peId, long waybillId, int imageType) {
        String wCode = convertIntToString9(waybillId);
        String baseCode = peId + wCode;
        StringBuilder sb = new StringBuilder(baseCode.length());
        sb.append(group);
        if(baseCode.length() <= LONG_MAX_LENGTH){
            // 小于最大18位范围内的值，直接计算
            sb.append(convert10To62(Long.parseLong(baseCode)));
        }else{
            // 大于long的最大值，需要二次计算(如：799999999+899999998)
            sb.append(new BigInteger(baseCode).toString(36));
        }
        return sb.append("_").append(imageType).toString();
    }

    /**
     * 对运单货物的车辆照片进行索引拼接压缩，拼接格式：
     * group(1) + peId(9) + waybillId(9)
     * @param from 图片来源：1=发货，2=收货
     * @param peId 运单货物Id
     * @param waybillId 运单Id
     * @return 最大长度12位
     */
    public static String encodeWaybillPECode(int from, long peId, long waybillId) {
        String wCode = convertIntToString9(waybillId);
        String baseCode = peId + wCode;
        StringBuilder sb = new StringBuilder(baseCode.length());
        sb.append(from);
        if(baseCode.length() <= LONG_MAX_LENGTH){
            // 小于最大18位范围内的值，直接计算
            sb.append(convert10To62(Long.parseLong(baseCode)));
        }else{
            // 大于long的最大值，需要二次计算(如：799999999+899999998)
            sb.append(new BigInteger(baseCode).toString(36));
        }
        return sb.toString();
    }

    /**
     * 对运单
     * @param code
     * @return
     */
    public static String decodeWaybillPECode(String code){
        String group = code.substring(0, 1);
        code = code.substring(1, code.length());
        String peId = "";
        String waybillId = "";
        if(code.length() > ENCODE_LENGTH){
            // 暂时不支持回转
            code = new BigInteger(code, 36).toString();
        }else{
            long val = convert62To10(code);
            code = String.valueOf(val);

            waybillId = code.substring(code.length()-9).replaceAll("^0*", "");
            peId = code.substring(0, code.length()-9);
        }

        return group + "," + peId + "," + waybillId;
    }

    public static void main(String[] args){
        /**
         * 初始格式: group(1) + peId(9) + waybillId(9)
         */
//        String val = encodeWaybillPECode(1, 1, 3955);
//        System.out.println("------------------code1:  "+ val + ", " + decodeWaybillPECode(val));
//        val = encodeWaybillPECode(2,999999999, 999999999);
//        System.out.println("------------------code2:  "+ val + ", " + decodeWaybillPECode(val));

        //System.out.println("------------------code3:  "+ encodeWaybillPECode(999999999999999999L, 999999999999999999L, 2));


        //System.out.println(decodeWaybillPECode("21Bs0emTbBk7"));
        //System.out.println(decodeWaybillPECode("21lq0c18ckzvyev7ttzd0msxr"));


//        List<?> list = new ArrayList<>();
//        list.add(null);
//        System.err.println(list.get(0));
//        System.err.println(list);
//
//        System.err.println("---------------------------------");
//
//        List<? extends Number> list1 = new ArrayList<>();
//        list1.add(null);
//        Number number = list1.get(0);
//        System.err.println(list1.get(0));
//        System.err.println(list1);
//
//        System.err.println("---------------------------------");
//
//        List<? super Number> list2 = new ArrayList<>();
//        list2.add(null);
//        list2.add(1);
//        list2.add(1.2);
//        Number object = (Number)list2.get(1);
//
//        System.err.println(object instanceof Number);
//        System.err.println(list2);
//
//        System.err.println("---------------------------------");
//
//        List<Number> list3 = new ArrayList<>();
//        list3.add(1);
//        list3.add(1.2);
//        list3.add(1L);
//        System.err.println(list3);
//
//        System.err.println("---------------------------------");
//
//        List<? super Integer> list4 = new ArrayList<>();
//        list4.add(1);
//        Object o = 1.2;
//        System.err.println(list4);

        Map<Long, Long> map = new HashMap<>();
        map.put(1L,12L);
        map.put(2L,13L);

        System.err.println(map.containsKey(1L));
        System.err.println(map.containsKey(12L));
        System.err.println(map.containsValue(1L));
        System.err.println(map.containsValue(12L));
    }
}
