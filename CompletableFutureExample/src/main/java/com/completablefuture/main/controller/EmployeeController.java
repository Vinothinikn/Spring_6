package com.completablefuture.main.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.completablefuture.main.dto.StatusCode;
import com.completablefuture.main.entity.Employee;
import com.completablefuture.main.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private final Executor executor = Executors.newFixedThreadPool(2);

	@Autowired
	EmployeeService service;
	
	@PostMapping("/save")
	ResponseEntity<?> saveEmployeeDataFromCsv(@RequestParam("file") MultipartFile file){
		
		if(file.isEmpty()) {
			StatusCode status = new StatusCode(601, "File not found");
			return new ResponseEntity<StatusCode>(status, HttpStatus.NOT_FOUND);
		}
		try {
			List<Employee> empList = service.readCsvFile(file);
			service.saveEmployees(empList);
			StatusCode success = new StatusCode(602, "Saved Successfully");
			return new ResponseEntity<StatusCode>(success, HttpStatus.CREATED);
		}catch(Exception e) {
			logger.error("Exception in EmployeeController "+e.getMessage());
			StatusCode error = new StatusCode(500, "Internal Server Error");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	 @PostMapping("/upload-csv")
	    public CompletableFuture<ResponseEntity<StatusCode>> uploadCsvFile(@RequestParam("file") MultipartFile file) {
		
		 return CompletableFuture.supplyAsync(() -> {
	            try {
	                return service.readCsvFile(file);
	            } catch (Exception e) {
	            	StatusCode failure = new StatusCode(601, "Error in Reading the file");
		            return new ResponseEntity<StatusCode>(failure, HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        }, executor).thenApplyAsync(empList -> {
	            service.saveEmployees((List<Employee>) empList);
	            StatusCode success = new StatusCode(602, "Saved Successfully");
	            return new ResponseEntity<StatusCode>(success, HttpStatus.CREATED);
	        }, executor).exceptionally(e -> {
	            logger.error("Exception in EmployeeController: " + e.getMessage(), e);
	            StatusCode error = new StatusCode(500, "Internal Server Error");
	            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	        });
	    }
	    
}
