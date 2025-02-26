package com.itwill.springboot4.domain;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
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
@NoArgsConstructor // 엔터티클래스는 반드시 기본 생성자를 가져야함

//Post의 toString메서드를 작성할 때 BaseTimeEntity의 toString()을 사용.
@ToString(callSuper=true)

//Post의 equals 메서드를 작성할 때 상위 클래스 BaseTimeEntity의 equals()를 사용.
@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
@Table(name = "posts")

//BaseTimeEntity를 상속받는다.
public class Post extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	//primary key값을 생성하는 방법-테이블 생성 시 "generatedd as identity"로 설정한 경우.
	private Long id;
	@Basic(optional = false)
	private String title;
	@Basic(optional = false)
	private String content;
	@Basic(optional = false)
	private String author;
	
	public Post update(String title, String content) throws NullPointerException{
		//title과 content는 null되면 안됨.
		this.title=title;
		this.content=content;
		return this;
	}
}
