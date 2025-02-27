package com.itwill.springboot4.dto;


import lombok.Data;


@Data
public class PostSearchDto {
	private String category;
	private String keyword;
	private int p;
}
