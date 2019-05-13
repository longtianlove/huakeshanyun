package com.stys.ipfs.ex;

public class TokenException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	public TokenException(String code, String message) {
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
