
package com.stys.ipfs.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimestampUtil {

	/**
	 * 验证时间戳是否合法
	 * 
	 * @param cacheKey
	 * @param currentTimestamp
	 * @return
	 */
	public static Boolean validateTimestamp(String cacheKey, long currentTimestamp) {
		long beforeTimeStamp = 0;

		String timestamp = RedisUtil.Instance().getCacheAccessToken(cacheKey);

		if (timestamp != null && timestamp != "") {
			beforeTimeStamp = Long.parseLong(timestamp);
		}
		// //当前时间戳小于或等于之前的时间戳。说明是重复的
		if (currentTimestamp < beforeTimeStamp && beforeTimeStamp > 0) {
			return false;
		}
		// session可能会超时
		long nowTimeStamp = getSecondTimestampTwo(new Date());
		if (nowTimeStamp - currentTimestamp > 3000000) {
			return false;
		}
		// redis存储的key,value,失效时间，时间单位

		RedisUtil.Instance().setCacheAccessToken(cacheKey, String.valueOf(currentTimestamp), 3, TimeUnit.DAYS);

		return true;

	}

	/**
	 * 精确到秒的时间戳
	 * 
	 * @param date
	 * @return
	 */
	private static long getSecondTimestampTwo(Date date) {
		if (null == date) {
			return 0;
		}
		String timestamp = String.valueOf(date.getTime() / 1000);
		return Long.parseLong(timestamp);
	}
}