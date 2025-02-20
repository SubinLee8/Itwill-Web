package com.itwill.springboot3.domain;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor //엔터티클래스는 반드시 기본 생성자를 가져야함
@ToString
@EqualsAndHashCode
@Getter
@Entity 
@Table(name="EMPLOYEES")
public class Employee {
	@Id //반드시 @id 를 가지고 있어야함
	@Column(name="employee_id")
	private Integer id; 
	
	//JPA/Hibernate는 camel 표기법의 엔터티이름을 snake표기법의 컬럼 이름으로 매핑함.
	//필드 firstName <==> 컬럼 first_name(FIRST_NAME) 
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private LocalDate hireDate;
	
	//job엔터티에서 또 다른 객체를 부르지 않기 때문에 toString을 exclude할 필요는 없다
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="job_id")
	@ToString.Exclude
	private Job job;
	private Double salary;
	private Double commissionPct;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="manager_id")
	private Employee manager;
	
	@ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;
}
