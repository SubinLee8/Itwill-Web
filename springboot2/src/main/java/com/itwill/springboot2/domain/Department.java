package com.itwill.springboot2.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name="dept")
public class Department {
	@Id
	@Column(name="deptno")
	private Integer id;
	private String dname;
	private String loc;
	
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy="department") 
	//Employee엔터티에서 @ManyToOne 애너테이션이 설정된 "필드"이름으로 설정.
	private List<Employee> employees;
}
