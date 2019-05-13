package com.stys.ipfs.test;

import static org.bitcoinj.core.Utils.HEX;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.ScriptBuilder;

import com.stys.ipfs.util.usdt.HttpJsonRpc;
import com.stys.ipfs.util.usdt.UnSpentBtc;

public class EnterCurrencyBtc {

	// 当前已经处理的区块
	private static String bestblockhash = "";
	public static Integer height = 0;
	public SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static EnterCurrencyBtc ebtc = new EnterCurrencyBtc();
	private String curtypeBTC = "BTC";
	private String curtypeUSDT = "USDT";
	public Map<String, Long> enterMapBTC = new HashMap<>();
	public Map<String, Long> enterMapUsdt = new HashMap<>();
	private boolean usdt = false;
	// satoshis转 btc 单位
	private BigDecimal btcFormatSato = new BigDecimal("100000000");

	// 发布交易
	public static boolean sendRwtx(String hex) {
		boolean b = HttpJsonRpc.broadRatx(hex);
		if (b) {
			return true;
		} else {
			return false;
		}
	}

	// 查询未花费
	public List<UnSpentBtc> getUn(String adress) {

		List<UnSpentBtc> unSpentBtcs = HttpJsonRpc.getUn(adress);
		return unSpentBtcs;
	}

	// 查询余额
	public Long getBalance(String adress) {
		Long b = HttpJsonRpc.getBalance(adress);
		return b;
	}

	// 生成交易
	public String getTrantionHex(List<UnSpentBtc> unSpentBTCList, String privateKey, long rate, String to) {
		NetworkParameters networkParameters = MainNetParams.get();
		Transaction transaction = new Transaction(networkParameters);
		DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParameters, privateKey);
		ECKey ecKey = dumpedPrivateKey.getKey();

		List<UTXO> utxos = new ArrayList<>();

		long totalMoney = 0;
		long inputNum = 0;
		// 遍历未花费列表
		for (UnSpentBtc un : unSpentBTCList) {
			if (un.getConfirmations() > 0) {
				UTXO utxo = null;
				try {
					// ScriptBuilder.createOutputScript(ecKey) Sha256Hash.wrap(un.getTx_hash())
					utxo = new UTXO(Sha256Hash.wrap(un.getTx_hash()), un.getTx_output_n(), Coin.valueOf(un.getValue()),
							546482, false, ScriptBuilder.createOutputScript(ecKey.toAddress(networkParameters)));
				} catch (ScriptException e) {
					// e.printStackTrace();
				}
				utxos.add(utxo);
				totalMoney += un.getValue();
				inputNum++;
			} else {
				System.out.println("0确认 " + un.getTx_hash() + " " + sim.format(System.currentTimeMillis()));
			}
		}

		long fees = (248 * inputNum + 34 * 2 + 10) * rate;
		long realValue = totalMoney - fees;
		System.out.println(" 手续费" + fees + " 总余额" + totalMoney + " 转账金额" + realValue);
		if (realValue > 0) {
			transaction.addOutput(Coin.valueOf(realValue), org.bitcoinj.core.Address.fromBase58(networkParameters, to));
		} else {
			System.out.println("手续费不足，或者余额未确认");
			return null;
		}

		// 消费列表总金额-已经转账的金额-手续费 就等于需要返回给自己的金额了
//        long balance=totalMoney-realValue-fees;//此处是为了将来扩展转账，暂时保留废代码
//        if (realValue>0){
////            transaction.addOutput(Coin.valueOf(balance),Address.fromBase58(networkParameters,Constants.btcin));
//        }
		for (UTXO utxo : utxos) {
//            TransactionOutPoint outPoint=new TransactionOutPoint(networkParameters,utxo.getIndex(),utxo.getHash());
			TransactionOutPoint outPoint = new TransactionOutPoint(networkParameters, utxo.getIndex(), utxo.getHash());
			transaction.addSignedInput(outPoint, utxo.getScript(), ecKey, Transaction.SigHash.ALL, true);
//            transaction.addSignedInput(outPoint,utxo.getScript(),ecKey);
			// return HEX.encode(transaction.bitcoinSerialize());//为什么不循环完，目前理解是同一个地址多个输入，
			// 只需签名一次，不一定正确
		}
		// System.out.println(transaction.getHash());
		return HEX.encode(transaction.bitcoinSerialize());
	}

	// 获取手续费单价
	public Long getBtcFee() {
		Long f = HttpJsonRpc.getBtcFee();
		return f;
	}

	// 生成交易
//    public static String getRawtx(String txid,int utxo_vout){
////        ECKey spenderKey= new ECKey();//转账地址
////        ECKey shopKey= new ECKey();//转向地址
////        ECKey changeKey=new ECKey();//找零地址
////
////        NetworkParameters params=MainNetParams.get();
////
////        Transaction tx=new Transaction(params);
////
////        Script utxo_script=  (new ScriptBuilder()).createOutputScript(spenderKey.toAddress(params));
////        TransactionOutPoint txopt= new TransactionOutPoint(params,utxo_vout,Sha256Hash.wrap(txid));
////        tx.addSignedInput(txopt,utxo_script,spenderKey);
////
////        Coin tavl=Coin.parseCoin()
//        return null;
//
//    }

}
