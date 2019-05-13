package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.service.IDicService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2018-10-24
 */
@Controller
@RequestMapping("/dic")
public class DicController extends BaseController {

    @Reference(version = "1.0.0",check=false)
    private IDicService idicService;

    @RequestMapping("/*")
    public void toHtml(){

    }
    
    @RequestMapping("/selectListData")
    @ResponseBody
    public ResultInfo<List<Dic>> selectListData(Dic dic){
        List<Dic> list = idicService.selectList(new EntityWrapper<>(dic));
        return new ResultInfo<>(list);
    }

    @RequestMapping("/listData")
    @RequiresPermissions("dic:view")
    public @ResponseBody
        ResultInfo<List<Dic>> listData(Dic dic, Integer page, Integer limit){
        EntityWrapper<Dic> wrapper = new EntityWrapper<>(dic);
        if (dic != null && dic.getName() != null) {
			wrapper.like("name", dic.getName());
			dic.setName(null);
		}
        Page<Dic> pageObj = idicService.selectPage(new Page<Dic>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("dic:add")
    public @ResponseBody
        ResultInfo<Boolean> add(Dic dic){
        boolean b = idicService.insert(dic);
        return new ResultInfo<>(b);
    }
    
    @RequestMapping("/save")
    @RequiresPermissions(value = {"dic:add", "dic:edit"}, logical = Logical.OR)
    public @ResponseBody
    ResultInfo<Boolean> save(Dic dic) {
        return new ResultInfo<>(idicService.insertOrUpdate(dic));  
    }
    
    

    @RequestMapping("/delBatch")
    @RequiresPermissions("dic:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = idicService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("dic:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(Dic dic){
        boolean b = idicService.updateById(dic);
        return new ResultInfo<>(b);
    }

}

