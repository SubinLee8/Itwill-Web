package com.itwill.springboot4.dto;

import com.itwill.springboot4.domain.Comment;
import com.itwill.springboot4.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRegisterDto {
	private Long postId;
	private String text;
	private String writer;
}
