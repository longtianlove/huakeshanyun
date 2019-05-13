package com.stys.ipfs.util.usdt;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

/**
 * 
 * @author dp
 *
 */
public class CoinUtils {

	//修改后发现 速度非常快
	private static volatile CoinUtils instance; 
	

	private CoinUtils() throws Throwable {
		Base64 base64 = new Base64();
		String auth = Account.username + ":" + Account.password;
		byte[] textByte = auth.getBytes("UTF-8");
		String cred = base64.encodeToString(textByte);
		Map<String, String> headers = new HashMap<String, String>(1);
		headers.put("Authorization", "Basic " + cred);
		client = new JsonRpcHttpClient(new URL(Account.url), headers);
		 
	} 

	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
	public static CoinUtils getInstance() throws Throwable {
	
		 if (instance == null) {
	            synchronized (CoinUtils.class) {
	                if (instance == null) {
	                	instance = new CoinUtils();
	                }
	            }
	        }
	        return instance;
	}


	private JsonRpcHttpClient client;

	
	/**
	 *       查询 交易记录
	 * @param txid  
	 * @return
	 * @throws Throwable
	 */
	public LinkedHashMap gettransaction(String txid) throws Throwable {

		return (LinkedHashMap) client.invoke("omni_gettransaction", new Object[] { txid }, Object.class);

	}

	
	/**
	 * 获取地址对于的账户名称 by address
	 * 
	 * @param address
	 * @return
	 * @throws Throwable
	 */
	public String getAccountByAddress(String address) throws Throwable {

		return client.invoke("getaccount", new Object[] { address }, Object.class).toString();
	}

	/***
	 * 查询令牌属性。
	 * 
	 * @param: [propertyid]
	 * @return: java.lang.String
	 * @throws Throwable
	 **/
	public String getOmniPropertyById(int propertyid) throws Throwable {
		return client.invoke("omni_getproperty", new Object[] { propertyid }, Object.class).toString();
	}

	/**
	 * 得到所有地址
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String[] getAddresses() throws Throwable {

		String str = client.invoke("getaddressesbyaccount", new Object[] { "" }, Object.class).toString();

		String[] liststr = str.substring(1, str.length() - 1).replaceAll("\"", "").split(",");

		return liststr;

	}

	/**
	 * 得到钱包的秘钥
	 * 
	 * @param address
	 * @return
	 * @throws Throwable
	 */
	public String dumpprivkey(String address) throws Throwable {

		return client.invoke("dumpprivkey", new Object[] { address }, Object.class).toString();

	}
	
	/**
	 * 查询交易明细
	 * @param address
	 * @return
	 * @throws Throwable
	 */
	public  ArrayList listtransactions(String address) throws Throwable {
		return  (ArrayList) client.invoke("omni_listtransactions", new Object[] {address}, Object.class);
	}
	
	

	/**
	 * 
	 * @param address
	 * @return
	 * @throws Throwable
	 */
	public LinkedHashMap getBalance(String address) throws Throwable {

		return (LinkedHashMap) client.invoke("omni_getbalance", new Object[] { address, Account.propertyid }, Object.class);
	}

	/**
	 * 查询钱包内的所有地址的USDT余额列表
	 * 
	 * @return
	 * @throws Throwable
	 */
	public ArrayList getwalletaddressbalances() throws Throwable {
		return  (ArrayList) client.invoke("omni_getwalletaddressbalances", new Object[] {}, Object.class);
	}

	/**
	 * 
	 * 
	 * 查询钱包内的USDT总额
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String getwalletbalancesc() throws Throwable {
		return client.invoke("omni_getwalletbalances", new Object[] {}, Object.class).toString();
	}

	/**
	 * 验证地址是否存在
	 *
	 * @param address
	 * @return
	 * @throws Throwable
	 */
	public String validateaddress(String address) throws Throwable {
		return client.invoke("validateaddress", new Object[] { address }, Object.class).toString();
	}

	/**
	 * 如果钱包加密需要临时解锁钱包
	 *
	 * @param password
	 * @param time
	 * @return
	 * @throws Throwable
	 */
	public String walletpassphase(String password, int time) throws Throwable {
		return client.invoke("walletpassphase", new Object[] { password, time }, Object.class).toString();
	}

	/**
	 * btc转账到制定的账户中
	 *
	 * @param address
	 * @param amount
	 * @return
	 * @throws Throwable
	 */
	public String sendtoaddress(String address, double amount) throws Throwable {
		return client.invoke("sendtoaddress", new Object[] { address, amount }, Object.class).toString();
	}

	/**
	 * usdt转账
	 * 
	 * @param mainAddress
	 * @param address
	 * @param amount
	 * @return
	 * @throws Throwable
	 */
	public String send(String fromaddress, String toaddress, int propertyid, double amount, String feeaddress)
			throws Throwable {
		return client.invoke("omni_funded_send",
				new Object[] { fromaddress, toaddress, propertyid, String.valueOf(amount), feeaddress }, Object.class)
				.toString();
	}

	/**
	 * 创建并发送将给定生态系统中的所有可用令牌传输给收件人的事务，全部发送
	 * 
	 * @param fromaddress
	 * @param toaddress
	 * @param is_main_ecosystem
	 * @param feeaddressx
	 * @return
	 * @throws Throwable
	 */
	public String sendOmniTokenAll(String fromaddress, String toaddress, boolean is_main_ecosystem, String feeaddressx)
			throws Throwable {

		return client
				.invoke("omni_funded_sendall",
						new Object[] { fromaddress, toaddress, is_main_ecosystem ? 1 : 2, feeaddressx }, Object.class)
				.toString();
	}

	/**
	 * 查询账户下的交易记录
	 *
	 * @param account
	 * @param count
	 * @param offset
	 * @return
	 * @throws Throwable
	 */
	public String listtransactions(String account, int count, int offset) throws Throwable {
		return client.invoke("listtransactions", new Object[] { account, count, offset }, Object.class).toString();
	}

	/**
	 * 获取地址下未花费的币量
	 *
	 * @param minconf
	 * @param maxconf
	 * @param address
	 * @return
	 * @throws Throwable
	 */
	public String listunspent(int minconf, int maxconf, String address) throws Throwable {
		String[] addresss = new String[] { address };
		return client.invoke("listunspent", new Object[] { minconf, maxconf, addresss }, Object.class).toString();
	}

	/**
	 * 生成新的接收地址
	 *
	 * @return
	 * @throws Throwable
	 */
	public String getNewaddress() throws Throwable {
		return client.invoke("getnewaddress", new Object[] {}, Object.class).toString();
	}

	/**
	 * 获取钱包信息
	 *
	 * @return
	 * @throws Throwable
	 */
	public String getInfo() throws Throwable {
		return client.invoke("getinfo", new Object[] {}, Object.class).toString();
	}

}