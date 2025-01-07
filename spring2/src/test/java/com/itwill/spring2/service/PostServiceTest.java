package com.itwill.spring2.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml"})
public class PostServiceTest {

	//단위 테스트에서, 단위테스트 객체를 생성하고 테스트 메서드를 호출하는 테스트 메인 쓰레드는 
	//단위 테스트 클래스(class PostServiceTest)의 "기본 생성자"만 호출.
	//-> 아규먼트를 갖는 생성자를 호출할 수 없음.
	//-> 애너테이션을 사용한 의존성 주입만 가능.
	
	@Autowired private PostService postService;

	
	//목록보기 서비스
	@Test
	public void testRead() {
		Assertions.assertNotNull(postService);
		List<Post> list = postService.read();
		list.forEach(x->log.debug("{}",x));
	}
	
	//상세보기 서비스
	@Test
	public Post read(Integer id) {
		Post post = postService.read(id);
		return post;
	}
	
	//새글작성 서비스
//	@Test int create(Post post) {
//		return postService.create(post);
//	}
	
	//수정하기 서비스
	//@Test int update(Post post) {
		//return postService.update(post);
	//}
	
	//삭제하기 서비스
	@Test int delete(Integer Id) {
		return postService.delete(Id);
	}
	
}
