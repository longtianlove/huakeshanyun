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
import com.stys.ipfs.entity.TbSystemProfit;
import com.stys.ipfs.service.ITbSystemProfitService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-17
 */
@Controller
@RequestMapping("/tbSystemProfit")
public class TbSystemProfitController extends BaseController {

    @Reference(version = "1.0.0")
    private ITbSystemProfitService itbSystemProfitService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbSystemProfit:view")
    public @ResponseBody
        ResultInfo<List<TbSystemProfit>> listData(TbSystemProfit tbSystemProfit, Integer page, Integer limit){
        EntityWrapper<TbSystemProfit> wrapper = new EntityWrapper<>(tbSystemProfit);
        Page<TbSystemProfit> pageObj = itbSystemProfitService.selectPage(new Page<TbSystemProfit>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbSystemProfit:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbSystemProfit tbSystemProfit){
        boolean b = itbSystemProfitService.insert(tbSystemProfit);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbSystemProfit:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbSystemProfitService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbSystemProfit:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbSystemProfit tbSystemProfit){
        boolean b = itbSystemProfitService.updateById(tbSystemProfit);
        return new ResultInfo<>(b);
    }

}

