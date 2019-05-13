package com.stys.ipfs.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.service.ITbUsdtService;

public class MakeUSDTDataTask implements Job  {

	@Reference(version = "1.0.0", check = false)
	private ITbUsdtService itbUsdtService;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		itbUsdtService.makeUSDTData();
	}
}