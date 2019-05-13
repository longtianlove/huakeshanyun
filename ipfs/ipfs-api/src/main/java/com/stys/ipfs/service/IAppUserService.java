package com.stys.ipfs.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.dto.TbUsdtVO;
import com.stys.ipfs.entity.AppUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-11
 */
public interface IAppUserService extends IService<AppUser> {

	List<TbUsdtVO> selectUSDTs();

	TbUsdtVO selectUserIdByUSDTAddress(String USDTAddress);

	boolean updateStatus(int user_id, int status);  
	
}
