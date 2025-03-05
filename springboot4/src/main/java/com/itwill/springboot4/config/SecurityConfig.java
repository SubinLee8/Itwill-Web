package com.itwill.springboot4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
//-> 스프링 컨테이너에서 생성하고 관리하는 설정 컴포넌트.
//-> 스프링 컨테이너에서 필요한 곳에 의존성을 주입해 줄 수 있음.

public class SecurityConfig {

	@Bean // ->스프링 컨테이너에서 관리하는 객체(빈) -> 의존성 주입.
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * UserDetailService: 사용자 관리(로그인, 로그아웃, 회원가입 등)을 위한 서비스 인터페이스.
	 * 
	 * 스프링 부트 애플리케이션에서 스프링 시큐리티를 이용한 로그잇/로그아웃을 구현하려면 (1)UserDetailsService 인터페이스를
	 * 구현하는 서비스 클래스와 (2)UserDetails 인터페이스를 구현하는 엔터티 클래스가 있어야 함.
	 * 
	 */

	// ->임시로 사용할 코드(테스트용)
	// ->사용자(User) 엔터티와 사용자 서비스를 구현하기 전에 테스트 용도로 사용할 코드.
	@Bean
	UserDetailsService inMemoryUserDetailsService() {
		// 어플리케이션이 동작 중에 메모리에 임시 저장할 사용자 객체들을 생성
		UserDetails user1 = User.withUsername("user1")// 로그인 사용자 아이디
				.password(passwordEncoder().encode("1111"))// 암호화된 로그인 사용자 비밀번호
				.roles("USER").build();
		UserDetails user2 = User.withUsername("user2").password(passwordEncoder().encode("2222")).roles("ADMIN")
				.build();
		UserDetails user3 = User.withUsername("user3").password(passwordEncoder().encode("3333")).roles("USER", "ADMIN")
				.build();
		// User 타입 객체 3개를 가지고 있는 사용자 상세정보 매니저 객체를 생성하고 리턴.
		return new InMemoryUserDetailsManager(user1, user2, user3);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		log.info("SecurityFilterChain 생성");

		// CSRF 기능 비활성화
		http.csrf(t -> t.disable());

		// 로그인 페이지(폼) 설정을 스프링 시큐리티에서 제공하는 기본 html 페이지를 사용하도록 설정
		http.formLogin(t -> t.loginPage("/member/signin"));

		// 페이지 접근 권한, 인증 구성:
		http.authorizeHttpRequests(t ->
		// 모든 요청 주소에 대해서 (권한(roll)에 상관없이) 아이디/비밀번호 인증을 하는 경우:
		// t.anyRequest().authenticated())

		// 모든 요청 주소에 대해서 유저 권한을 가진 사용자의 아이디/비밀번호 인증을 하는 경우:
		// t.anyRequest().hasRole("USER")
        
		// 특정 요청 주소에 대해서 유저 권한을 가진 사용자의 아이디/비밀번호 인증을 하는 경우: 
		//유저가 아닌 사용자는 접근할수없다
		t.requestMatchers("/post/create", "/post/details","/post/modify", "/post/delete", "/post/update", "/api/comment/**").hasRole("USER")
		
		//.requestMatchers("").hasRole("") ... 추가로 설정
		.anyRequest().permitAll() //그 외는 모두 허용

		);
		return http.build(); //Security 필터 체인을 생성해서 리턴
	}
}
