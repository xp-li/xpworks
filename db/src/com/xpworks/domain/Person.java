package com.xpworks.domain;

public class Person {
	
	Integer id;
	String name;
	String phone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}
	
	public Person(Integer id, String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	public Person(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	public Person() {
		super();
	}
	
}
