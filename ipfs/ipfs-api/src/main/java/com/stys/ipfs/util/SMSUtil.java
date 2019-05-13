package com.stys.ipfs.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	 * 	//String msg = "【不倒翁】您的验证码为" + code + "，在5分钟内有效。";
	 * @param phone
	 * @param code
	 * @return
	 */
	public static boolean doGetStr(String phone, String msg,String... params) {
		if(null==msg) {
			msg="【IPFS华科闪云】您的验证码为%s，在5分钟内有效。"; 
		}
		String newmsg=msg.format(msg, params);
		String url = buildUrl(phone, newmsg);
		CloseableHttpClient defaultHttpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		return sendSMS(phone, msg, defaultHttpClient, httpGet);
	}
	public static boolean doBuySeccuss(String phone,String msg,String... params) {
		if(null==msg) {
			msg="【IPFS华科闪云】您购买的IPFS,于时到账。"; 
		}
		String newmsg=msg.format(msg, params);
		String url = buildUrl(phone, newmsg);
		CloseableHttpClient defaultHttpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		return sendSMS(phone, msg, defaultHttpClient, httpGet);
	}

	private static String buildUrl(String phone, String msg) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("http://api.smsbao.com/sms?u=xastys&p=77a1af8f27ef9cc3682e7cb17bdf08a8&m=");
		//stringBuilder.append("http://api.smsbao.com/sms?u=17391686332&p=197d3e7f1a64db39269a19b560bb1cb1&m=");
		stringBuilder.append(phone);
		stringBuilder.append("&c=");
		stringBuilder.append(msg);
		String url = stringBuilder.toString();
		return url;
	}

	private static boolean sendSMS(String phone, String msg, CloseableHttpClient defaultHttpClient, HttpGet httpGet) {
		try {
			HttpResponse response = defaultHttpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
			   logger.debug("SmsBao send " + msg + " to " + phone + " error code " + result);
			}
			return true;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		String phone = "15891077791";
//		String msg = "【IPFS星际黑蜂】您的验证码为%s，在5分钟内有效.";
//		int randomNum4 = (int) ((Math.random() * 9 + 1) * 100000);
//    	String romNum4=String.valueOf(randomNum4);
//     
//		doGetStr(phone, msg,romNum4);
		String msg = "【IPFS华科闪云】您的IPFS已订购成功,于时到账";
    	doBuySeccuss(phone,msg);
	}
}