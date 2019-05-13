package com.stys.ipfs.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.dto.UserAssetDetailVO;
import com.stys.ipfs.entity.TbAssetsDetail;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface ITbAssetsDetailService extends IService<TbAssetsDetail> {

	/**
	 * 获取当前用户的账户明细
	 * 
	 * @param userId
	 * @return
	 */
	public Page<UserAssetDetailVO> getAessetForUser(Page<UserAssetDetailVO> page, Integer userId);

	/**
	 * 分页查询账户明细
	 * 
	 * @param page
	 * @param phone
	 * @param nickname
	 * @return
	 */
	Page<TbAssetsDetail> getPageTbAssetsDetail(Page<TbAssetsDetail> page, String phone, String nickname,
			String startTime, String endTime, Integer type, Integer accountType);

	/**
	 * 导出 excel
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<TbAssetsDetail> getListTbAssetsDetail(String startTime, String endTime);

	/**
	 *
	 * S3级别帐户享受所在系统内全球分红 2%奖励。每天分红一次 每天23点15分钟触发一次
	 */
	public void S3PlatformProfit();

	/**
	 * 超级旷工收益分红
	 */
	public void vipMinerPlatformProfit();
}
