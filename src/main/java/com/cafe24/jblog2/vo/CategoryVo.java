package com.cafe24.jblog2.vo;

public class CategoryVo {
	private Long no;
	private String regDate;
	private String description;
	private Long blogNo;
	private String name;
	private Long postCount;
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", regDate=" + regDate + ", description=" + description + ", blogNo=" + blogNo
				+ ", name=" + name + ", postCount=" + postCount + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(Long blogNo) {
		this.blogNo = blogNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPostCount() {
		return postCount;
	}
	public void setPostCount(Long postCount) {
		this.postCount = postCount;
	}
	
}
