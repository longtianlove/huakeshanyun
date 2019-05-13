package com.stys.ipfs.web;

import java.util.Arrays;
import java.util.Date;
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
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.entity.TbProduct;
import com.stys.ipfs.entity.TbUserAssets;
import com.stys.ipfs.entity.TbUserBuyLog;
import com.stys.ipfs.entity.TbVipminer;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.service.ITbProductService;
import com.stys.ipfs.service.ITbUserAssetsService;
import com.stys.ipfs.service.ITbUserBuyLogService;
import com.stys.ipfs.service.ITbVipminerService;
import com.stys.ipfs.util.BigDecimalUtils;
import com.stys.ipfs.util.StringUtils;
import com.stys.ipfs.util.UUIdUtils;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-17
 */
@Controller
@RequestMapping("/tbVipminer")
public class TbVipminerController extends BaseController {
	
	@Reference(version = "1.0.0", check = false)
	private ITbUserAssetsService itbUserAssetsService;

    @Reference(version = "1.0.0", check = false)
    private ITbVipminerService itbVipminerService;
    
    @Reference(version = "1.0.0", check = false)
    private ITbProductService itbProductService;
    
    @Reference(version = "1.0.0", check = false)
	private IDicService idicService;
    
	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService itbAssetsDetailService;
	
	@Reference(version = "1.0.0", check = false)
	private ITbUserBuyLogService itbUserBuyLogService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbVipminer:view")
    public @ResponseBody
        ResultInfo<List<TbVipminer>> listData(String nickname, Integer page, Integer limit){
        Page<TbVipminer> pageObj = itbVipminerService.getPageTbVipminer(new Page<TbVipminer>(page,limit), nickname);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbVipminer:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbVipminer tbVipminer){
        boolean b = itbVipminerService.insert(tbVipminer);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbVipminer:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbVipminerService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbVipminer:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbVipminer tbVipminer){
        boolean b = itbVipminerService.updateById(tbVipminer);
        return new ResultInfo<>(b);
    }
    @RequestMapping("/rebuy")
   	public @ResponseBody ResultInfo<?> personBuy(Integer id) {
    	TbVipminer tbVipminer=itbVipminerService.selectById(id);
    	if(StringUtils.isEmpty(tbVipminer)) {
    		return new ResultInfo<>("1","数据异常请重试！");
    	}
    	Integer userId=tbVipminer.getUserId();
    	// 查询余额
		TbUserAssets newtbUserAssets = itbUserAssetsService
				.selectOne(new EntityWrapper<TbUserAssets>().eq("user_id", userId));
		TbProduct tbProduct=itbProductService.selectOne(new EntityWrapper<TbProduct>().eq("product_type", 333));
		if (newtbUserAssets.getCoin() < tbProduct.getProductPreferentialPrice()) {
			return new ResultInfo<>("1","余额不足！");
		}
    	Float outNumber = Float.parseFloat(BigDecimalUtils.multiply(tbProduct.getProductPreferentialPrice().toString(),
    			"5"));
    	Float newLimit=tbVipminer.getLimitCoin()+outNumber;
    	tbVipminer.setLimitCoin(newLimit);
    	Float befor=newtbUserAssets.getCoin();
    	// 用户余额
		Float balance = Float.parseFloat(BigDecimalUtils.subtract(befor.toString(),
				tbProduct.getProductPreferentialPrice().toString()));
		newtbUserAssets.setCoin(balance);
		newtbUserAssets.setStorage(newtbUserAssets.getStorage() + tbProduct.getProductType());
		itbUserAssetsService.updateById(newtbUserAssets);
		
		itbVipminerService.updateById(tbVipminer);
    	
    	//添加购买记录
    	TbAssetsDetail tbAssetsDetail = new TbAssetsDetail();
		tbAssetsDetail.setId(UUIdUtils.getUUID());
		tbAssetsDetail.setAfterAmount(balance);
		tbAssetsDetail.setAmount(tbProduct.getProductPreferentialPrice());
		tbAssetsDetail.setBeforeAmount(befor);
		tbAssetsDetail.setInOrOut(IncomeOrOut.expend.getCode());
		Dic dic = idicService.getDicData("账变类型", "金币购买");
		tbAssetsDetail.setType(dic.getId());
		tbAssetsDetail.setUserId(userId);

		itbAssetsDetailService.insert(tbAssetsDetail);
		
		//添加购买记录
		TbUserBuyLog buylog =new TbUserBuyLog();
		buylog.setProductPrice(tbProduct.getProductPreferentialPrice());
		buylog.setProductType(tbProduct.getProductType());
		buylog.setUserId(userId);
		buylog.setCreateTime(new Date());
		buylog.setStatus(1);
		itbUserBuyLogService.insert(buylog);
    	
   		return new ResultInfo<>(true);
   	}
}

