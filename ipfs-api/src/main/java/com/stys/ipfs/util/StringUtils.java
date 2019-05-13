package com.stys.ipfs.util;
//import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * @param list 要进行判断的数据集合
	 * @return 不为空返回true，为空返回false 方法说明：判断list集合是否为空
	 */
	public static boolean listIsNotNull(List<?> list) {
		return ((null != list) && (list.size() > 0));
	}

	public static boolean isEmpty(Object obj) {
		if (obj != null && !obj.equals("")) {
			return false;
		}
		return true;
	}

	public static Object isEmptyBack(Object obj) {
		if (obj != null && !obj.equals("")) {
			return obj;
		}
		return "";
	}

	/**
	 * @param src 要进行判断的字符串
	 * @return 为空返回true，不为空为false 方法说明：判断字符串是否为空
	 */
	public static boolean strIsNull(String src) {
		return (null == src) || "null".equals(src) || (src.trim().length() <= 0) || "".equals(src);
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {

		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 判断是否为数字
	 *
	 * @param str
	 * @return
	 * @Title: isNumeric
	 * @Description: 判断是否为数字
	 * @return: boolean
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param param
	 * @return
	 * @title: camelToUnderline
	 * @description: 驼峰转下划线
	 * @return: String
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append('_');
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * @param param
	 * @return
	 * @title: underlineToCamel
	 * @description:下划线转驼峰
	 * @return: String
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * A hashing method that changes a string (like a URL) into a hash suitable for
	 * using as a disk filename.
	 */
	public static String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private static String bytesToHexString(byte[] bytes) {
		// http://stackoverflow.com/questions/332079
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * @param filename
	 * @return
	 * @title: getExtensionName
	 * @description: Java文件操作 获取文件扩展名
	 * @return: String
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * @param filename
	 * @return
	 * @title: getFileNameNoEx
	 * @description: Java文件操作 获取不带扩展名的文件名
	 * @return: String
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	public static String randomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	public static boolean isNumericAndDot(String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]*([Ee]{1}[0-9]+)?");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 去除字符串首尾出现的某个字符.
	 *
	 * @param source  源字符串.
	 * @param element 需要去除的字符.
	 * @return String.
	 */
	public static String trimFirstAndLastChar(String source, char element) {
		boolean beginIndexFlag = true;
		boolean endIndexFlag = true;
		do {
			int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
			int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element)
					: source.length();
			source = source.substring(beginIndex, endIndex);
			beginIndexFlag = (source.indexOf(element) == 0);
			endIndexFlag = (source.lastIndexOf(element) + 1 == source.length());
		} while (beginIndexFlag || endIndexFlag);
		return source;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 *
	 * @param str    目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		/*
		 * if (str == null) { return ""; } try { StringBuilder sb = new StringBuilder();
		 * int currentLength = 0; for (char c :
		 * replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
		 * currentLength += String.valueOf(c).getBytes("GBK").length; if (currentLength
		 * <= length - 3) { sb.append(c); } else { sb.append("..."); break; } } return
		 * sb.toString(); } catch (UnsupportedEncodingException e) {
		 * e.printStackTrace(); }
		 */
		return "";
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		/*
		 * if (isBlank(html)) { return ""; }
		 */
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 转换为字节数组
	 *
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			// return EMPTY;
		}
		return null;
	}

	/**
	 * 转换为字节数组
	 *
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 得到类名
	 */
	public static String getClassName(String str) {
		String lowerStr = str.toLowerCase();
		int index = lowerStr.indexOf("_");
		lowerStr = lowerStr.replaceFirst("_", "");
		lowerStr = lowerStr.substring(index, index + 1).toUpperCase() + lowerStr.substring(index + 1);
		while (lowerStr.indexOf("_") != -1) {
			index = lowerStr.indexOf("_");
			lowerStr = lowerStr.replaceFirst("_", "");
			lowerStr = lowerStr.substring(0, index) + lowerStr.substring(index, index + 1).toUpperCase()
					+ lowerStr.substring(index + 1);
		}
		return toUpperCaseFirstOne(lowerStr);
	}

	/**
	 * 横线前第一个字母大写
	 */
	public static String getHorizontalLineUpper(String str) {
		String lowerStr = str.toLowerCase();
		while (lowerStr.indexOf("_") != -1) {
			int index = lowerStr.indexOf("_");
			lowerStr = lowerStr.replaceFirst("_", "");
			lowerStr = lowerStr.substring(0, index) + lowerStr.substring(index, index + 1).toUpperCase()
					+ lowerStr.substring(index + 1);
		}
		return lowerStr;
	}

	/**
	 * 判断大小
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static String max(String x, String y) {
		int i = StringUtils.strIsNull(x) ? 0 : Integer.parseInt(x),
				j = StringUtils.strIsNull(y) ? 0 : Integer.parseInt(y);
		return String.valueOf(Math.max(i, j));
	}

	/**
	 * 比较两个字符串大小
	 *
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compareStr(String str1, String str2) {

		return str1.compareTo(str2) >= 0;
	}
}
