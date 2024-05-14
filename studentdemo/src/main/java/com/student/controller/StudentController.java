package com.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

	@ResponseBody
	@GetMapping("/home")
	public String welcome() {
		return "Welcome to HomePage";
	}
	
}
