package com.stys.ipfs.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.dto.UserAssetDetailVO;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.mapper.TbAssetsDetailMapper;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbAssetsDetailServiceImpl extends ServiceImpl<TbAssetsDetailMapper, TbAssetsDetail>
		implements ITbAssetsDetailService {

	@Override
	public Page<UserAssetDetailVO> getAessetForUser(Page<UserAssetDetailVO> page,Integer userId) {
		page.setRecords(this.baseMapper.getUserAessetDetail(page,userId));
		return page;
	}

	@Override
	public Page<TbAssetsDetail> getPageTbAssetsDetail(Page<TbAssetsDetail> page, String phone, String nickname,String startTime,  String endTime, Integer type) {
		if(!StringUtils.isEmpty(nickname)) {
			nickname="%"+nickname+"%";
		}
		page.setRecords(this.baseMapper.selectPageListTbAssetsDetail(page, phone, nickname,startTime,  endTime,type));
		return page;
	}

	@Override
	public List<TbAssetsDetail> getListTbAssetsDetail(String startTime, String endTime) {
		return this.baseMapper.selectListTbAssetsDetail(startTime, endTime);
	}

}
