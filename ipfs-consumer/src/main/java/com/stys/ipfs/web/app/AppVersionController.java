package com.stys.ipfs.web.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.util.AppConstant;

@RestController
@RequestMapping("/tbr")
public class AppVersionController extends AppController {

	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;

	/**
	 * 获取系统版本号
	 * 
	 * @api {post} /tbr/getAppVersion 获取系统版本号
	 * @apiGroup userBase
	 * @apiDescription 详细描述：获取系统版本号
	 * @apiVersion 0.1.0
	 * @apiSampleRequest http://127.0.0.1/tbr/getAppVersion
	 *
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getAppVersion")
	public @ResponseBody AppResultInfo<?> getAppVersion() throws IOException {
		Dic android_version_dic = idicService.getDicData("App系统", "安卓版本");
		Dic ios_version_dic = idicService.getDicData("App系统", "苹果版本");
		Dic readme_dic = idicService.getDicData("App系统", "更新内容");
		Dic android_dic = idicService.getDicData("App系统", "安卓下载地址");
		Dic ios_dic = idicService.getDicData("网站地址", "App下载地址");

		Map<String, String> versionDataMap = new HashMap<>();
		versionDataMap.put("android_version", android_version_dic.getValue1());
		versionDataMap.put("ios_version", ios_version_dic.getValue1());
		versionDataMap.put("readme", readme_dic.getValue1());
		versionDataMap.put("android_download", android_dic.getValue1());
		versionDataMap.put("ios_download", ios_dic.getValue1());
		return new AppResultInfo<>(versionDataMap, AppConstant.STATUE_200);
	}
}
