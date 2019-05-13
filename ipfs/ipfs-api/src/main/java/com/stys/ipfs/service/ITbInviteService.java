package com.stys.ipfs.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.dto.TbInviteVO;
import com.stys.ipfs.entity.TbInvite;

/**
 * <p>
 * 邀请表 服务类
 * </p>
 *
 * @author dp
 * @since 2018-10-29
 */

public interface ITbInviteService extends IService<TbInvite> {
	
	/**
	 * 	邀请详细列表
	 * @return
	 */
	List<TbInviteVO> getTreeNode();
	
	/**
	 * 	获取邀请人的父节点
	 * @return
	 */
	List<TbInviteVO> getParentNode();
	/**
	 *  我邀请的人
	 * @return
	 */
	List<TbInviteVO> getMyInvite(Integer userId);
	
	public TbInvite getTbInviteByTb_iviter(Integer tb_iviter);
}
