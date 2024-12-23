package com.itwill.jsp2.junit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitTest {
	private static final Logger log = LoggerFactory.getLogger(JUnitTest.class);
	
	@Test
	public void test() {
		log.debug("JUnit 테스트 입니다...");
	}
	
	@Test
	public void test2() {
		log.debug("JUnit 테스트2 입니다...");
	}
}
