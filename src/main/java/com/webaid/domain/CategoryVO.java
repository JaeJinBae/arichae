package com.webaid.domain;

public class CategoryVO {

	private String category1_nm;
	private String category2_nm;
	private String category3_nm;
	private String category_pay;

	public CategoryVO() {
		super();
	}

	public String getCategory1_nm() {
		return category1_nm;
	}

	public void setCategory1_nm(String category1_nm) {
		this.category1_nm = category1_nm;
	}

	public String getCategory2_nm() {
		return category2_nm;
	}

	public void setCategory2_nm(String category2_nm) {
		this.category2_nm = category2_nm;
	}

	public String getCategory3_nm() {
		return category3_nm;
	}

	public void setCategory3_nm(String category3_nm) {
		this.category3_nm = category3_nm;
	}

	public String getCategory_pay() {
		return category_pay;
	}

	public void setCategory_pay(String category_pay) {
		this.category_pay = category_pay;
	}

	@Override
	public String toString() {
		return "CategoryVO [category1_nm=" + category1_nm + ", category2_nm=" + category2_nm + ", category3_nm="
				+ category3_nm + ", category_pay=" + category_pay + "]";
	}

}
