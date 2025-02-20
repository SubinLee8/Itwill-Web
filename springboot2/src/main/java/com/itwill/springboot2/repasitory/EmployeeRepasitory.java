package com.itwill.springboot2.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Employee;


/*
 * Repository<T, ID>
 *     |__CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
 *     					       |__JpaRepository<T, ID>
 *     
 *  T: 엔터티 클래스
 *  ID: 엔터티 클래스의 @id 필드(pk)의 타입
 */ 

public interface EmployeeRepasitory extends JpaRepository<Employee, Integer>{

}
