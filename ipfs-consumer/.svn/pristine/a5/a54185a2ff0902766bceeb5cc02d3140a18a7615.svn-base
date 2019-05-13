package com.stys.ipfs.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.service.ITbAssetsDetailService;
import com.stys.ipfs.util.ExcelUtil;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Controller
@RequestMapping("/tbAssetsDetail")
public class TbAssetsDetailController extends BaseController {

	@Reference(version = "1.0.0", check = false)
	private ITbAssetsDetailService itbAssetsDetailService;

	@RequestMapping("/*")
	public void toHtml() {

	}

	@RequestMapping("/listData")
	@RequiresPermissions("tbAssetsDetail:view")
	public @ResponseBody ResultInfo<List<TbAssetsDetail>> listData(String phone, String nickname, Integer page,
			Integer limit,String startTime, String endTime, Integer type) {
		Page<TbAssetsDetail> pageObj = itbAssetsDetailService
				.getPageTbAssetsDetail(new Page<TbAssetsDetail>(page, limit),phone, nickname,startTime,  endTime,type);
		return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal()); 
	}

	@RequestMapping("/add")
	@RequiresPermissions("tbAssetsDetail:add")
	public @ResponseBody ResultInfo<Boolean> add(TbAssetsDetail tbAssetsDetail) {
		boolean b = itbAssetsDetailService.insert(tbAssetsDetail);
		return new ResultInfo<>(b);
	}

	@RequestMapping("/delBatch")
	@RequiresPermissions("tbAssetsDetail:del")
	public @ResponseBody ResultInfo<Boolean> delBatch(Integer[] idArr) {
		boolean b = itbAssetsDetailService.deleteBatchIds(Arrays.asList(idArr));
		return new ResultInfo<>(b);
	}

	@RequestMapping("/edit")
	@RequiresPermissions("tbAssetsDetail:edit")
	public @ResponseBody ResultInfo<Boolean> edit(TbAssetsDetail tbAssetsDetail) {
		boolean b = itbAssetsDetailService.updateById(tbAssetsDetail);
		return new ResultInfo<>(b);
	}

	@RequestMapping(value = "/export")
	public String export(HttpServletRequest request, HttpServletResponse response, String endTime, String startTime)
			throws Exception {
		List<TbAssetsDetail> list = itbAssetsDetailService.getListTbAssetsDetail(startTime, endTime);

		// excel标题
		String[] title = { "用户编号", "用户昵称", "用户账户", "账变前金额","账变金额", "账变后金额", "账变类型", "创建时间", "收支类型", "返利用户昵称" };
		String fileName = "账务明细信息" + System.currentTimeMillis() + ".xls";

		String sheetName = "账务明细信息";
		String[][] content = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[title.length];
			TbAssetsDetail obj = list.get(i);
            content[i][0] = obj.getUserId().toString();
			content[i][1] = obj.getNickname();
			content[i][2] = obj.getPhone();
			content[i][3] = obj.getBeforeAmount().toString();
			content[i][4] = obj.getAmount().toString();
			content[i][5] = obj.getAfterAmount().toString();
			content[i][6] = obj.getName();
			content[i][7] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj.getCreateTime());
			content[i][8] = obj.getInOrOut()==1?"收入":"支出";
            content[i][9] = obj.getSunUserId()==null?"": obj.getSunUserId().toString();
		}
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os); 
			//os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.flushBuffer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
