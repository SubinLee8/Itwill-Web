package com.itwill.springboot4.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Basic;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@Getter
@ToString(callSuper = true) //super class인 createdtime, modifiedTime도 toString으로
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false) //@EqualsAndHashCode.Include로 지정된 필드만 가지고서 equalsAndHashCode를 만들겠다는 뜻
												  //즉 유저네임만 가지고 쓰겠다는 것
@Entity
@Table(name="Members")
public class Member extends BaseTimeEntity {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId //unique 제약조건
	@Basic(optional = false) //not null 제약조건(false)
	@EqualsAndHashCode.Include
	private String username;
	
	@Basic(optional = false)
	private String email;
	
	@Basic(optional=false)
	private String password;
	
	@Builder.Default // 빌더패턴에서도 널이 아닌 해쉬셋 타입 객체로 초기화될 수 있도록.
	@ToString.Exclude // toString 메소드에서 제외
	@ElementCollection(fetch = FetchType.LAZY)
	//-> 연관 테이블 member_roles를 사용
	//-> 데이터 fetch 방식(가져오는 방식)은 Lazy 방식.
	@Enumerated(EnumType.STRING) //테이블에 저장할 때 상수의 이름으로 저장
	private Set<MemberRole> roles=new HashSet<>(); //기본 생성자에서 null이 아닌 값으로 초기화될 수 있도록.
	
	public Member addRole(MemberRole role) {
		roles.add(role); // HashSet<MemberRole> roles에 원소를 추가.
		return this;
	}
	
	public Member removeRole(MemberRole role) {
		roles.remove(role); // HashSet<MemberRole> roles에서 원소를 삭제.
		return this;
	}
	
	public Member clearRoles() {
		roles.clear(); // HashSet<MemberRole> roles의 모든 원소를 삭제.
		return this;
	}
}
