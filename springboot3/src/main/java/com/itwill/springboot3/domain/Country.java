package com.itwill.springboot3.domain;

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
@Table(name="countries")
public class Country {
	@Id
	@Column(name="country_id")
	private String id;
	private String countryName;
	@ToString.Exclude
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id")
	private Region region;
}
