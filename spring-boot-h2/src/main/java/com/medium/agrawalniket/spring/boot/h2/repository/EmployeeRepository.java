package com.medium.agrawalniket.spring.boot.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.medium.agrawalniket.spring.boot.h2.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>  {

}
