package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import com.stys.ipfs.entity.TaskSchedule;
import com.stys.ipfs.service.ITaskScheduleService;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 系统任务调度表 前端控制器
 * </p>
 *
 * @author dp
 * @since 2018-10-25
 */
@Controller
@RequestMapping("/taskSchedule")
public class TaskScheduleController extends BaseController {

    @Reference(version = "1.0.0",check=false)
    private ITaskScheduleService itaskScheduleService;
    

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("taskSchedule:view")
    public @ResponseBody
        ResultInfo<List<TaskSchedule>> listData(TaskSchedule taskSchedule, Integer page, Integer limit){
        EntityWrapper<TaskSchedule> wrapper = new EntityWrapper<>(taskSchedule);
        Page<TaskSchedule> pageObj = itaskScheduleService.selectPage(new Page<TaskSchedule>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("taskSchedule:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TaskSchedule taskSchedule){
        boolean b = itaskScheduleService.insert(taskSchedule);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("taskSchedule:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itaskScheduleService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("taskSchedule:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TaskSchedule taskSchedule){
        boolean b = itaskScheduleService.updateById(taskSchedule);
        return new ResultInfo<>(b);
    }
    
    
    
    
    /**启动当前任务
     * @param idArr
     * @return
     */
    @RequestMapping("/resumeJob")
   public  @ResponseBody
   		ResultInfo<TaskSchedule> startJob(Integer id){
    	try {
			itaskScheduleService.resumeJob(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return new ResultInfo<TaskSchedule>("启动成功！");
   }
    /**暂停
     * @param idArr
     * @return
     */
    @RequestMapping("/stopJob")
    public  @ResponseBody
    ResultInfo<TaskSchedule> stopJob(Integer id){
 
    	try {
    		itaskScheduleService.pauseJob(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResultInfo<TaskSchedule>("暂停成功！");
    }
    /**
     * 运行一次
     * @param idArr
     * @return
     */
    @RequestMapping("/runOnceJob")
    public  @ResponseBody
    ResultInfo<TaskSchedule> runOnceJob(Integer id){
    	try {
    		itaskScheduleService.runOnceJob(id);	
		} catch (Exception e) {
			e.printStackTrace();
		}
    			
    	return new ResultInfo<TaskSchedule>("运行成功！");
    }
}

