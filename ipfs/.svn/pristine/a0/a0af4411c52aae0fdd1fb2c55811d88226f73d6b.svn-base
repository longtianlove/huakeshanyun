package com.stys.ipfs.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.stys.ipfs.dto.MenuInfo;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.dto.RoleInfo;
import com.stys.ipfs.dto.UserInfo;
import com.stys.ipfs.entity.Permission;
import com.stys.ipfs.service.IPermissionService;

/**
 * 菜单控制器
 *
 * @author dp
 * @since 2018-07-16
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

    @Reference(version = "1.0.0",check=false)
    private IPermissionService iPermissionService;

    @RequestMapping("/left")
    @ResponseBody
    public ResultInfo<List<MenuInfo>> getMenuInfoList(@RequestParam("code") String code){
        List<MenuInfo> menuInfoList = new ArrayList<>();
        //获取当前用户角色信息
        UserInfo userInfo = this.getUserInfo();
        RoleInfo roleInfo = userInfo.getRoleInfo();
        if (roleInfo == null || StringUtils.isEmpty(roleInfo.getPermissionIds())){
            return new ResultInfo<>(menuInfoList);
        }
        //获取权限菜单信息
        List<Permission> permissionList = iPermissionService.getMenuPermissions(code);
        if (permissionList == null || permissionList.isEmpty()){
            return new ResultInfo<>(menuInfoList);
        }
        //获得有权限访问菜单
        for (Permission ps : permissionList) {
            if(roleInfo.getPermissionIds().contains(","+ps.getId()+",")){
                MenuInfo menuInfo = new MenuInfo();
                menuInfo.setTitle(ps.getPermissionName());
                menuInfo.setHref(ps.getUrl());
                menuInfoList.add(menuInfo);
            }
        }
        return new ResultInfo<>(menuInfoList);
    }

}