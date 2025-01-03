package com.itwill.spring2.repository;

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

public class PostDaoTest {
	@Autowired //MyBatis 프레임워크에서 자동으로 생성된 postDao 인터페이스 구현 객체를 주입.
	private PostDao postDao;
	
	@Test
	public void testPostDao() {
		Assertions.assertNotNull(postDao);
		log.debug("postDao={}",postDao);
	}
	
	@Test
	public void testSelectOrderByIdDesc() {
		List<Post> list=postDao.selectOrderByIdDesc();
		list.forEach(x->log.debug("{}",x));
				
	}
	
	@Test
	public void testSelectById() {
		//테이블에 id가 존재하는 경우:
		Post post=postDao.selectById(84);
		Assertions.assertNotNull(post);
		log.debug("post={}",post);
		
		//테이블에 id가 없는 경우:
		Post post2=postDao.selectById(2);
		Assertions.assertNull(post2);
	}
	
	
 }
