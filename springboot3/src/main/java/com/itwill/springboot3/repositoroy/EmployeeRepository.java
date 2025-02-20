package com.itwill.springboot3.repositoroy;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	// 이름(first_name)에 포함된 문자열로 검색
	List<Employee> findByFirstNameContaining(String firstName);

	// 이름(first_name)에 포함된 문자열로 검색하고 정렬
	List<Employee> findByFirstNameContainingOrderByFirstNameDesc(String firstName);

	// 대소문자구분 없이 성 또는 이름에 문자열이 포함된 직원 검색
	List<Employee> findByFirstNameOrLastNameContaining(String name);

	// 급여가 어떤 값을 초과하는 직원들 검색
	List<Employee> findBySalaryGreaterThan(Integer salary);

	// 급여가 어떤 값의 미만인 직원들 검색
	List<Employee> findBySalaryLessThan(Integer salary);

	// 급여가 어떤 범위 안에 있는 직원들검색, 틀릴지도??
	List<Employee> findBySalaryBetween(Integer salary1, Integer salary2);

	// 입사 날짜가 특정 날짜 이후인 직원들 검색
	List<Employee> findByHireDateAfter(LocalDate hireDate);

	// 입사 날짜가 특정 날짜 이전인 직원들 검색
	List<Employee> findByHireDateBefore(LocalDate hireDate);

	// 입사 날짜가 특정 날짜 범위인 직원들 검색
	List<Employee> findByHireDateBetween(LocalDate hireDate1, LocalDate hireDate2);

	// 부서 이름으로 직원 검색
	@Query("select e from Employee e left join e.department d where e.departmentName = :departmentName")
	List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);


	// 근무 도시 이름으로 직원 검색
	List<Employee> findByCity(String city);

	// 근무 국가 아이디로 직원 검색
	List<Employee> findByCountryId(Integer id);
}
