package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbPersonalInfo;
import com.stys.ipfs.service.ITbPersonalInfoService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Controller
@RequestMapping("/tbPersonalInfo")
public class TbPersonalInfoController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private ITbPersonalInfoService itbPersonalInfoService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbPersonalInfo:view")
    public @ResponseBody
        ResultInfo<List<TbPersonalInfo>> listData( String accont, String nickname,String realName, Integer page, Integer limit){
        Page<TbPersonalInfo> pageObj = itbPersonalInfoService.getPageTbPersonalInfo(new Page<TbPersonalInfo>(page,limit), accont, nickname, realName);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbPersonalInfo:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbPersonalInfo tbPersonalInfo){
        boolean b = itbPersonalInfoService.insert(tbPersonalInfo);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbPersonalInfo:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbPersonalInfoService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbPersonalInfo:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbPersonalInfo tbPersonalInfo){
        boolean b = itbPersonalInfoService.updateById(tbPersonalInfo);
        return new ResultInfo<>(b);
    }

}

