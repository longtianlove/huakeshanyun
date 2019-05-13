package com.stys.ipfs.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.entity.TbFeimaWithdrawadLog;
import com.stys.ipfs.mapper.TbFeimaWithdrawadLogMapper;
import com.stys.ipfs.service.ITbFeimaWithdrawadLogService;
import com.stys.ipfs.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-05-05
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbFeimaWithdrawadLogServiceImpl extends ServiceImpl<TbFeimaWithdrawadLogMapper, TbFeimaWithdrawadLog>
		implements ITbFeimaWithdrawadLogService {

	@Override
	public Page<TbFeimaWithdrawadLog> getPageTbFeimaWithdrawadLog(Page<TbFeimaWithdrawadLog> page, String phone,
			String realName, String startTime, String endTime) {
		if (!StringUtils.isEmpty(realName)) {
			realName = "%" + realName + "%";
		}
		if (!StringUtils.isEmpty(realName)) {
			realName = "%" + realName + "%";
		}
		page.setRecords(this.baseMapper.selectTbFeimaWithdrawadLog(page, realName, phone, startTime, endTime));
		return page;
	}

}
