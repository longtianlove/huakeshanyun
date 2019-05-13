package com.stys.ipfs.web;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.AppLoginLog;
import com.stys.ipfs.service.IAppLoginLogService;

/**
 * <p>
 * app用户登录日志表 前端控制器
 * </p>
 *
 * @author dp
 * @since 2018-11-05
 */
@Controller
@RequestMapping("/appLoginLog")
public class AppLoginLogController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private IAppLoginLogService iappLoginLogService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("appLoginLog:view")
    public @ResponseBody
        ResultInfo<List<AppLoginLog>> listData(AppLoginLog appLoginLog, Integer page, Integer limit){
        EntityWrapper<AppLoginLog> wrapper = new EntityWrapper<>(appLoginLog);
        Page<AppLoginLog> pageObj = iappLoginLogService.selectPage(new Page<AppLoginLog>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("appLoginLog:add")
    public @ResponseBody
        ResultInfo<Boolean> add(AppLoginLog appLoginLog){
        boolean b = iappLoginLogService.insert(appLoginLog);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("appLoginLog:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = iappLoginLogService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("appLoginLog:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(AppLoginLog appLoginLog){
        boolean b = iappLoginLogService.updateById(appLoginLog);
        return new ResultInfo<>(b);
    }

}

