package com.webaid.domain;

public class StatisticVO {
	private int no;
	private String date;
	private String dayofweek;
	private int hour;
	private int minute;
	private String browser;
	private String os;
	private String prev_url;

	public StatisticVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPrev_url() {
		return prev_url;
	}

	public void setPrev_url(String prev_url) {
		this.prev_url = prev_url;
	}

	@Override
	public String toString() {
		return "StatisticVO [no=" + no + ", date=" + date + ", dayofweek=" + dayofweek + ", hour=" + hour + ", minute="
				+ minute + ", browser=" + browser + ", os=" + os + ", prev_url=" + prev_url + "]";
	}

}
