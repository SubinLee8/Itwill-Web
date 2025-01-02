package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.spring1.dto.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ExampleController {

	@GetMapping("/")
	public String home(Model model) {
		log.debug("home()");
		LocalDateTime now = LocalDateTime.now(); // 현재 시간
		model.addAttribute("now", now);
		return "home";
	}

	@GetMapping("/example")
	public void ex(Model model) {
		log.debug("example()");
		// 디스패쳐 서블릿이 뷰의 이름을 ViewResolver를 이용해서 찾는 방법:
		// (1) 컨트롤러의 메서드가 String을 리턴하는 경우는, 리턴값이 jsp 파일의 이름.
		// (2) 컨트롤러의 메서드가 void 타입인 경우, 매핑된 요청주소가 jsp 파일의 이름.
	}
	
	/*
	 * 컨트롤러 메서드의 파라미터를 선언할 때 @RequestParam 애넡테이션을 사용하면,
	 * 디스패쳐 서블릿이 컨트롤러 메서드를 호출할 때, 요청 파라미터 값을 메서드 아규먼트로 전달하게 됨.
	 * 
	 * @RequestParam 애너테이션에 name 속성이 설정되지 않은 경우에는
	 * 메서드 파라미터 이름으로 요청 파라미터 값을 찾음.
	 * 
	 * 디스패처 서블릿을 컨트롤러 메서드의 아규먼트들을 전달하기 위해서 가능한 경우 타입변환도 자동으로 수행함.
	 * 
	 * @RequestParam 애너테이션의 defaultValue 속성 값을 설정하면
	 * 요청 파라미터 값이 없는 경우 기본값으로 사용할 수 있음.
	 */
	
	@GetMapping("/ex1")
	public void ex1(@RequestParam(name = "username") String name, @RequestParam(defaultValue = "0") int age, Model model) {
		log.debug("ex1(username={} age={})",name,age);
		
		//요청 파라미터값들로 User타입의 객체를 생성.
		User user = User.builder().age(age).username(name).build();
		
		//user 객체를 뷰(jsp)에게 전달.
		model.addAttribute("user", user);
	}
	
	//Post /ex2 요청 -> ex1.jsp
	@PostMapping("/ex2")
	public String ex2(User user) {
		//디스패처 서블릿은 컨트롤러 메서드를 호출하기 위해서,
		//(1) User 클래스의 기본 생성자를 호출.
		//(2) 요청 파라미터 값을 읽어서, 요청 파라미터 이름으로 User 클래스의 setter를 호출.
		//(3) User 타입 객체를 컨트롤러 메서드의 아규먼트로 전달.
		//(4) 컨트롤러 메서드의 아규먼트를 Model의 속성으로 추가.
		
		log.debug("ex2(user={})",user);
		
		
		return "ex1";
	}
	
	@GetMapping("/test")
	public void test() {
		log.debug("test()");
	}
	

	@GetMapping("/test2")
	public String test2() {
		log.debug("test2()");
		return "forward:/test";
	}
	
	@GetMapping("/test3")
	public String test3() {
		log.debug("test3()");
		return "redirect:/test";
		//컨트롤러 메서드가 "redirect:"으로 시작하는 문자열을 리턴.
		//->리다이렉트 방식의 이동.
		//HTTP 302(redirect)응답 이후에 클라이언트가 요청을 다시 보냄.
		//새로운 request, response 객체가 만들어짐. 
	}
	
	@ResponseBody
	//컨트롤러 메서드가 리턴하는 값이 뷰를 찾기 위한 문자열이 아니라,
	//클라이언트로 직접 응답으로 전송되는 데이터.
	@GetMapping("/rest1")
	public String rest1() {
		log.debug("rest1()");
		return "Hello, 안녕하세요!";
	}
	
	@GetMapping("/rest2") 
	@ResponseBody
	//->컨트롤러 메서드의 리턴값이 클라이언트에게 직접 전송되는 데이터.
	
	public User rest2() {
		log.debug("rest2()");
		return User.builder().age(26).username("홍길동").build();
		//->컨트롤러가 리턴한 자바 객체를 jackson-databind 라이브러리에서 
		// JSON형식의 문자열로 변경하고, 클라이언트에게 이를 응답.
		// (참고) jackson-databind의 기능: JAVA 객체 <--> JSON 문자열
	}

}
 