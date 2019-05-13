package com.stys.ipfs.util;

/**
 * 常量工具类
 *
 * @author
 */
public class Constant {

	// 登录错误次数统计
	public static String LOGIN_ERROR_COUNT = "loginErrorCount";
	// 最大登录错误次数
	public static int MAX_LOGIN_ERROR_NUM = 15;
	// 登录用户ID
	public static String LOGIN_USER_ID = "loginUserId";
	// 登录IP地址
	public static String LOGIN_IP_ADDRESS = "loginIpAddress";
	// 有错误
	public static String YES_ERROR = "1";
	// 无错误
	public static String NOT_ERROR = "0";
	//8T对应的LV1
	public static final int LV1=8;
	//200T对应的LV2
	public static final int LV2=200;
	//1000T对应的LV3
	public static final int LV3=1000;
	//累积最小量
	public static final int RULE0=8;
	//从LV1累积到 LV2 或者从LV2 累积到LV3 累积量
	public static final int RULE12=1000;
	//从LV1累积到LV3 累积量
	public static final int RULE13=2000;
	//从LV3累积到LV4 累积量
	public static final int RULE34=3000;
	//从LV4累积到LV5 累积量
	public static final int RULE45=8000;

	public static String WEIXIN_URL ="https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

}
