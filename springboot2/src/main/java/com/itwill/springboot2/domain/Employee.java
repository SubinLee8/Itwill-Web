package com.itwill.springboot2.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/*
 * ORM(Object Relation Mapping, 객체 관계 매핑)
 * 객체와 테이블(데이터)를 매핑시키는 기술 스펙.
 * 
 * JPA(Java Persistence API):
 * 자바에서 ORM을 정의한 기술 스펙.
 * 
 * Hibernate: JPA를 구현한 프레임워크.
 */

@NoArgsConstructor
@Getter
//엔터티 클래스가 setter 메소드를 가지고 있지 않아도, JPA/Hibernate 프레임워크는 Reflexion을 사용해서
//private field 값들을 설정할 수 있다.
@ToString
@EqualsAndHashCode
@Entity //DB의 테이블에 매핑되는 자바 객체
@Table(name = "EMP")
public class Employee {
	@Id //Primary Key. Repasitory 인터페이스를 선언할 때 사용할 id 타입.
	@Column(name="empno")
	private Integer id;
	private String job;
	private String ename;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mgr")
	@ToString.Exclude
	private Employee manager;
	
	
	
	private LocalDateTime hiredate;
	@Column(name="sal")
	private Double salary;
	@Column(name="comm")
	private Double commision;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deptno")
	private Department department;
}
