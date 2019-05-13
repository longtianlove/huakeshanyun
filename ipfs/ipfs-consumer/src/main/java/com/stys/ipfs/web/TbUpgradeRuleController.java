package com.stys.ipfs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbUpgradeRule;
import com.stys.ipfs.service.ITbUpgradeRuleService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-29
 */
@Controller
@RequestMapping("/tbUpgradeRule")
public class TbUpgradeRuleController extends BaseController {

	@Reference(version = "1.0.0",check=false)
    private ITbUpgradeRuleService itbUpgradeRuleService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbUpgradeRule:view")
    public @ResponseBody
        ResultInfo<List<TbUpgradeRule>> listData(TbUpgradeRule tbUpgradeRule, Integer page, Integer limit){
        EntityWrapper<TbUpgradeRule> wrapper = new EntityWrapper<>(tbUpgradeRule);
        Page<TbUpgradeRule> pageObj = itbUpgradeRuleService.selectPage(new Page<TbUpgradeRule>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbUpgradeRule:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbUpgradeRule tbUpgradeRule){
        boolean b = itbUpgradeRuleService.insert(tbUpgradeRule);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbUpgradeRule:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbUpgradeRuleService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbUpgradeRule:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbUpgradeRule tbUpgradeRule){
        boolean b = itbUpgradeRuleService.updateById(tbUpgradeRule);
        return new ResultInfo<>(b);
    }

}

