package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.TbPersonalInfo;
import com.stys.ipfs.mapper.TbPersonalInfoMapper;
import com.stys.ipfs.service.ITbPersonalInfoService;
import com.stys.ipfs.util.StringUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbPersonalInfoServiceImpl extends ServiceImpl<TbPersonalInfoMapper, TbPersonalInfo> implements ITbPersonalInfoService {

	@Override
	public Page<TbPersonalInfo> getPageTbPersonalInfo(Page<TbPersonalInfo> page, String accont, String nickname,
			String realName) {
		if(!StringUtils.isEmpty(nickname)) {
			nickname="%"+nickname+"%";
		}
		if(!StringUtils.isEmpty(realName)) {
			realName="%"+realName+"%";
		}
		page.setRecords(this.baseMapper.selectPageListTbPersonalInfo(page, accont, nickname, realName));
		return page;
	}

}
