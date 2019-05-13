package com.stys.ipfs.web.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stys.ipfs.service.IDicService;
import com.stys.ipfs.web.BaseController;

@Controller
@RequestMapping("/invite")
public class AppShareController extends BaseController {
	
	@Reference(version = "1.0.0", check = false)
	private IDicService idicService;
	
	@RequestMapping("/*")
    public void toHtml(){

    }
	
	@RequestMapping(value = "/openshare")
	public void openSharePage(HttpServletRequest request, HttpServletResponse response,String invitation_code) throws IOException {
		String url=	idicService.getDicData("网站地址", "邀请码登录地址").getValue1()+"/invite/login.html?invitation_code=" + invitation_code;
		response.sendRedirect(url);
	}

}
