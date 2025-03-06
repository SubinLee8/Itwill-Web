package com.itwill.springboot4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot4.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	@EntityGraph(attributePaths = "roles")
	Optional<Member> findByUsername(String username);
}
