package com.spring.hibernatedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.hibernatedemo.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
