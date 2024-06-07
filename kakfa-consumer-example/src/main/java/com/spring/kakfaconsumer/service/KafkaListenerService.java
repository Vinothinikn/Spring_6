package com.spring.kakfaconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

//	Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);
	
	@KafkaListener(topics = "customer" ,groupId = "group-1")
	public void consume(String message) {
		//logger.info("Consumer consume the message {} ", message);
	}
}
