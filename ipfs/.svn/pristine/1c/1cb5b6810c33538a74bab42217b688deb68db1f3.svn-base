package com.stys.ipfs.util.usdt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;

public class Constants {

	public static String rpcUser = "wydydn";
	public static String rpcPassword = "9999";
	public static String url = "http://192.168.1.100:61315";

	// 查询最新区块
	public static String blocklast = "https://chain.api.btc.com/v3/block/latest";

	// 查询交易列表
	public static String txBlock = "https://chain.api.btc.com/v3/block/";
	public static String txBlockpage = "/tx/?page=";

	// 查询区块信息
	public static String block = "https://chain.api.btc.com/v3/block/";

	// 查询未花费输出 /unspent 或者余额
	public static String unspentBtc = "https://chain.api.btc.com/v3/address/";

	// 广播交易 post提交
	public static String broadRwTxBtc = "https://chain.api.btc.com/v3/tools/tx-publish";

	// usdt查询单笔hash,后缀拼接txhash
	public static String txhashUsdt = "https://api.omniexplorer.info/v1/transaction/tx/";
	// usdt查询余额post提交
	public static String balanceUsdt = "https://api.omniexplorer.info/v2/address/addr/";
	// usdt广播交易 post
	public static String pushtxUsdt = "https://api.omniexplorer.info/v1/transaction/pushtx/";

	// btc手续费
	public static long btcfee = 6;

	// 用于转账usdt的手续费指定KwFeJox8msX1TkRqNzRU8LWE8BGShQozmjWDda9jsqo8ZNm51wRD
	public static String privateUsdtFeesKey = "KwFeJox8msX1TkRqNzRU8LWE8BGShQozmjWDda9jsqo8ZNm51wRD";
	public static String addressUsdtFees = "18QqEJafdKJX746ZaWv62623ZAk9W7Cd61";

	public static Long btc = Long.valueOf(100000);
	public static BigDecimal btcBig = new BigDecimal("0.001");
	public static BigDecimal eth = new BigDecimal("0.01");
	public static BigDecimal usdt = new BigDecimal("1");// usdt上账限额
	public static BigInteger gasLimit = BigInteger.valueOf(21000);

	// btic归集地址
	public static String btcin = "1MrfqFkxpxRZpxTwPtEwFLf6fqfFgrgN82";
	// eth归集地址
	public static String ethin = "0x857fe0c53b8e4665d7e53d91c7200f7386c26ddf";
	// usdt归集地址
	public static String usdtin = "18UcK33bo5pzfntCB3sLKnB2UUsZWhUGy9";

	// btc手续费查询
	public static String earncom = "https://bitcoinfees.earn.com/api/v1/fees/recommended";

	// BTC路径
	public static String parsePathBTC = "M/44/0/0/0";

	// ETH路径
	public static String parePathETH = "M/44/60/0/0";

	// 查询归集其实入币记录id,用于对于入了币得地址进行资金归集 ,此方法废弃
//    public static Map<String,Integer> enterLogidmap=new HashMap<>();

	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    public static void init(){
//        enterLogidmap.put("ETH",-1);
//        enterLogidmap.put("BTC",-1);
//        enterLogidmap.put("USDT",-1);
//    }
}
