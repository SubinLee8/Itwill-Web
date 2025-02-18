package com.itwill.springboot1.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.springboot1.domain.Author;
import com.itwill.springboot1.domain.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		log.info("home() 호출");
		
		LocalDateTime now = LocalDateTime.now(); // 서버 현재 시간
		model.addAttribute("currentTime", now); // 뷰에 전달되는 데이터
		
		Author author = Author.builder()
				.firstName("강").lastName("한").build();
		Book book = Book.builder()
				.id(100100).title("채식주의자").author(author).build();
		model.addAttribute("book", book);
		
		return "index";
		// 컨트롤러 메서드의 리턴 값(문자열) -> 뷰의 이름
		// 스프링 부트 프로젝트에서는 src/main/resources/templates/리턴값.html
	}
	
	@GetMapping("/book/list")
	public void bookList(Model model) {
		log.info("bookList()");
		
		// 도서 목록을 저장할 ArrayList:
		ArrayList<Book> list = new ArrayList<>();
		
		// list에 Book 타입 객체를 5개 저장.
		List<Book> books = new ArrayList<Book>();
		for (int i=0;i<5;i++) {
			books.add(Book.builder().id(i).author(Author.builder().firstName("first"+i).lastName("last"+i).build()).title("제목"+i).build());
		}
		books.add(Book.builder().build());
		// list를 뷰에 전달.
		model.addAttribute("books",books);
		// 뷰(html) 작성. -> controller 메소드가 void인 경우 뷰의 이름은 요청 url과 같음.
		// src/main/resources/templates/book/list.html
	}
	
	@GetMapping("/book/details")
	public void bookDetails(Model model, Integer id) {
		log.info("details(id={})",id);
		Book book = Book.builder().id(id).title("무제").build();
		model.addAttribute("book", book);
	}
	
	@GetMapping("/book/details/{id}")
	public String bookDetailsWithPathVariable(Model model, Integer id) {
		log.info("details(id={})",id);
		Book book = Book.builder().id(id).title("무제").build();
		model.addAttribute("book", book);
		return "book/details";
	}

}