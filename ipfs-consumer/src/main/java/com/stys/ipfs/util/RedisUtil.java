package com.stys.ipfs.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private static RedisUtil _instance;

	public static RedisUtil Instance() {
		if (_instance == null) {
			_instance = SpringContextUtil.getBean(RedisUtil.class);
		}
		return _instance;
	}

	/**
	 * 缓存AccessToken
	 * 
	 * @param redisKey
	 * @param token
	 * @param expireTime
	 * @param timeUnit
	 */
	public void setCacheAccessToken(String redisKey, String token, long expireTime, TimeUnit timeUnit) {
		ValueOperations<String, String> ho = redisTemplate.opsForValue();
		// 存储用户资源权限到redis，3天失效
		ho.set(redisKey, token, expireTime, timeUnit);
	}

	/**
	 * 缓存中读取AccessToken
	 * 
	 * @param redisKey
	 * @return
	 */
	public String getCacheAccessToken(String redisKey) {
		ValueOperations<String, String> ho = redisTemplate.opsForValue();
		return ho.get(redisKey);
	}

}