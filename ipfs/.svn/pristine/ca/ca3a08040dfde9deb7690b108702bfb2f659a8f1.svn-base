package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.TaskSchedule;
import com.stys.ipfs.mapper.TaskScheduleMapper;
import com.stys.ipfs.service.ITaskScheduleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 系统任务调度表 服务实现类
 * </p>
 *
 * @author dp
 * @since 2018-10-25
 */
@Service(version = "1.0.0", timeout = 60000)
public class TaskScheduleServiceImpl extends ServiceImpl<TaskScheduleMapper, TaskSchedule>
		implements ITaskScheduleService {
	@Reference(version = "1.0.0", check = false)
	private	ITaskScheduleService iTaskScheduleService;
	
    @Autowired
     SchedulerFactoryBean schedulerFactoryBean;
	

   
    public void scheduleJobs() throws Exception {
        startJob();
    }

    // 获取scheduler
    private Scheduler getScheduler(){
       return schedulerFactoryBean.getScheduler();
    }
	   // 项目启动 开启任务
    @SuppressWarnings("unchecked")
	public void startJob() {
    	 Scheduler scheduler = getScheduler();
        try {
            List<TaskSchedule> jobList = iTaskScheduleService.selectList(new EntityWrapper<TaskSchedule>());
            for (TaskSchedule taskschedule : jobList) {
                // 1-启用任务   0-暂停 项目启动时启动所有的启用任务
                if (0==taskschedule.getStatus()){
                    continue;
                }
                Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(taskschedule.getJobClass());
                JobDetail jobDetail = JobBuilder.newJob(clazz)
                        .withIdentity(taskschedule.getJobName(), taskschedule.getJobGroup()).build();
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskschedule.getCronexpression());
                CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(taskschedule.getJobName(), taskschedule.getJobGroup())
                        .withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, cronTrigger);
                taskschedule.setStatus(1);
                //更新数据库
                iTaskScheduleService.updateById(taskschedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
	// 任务暂停
    public void pauseJob(int id) throws Exception {
        Scheduler scheduler = getScheduler();
        TaskSchedule taskschedule = iTaskScheduleService.selectById(id);
        JobKey jobKey = JobKey.jobKey(taskschedule.getJobName(), taskschedule.getJobGroup());
        scheduler.deleteJob(jobKey);
        taskschedule.setStatus(0);
        //更新数据库
        iTaskScheduleService.updateById(taskschedule);
    }
    // 运行一次
    @SuppressWarnings("unchecked")
    public void runOnceJob(int id) throws Exception {
    	 Scheduler scheduler = getScheduler();
         TaskSchedule taskschedule = iTaskScheduleService.selectById(id);
         JobKey jobKey = JobKey.jobKey(taskschedule.getJobName(), taskschedule.getJobGroup());
         JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
         if(jobDetail1!=null) {
        	 scheduler.triggerJob(jobKey);
         }else {
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(taskschedule.getJobClass());
        	 JobDetail jobDetail = JobBuilder.newJob(clazz)
                     .withIdentity(taskschedule.getJobName(), taskschedule.getJobGroup()).build();
             CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskschedule.getCronexpression());
             CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(taskschedule.getJobName(), taskschedule.getJobGroup())
                     .withSchedule(scheduleBuilder).build();
             scheduler.scheduleJob(jobDetail, cronTrigger);
             scheduler.triggerJob(jobKey);//立即执行一次
             Thread.sleep(1000);//运行1秒后删除任务
             scheduler.deleteJob(jobKey);
         }
    }

    // 任务恢复
    @SuppressWarnings("unchecked")
    public void resumeJob(int id) throws Exception{
        Scheduler scheduler = getScheduler();
        TaskSchedule taskschedule = iTaskScheduleService.selectById(id);
        JobKey jobKey = JobKey.jobKey(taskschedule.getJobName(), taskschedule.getJobGroup());
		Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(taskschedule.getJobClass());
        JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
        if (jobDetail1==null){
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(taskschedule.getJobName(), taskschedule.getJobGroup()).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskschedule.getCronexpression());
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(taskschedule.getJobName(), taskschedule.getJobGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }else {
            scheduler.resumeJob(jobKey);
        }
        taskschedule.setStatus(1);
        //更新数据库
        iTaskScheduleService.updateById(taskschedule);
    }
    
    
}
