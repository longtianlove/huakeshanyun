package com.stys.ipfs.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.entity.TbUsdtlog;
import com.stys.ipfs.mapper.TbUsdtlogMapper;
import com.stys.ipfs.service.ITbUsdtlogService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbUsdtlogServiceImpl extends ServiceImpl<TbUsdtlogMapper, TbUsdtlog> implements ITbUsdtlogService {

	@Override
	public List<TbUsdtlog> selectUsdtlog(Integer userId) {
		 
		return  this.baseMapper.selectUsdtlog(userId); 
	}

}
