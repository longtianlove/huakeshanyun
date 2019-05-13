package com.stys.ipfs.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestJson {

	public static void main(String[] args) {

		String json = "{" + " \"code\": \"0000\"," + " \"msg\": \"success\"," + " \"obj\": {"
				+ "  \"mchUserId\": \"111471\"," + " \"mobile\": \"180****1273\","
				+ "  \"account\": \"201812180000004\"," + "  \"name\": \"罗**\","
				+ "  \"idCardNo\": \"42900**********618\"" + "}" + "}";

		JSONObject authjsonObject = JSON.parseObject(json);
		String authMsg = authjsonObject.getString("obj");
		JSONObject authjsonObject2 = JSON.parseObject(authMsg);
		String account = authjsonObject2.getString("account");
		System.out.println(account);

	}

}
