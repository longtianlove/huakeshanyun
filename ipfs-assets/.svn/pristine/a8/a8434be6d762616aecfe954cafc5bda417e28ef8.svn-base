package com.stys.ipfs.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbCoCreatorService;

/**
 * 	联合创始人分红定时任务
 * @author Administrator
 *
 */
public class Co_creatorProfitShareTask implements Job {

	@Reference(version = "1.0.0", check = false)
	private ITbCoCreatorService itbCoCreatorService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		itbCoCreatorService.Co_CreatorProfitshare();
	}

}
