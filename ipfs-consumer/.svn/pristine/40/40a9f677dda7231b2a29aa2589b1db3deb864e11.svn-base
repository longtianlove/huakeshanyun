package com.stys.ipfs.web;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbFeimaWithdrawadLog;
import com.stys.ipfs.service.ITbFeimaWithdrawadLogService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-05-05
 */
@Controller
@RequestMapping("/tbFeimaWithdrawadLog")
public class TbFeimaWithdrawadLogController extends BaseController {

	@Reference(version = "1.0.0")
	private ITbFeimaWithdrawadLogService itbFeimaWithdrawadLogService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/listData")
	@RequiresPermissions("tbFeimaWithdrawadLog:view")
	public @ResponseBody ResultInfo<List<TbFeimaWithdrawadLog>> listDataView(TbFeimaWithdrawadLog tbFeimaWithdrawadLog,
			Integer page, Integer limit, String phone, String realName, String startTime, String endTime) {
		Page<TbFeimaWithdrawadLog> pageObj = itbFeimaWithdrawadLogService.getPageTbFeimaWithdrawadLog(
				new Page<TbFeimaWithdrawadLog>(page, limit), phone, realName, startTime, endTime);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());

	}

	@RequestMapping("/add")
	@RequiresPermissions("tbFeimaWithdrawadLog:add")
	public @ResponseBody ResultInfo<Boolean> add(TbFeimaWithdrawadLog tbFeimaWithdrawadLog) {
		boolean b = itbFeimaWithdrawadLogService.insert(tbFeimaWithdrawadLog);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("tbFeimaWithdrawadLog:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = itbFeimaWithdrawadLogService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("tbFeimaWithdrawadLog:edit")
	public @ResponseBody ResultInfo<Boolean> edit(TbFeimaWithdrawadLog tbFeimaWithdrawadLog) {
		boolean b = itbFeimaWithdrawadLogService.updateById(tbFeimaWithdrawadLog);
		return new ResultInfo<>(b);
	}

}
