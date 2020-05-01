package com.project.selflearningplatformserver.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;

/**
 * @author itning
 * @date 2020/5/1 14:02
 */
public class Md5Utils {
    /**
     * 字符串转MD5
     *
     * @param src  字符串
     * @param salt 盐
     * @return MD5
     */
    public static String string2Md5(String src, String salt) {
        return DigestUtils.md5Hex(src + salt);
    }

    /**
     * 字符串转MD5
     *
     * @param src 字符串
     * @return <code>Tuple&lt;MD5,SALT&gt;</code>
     */
    public static Tuple<String, String> string2Md5(String src) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[15];
        random.nextBytes(bytes);
        String salt = Base64.encodeBase64String(bytes);
        return new Tuple<>(string2Md5(src, salt), salt);
    }

    /**
     * 检查MD5是否匹配
     *
     * @param src     源字符串
     * @param srcSalt 源字符串盐
     * @param md5     要比较的值
     * @return 相同返回<code>true</code>
     */
    public static boolean checkEquals(String src, String srcSalt, String md5) {
        return string2Md5(src, srcSalt).equals(md5);
    }

    public static void main(String[] args) {
        System.out.println(string2Md5("admin", "a"));
    }
}
