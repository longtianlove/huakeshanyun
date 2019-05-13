package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.TbFeimauser;
import com.stys.ipfs.mapper.TbFeimauserMapper;
import com.stys.ipfs.service.ITbFeimauserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-04-15
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbFeimauserServiceImpl extends ServiceImpl<TbFeimauserMapper, TbFeimauser> implements ITbFeimauserService {

}
