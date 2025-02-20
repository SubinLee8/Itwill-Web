package com.itwill.springboot2.repasitory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class DepartmentRepositoryTest {
	@Autowired
	DepartmentRepository deptRe;
	
	//@Test
	public void findAlltest() {
		 List<Department> lists=deptRe.findAll();
		 log.info("lists.size={}",lists.size());
		 assertThat(lists.size()).isEqualTo(4);
		 lists.forEach(System.out::println);
	}
	//@Test
	@Transactional
	public void findByID() {
		Department dept=deptRe.findById(30).orElseGet(null);
		assertThat(dept.getDname()).isEqualTo("SALES");
	    List<Employee> empos=dept.getEmployees();
	    empos.forEach(System.out::println);
	}
}
