package com.stys.ipfs.web.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.dto.AppUserInfoVo;
import com.stys.ipfs.entity.AppUser;
import com.stys.ipfs.entity.AppUserinfo;
import com.stys.ipfs.entity.TbInvite;
import com.stys.ipfs.entity.TbPersonalInfo;
import com.stys.ipfs.ex.TokenException;
import com.stys.ipfs.service.IAppLoginLogService;
import com.stys.ipfs.service.IAppUserService;
import com.stys.ipfs.service.IAppUserinfoService;
import com.stys.ipfs.service.ITbInviteService;
import com.stys.ipfs.service.ITbPersonalInfoService;
import com.stys.ipfs.util.AliyunOSSClientUtil;
import com.stys.ipfs.util.AppConstant;
import com.stys.ipfs.util.AuthenticationUtil;
import com.stys.ipfs.util.CoinUtil;
import com.stys.ipfs.util.ComUtil;
import com.stys.ipfs.util.JWTUtil;
import com.stys.ipfs.util.OSSClientConstants;
import com.stys.ipfs.util.ReduceImgUtil;
import com.stys.ipfs.util.SecuritySHA1Utils;
import com.stys.ipfs.util.StringUtils;
import com.stys.ipfs.util.UUIdUtils;

@RestController
@RequestMapping("/tbr")
public class AppHomeController extends AppController {

	@Reference(version = "1.0.0", check = false)
	private ITbInviteService itbInviteService;

	@Reference(version = "1.0.0", check = false)
	private IAppLoginLogService iappLoginLogService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserinfoService iappUserinfoService;

	@Reference(version = "1.0.0", check = false)
	private IAppUserService iappUserService;

	@Reference(version = "1.0.0", check = false)
	private ITbPersonalInfoService itbPersonalInfoService;

	/**
	 * 更新头像
	 * 
	 * @api {post} /tbr/changeAvater 更新头像
	 * @apiGroup userBase
	 * @apiDescription 详细描述：更新头像
	 * @apiParam {String} url 头像地址
	 * @apiParam {String} nickname 用户昵称
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/changeAvater
	 *
	 * @return
	 */
	@RequestMapping("/changeAvater")
	public @ResponseBody AppResultInfo<?> changeAvater(String url, String nickname, HttpServletRequest request) {
		String phone = getPhoneBytoken(request);
		System.err.println(url);
		AppUser appuser = iappUserService.selectOne(new EntityWrapper<AppUser>().eq("phone", phone));
		AppUserinfo appuserinfo = iappUserinfoService
				.selectOne(new EntityWrapper<AppUserinfo>().eq("user_id", appuser.getId()));
		if (!StringUtils.isEmpty(appuserinfo)) {
			appuserinfo.setAvater(url);
			appuserinfo.setNickname(nickname);
			if (iappUserinfoService.updateById(appuserinfo)) {
				return new AppResultInfo<>("修改成功");
			}
		}
		return new AppResultInfo<>(-1, "更新失败");
	}

