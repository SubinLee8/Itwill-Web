package com.itwill.springboot4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor
@Data
public class PostSearchDto {
	private String category;
	private String keyword;
	private Integer p;
}
