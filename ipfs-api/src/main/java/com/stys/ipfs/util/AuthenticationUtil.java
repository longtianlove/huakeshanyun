package com.stys.ipfs.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.stys.ipfs.entity.TbPersonalInfo;

public class AuthenticationUtil {
	public final static String host = "https://ibankcard.market.alicloudapi.com";
	public final static String path = "/integrationBankCard/check";
	public final static String method = "POST";
	public final static String appcode = "db40b689c6ee47b4a3a254fa0d45af09";

	public static TbPersonalInfo getAuthenticationInfo(String bankCard, String idCard, String name, String phone) {
		TbPersonalInfo personalinfo = new TbPersonalInfo();
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		// 根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		Map<String, String> querys = new HashMap<String, String>();
		Map<String, String> bodys = new HashMap<String, String>();
		bodys.put("bankCard", bankCard);
		bodys.put("idCard", idCard);
		bodys.put("name", name);
		bodys.put("phone", phone);

		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
			JSONObject object = JSONObject.parseObject(result);
			System.out.println(result.toString());
			if (("0").equals(object.get("code"))) {
				// 获取response的body
				JSONObject obj = JSONObject.parseObject(object.get("result").toString());
				personalinfo.setBankCard(bankCard);
				personalinfo.setRealName(name);
				personalinfo.setIdcard(idCard);
				personalinfo.setPhone(phone);
				if (null == obj.get("bankName")) {
					personalinfo.setBankName("银联卡");
				} else {
					personalinfo.setBankName(obj.get("bankName").toString());
				}
//	    		personalinfo.setPersonalStatus(Integer.parseInt(obj.get("res").toString()));
//	    		personalinfo.setRemark(obj.get("description").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personalinfo;
	}
}
