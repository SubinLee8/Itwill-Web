package com.itwill.springboot4.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot4.dto.PostCreateDto;
import com.itwill.springboot4.dto.PostItemDto;
import com.itwill.springboot4.dto.PostListItemDto;
import com.itwill.springboot4.dto.PostSearchDto;
import com.itwill.springboot4.dto.PostUpdateDto;
import com.itwill.springboot4.repository.PostRepository;
import com.itwill.springboot4.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/post")
public class PostController {
	private final PostService postServ;

	@GetMapping("/list")
	public void getList(Model model, @RequestParam(name = "p", defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "") String category, @RequestParam(defaultValue = "") String keyword) {
		Page<PostListItemDto> page = postServ.readAll(pageNo, Sort.by("id").descending()); // 내림차순
		model.addAttribute("page", page);
		model.addAttribute("baseUrl", "/post/list");
	}

	@PostMapping("/create")
	public String createPost(PostCreateDto dto) {
		Long id = postServ.createPost(dto);
		return "redirect:/post/list";
	}

	@GetMapping("/search")
	public String searchPost(PostSearchDto dto, Model model) {
		Page<PostListItemDto> page = postServ.search(dto, Sort.by("id").descending());
		log.info("page= {}", page);
		model.addAttribute("page", page);
		model.addAttribute("baseUrl", "/post/search");
		return "/post/list";
	}

	@GetMapping("/create")
	public void toPostPage() {

	}

	@GetMapping("/details")
	public void toDetailsPage(Long id, Model model) {
		PostItemDto dto = postServ.readById(id);
		model.addAttribute("post", dto);
	}

	@GetMapping("/modify/{id}")
	public String toModifyPage(@PathVariable Long id, Model model) {
		PostItemDto dto = postServ.readById(id);
		model.addAttribute("post", dto);
		return "post/modify";
	}

	@GetMapping("/delete")
	public String delete(Long id) {
		log.info("delete(id={})", id);
		postServ.delete(id);
		return "redirect:/post/list";
	}

	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.info("update dto={}", dto);
		postServ.update(dto);
		return "redirect:/post/details?id=" + dto.getId();
	}
}
