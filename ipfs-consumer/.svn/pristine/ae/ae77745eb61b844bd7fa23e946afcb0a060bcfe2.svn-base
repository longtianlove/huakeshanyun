package com.stys.ipfs.util.usdt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author: DengJiong
 * @date: 2018-05-09 18:43
 * @description:
 */

import org.apache.commons.lang3.StringUtils;

/**
 * http 工具类
 *
 * @author cloud cloud
 * @create 2017/10/18
 **/
public class USDTHttpUtil {

	private static final String CHARSET = "UTF-8";
	private static final String HTTP_POST = "POST";
	private static final String HTTP_GET = "GET";

	private static final String HTTP_PUT = "PUT";

	/**
	 * Send GET request
	 */
	public static String get(String url, Map<String, String> queryParas, Map<String, String> headers) {
		HttpURLConnection conn = null;
		try {
			conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), HTTP_GET, headers);
			conn.connect();
			return readResponseString(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public static String get(String url, Map<String, String> queryParas) {
		return get(url, queryParas, null);
	}

	public static String get(String url) {
		return get(url, null, null);
	}

	public static String jsonGet(String url, Map<String, String> params) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return get(url, params, headers);
	}

	/**
	 * Send POST request
	 */
	public static String post(String url, Map<String, String> queryParas, String data, Map<String, String> headers) {
		HttpURLConnection conn = null;
		try {
			conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), HTTP_POST, headers);
			conn.connect();
			OutputStream out = conn.getOutputStream();
			out.write(data.getBytes(CHARSET));
			out.flush();
			out.close();
			return readResponseString(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public static String post(String url, Map<String, String> queryParas, String data) {
		return post(url, queryParas, data, null);
	}

	public static String post(String url, String data, Map<String, String> headers) {
		return post(url, null, data, headers);
	}

	public static String post(String url, String data) {
		return post(url, null, data, null);
	}

	public static String jsonPost(String url, String data) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return post(url, null, data, headers);
	}

	public static String jsonPost(String url, Map<String, String> headers, String data) {
		if (headers == null) {
			headers = new HashMap<>();
		}
		headers.put("Content-Type", "application/json");
		return post(url, null, data, headers);
	}

	/**
	 * Send POST request
	 */
	public static String put(String url, Map<String, String> queryParas, String data, Map<String, String> headers) {
		HttpURLConnection conn = null;
		try {
			conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), HTTP_PUT, headers);
			conn.connect();
			OutputStream out = conn.getOutputStream();
			out.write(data.getBytes(CHARSET));
			out.flush();
			out.close();
			return readResponseString(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public static String jsonPut(String url, String data) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return post(url, null, data, headers);
	}

	/**
	 * https 域名校验
	 */
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * https 证书管理
	 */
	private static class TrustAnyTrustManager implements X509TrustManager {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	}

	private static SSLSocketFactory initSSLSocketFactory() {
		try {
			TrustManager[] tm = { new TrustAnyTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("TLS", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			return sslContext.getSocketFactory();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final SSLSocketFactory sslSocketFactory = initSSLSocketFactory();
	private static final TrustAnyHostnameVerifier trustAnyHostnameVerifier = new TrustAnyHostnameVerifier();

	private static HttpURLConnection getHttpConnection(String url, String method, Map<String, String> headers)
			throws Exception {
		URL _url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
		if (conn instanceof HttpsURLConnection) {
			((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
			((HttpsURLConnection) conn).setHostnameVerifier(trustAnyHostnameVerifier);
		}
		conn.setRequestMethod(method);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(30000);
		conn.setUseCaches(false); // Post 请求不能使用缓存
		if (headers != null) {
			String contentType = headers.get("Content-Type");
			if (StringUtils.isNotEmpty(contentType)) {
				conn.setRequestProperty("Content-Type", contentType);
			} else {
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			}
		}
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
		if (headers != null && !headers.isEmpty())
			for (Map.Entry<String, String> entry : headers.entrySet())
				conn.setRequestProperty(entry.getKey(), entry.getValue());

		return conn;
	}

	private static String readResponseString(HttpURLConnection conn) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		try {
			inputStream = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Build queryString of the url
	 */
	private static String buildUrlWithQueryString(String url, Map<String, String> queryParas) {
		if (queryParas == null || queryParas.isEmpty())
			return url;

		StringBuilder sb = new StringBuilder(url);
		boolean isFirst;
		if (url.indexOf("?") == -1) {
			isFirst = true;
			sb.append("?");
		} else {
			isFirst = false;
		}

		for (Map.Entry<String, String> entry : queryParas.entrySet()) {
			if (isFirst)
				isFirst = false;
			else
				sb.append("&");

			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isNotEmpty(value)) {
				try {
					value = URLEncoder.encode(value, CHARSET);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
				sb.append(key).append("=").append(value);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String httpUrl = "http://127.0.0.1/tbr/USDTRecharge";

		/*
		 * Map<String, Object> queryParas = new HashMap<>();
		 * 
		 * queryParas.put("userId", 1); queryParas.put("sendingaddress",
		 * "as23423423424"); queryParas.put("referenceaddress", 345345345);
		 * queryParas.put("txid", 345345345); queryParas.put("confirmations", 1);
		 * queryParas.put("amount", 2);
		 * 
		 * Map<String, String> headers = new HashMap<>(); headers.put("Content-Type",
		 * "application/json");
		 */

		String result = doPost(httpUrl);
		System.out.println(result);

	}

	public static String doPost(String httpUrl) {
		Map<String, String> queryParas = new HashMap<>();

		queryParas.put("userId", "1");
		queryParas.put("sendingaddress", "a1212s23423423424");
		queryParas.put("referenceaddress", "122323233123123123");
		queryParas.put("txid", "34534534232323434343451");
		queryParas.put("confirmations", "1");
		queryParas.put("amount", "2");
		queryParas.put("timestamp", "9999");
		String param = buildUrlWithQueryString("", queryParas).substring(1);
		System.out.println("-=-----------------------------------------" + param);
		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		String result = null;
		try {
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接请求方式
			connection.setRequestMethod("POST");
			// 设置连接主机服务器超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取主机服务器返回数据超时时间：60000毫秒
			connection.setReadTimeout(60000);

			// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
			connection.setDoOutput(true);
			// 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
			connection.setDoInput(true);
			// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
			connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 通过连接对象获取一个输出流
			os = connection.getOutputStream();
			// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
			os.write(param.getBytes());
			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200) {

				is = connection.getInputStream();
				// 对输入流对象进行包装:charset根据工作项目组的要求来设置
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				StringBuffer sbf = new StringBuffer();
				String temp = null;
				// 循环遍历一行一行读取数据
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}
		return result;
	}

}
