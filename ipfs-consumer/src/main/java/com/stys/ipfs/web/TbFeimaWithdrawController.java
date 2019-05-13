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
import com.stys.ipfs.entity.TbFeimaWithdraw;
import com.stys.ipfs.service.ITbFeimaWithdrawService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-05-05
 */
@Controller
@RequestMapping("/tbFeimaWithdraw")
public class TbFeimaWithdrawController extends BaseController {

	@Reference(version = "1.0.0")
	private ITbFeimaWithdrawService itbFeimaWithdrawService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/listData")
	@RequiresPermissions("tbFeimaWithdraw:view")
	public @ResponseBody ResultInfo<List<TbFeimaWithdraw>> listData(TbFeimaWithdraw tbFeimaWithdraw, Integer page,
			Integer limit) {
		EntityWrapper<TbFeimaWithdraw> wrapper = new EntityWrapper<>(tbFeimaWithdraw);
		Page<TbFeimaWithdraw> pageObj = itbFeimaWithdrawService.selectPage(new Page<TbFeimaWithdraw>(page, limit),
				wrapper);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
	}

	@RequestMapping("/add")
	@RequiresPermissions("tbFeimaWithdraw:add")
	public @ResponseBody ResultInfo<Boolean> add(TbFeimaWithdraw tbFeimaWithdraw) {
		boolean b = itbFeimaWithdrawService.insert(tbFeimaWithdraw);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("tbFeimaWithdraw:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = itbFeimaWithdrawService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("tbFeimaWithdraw:edit")
	public @ResponseBody ResultInfo<Boolean> edit(TbFeimaWithdraw tbFeimaWithdraw) {
		boolean b = itbFeimaWithdrawService.updateById(tbFeimaWithdraw);
		return new ResultInfo<>(b);
	}

}
