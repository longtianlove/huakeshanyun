package com.stys.ipfs.test;

import java.util.Map;

import com.stys.ipfs.util.PasswordEncoder;

public class TEstuser {

	public static void main(String[] args) {

		String username = "admin";
		String pwd = "123456";

		Map<String, String> map = PasswordEncoder.enCodePassWord(username, pwd);
		System.out.println(map.get(PasswordEncoder.SALT));
		System.out.println(map.get(PasswordEncoder.PASSWORD));
	}

}
