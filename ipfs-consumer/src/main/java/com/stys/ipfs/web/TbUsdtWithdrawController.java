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
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbUsdtWithdraw;
import com.stys.ipfs.service.ITbUsdtWithdrawService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-04-26
 */
@Controller
@RequestMapping("/tbUsdtWithdraw")
public class TbUsdtWithdrawController extends BaseController {

	@Reference(version = "1.0.0", check = false)
	private ITbUsdtWithdrawService itbUsdtWithdrawService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/listData")
	@RequiresPermissions("tbUsdtWithdraw:view")
	public @ResponseBody ResultInfo<List<TbUsdtWithdraw>> listData(TbUsdtWithdraw tbUsdtWithdraw, Integer page,
			Integer limit) {
		EntityWrapper<TbUsdtWithdraw> wrapper = new EntityWrapper<>(tbUsdtWithdraw);
		Page<TbUsdtWithdraw> pageObj = itbUsdtWithdrawService.selectPage(new Page<TbUsdtWithdraw>(page, limit),
				wrapper);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
	}

	@RequestMapping("/add")
	@RequiresPermissions("tbUsdtWithdraw:add")
	public @ResponseBody ResultInfo<Boolean> add(TbUsdtWithdraw tbUsdtWithdraw) {
		boolean b = itbUsdtWithdrawService.insert(tbUsdtWithdraw);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("tbUsdtWithdraw:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = itbUsdtWithdrawService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("tbUsdtWithdraw:edit")
	public @ResponseBody ResultInfo<Boolean> edit(TbUsdtWithdraw tbUsdtWithdraw) {
		boolean b = itbUsdtWithdrawService.updateById(tbUsdtWithdraw);
		return new ResultInfo<>(b);
	}

}
