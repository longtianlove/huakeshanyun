package com.stys.ipfs.service;

import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.entity.TaskSchedule;

/**
 * <p>
 * 系统任务调度表 服务类
 * </p>
 *
 * @author dp
 * @since 2018-10-25
 */
public interface ITaskScheduleService extends IService<TaskSchedule> {
	
	public void scheduleJobs() throws Exception;
	
	/**暂停任务
	 * @param id
	 * @throws Exception
	 */
	public void pauseJob(int id) throws Exception;
	
	/**恢复任务
	 * @param id
	 * @throws Exception
	 */
	public void resumeJob(int id) throws Exception;
	
	/**启动所有任务
	 * @param scheduler
	 */
	public void startJob ();
	
	/**运行一次
	 * @param id
	 * @throws Exception
	 */
	public void runOnceJob(int id) throws Exception;
	}
