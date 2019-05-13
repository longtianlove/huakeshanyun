package com.stys.ipfs.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.entity.TbFeimaWithdrawadLog;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dp
 * @since 2019-05-05
 */
public interface ITbFeimaWithdrawadLogService extends IService<TbFeimaWithdrawadLog> {

	Page<TbFeimaWithdrawadLog> getPageTbFeimaWithdrawadLog(Page<TbFeimaWithdrawadLog> page, String phone,
			String realName, String startTime, String endTime);

}
