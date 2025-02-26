package com.itwill.springboot4.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot4.dto.PostCreateDto;
import com.itwill.springboot4.dto.PostItemDto;
import com.itwill.springboot4.dto.PostListItemDto;
import com.itwill.springboot4.repository.PostRepository;
import com.itwill.springboot4.service.PostService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
	private final PostService postServ;
	
	@GetMapping("/list")
	public void getList(Model model, @RequestParam(name="p", defaultValue = "0")Integer pageNo ) {
		Page<PostListItemDto> page=postServ.readAll(pageNo, Sort.by("id").descending()); //내림차순
		model.addAttribute("page", page);
	}
	
	@PostMapping("/create")
	public String createPost(PostCreateDto dto) {
		postServ.createPost(dto);
		return "redirect: /post/list";
	}
	
	@GetMapping("/create")
	public void toPostPage() {
		
	}
	
	@GetMapping("/details")
	public void toDetailsPage(Long id,Model model) {
		PostItemDto dto= postServ.readById(id);
		model.addAttribute("post", dto);
	}
}
