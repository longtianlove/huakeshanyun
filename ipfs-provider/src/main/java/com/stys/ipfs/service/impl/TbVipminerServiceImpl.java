package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.TbVipminer;
import com.stys.ipfs.mapper.TbVipminerMapper;
import com.stys.ipfs.service.ITbVipminerService;
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
 * @since 2019-03-17
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbVipminerServiceImpl extends ServiceImpl<TbVipminerMapper, TbVipminer> implements ITbVipminerService {

	@Override
	public Page<TbVipminer> getPageTbVipminer(Page<TbVipminer> page, String nickname) {
		if(!StringUtils.isEmpty(nickname)) {
			nickname="%"+nickname+"%";
		}
		page.setRecords(this.baseMapper.selectPageListTbVipminer(page, nickname));
		return page;
	}

}
