package com.stys.ipfs.service;

import com.stys.ipfs.entity.TbPersonalInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface ITbPersonalInfoService extends IService<TbPersonalInfo> {

	
	/**
	 *	分页查询实名认证信息
	 * @param page
	 * @param accont
	 * @param nickname
	 * @param realName
	 * @return
	 */
	public	Page<TbPersonalInfo>  getPageTbPersonalInfo(Page<TbPersonalInfo> page,String accont,String nickname,String realName);
}
