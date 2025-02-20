package com.itwill.springboot2.repasitory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot2.domain.Employee;

import jakarta.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepasitoryTest {
	@Autowired //의존성 주입
	private EmployeeRepasitory empRepo;
	
	//@Test
	public void test() {
		Assertions.assertThat(empRepo).isNotNull();
	}
	
	@Transactional
	//@Test
	public void testFindAll() {
		List<Employee> list = empRepo.findAll();
		assertThat(list.size()).isEqualTo(14);
		list.forEach(emp -> {
			System.out.println("emp"+emp);
			System.out.println("emp department"+emp.getDepartment());
			System.out.println("emp manager"+emp.getManager());
		});
	}
	
	//@Test
	@Transactional
	public void testFindById() {
		//Optional<T>.orElseThrow(): 데이터가 있으면 T 타입의 객체를 리턴, 
		//데이터가 없으면 exception 발생.
		Employee emp1=empRepo.findById(7788).orElseThrow();
		assertThat(emp1.getEname()).isEqualTo("SCOTT");
		log.info("emp={}",emp1);
		log.info("emp dept={}",emp1.getDepartment());
		log.info("emp mgr={}",emp1.getManager());
		
		//Optional<T>.orElse(T other): 데이터가 있으면 T타입의 객체를 리턴,
		//데이터가 없으면 아규먼트로 전달된 other 객체를 리턴(현재는 null 리턴)
		//Employee emp2=empRepo.findById(1000).orElse(null);
		//log.info("emp2={}",emp2);
		
		//Optional<T>.orElseGet(Supplier function): 데이터가 있으면 T타입의 객체를 리턴,
		//데이터가 없으면 람다표현식 function에서 리턴하는 객체를 리턴
		//Employee emp3=empRepo.findById(1000).orElseGet(()->null);
	}
	
	
	//@Test
	
}
