package com.stys.ipfs.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CheckUtil {

	private String secret;

	private Map<String, Object> map = new HashMap<String, Object>();

	public CheckUtil(String secret) {
		this.secret = secret;
	}

	/**
	 * 添加参数
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, Object value) {
		map.put(key, value);
	}

	/**
	 * 检验签名是否正确
	 * 
	 * @param sign
	 * @return
	 */
	public boolean checkSign(String sign) {
		if (sign == null || sign == "") {
			return false;
		}
		// 本地计算新的签名
		String cal_sign = makeSign();
		System.out.println("cal_sign:" + cal_sign);
		if (cal_sign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成签名
	 * 
	 * @return
	 */
	public String makeSign() {
		// 拼接数据
		String str = buildData();
		// 在拼接的数据后拼入API KEY
		str += "&key=" + secret;
		// MD5加密
		String re = MD5Util.encrypt(str);
		// 所有字符串转成大写
		return re.toUpperCase();

	}

	/**
	 * 拼接数据
	 * 
	 * @return
	 */
	private String buildData() {
		String str = "";
		Map<String, Object> resultMap = sortMapByKey(map);
		Iterator<String> it = resultMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = resultMap.get(key);
			str += key + "=" + value + "&";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * 使用 Map按key进行排序(这里重写了比较器的compare方法按升序排序)
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}

}