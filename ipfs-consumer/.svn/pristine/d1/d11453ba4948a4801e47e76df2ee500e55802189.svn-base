package com.stys.ipfs.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.dto.UserExperienceVO;
import com.stys.ipfs.entity.TbUserExperience;
import com.stys.ipfs.service.ITbUserExperienceService;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-01-13
 */
@Controller
@RequestMapping("/tbUserExperience")
public class TbUserExperienceController {
    @Reference(version = "1.0.0",check=false)
    private ITbUserExperienceService itbUserExperienceService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbUserExperience:view")
    public @ResponseBody
        ResultInfo<List<UserExperienceVO>> listData(String nickname,Integer page, Integer limit){
        EntityWrapper<UserExperienceVO> wrapper = new EntityWrapper<UserExperienceVO>();
        Page<UserExperienceVO> pageObj = itbUserExperienceService.selectPageUserExperienceVO(new Page<UserExperienceVO>(page,limit),wrapper,nickname);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbUserExperience:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbUserExperience tbUserExperience){
        boolean b = itbUserExperienceService.insert(tbUserExperience);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbUserExperience:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbUserExperienceService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbUserExperience:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbUserExperience tbUserExperience){
        boolean b = itbUserExperienceService.updateById(tbUserExperience);
        return new ResultInfo<>(b);
    }
}

