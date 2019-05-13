package com.stys.ipfs.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.code.IncomeOrOut;
import com.stys.ipfs.dto.UserAssetDetailVO;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.entity.TbInvite;
import com.stys.ipfs.entity.TbSystemProfit;
import com.stys.ipfs.entity.TbUserAssets;
import com.stys.ipfs.entity.TbUserBuyLog;
import com.stys.ipfs.entity.TbVipminer;
import com.stys.ipfs.mapper.TbAssetsDetailMapper;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbInviteService;
import com.stys.ipfs.service.ITbSystemProfitService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.service.ITbUserBuyLogService;
import com.stys.ipfs.service.ITbVipminerService;
import com.stys.ipfs.util.BigDecimalUtils;
import com.stys.ipfs.util.DateUtil;
import com.stys.ipfs.util.StringUtils;
import com.stys.ipfs.util.UUIdUtils;

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

	@Reference(version = "1.0.0", check = false)
	private ITbUserBuyLogService itbUserBuyLogService;

	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;

	@Reference(version = "1.0.0", check = false)
	private ITbVipminerService itbVipminerService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbInviteService itbInviteService;

	@Reference(version = "1.0.0", check = false)
	private ITbUserAssetsService itbUserAssetsService;

	@Reference(version = "1.0.0", check = false)
	private ITbSystemProfitService itbSystemProfitService;

	@Override
	public Page<UserAssetDetailVO> getAessetForUser(Page<UserAssetDetailVO> page, Integer userId) {
		page.setRecords(this.baseMapper.getUserAessetDetail(page, userId));
		return page;
	}

	@Override
	public Page<TbAssetsDetail> getPageTbAssetsDetail(Page<TbAssetsDetail> page, String phone, String nickname,
			String startTime, String endTime, Integer type, Integer accountType) {
		if (!StringUtils.isEmpty(nickname)) {
			nickname = "%" + nickname + "%";
		}
		page.setRecords(this.baseMapper.selectPageListTbAssetsDetail(page, phone, nickname, startTime, endTime, type,
				accountType));
		return page;
	}

	@Override
	public List<TbAssetsDetail> getListTbAssetsDetail(String startTime, String endTime) {
		return this.baseMapper.selectListTbAssetsDetail(startTime, endTime);
	}

	@Override
	public void S3PlatformProfit() {
		AppUser user = iappUserService.selectList(new EntityWrapper<AppUser>().orderBy("id")).get(0);// 公司账号第一个账号
		List<TbInvite> list = itbInviteService.selectList(new EntityWrapper<TbInvite>().eq("tb_iviter", user.getId())); // 分公司
		String today = DateUtil.getDay();
		Dic dic = idicService.getDicData("用户级别", "S3");
		for (TbInvite tbInvite : list) {
			List<TbInvite> alist = itbInviteService
					.selectList(new EntityWrapper<TbInvite>().eq("tb_iviter", tbInvite.getTbIvitee())); // 子公司
			for (TbInvite invite : alist) {
				Set<Integer> userSet = new HashSet<>();
				// 计算每个invite
				Set<Integer> set = getInviter(userSet, invite.getTbIvitee());

				Map<String, Object> map = itbUserBuyLogService
						.selectMap(new EntityWrapper<TbUserBuyLog>().setSqlSelect("SUM(product_price) as todayEarnings")
								.in("user_id", set).like("create_time", today));
				if (map != null && map.size() > 0) {
					BigDecimal todayEarnings = (BigDecimal) map.get("todayEarnings");
					// 当前的所有收入 的 2%
					String share_value = BigDecimalUtils.multiply(todayEarnings.toString(), "0.02");
					// 平均分给 invite 下的所有S3
					List<AppUser> applist = iappUserService
							.selectList(new EntityWrapper<AppUser>().in("id", set).eq("dic_id", dic.getId()));
					if (applist != null && applist.size() > 0) {
						int number = applist.size();
						String average_profitStr = BigDecimalUtils.Divide(share_value, String.valueOf(number));
						for (AppUser AppUser : applist) {
							TbUserAssets TbUserAssets = itbUserAssetsService
									.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", AppUser.getId()));
							synchronized (TbUserAssets) {

								Double giftcoin = TbUserAssets.getGiftCoin();
								String new_giftcoin = BigDecimalUtils.add(String.valueOf(giftcoin), average_profitStr);
								TbUserAssets.setGiftCoin(Double.valueOf(new_giftcoin));
								TbAssetsDetail tbAssetsDetail = new TbAssetsDetail();
								tbAssetsDetail.setId(UUIdUtils.getUUID());
								tbAssetsDetail.setUserId(AppUser.getId());
								tbAssetsDetail.setAfterAmount(Double.valueOf(new_giftcoin));
								tbAssetsDetail.setAmount(Double.valueOf(average_profitStr));
								Dic dicd = idicService.getDicData("账变类型", "平台分红");
								tbAssetsDetail.setType(dicd.getId());
								tbAssetsDetail.setBeforeAmount(giftcoin);
								tbAssetsDetail.setInOrOut(IncomeOrOut.income.getCode());
								tbAssetsDetail.setAccountType(2);// 推广礼包
								this.insert(tbAssetsDetail);
								itbUserAssetsService.updateById(TbUserAssets);
							}
						}
					}
				}

			}
		}
	}

	/**
	 * 查询这个下面的所有邀请人
	 * 
	 * @param userSet
	 * @param userId
	 * @return
	 */
	private Set<Integer> getInviter(Set<Integer> userSet, Integer userId) {
//		先把自己放进来，set集合不能重复放直接去除重复
		userSet.add(userId);
		List<TbInvite> list = itbInviteService.selectList(new EntityWrapper<TbInvite>().eq("tb_iviter", userId));
		if (list.size() > 0 && list != null) {
			for (TbInvite tbInvite : list) {
				Set<Integer> set = getInviter(userSet, tbInvite.getTbIvitee());
				userSet.add(tbInvite.getTbIvitee());
				userSet.addAll(set);
			}
		}
		return userSet;
	}

	@Override
	public void vipMinerPlatformProfit() {
		String today = DateUtil.getToday();
		// 查询今日的收益
		float total_profit = itbUserBuyLogService.selectTodayBuyProfit("%" + today + "%");

		String percentageStr = idicService.getDicData("平台收益", "分红").getValue1();
		// 计算百分比
		String profitStr = BigDecimalUtils.multiply(percentageStr, String.valueOf(total_profit));

		// 这里也需要冻结 统计超级旷工人数
		int vipcount = itbVipminerService.selectCount(new EntityWrapper<TbVipminer>());
		if (vipcount != 0) {
			// 平分收益
			String average_profitStr = BigDecimalUtils.Divide(profitStr, String.valueOf(vipcount));

			List<TbVipminer> vipMinerList = itbVipminerService
					.selectList(new EntityWrapper<TbVipminer>().eq("status", 1));
			// 分红给超级旷工
			for (TbVipminer tbVipminer : vipMinerList) {
				// 增加分红
				Double giftcoin = tbVipminer.getGiftCoin();
				String new_giftcoin = BigDecimalUtils.add(String.valueOf(giftcoin), average_profitStr);
				Double coin = Double.valueOf(new_giftcoin);
				Double limit = tbVipminer.getLimitCoin();
				if (limit >= coin) {
					tbVipminer.setGiftCoin(coin);
				} else if (limit < coin) {
					if (limit > 0) {
						tbVipminer.setGiftCoin(coin - limit);
					} else if (limit == 0) {
						tbVipminer.setGiftCoin(0d);
					}
					tbVipminer.setStatus(0);
				}

				itbVipminerService.updateById(tbVipminer);

				TbAssetsDetail tbAssetsDetail = new TbAssetsDetail();
				tbAssetsDetail.setId(UUIdUtils.getUUID());
				tbAssetsDetail.setUserId(tbVipminer.getUserId());
				tbAssetsDetail.setAfterAmount(coin);
				tbAssetsDetail.setAmount(Double.valueOf(average_profitStr));
				Dic dic = idicService.getDicData("账变类型", "平台分红");
				tbAssetsDetail.setType(dic.getId());
				tbAssetsDetail.setBeforeAmount(Double.valueOf(giftcoin));
				tbAssetsDetail.setInOrOut(IncomeOrOut.income.getCode());
				tbAssetsDetail.setAccountType(3);// 超级矿工
				this.insert(tbAssetsDetail);
			}
		}
		// 统计平台收入
		TbSystemProfit systemprofit = new TbSystemProfit();
		systemprofit.setProfit(total_profit);
		systemprofit.setCreateTime(new Date());
		itbSystemProfitService.insert(systemprofit);

	}
}
