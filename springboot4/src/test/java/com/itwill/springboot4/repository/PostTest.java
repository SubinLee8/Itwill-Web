package com.itwill.springboot4.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot4.domain.Post;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class PostTest {
	@Autowired
	private PostRepository postRepo;
	
	//@Test
	public void findAllTest() {
		assertThat(postRepo.findAll().size()).isEqualTo(1);
		List<Post> list=postRepo.findAll();
		list.forEach(System.out::println);
	}
	//@Test
	public void saveTest() {
		Post entity=Post.builder().author("admin").content("저장테스트").title("jpa저장테스트").build();
		postRepo.save(entity); //insert쿼리를 생성하고 실행.
	}
	
	//@Test
	public void updateTest() {
		Post entity=postRepo.findById(2L).orElseThrow();
		log.info("findById결과={}",entity); 
		
		entity.update("수정2테스트", "수정2테스트"); 
		log.info("update 호출 후={}",entity); //entity값에 제목,내용 수정됨
		
		entity=postRepo.save(entity);
		log.info("save 호출 후={}",entity); //생성시간, 최종 수정시간 수정됨
		
	}
	
	//@Test
	public void deleteTest() {
		postRepo.deleteById(2L);
	}
	
	@Test
	public void makeDummyData() {
		List<Post> data=new ArrayList<>();
		for(int i=1;i<=50;i++) {
			Post post=Post.builder().title("dummy title #"+i).content("dummy content #"+i).author("admin").build();
			data.add(post);
		}
		postRepo.saveAll(data);
	}
}
