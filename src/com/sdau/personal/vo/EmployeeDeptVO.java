package com.sdau.personal.vo;

public class EmployeeDeptVO {
	private Integer id;
	private String name;
	private Integer age;
	private String gender;
	private String deptName;

	public EmployeeDeptVO() {
		super();
	}

	public EmployeeDeptVO(Integer id, String name, Integer age, String gender, String deptName) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.deptName = deptName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "EmployeeDeptVO [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", deptName="
				+ deptName + "]";
	}

}
