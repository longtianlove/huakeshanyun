package com.stys.ipfs.web.app;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.dto.UserAssetDetailVO;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.entity.TbUsdtlog;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbBackfillService;
import com.stys.ipfs.service.ITbProductService;
import com.stys.ipfs.service.ITbUsdtlogService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.service.ITbWithdrawadLogService;
import com.stys.ipfs.util.AppConstant;
import com.stys.ipfs.util.SecuritySHA1Utils;
import com.stys.ipfs.util.StringUtils;

@RestController
@RequestMapping("/tbr")
public class AppFundsActionController extends AppController {

	@Reference(version = "1.0.0", check = false)
	private ITbUsdtlogService itbUsdtlogService;

	@Reference(version = "1.0.0", check = false)
	private ITbUserAssetsService itbUserAssetsService;

	@Reference(version = "1.0.0", check = false)
	private ITbWithdrawadLogService itbWithdrawadLogService;

	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService itbAssetsDetailService;

	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;

	@Reference(version = "1.0.0", check = false)
	private ITbBackfillService itbBackfillService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbProductService itbProductService;

	@RequestMapping(value = "/rechargeByUsdt", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<?> rechargeByUsdt(String usdtAddress) {

		return new AppResultInfo<>(AppConstant.STATUE_1, "参数错误");
	}

	/**
	 * 购买产品
	 * 
	 * @api {post} /tbr/buyProduct 购买产品
	 * @apiGroup Finance
	 * @apiDescription 详细描述： 购买产品
	 * @apiParam {Integer} userId 用户id
	 * @apiParam {String} productId 产品编号
	 * @apiParam {String} paymentCode 支付密码
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/buyProduct
	 * @return
	 */
	@RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<?> buyProduct(String paymentCode, Integer userId, Integer productId) {
		if (null == userId || null == productId || null == paymentCode) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "参数错误");
		}
		AppUser appuser = iappUserService.selectById(userId);
		if (StringUtils.strIsNull(appuser.getPaymentCode())) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "请先设置支付密码！");
		}
		String temp_newWord = null;
		try {

			temp_newWord = SecuritySHA1Utils.shaEncode(paymentCode.trim());
		} catch (Exception e) {
			logger.info("=======密码解密异常========");
			return new AppResultInfo<>(AppConstant.STATUE_1, "支付密码错误请重新输入！");
		}
		if (!appuser.getPaymentCode().equals(temp_newWord)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "支付密码错误请重新输入！");
		}
		return itbProductService.buyProduct(userId, productId);
	}

	/**
	 * 查询usdt充值消费记录
	 * 
	 * @api {post} /tbr/queryUSDTLog 查询usdt充值消费记录
	 * @apiGroup Finance
	 * @apiDescription 详细描述： 金币划转
	 * @apiParam {Integer} userId 用户id
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/queryUSDTLog
	 * @return
	 */
	@RequestMapping(value = "/queryUSDTLog", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<?> queryUSDTLog(Integer userId) {
		if (null == userId) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "参数传递错误");
		}
		List<TbUsdtlog> tbUsdtlogs = itbUsdtlogService.selectUsdtlog(userId);
		return new AppResultInfo<>(tbUsdtlogs);
	}

	/**
	 * 金币划转
	 * 
	 * @api {post} /tbr/transfer 金币划转
	 * @apiGroup Finance
	 * @apiDescription 详细描述： 金币划转
	 * @apiParam {Integer} ownerUserId 用户id
	 * @apiParam {String} phone 对方账号
	 * @apiParam {Float} balance 划转金额
	 * @apiParam {String} paymentCode 支付密码
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/transfer
	 * @return
	 */
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<?> transfer(String paymentCode, Integer ownerUserId, String phone,
			Float balance) {
		AppUser appuser = iappUserService.selectById(ownerUserId);
		if (StringUtils.isEmpty(appuser.getPaymentCode())) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "请先设置支付密码！");
		}
		String temp_newWord = null;
		try {

			temp_newWord = SecuritySHA1Utils.shaEncode(paymentCode.trim());
		} catch (Exception e) {
			logger.info("=======密码解密异常========");
			return new AppResultInfo<>(AppConstant.STATUE_1, "支付密码错误请重新输入！");
		}
		if (!appuser.getPaymentCode().equals(temp_newWord)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "支付密码错误请重新输入！");
		}
		if (appuser.getPhone().equals(phone)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "不能转入当前账户！");
		}
		return itbUserAssetsService.transfer(ownerUserId, phone, balance);

	}

	/**
	 * 获取当前用户的账户明细
	 * 
	 * @api {post} /tbr/queryBalance 获取当前用户的账户明细（分页）
	 * @apiGroup userBase
	 * @apiDescription 详细描述： 获取当前用户的账户明细 inOrOut 0表示减少 1表示增加
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {Integer} page 当前页
	 * @apiParam {Integer} limit 每页多少条
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/queryBalance
	 * @return
	 */
	@RequestMapping(value = "/queryBalance", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<?> queryBalance(Integer userId, Integer page, Integer limit) {
		Page<UserAssetDetailVO> pageObj = itbAssetsDetailService
				.getAessetForUser(new Page<UserAssetDetailVO>(page, limit), userId);
		return new AppResultInfo<>(pageObj);
	}

	/**
	 * 提现申请
	 * 
	 * @api {post} /tbr/withdrawCash 提现申请
	 * @apiGroup Business
	 * @apiDescription 详细描述： 提现申请
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {Float} cashNumber 提现金额
	 * @apiParam {String} paymentCode 支付密码
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/withdrawCash
	 * @return
	 */
	@RequestMapping(value = "/withdrawCash", method = RequestMethod.POST)
	public @ResponseBody AppResultInfo<?> withdraw_cash(String paymentCode, Integer userId, Float cashNumber) {
		AppUser appuser = iappUserService.selectById(userId);
		if (StringUtils.isEmpty(appuser.getPaymentCode())) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "请先设置支付密码！");
		}
		String temp_newWord = null;
		try {

			temp_newWord = SecuritySHA1Utils.shaEncode(paymentCode.trim());
		} catch (Exception e) {
			logger.info("=======密码解密异常========");
			return new AppResultInfo<>(AppConstant.STATUE_1, "支付密码错误请重新输入！");
		}
		if (!appuser.getPaymentCode().equals(temp_newWord)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "支付密码错误请重新输入！");
		}
		return itbWithdrawadLogService.withdraw_cash(userId, cashNumber);
	}

}
