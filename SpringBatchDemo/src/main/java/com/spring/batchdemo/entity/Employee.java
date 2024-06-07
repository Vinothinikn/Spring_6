package com.spring.batchdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMPLOYEE_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	
	@Id
	@Column(name = "eeId")
	private String id;
	
	@Column(name = "FullName")
	private String fullName;
	
	@Column(name = "JobTitle")
	private String jobTitle;
	
	@Column(name = "Department")
	private String department;
	
	@Column(name = "BusinessUnit")
	private String businessUnit;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Ethinicity")
	private String ethinicity;
	
	@Column(name = "Age")
	private String age;
	
	@Column(name = "HireDate")
	private String hireDate;
	
	@Column(name = "AnnualSalary")
	private String annualSalary;
	
	@Column(name = "Bonus")
	private String bonus;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "ExitDate")
	private String exitDate;
	
	
}
