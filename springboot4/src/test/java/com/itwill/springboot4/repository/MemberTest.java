package com.itwill.springboot4.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.itwill.springboot4.domain.Member;
import com.itwill.springboot4.domain.MemberRole;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	//@Test
	public void testPasswordEncoder() {
		assertThat(encoder).isNotNull();
		log.info("encoder={}",encoder);
	}
	
	//@Test
	public void testMemberEquals() {
		Member m1 = Member.builder()
				.id(1L).username("user1").password("1111")
				.build();
		m1.addRole(MemberRole.USER);
		log.info("m1 = {}, roles = {}", m1, m1.getRoles());
		
		Member m2 = Member.builder()
				.id(2L).username("user1").password("1234")
				.build();
		m2.addRole(MemberRole.ADMIN);
		log.info("m2 = {}, roles = {}", m2, m2.getRoles());
		
		assertThat(m1).isEqualTo(m2);  // m1.equals(m2)의 리턴값이 true임을 주장.
		// equals(), hashCode() 메서드를 재정의할 때 username 필드만 사용.
	}
	
	//@Test
	public void testSave() {
		// members 테이블에 저장하기 위한 엔터티를 생성
		Member admin = Member.builder()
				.username("admin")
				.password(encoder.encode("admin"))
				.email("admin@itwill.com")
				.build();
		admin.addRole(MemberRole.ADMIN).addRole(MemberRole.USER);
		log.info("save 호출 전: admin={}, roles={}", admin, admin.getRoles());
		
		Member entity = memberRepo.save(admin);
		log.info("save 호출 후: entity={}, roles={}", entity, entity.getRoles());
	}
	
	//@Test
	public void testSave2() {
		Member user1 = Member.builder()
				.username("user1")
				.password(encoder.encode("user1"))
				.email("user1@test.com")
				.build();
		user1.addRole(MemberRole.USER);
		
		//user1 = memberRepo.save(user1);
		log.info("save 후 user1={}, roles={}", user1, user1.getRoles());
	}
	
	//@Test
	@Transactional //단위테스트는 트랜잭셔널을 넣어줘야한다.
	public void testFindAll() {
		List<Member> members=memberRepo.findAll();
		members.forEach(m->{
			System.out.println(m);
			System.out.println("/t" + m.getRoles());
		});
	}
	
	@Test
	@Transactional
	public void findByUsernameTest() {
		Member m=memberRepo.findByUsername("user1").orElseThrow();
		log.info("m={}",m);
	}
}
