package com.stys.ipfs.test;

import static org.bitcoinj.core.Utils.HEX;
import static org.bitcoinj.crypto.HDUtils.parsePath;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.spongycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.stys.ipfs.util.HttpUtils;
import com.stys.ipfs.util.usdt.HttpJsonRpc;
import com.stys.ipfs.util.usdt.HttpJsonUsdt;
import com.stys.ipfs.util.usdt.UnSpentBtc;

public class UsdtUtils {

	private boolean isMainNet = true;

	public static BigDecimal usdt = new BigDecimal("1");// usdt上账限额

	private final static NetworkParameters networkParameters = MainNetParams.get();

	// usdt归集地址
	public static String usdtin = "1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU";

	// 用于转账usdt的手续费
	public static String addressUsdtFees = "1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU";

	// btic归集地址
	public static String btcin = "1LyjyYkq1gnGB58CLDiHVtMhYqLBGa5NRu";

	static String privateUsdtFeesKey = "L3UfzW7r4EbKBJJivqfDzDWGi7yUpjZy3iBmRrhA5x672pKKwLqb";

	public static SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Logger logger = Logger.getLogger(getClass());

	public static void main(String[] args) {
		// (String curr, int count, int index, int coinType, List<String> wl)
		/*
		 * List<String> worlds = getWords(); Map<String, ShellWallet> walletMapBTC =
		 * grenerateWallet(0, 50000, 1, "1", worlds);
		 * System.out.println(walletMapBTC.toString());
		 */

		cashSweepUsdt("1AKCjiDRLcNgLm46mrLBQmexMSPiRK3mQY", 20L);

	}

	/**
	 * 归集usdt
	 * 
	 * @param adress
	 * @param rate
	 * @return
	 */
	public static int cashSweepUsdt(String adress, long rate) {

		ShellWallet shellWallet = new ShellWallet();
		shellWallet.setAddress("1AKCjiDRLcNgLm46mrLBQmexMSPiRK3mQY");
		shellWallet.setPrivateKey("L5Vs6hDmrQ9BVLVFVUXUDFUq7zZruHjX8RE1dikXgb8t4e18b5RP");
		int t = -1;

		Long b = getOmniUsdtBalance(adress);// usdt余额
		System.out.println("1AKCjiDRLcNgLm46mrLBQmexMSPiRK3mQY-usdt余额:" + b);
		if (b != null) {

			System.out.println("usdt归集地址 " + adress + " 归集余额 " + b);
			if (b >= Long.valueOf(usdt.toString())) {// 归集余额是否足够

				List<UnSpentBtc> unSpentBtcs = getUn(adress);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (unSpentBtcs != null) {
					if (unSpentBtcs.size() > 0) {
						// 生成usdt签名交易
						Transaction tx = getUsdtOmniSimpleSendHex(shellWallet.getPrivateKey(), shellWallet.getAddress(),
								usdtin, b, rate);
						System.out.println("tx----------------->" + tx);
						if (tx != null) {

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							boolean bo = EnterCurrencyBtc.sendRwtx(HEX.encode(tx.bitcoinSerialize()));
							if (bo) {
								t++;
								System.out.println("usdt归集成功: " + b + " to" + btcin + " from:"
										+ shellWallet.getAddress() + " hash: " + tx.getHash() + " 时间: "
										+ sim.format(System.currentTimeMillis()));
							} else {
								System.out.println("usdt归集失败: " + b + " to" + btcin + " from:"
										+ shellWallet.getAddress() + " 时间: " + sim.format(System.currentTimeMillis()));
							}
						} else {
							System.out.println("归集生成失败: " + adress + " " + sim.format(System.currentTimeMillis()));
						}
					}
					// }
				}
			} else {
				System.out.println("查询usdt余额出错，未查到余额 " + adress);
			}
		} else

		{
			System.out.println("没有在数据库查询到钱包 地址: " + adress + "  " + sim.format(System.currentTimeMillis()));
		}
		return t;
	}

