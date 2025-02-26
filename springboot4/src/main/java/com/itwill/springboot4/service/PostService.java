package com.itwill.springboot4.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.dto.PostCreateDto;
import com.itwill.springboot4.dto.PostItemDto;
import com.itwill.springboot4.dto.PostListItemDto;
import com.itwill.springboot4.repository.PostRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepo;
	
	
	@Transactional(readOnly=true) //select만 할 때 사용
	public Page<PostListItemDto> readAll(int pageNo, Sort sort){
		Pageable pageable=PageRequest.of(pageNo, 10, sort);
		Page<Post> posts=postRepo.findAll(pageable);
		Page<PostListItemDto> dto=posts.map(PostListItemDto::fromEntity);
		return dto;
	}
	
	public void createPost(PostCreateDto dto) {
		postRepo.save(dto.toEntity());
	}
	
	public PostItemDto readById(Long id) {
		Post post=postRepo.findById(id).orElseThrow();
		return PostItemDto.fromEntity(post);
	}
}
