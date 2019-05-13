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
import com.stys.ipfs.entity.TbTransferUsdt;
import com.stys.ipfs.service.ITbTransferUsdtService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-15
 */
@Controller
@RequestMapping("/tbTransferUsdt")
public class TbTransferUsdtController extends BaseController {

    @Reference(version = "1.0.0")
    private ITbTransferUsdtService itbTransferUsdtService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbTransferUsdt:view")
    public @ResponseBody
        ResultInfo<List<TbTransferUsdt>> listData(TbTransferUsdt tbTransferUsdt, Integer page, Integer limit){
        EntityWrapper<TbTransferUsdt> wrapper = new EntityWrapper<>(tbTransferUsdt);
        Page<TbTransferUsdt> pageObj = itbTransferUsdtService.selectPage(new Page<TbTransferUsdt>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbTransferUsdt:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbTransferUsdt tbTransferUsdt){
        boolean b = itbTransferUsdtService.insert(tbTransferUsdt);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbTransferUsdt:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbTransferUsdtService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbTransferUsdt:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbTransferUsdt tbTransferUsdt){
        boolean b = itbTransferUsdtService.updateById(tbTransferUsdt);
        return new ResultInfo<>(b);
    }

}

