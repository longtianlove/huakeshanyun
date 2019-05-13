package com.stys.ipfs.util;

import java.security.MessageDigest;

/**
 * Sha 加密
 * @author Administrator
 *
 */
public class SecuritySHA1Utils {
	/**
	 * @Comment SHA1实现
	 * @return
	 */
	public static String shaEncode(String inStr) throws Exception {
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = sha.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static void main(String args[]) throws Exception {
		String str = "7486045";
		System.out.println("原始：" + str);
		System.out.println("SHA后：" + shaEncode(str));
	}
}
