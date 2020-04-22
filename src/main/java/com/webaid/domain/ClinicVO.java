package com.webaid.domain;

public class ClinicVO {
	private int no;
	private int kind1;
	private String kind1nm;
	private String kind2;
	private String kind2nm;
	private String name;
	private String code;
	private int price_before;
	private int price_after;
	private String discount;
	private String popular;

	public ClinicVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getKind1() {
		return kind1;
	}

	public void setKind1(int kind1) {
		this.kind1 = kind1;
	}

	public String getKind1nm() {
		return kind1nm;
	}

	public void setKind1nm(String kind1nm) {
		this.kind1nm = kind1nm;
	}

	public String getKind2() {
		return kind2;
	}

	public void setKind2(String kind2) {
		this.kind2 = kind2;
	}

	public String getKind2nm() {
		return kind2nm;
	}

	public void setKind2nm(String kind2nm) {
		this.kind2nm = kind2nm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPrice_before() {
		return price_before;
	}

	public void setPrice_before(int price_before) {
		this.price_before = price_before;
	}

	public int getPrice_after() {
		return price_after;
	}

	public void setPrice_after(int price_after) {
		this.price_after = price_after;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPopular() {
		return popular;
	}

	public void setPopular(String popular) {
		this.popular = popular;
	}

	@Override
	public String toString() {
		return "ClinicVO [no=" + no + ", kind1=" + kind1 + ", kind1nm=" + kind1nm + ", kind2=" + kind2 + ", kind2nm="
				+ kind2nm + ", name=" + name + ", code=" + code + ", price_before=" + price_before + ", price_after="
				+ price_after + ", discount=" + discount + ", popular=" + popular + "]";
	}

}
