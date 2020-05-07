package com.fkhwl.scf.web.util;

/**
 * @program: fkh-scf-parent
 * @description: 生成十六进制标签号
 * @author: yuanxu
 * @create: 2019-06-01 18:52
 **/
public class TagNumUtil {
    /**
     * 将字符串生成16进制
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i =0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }
}