	/**
	 * 单个文件上传
	 * 
	 * @api {post} /tbr/uploadImg 单个文件上传
	 * @apiGroup userBase
	 * @apiDescription 详细描述：单个文件上传（包含视频上传）视频不能大于20M
	 * @apiParam {MultipartFile} file
	 * @apiParam request
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/uploadImg
	 * @return
	 */
	@RequestMapping("/uploadImg")
	public @ResponseBody AppResultInfo<?> upImgForOss(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws IOException {
		if (file.isEmpty()) {
			return new AppResultInfo<>(-1, "文件为空");
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		String phone = getPhoneBytoken(request);
		if (ResourceBundle.getBundle("config/thirdParty").getString("suffix_name")
				.indexOf(suffixName.toLowerCase().trim()) == -1) {
			return new AppResultInfo<>(-1, "上传文件格式错误");
		}

		// 初始化OSSClient
		String imagePath = ResourceBundle.getBundle("config/thirdParty").getString("uploadimg_path");
		String src_dest_path = imagePath + phone + File.separator + fileName;
		// 图片原路径
		File dest = new File(src_dest_path);
		// 压缩后路劲
		File temp = new File(imagePath + phone + File.separator + UUIdUtils.getUUID() + ".png");
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		if (!temp.getParentFile().exists()) {
			temp.getParentFile().mkdirs();
		}
		file.transferTo(dest);
		// 图片压缩像素
		int[] arr = ReduceImgUtil.getImgWidthHeight(dest);
		ReduceImgUtil.reduceImg(src_dest_path, temp.getCanonicalPath(), arr[0], arr[1], null);

		OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
		String[] s = AliyunOSSClientUtil.uploadObject2OSS(ossClient, temp, OSSClientConstants.BACKET_NAME, phone);
		dest.delete();
		temp.delete();
		logger.info("文件路径:" + s[1]);
		String url = AliyunOSSClientUtil.getUrl(ossClient, OSSClientConstants.BACKET_NAME, s[1]);
		logger.info("访问网址路径:" + url); //
		return new AppResultInfo<>(url);
	}

	private String getPhoneBytoken(HttpServletRequest request) {

		if (ComUtil.isEmpty(request.getHeader("token"))) {

			throw new TokenException("-4", "您无权操作！");
		}
		String token = request.getHeader("token");
		String phone = JWTUtil.getUsername(token);
		return phone;
	}

	/**
	 * 修改支付密码
	 * 
	 * @api {post} /tbr/changePaymentCode 修改支付密码
	 * @apiGroup userBase
	 * @apiDescription 详细描述：修改支付密码
	 * @apiParam {String} phone 手机号
	 * @apiParam {String} passWord 新密码
	 * @apiParam {String} code 验证码
	 * @apiParam {String} sessionId 回话Id
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/changePaymentCode
	 * @return
	 */
	@RequestMapping("/changePaymentCode")
	public @ResponseBody AppResultInfo<?> changePaymentCode(String passWord, String phone, String code,
			String sessionId, ServletRequest request, ServletResponse response) {
		if (verifySession(phone, sessionId, request, response, code) != AppConstant.STATUE_200) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "请重新发送验证码");
		}
		AppUser appuser = iappUserService.selectOne(new EntityWrapper<AppUser>().eq("phone", phone));
		String temp_newWord = null;
		try {

			temp_newWord = SecuritySHA1Utils.shaEncode(passWord.trim());
		} catch (Exception e) {
			logger.info("=======密码解密异常========");
			return new AppResultInfo<>(AppConstant.STATUE_1, "请重新输入支付密码！");
		}

		appuser.setPaymentCode(temp_newWord);
		iappUserService.updateById(appuser);
		return new AppResultInfo<>(AppConstant.STATUE_200, "修改成功！");

	}

	/**
	 * 判断验证码
	 */
	private int verifySession(String phone, String sessionId, ServletRequest request, ServletResponse response,
			String code) {
		if (ComUtil.isEmpty(sessionId)) {
			return AppConstant.STATUE_1;
		}
		SessionKey sessionKey = new WebSessionKey(sessionId, request, response);
		Session session = SecurityUtils.getSecurityManager().getSession(sessionKey);
		if (null != session) {
			if (StringUtils.strIsNull(code) || !code.equals(session.getAttribute(phone))) {
				return AppConstant.STATUE_1;
			}
			session.setAttribute(phone, null);
			return AppConstant.STATUE_200;
		} else {
			return AppConstant.STATUE_1;
		}

	}

	/**
	 * 实名认证
	 * 
	 * @api {post} /tbr/authentication 实名认证
	 * @apiGroup userBase
	 * @apiDescription 详细描述：实名认证
	 * @apiParam {String} bankCard 银行卡号
	 * @apiParam {String} idCard 身份证号码
	 * @apiParam {String} name 真实姓名
	 * @apiParam {String} phone 手机号码
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {String} identityImgFront 身份证图片正面地址
	 * @apiParam {String} identityImgReverse 身份证反面正面地址
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/authentication
	 * @return
	 */
	@RequestMapping("/authentication")
	public @ResponseBody AppResultInfo<?> authenticationForUser(String bankCard, String idCard, String name,
			String phone, Integer userId, String identityImgFront, String identityImgReverse) {
		TbPersonalInfo personalinfo = AuthenticationUtil.getAuthenticationInfo(bankCard, idCard, name, phone);
		if (personalinfo.getPersonalStatus() != 1) {
			return new AppResultInfo<>(AppConstant.STATUE_1, personalinfo.getRemark());
		}
		personalinfo.setUserId(userId);
		personalinfo.setIdentityImgFront(identityImgFront);
		personalinfo.setIdentityImgReverse(identityImgReverse);
		itbPersonalInfoService.insert(personalinfo);
		return new AppResultInfo<>(AppConstant.STATUE_200, personalinfo.getRemark());
	}

