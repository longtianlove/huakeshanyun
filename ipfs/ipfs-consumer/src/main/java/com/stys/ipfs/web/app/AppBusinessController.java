package com.stys.ipfs.web.app;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.code.IncomeOrOut;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.entity.TbOfflinePayment;
import com.stys.ipfs.entity.TbTransferUsdt;
import com.stys.ipfs.entity.TbUserAssets;
import com.stys.ipfs.entity.TbUserBuyLog;
import com.stys.ipfs.entity.TbVipminer;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbOfflinePaymentService;
import com.stys.ipfs.service.ITbTransferUsdtService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.service.ITbUserBuyLogService;
import com.stys.ipfs.service.ITbVipminerService;
import com.stys.ipfs.util.AppConstant;
import com.stys.ipfs.util.BigDecimalUtils;
import com.stys.ipfs.util.DateUtil;
import com.stys.ipfs.util.StringUtils;
import com.stys.ipfs.util.UUIdUtils;

@RestController
@RequestMapping("/tbr")
public class AppBusinessController extends AppController {

	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;

	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService itbAssetsDetailService;

	@Reference(version = "1.0.0", check = false)
	private ITbUserAssetsService itbUserAssetsService;

	@Reference(version = "1.0.0", check = false)
	private ITbUserBuyLogService itbUserBuyLogService;

	@Reference(version = "1.0.0", check = false)
	private ITbVipminerService itbVipminerService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbOfflinePaymentService itbOfflinePaymentService;

	@Reference(version = "1.0.0", check = false)
	private ITbTransferUsdtService itbTransferUsdtService;

	/**
	 * 推广收益
	 * 
	 * @api {post} /tbr/getEarningsForUser 个人推广收益
	 * @apiGroup userBase
	 * @apiDescription 详细描述：推广收益
	 * @apiParam {Integer} userId 用户Id
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getEarningsForUser
	 */
	@RequestMapping("/getEarningsForUser")
	public @ResponseBody AppResultInfo<?> getEarningsForUser(Integer userId) {
		Dic dic = idicService.getDicData("账变类型", "推广奖励" + "");
		Map<String, Object> map = new HashMap<>();
		// 查询今日收益

		// 今天
		String today = DateUtil.getDay();
		// 昨天
		String yesterday = DateUtil.getYesterday();

		Map<String, Object> map1 = itbAssetsDetailService
				.selectMap((new EntityWrapper<TbAssetsDetail>().setSqlSelect("SUM(amount) as todayEarnings")
						.eq("type", dic.getId()).eq("user_id", userId).like("create_time", today)));
		if (map1 != null && map1.size() > 0) {
			BigDecimal todayEarnings = (BigDecimal) map1.get("todayEarnings");
			map.put("todayEarnings", todayEarnings.floatValue());
		} else {
			map.put("todayEarnings", "0.00");
		}
		// 昨日收益
		Map<String, Object> map2 = itbAssetsDetailService
				.selectMap((new EntityWrapper<TbAssetsDetail>().setSqlSelect("SUM(amount) as yesterdayEarnings")
						.eq("type", dic.getId()).eq("user_id", userId).like("create_time", yesterday)));
		if (map2 != null && map2.size() > 0) {
			BigDecimal yesterdayEarnings = (BigDecimal) map2.get("yesterdayEarnings");
			map.put("yesterdayEarnings", yesterdayEarnings.floatValue());
		} else {
			map.put("yesterdayEarnings", "0.00");
		}
		TbUserAssets userassets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", userId));

		map.put("giftcoin", userassets.getGiftCoin().floatValue());
		map.put("limitcoin", userassets.getLimitCoin().floatValue());

		return new AppResultInfo<>(JSONObject.toJSON(map));
	}

	/**
	 * 推广收益明细
	 * 
	 * @api {post} /tbr/getEarningsDetailForUser 推广收益明细（分页）
	 * @apiGroup userBase
	 * @apiDescription 详细描述：推广收益明细
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {Integer} page 当前页
	 * @apiParam {Integer} limit 每页多少条
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getEarningsDetailForUser
	 */
	@RequestMapping("/getEarningsDetailForUser")
	public @ResponseBody AppResultInfo<?> getEarningsDetailForUser(Integer userId, Integer page, Integer limit) {
		Dic dic = idicService.getDicData("账变类型", "推广奖励");
		AppUser appuser = iappUserService.selectById(userId);
		if (limit == 0 || limit == null) {
			limit = 10;
		}
		Page<TbAssetsDetail> pageObj = itbAssetsDetailService.getPageTbAssetsDetail(
				new Page<TbAssetsDetail>(page, limit), appuser.getPhone(), null, null, null, dic.getId());
		return new AppResultInfo<>(pageObj);
	}

