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
import com.itwill.springboot4.dto.PostSearchDto;
import com.itwill.springboot4.dto.PostUpdateDto;
import com.itwill.springboot4.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepo;

	@Transactional(readOnly = true) // select만 할 때 사용
	public Page<PostListItemDto> readAll(int pageNo, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, 10, sort);
		Page<Post> posts = postRepo.findAll(pageable);
		Page<PostListItemDto> dto = posts.map(PostListItemDto::fromEntity);
		return dto;
	}

	@Transactional
	public Long createPost(PostCreateDto dto) {
		Post entity = postRepo.save(dto.toEntity());
		return entity.getId();
	}

	@Transactional(readOnly = true) // select만 할 때 사용
	public PostItemDto readById(Long id) {
		Post post = postRepo.findById(id).orElseThrow();
		return PostItemDto.fromEntity(post);
	}

	@Transactional(readOnly = true)
	public Page<PostListItemDto> search(PostSearchDto dto, Sort sort) {
		log.info("dto={}",dto);
//		Integer pageNo=dto.getP();
//		if(dto.getP()==null) {
//			pageNo=0;
//		}
		Pageable pageable = PageRequest.of(dto.getP(), 10, sort);
		log.info("pageable={}",pageable);
		String category = dto.getCategory();
		String keyword = dto.getKeyword();
		Page<Post> page = null;
		switch (category) {
		case "t":
			page = postRepo.findByTitleContainingIgnoreCase(keyword, pageable);
			break;
		case "c":
			page = postRepo.findByContentContainingIgnoreCase(keyword, pageable);
			break;
		case "tc":
			page = postRepo.findByTitleOrContent(keyword, pageable);
			break;
		}
		return page.map(PostListItemDto::fromEntity);
	}

	@Transactional
	public void delete(Long id) {
		log.info("포스트 삭제");
		postRepo.deleteById(id);
	}

	@Transactional // DB에서 검색한 엔터티의 필드들이 변경되면
	// CrudeRepository<T,TD>.save() 메서드가 자동으로 호출되고 update쿼리가 실행된다.
	public void update(PostUpdateDto dto) {
		Post post = postRepo.findById(dto.getId()).orElseThrow();
		post.update(dto.getTitle(), dto.getContent());
	}

}
