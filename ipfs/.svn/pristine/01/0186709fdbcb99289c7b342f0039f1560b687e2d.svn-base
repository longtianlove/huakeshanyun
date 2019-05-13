package com.stys.ipfs.service.impl;

import com.stys.ipfs.dto.UserExperienceVO;
import com.stys.ipfs.entity.TbUserExperience;
import com.stys.ipfs.mapper.TbUserExperienceMapper;
import com.stys.ipfs.service.ITbUserExperienceService;
import com.stys.ipfs.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-01-13
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbUserExperienceServiceImpl extends ServiceImpl<TbUserExperienceMapper, TbUserExperience> implements ITbUserExperienceService {

	@Override
	public Page<UserExperienceVO> selectPageUserExperienceVO(Page<UserExperienceVO> page,
			EntityWrapper<UserExperienceVO> wrapper, String nickname) {
		SqlHelper.fillWrapper(page, wrapper);
		if(!StringUtils.isEmpty(nickname)) {
			nickname="%"+nickname+"%";
		}
        page.setRecords( this.baseMapper.selectPageListVO(page, wrapper, nickname));    
        return page;
	}

}
