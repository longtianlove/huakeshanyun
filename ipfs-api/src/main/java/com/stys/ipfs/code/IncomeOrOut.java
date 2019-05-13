package com.stys.ipfs.code;

public enum IncomeOrOut {
	income(1,"收入"),
	expend(0,"支出");
	
	private Integer code;
	
	private String  name;
	
	private IncomeOrOut(Integer code,String  name) {
		this.code=code;
		this.name=name;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	
}
