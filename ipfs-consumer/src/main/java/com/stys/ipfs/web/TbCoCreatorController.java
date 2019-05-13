package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.entity.TbCoCreator;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.ITbCoCreatorService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-04-26
 */
@Controller
@RequestMapping("/tbCoCreator")
public class TbCoCreatorController extends BaseController {

    @Reference(version = "1.0.0")
    private ITbCoCreatorService itbCoCreatorService;
    
    @Reference(version = "1.0.0", check = false)
    private IAppUserService iappUserService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbCoCreator:view")
    public @ResponseBody
        ResultInfo<List<TbCoCreator>> listData(String realName,String phone, Integer page, Integer limit){
        Page<TbCoCreator> pageObj = itbCoCreatorService.selectPageTbCoCreator(new Page<TbCoCreator>(page,limit),realName,phone);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    /**
     * 	添加联合创始人
     * @param phone
     * @return
     */
    @RequestMapping("/add")
    @RequiresPermissions("tbCoCreator:add")
    public @ResponseBody
        ResultInfo<Boolean> add(String phone){
    	AppUser appuser=iappUserService.selectOne(new EntityWrapper<AppUser>().eq("phone", phone));
    	if(appuser==null) {
    		return new ResultInfo<>("1","输入的用户账号不存在！");
    	}
    	TbCoCreator tbCoCreator=new TbCoCreator ();
    	tbCoCreator.setUserId(appuser.getId());
    	boolean b = itbCoCreatorService.insert(tbCoCreator);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbCoCreator:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbCoCreatorService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbCoCreator:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbCoCreator tbCoCreator){
        boolean b = itbCoCreatorService.updateById(tbCoCreator);
        return new ResultInfo<>(b);
    }

}

