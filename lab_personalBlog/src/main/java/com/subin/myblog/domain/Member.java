package com.subin.myblog.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Member {
	private int id;
	private String userName;
	private String passWord;
	private String email;
	private int points;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	
}
