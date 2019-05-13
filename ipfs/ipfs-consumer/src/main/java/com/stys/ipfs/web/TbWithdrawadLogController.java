package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

import com.stys.ipfs.code.IncomeOrOut;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.entity.TbUserAssets;
import com.stys.ipfs.entity.TbWithdrawadLog;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.service.ITbWithdrawadLogService;
import com.stys.ipfs.util.UUIdUtils;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-13
 */
@Controller
@RequestMapping("/tbWithdrawadLog")
public class TbWithdrawadLogController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private ITbWithdrawadLogService itbWithdrawadLogService;
    
	@Reference(version = "1.0.0", check = false)
	private ITbUserAssetsService itbUserAssetsService;
	
	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService itbAssetsDetailService;

	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbWithdrawadLog:view")
    public @ResponseBody
        ResultInfo<List<TbWithdrawadLog>> listData(String phone,String cashStatus,String userName, Integer page, Integer limit){
        Page<TbWithdrawadLog> pageObj = itbWithdrawadLogService.selectPageTbWithdrawadLog(new Page<TbWithdrawadLog>(page,limit), phone, cashStatus, userName);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbWithdrawadLog:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbWithdrawadLog tbWithdrawadLog){
        boolean b = itbWithdrawadLogService.insert(tbWithdrawadLog);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbWithdrawadLog:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbWithdrawadLogService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbWithdrawadLog:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbWithdrawadLog tbWithdrawadLog){
        boolean b = itbWithdrawadLogService.updateById(tbWithdrawadLog);
        return new ResultInfo<>(b);
    }
    
    @RequestMapping("/pass")
    public @ResponseBody
    ResultInfo<Boolean> passCashadvance(Integer id){
    	TbWithdrawadLog withdrawadlog=itbWithdrawadLogService.selectById(id);
    	if(withdrawadlog.getCashStatus()==0) {
    		withdrawadlog.setCashStatus(1);
    		withdrawadlog.setVerifyInfo("同意");
    		itbWithdrawadLogService.updateById(withdrawadlog);
    		return new ResultInfo<>(true);
    	}else {
    		return new ResultInfo<>(false);
    	}
    	//短信通知
//    	AppUser user = iappUserService.selectById(tbUserCashadvance.getUserId());
//    	SMSUtil.doBuySeccuss(user.getPhone(),"【华科闪云】您的金币提现申请已经通过，请注意查看银行提示信息！");
    }
    @RequestMapping("/unpass")
    public @ResponseBody
    ResultInfo<Boolean> unpassCashadvance(Integer id,String verifyInfo){
    	TbWithdrawadLog withdrawadlog=itbWithdrawadLogService.selectById(id);
    	withdrawadlog.setCashStatus(2);
    	withdrawadlog.setVerifyInfo(verifyInfo);
    	itbWithdrawadLogService.updateById(withdrawadlog);
    	
    	TbUserAssets tbuserassets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", withdrawadlog.getUserId()));
    	Float temp=	new BigDecimal(tbuserassets.getCoin()).add(new BigDecimal(withdrawadlog.getActualAmount())).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    	
    	TbAssetsDetail assetsdetail = new TbAssetsDetail();
		assetsdetail.setId(UUIdUtils.getUUID());
		assetsdetail.setUserId(withdrawadlog.getUserId());
		assetsdetail.setBeforeAmount(tbuserassets.getCoin());
		assetsdetail.setAmount(withdrawadlog.getActualAmount());
		assetsdetail.setAfterAmount(temp);
		assetsdetail.setInOrOut(IncomeOrOut.income.getCode());
		assetsdetail.setType(idicService.getDicData("账变类型", "提现失败返还").getId());
		itbAssetsDetailService.insert(assetsdetail);
    	
		tbuserassets.setCoin(temp);
		itbUserAssetsService.updateById(tbuserassets);
		
//    	AppUser user = iappUserService.selectById(tbUserCashadvance.getUserId());
//    	SMSUtil.doBuySeccuss(user.getPhone(),"【华科闪云】您的金币提现申请系统检测异常，金币已返还到您的账户，请联系客服！");
    	
    	return new ResultInfo<>(true);
    }
}
