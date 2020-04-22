package com.webaid.domain;

import java.util.List;

public class ReservationJsonVO {
	private String mode;
	private String r_regdate;
	private String r_pay;
	private String r_date;
	private String r_time_code;
	private String r_time;
	private String r_name;
	private String r_phone;
	private String r_email;
	private String r_memo;
	private String r_counsel;
	private List<CategoryVO> categoryList;

	public ReservationJsonVO() {
		super();
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getR_regdate() {
		return r_regdate;
	}

	public void setR_regdate(String r_regdate) {
		this.r_regdate = r_regdate;
	}

	public String getR_pay() {
		return r_pay;
	}

	public void setR_pay(String r_pay) {
		this.r_pay = r_pay;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	public String getR_time_code() {
		return r_time_code;
	}

	public void setR_time_code(String r_time_code) {
		this.r_time_code = r_time_code;
	}

	public String getR_time() {
		return r_time;
	}

	public void setR_time(String r_time) {
		this.r_time = r_time;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getR_phone() {
		return r_phone;
	}

	public void setR_phone(String r_phone) {
		this.r_phone = r_phone;
	}

	public String getR_email() {
		return r_email;
	}

	public void setR_email(String r_email) {
		this.r_email = r_email;
	}

	public String getR_memo() {
		return r_memo;
	}

	public void setR_memo(String r_memo) {
		this.r_memo = r_memo;
	}

	public String getR_counsel() {
		return r_counsel;
	}

	public void setR_counsel(String r_counsel) {
		this.r_counsel = r_counsel;
	}

	public List<CategoryVO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryVO> categoryList) {
		this.categoryList = categoryList;
	}

	@Override
	public String toString() {
		return "ReservationJsonVO [mode=" + mode + ", r_regdate=" + r_regdate + ", r_pay=" + r_pay + ", r_date="
				+ r_date + ", r_time_code=" + r_time_code + ", r_time=" + r_time + ", r_name=" + r_name + ", r_phone="
				+ r_phone + ", r_email=" + r_email + ", r_memo=" + r_memo + ", r_counsel=" + r_counsel
				+ ", categoryList=" + categoryList + "]";
	}

}
