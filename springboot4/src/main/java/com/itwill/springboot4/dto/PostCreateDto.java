package com.itwill.springboot4.dto;

import java.time.LocalDateTime;

import com.itwill.springboot4.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE) //빌더에 필요하다
public class PostCreateDto {
	private String title;
	private String author;
	private String content;
	
	public Post toEntity() {
		return Post.builder().title(title).author(author).content(content).build();
	}
}
