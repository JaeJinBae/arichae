package com.webaid.domain;

public class AdviceVO {
	private int no;
	private String name;
	private String phone;
	private String regdate;
	private String email;
	private String state;
	private String secret;
	private String pw;
	private String title;
	private String content;
	private String reply;
	private String memo;
	private String ip;
	private String access_url;
	private String reply_date;
	private String upload_origin;
	private String upload_stored;

	public AdviceVO() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAccess_url() {
		return access_url;
	}

	public void setAccess_url(String access_url) {
		this.access_url = access_url;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}

	public String getUpload_origin() {
		return upload_origin;
	}

	public void setUpload_origin(String upload_orgin) {
		this.upload_origin = upload_orgin;
	}

	public String getUpload_stored() {
		return upload_stored;
	}

	public void setUpload_stored(String upload_stored) {
		this.upload_stored = upload_stored;
	}

	@Override
	public String toString() {
		return "AdviceVO [no=" + no + ", name=" + name + ", phone=" + phone + ", regdate=" + regdate + ", email="
				+ email + ", state=" + state + ", secret=" + secret + ", pw=" + pw + ", title=" + title + ", content="
				+ content + ", reply=" + reply + ", memo=" + memo + ", ip=" + ip + ", access_url=" + access_url
				+ ", reply_date=" + reply_date + ", upload_origin=" + upload_origin + ", upload_stored=" + upload_stored
				+ "]";
	}

}
