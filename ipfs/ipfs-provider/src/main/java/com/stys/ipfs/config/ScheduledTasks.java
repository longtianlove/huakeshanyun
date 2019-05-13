package com.stys.ipfs.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbTransferUsdtService;
import com.stys.ipfs.service.ITbUsdtService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.util.DateUtil;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;

	@Reference(version = "1.0.0", check = false)
	private ITbUsdtService itbUsdtService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbUserAssetsService itbUserAssetsService;

	@Reference(version = "1.0.0", check = false)
	private ITbTransferUsdtService itbTransferUsdtService;

	@Reference(version = "1.0.0",check = false)
	private ITbAssetsDetailService itbAssetsDetailService;

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 延迟30s执行
	 */
 //	@Scheduled(fixedRate = 1000 * 3)
	public void reportCurrentTime() {

	  logger.info("USDT----start:" + DateUtil.getCurrentDateTime());
	} 

	/**
	 * 每分钟执行 * / 3 * * * *
	 */
	@Scheduled(cron = "0 */33 * * * ?") 
	public void reportCurrentByCron() {
		 new UsdtListenThread(itbUsdtService, iappUserService, itbUserAssetsService,
				itbTransferUsdtService, idicService, itbAssetsDetailService).run();
		 
	}

}