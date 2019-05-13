package com.stys.ipfs.web.app;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.TbAssetsDetail;
import com.stys.ipfs.entity.TbContract;
import com.stys.ipfs.entity.TbPersonalInfo;
import com.stys.ipfs.entity.TbProduct;
import com.stys.ipfs.entity.TbUserBuyLog;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.ITbContractService;
import com.stys.ipfs.service.ITbPersonalInfoService;
import com.stys.ipfs.service.ITbProductService;
import com.stys.ipfs.service.ITbUserBuyLogService;
import com.stys.ipfs.util.AppConstant;
import com.stys.ipfs.util.BigDecimalUtils;
import com.stys.ipfs.util.CnNumberUtils;
import com.stys.ipfs.util.DateUtil;
import com.stys.ipfs.util.RandomNumUtil;
import com.stys.ipfs.util.UUIdUtils;
import com.stys.ipfs.util.WordUtil;

@RestController
@RequestMapping("/tbr")
public class AppContractConroller extends AppController {

	@Reference(version = "1.0.0", check = false)
	private ITbContractService itbContractService;

	@Reference(version = "1.0.0", check = false)
	private ITbUserBuyLogService itbUserBuyLogService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbProductService itbProductService;

	@Reference(version = "1.0.0", check = false)
	private ITbPersonalInfoService itbPersonalInfoService;
	
	@Value("${cbs.imagesPath}")
	private String localImagesPath;

	/**
	 * 
	 *	 生成合同
	 * 
	 * @api {post} /tbr/createContract 	生成合同（必须先实名认证）
	 * @apiParam {Integer} userId 用户id
	 * @apiGroup hetong
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/createContract
	 * @return
	 */
	@RequestMapping("/createContract")
	public @ResponseBody AppResultInfo<?> getContract(Integer userId) {
		List<TbUserBuyLog> list = itbUserBuyLogService
				.selectList(new EntityWrapper<TbUserBuyLog>().eq("user_id", userId).eq("status", 1));// 未生成合同的
		String phone = iappUserService.selectById(userId).getPhone();
		TbPersonalInfo tbpersonalinfo = itbPersonalInfoService
				.selectOne(new EntityWrapper<TbPersonalInfo>().eq("user_id", userId));
		TbProduct tbproduct = itbProductService.selectList(null).get(0);
		// 获取单价
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("width", 100);
		header.put("height", 100);
		header.put("type", "png");
		header.put("content", localImagesPath+"/gongzhang/gongz.png");
		String unitPrice = BigDecimalUtils.Divide(tbproduct.getProductPreferentialPrice().toString(),
				tbproduct.getProductType().toString());
		// word 模板路径 D:/budaowen/word/target/classes/conf/5082.docx
		if (list == null || list.size() == 0) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "当前用户合同已全部生成!");
		}
		for (TbUserBuyLog userbuylog : list) {
			int i=itbContractService.selectCount( new EntityWrapper<TbContract>());
			String contractnumber = RandomNumUtil.recountNew(i);
			Map<String, Object> map = new HashMap<>();
			Date date = userbuylog.getCreateTime();
			String time = new SimpleDateFormat("yyyyMMdd").format(date);
			map.put("${tupian}", header);
			map.put("${phone}", phone);// 账号
			map.put("${idCard}", tbpersonalinfo.getIdcard());// 身份证号码
			map.put("${realName}", tbpersonalinfo.getRealName());// 姓名
			map.put("${type}", userbuylog.getProductType().toString());// 购买了对少T
			map.put("${unitPrice}", new BigDecimal(unitPrice).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());// 单价
			map.put("${money}", userbuylog.getProductPrice().toString());// 总价
			map.put("${RMB}", CnNumberUtils.toUppercase(userbuylog.getProductPrice()));// 大写的总价
			map.put("${year}", time.substring(0, 4));// 合同签订日期
			map.put("${month}", time.substring(4, 6));// 合同签订日期
			map.put("${day}", time.substring(6, time.length()));// 合同签订日期
			map.put("${contractNumber}", contractnumber);
			String result = WordUtil.replaceAndGenerateWord(localImagesPath+"/muban/5082.docx", map,
					localImagesPath+"/word/"+UUIdUtils.getUUID()+".docx");
			if (result.equals("fail")) {
				break;
			}
			TbContract tbcontract = new TbContract();
			tbcontract.setContractAddress(result);
			tbcontract.setContractNumber(contractnumber);
			tbcontract.setUserId(userId);
			tbcontract.setCreateDate(new Date());
			itbContractService.insertOrUpdate(tbcontract);
			userbuylog.setStatus(2);
			itbUserBuyLogService.updateById(userbuylog);
		}
		return new AppResultInfo<>("合同生成完成！");
	}
	/**
	 * 
	 *	 下载合同
	 * 
	 * @api {post} /tbr/downContract 	下载合同
	 * @apiParam {String} filePath 合同地址
	 * @apiGroup hetong
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/downContract
	 * @return
	 */
	@RequestMapping("/downContract")
	public @ResponseBody AppResultInfo<?> downContract(String filePath,HttpServletRequest request, HttpServletResponse response){
		int i= WordUtil.doExport(filePath, request, response);
		if(i==1) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "下载出错！");
		}else if(i==2){
			return new AppResultInfo<>(AppConstant.STATUE_1, "下载资源不存在！");
			
		}
		return new AppResultInfo<>("下载完成！");
	}
	
	/**
	 * 
	 *	 合同列表展示
	 * 
	 * @api {post} /tbr/getContractDetail 	 合同列表展示
	 * @apiParam {Integer} userId 用户id
	 * @apiParam {Integer} page 第几页
	 * @apiParam {Integer} limit 每页多少条
	 * @apiGroup hetong
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getContractDetail
	 * @return
	 */
	
	@RequestMapping("/getContractDetail")
	public @ResponseBody AppResultInfo<?> getContractDetail(Integer userId,Integer page ,Integer limit){
		Page<TbContract> pageObj=itbContractService.selectPage( new Page<TbContract>(page, limit),new EntityWrapper<TbContract>().eq("user_id", userId));
		return new AppResultInfo<>(pageObj);
	}


}
