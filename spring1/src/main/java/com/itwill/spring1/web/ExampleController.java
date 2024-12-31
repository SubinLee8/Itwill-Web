package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
