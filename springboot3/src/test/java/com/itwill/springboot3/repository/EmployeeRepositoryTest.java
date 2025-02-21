package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repositoroy.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {
	@Autowired 
	private EmployeeRepository empRepo;
	
	//@Test
	public void testDependencyInjection() {
		assertThat(empRepo).isNotNull();
		log.info("empRepo={}", empRepo);
	}
	
	//@Test
	public void testFindAll() {
		List<Employee> list=empRepo.findAll();
		assertThat(list.size()).isEqualTo(107);
		log.info("index 0 : {}",list.get(0));
	}
	@Transactional
	//@Test
	public void testFindById() {
		Employee emp = empRepo.findById(100).orElseThrow();
		assertThat(emp.getFirstName()).isEqualTo("Steven");
		log.info("emp={}",emp);
		log.info("emp job {}", emp.getJob());
	}
	
	//@Test
	@Transactional
	public void testJpaQueryMethods() {
		//List<Employee> list4 = empRepo.findByDepartmentName("Executive");
		//list4.forEach(System.out::println);
		
	}
	
	@Test
	@Transactional
	public void jpqlTest() {
		List<Employee> list=empRepo.findByDeptName("IT");
		list.forEach(System.out::println);
	}
}
