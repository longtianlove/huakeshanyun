package com.stys.ipfs.util.usdt;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HttpJsonUsdt {
	private static BigDecimal dv = new BigDecimal(100000000);

	public static void main(String[] args) {
		// Long b= getUsdtBalance("1PyxKH8QLSKS1KYVWyfyMHCcv7sMCmsmWK");
		// Long b = getUsdtBalance("1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU");
		// System.out.println(b);

	}

	// usdt查询余额
	public static Long getUsdtBalance(String address) {
		String url = Constants.balanceUsdt;
		String text = "addr=" + address;
		JSONObject jsonObject = postOmni(url, text);
		if (jsonObject == null) {
			System.out.println("usdt查询余额结果为空");
			return null;
		}
		JSONObject balancejson = jsonObject.getJSONObject(address);
		JSONArray jsonArray = balancejson.getJSONArray("balance");

		for (int index = 0; index < jsonArray.size(); index++) {
			JSONObject obj = jsonArray.getJSONObject(index);
			if (obj.containsKey("error")) {
				if (obj.getBoolean("error") == true) {
					System.out.println("usdt查询错误");
					return null;
				}
			}
			if (obj.getString("id").equals("31")) {
				Long balance = obj.getLong("value");
				return balance;
			}
		}
		return null;
	}

	// usdt查询单笔交易,用于检测是否入币
	public static TxUsdtDe getTxusdtDe(String hash) {
		String url = Constants.txhashUsdt + hash;
		JSONObject jsonObject = HttpJsonRpc.getBtccom(url);
		if (jsonObject == null) {
			System.out.println("usdt入币hash查询失败");
			return null;
		}
		System.out.println(jsonObject.toString() + " *********usdt查询的单笔交易，即将进入检测正确性");
		String type = jsonObject.getString("type");
		if (type != null) {
			if (!type.equals("Simple Send")) {
				System.out.println("usdt无此交易");
				TxUsdtDe txUsdtDe = new TxUsdtDe();
				txUsdtDe.setBlock(-1);// 用户标识usdt无此交易
				return txUsdtDe;
			}
		}
		TxUsdtDe txUsdtDe = JSON.parseObject(jsonObject.toJSONString(), TxUsdtDe.class);
		return txUsdtDe;
	}

	/**
	 * 广播交易
	 */
	public static JSONObject pushtx(String hex) {
		String url = Constants.pushtxUsdt;
		String text = "signedTransaction=" + hex;
		JSONObject jsonObject = postOmni(url, text);
		return jsonObject;
	}

	/**
	 * post请求 https://api.omniexplorer.info/
	 * 
	 * @param url
	 * @param text
	 * @return
	 */
	private static JSONObject postOmni(String url, String text) {
		CloseableHttpResponse httpResponse = null;
		String result = null;
		// 创建httpClient实例
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建httpPost远程连接实例
		HttpPost httpPost = new HttpPost(url);
		// 设置请求头
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(5000)
				.setSocketTimeout(10000).build();
		httpPost.setConfig(requestConfig);
		StringEntity entity = new StringEntity(text, ContentType.DEFAULT_TEXT);
		httpPost.setEntity(entity);

		try {
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity enti = httpResponse.getEntity();
				result = EntityUtils.toString(enti, "UTF-8");
			}
		} catch (IOException e) {
			System.out.println("usdt");
			// e.printStackTrace();
		} finally {

			try {
				if (httpResponse != null)
					httpResponse.close();
			} catch (IOException e) {
				System.out.println("omni关闭连接异常 134,httpJsonUsdt");
				// e.printStackTrace();
			} finally {
				try {
					if (httpClient != null) {
						httpClient.close();
					}
				} catch (IOException e) {
					System.out.println("omni关闭连接异常 142,httpJsonUsdt");
					// e.printStackTrace();
				}
			}
		}
		if (result == null)
			return null;
		try {
			JSONObject rs = JSON.parseObject(result);
			return rs;
		} catch (Exception e) {
			System.out.println("omni查询结果解析json异常");
			// e.printStackTrace();
		}
		return null;
	}

}
