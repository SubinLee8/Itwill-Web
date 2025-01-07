package com.subin.myblog.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Post {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	
	
}