	/**
	 * 用户基本信息
	 * 
	 * @api {post} /tbr/getUserInfo 用户基本信息
	 * @apiGroup userBase
	 * @apiDescription 详细描述：用户基本信息
	 * @apiParam {Integer} userId 用户Id
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getUserInfo
	 */
	@RequestMapping("/getUserInfo")
	public @ResponseBody AppResultInfo<?> getUserInfo(Integer userId) {
		AppUserInfoVo appuserunfovo = iappUserinfoService.getUserInfo(userId);
		if (StringUtils.isEmpty(appuserunfovo)) {
			return new AppResultInfo<>(AppConstant.STATUE_1, "请求信息不对！");
		}
		return new AppResultInfo<>(appuserunfovo);
	}

	/**
	 * 我的好友
	 * 
	 * @api {post} /tbr/getMyFriend 我的好友
	 * @apiGroup userBase
	 * @apiDescription 详细描述：我的好友
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {Integer} page 当前页
	 * @apiParam {Integer} limit 每页多少条
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getMyFriend
	 */
	@RequestMapping("/getMyFriend")
	public @ResponseBody AppResultInfo<?> getMyFriend(Integer userId, Integer page, Integer limit) {
		List<TbInvite> list = itbInviteService.selectList(new EntityWrapper<TbInvite>().eq("tb_iviter", userId));
		List<Integer> arrId = new ArrayList<>();
		if (list.size() > 0 && list != null) {
			for (TbInvite tbInvite : list) {
				arrId.add(tbInvite.getTbIvitee());
			}
		}
		Page<AppUser> pageObj = new Page<AppUser>();
		if (arrId.size() > 0 && arrId != null) {
			pageObj = iappUserService.selectPage(new Page<AppUser>(page, limit),
					(new EntityWrapper<AppUser>().in("id", arrId).orderBy(false, "create_time", false)));
		}
		return new AppResultInfo<>(pageObj);
	}

	/**
	 * 绑定邮箱
	 * 
	 * @api {post} /tbr/bindEmail 绑定邮箱
	 * @apiGroup userBase
	 * @apiDescription 详细描述：绑定邮箱
	 * @apiParam {Integer} userId 用户Id
	 * @apiParam {String} email 邮箱地址
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/bindEmail
	 * @return
	 */
	@RequestMapping("/bindEmail")
	public @ResponseBody AppResultInfo<?> bindEmail(Integer userId, String email) {
		AppUser appuser = iappUserService.selectById(userId);
		if (appuser != null) {
			appuser.setEmail(email);
			iappUserService.updateById(appuser);
			return new AppResultInfo<>("绑定邮箱成功！");
		}
		return new AppResultInfo<>(-1, "参数错误！");
	}

	/**
	 * 用户实名认证信息
	 * 
	 * @api {post} /tbr/getPersonalInfo 用户实名认证信息
	 * @apiGroup userBase
	 * @apiDescription 详细描述：用户实名认证信息
	 * @apiParam {Integer} userId 用户Id
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getPersonalInfo
	 */
	@RequestMapping("/getPersonalInfo")
	public @ResponseBody AppResultInfo<?> getPersonalInfo(Integer userId) {
		TbPersonalInfo tbpersonalinfo = itbPersonalInfoService
				.selectOne(new EntityWrapper<TbPersonalInfo>().eq("user_id", userId));
		return new AppResultInfo<>(tbpersonalinfo);
	}

	/**
	 * 获取各种数字货币的交易信息
	 * 
	 * @api {post} /tbr/getCurrencyList 获取各种数字货币的交易信息
	 * @apiGroup userBase
	 * @apiDescription 详细描述： 获取各种数字货币的交易信息
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getCurrencyList
	 */
	@RequestMapping(value = "/getCurrencyList")
	public @ResponseBody AppResultInfo<JSONArray> getCurrencyList() {
		try {
			String json = CoinUtil.readData();
			JSONArray jsonArray = JSON.parseArray(json);
			return new AppResultInfo<>(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AppResultInfo<>(AppConstant.STATUE_1, "未获取到数据");
	}
}
