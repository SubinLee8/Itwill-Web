package com.itwill.springboot3.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
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
	public void getAll(Model model, @RequestParam(name="p", defaultValue="0")Integer pageNo) {
		Page<Department> list=deptServ.read(pageNo,Sort.by("id")); //정렬할 컬럼 이름
		model.addAttribute("page",list);
	}
	
	@GetMapping("/details")
	public void getDetails(Model model, Integer id) {
		model.addAttribute("dto", deptServ.readDepartmentDetails(id));
	}

}
