package com.cafe24.jblog2.vo;

public class PostVo {
	private Long no;
	private String title;
	private String content;
	private Long categoryNo;
	private String regDate;
	private Long blogNo;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(Long blogNo) {
		this.blogNo = blogNo;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", categoryNo=" + categoryNo
				+ ", regDate=" + regDate + ", blogNo=" + blogNo + "]";
	}
	
}
