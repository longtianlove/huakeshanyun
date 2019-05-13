package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbUsdtlog;
import com.stys.ipfs.service.ITbUsdtlogService;
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
@RequestMapping("/tbUsdtlog")
public class TbUsdtlogController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private ITbUsdtlogService itbUsdtlogService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbUsdtlog:view")
    public @ResponseBody
        ResultInfo<List<TbUsdtlog>> listData(TbUsdtlog tbUsdtlog, Integer page, Integer limit){
        EntityWrapper<TbUsdtlog> wrapper = new EntityWrapper<>(tbUsdtlog);
        Page<TbUsdtlog> pageObj = itbUsdtlogService.selectPage(new Page<TbUsdtlog>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbUsdtlog:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbUsdtlog tbUsdtlog){
        boolean b = itbUsdtlogService.insert(tbUsdtlog);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbUsdtlog:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbUsdtlogService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbUsdtlog:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbUsdtlog tbUsdtlog){
        boolean b = itbUsdtlogService.updateById(tbUsdtlog);
        return new ResultInfo<>(b);
    }

}

