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
import com.stys.ipfs.entity.TbBackfill;
import com.stys.ipfs.service.ITbBackfillService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-15
 */
@Controller
@RequestMapping("/tbBackfill")
public class TbBackfillController extends BaseController {

    @Reference(version = "1.0.0")
    private ITbBackfillService itbBackfillService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbBackfill:view")
    public @ResponseBody
        ResultInfo<List<TbBackfill>> listData(TbBackfill tbBackfill, Integer page, Integer limit){
        EntityWrapper<TbBackfill> wrapper = new EntityWrapper<>(tbBackfill);
        Page<TbBackfill> pageObj = itbBackfillService.selectPage(new Page<TbBackfill>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbBackfill:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbBackfill tbBackfill){
        boolean b = itbBackfillService.insert(tbBackfill);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbBackfill:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbBackfillService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbBackfill:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbBackfill tbBackfill){
        boolean b = itbBackfillService.updateById(tbBackfill);
        return new ResultInfo<>(b);
    }

}

