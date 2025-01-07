package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;

public interface PostDao {
	List<Post> selectOrderByIdDesc();
	
	Post selectById(Integer id);
	
	int insertPost(Post post);
	
	int updatePost(PostUpdateDto dto);
	
	int deletePost(Integer id);
	
	List<Post> search(PostSearchDto dto);
}


