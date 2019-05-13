package com.stys.ipfs.service;


import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.TbUserAssets;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface ITbUserAssetsService extends IService<TbUserAssets> {

	
	/** 
	 * 金币划转
	 * @param ownerUserId  自己Id
	 * @param phone 账号
	 * @param balance	金额
	 * @return
	 */
	public  AppResultInfo<?> transfer(Integer ownerUserId, String phone, Float balance);


}
