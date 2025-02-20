package com.itwill.springboot3.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name="departments")
public class Department {
	@Id
	@Column(name="department_id")
	private Integer id;
	
	private String departmentName;
	
	@ToString.Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="manager_id")
	private Employee manager;
	@ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location_id")
	private Location location;
}
