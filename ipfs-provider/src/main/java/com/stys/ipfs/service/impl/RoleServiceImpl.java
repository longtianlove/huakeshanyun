package com.stys.ipfs.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.entity.Role;
import com.stys.ipfs.mapper.RoleMapper;
import com.stys.ipfs.service.IRoleService;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Service(version = "1.0.0", timeout = 60000)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    public Boolean saveRole(Role role) {
        Boolean res = false;
        if (role.getId() == null) {
            res = this.insert(role);
        } else {
            res = this.updateById(role);
        }
        return res;
    }
 
}