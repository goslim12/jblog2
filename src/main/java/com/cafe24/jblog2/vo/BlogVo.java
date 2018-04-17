package com.cafe24.jblog2.vo;

public class BlogVo {
	private Long no;
	private String title;
	private String logoPath;
	private String introduction;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "BlogVo [no=" + no + ", title=" + title + ", logoPath=" + logoPath + ", introduction=" + introduction
				+ "]";
	}
	
}
