package com.itwill.jsp2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.repository.MemberDao;
import com.itwill.jsp2.repository.PostDao;

import jakarta.servlet.http.HttpSession;

// 웹 MVC 아키텍처에서 Service/Buisness 계층을 담당.
// 영속성 계층(persistance layer)의 기능들을 사용해서 비즈니스 로직을 구현하는 객체.
public enum PostService {
	INSTANCE; //싱글턴 객체.
	
	private static final Logger log = LoggerFactory.getLogger(PostService.class);
	private final PostDao postDao=PostDao.INTANCE;
	private final MemberDao memberDao=MemberDao.INSTANCE;
	
	public List<Post> read() {
		log.debug("read()");
		
		return postDao.selelct();
		
	}
	
	public int create(Post post) {
		log.info("create()(Post={})",post);
		int result=postDao.insert(post);
		log.debug("insert result:{}",result);
		
		//로그인한 유저의 포인트를 10점 올림.
    	memberDao.updatePoints(10,post.getAuthor());
		return result;
	}
	
	public Post read(int id) {
		log.debug("read(id)");
		return postDao.select(id);
	}

	public int delete(int id) {
		log.debug("delete()(id={})",id);
		int result=postDao.delete(id);
		log.debug("delete result:{}",result);
		return result;
		
	}

	public int update(Post post) {
		int result=postDao.update(post);
		return result;
		
	}

	public List<Post> search(String category, String keyword) {
		return postDao.select(category,keyword);
	}
	
	
	
	
}
