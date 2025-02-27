package com.itwill.springboot4.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itwill.springboot4.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	Page<Post> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

	@Query("select p from Post p where upper(p.title) like upper('%' || :keyword || '%')"
			+ "or upper(p.content) like upper('%' || :keyword || '%')")
	Page<Post> findByTitleOrContent(String keyword, Pageable pageable);

	Page<Post> findByContentContainingIgnoreCase(String keyword, Pageable pageable);
}
