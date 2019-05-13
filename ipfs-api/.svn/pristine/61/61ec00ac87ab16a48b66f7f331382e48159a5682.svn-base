package com.stys.ipfs.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String stringToMD5(String plainText) {
        String md5code = "";
		try {
			byte[] secretBytes = null;
			try {
			    secretBytes = MessageDigest.getInstance("md5").digest(
			            plainText.getBytes("UTF-8"));
			} catch (NoSuchAlgorithmException e) {
			    throw new RuntimeException("没有这个md5算法！");
			}
			md5code = new BigInteger(1, secretBytes).toString(16);
			for (int i = 0; i < 32 - md5code.length(); i++) {
			    md5code = "0" + md5code;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return md5code;
    }
    
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes("UTF-8"));
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16); 
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
    
    
 

}
