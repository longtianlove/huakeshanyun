package com.stys.ipfs.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameUtil {

	public static void main(String[] args) {

			System.out.println("http://www.shandw.com/pc/auth/3thAuth/?"+loadGameParam("15802912094", "阿杜", "xxx", "1", "15802912094", "1247433539"));
		 

	}
	public static String loadGameParam(String openid,String nick,String avatar,String sex,String phone,String gid) {
		 
		StringBuffer  sb=new StringBuffer(); 
		sb.append("channel=11823&");
		sb.append("openid="+openid+"&");
		sb.append("time="+String.valueOf(System.currentTimeMillis()/1000)+"&");
		sb.append("nick="+nick+"&");
		sb.append("avatar="+avatar+"&");
		sb.append("sex="+sex+"&");
		sb.append("phone="+phone);
		String stringA= sb.toString();		 
		String StringB=stringA+"0182AE1C03219ABD6AE9D9BA114AD6C9";
		String resultStr=MD5Utils.stringToMD5(StringB).toLowerCase(); 
		System.out.println("加密======"+resultStr);
		sb.append("&sign="+resultStr);
		sb.append("&"+"gid="+gid); 
		sb.append("&"+"sdw_tt=1");
		sb.append("&"+"sdw_ld=1");
		sb.append("&"+"sdw_kf=1");
		sb.append("&"+"sdw_dl=1");
		sb.append("&"+"sdw_qd=1");
		
		System.out.println(sb.toString());
		 return sb.toString(); 
		 
	}

	public static String test() throws UnsupportedEncodingException { 
		
		Map<String, String> map = new HashMap<>();
 		map.put("channel", "11823");
		map.put("openid", "15802912094");
		map.put("time", String.valueOf(System.currentTimeMillis()/1000)); 
		map.put("nick", "阿杜");
		String avatar ="http://www.tumbler888.com/static/images/face.jpg";
		map.put("avatar", avatar);
		map.put("gid","1247433539");
		map.put("sex", "1");
		map.put("phone", "15802912094"); 
		
		StringBuffer  sb=new StringBuffer();
		sb.append("channel="+map.get("channel")+"&");
		sb.append("openid="+map.get("openid")+"&");
		sb.append("time="+map.get("time")+"&");
		sb.append("nick="+map.get("nick")+"&");
		sb.append("avatar="+map.get("avatar")+"&");
		sb.append("sex="+map.get("sex")+"&");
		sb.append("phone="+map.get("phone"));
		String stringA= sb.toString();		 
		String StringB=stringA+"0182AE1C03219ABD6AE9D9BA114AD6C9";
		String resultStr=MD5Utils.getMD5(StringB).toLowerCase();
		sb.append("&sign="+resultStr);
		sb.append("&"+"gid="+map.get("gid")); 
		 return sb.toString(); 
		 
	} 
	 
	 
	

	public static String createLinkStringByGet(Map<String, String> params) throws UnsupportedEncodingException {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
		//	value = URLEncoder.encode(value, "UTF-8");
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

}
