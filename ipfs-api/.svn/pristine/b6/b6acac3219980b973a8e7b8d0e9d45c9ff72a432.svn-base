package com.stys.ipfs.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.dto.UserAssetDetailVO;
import com.stys.ipfs.entity.TbAssetsDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface ITbAssetsDetailService extends IService<TbAssetsDetail> {
	
	/**
	 * 获取当前用户的账户明细
	 * @param userId
	 * @return
	 */
	public Page<UserAssetDetailVO>  getAessetForUser(Page<UserAssetDetailVO> page, Integer userId);
	
	
	/**
	 *  分页查询账户明细
	 * @param page
	 * @param phone
	 * @param nickname
	 * @return
	 */
	Page<TbAssetsDetail> getPageTbAssetsDetail(Page<TbAssetsDetail> page, String phone, String nickname,
			String startTime, String endTime, Integer type);
	
	/**
	 *	导出	excel
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<TbAssetsDetail> getListTbAssetsDetail(String startTime,String endTime);


	 
}
