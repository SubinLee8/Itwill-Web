package com.itwill.springboot2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.repasitory.DepartmentRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class DepartmentService {
	private final DepartmentRepository deptRepo;
	
	public List<Department> readAll(){
		return deptRepo.findAll();
	}
	
	public Department readById(Integer id) {
		return deptRepo.findById(id).orElse(null);
	}
}
