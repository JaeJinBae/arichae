package com.webaid.domain;

public class MediaVO {
	private int no;
	private String writer;
	private String regdate;
	private int cnt;
	private String title;
	private String content;
	private String use_state;
	private String thumb_origin;
	private String thumb_stored;

	public MediaVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
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

	public String getUse_state() {
		return use_state;
	}

	public void setUse_state(String use_state) {
		this.use_state = use_state;
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

	@Override
	public String toString() {
		return "MediaVO [no=" + no + ", writer=" + writer + ", regdate=" + regdate + ", cnt=" + cnt + ", title=" + title
				+ ", content=" + content + ", use_state=" + use_state + ", thumb_origin=" + thumb_origin
				+ ", thumb_stored=" + thumb_stored + "]";
	}

}
