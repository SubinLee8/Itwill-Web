package com.itwill.springboot4.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor // 엔터티클래스는 반드시 기본 생성자를 가져야함
@ToString (callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
@Builder
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Table(name = "comments")
public class Comment extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Exclude
	@Basic(optional = false)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="post_id")
	private Post post;
	
	@Basic(optional = false)
	private String text;
	
	@Basic(optional = false)
	private String writer;
	
	public Comment update(String text) {
		this.text=text;
		return this;
	}
}
