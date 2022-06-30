package com.sdau.personal.pojo;

public class Employee {
	private Integer id;
	private String name;
	private Integer age;
	private String gender;
	private Integer deptId;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, Integer age, String gender, Integer deptId) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.deptId = deptId;
	}

	public Employee(Integer id, String name, Integer age, String gender, Integer deptId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.deptId = deptId;
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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", deptId=" + deptId
				+ "]";
	}

}