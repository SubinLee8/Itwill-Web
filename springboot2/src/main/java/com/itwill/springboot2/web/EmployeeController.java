package com.itwill.springboot2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot2.service.EmployeeServie;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmployeeController {
	private final EmployeeServie empServ; 
	
	@GetMapping("/list")
	public void list(Model model) {
	    model.addAttribute("employees", empServ.readAll());
	}
	
	@GetMapping("/details/{id}")
	public String details(Model model, @PathVariable Integer id) {
		model.addAttribute("employee",empServ.readById(id));
		return "/emp/details";
	}

}
