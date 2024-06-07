package com.completablefuture.main.service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.main.entity.Employee;
import com.completablefuture.main.repo.EmployeeRepository;
import com.opencsv.CSVReader;

@Service
public class EmployeeService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EmployeeRepository repo;
	
	
	public List<Employee> readCsvFile(MultipartFile file){
		 List<Employee> employees = new ArrayList<>();
	        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
	            List<String[]> records = csvReader.readAll();
	            for (String[] record : records) {
	                Employee employee = new Employee();
	                employee.setId(record[0]);
	                employee.setFullName(record[1]);
	                employee.setJobTitle(record[2]);
	                employee.setDepartment(record[3]);
	                employee.setBusinessUnit(record[4]);
	                employee.setGender(record[5]);
	                employee.setEthinicity(record[6]);
	                employee.setAge(record[7]);
	                employee.setHireDate(record[8]);
	                employee.setAnnualSalary(record[9]);
	                employee.setBonus(record[10]);
	                employee.setCountry(record[11]);
	                employee.setCity(record[12]);
	                employee.setExitDate(record[13]);
	                employees.add(employee);
	            }
	        }catch(Exception e) {
	        	logger.info("Exception thrown in EmployeeService "+e.getMessage());
	        }
	        return employees;
	}
	
	
	public void saveEmployees(List<Employee> employees) {
        repo.saveAll(employees);
    }

}
