package com.itwill.springboot4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot4.domain.Comment;
import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.dto.CommentRegisterDto;
import com.itwill.springboot4.dto.CommentUpdateDto;
import com.itwill.springboot4.repository.CommentRepository;
import com.itwill.springboot4.repository.PostRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepo;
	private final PostRepository postRepo;
	
	@Transactional
	public Comment create(CommentRegisterDto dto) {
		log.info("create dto={}",dto);
		
		//postId로 댓글이 등록될 포스트 엔터티를 검색.
		Post post= postRepo.findById(dto.getPostId()).orElseThrow();
		
		//저장할 Comment엔터티 생성 
		Comment entity=Comment.builder().post(post).writer(dto.getWriter()).text(dto.getText()).build();
		
		//save메소드 호출 -> DB에 INSERT
		entity=commentRepo.save(entity);
		
		return entity;
	}
	
	@Transactional(readOnly=true)
	public Comment read(Long id) {
		log.info("read(id= {})",id);
		//Comment나 null이 리턴된다.
		return commentRepo.findById(id).orElseThrow(()->null);
	}
	
	
	@Transactional(readOnly=true)
	public Page<Comment> readByPostId(Long postId, Sort sort, int p){
		PageRequest pageable=PageRequest.of(p, 5, sort);
		Post post=postRepo.findById(postId).orElseThrow();
		Page<Comment> comments=commentRepo.findByPost(post, pageable);
		//Page<Comment> comments=commentRepo.findByPostId(postId, pageable);
		//-> 우리가 lazy타입으로 지정했기 때문에 comment와 post가 나중에 조인되는 과정과 페이징처리하는 과정에서 충돌발생
		//-> findByPost를 하면 Post를 미리 찾아서 이를 이용해 호출하기 때문에 충돌되지 않음.
		return comments;
	}
	
	@Transactional
	public void delete(Long commentId) {
		log.info("delete commentId={}",commentId);
		commentRepo.deleteById(commentId);
	}
	
	@Transactional //엔터티의 필드가 변겨오디면 save메서드가 자동으로 실행되도록 하기 위해서
	public void update(CommentUpdateDto dto) {
		log.info("commentUpdateDto={}",dto);
		Comment comment=commentRepo.findById(dto.getId()).orElseThrow();
		comment.update(dto.getText());
	}
}
