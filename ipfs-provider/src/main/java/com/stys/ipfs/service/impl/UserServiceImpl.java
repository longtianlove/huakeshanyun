package com.stys.ipfs.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.dto.UserInfo;
import com.stys.ipfs.entity.User;
import com.stys.ipfs.mapper.UserMapper;
import com.stys.ipfs.service.IUserService;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Service(version = "1.0.0", timeout = 60000)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
    public UserInfo findUserInfo(String userName) { 
        return this.baseMapper.findUserInfo(userName);
    }
 
	 
}