package com.stys.ipfs.ex;

/**
 * 自定义异常
 * 
 * @author  
 * 
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -4223349714913637416L;

	private String code;

	public BusinessException(String code, String message) {
		super(message);
		this.code = code; 
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}