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
import com.stys.ipfs.entity.TbFeimauser;
import com.stys.ipfs.service.ITbFeimauserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-04-15
 */
@Controller
@RequestMapping("/tbFeimauser")
public class TbFeimauserController extends BaseController {

	@Reference(version = "1.0.0")
	private ITbFeimauserService itbFeimauserService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/listData")
	@RequiresPermissions("tbFeimauser:view")
	public @ResponseBody ResultInfo<List<TbFeimauser>> listData(TbFeimauser tbFeimauser, Integer page, Integer limit) {
		EntityWrapper<TbFeimauser> wrapper = new EntityWrapper<>(tbFeimauser);
		Page<TbFeimauser> pageObj = itbFeimauserService.selectPage(new Page<TbFeimauser>(page, limit), wrapper);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
	}

	@RequestMapping("/add")
	@RequiresPermissions("tbFeimauser:add")
	public @ResponseBody ResultInfo<Boolean> add(TbFeimauser tbFeimauser) {
		boolean b = itbFeimauserService.insert(tbFeimauser);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("tbFeimauser:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = itbFeimauserService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("tbFeimauser:edit")
	public @ResponseBody ResultInfo<Boolean> edit(TbFeimauser tbFeimauser) {
		boolean b = itbFeimauserService.updateById(tbFeimauser);
		return new ResultInfo<>(b);
	}

}
