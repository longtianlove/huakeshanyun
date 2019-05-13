package com.stys.ipfs.web.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stys.ipfs.util.SignUtil;
import com.stys.ipfs.util.TimestampUtil;
import com.stys.ipfs.util.VerificationCodeParam;

/**
 * 测试
 * 
 * @author dp
 *
 */
@RestController
@RequestMapping(value = "/sign", produces = "application/json")
public class SignValidateController {

	@Value("${ticketSecret}")
	private String ticketSecret;

	// 签名校验@RequestBody post @RequestParam get
	@RequestMapping(value = "/valid")
	public boolean signValid(VerificationCodeParam param) {

		// 验证信息是否被篡改
		if (!SignUtil.validateMessage(param, ticketSecret)) {
			return false;
		}

		// 验证时间戳,防止重复提交
		Boolean validateResult = TimestampUtil.validateTimestamp("verificationCode", param.getTimeStamp());
		if (!validateResult) {
			return false;
		}
		return true;
	}
}