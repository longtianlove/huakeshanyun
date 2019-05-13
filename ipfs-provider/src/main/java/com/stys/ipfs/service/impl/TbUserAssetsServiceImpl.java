package com.stys.ipfs.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.code.IncomeOrOut;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.entity.TbUserAssets;
import com.stys.ipfs.mapper.TbUserAssetsMapper;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.IAppUserinfoService;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbInviteService;
import com.stys.ipfs.service.ITbProductService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.service.ITbUserBuyLogService;
import com.stys.ipfs.service.ITbVipminerService;
import com.stys.ipfs.util.AppConstant;
import com.stys.ipfs.util.BigDecimalUtils;
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
public class TbUserAssetsServiceImpl extends ServiceImpl<TbUserAssetsMapper, TbUserAssets>
		implements ITbUserAssetsService {

	protected static Logger logger = LoggerFactory.getLogger(TbUserAssetsServiceImpl.class);
	@Reference(version = "1.0.0")
	private ITbUserAssetsService itbUserAssetsService;

	@Reference(version = "1.0.0", check = false)
	private ITbProductService itbProductService;

	@Reference(version = "1.0.0", check = false)
	private ITbUserBuyLogService itbUserBuyLogService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserinfoService iappUserinfoService;

	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbInviteService itbInviteService;

	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService itbAssetsDetailService;

	@Reference(version = "1.0.0")
	private ITbVipminerService itbVipminerService;

	@Override
	public AppResultInfo<?> transfer(Integer ownerUserId, String phone, Double balance) {
		TbUserAssets owneruserassets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", ownerUserId));
		// 验证资产
		Double total = owneruserassets.getCoin();
		if (total < balance) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "余额不足！");
		}
		// 验证对方账号
		AppUser appuser = iappUserService.selectOne(new EntityWrapper<AppUser>().eq("phone", phone));
		if (StringUtils.isEmpty(appuser)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "账号错误！");
		}
		Dic dic = idicService.getDicData("账变类型", "金币划转");

		// 自己转账记录
		if (!subTransferLog(ownerUserId, balance, appuser.getId(), dic)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "转账失败！");
		}
		// 添加对方转账明细
		if (!addTransferLog(appuser.getId(), balance, ownerUserId, dic)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "转账失败！");
		}
		synchronized (owneruserassets) {
			Double newbalance = Double.parseDouble(BigDecimalUtils.subtract(total.toString(), balance.toString()));
			owneruserassets.setCoin(newbalance);
			// 修改自己余额
			if (itbUserAssetsService.updateById(owneruserassets)) {
				// 修改对方金额
				TbUserAssets otheruserassets = itbUserAssetsService
						.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", appuser.getId()));
				synchronized (otheruserassets) {
					Double otherbalance = otheruserassets.getCoin();
					Double otherNewbalance = Double
							.parseDouble(BigDecimalUtils.add(otherbalance.toString(), balance.toString()));
					otheruserassets.setCoin(otherNewbalance);
					if (itbUserAssetsService.updateById(otheruserassets)) {
						return new AppResultInfo<>(AppConstant.STATUE_200, "转账成功！");
					}
				}
			}
		}
		return new AppResultInfo<>(AppConstant.STATUE_1, "转账失败！");
	}

	/**
	 * 增加
	 * 
	 * @apiParam userId
	 * @apiParam balance
	 * @apiParam otherUserId
	 * @apiParam dic
	 */
	private boolean addTransferLog(Integer userId, Double balance, Integer otherUserId, Dic dic) {
		TbUserAssets tbuserassets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", userId));
		TbAssetsDetail entity = new TbAssetsDetail();
		entity.setId(UUIdUtils.getUUID());
		entity.setUserId(userId);
		entity.setBeforeAmount(tbuserassets.getCoin());
		entity.setAmount(balance);
		entity.setAfterAmount(new BigDecimal(tbuserassets.getCoin()).add(new BigDecimal(balance))
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		entity.setInOrOut(IncomeOrOut.income.getCode());
		entity.setType(dic.getId());
		entity.setSunUserId(otherUserId);
		entity.setAccountType(1);// 金币
		return itbAssetsDetailService.insert(entity);
	}

	/**
	 * 减少
	 * 
	 * @apiParam userId
	 * @apiParam balance
	 * @apiParam otherUserId
	 * @apiParam dic
	 */
	private boolean subTransferLog(Integer userId, Double balance, Integer otherUserId, Dic dic) {
		TbUserAssets tbuserassets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", userId));
		TbAssetsDetail entity = new TbAssetsDetail();
		entity.setId(UUIdUtils.getUUID());
		entity.setUserId(userId);
		entity.setBeforeAmount(tbuserassets.getCoin());
		entity.setAmount(balance);
		entity.setAfterAmount(new BigDecimal(tbuserassets.getCoin()).subtract(new BigDecimal(balance))
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		entity.setInOrOut(IncomeOrOut.expend.getCode());
		entity.setType(dic.getId());
		entity.setSunUserId(otherUserId);
		entity.setAccountType(1);// 金币
		return itbAssetsDetailService.insert(entity);
	}

}