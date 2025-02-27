package com.itwill.springboot4.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot4.domain.Comment;
import com.itwill.springboot4.domain.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Page<Comment> findByPost(Post post, Pageable pageable);
	Page<Comment> findByPostId(Long id, Pageable pageable);
	//=> 둘 다 동일한 결과를 준다
}
