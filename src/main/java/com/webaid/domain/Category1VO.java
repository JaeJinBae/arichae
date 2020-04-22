package com.webaid.domain;

public class Category1VO {
	private int no;
	private String name;

	public Category1VO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category1 [no=" + no + ", name=" + name + "]";
	}

}
