package com.itwill.springboot3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Entity
@Table(name="jobs")
public class Job {
	@Id
	@Column(name="job_id")
	private String id;
	private String jobTitle;
	private Integer minSalary;
	private Integer maxSalary;
}
