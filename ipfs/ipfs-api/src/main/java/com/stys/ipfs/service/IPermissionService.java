package com.stys.ipfs.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.dto.PermissionInfo;
import com.stys.ipfs.entity.Permission;

/**
 * <p>
 * 系统权限表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface IPermissionService extends IService<Permission> {

     List<Permission> getAllPermissions();

     Boolean savePermission(Permission permission);

     Boolean delBatchPermission(List<Integer> ids);

     List<PermissionInfo> allPermissionInfo();

     List<Permission> getMenuPermissions(String code);

     List<Permission> getAllDirectoryPermissions();

}