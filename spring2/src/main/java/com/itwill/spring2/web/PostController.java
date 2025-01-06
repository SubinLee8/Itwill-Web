package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.GetProxy;

@Slf4j
@RequiredArgsConstructor //final 필드를 아규먼트로 갖는 생성자.
@Controller //컨트롤러 컴포넌트
@RequestMapping("/post")
//-> PostController 클래스의 모든 메서드의 매핑 주소는 "/post"로 시작.
public class PostController {
	private final PostService postService;
	
	@GetMapping("/list") //->GET방식의 /post/list 주소를 처리하는 컨트롤러 메서드.
	public void list(Model model) {
		log.debug("list()");
		//컨트롤러 메서드의 리턴 타입이 void
		//뷰의 이름: "/WEB-INF/views/post/list.jsp"
		
		List<Post> list=postService.read();
		model.addAttribute("list", list);	
	}
	
	@GetMapping("/create")
	public void create() {
		log.debug("create()");
	}
	
	@PostMapping("/create")
	public String createPost(PostCreateDto dto) {
		log.debug("POST create(dto={})",dto);
		
		//controller ==> service 메소드 호출 & dto를 아규먼트로 전달.
		postService.create(dto);
		log.debug("new post={}",dto.toEntity());
		return "redirect:/post/list";
	}
}
