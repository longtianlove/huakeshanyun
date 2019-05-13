package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbUsdt;
import com.stys.ipfs.service.ITbUsdtService;
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
@RequestMapping("/tbUsdt")
public class TbUsdtController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private ITbUsdtService itbUsdtService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbUsdt:view")
    public @ResponseBody
        ResultInfo<List<TbUsdt>> listData(TbUsdt tbUsdt, Integer page, Integer limit){
        EntityWrapper<TbUsdt> wrapper = new EntityWrapper<>(tbUsdt);
        Page<TbUsdt> pageObj = itbUsdtService.selectPage(new Page<TbUsdt>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbUsdt:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbUsdt tbUsdt){
        boolean b = itbUsdtService.insert(tbUsdt);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbUsdt:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbUsdtService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbUsdt:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbUsdt tbUsdt){
        boolean b = itbUsdtService.updateById(tbUsdt);
        return new ResultInfo<>(b);
    }

}

