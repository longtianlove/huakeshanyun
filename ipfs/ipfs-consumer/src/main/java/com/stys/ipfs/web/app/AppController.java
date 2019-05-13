package com.stys.ipfs.web.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stys.ipfs.util.AppConstant;

public class AppController {

	
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Map<String, Object> returnMap() {
		Map<String,Object> result=new HashMap<>();
		result.put("state", AppConstant.STATUE_200);
		result.put("msg", AppConstant.MSG);
		return result;
	}
	
    protected String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

}