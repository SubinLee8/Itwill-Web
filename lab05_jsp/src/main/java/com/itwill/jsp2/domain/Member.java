package com.itwill.jsp2.domain;

import java.time.LocalDateTime;

public class Member {
	private int id;
	private String userName;
	private String passWord;
	private String email;
	private int points;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	private Member() {}
	
	private Member(int id, String userName, String passWord, String email, int points, LocalDateTime createdTime,
			LocalDateTime modifiedTime) {
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.points = points;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	
	public static MemberBuilder Builder() {
		return new MemberBuilder();
	}
	
	public static class MemberBuilder{
		private int id;
		private String userName;
		private String passWord;
		private String email;
		private int points;
		private LocalDateTime createdTime;
		private LocalDateTime modifiedTime;
		
		public MemberBuilder id(int id) {
			this.id=id;
			return this;
		}
		
		public MemberBuilder userName(String userName) {
			this.userName=userName;
			return this;
		}
		
		public MemberBuilder passWord(String passWord) {
			this.passWord=passWord;
			return this;
		}
		
		public MemberBuilder email(String email) {
			this.email=email;
			return this;
		}
		
		public MemberBuilder points(int points) {
			this.points=points;
			return this;
		}
		
		public MemberBuilder createdTime(LocalDateTime creDateTime) {
			this.createdTime=creDateTime;
			return this;
		}
		
		public MemberBuilder modifiedTime(LocalDateTime moDateTime) {
			this.modifiedTime=moDateTime;
			return this;
		}
		
		public Member build() {
			return new Member(id, userName, passWord, email, points,createdTime,
			modifiedTime);
		}
	}
}
