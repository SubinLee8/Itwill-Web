package com.itwill.springboot2.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Employee;

public interface EmployeeRepasitory extends JpaRepository<Employee, Integer>{

}
