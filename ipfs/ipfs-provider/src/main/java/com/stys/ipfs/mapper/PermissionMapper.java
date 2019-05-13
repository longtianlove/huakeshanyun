package com.stys.ipfs.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stys.ipfs.dto.PermissionInfo;
import com.stys.ipfs.entity.Permission;

/**
 * <p>
 * 系统权限表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionInfo> allPermissionInfo();

}
