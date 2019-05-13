package com.stys.ipfs.config;

import org.apache.log4j.Logger;

import com.stys.ipfs.entity.TbUsdt;
import com.stys.ipfs.service.ITbUsdtService;
import com.stys.ipfs.util.TwoDimensionCode;
import com.stys.ipfs.util.usdt.CoinUtils;

public class UsdtThread implements Runnable {

	private Logger logger = Logger.getLogger(getClass());
	
	private ITbUsdtService itbUsdtService;
	
	

	public UsdtThread(ITbUsdtService itbUsdtService) {
		super();
		this.itbUsdtService = itbUsdtService;
	}

	@Override
	public void run() {
		executeModel();

	}

	private void executeModel() {
		TbUsdt tbUsdt = new TbUsdt();
		try {
			for (int i = 0; i < 5; i++) {
				String address = CoinUtils.getInstance().getNewaddress();
				String privateKey = CoinUtils.getInstance().dumpprivkey(address);
				tbUsdt.setStatus(0);
				tbUsdt.setUsdtBalance(0f);
				tbUsdt.setUsdtPrivatekey(privateKey);
				tbUsdt.setUsdtAddr(address);

				TwoDimensionCode.getEncode(address, "");
				itbUsdtService.insert(tbUsdt);
			}

		} catch (Throwable e) {

			logger.info("-----------错误 -------");
			e.printStackTrace();
		}
	}

}
