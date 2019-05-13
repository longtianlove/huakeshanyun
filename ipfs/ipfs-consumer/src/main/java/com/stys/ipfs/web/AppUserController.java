package com.stys.ipfs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.service.IAppUserService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/appUser")
public class AppUserController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private IAppUserService iappUserService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("appUser:view")
    public @ResponseBody
        ResultInfo<List<AppUser>> listData(AppUser appUser, Integer page, Integer limit){
        EntityWrapper<AppUser> wrapper = new EntityWrapper<>(appUser);
        Page<AppUser> pageObj = iappUserService.selectPage(new Page<AppUser>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("appUser:add")
    public @ResponseBody
        ResultInfo<Boolean> add(AppUser appUser){
        boolean b = iappUserService.insert(appUser);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("appUser:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = iappUserService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("appUser:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(AppUser appUser){
        boolean b = iappUserService.updateById(appUser);
        return new ResultInfo<>(b);
    }

}

