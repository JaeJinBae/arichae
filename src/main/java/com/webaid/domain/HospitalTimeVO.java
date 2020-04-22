package com.webaid.domain;

public class HospitalTimeVO {
	private int no;
	private String h_date;
	private int start_time;
	private int end_time;

	public HospitalTimeVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getH_date() {
		return h_date;
	}

	public void setH_date(String h_date) {
		this.h_date = h_date;
	}

	public int getStart_time() {
		return start_time;
	}

	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}

	public int getEnd_time() {
		return end_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "HospitalTimeVO [no=" + no + ", h_date=" + h_date + ", start_time=" + start_time + ", end_time="
				+ end_time + "]";
	}

}
