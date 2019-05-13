package com.stys.ipfs.service;

import com.stys.ipfs.entity.TbVipminer;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-17
 */
public interface ITbVipminerService extends IService<TbVipminer> {

	/**
	 *  分页查询账户明细
	 * @param page
	 * @param phone
	 * @param nickname
	 * @return
	 */
	public	Page<TbVipminer>  getPageTbVipminer(Page<TbVipminer> page,String nickname);
}
