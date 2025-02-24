package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repositoroy.EmployeeRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
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
	
	//pageNo: 페이지 번호, sort: 정렬 조건(컬럼, 방식)
	public Page<Employee> read(int pageNo, Sort sort){
		Pageable pageable=PageRequest.of(pageNo, 10, sort);
		//JpaRepository<T,ID>.findAll(Pageable pageable) 메서드 호출
		Page<Employee> page=empRepo.findAll(pageable);
		log.info("hasNext={}",page.hasNext()); //이후 페이지가 있는지 여부
		log.info("hasPrevious={}",page.hasPrevious()); //이전페이지가 있는지 여부
		log.info("getNumber={}", page.getNumber()); //현재 페이지 번호
		log.info("totalPages={}",page.getTotalPages()); //전체 페이지 번호
		return page;
	}
}
