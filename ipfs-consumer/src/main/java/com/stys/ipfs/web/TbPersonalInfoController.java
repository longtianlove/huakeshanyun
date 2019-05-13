package com.stys.ipfs.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.dto.ResultInfo;
import com.stys.ipfs.entity.AppUserinfo;
import com.stys.ipfs.entity.TbFeimalog;
import com.stys.ipfs.entity.TbFeimauser;
import com.stys.ipfs.entity.TbPersonalInfo;
import com.stys.ipfs.service.FeimaService;
import com.stys.ipfs.service.IAppUserinfoService;
import com.stys.ipfs.service.ITbFeimalogService;
import com.stys.ipfs.service.ITbFeimauserService;
import com.stys.ipfs.service.ITbPersonalInfoService;
import com.stys.ipfs.util.AppConstant;
import com.stys.ipfs.util.FileUtil;
import com.stys.ipfs.util.HttpUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Controller
@RequestMapping("/tbPersonalInfo")
public class TbPersonalInfoController extends BaseController {

    @Reference(version = "1.0.0", check = false)
    private ITbPersonalInfoService itbPersonalInfoService;
    
	@Reference(version = "1.0.0", check = false)
	private IAppUserinfoService iappUserinfoService;
    
    @Reference(version = "1.0.0")
	private ITbFeimalogService itbFeimalogService;

	@Reference(version = "1.0.0")
	private ITbFeimauserService itbFeimauserService;

	@Value("${userAuthRequestURL}")
	private String userAuthRequestURL;

	@Value("${merchantNo}")
	private String merchantNo;

	@Value("${cbs.imagesPath}")
	private String localImagesPath;

	@Value("${publicKey}")
	private String publicKey;

	@Value("${privateKey}")
	private String privateKey;

	@Value("${feimaPublicKey}")
	private String feimaPublicKey;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("tbPersonalInfo:view")
    public @ResponseBody
        ResultInfo<List<TbPersonalInfo>> listData( String accont, String nickname,String realName, Integer page, Integer limit){
        Page<TbPersonalInfo> pageObj = itbPersonalInfoService.getPageTbPersonalInfo(new Page<TbPersonalInfo>(page,limit), accont, nickname, realName);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("tbPersonalInfo:add")
    public @ResponseBody
        ResultInfo<Boolean> add(TbPersonalInfo tbPersonalInfo){
        boolean b = itbPersonalInfoService.insert(tbPersonalInfo);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("tbPersonalInfo:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = itbPersonalInfoService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("tbPersonalInfo:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(TbPersonalInfo tbPersonalInfo){
        boolean b = itbPersonalInfoService.updateById(tbPersonalInfo);
        return new ResultInfo<>(b);
    }
    @RequestMapping("/registerFMT")
    public @ResponseBody
    ResultInfo<Boolean> registerFMT(Integer id){
    	TbPersonalInfo tbPersonalInfo=itbPersonalInfoService.selectById(id);
    	//认证信息
    	AppResultInfo<?> appre=feimaRegister(tbPersonalInfo.getIdcard(), tbPersonalInfo.getRealName(), tbPersonalInfo.getPhone(),
    			tbPersonalInfo.getUserId(), tbPersonalInfo.getIdentityImgFront(), tbPersonalInfo.getIdentityImgReverse(), tbPersonalInfo);
    	
    	return new ResultInfo<>(appre.getState().toString(),appre.getMsg());
    }
	/**
	 * 
	 * @param idCard
	 * @param name
	 * @param phone
	 * @param userId
	 * @param identityImgFront
	 * @param personalinfo
	 */
	private AppResultInfo<?> feimaRegister(String idCard, String name, String phone, Integer userId,
			String identityImgFront, String identityImgReverse, TbPersonalInfo personalinfo) {
		FeimaService feimaService = new FeimaService();
		feimaService.feimaPublicKey = feimaPublicKey;
		feimaService.privateKey = privateKey;
		feimaService.publicKey = publicKey;

		AppUserinfo appuserinfo = iappUserinfoService.selectOne(new EntityWrapper<AppUserinfo>().eq("user_id", userId));
		String result = feimaService.userRegister(merchantNo, String.valueOf(userId), phone, appuserinfo.getNickname(),
				name, idCard);
		JSONObject jsonObject = JSON.parseObject(result);
		String msg = jsonObject.getString("msg");
		if (msg.equals("success")) {
			// 注册时飞码通成功
			Map<String, String> params = new HashMap<String, String>();
			params.put("merchantNo", merchantNo);

			String authMsgObject = jsonObject.getString("obj");
			JSONObject authjsonObject2 = JSON.parseObject(authMsgObject);
			String account = authjsonObject2.getString("account");
			params.put("account", account);
			params.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));

			// 设置file的name，路径
			Map<String, String> fileMap = new HashMap<String, String>();
			String localImagesPath_font = localImagesPath + userId + ".png";
			String localImagesPath_Back = localImagesPath + userId + "1.png";
			HttpUtil.downloadPicture(identityImgFront, localImagesPath_font);

			HttpUtil.downloadPicture(identityImgReverse, localImagesPath_Back);
			fileMap.put("idCardFrontFile", localImagesPath_font);
			fileMap.put("idCardBackFile", localImagesPath_Back);

			String authResult = feimaService.commonRequest(userAuthRequestURL, params, fileMap, "");
			// 删除上传的身份证文件
			FileUtil.deleteFile(localImagesPath_font);
			FileUtil.deleteFile(localImagesPath_Back);
			JSONObject jsonIdcard = JSON.parseObject(authResult);
			String cardMsg = jsonIdcard.getString("msg");
			logger.error("认证msg:" + cardMsg);
			if (cardMsg.equals("success")) {
				// 认证成功
				TbFeimauser tbFeimauser = new TbFeimauser();
				tbFeimauser.setAccount(account);
				tbFeimauser.setUserId(userId);
				itbFeimauserService.insertOrUpdate(tbFeimauser);

				// 后台认证身份证
				if (itbPersonalInfoService.insertOrUpdate(personalinfo)) {
					logger.info("后台认证身份证");
				}
			} else {
				itbPersonalInfoService.deleteById(personalinfo.getId());
				return new AppResultInfo<>(AppConstant.STATUE_1, cardMsg);
			}
			// 飞码通认证失败
			TbFeimalog tbFeimalog = new TbFeimalog();
			tbFeimalog.setLogData(cardMsg);
			tbFeimalog.setUserId(userId);
			itbFeimalogService.insert(tbFeimalog);

		} else {
			logger.error("飞码通认证：" + msg);
			return new AppResultInfo<>(AppConstant.STATUE_1, msg);
		}
		return null;
	}
    
}

