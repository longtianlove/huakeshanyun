package com.stys.ipfs.service;

import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.TbWithdrawadLog;



import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-13
 */
public interface ITbWithdrawadLogService extends IService<TbWithdrawadLog> {
	
	/**
	 *  金币提现申请
	 * @param userId
	 * @param cashNumber
	 * @return
	 */
	public AppResultInfo<?> withdraw_cash(Integer userId,Double cashNumber);
	
	Page<TbWithdrawadLog> selectPageTbWithdrawadLog(Page<TbWithdrawadLog> page, String phone,String cashStatus,String userName);
}
