package com.itwill.springboot4.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot4.domain.Comment;

@SpringBootTest
public class CommentTest {
	@Autowired
	private CommentRepository commentRepo;
	
	@Test
	public void findAllTest() {
		List<Comment> comments=commentRepo.findAll();
		comments.forEach(System.out::println);
	}
}
