package com.itwill.springboot2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot2.service.DepartmentService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DepartmentController {
	private final DepartmentService deptServ;
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("departments", deptServ.readAll());
	}
	
	@GetMapping("/details")
	public String details(Integer id, Model model) {
		model.addAttribute("department", deptServ.readById(id));
		return "/dept/details";
	}
}
