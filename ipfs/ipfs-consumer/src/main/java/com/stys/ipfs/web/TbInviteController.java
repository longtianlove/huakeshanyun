package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.dto.TbInviteVO;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.entity.TbInvite;
import com.stys.ipfs.service.ITbInviteService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 邀请表 前端控制器
 * </p>
 *
 * @author dp
 * @since 2018-10-29
 */
@Controller
@RequestMapping("/tbInvite")
public class TbInviteController extends BaseController {

    @Reference(version = "1.0.0",check=false)
    private ITbInviteService itbInviteService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbInvite:view")
    public @ResponseBody
        ResultInfo<List<TbInviteVO>> listData(){ 
    	List<TbInviteVO> list=itbInviteService.getTreeNode(); 
    	 return new ResultInfo<>(list);
    	 
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbInvite:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbInvite tbInvite){
        boolean b = itbInviteService.insert(tbInvite);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbInvite:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbInviteService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbInvite:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbInvite tbInvite){
        boolean b = itbInviteService.updateById(tbInvite);
        return new ResultInfo<>(b);
    }

}

