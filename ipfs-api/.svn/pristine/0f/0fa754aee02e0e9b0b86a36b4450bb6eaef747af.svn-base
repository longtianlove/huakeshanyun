package com.stys.ipfs.dto;

import java.io.Serializable;
import java.util.Map;

import com.stys.ipfs.util.AppConstant;

public class AppResultInfo<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppResultInfo() {
	}

	public AppResultInfo(T data) {
		this.state = AppConstant.STATUE_200;
		this.msg = AppConstant.MSG;
		this.data = data;
		this.token=token;
	}
	public AppResultInfo(T data,String token) {
		this.state = AppConstant.STATUE_200;
		this.msg = AppConstant.MSG;
		this.data = data;
		this.token=token; 
	}
	
	@SuppressWarnings("unchecked")
	public AppResultInfo(Map<String,Object> map) {
		this.state =(Integer) map.get("state");
		this.msg = (String) map.get("msg");
		this.data =  (T) map.get("data");  
		this.token=(String) map.get("token");
	}

	public AppResultInfo(T data, int state) {
		this.data = data;
		this.state = state;
	}

	public AppResultInfo(int state, String msg) {
		this.state = state;
		this.msg = msg;
	}

	public AppResultInfo(int state, String msg, T data) { 
		this.state = state;
		this.msg = msg;
		this.data = data;
	}

	public AppResultInfo(String msg) {
		this.state = AppConstant.STATUE_200;
		this.msg = msg;
	}
	
	public AppResultInfo(String msg, T data) {
		this.state = AppConstant.STATUE_200;
		this.msg = msg;
		this.data = data;
		this.token=token;
	}


	/**
	 * 信息
	 */
	private String msg;

	/**
	 * 返回数据
	 */
	private T data;
	/**
	 * 总记录数
	 */
	private Integer state;
	
	private String token;
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	

}
