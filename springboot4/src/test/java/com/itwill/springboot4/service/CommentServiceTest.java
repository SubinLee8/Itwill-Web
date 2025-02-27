package com.itwill.springboot4.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot4.dto.CommentRegisterDto;
import com.itwill.springboot4.dto.CommentUpdateDto;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CommentServiceTest {
	@Autowired
	private CommentService commentServ;
	
	//@Test
	public void saveTest() {
		CommentRegisterDto dto=new CommentRegisterDto(53L, "comment test", "haha");
		commentServ.create(dto);
	}
	
	//@Test
	public void makeDumyComments() {
		for(int i=0;i<=10; i++) {
			CommentRegisterDto dto=new CommentRegisterDto(53L, "댓글놀이#"+i, "admin");
			commentServ.create(dto);
		}
	}
	
	//@Test
	public void testDelete() {
		commentServ.delete(1L);
	}
	
	//@Test
	public void testUpdate() {
		CommentUpdateDto dto=new CommentUpdateDto();
		dto.setId(21L);
		dto.setText("댓글수정테스트");
		commentServ.update(dto);
	}
}
