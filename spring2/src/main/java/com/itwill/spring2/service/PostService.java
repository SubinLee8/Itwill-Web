package com.itwill.spring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service //스프링 컨테이너에 서비스 컴포넌트로 등록.
public class PostService {
	//의존성 주입 방법:
	//(1) 애너테이션을 사용한 의존성 주입
	//(2) final 필드와 생성자를 사용한 의존성 주입
	
	//(1) 애너테이션을 사용한 의존성 주입(DI: Dependency Injection)
	//@Autowired private PostDao postDao;
	
	//(2) final 필드와 생성자를 사용한 의존성 주입
	private final PostDao postDao;
	
//	public PostService(PostDao postDao) {
//		this.postDao=postDao;
//	}
	
//	
	
	
	public List<Post> read() {
		log.debug("read()");
		List<Post> list=postDao.selectOrderByIdDesc();
		return list;
	}
	
	public Post read(Integer id) {
		log.debug("read(id)");
		Post post=postDao.selectById(id);
		return post;
	}
	
	public int update(PostUpdateDto dto) {
		log.debug("update()");
		int result=postDao.updatePost(dto);
		return result;
	}
	
	public int delete(Integer id) {
		log.debug("delete()");
		int result=postDao.deletePost(id);
		return result;
	}
	
	public int create(PostCreateDto dto) {
		log.debug("create()");
		
		//PostService ==> PostDao 계층의 메서드 호출 & Entity를 아규먼트로 전달.
		int result=postDao.insertPost(dto.toEntity());
		return result;
	}
	
	public List<Post> read(PostSearchDto dto){
		log.debug("read(dto={})",dto);
		
		//영속성 계층의 메소드를 호출해서 DB에서 SELECT 수행.
		List<Post> list=postDao.search(dto);
		log.debug("# of search results={}",list.size());
		return list;
	}
	
	
	
}
