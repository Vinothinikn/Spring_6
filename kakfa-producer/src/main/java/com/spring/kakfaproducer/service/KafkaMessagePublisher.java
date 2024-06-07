package com.spring.kakfaproducer.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.spring.dto.Customer;


@Service
public class KafkaMessagePublisher {

	@Autowired
	private KafkaTemplate<String, Object> template;	
	
	public void sendMessageToTopic(String message) {
		
		CompletableFuture<SendResult<String, Object>>  future = template.send("Sample_Topic", message);
		future.whenComplete((result,ex)->{
			if(ex==null) {
				System.out.println("Send Message=[" +message+ "] with offset=[" +result.getRecordMetadata().offset() + "]");
			}else {
				System.out.println("Unable to send message=[" +message + "] due to : " +ex.getMessage());
			}
		});
	}
	
   public void sendEventToTopic(Customer message) {
		
		CompletableFuture<SendResult<String, Object>>  future = template.send("customer1", message);
		future.whenComplete((result,ex)->{
			if(ex==null) {
				System.out.println("Send Message=[" +message.toString()+ "] with offset=[" +result.getRecordMetadata().offset() + "]");
			}else {
				System.out.println("Unable to send message=[" +message.toString() + "] due to : " +ex.getMessage());
			}
		});
	}
}
