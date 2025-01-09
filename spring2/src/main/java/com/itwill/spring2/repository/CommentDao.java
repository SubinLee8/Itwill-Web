package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.domain.Comment;

public interface CommentDao {
	List<Comment> selectByPostId(Integer postId);
	Integer insertComment(Comment comment);
	Integer deleteById(Integer id);
	Integer deleteByPostId(Integer postId);
	Integer updateComment(Comment comment);
	Integer selectCommentCount(Integer postId);
	Comment selectById(Integer id);
}
