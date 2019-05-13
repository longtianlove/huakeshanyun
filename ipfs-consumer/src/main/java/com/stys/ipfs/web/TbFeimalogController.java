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
import com.stys.ipfs.entity.TbFeimalog;
import com.stys.ipfs.service.ITbFeimalogService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-04-15
 */
@Controller
@RequestMapping("/tbFeimalog")
public class TbFeimalogController extends BaseController {

	@Reference(version = "1.0.0")
	private ITbFeimalogService itbFeimalogService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/listData")
	@RequiresPermissions("tbFeimalog:view")
	public @ResponseBody ResultInfo<List<TbFeimalog>> listData(TbFeimalog tbFeimalog, Integer page, Integer limit) {
		EntityWrapper<TbFeimalog> wrapper = new EntityWrapper<>(tbFeimalog);
		Page<TbFeimalog> pageObj = itbFeimalogService.selectPage(new Page<TbFeimalog>(page, limit), wrapper);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
	}

	@RequestMapping("/add")
	@RequiresPermissions("tbFeimalog:add")
	public @ResponseBody ResultInfo<Boolean> add(TbFeimalog tbFeimalog) {
		boolean b = itbFeimalogService.insert(tbFeimalog);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("tbFeimalog:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = itbFeimalogService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("tbFeimalog:edit")
	public @ResponseBody ResultInfo<Boolean> edit(TbFeimalog tbFeimalog) {
		boolean b = itbFeimalogService.updateById(tbFeimalog);
		return new ResultInfo<>(b);
	}

}
