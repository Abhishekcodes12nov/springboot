package com.example.rabbitmqproducer;

import java.util.Date;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class MessagePublisher {
	
	
	@PostMapping("/publish")
	public String publishMessage(@RequestBody  CustomeMessageVO message) {
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageDate(new Date());
		
		return "message sent to queue";
	}
	

}
