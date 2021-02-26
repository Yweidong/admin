package com.haoyong.admin.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 工具类
 */
@Slf4j
public class MD5Util {

    /**
     * 生成MD5加密串
     */
    public static String getMD5(String message) {
        String md5 = "";
        try {
            //创建一个md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageByte = message.getBytes(StandardCharsets.UTF_8);
            //获得MD5字节数组,16*8=128位
            byte[] md5Byte = md.digest(messageByte);
            //转换为16进制字符串
            md5 = ByteUtil.bytesToHex(md5Byte);
        } catch (Exception e) {
            //输出到日志文件中
            log.error(ErrorUtil.errorInfoToString(e));
        }
        return md5;
    }


    public static String encrypt(String strSrc) {
        try {
            char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！！+" + e);
        }
    }


    /**
     * 验证方法
     * @param text 明文
     * @param md5 密文
     * @return 对比结果
     */
    private static boolean verify(String text, String md5){
        return md5.equals(getMD5(text));
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encrypt("111111"));
    }
}
