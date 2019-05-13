package com.stys.ipfs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.service.ITaskScheduleService;

/**
 * 定时任务运行工厂类
 * Created by jinyu on 2018/4/12/012.
 */
@Configuration
public class StartSchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
	@Reference(version = "1.0.0", check = false)
	private	ITaskScheduleService iTaskScheduleService;
    // springboot 启动监听
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
        	iTaskScheduleService.scheduleJobs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
