package com.stys.ipfs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.dto.AppUserInfoVo;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.entity.AppUserinfo;
import com.stys.ipfs.entity.TbInvite;
import com.stys.ipfs.entity.TbUpgradeRule;
import com.stys.ipfs.entity.TbUserExperience;
import com.stys.ipfs.mapper.AppUserinfoMapper;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.IAppUserinfoService;
import com.stys.ipfs.service.ITbInviteService;
import com.stys.ipfs.service.ITbUpgradeRuleService;
import com.stys.ipfs.service.ITbUserExperienceService;
import com.stys.ipfs.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-11
 */
@Service(version = "1.0.0", timeout = 60000)
public class AppUserinfoServiceImpl extends ServiceImpl<AppUserinfoMapper, AppUserinfo> implements IAppUserinfoService {
	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbUserExperienceService itbUserExperienceService;

	@Reference(version = "1.0.0", check = false)
	private ITbUpgradeRuleService itbUpgradeRuleService;

	@Reference(version = "1.0.0", check = false)
	private ITbInviteService itbInviteService;

	@Override
	public AppUserInfoVo getUserInfo(Integer userId) {
		return this.baseMapper.UserInfo(userId);
	}

	@Override
	public Page<AppUserInfoVo> selectPageAppUserInfoVo(Page<AppUserInfoVo> page, String phone, String nickname) {
		if (!StringUtils.isEmpty(nickname)) {
			nickname = "%" + nickname + "%";
		}
		page.setRecords(this.baseMapper.selectPageListAppUserInfoVo(page, phone, nickname));
		return page;
	}

	@Override
	public void upGared() {
		System.out.println("=============检测用户升级================");
		List<AppUser> list = iappUserService.selectList(new EntityWrapper<AppUser>());// TODO 需要排除系统两级
		for (AppUser appUser : list) {

			Integer userId = appUser.getId();
			// 获取当前用户的所有一级邀请人
			List<TbInvite> inviteList = itbInviteService
					.selectList(new EntityWrapper<TbInvite>().eq("tb_iviter", userId));
			// 确保用户最少拥有2条线 单条线路用户不能升级
			List<Integer> arr = new ArrayList<>();
			if (inviteList != null && inviteList.size() > 1) {
				for (TbInvite invite : inviteList) {
					TbUserExperience userExperience = itbUserExperienceService
							.selectOne(new EntityWrapper<TbUserExperience>().eq("user_id", invite.getTbIvitee()));
					if (null != userExperience) {
						arr.add(userExperience.getExperience());
					}

				}
				// 排序
				Collections.sort(arr);
				// arr去掉最大值累加获得当前用户的小区业绩
				int limitExperience = 0;
				for (int i = 0; i < arr.size(); i++) {
					if (i != arr.size() - 1) {
						limitExperience += arr.get(i);
					}
				}
				// 获取当前用户的累计
				TbUserExperience experience = itbUserExperienceService
						.selectOne(new EntityWrapper<TbUserExperience>().eq("user_id", userId));
				// 根据用户累计与小区业绩判断当前用户存在的最大等级
				Integer grade = this.getRule(experience.getExperience(), limitExperience);
				synchronized (appUser) {
					if (grade > appUser.getDicId()) {
						appUser.setDicId(grade);
						iappUserService.updateById(appUser);
					}
				}
			}

		}

	}

	/**
	 * 判断规则返回用户等级
	 * 
	 * @param experience      用户的累计
	 * @param limitExperience 用户的小区业绩
	 */
	public Integer getRule(Integer experience, Integer limitExperience) {
		List<TbUpgradeRule> list = itbUpgradeRuleService.selectList(new EntityWrapper<TbUpgradeRule>());
		int i = 1;
		for (TbUpgradeRule tbUpgradeRule : list) {
			if (experience > 0 && experience >= tbUpgradeRule.getExperience() && limitExperience >= 0
					&& limitExperience >= tbUpgradeRule.getLimitExperience()) {
				i = tbUpgradeRule.getDicId();
			}
		}
		return i;
	}

}
