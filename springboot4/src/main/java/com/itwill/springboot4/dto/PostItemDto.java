package com.itwill.springboot4.dto;

import java.time.LocalDateTime;

import com.itwill.springboot4.domain.Post;

import jakarta.persistence.Basic;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 빌더에 필요하다
public class PostItemDto {
	private Long id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	public static PostItemDto fromEntity(Post entity) {
		return PostItemDto.builder().id(entity.getId()).title(entity.getTitle()).author(entity.getAuthor()).createdTime(entity.getCreatedTime()).content(entity.getContent())
				.modifiedTime(entity.getModifiedTime()).build();
	}

}
