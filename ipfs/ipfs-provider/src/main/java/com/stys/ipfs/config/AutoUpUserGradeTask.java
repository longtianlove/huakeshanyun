package com.stys.ipfs.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.service.IAppUserinfoService;

@Component
@Configurable
@EnableScheduling
public class AutoUpUserGradeTask {
	
	@Reference(version = "1.0.0", check = false)
	private IAppUserinfoService iAppUserinfoService;

	
	
	/**
	 * 自动升级
	 */
	@Scheduled(cron = "0 */11 * * * ?") 
	public void  upGrade() {
			iAppUserinfoService.upGared();
	}
	
	
}
