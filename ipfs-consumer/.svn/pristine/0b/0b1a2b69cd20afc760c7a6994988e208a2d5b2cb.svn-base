package com.stys.ipfs.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomNumUtil {

	
	/**
	 *      根据num 的位数， 得到几位随机数
	 * @param num
	 * @return
	 */
	public static Integer  getRandom(int num) {
		return (int) ((Math.random() * 9 + 1) * Math.pow(10, num - 1));
	}
	
	/**
	 * 生产合同编号
	 * 
	 * @param maxCount
	 * @return
	 */
	public static String recountNew(int maxCount) {
		if (maxCount < 0) {
			return null;
		}
		// 20170731FXJT99999999
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(new Date());
		maxCount = maxCount + 1;
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(7);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(7);
		String temp=new BigDecimal(BigDecimalUtils.add(str, stringToAscii("HKSY"))).setScale(0, BigDecimal.ROUND_DOWN).toString();	
		String countStr = str + "HKSY" +temp.substring(4, temp.length())+ nf.format(maxCount);
		return countStr;
	}
	/**
	* 字符串转换为Ascii
	* @param value
	* @return
	*/
	public static String stringToAscii(String value) 
	{ 
	StringBuffer sbu = new StringBuffer(); 
	char[] chars = value.toCharArray(); 
	for (int i = 0; i < chars.length; i++) { 
	if(i != chars.length - 1) 
	{ 
	sbu.append((int)chars[i]); 
	} 
	else { 
	sbu.append((int)chars[i]); 
	} 
	} 
	return sbu.toString();
	}

	public static void main(String[] args) {
		System.out.println(recountNew(1));
	}
}
