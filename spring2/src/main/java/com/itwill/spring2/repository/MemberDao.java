package com.itwill.spring2.repository;

import com.itwill.spring2.domain.Member;

public interface MemberDao {
	Member selectByUsername(String username);

	Member selectByEmail(String email);

	Integer insert(Member member);
	
	Member selectByUsernameAndPassword(Member member);
}
