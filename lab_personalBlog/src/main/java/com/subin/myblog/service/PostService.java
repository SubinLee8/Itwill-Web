package com.subin.myblog.service;

import java.util.List;

import com.subin.myblog.domain.Post;
import com.subin.myblog.repository.MemberDao;
import com.subin.myblog.repository.PostDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum PostService {
	INSTANCE;
	PostDao postDao=PostDao.INSTANCE;
	MemberDao memberDao=MemberDao.INSTANCE;
	
	public List<Post> read() {
		log.debug("read()");
		
		return postDao.selelct();
		
	}
	
	public Integer postCount() {
		log.debug("readCount()");
		return postDao.selectCount();
	}
	
	public List<Post> readPage(Integer page) {
		log.debug("readPage()");
		
		return postDao.selelctPage(page);
		
	}
	
	public int create(Post post) {
		log.info("create()(Post={})",post);
		int result=postDao.insert(post);
		log.debug("insert result:{}",result);
		return result;
	}
	
	public Post read(int id) {
		log.debug("read(id)");
		return postDao.select(id);
	}

	public int delete(int id) {
		log.debug("delete()(id={})",id);
		int result=postDao.delete(id);
		log.debug("delete result:{}",result);
		return result;
		
	}

	public int update(Post post) {
		int result=postDao.update(post);
		return result;
		
	}

	public List<Post> search(String category, String keyword) {
		return postDao.select(category,keyword);
	}
	
	
}
