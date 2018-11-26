package com.dayainfo.mirror.migrate.entity.po;

import java.io.Serializable;

public class User implements Serializable {
	
	private Integer id;
	private Integer age;
	private String name;
	private Integer sex;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSex() {
		return sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
