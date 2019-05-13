package com.stys.ipfs.web;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.code.IncomeOrOut;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.entity.TbOfflinePayment;
import com.stys.ipfs.entity.TbUserAssets;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbOfflinePaymentService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.util.BigDecimalUtils;
import com.stys.ipfs.util.UUIdUtils;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-21
 */
@Controller
@RequestMapping("/tbOfflinePayment")
public class TbOfflinePaymentController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private ITbOfflinePaymentService itbOfflinePaymentService;
    
	@Reference(version = "1.0.0")
	private ITbUserAssetsService itbUserAssetsService;
	
	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService itbAssetsDetailService;
	
	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbOfflinePayment:view")
    public @ResponseBody
        ResultInfo<List<TbOfflinePayment>> listData(String realName,String phone,Integer status, Integer page, Integer limit){
        Page<TbOfflinePayment> pageObj = itbOfflinePaymentService.getPageTbOfflinePayment(new Page<TbOfflinePayment>(page,limit),phone,realName,status);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbOfflinePayment:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbOfflinePayment tbOfflinePayment){
        boolean b = itbOfflinePaymentService.insert(tbOfflinePayment);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbOfflinePayment:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbOfflinePaymentService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbOfflinePayment:edit")
    public @ResponseBody
    ResultInfo<Boolean> edit(TbOfflinePayment tbOfflinePayment){
    	boolean b = itbOfflinePaymentService.updateById(tbOfflinePayment);
    	return new ResultInfo<>(b);
    }
    @RequestMapping("/fisrtApprove")
    @RequiresPermissions("tbOfflinePayment:fisrtApprove")
    public @ResponseBody
        ResultInfo<Boolean> recharge(TbOfflinePayment tbOfflinePayment){
    	//不通过
    	if(tbOfflinePayment.getStatus()==2) {
    		 itbOfflinePaymentService.updateById(tbOfflinePayment);
    	}else {
    		tbOfflinePayment.setRemark("同意");
    		itbOfflinePaymentService.updateById(tbOfflinePayment);
    	}
        return new ResultInfo<>(true);
    }
    @RequestMapping("/approved")
    @RequiresPermissions("tbOfflinePayment:approved")
    public @ResponseBody
    ResultInfo<Boolean> rechargeApproving(TbOfflinePayment tbOfflinePayment){
    	if(tbOfflinePayment.getStatus()==2) {
    		itbOfflinePaymentService.updateById(tbOfflinePayment);
    	}else {
    		TbOfflinePayment payment=itbOfflinePaymentService.selectById(tbOfflinePayment.getId());
    		this.getRecharge(payment.getId(),payment.getPrice());
    	}
    	return new ResultInfo<>(true);
    }
    /**
     * 	充值
     * @param tbOfflinePayment
     * @return
     */
    public ResultInfo<Boolean> getRecharge(Integer id,Double price){
    	TbOfflinePayment tbOfflinePayment=itbOfflinePaymentService.selectById(id);
    	tbOfflinePayment.setStatus(1);
    	tbOfflinePayment.setRemark("充值成功！");
    	//
    	TbUserAssets newtbUserAssets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", tbOfflinePayment.getUserId()));
    	
    	Double coin=	newtbUserAssets.getCoin();
    	Double t_value = Double.parseDouble(BigDecimalUtils.add(price.toString(), coin.toString()));
    	
    	TbAssetsDetail entity = new TbAssetsDetail();
		entity.setId(UUIdUtils.getUUID());
		entity.setUserId(tbOfflinePayment.getUserId());
		entity.setBeforeAmount(coin);
		entity.setAmount(tbOfflinePayment.getPrice());
		entity.setAfterAmount(t_value);
		entity.setType(idicService.getDicData("账变类型", "线下充值").getId());
		entity.setInOrOut(IncomeOrOut.income.getCode());
		entity.setAccountType(1);
		itbAssetsDetailService.insert(entity);
		newtbUserAssets.setCoin(t_value);
		itbUserAssetsService.updateById(newtbUserAssets);
    	
    	boolean b = itbOfflinePaymentService.updateById(tbOfflinePayment);
    	return new ResultInfo<>(b);
    }

}

