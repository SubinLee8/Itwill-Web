package com.itwill.springboot2.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	private Integer mgr;
	private LocalDateTime hiredate;
	private Double sal;
	private Double comm;
	private Integer deptno;
}
