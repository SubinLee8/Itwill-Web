package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberSignInDto {
	private String username;
	private String password;
	
	public static MemberSignInDto fromEntity(Member member) {
		if(member!=null) {
			return MemberSignInDto.builder().username(member.getUsername()).password(member.getPassword()).build();
		}else {
			return null;
		}
		
	}
	
	public Member toEntity() {
		return Member.builder().username(username).password(password).build();
	}
}
