package com.spring.hibernatedemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.hibernatedemo.entity.Employee;
import com.spring.hibernatedemo.repository.EmployeeRepo;

public class EmployeeService {
	
	@Autowired
	EmployeeRepo repo;

	public Employee getEmployeeById(Long id){
		Optional<Employee> employee = repo.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			return null;
		}
	}
}
