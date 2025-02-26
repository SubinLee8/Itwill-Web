package com.itwill.springboot4.dto;

import java.time.LocalDateTime;

import com.itwill.springboot4.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE) //빌더에 필요하다
@Builder
public class PostListItemDto {
	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;
	
	public static PostListItemDto fromEntity(Post entity) {
		return PostListItemDto.builder().id(entity.getId()).title(entity.getTitle()).author(entity.getAuthor())
				.modifiedTime(entity.getModifiedTime()).build();
	}
}
