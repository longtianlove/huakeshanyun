package com.stys.ipfs.service.impl;

import com.stys.ipfs.code.IncomeOrOut;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.entity.TbPersonalInfo;
import com.stys.ipfs.entity.TbUserAssets;
import com.stys.ipfs.entity.TbWithdrawadLog;
import com.stys.ipfs.mapper.TbWithdrawadLogMapper;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbPersonalInfoService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.service.ITbWithdrawadLogService;
import com.stys.ipfs.util.AppConstant;
import com.stys.ipfs.util.StringUtils;
import com.stys.ipfs.util.UUIdUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-13
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbWithdrawadLogServiceImpl extends ServiceImpl<TbWithdrawadLogMapper, TbWithdrawadLog> implements ITbWithdrawadLogService {
	
	@Reference(version = "1.0.0", check = false)
	private ITbUserAssetsService itbUserAssetsService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService itbAssetsDetailService;

	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;
	
	@Reference(version = "1.0.0", check = false)
	private ITbPersonalInfoService itbPersonalInfoService;
	
	@Reference(version = "1.0.0", check = false)
	private ITbWithdrawadLogService itbWithdrawadLogService;
	
	
	@Override
	public AppResultInfo<?> withdraw_cash(Integer userId, Double cashNumber) {
		TbUserAssets tbuserassets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", userId));
		Double balance=tbuserassets.getCoin();
		//手续费0.05
		Dic dic = idicService.getDicData("提现手续费", "提现手续费");
		/**
		 * 1.0 规则   提现多少金额实际到账多少金额            从总数里面扣除手续费           再判断金额是否足够  （参考微信提现）
		 */
		Double temp=new BigDecimal(cashNumber).multiply(new BigDecimal(dic.getValue1())).add(new BigDecimal(cashNumber)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		
		
		if(balance<temp) {
			//金币不足
			return new AppResultInfo<>(AppConstant.STATUE_1,"你剩余的金币不支持当前额度提现！");
		}
		//确认是否绑定银行卡
		TbPersonalInfo tbpersonalinfo=itbPersonalInfoService.selectOne(new EntityWrapper<TbPersonalInfo>().eq("user_id", userId));
		if (StringUtils.isEmpty(tbpersonalinfo)) {
			return new AppResultInfo<>(AppConstant.STATUE_2,"请您先绑定银行卡才能提现！");
		}
		tbuserassets.setCoin(new BigDecimal(balance).subtract(new BigDecimal(temp)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		
		//添加账务明细
		
		TbAssetsDetail assetsdetail = new TbAssetsDetail();
		assetsdetail.setId(UUIdUtils.getUUID());
		assetsdetail.setUserId(userId);
		assetsdetail.setBeforeAmount(balance);
		assetsdetail.setAmount(temp);
		assetsdetail.setAfterAmount(new BigDecimal(balance).subtract(new BigDecimal(temp)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		assetsdetail.setInOrOut(IncomeOrOut.expend.getCode());
		assetsdetail.setType(idicService.getDicData("账变类型", "金币提现").getId());
		assetsdetail.setAccountType(1);//金币
		itbAssetsDetailService.insert(assetsdetail);
		
		itbUserAssetsService.updateById(tbuserassets);
		
		
		//创建申请
		TbWithdrawadLog withdrawadlog=new TbWithdrawadLog();
		withdrawadlog.setUserId(userId);
		withdrawadlog.setBeforeAmount(balance);
		withdrawadlog.setCashNumber(cashNumber);
		withdrawadlog.setActualAmount(temp);
		withdrawadlog.setUserAccount(tbpersonalinfo.getBankCard());
		withdrawadlog.setUserName(tbpersonalinfo.getRealName());
		withdrawadlog.setCreateTime(new Date());
		withdrawadlog.setCashStatus(0);
		withdrawadlog.setAfterAmount(new BigDecimal(balance).subtract(new BigDecimal(temp)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		withdrawadlog.setHandfree(new BigDecimal(cashNumber).multiply(new BigDecimal(dic.getValue1())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		itbWithdrawadLogService.insert(withdrawadlog);
		return new AppResultInfo<>(AppConstant.STATUE_200,"申请成功！");
	}


	@Override
	public Page<TbWithdrawadLog> selectPageTbWithdrawadLog(Page<TbWithdrawadLog> page, String phone, String cashStatus,
			String userName) {
		if(!StringUtils.isEmpty(userName)) {
			userName="%"+userName+"%";
		}
		page.setRecords(this.baseMapper.selectPageListTbWithdrawadLog(page, phone, cashStatus, userName));
		return page;
	}

}
