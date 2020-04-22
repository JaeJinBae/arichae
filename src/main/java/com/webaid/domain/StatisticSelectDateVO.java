package com.webaid.domain;

public class StatisticSelectDateVO {
	private String s_date;
	private String e_date;

	public StatisticSelectDateVO() {
		super();
	}

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getE_date() {
		return e_date;
	}

	public void setE_date(String e_date) {
		this.e_date = e_date;
	}

	@Override
	public String toString() {
		return "StatisticSelectDateVO [s_date=" + s_date + ", e_date=" + e_date + "]";
	}

}
