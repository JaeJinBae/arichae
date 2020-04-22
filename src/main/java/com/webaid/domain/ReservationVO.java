package com.webaid.domain;

public class ReservationVO {
	private int no;
	private String regdate;
	private String clinic_list;
	private int price;
	private String counseling;
	private String res_date;
	private String res_time;
	private String name;
	private String phone;
	private String email;
	private String memo;
	private String res_state;

	public ReservationVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getClinic_list() {
		return clinic_list;
	}

	public void setClinic_list(String clinic_list) {
		this.clinic_list = clinic_list;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCounseling() {
		return counseling;
	}

	public void setCounseling(String counseling) {
		this.counseling = counseling;
	}

	public String getRes_date() {
		return res_date;
	}

	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}

	public String getRes_time() {
		return res_time;
	}

	public void setRes_time(String res_time) {
		this.res_time = res_time;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRes_state() {
		return res_state;
	}

	public void setRes_state(String res_state) {
		this.res_state = res_state;
	}

	@Override
	public String toString() {
		return "ReservationVO [no=" + no + ", regdate=" + regdate + ", clinic_list=" + clinic_list + ", price=" + price
				+ ", counseling=" + counseling + ", res_date=" + res_date + ", res_time=" + res_time + ", name=" + name
				+ ", phone=" + phone + ", email=" + email + ", memo=" + memo + ", res_state=" + res_state + "]";
	}

	

}
