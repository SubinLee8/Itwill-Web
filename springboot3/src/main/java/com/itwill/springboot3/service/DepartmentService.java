package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.repositoroy.DepartmentRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class DepartmentService {
	private final DepartmentRepository deptRepo;
	
	public List<Department> findAll(){
		return deptRepo.findAll();
	}
	
	public Department findById(Integer id) {
		return deptRepo.findById(id).orElseThrow();
	}
}
