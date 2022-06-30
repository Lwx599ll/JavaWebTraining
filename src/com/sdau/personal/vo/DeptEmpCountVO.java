package com.sdau.personal.vo;

public class DeptEmpCountVO {
	private String name;
	private Integer value;
	
	public DeptEmpCountVO() {
		super();
	}

	public DeptEmpCountVO(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DeptEmpCountVO [name=" + name + ", value=" + value + "]";
	}

}
