package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.Data;

//DTO(Data Transfer Object): 
//PostController ===> PostService 계층으로 새 글 작성 데이터를 전달할 때 사용할 객체.
//클라이언트 ==> DispatcherServlet ==> PostController에게 요청 파라미터들을 전달할 때 사용할 객체.
//PostController ==> PostService 계층으로 새 글 작성 데이터를 전달할 때 사용할 객체.
@Data
public class PostCreateDto {
	//필드 이름들을 요청 파라미터 이름들과 동일하게 선언 & 기본 생성자 & setter
	private String title;
	private String content;
	private String author;
	
	//DTO를 Model로 변환해서 리턴하는 메서드.
	public Post toEntity() {
		return Post.builder().title(title).content(content).author(author).build();
	}


}
