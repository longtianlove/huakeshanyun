package com.stys.ipfs.util;

public class SignUtil {

	public static boolean validateMessage(VerificationCodeParam param, String secretKey) {
		CheckUtil check = new CheckUtil(secretKey);
		check.setValue("timeStamp", param.getTimeStamp());
		boolean result = check.checkSign(param.getSign());
		return result;
	}
}