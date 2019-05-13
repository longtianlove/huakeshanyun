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
import com.stys.ipfs.entity.TbFeimaOrder;
import com.stys.ipfs.service.ITbFeimaOrderService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-04-16
 */
@Controller
@RequestMapping("/tbFeimaOrder")
public class TbFeimaOrderController extends BaseController {

	@Reference(version = "1.0.0")
	private ITbFeimaOrderService itbFeimaOrderService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/listData")
	@RequiresPermissions("tbFeimaOrder:view")
	public @ResponseBody ResultInfo<List<TbFeimaOrder>> listData(TbFeimaOrder tbFeimaOrder, Integer page,
			Integer limit) {
		EntityWrapper<TbFeimaOrder> wrapper = new EntityWrapper<>(tbFeimaOrder);
		Page<TbFeimaOrder> pageObj = itbFeimaOrderService.selectPage(new Page<TbFeimaOrder>(page, limit), wrapper);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
	}

	@RequestMapping("/add")
	@RequiresPermissions("tbFeimaOrder:add")
	public @ResponseBody ResultInfo<Boolean> add(TbFeimaOrder tbFeimaOrder) {
		boolean b = itbFeimaOrderService.insert(tbFeimaOrder);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("tbFeimaOrder:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = itbFeimaOrderService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("tbFeimaOrder:edit")
	public @ResponseBody ResultInfo<Boolean> edit(TbFeimaOrder tbFeimaOrder) {
		boolean b = itbFeimaOrderService.updateById(tbFeimaOrder);
		return new ResultInfo<>(b);
	}

}
