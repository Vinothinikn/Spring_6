package com.spring.kakfaproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.Customer;
import com.spring.kakfaproducer.service.KafkaMessagePublisher;
import com.spring.kakfaproducer.status.Status;

@RestController
@RequestMapping("/producer-app")
public class EventController {

	@Autowired
	KafkaMessagePublisher publisher;
	
	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publishMessage(@PathVariable String message){
		try {
			for(int i=0;i<10000;i++) {
			publisher.sendMessageToTopic(message);
			}
			return new ResponseEntity<>("Message successfully published",HttpStatus.ACCEPTED);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/publish/events")
	public ResponseEntity<Status> publishCustomerEvents(@RequestBody Customer customer) {
		try {
			publisher.sendEventToTopic(customer);
			Status status = new Status(HttpStatus.OK.value(), "Request was successful");
			return new ResponseEntity<>(status,HttpStatus.OK);
		}catch(Exception e) {
			Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred");
	        return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
