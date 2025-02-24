package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.DepartmentDetailsDto;
import com.itwill.springboot3.repositoroy.DepartmentRepository;
import com.itwill.springboot3.repositoroy.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DepartmentService {
	private final DepartmentRepository deptRepo;
	private final EmployeeRepository empRepo;

	public List<Department> findAll() {
		return deptRepo.findAll();
	}

	public Department findById(Integer id) {
		return deptRepo.findById(id).orElseThrow();
	}

	public DepartmentDetailsDto readDepartmentDetails(Integer id) {
		Department dept = deptRepo.findById(id).orElseThrow();
		List<Employee> employees = empRepo.findByDepartmentId(id);
		return DepartmentDetailsDto.fromEntity(dept, employees);
	}

	// pageNo: 페이지 번호, sort: 정렬 조건(컬럼, 방식)
	public Page<Department> read(int pageNo, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, 10, sort);
		// JpaRepository<T,ID>.findAll(Pageable pageable) 메서드 호출
		Page<Department> page = deptRepo.findAll(pageable);
		return page;
	}
}
