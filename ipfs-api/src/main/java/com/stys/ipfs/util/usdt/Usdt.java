package com.stys.ipfs.util.usdt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class Usdt {
	// private Logger log = Logger.getLogger("");

	public static void main(String[] args) {

		String privateket = new Usdt().dumpprivkey("1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU");
		System.out.println(privateket);
	}

	public static String getBtcBalance(String address) {
		String url = "https://blockchain.info/balance?active=" + address;
		String datastr = HttpUtil.get(url);
		return datastr;
	}

	/**
	 * USDT查询余额
	 * 
	 * @return
	 */
	public String getBalance(String address) {
		JSONObject json = doRequest(Account.METHOD_GET_BALANCE, address, Account.propertyid);
		if (isError(json)) {
			System.out.println(json.get("error"));
			// log.error("获取USDT余额:{}",json.get("error"));
			return null;
		}
		return json.getString(Account.RESULT);
	}

	/***
	 * 查询Omni状态信息。
	 * 
	 * @param: []
	 * @return: java.lang.String
	 **/
	public String getOmniInfo() {
		JSONObject json = doRequest("omni_getinfo");
		if (isError(json)) {
			System.out.println(json.get("error"));
			// log.error("获取USDT余额:{}",json.get("error"));
			return null;
		}
		return json.getString(Account.RESULT);
	}

	/**
	 * 创建新的地址
	 * 
	 * @return
	 */
	public String newAddress() {
		JSONObject json = doRequest(Account.METHOD_NEW_ADDRESS);
		if (isError(json)) {
			System.out.println(json.get("error"));
			// log.error("获取USDT地址失败:{}",json.get("error"));
			return "";
		}
		return json.getString(Account.RESULT);

	}

	public String dumpprivkey(String address) {

		JSONObject json = doRequest(Account.METHOD_DUMPPRIVKEY, address);
		if (isError(json)) {
			System.out.println(json.get("error"));
			// log.error("获取USDT地址失败:{}",json.get("error"));
			return "";
		}
		return json.getString(Account.RESULT);

	}

	/**
	 * 得到钱包的总余额
	 * 
	 * @return
	 */
	public String getwalletbalances() {
		JSONObject json = doRequest(Account.METHOD_OMNI_GETWALLETBALANCES);
		return json.getString(Account.RESULT);

	}

	/**
	 * 
	 * @return
	 */
	public String getAddresses() {
		JSONObject json = doRequest("getaddressesbyaccount", "");
		if (isError(json)) {
			System.out.println(json.get("error"));
			// log.error("获取USDT地址失败:{}",json.get("error"));
			return "";
		}
		return json.getString(Account.RESULT);
	}

	/**
	 *
	 * @param json
	 * @return
	 */
	private boolean isError(JSONObject json) {
		if (json == null || (StringUtils.isNotEmpty(json.getString("error")) && json.get("error") != "null")) {
			return true;
		}
		return false;
	}

	private JSONObject doRequest(String method, Object... params) {
		JSONObject param = new JSONObject();
		param.put("id", System.currentTimeMillis() + "");
		param.put("jsonrpc", "2.0");
		param.put("method", method);
		if (params != null) {
			param.put("params", params);
		}
		String creb = Base64.encodeBase64String((Account.username + ":" + Account.password).getBytes());
		Map<String, String> headers = new HashMap<>(2);
		headers.put("Authorization", "Basic " + creb);
		String resp = "";
		if (Account.METHOD_GET_TRANSACTION.equals(method)) {
			try {
				resp = USDTHttpUtil.jsonPost(Account.url, headers, param.toJSONString());
			} catch (Exception e) {
				if (e instanceof IOException) {
					resp = "{}";
				}
			}
		} else {
			resp = USDTHttpUtil.jsonPost(Account.url, headers, param.toJSONString());
		}
		return JSON.parseObject(resp);
	}

	/**
	 * 一个自动归集的方法
	 * 
	 * @param outValue
	 * @return
	 */
	public String collectionUsdt(BigDecimal outValue) {
		JsonRpcHttpClient client = null;
		try {
			Map[] argsOne = new Map[1];
			Map[] args1 = new Map[1];
			String creb = Base64.encodeBase64String((Account.username + ":" + Account.password).getBytes());
			Map<String, String> headers = new HashMap<>(2);
			headers.put("Authorization", "Basic " + creb);
			headers.put("server", "1");
			client = new JsonRpcHttpClient(new URL(Account.url), headers);
			List<Map> listunspent = (List<Map>) client.invoke("listunspent", new Object[] {}, Object.class);
			for (Map map : listunspent) {
				Double amount = (Double) map.get("amount");
				// 这里是找一笔uxto的btc交易做桥梁
				if (amount > 1) {
					String txId = (String) map.get("txid");
					String scriptPubKey = (String) map.get("scriptPubKey");
					int vout = (int) map.get("vout");
					Map input = new HashMap<>();
					input.put("txid", txId);
					input.put("vout", vout);
					args1[0] = input;
					Map inputT = new HashMap<>();
					inputT.put("txid", txId);
					inputT.put("vout", vout);
					inputT.put("scriptPubKey", scriptPubKey);
					inputT.put("value", amount);
					argsOne[0] = inputT;
					break;
				}
			}
			// 计算字节大小和费用(因为是归集USDT 所以我用最小的输入来降低手续费，如果你是BTC和USDT一起归总那就要根据归集的输入来计算了)
			BigDecimal keyCount = calculationFee(1);
			// 将聪换算成BTC
			BigDecimal transferFee = keyCount.divide(new BigDecimal("100000000"), 8, RoundingMode.HALF_UP);
			if (transferFee.compareTo(BigDecimal.ZERO) <= 0 || outValue.compareTo(transferFee) <= 0) {
				return null;
			}
			/**
			 * 通过全节点构造原生交易
			 */
			// 创建BTC交易
			Map args2 = new HashMap<>();
			Object result = client.invoke("createrawtransaction", new Object[] { args1, args2 }, Object.class);
			String transaction = String.valueOf(result);
			/*
			 * //解锁钱包 client.invoke("walletpassphrase", new Object[]{"xxxx", 100},
			 * Object.class);
			 */
			// 创建Usdt交易
			String simplesendResult = (String) client.invoke("omni_createpayload_simplesend",
					new Object[] { 1, outValue.toString() }, Object.class);
			// usdt交易附加到BTC交易上
			String opreturnResult = (String) client.invoke("omni_createrawtx_opreturn",
					new Object[] { transaction, simplesendResult }, Object.class);
			// 设置归总地址
			String reference = (String) client.invoke("omni_createrawtx_reference",
					new Object[] { opreturnResult, "归总地址" }, Object.class);
			// 填写手续费及找零地址
			String changeResult = (String) client.invoke("omni_createrawtx_change",
					new Object[] { reference, argsOne, "找零地址", transferFee.toString() }, Object.class);
			// 获取原生交易hex
			Map signrawtransaction = (Map) client.invoke("signrawtransaction", new Object[] { changeResult },
					Object.class);
			// 广播交易
			String txId = (String) client.invoke("sendrawtransaction", new Object[] { signrawtransaction.get("hex") },
					Object.class);
			return txId;
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算手续费
	 *
	 * @param inputCount
	 * @return
	 */
	private BigDecimal calculationFee(int inputCount) {
		// 计算手续费获取每个字节的手续费
		String url = "bitcoinfees.earn.com";
		// 计算字节大小和费用
		String resut = sendGet(url, null);
		// =====resut===>转对象Model省略了，其实http请求都有公用的方法所以我随便写了。。
		BigDecimal keyCount = BigDecimal.valueOf((inputCount * 148 + 44) * getFeeRate());
		//
		return keyCount;

	}

	public static Long getFeeRate() {
		try {
			String httpGet1 = HttpUtil.get("https://bitcoinfees.earn.com/api/v1/fees/recommended");
			Map map = JSON.parseObject(httpGet1, Map.class);
			Long fastestFee = Long.valueOf(map.get("fastestFee").toString());
			return fastestFee;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 转账
	 * 
	 * @param toAddr
	 * @param value
	 * @return
	 */
	public String send(String mainAddress, String toAddr, double value, String feeaddress) {
		if (vailedAddress(toAddr)) {
			JSONObject json = doRequest(Account.METHOD_SEND_TO_ADDRESS, mainAddress, toAddr, Account.propertyid,
					String.valueOf(value));
			if (isError(json)) {
				System.out.println("USDT 转账失败 ");
				return "";
			} else {
				System.out.println("USDT 转账成功 ");
				return json.getString(Account.RESULT);
			}
		} else {
			System.out.println("USDT接受地址不正确");
			return "";
		}

	}

	/*
	 * 导出钱包数据以可读的方式
	 * 
	 * @param: []
	 * 
	 * @return: java.lang.String
	 **/
	public String dumpWallet() {
		System.currentTimeMillis();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String file = String.format("wallet-%s.txt", time);
		doRequest("dumpwallet", file);
		return file;
	}

	public boolean vailedAddress(String address) {
		JSONObject json = doRequest("validateaddress", address);
		if (isError(json)) {
			System.out.println("USDT验证地址失败");
			return false;
		} else {
			return json.getJSONObject(Account.RESULT).getBoolean("isvalid");
		}

	}
}