	/**
	 * 个人推广礼包划转
	 * 
	 * @api {post} /tbr/giftCoinTransfer 个人推广礼包划转
	 * @apiGroup userBase
	 * @apiDescription 详细描述：个人推广礼包划转
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {Float} balance 划转金额
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/giftCoinTransfer
	 */
	@RequestMapping("/giftCoinTransfer")
	public @ResponseBody AppResultInfo<?> getGiftCoinTransfer(Integer userId, Float balance) {
		TbUserAssets userassets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", userId));
		synchronized (userassets) {
			Float giftCoin = userassets.getGiftCoin();
			Float limitCoin = userassets.getLimitCoin();
			if (giftCoin < balance) {
				return new AppResultInfo<>(-1, "推广礼包金额不足");
			}
			if (limitCoin < balance) {
				return new AppResultInfo<>(-1, "已超过剩余转出额度");
			}
			Float coin = userassets.getCoin();
			String newCoin = BigDecimalUtils.add(coin.toString(), balance.toString());
			String newgiftCoin = BigDecimalUtils.subtract(giftCoin.toString(), balance.toString());
			String newlimitCoin = BigDecimalUtils.subtract(limitCoin.toString(), balance.toString());
			userassets.setCoin(Float.parseFloat(newCoin));
			userassets.setGiftCoin(Float.parseFloat(newgiftCoin));
			userassets.setLimitCoin(Float.parseFloat(newlimitCoin));
			itbUserAssetsService.updateById(userassets);

			Dic dic = idicService.getDicData("账变类型", "收益划转");
			TbAssetsDetail entity = new TbAssetsDetail();
			entity.setId(UUIdUtils.getUUID());
			entity.setUserId(userId);
			entity.setBeforeAmount(giftCoin);
			entity.setAmount(balance);
			entity.setAfterAmount(Float.parseFloat(newgiftCoin));
			entity.setInOrOut(IncomeOrOut.expend.getCode());
			entity.setType(dic.getId());
			itbAssetsDetailService.insert(entity);

			TbAssetsDetail assetsdetail = new TbAssetsDetail();
			assetsdetail.setId(UUIdUtils.getUUID());
			assetsdetail.setUserId(userId);
			assetsdetail.setBeforeAmount(coin);
			assetsdetail.setAmount(balance);
			assetsdetail.setAfterAmount(Float.parseFloat(newCoin));
			assetsdetail.setInOrOut(IncomeOrOut.income.getCode());
			assetsdetail.setType(dic.getId());
			itbAssetsDetailService.insert(assetsdetail);
		}

		return new AppResultInfo<>("划转成功！");
	}

	/**
	 * @api {post} /tbr/vipminerAndTotalEarnings 获取超级矿工人数和全球昨日收益
	 * @apiGroup userBase
	 * @apiDescription 详细描述：获取超级矿工人数和全球昨日收益
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/vipminerAndTotalEarnings
	 */
	@RequestMapping("/vipminerAndTotalEarnings")
	public @ResponseBody AppResultInfo<?> getVipminerAndTotalEarnings() {
		Map<String, Object> map = new HashMap<>();
		String yesterday = DateUtil.getYesterday();
		Map<String, Object> map1 = itbUserBuyLogService.selectMap((new EntityWrapper<TbUserBuyLog>()
				.setSqlSelect("sum(product_price) as totalEarnings").like("create_time", yesterday)));
		if (map1 != null && map1.size() > 0) {
			BigDecimal totalEarnings = (BigDecimal) map1.get("totalEarnings");
			map.put("totalEarnings", totalEarnings.floatValue());
		} else {
			map.put("totalEarnings", "0.00");
		}
		int vipNumber = itbVipminerService.selectCount(new EntityWrapper<TbVipminer>());
		map.put("vipNumber", vipNumber);
		return new AppResultInfo<>(JSONObject.toJSON(map));
	}

