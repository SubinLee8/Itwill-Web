package com.itwill.spring1.web;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring1.dto.User;

import lombok.extern.slf4j.Slf4j;

/*
 * @Controller vs @RestController
 * @Controller 애너테이션이 설정된 클래스의 컨트롤러 메서드들의 리턴 값은
 * 		- 기본적으로는 뷰의 이름을 의미.
 * 		- @ResponseBody가 설정된 메서드인 경우에는, 응답으로 전송되는 데이터.
 * @RestController 애너테이션이 설정된 클래스의 모든 컨트롤러 메서드들의 리턴값은
 * 		- 응답으로 전송되는 데이터.
 * 		- RestController의 메서드에서는 @ResponseBody 애너테이션을 사용하지 않음. 
 * */


@Slf4j
@RestController
public class ExampleRestController {
	
	@GetMapping("/rest3")
	public String rest3() {
		log.debug("rest3()");
		return "안녕하세요, REST~";
	}
	
	@GetMapping("/rest4")
	public List<User> rest4() {
		log.debug("rest3()");
		List<User> list=new ArrayList<User>();
		User user1=User.builder().username("subin").age(10).build();
		User user2=User.builder().username("gahyoun").age(5).build();
		list.add(user1);
		list.add(user2);
		return list;
	}
	
}
