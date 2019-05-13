package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.AppLoginLog;
import com.stys.ipfs.mapper.AppLoginLogMapper;
import com.stys.ipfs.service.IAppLoginLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 系统登录日志表 服务实现类
 * </p>
 *
 * @author dp
 * @since 2018-11-05
 */
@Service(version = "1.0.0", timeout = 60000)
public class AppLoginLogServiceImpl extends ServiceImpl<AppLoginLogMapper, AppLoginLog> implements IAppLoginLogService {

}
