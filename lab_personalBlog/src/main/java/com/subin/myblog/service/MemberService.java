package com.subin.myblog.service;

import com.subin.myblog.domain.Member;
import com.subin.myblog.repository.MemberDao;

public enum MemberService {
	INSTANCE;
	MemberDao dao = MemberDao.INSTANCE;

	public int signUp(Member member) {
		return dao.insert(member);
	}
	
	public Member signIn(String username, String password) {
		Member member=dao.select(username, password);
		return member;
	}

}
