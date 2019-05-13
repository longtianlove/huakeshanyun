package com.stys.ipfs.web;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.Role;
import com.stys.ipfs.entity.User;
import com.stys.ipfs.service.IRoleService;
import com.stys.ipfs.service.IUserService;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-10
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Reference(version = "1.0.0",check=false)
    private IRoleService iRoleService;

    @Reference(version = "1.0.0",check=false)
    private IUserService iUserService;

    @RequestMapping("/*")
    public void toHtml() {

    }

    @RequestMapping("/selectListData")
    @ResponseBody
    public ResultInfo<List<Role>> selectListData(Role role){
        List<Role> list = iRoleService.selectList(new EntityWrapper<>(role));
        return new ResultInfo<>(list);
    }

    @RequestMapping("/listData")
    @RequiresPermissions("role:view")
    public @ResponseBody
    ResultInfo<List<Role>> listData(Role role, Integer page, Integer limit) {
        EntityWrapper<Role> wrapper = new EntityWrapper<Role>(role);
        if (role != null && role.getRoleCode() != null) {
            wrapper.like("role_code", role.getRoleCode());
            role.setRoleCode(null);
        }
        if (role != null && role.getRoleName() != null) {
            wrapper.like("role_name", role.getRoleName());
            role.setRoleName(null);
        }
        Page<Role> pageObj = iRoleService.selectPage(new Page<Role>(page, limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/save")
    @RequiresPermissions(value = {"role:add", "role:edit"}, logical = Logical.OR)
    public @ResponseBody
    ResultInfo<Boolean> save(Role role) {
        return new ResultInfo<>(iRoleService.saveRole(role)); 
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("role:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.in("role_id", idArr);
        List<User> userList = iUserService.selectList(wrapper);
        if(userList!=null && userList.size()>0){
            return new ResultInfo<>("用户拥有角色不能删除！");
        }
        return new ResultInfo<>(iRoleService.deleteBatchIds(Arrays.asList(idArr)));
    }

}
