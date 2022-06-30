package com.sdau.personal.pojo;

public class Dept {
	private Integer id;
	private String name;
	private String address;

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dept(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Dept(Integer id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}