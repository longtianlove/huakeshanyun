package com.stys.ipfs.tools.message;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author dp
 *
 */
public class SMSUtil {

	private static final String STATUS = "0";
	
	private static final Logger logger = LoggerFactory.getLogger(SMSUtil.class);
	/**
	 * 30 错误密码 40 账号不存在 41 余额不足 43 IP地址限制 50 内容含有敏感词 51 手机号码不正确
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static boolean doGetStr(String phone, String code) {
		String msg = "【Limit】您的验证码为" + code + "，在5分钟内有效。";
		String url = "http://api.smsbao.com/sms?u=xastys&p=77a1af8f27ef9cc3682e7cb17bdf08a8&m=" + phone + "&c=" + msg;

		CloseableHttpClient defaultHttpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = defaultHttpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				if (STATUS.equals(response)) { // 发送成功
					return true;
				} else {
					   logger.debug("SmsBao send " + msg + " to " + phone + " error code " + result);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		String phone = "15802912094";
		String msg = "456321";
		doGetStr(phone, msg);
	}
}