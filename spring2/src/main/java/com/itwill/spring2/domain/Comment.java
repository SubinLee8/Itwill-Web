package com.itwill.spring2.domain;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder   
@NoArgsConstructor //-> 기본생성자 스프링컨테이너(디스패처서블릿,마이배티스)은 항상 기본 생성자를 만든다.
@AllArgsConstructor //-> 모든 필드를 갖는 생성자. Builder 패턴에서 사용.
//@RequiredArgsConstructor -> final필드를 갖는 생성자, final 필드가 없으면 기본생성자와 동일.
@Data
// comments 테이블의 entity
public class Comment {
	private Integer id; //pk
	private Integer postId; //fk
	private String username; //댓글작성자
	private String ctext; //댓글 내용
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
