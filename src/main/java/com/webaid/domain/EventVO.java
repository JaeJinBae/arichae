package com.webaid.domain;

public class EventVO {
	private int no;
	private String title;
	private String start_date;
	private String end_date;
	private String content;
	private int price;
	private String thumb_origin;
	private String thumb_stored;
	private String regdate;

	public EventVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getThumb_origin() {
		return thumb_origin;
	}

	public void setThumb_origin(String thumb_origin) {
		this.thumb_origin = thumb_origin;
	}

	public String getThumb_stored() {
		return thumb_stored;
	}

	public void setThumb_stored(String thumb_stored) {
		this.thumb_stored = thumb_stored;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "EventVO [no=" + no + ", title=" + title + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", content=" + content + ", price=" + price + ", thumb_origin=" + thumb_origin + ", thumb_stored="
				+ thumb_stored + ", regdate=" + regdate + "]";
	}

}
