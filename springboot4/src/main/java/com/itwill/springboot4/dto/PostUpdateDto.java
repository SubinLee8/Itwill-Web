package com.itwill.springboot4.dto;

import java.time.LocalDateTime;

import com.itwill.springboot4.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor(access=AccessLevel.PRIVATE) //빌더에 필요하다
public class PostUpdateDto {
	private Long id;
	private String title;
	private String content;
	
	public Post toEntity() {
		return Post.builder().id(id).title(title).content(content).build();
	}
}
