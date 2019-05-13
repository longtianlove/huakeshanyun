package com.stys.ipfs.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.entity.TbUserBuyLog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-01-14
 */
public interface ITbUserBuyLogService extends IService<TbUserBuyLog> {

	Page<TbUserBuyLog> selectPageTbUserBuyLog(Page<TbUserBuyLog> page, String nickname ,String phone);

	Float selectTodayBuyProfit(String currentDateTime);

}
