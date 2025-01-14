package com.itwill.spring2.service;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Member;
import com.itwill.spring2.dto.MemberSignInDto;
import com.itwill.spring2.dto.MemberSignUpDto;
import com.itwill.spring2.repository.MemberDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberDao memberDao;
	
	public boolean checkUsername(String username) {
		Member member = memberDao.selectByUsername(username);
		if(member!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkEmail(String email) {
		Member member = memberDao.selectByEmail(email);
		return (member==null)?false:true;
	}
	
	public Integer createUser(MemberSignUpDto dto) {
		int result=memberDao.insert(dto.toEntity());
		return result;
	}
	
	public MemberSignInDto checkLogIn(MemberSignInDto dto) {
		Member member=memberDao.selectByUsernameAndPassword(dto.toEntity());
		
		return MemberSignInDto.fromEntity(member);
	}

	
}
