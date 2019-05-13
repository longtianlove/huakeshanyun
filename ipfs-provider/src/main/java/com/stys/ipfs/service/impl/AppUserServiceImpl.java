package com.stys.ipfs.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.dto.TbUsdtVO;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.mapper.AppUserMapper;
import com.stys.ipfs.service.IAppUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-11
 */
@Service(version = "1.0.0", timeout = 60000)
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

	@Override
	public List<TbUsdtVO> selectUSDTs() {
		return this.baseMapper.selectUSDTs();

	}

	@Override
	public TbUsdtVO selectUserIdByUSDTAddress(String USDTAddress) {

		return this.baseMapper.selectUserIdByUSDTAddress(USDTAddress);
	}

	@Override
	public boolean updateStatus(int user_id, int status) {
		 
		return this.baseMapper.updateStatus( user_id, status);
	}

}
