package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbProduct;
import com.stys.ipfs.service.ITbProductService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-09
 */
@Controller
@RequestMapping("/tbProduct")
public class TbProductController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private ITbProductService itbProductService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbProduct:view")
    public @ResponseBody
        ResultInfo<List<TbProduct>> listData(TbProduct tbProduct, Integer page, Integer limit){
        EntityWrapper<TbProduct> wrapper = new EntityWrapper<>(tbProduct);
        Page<TbProduct> pageObj = itbProductService.selectPage(new Page<TbProduct>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbProduct:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbProduct tbProduct){
        boolean b = itbProductService.insert(tbProduct);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbProduct:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbProductService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbProduct:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbProduct tbProduct){
        boolean b = itbProductService.updateById(tbProduct);
        return new ResultInfo<>(b);
    }
    
    @RequestMapping("/selectData")
	public @ResponseBody List<TbProduct> selectData() {
		return itbProductService.selectList(new EntityWrapper<TbProduct>().eq("status", 1)); 
	}
}
