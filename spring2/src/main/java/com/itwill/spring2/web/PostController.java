package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.GetProxy;

@Slf4j
@RequiredArgsConstructor // final 필드를 아규먼트로 갖는 생성자.
@Controller // 컨트롤러 컴포넌트
@RequestMapping("/post")
//-> PostController 클래스의 모든 메서드의 매핑 주소는 "/post"로 시작.
public class PostController {
	private final PostService postService;

	@GetMapping("/list") // ->GET방식의 /post/list 주소를 처리하는 컨트롤러 메서드.
	public void list(Model model) {
		log.debug("list()");
		// 컨트롤러 메서드의 리턴 타입이 void
		// 뷰의 이름: "/WEB-INF/views/post/list.jsp"

		List<Post> list = postService.read();
		model.addAttribute("list", list);
	}

	@GetMapping("/create")
	public void create() {
		log.debug("create()");
	}

	@PostMapping("/create")
	public String createPost(PostCreateDto dto) {
		log.debug("POST create(dto={})", dto);

		// controller ==> service 메소드 호출 & dto를 아규먼트로 전달.
		postService.create(dto);
		log.debug("new post={}", dto.toEntity());
		return "redirect:/post/list";
	}

	@GetMapping({ "/details", "/modify" })
	// =>GET 방식의 /post/details와 /post/modify 요청 주소들을 처리하는 컨트롤러
	// =>리턴타입이 void이므로 요청주소가 details일때는 details.jsp
	// =>modify일 때는 /modify.jsp
	public void details(@RequestParam Integer id, Model model) {
		log.debug("details/modify(id={}", id);
		// 포스트 객체 불러옴
		Post post = postService.read(id);
		// 상세보기 내용을 뷰에 전달하기 위해 모델에 속성으로 추가.
		model.addAttribute(post);

	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		log.debug("delete(postId={})",id);
		postService.delete(id);
		return "redirect:/post/list";
	}
	
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.debug("update(dto={})",dto);
		postService.update(dto);
		return "redirect:/post/details?id="+dto.getId();
	}
	
	@GetMapping("/search")
	public String search(PostSearchDto dto, Model model) {
		log.debug("search(dto={})",dto);
		List<Post> list=postService.read(dto);
		model.addAttribute("list", list);
		return "post/list"; //=> /WEB-INF/views/post/list.jsp
	}
	
	

}
