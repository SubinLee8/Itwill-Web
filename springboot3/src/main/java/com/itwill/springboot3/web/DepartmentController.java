package com.itwill.springboot3.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.service.DepartmentService;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DepartmentController {
	private final DepartmentService deptServ;
	private final EmployeeService empServ;
	
	@GetMapping("/list")
	public void getAll(Model model) {
		model.addAttribute("departments", deptServ.findAll());
	}
	
	@GetMapping("/details")
	public void getDetails(Model model, Integer id) {
		model.addAttribute("department", deptServ.findById(id));
		model.addAttribute("employees",empServ.findByDept(id));
		
	}

}
