package com.example.demo.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Student;
import com.example.demo.service.MyDefaultInterface;
import com.example.demo.service.MyFunctionalInterface;
import com.example.demo.serviceImpl.ServiceImplementationClass;

@RestController
public class MainController {

	@Autowired
	MyDefaultInterface defaultInterface;
	
	@Autowired
	MyFunctionalInterface funcInterface;
	
	@Autowired
	ServiceImplementationClass serviceImpl;
	
	@Autowired
	Student student;
	
	@GetMapping("method")
	public void getDefaultMethod() {
		defaultInterface.myDefaultMethod();
		funcInterface.abstractMethod();
	}
	
	@GetMapping("service")
	public void getServiceImpl() {
		serviceImpl.remainingMethod();
		System.out.println("test");
	}
	
	@GetMapping("student")
	public void getStudent() {
		HashMap<Integer, String> studentMap = new HashMap<>();
		studentMap.put(1, "student1");
		studentMap.put(2, "student2");
		studentMap.put(3, "student3");
		studentMap.put(4, "student4");
		studentMap.put(5, "student5");
		studentMap.put(6, "student6");
		
//		for(Entry<Integer, String> entry: studentMap.entrySet()) {
//			System.out.println("Key: "+entry.getKey()+ " value: "+entry.getValue());
//		}
		
		studentMap.entrySet().forEach((e)->System.out.println("Keys " +e.getKey() + " Value "+e.getValue()));
		
		studentMap.put(6, "student7");
			
		LinkedHashMap<Integer, String> sortedStudentMap = studentMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by keys to maintain insertion order, if necessary
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, // Merge function to handle duplicate keys
                        LinkedHashMap::new // Supplier of the desired Map implementation
                ));
		
		sortedStudentMap.entrySet().forEach((e)->System.out.println("Linked Key " +e.getKey() + " Linked Value "+e.getValue()));
		
		List<String> studentNameList = studentMap.values().stream().collect(Collectors.toList());
		
		
		System.out.println("StudentName List "+studentNameList);
		
		List<Integer> studentIdList = studentMap.keySet().stream().collect(Collectors.toList());
		
		System.out.println("StudentId List "+studentIdList);
 		
	}
}
