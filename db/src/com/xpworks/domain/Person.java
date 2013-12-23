package com.xpworks.domain;

public class Person {
	
	Integer id;
	String name;
	String phone;
	Integer amount;
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
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
		return "Person [id=" + id + ", name=" + name + ", "
				+ "phone=" + phone + ",amount="+amount+"]";
	}
	
	public Person(Integer id, String name, String phone,Integer amount) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.amount = amount;
	}
	
	public Person(String name, String phone,Integer amount) {
		super();
		this.name = name;
		this.phone = phone;
		this.amount = amount;
	}
	public Person() {
		super();
	}
	
}
