package com.medium.agrawalniket.spring.boot.h2.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.medium.agrawalniket.spring.boot.h2.entity.Employee;
import com.medium.agrawalniket.spring.boot.h2.repository.EmployeeRepository;

@RestController
public class UserController {
  @Autowired
  private EmployeeRepository employeeRepository;

  @GetMapping("findEmployee/{empId}")
  public Employee getEmployeeById(@PathVariable("empId") Integer empId) {
    return employeeRepository.findById(empId).get();
  }

  @GetMapping("findAllEmployee")
  public List<Employee> getAllEmployee() {
    return employeeRepository.findAll();
  }

  @PostMapping("saveEmployee")
  public Employee saveEmployee(@RequestBody Employee employee) {
    return employeeRepository.save(employee);
  }

  @DeleteMapping("deleteEmployee/{empId}")
  public void deleteEmployee(@PathVariable("empId") Integer empId) {
    employeeRepository.deleteById(empId);
  }
}
