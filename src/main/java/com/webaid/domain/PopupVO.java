package com.webaid.domain;

public class PopupVO {
	private int no;
	private String output_type;
	private String startdate;
	private String enddate;
	private int orderno;
	private int p_left;
	private int p_top;
	private String use_state;
	private String link;
	private String title;
	private String content;
	private String regdate;

	public PopupVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOutput_type() {
		return output_type;
	}

	public void setOutput_type(String output_type) {
		this.output_type = output_type;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public int getP_left() {
		return p_left;
	}

	public void setP_left(int p_left) {
		this.p_left = p_left;
	}

	public int getP_top() {
		return p_top;
	}

	public void setP_top(int p_top) {
		this.p_top = p_top;
	}

	public String getUse_state() {
		return use_state;
	}

	public void setUse_state(String use_state) {
		this.use_state = use_state;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "PopupVO [no=" + no + ", output_type=" + output_type + ", startdate=" + startdate + ", enddate="
				+ enddate + ", orderno=" + orderno + ", p_left=" + p_left + ", p_top=" + p_top + ", use_state="
				+ use_state + ", link=" + link + ", title=" + title + ", content=" + content + ", regdate=" + regdate
				+ "]";
	}

}
