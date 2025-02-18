package com.itwill.springboot2.repasitory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot2.domain.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepasitoryTest {
	@Autowired
	private EmployeeRepasitory empRepo;
	
	//@Test
	public void test() {
		Assertions.assertThat(empRepo).isNotNull();
	}
	
	//@Test
	public void testFindAll() {
		List<Employee> list = empRepo.findAll();
		assertThat(list.size()).isEqualTo(14);
		list.forEach(emp -> log.info("emp={}",emp));
	}
	
	@Test
	public void testFindById() {
		Employee emp1=empRepo.findById(7788).orElseThrow();
		assertThat(emp1).isNotNull();
		log.info("{}",emp1);
	}
}