	/**
	 * 线下充值申请
	 * 
	 * @api {post} /tbr/offlineRecharge 线下充值申请
	 * @apiGroup userBase
	 * @apiDescription 详细描述：线下充值申请
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {Float} balance 充值金额
	 * @apiParam {String} voucherPath 凭证地址
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/offlineRecharge
	 */
	@RequestMapping("/offlineRecharge")
	public @ResponseBody AppResultInfo<?> getOfflineRecharge(HttpServletRequest request, Integer userId, Float balance,
			String voucherPath) {
		AppUser appuser = iappUserService.selectById(userId);
		TbOfflinePayment entity = new TbOfflinePayment();
		entity.setPhone(appuser.getPhone());
		entity.setPrice(balance);
		entity.setUserId(userId);
		entity.setVoucherPath(voucherPath);
		entity.setStatus(0);
		itbOfflinePaymentService.insert(entity);
		return new AppResultInfo<>("申请成功！");
	}

	/**
	 * USDT充值申请
	 * 
	 * @api {post} /tbr/USDTRecharge USDT充值申请
	 * @apiGroup userBase
	 * @apiDescription 详细描述： USDT充值申请
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {referenceaddress} referenceaddress From充值地址
	 * @apiParam {sendingaddress} sendingaddress To充值地址
	 * @apiParam {txid} txid 交易编号
	 * @apiParam {confirmations} confirmations 确认数量
	 * @apiParam {amount} amount 金额
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/USDTRecharge
	 */
	@RequestMapping("/USDTRecharge")
	public @ResponseBody AppResultInfo<?> USDTRecharge(HttpServletRequest request, Integer userId,
			String sendingaddress, String referenceaddress, String txid, Integer confirmations, String amount) {
		// AppUser appuser = iappUserService.selectById(userId);
		if (StringUtils.strIsNull(txid) | null == userId | StringUtils.strIsNull(sendingaddress) | null == confirmations
				| StringUtils.strIsNull(amount)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "fail");
		}
		if (confirmations > 0) {

			TbTransferUsdt tbTransferUsdt = new TbTransferUsdt();
			tbTransferUsdt.setFromaddress(sendingaddress);
			tbTransferUsdt.setToaddress(referenceaddress);
			tbTransferUsdt.setTxid(txid);
			tbTransferUsdt.setUserId(userId);
			tbTransferUsdt.setStatus(1);
			tbTransferUsdt.setConfirmations(confirmations);
			Float balance = Float.valueOf(amount);
			tbTransferUsdt.setBalance(balance);
			itbTransferUsdtService.insert(tbTransferUsdt);
			// 通过usdt 地址 查询用户账号
			logger.info("referenceaddress---->" + referenceaddress);
			logger.info("user_id---->" + userId);
			// 用户资产表
			TbUserAssets tbUserAssets = itbUserAssetsService
					.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", userId));
			synchronized (tbUserAssets) {

				// 转成人民币
				String new_balance = BigDecimalUtils.multiply(String.valueOf(balance), "6.72");

				float coin = tbUserAssets.getCoin();
				// 增加余额
				String new_coin = BigDecimalUtils.add(String.valueOf(coin), new_balance);
				tbUserAssets.setCoin(Float.valueOf(new_coin));
				// 更新个人资产
				if (itbUserAssetsService.updateById(tbUserAssets)) {
					logger.info("金额修改成功：-->");
				}
				// 增加日志
				addAssetsDetail(tbTransferUsdt, new_balance, coin, new_coin);
				return new AppResultInfo<>("成功！");
			}
		}
		return new AppResultInfo<>(AppConstant.STATUE_1, "fail");
	}

	private void addAssetsDetail(TbTransferUsdt tbTransferUsdt, String new_balance, float coin, String new_coin) {
		TbAssetsDetail tbAssetsDetail = new TbAssetsDetail();
		tbAssetsDetail.setId(UUIdUtils.getUUID());
		tbAssetsDetail.setAfterAmount(Float.valueOf(new_coin));
		tbAssetsDetail.setAmount(Float.valueOf(new_balance));
		tbAssetsDetail.setBeforeAmount(coin);
		tbAssetsDetail.setInOrOut(IncomeOrOut.income.getCode());
		tbAssetsDetail.setType(idicService.getDicData("账变类型", "USDT充值").getId());
		tbAssetsDetail.setUserId(tbTransferUsdt.getUserId());
		// 插入转账明细
		itbAssetsDetailService.insert(tbAssetsDetail);
	}

}