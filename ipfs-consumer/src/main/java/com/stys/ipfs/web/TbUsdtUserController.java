package com.stys.ipfs.web;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.dto.TbUsdtVO;
import com.stys.ipfs.entity.TbUsdtUser;
import com.stys.ipfs.service.ITbUsdtUserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Controller
@RequestMapping("/tbUsdtUser")
public class TbUsdtUserController extends BaseController {

	@Reference(version = "1.0.0", check = false)
	private ITbUsdtUserService itbUsdtUserService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/listData")
	@RequiresPermissions("tbUsdtUser:view")
	public @ResponseBody ResultInfo<List<TbUsdtUser>> listData(TbUsdtUser tbUsdtUser, Integer page, Integer limit) {
		EntityWrapper<TbUsdtUser> wrapper = new EntityWrapper<>(tbUsdtUser);
		Page<TbUsdtUser> pageObj = itbUsdtUserService.selectPage(new Page<TbUsdtUser>(page, limit), wrapper);

		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
	}

	@RequestMapping("/listData2")
	@RequiresPermissions("tbUsdtUser:view2")
	public @ResponseBody ResultInfo<List<TbUsdtVO>> listData2(String queryStr, Integer page, Integer limit) {
		System.out.println(queryStr);
		TbUsdtVO tbUsdtVO = new TbUsdtVO();
		if (null != queryStr) {
			JSONObject jsonObject = JSONObject.parseObject(queryStr);
			tbUsdtVO = JSONObject.toJavaObject(jsonObject, TbUsdtVO.class);
		}

		EntityWrapper<TbUsdtVO> wrapper = new EntityWrapper<TbUsdtVO>();
		Page<TbUsdtVO> pageObj = itbUsdtUserService.selectPageTbUsdtVO(new Page<TbUsdtVO>(page, limit), wrapper,
				tbUsdtVO);

		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
	}

	@RequestMapping("/add")
	@RequiresPermissions("tbUsdtUser:add")
	public @ResponseBody ResultInfo<Boolean> add(TbUsdtUser tbUsdtUser) {
		boolean b = itbUsdtUserService.insert(tbUsdtUser);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("tbUsdtUser:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = itbUsdtUserService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("tbUsdtUser:edit")
	public @ResponseBody ResultInfo<Boolean> edit(TbUsdtUser tbUsdtUser) {
		boolean b = itbUsdtUserService.updateById(tbUsdtUser);
		return new ResultInfo<>(b);
	}

}
