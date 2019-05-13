/**
 * Tenyuanfund.com Inc.
 * Copyright (c) 2012-2018 All Rights Reserved.
 */
package com.stys.ipfs.util;

/**
 * 
 * @author liuguihua
 */
public class SignException extends Exception {

	private static final long serialVersionUID = -6227016905742372584L;
	
	private String errCode;
	private String errMsg;

	public SignException() {
		super();
	}

	public SignException(String message, Throwable cause) {
		super(message, cause);
	}

	public SignException(String message) {
		super(message);
	}

	public SignException(Throwable cause) {
		super(cause);
	}

	public SignException(String errCode, String errMsg) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

}