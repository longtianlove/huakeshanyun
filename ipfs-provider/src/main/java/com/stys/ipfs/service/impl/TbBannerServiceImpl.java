package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.TbBanner;
import com.stys.ipfs.mapper.TbBannerMapper;
import com.stys.ipfs.service.ITbBannerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 广告信息管理表 服务实现类
 * </p>
 *
 * @author dp
 * @since 2018-10-29
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbBannerServiceImpl extends ServiceImpl<TbBannerMapper, TbBanner> implements ITbBannerService {

}
