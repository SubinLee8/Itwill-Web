package com.itwill.spring2.repository;



import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}) //->만들어진 CommentDao 객체를 가져온다.
public class CommentDaoTest {
	@Autowired
	private CommentDao commentDao;
	
//	@Test
//	public void selectTest() {
//		List<Comment> clist=commentDao.selectByPostId(1);
//		Assertions.assertNotNull(clist);
//		log.debug("clist={}",clist);
//	}
//	
	
//	@Test
//	public void insertComment() {
//		Comment comment = Comment.builder().postId(1).username("guest").ctext("새로운 댓글").build();
//		int result=commentDao.insertComment(comment);		
//		Assertions.assertEquals(1, result);
//		log.debug("inserCommentResult={}",result);
//	}
	
//	@Test
//	public void deleteById() {
//		int result=commentDao.deleteById(2);
//		Assertions.assertEquals(1, result);
//		log.debug("deleteCommentResult={}",result);
//	}
	
//	@Test
//	public void updateComment() {
//		int result=commentDao.updateComment(3, "댓글 수정 후");
//		Assertions.assertEquals(1, result);
//		log.debug("updateComment()={}",result);
//	}
	
	
	@Test
	public void selectCommentCount() {
		int count=commentDao.selectCommentCount(1);
		Assertions.assertNotNull(count);
		log.debug("count={}",count);
	}
	
	@Test
	public void selectById() {
		Comment comment=commentDao.selectById(1);
		Assertions.assertNotNull(comment);
		log.debug("comment={}",comment);
	}
	
}
