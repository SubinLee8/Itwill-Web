package com.itwill.springboot3.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmployeeController {
	private final EmployeeService empServ;
	
	@GetMapping("/list")
	public void getList(Model model, @RequestParam(name="p", defaultValue="0") int pageNo) {
		log.info("list(pageNo={})",pageNo);
		Page<Employee> list=empServ.read(pageNo,Sort.by("id")); //정렬할 컬럼 이름
		model.addAttribute("page",list);
	}
	
	@GetMapping("/details")
	public void getDetails(Model model, Integer id) {
		model.addAttribute("employee",empServ.findById(id));
	}
	
}
