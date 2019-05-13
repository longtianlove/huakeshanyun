package com.stys.ipfs.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbUserBuyLog;
import com.stys.ipfs.service.ITbUserBuyLogService;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-01-14
 */
@Controller
@RequestMapping("/tbUserBuyLog")
public class TbUserBuyLogController {
	

    @Reference(version = "1.0.0", check = false)
    private ITbUserBuyLogService itbUserBuyLogService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbUserBuyLog:view")
    public @ResponseBody
        ResultInfo<List<TbUserBuyLog>> listData(String nickname,String phone, Integer page, Integer limit){
        Page<TbUserBuyLog> pageObj = itbUserBuyLogService.selectPageTbUserBuyLog(new Page<TbUserBuyLog>(page, limit),nickname,phone);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    	 
    }
}

