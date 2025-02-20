package com.itwill.springboot2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.repasitory.EmployeeRepasitory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RequiredArgsConstructor
@Service
@Slf4j
public class EmployeeServie {
	private final EmployeeRepasitory empRepo;
	
	public List<Employee> readAll(){
		return empRepo.findAll();
	}

	public Employee readById(Integer id) {
		return empRepo.findById(id).orElseThrow();
	}
}
