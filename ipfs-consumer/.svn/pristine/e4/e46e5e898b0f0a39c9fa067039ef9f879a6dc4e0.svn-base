package com.stys.ipfs.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Test111 {

	public static void main(String[] args) {

		// 在拼接的数据后拼入API KEY
		String str = "data=123&timeStamp=1554349291840&key=123456789";
		// MD5加密
		String re = MD5Util.encrypt(str);
		System.out.println(re);

		long timepalte = Calendar.getInstance().getTimeInMillis();
		System.out.println(timepalte);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("ticketSecret", "123456789");

		map.put("sign", "36DD5E76EBE9618CCFB34DBB0701125D");

		map.put("timeStamp", "1554349291840");

		String secretKey = "123456789";
		CheckUtil check = new CheckUtil(secretKey);
		check.setValue("timeStamp", "1554349291840");
		boolean result = check.checkSign(String.valueOf(map.get("sign")));

		System.out.println(result + "--------------------");

	}

}
