package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbUserAssets;
import com.stys.ipfs.service.ITbUserAssetsService;
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
@RequestMapping("/tbUserAssets")
public class TbUserAssetsController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private ITbUserAssetsService itbUserAssetsService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbUserAssets:view")
    public @ResponseBody
        ResultInfo<List<TbUserAssets>> listData(TbUserAssets tbUserAssets, Integer page, Integer limit){
        EntityWrapper<TbUserAssets> wrapper = new EntityWrapper<>(tbUserAssets);
        Page<TbUserAssets> pageObj = itbUserAssetsService.selectPage(new Page<TbUserAssets>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbUserAssets:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbUserAssets tbUserAssets){
        boolean b = itbUserAssetsService.insert(tbUserAssets);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbUserAssets:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbUserAssetsService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbUserAssets:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbUserAssets tbUserAssets){
        boolean b = itbUserAssetsService.updateById(tbUserAssets);
        return new ResultInfo<>(b);
    }

}

