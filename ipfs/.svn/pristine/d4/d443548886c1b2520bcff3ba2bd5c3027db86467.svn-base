package com.stys.ipfs.util;

import java.util.Random;

/**
 * 
 * @author dp
 *
 */

public class RandomUtil {

	public static String generateRandomStr(int len) {
		// 字符源，可以根据需要删减
		String generateSource = "23456789abcdefghgklmnpqrstuvwxyz";// 去掉1和i ，0和o
		String rtnStr = "";
		for (int i = 0; i < len; i++) {
			// 循环随机获得当次字符，并移走选出的字符
			String nowStr = String
					.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "");
		}
		return rtnStr;
	}

	// 生成随机数
	public static String getNumber(int num) {
		Random rand = new Random();// 生成随机数
		String Nnumer = "";
		for (int a = 0; a < num; a++) {
			Nnumer += rand.nextInt(10);// 生成6位数字
		}
		return Nnumer;

	}

	/***
	 * 100000 - 999999
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// String str = generateRandomStr(13);
		// System.out.println(str);
		for (int i = 0; i < 10; i++) {
			String cardNumber = getNumber(6);
			System.out.println(cardNumber);

		}
	}

}
