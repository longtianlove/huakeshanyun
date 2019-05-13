package com.stys.ipfs.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.code.ResourceType;
import com.stys.ipfs.dto.EnumInfo;
import com.stys.ipfs.dto.PermissionInfo;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.Permission;
import com.stys.ipfs.service.IPermissionService;

/**
 * <p>
 * 系统权限表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-10
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Reference(version = "1.0.0",check=false)
    private IPermissionService iPermissionService;

    @RequestMapping("/*")
    public void toHtml() {

    }

    @RequestMapping("/listData")
    @RequiresPermissions("permission:view")
    public @ResponseBody
    ResultInfo<List<Permission>> listData() {
        return new ResultInfo<>(iPermissionService.getAllPermissions());
    }

    @RequestMapping("/save")
    @RequiresPermissions(value = {"permission:add", "permission:edit"}, logical = Logical.OR)
    public @ResponseBody
    ResultInfo<Boolean> save(Permission permission) {
        return new ResultInfo<>(iPermissionService.savePermission(permission));
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("permission:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr) {
        return new ResultInfo<>(iPermissionService.delBatchPermission(Arrays.asList(idArr)));
    }

    @RequestMapping("/typeSelectData")
    public @ResponseBody
    ResultInfo<List<EnumInfo>> typeSelectData() {
        return new ResultInfo<>(ResourceType.getAllEnumInfo());
    }

    @RequestMapping("/parentSelectData")
    public @ResponseBody
    ResultInfo<List<Permission>> parentSelectData(String resourceType) {
        List<Permission> list = new ArrayList<>();
        if (resourceType.equals(ResourceType.DIRECTORY.getCode())) {
            return new ResultInfo<>(list);
        } else if (resourceType.equals(ResourceType.MENU.getCode())) {
            List<Permission> allPermissions = iPermissionService.getAllPermissions();
            for (Permission p : allPermissions) {
                if (p.getResourceType().equals(ResourceType.DIRECTORY.getCode())) {
                    list.add(p);
                }
            }
        } else if (resourceType.equals(ResourceType.BUTTON.getCode())) {
            List<Permission> allPermissions = iPermissionService.getAllPermissions();
            for (Permission p : allPermissions) {
                if (p.getResourceType().equals(ResourceType.MENU.getCode())) {
                    list.add(p);
                }
            }
        }
        return new ResultInfo<>(list);
    }

    @RequestMapping("/xtreeData")
    public @ResponseBody
    ResultInfo<List<PermissionInfo>> xtreeData() {
        return new ResultInfo<>(iPermissionService.allPermissionInfo());
    }

}