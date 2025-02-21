package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repositoroy.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService {
	private final EmployeeRepository empRepo;
	
	public List<Employee> findAll(){
		return empRepo.findAll();
	}
	
	public Employee findById(Integer id) {
		return empRepo.findById(id).orElseThrow();
	}
	
	public List<Employee> findByDept(Integer id) {
		return empRepo.findByDepartmentId(id);
	}
}
