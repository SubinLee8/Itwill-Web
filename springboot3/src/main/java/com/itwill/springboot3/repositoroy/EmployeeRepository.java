package com.itwill.springboot3.repositoroy;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.springboot3.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// JPA Query Method:
	// JPA에서 미리 약속된 키워드들과 엔터티들의 필드 이름들을 사용해서
	// 메서드 이름을 카멜 표기법으로 작성

	// SELECT * FROM EMPLOYEES WHERE DEPARTMENT_ID = ?
	List<Employee> findByDepartmentId(Integer id);

	// 이름이 일치하는 직원들 검색하기 (firstName)
	// SELECT * FROM EMPLOYEES WHERE FIRST_NAME=?
	List<Employee> findByFirstName(String firstName);

	// 이름이 일치하는 직원(들), 대소문자는 구분 없이 검색하기
	List<Employee> findByFirstNameIgnoreCase(String firstName);

	// 이름(first_name)에 포함된 문자열로 검색(% 위치 지정 가능)
	List<Employee> findByFirstNameLike(String firstName);

	// 이름(first_name)에 포함된 문자열로 검색 %문자열%
	List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

	// 이름(first_name)에 포함된 문자열로 검색하고 정렬
	List<Employee> findByFirstNameContainingOrderByFirstNameDesc(String firstName);

	// 대소문자구분 없이 성 또는 이름에 문자열이 포함된 직원 검색
	List<Employee> findByFirstNameOrLastNameContaining(String firstName, String lastName);

	// 급여가 어떤 값을 초과하는 직원들 검색
	List<Employee> findBySalaryGreaterThan(Integer salary);

	// 급여가 어떤 값의 미만인 직원들 검색
	List<Employee> findBySalaryLessThan(Integer salary);

	// 급여가 어떤 범위 안에 있는 직원들검색, 틀릴지도??
	List<Employee> findBySalaryBetween(Integer salary1, Integer salary2);

	// 입사 날짜가 특정 날짜 이후인 직원들 검색
	List<Employee> findByHireDateGreaterThan(LocalDate hireDate);

	// 입사 날짜가 특정 날짜 이전인 직원들 검색
	List<Employee> findByHireDateLessThan(LocalDate hireDate);
	// list=empRepo.findByHireDateLessThan(LocalDate.of(2003,1,1));

	// 입사 날짜가 특정 날짜 범위인 직원들 검색
	List<Employee> findByHireDateBetween(LocalDate hireDate1, LocalDate hireDate2);

	// 부서 이름으로 직원 검색
	List<Employee> findByDepartmentDepartmentName(String deptName);

	// 근무 도시 이름으로 직원 검색
	List<Employee> findByDepartmentLocationCity(String city);

	// 근무 국가 아이디로 직원 검색
	List<Employee> findByDepartmentLocationCountryId(String id);

	// JPQL(Java Persistence Query Language)
	// JPA에서 사용하는 객체지향 쿼리 문법
	// 테이블 이름과 컬럼 이름을 사용해서 쿼리 문장을 작성하는 것이 아니라,
	// 엔터티 이름과 엔터티 필드 이름을 사용해서 쿼리를 작성하는 문법.
	// ALIAS(별명)을 반드시 사용해야 함.
	// 엔터티 이름, 필드 이름들은 대소문자를 구분.

	@Query("select e from Employee e where upper(e.firstName) like upper('%' || ?1|| '%')"
			+ "or upper(e.lastName) like upper('%' || ?2  || '%')")
	List<Employee> findByName(String firstName, String lastName);

	@Query("select e from Employee e where upper(e.firstName) like upper('%' || ?1|| '%')"
			+ "or upper(e.lastName) like upper('%' || ?1 || '%')")
	List<Employee> findByName2(String name);
  
	@Query("select e from Employee e where upper(e.firstName) like upper('%' || :keyword || '%')"
			+ "or upper(e.lastName) like upper('%' || :keyword || '%')")
	List<Employee> findByName3(@Param("keyword") String name);
	
	@Query("select e from Employee e where e.department.departmentName= :deptName")
	List<Employee> findByDeptName(String deptName);

}
