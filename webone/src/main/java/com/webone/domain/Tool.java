package com.webone.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tool {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	String size1;
	String size2;
	int userId;
	int storeroomId;

	public int getId() {
		return id;
	}

	public void setId(int idtool) {
		this.id = idtool;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize1() {
		return size1;
	}

	public void setSize1(String size1) {
		this.size1 = size1;
	}

	public String getSize2() {
		return size2;
	}

	public void setSize2(String size2) {
		this.size2 = size2;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(int storeroomId) {
		this.storeroomId = storeroomId;
	}

}
