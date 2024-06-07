package com.completablefuture.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.completablefuture.main.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
