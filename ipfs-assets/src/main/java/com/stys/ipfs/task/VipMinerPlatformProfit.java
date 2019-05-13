package com.stys.ipfs.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.service.ITbAssetsDetailService;

public class VipMinerPlatformProfit implements Job {

	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService iTbAssetsDetailService;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		iTbAssetsDetailService.vipMinerPlatformProfit();
	}
}