package com.stys.ipfs.service;

import com.stys.ipfs.dto.UserExperienceVO;
import com.stys.ipfs.entity.TbUserExperience;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-01-13
 */
public interface ITbUserExperienceService extends IService<TbUserExperience> {

	Page<UserExperienceVO> selectPageUserExperienceVO(Page<UserExperienceVO> page, EntityWrapper<UserExperienceVO> wrapper,String nickname);
}
