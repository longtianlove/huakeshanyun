package com.stys.ipfs.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.entity.TbUserBuyLog;
import com.stys.ipfs.mapper.TbUserBuyLogMapper;
import com.stys.ipfs.service.ITbUserBuyLogService;
import com.stys.ipfs.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 * 
 * @apiUse dp
 * @author dp
 * @since 2019-01-14
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbUserBuyLogServiceImpl extends ServiceImpl<TbUserBuyLogMapper, TbUserBuyLog>
		implements ITbUserBuyLogService {

	@Override
	public Page<TbUserBuyLog> selectPageTbUserBuyLog(Page<TbUserBuyLog> page, String nickname, String phone) {
		EntityWrapper<TbUserBuyLog> wrapper = new EntityWrapper<TbUserBuyLog>();
		SqlHelper.fillWrapper(page, wrapper);
		if (!StringUtils.isEmpty(nickname)) {
			nickname = "%" + nickname + "%";
		}
		page.setRecords(this.baseMapper.selectPageTbUserBuyLog(page, nickname, phone));
		return page;
	}

	@Override
	public Float selectTodayBuyProfit(String currentDateTime) {

		Float reuslt = this.baseMapper.selectTodayBuyProfit(currentDateTime);
		if (null == reuslt) {
			return 0f;
		}
		return reuslt;
	}

}
