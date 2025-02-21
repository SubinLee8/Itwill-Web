package com.itwill.springboot3.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmployeeController {
	private final EmployeeService empServ;
	
	@GetMapping("/list")
	public void getList(Model model) {
		model.addAttribute("employees",empServ.findAll());
	}
	
	@GetMapping("/details")
	public void getDetails(Model model, Integer id) {
		model.addAttribute("employee",empServ.findById(id));
	}
	
}
