package com.spring.batchdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.batchdemo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
