package com.itwill.jsp2.repository;

import java.lang.System.LoggerFinder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Member;

public class MemberDaoTest {
	private static final Logger log =LoggerFactory.getLogger(MemberDaoTest.class);
	private final MemberDao memberDao=MemberDao.INSTANCE;
	
	
	@Test
	public void testSelect() {
		//username과 password가 일치하는 사용자가 있는 경우.
		Member m1=memberDao.select("max", "max");
		Assertions.assertNotNull(m1);
		
		//username과 password가 일치하는 사용자가 없는 경우
		Member m2=memberDao.select("max", "m123");
		Assertions.assertNotNull(m2);
	}
}