	// 生成usdt交易
	public static Transaction getUsdtOmniSimpleSendHex(String privateKey, String address, String to, Long amount,
			long rate) { // 构建私钥
		DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParameters, privateKey);
		ECKey fromKey = dumpedPrivateKey.getKey();

		// 构建usdt转账金额
		PropertyType propertyType = PropertyType.INDIVISIBLE;
		OmniValue omniValue = OmniValue.of(amount, propertyType);

		List<UnSpentBtc> unspentOutputs = getUn(address);
		if (unspentOutputs == null || unspentOutputs.size() == 0) {
			System.out.println("utxo未查询到");
			return null;
		}

		Transaction tx = myCreateSigendSimpleSend(fromKey, to, unspentOutputs, omniValue, rate);
		if (tx == null)
			return null;
		return tx;
	}

	// 1
	private static Transaction myCreateSigendSimpleSend(ECKey fromKey, String to, List<UnSpentBtc> unspentOutputs,
			OmniValue amount, long rate) {
		byte[] omni = "omni".getBytes();
		byte[] payload0 = new byte[7];// 中间7个0
		byte[] op = byteMergerAll(omni, payload0);// 合并byte
		byte[] sp = new byte[1];
		sp[0] = 31;// usdt
		byte[] opsp = byteMergerAll(op, sp);
		Long amou = amount.getWillets();
		ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
		byte[] amoun = byteBuffer.putLong(amou).array();
		byte[] paload = byteMergerAll(opsp, amoun);
		Script script = ScriptBuilder.createOpReturnScript(paload);// 构造usdt转账script

		return myCreateSignedOmniTransaction(fromKey, to, unspentOutputs, script, rate);
	}

	// 2
	private static Transaction myCreateSignedOmniTransaction(ECKey fromKey, String to, List<UnSpentBtc> unspentOutputs,
			Script script, long rate) {
		Transaction tx = new Transaction(networkParameters);
		tx.addOutput(Coin.ZERO, script);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 查询指定地址的btc手续费余额，输出
		DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParameters, privateUsdtFeesKey);
		ECKey fromKeyfees = dumpedPrivateKey.getKey();
		List<UnSpentBtc> unSpentBtcs = getUn(addressUsdtFees);
		Long b = sum(unSpentBtcs);
		if (b == null) {
			System.out.println("查询用于转账的手续费btc余额出错");
			return null;
		}
		Transaction transaction = makeChangeBtcFees(tx, fromKeyfees.toAddress(networkParameters), b, rate);
		if (transaction == null) {
			return null;
		}

		Address toAddr = Address.fromBase58(networkParameters, to);
		tx.addOutput(Transaction.MIN_NONDUST_OUTPUT, toAddr);// usdt转账地址，即btc地址

		// 签名输入
		Transaction txion = getSingedTransaction(unspentOutputs, fromKey, transaction);
		Transaction txx = getSingedTransaction(unSpentBtcs, fromKeyfees, txion);

		return txx;
	}

	private static long sum(Collection<UnSpentBtc> unspentOutputs) {
		long sum = 0;
		for (UnSpentBtc output : unspentOutputs) {
			if (output.getConfirmations() > 0) {
				sum += output.getValue();
			}
		}
		return sum;
	}

	// 未花费交易添加并签名
	private static Transaction getSingedTransaction(List<UnSpentBtc> unSpentBTCList, ECKey fromKey,
			Transaction transaction) {

		List<UTXO> utxos = new ArrayList<>();

		// long totalMoney=0;
		// long inputNum=0;
		// 遍历未花费列表
		for (UnSpentBtc un : unSpentBTCList) {
			if (un.getConfirmations() > 0) {
				UTXO utxo = null;
				try {
					utxo = new UTXO(Sha256Hash.wrap(un.getTx_hash()), un.getTx_output_n(), Coin.valueOf(un.getValue()),
							100, false, ScriptBuilder.createOutputScript(fromKey.toAddress(networkParameters)));
				} catch (ScriptException e) {
					e.printStackTrace();
				}
				utxos.add(utxo);
				// totalMoney+=un.getValue();
				// inputNum++;
			} else {
				System.out.println("0确认 " + un.getTx_hash() + " " + sim.format(System.currentTimeMillis()));
			}
		}

		for (UTXO utxo : utxos) {
			TransactionOutPoint outPoint = new TransactionOutPoint(networkParameters, utxo.getIndex(), utxo.getHash());
			transaction.addSignedInput(outPoint, utxo.getScript(), fromKey, Transaction.SigHash.ALL, true);
//            transaction.addInput(utxo.getHash(),utxo.getIndex(),utxo.getScript());
			// return transaction;
		}

		return transaction;
	}

	private static Transaction makeChangeBtcFees(Transaction tx, Address address, long totalInputAmount, long rate) {
		long fee = tx.getMessageSize() * 2 * rate + 546 + 546;
		long amountOut = totalInputAmount - fee;
//        long amountChange= totalInputAmount-amountOut-fee;
		if (amountOut < 0) {
			System.out
					.println("btc手续费不足" + " 手续费: " + fee + " 余额:" + totalInputAmount + " size: " + tx.getMessageSize());
			return null;
		}
		if (amountOut > 0) {
			System.out.println("btc手续费 " + fee + " 转账金额" + amountOut + " size: " + tx.getMessageSize());
			tx.addOutput(Coin.valueOf(amountOut), address);
		}
		return tx;
	}

	private static byte[] byteMergerAll(byte[]... values) {
		int length_byte = 0;
		for (int i = 0; i < values.length; i++) {
			length_byte += values[i].length;
		}
		byte[] all_byte = new byte[length_byte];
		int countLength = 0;
		for (int i = 0; i < values.length; i++) {
			byte[] b = values[i];
			System.arraycopy(b, 0, all_byte, countLength, b.length);
			countLength += b.length;
		}
		return all_byte;
	}

	// 查询未花费
	public static List<UnSpentBtc> getUn(String adress) {

		List<UnSpentBtc> unSpentBtcs = HttpJsonRpc.getUn(adress);
		return unSpentBtcs;
	}

	// 查询usdt余额
	public static Long getOmniUsdtBalance(String address) {
		Long b = HttpJsonUsdt.getUsdtBalance(address);
		return b;
	}

	/**
	 * usdt 离线签名
	 *
	 * @param privateKey：私钥
	 * @param toAddress：接收地址
	 * @param amount:转账金额
	 * @return
	 */
	public String omniSign(String fromAddress, String toAddress, String privateKey, Long amount, Long fee,
			Integer propertyid, List<UTXO> utxos) throws Exception {
		boolean isMainNet = true;
		NetworkParameters networkParameters = isMainNet ? MainNetParams.get() : TestNet3Params.get();
		Transaction tran = new Transaction(networkParameters);
		if (utxos == null || utxos.size() == 0) {
			throw new Exception("utxo为空");
		}
		// 这是比特币的限制最小转账金额，所以很多usdt转账会收到一笔0.00000546的btc
		Long miniBtc = 546L;
		tran.addOutput(Coin.valueOf(miniBtc), Address.fromBase58(networkParameters, toAddress));

		// 构建usdt的输出脚本 注意这里的金额是要乘10的8次方
		String usdtHex = "6a146f6d6e69" + String.format("%016x", propertyid) + String.format("%016x", amount);
		tran.addOutput(Coin.valueOf(0L), new Script(Utils.HEX.decode(usdtHex)));

		// 如果有找零就添加找零
		String changeAddress = fromAddress;
		Long changeAmount = 0L;
		Long utxoAmount = 0L;
		List<UTXO> needUtxo = new ArrayList<>();
		for (UTXO utxo : utxos) {
			if (utxoAmount > (fee + miniBtc)) {
				break;
			} else {
				needUtxo.add(utxo);
				utxoAmount += utxo.getValue().value;
			}
		}
		changeAmount = utxoAmount - (fee + miniBtc);
		// 余额判断
		if (changeAmount < 0) {
			throw new Exception("utxo余额不足");
		}
		if (changeAmount > 0) {
			tran.addOutput(Coin.valueOf(changeAmount), Address.fromBase58(networkParameters, changeAddress));
		}

		// 先添加未签名的输入，也就是utxo
		for (UTXO utxo : needUtxo) {
			tran.addInput(utxo.getHash(), utxo.getIndex(), utxo.getScript())
					.setSequenceNumber(TransactionInput.NO_SEQUENCE - 2);
		}

		// 下面就是签名
		for (int i = 0; i < needUtxo.size(); i++) {
			ECKey ecKey = DumpedPrivateKey.fromBase58(networkParameters, privateKey).getKey();
			TransactionInput transactionInput = tran.getInput(i);
			Script scriptPubKey = ScriptBuilder.createOutputScript(Address.fromBase58(networkParameters, fromAddress));
			Sha256Hash hash = tran.hashForSignature(i, scriptPubKey, Transaction.SigHash.ALL, false);
			org.bitcoinj.core.ECKey.ECDSASignature ecSig = ecKey.sign(hash);
			TransactionSignature txSig = new TransactionSignature(ecSig, Transaction.SigHash.ALL, false);
			transactionInput.setScriptSig(ScriptBuilder.createInputScript(txSig, ecKey));
		}

		// 这是签名之后的原始交易，直接去广播就行了
		String signedHex = Hex.toHexString(tran.bitcoinSerialize());
		// 这是交易的hash
		String txHash = Hex.toHexString(Utils.reverseBytes(Sha256Hash.hash(Sha256Hash.hash(tran.bitcoinSerialize()))));
		logger.info("fee:{" + fee + "},utxoAmount:{" + utxoAmount + "},changeAmount:{" + changeAmount + "}");
		return signedHex;
	}

	/**
	 * 获取矿工费用
	 * 
	 * @param utxos
	 * @return
	 */
	public Long getOmniFee(List<UTXO> utxos) {
		Long miniBtc = 546L;
		Long feeRate = getFeeRate();
		Long utxoAmount = 0L;
		Long fee = 0L;
		Long utxoSize = 0L;
		for (UTXO output : utxos) {
			utxoSize++;
			if (utxoAmount > (fee + miniBtc)) {
				break;
			} else {
				utxoAmount += output.getValue().value;
				fee = (utxoSize * 148 * 34 * 3 + 10) * feeRate;
			}
		}
		return fee;
	}

	/***
	 * 获取未消费列表
	 * 
	 * @param address ：地址
	 * @return
	 */
	public List<UTXO> getUnspent(String address) {
		List<UTXO> utxos = Lists.newArrayList();
		String host = this.isMainNet ? "blockchain.info" : "testnet.blockchain.info";
		String url = "https://" + host + "/zh-cn/unspent?active=" + address;
		try {

			String httpGet = HttpUtils.sendGet(url, null);// TODO;联网
			if (org.apache.commons.lang3.StringUtils.equals("No free outputs to spend", httpGet)) {
				return utxos;
			}
			com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(httpGet);
			com.alibaba.fastjson.JSONArray unspentOutputs = jsonObject.getJSONArray("unspent_outputs");
			List<Map> outputs = JSONObject.parseArray(unspentOutputs.toJSONString(), Map.class);

			if (outputs == null || outputs.size() == 0) {
				System.out.println("交易异常，余额不足");
			}
			for (int i = 0; i < outputs.size(); i++) {
				Map outputsMap = outputs.get(i);
				String tx_hash = outputsMap.get("tx_hash").toString();
				String tx_hash_big_endian = outputsMap.get("tx_hash_big_endian").toString();
				String tx_index = outputsMap.get("tx_index").toString();
				String tx_output_n = outputsMap.get("tx_output_n").toString();
				String script = outputsMap.get("script").toString();
				String value = outputsMap.get("value").toString();
				String value_hex = outputsMap.get("value_hex").toString();
				String confirmations = outputsMap.get("confirmations").toString();
				UTXO utxo = new UTXO(Sha256Hash.wrap(tx_hash_big_endian), Long.valueOf(tx_output_n),
						Coin.valueOf(Long.valueOf(value)), 0, false, new Script(Hex.decode(script)));
				utxos.add(utxo);
			}
			return utxos;
		} catch (Exception e) {
			logger.error("【BTC获取未消费列表】失败，", e);
			return null;
		}

	}

	/**
	 * 获取btc费率
	 *
	 * @return
	 */
	public Long getFeeRate() {
		try {
			String httpGet1 = HttpUtils.sendGet("https://bitcoinfees.earn.com/api/v1/fees/recommended", null);
			Map map = JSON.parseObject(httpGet1, Map.class);
			Long fastestFee = Long.valueOf(map.get("fastestFee").toString());
			return fastestFee;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	// 获取钱包
	public static Map<String, ShellWallet> grenerateWallet(int coinType, int count, int index, String name,
			List<String> words) {
		Map<String, ShellWallet> walletMap = new LinkedHashMap<>();
		if (words == null || words.size() != 24) {
			System.out.println("助记词为空,或者不够24个");
			return null;
		} else {
			DeterministicSeed deterministicSeed = new DeterministicSeed(words, null, "", 0);
			DeterministicKeyChain deterministicKeyChain = DeterministicKeyChain.builder().seed(deterministicSeed)
					.build();
			String path = null;
			if (coinType == 0) {
				for (int i = index; i <= count; i++) {
					path = "M/44/0/0/0" + i;
					NetworkParameters networkParameters = MainNetParams.get();
					BigInteger privkeybtc = deterministicKeyChain.getKeyByPath(parsePath(path), true).getPrivKey();
					ECKey ecKey = ECKey.fromPrivate(privkeybtc);
//                String publicKey= Numeric.toHexStringNoPrefixZeroPadded(new BigInteger(ecKey.getPubKey()),66);
					String publicKey2 = ecKey.getPublicKeyAsHex();
					// System.out.println("pub "+publicKey2);
					// System.out.println("pubkey "+ecKey.getPubKeyPoint().toString());
					String privateKey = ecKey.getPrivateKeyEncoded(networkParameters).toString();
					String address = ecKey.toAddress(networkParameters).toString();
					ShellWallet wallet = new ShellWallet(i, coinType, privateKey, publicKey2, address, name);
					walletMap.put(address, wallet);
				}
			} else if (coinType == 2) {
				for (int i = index; i <= count; i++) {
					path = "M/44/60/0/0" + i;
					BigInteger privkeyeth = deterministicKeyChain.getKeyByPath(parsePath(path), true).getPrivKey();
					ECKeyPair ecKeyPair = ECKeyPair.create(privkeyeth);
					String publicKey = Numeric.toHexStringWithPrefix(ecKeyPair.getPublicKey());
					String privateKey = Numeric.toHexStringWithPrefix(ecKeyPair.getPrivateKey());

//                    System.out.println(ecKeyPair.getPrivateKey()+" **********");
					String address = "0x" + Keys.getAddress(ecKeyPair);
					ShellWallet wallet = new ShellWallet(i, coinType, privateKey, publicKey, address, name);
					walletMap.put(address, wallet);
				}
			}
		}
		return walletMap;
	}

	/**
	 * 生成助记词
	 * 
	 * @return
	 */
	public static List<String> getWords() {
		MnemonicCode mnemonicCode = null;
		try {
			mnemonicCode = new MnemonicCode();
		} catch (IOException e) {
			System.out.println("mnemonicCode 声明出错");
			e.printStackTrace();
		}

		if (mnemonicCode == null) {
			return null;
		}
		byte[] init = new byte[32];

		// 指定种子
		init = "UvpaDEGNivozZkCDbKgSjwaYzEDUIuiouoNI".getBytes();
		List<String> wl = null;
		try {
			wl = mnemonicCode.toMnemonic(init);

		} catch (MnemonicException.MnemonicLengthException e) {
			e.printStackTrace();
		}
		if (wl == null) {
			System.out.println("未生成助记词");
			return null;
		}
		return wl;
	}

}
