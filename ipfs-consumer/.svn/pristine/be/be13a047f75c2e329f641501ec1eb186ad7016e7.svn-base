package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbContract;
import com.stys.ipfs.service.ITbContractService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-05-07
 */
@Controller
@RequestMapping("/tbContract")
public class TbContractController extends BaseController {

    @Reference(version = "1.0.0")
    private ITbContractService itbContractService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbContract:view")
    public @ResponseBody
        ResultInfo<List<TbContract>> listData(TbContract tbContract, Integer page, Integer limit){
        EntityWrapper<TbContract> wrapper = new EntityWrapper<>(tbContract);
        Page<TbContract> pageObj = itbContractService.selectPage(new Page<TbContract>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbContract:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbContract tbContract){
        boolean b = itbContractService.insert(tbContract);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbContract:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbContractService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbContract:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbContract tbContract){
        boolean b = itbContractService.updateById(tbContract);
        return new ResultInfo<>(b);
    }

}

