package com.stys.ipfs.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.dto.PerformanceData;
import com.stys.ipfs.dto.TbInviterExpericence;
import com.stys.ipfs.dto.UserExperienceVO;
import com.stys.ipfs.entity.TbUserExperience;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dp
 * @since 2019-01-13
 */
public interface ITbUserExperienceService extends IService<TbUserExperience> {

	Page<UserExperienceVO> selectPageUserExperienceVO(Page<UserExperienceVO> page,
			EntityWrapper<UserExperienceVO> wrapper, String nickname);

	PerformanceData selectPerformanceData(Integer user_id);

	/**
	 * 
	 * @param initer_user_id
	 * @return
	 */
	public TbInviterExpericence selectAllExperienceDatas(Integer initer_user_id);

	Object selectPerformanceDataList(Integer userId, Integer page, Integer limit);
}