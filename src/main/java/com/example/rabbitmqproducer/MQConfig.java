package com.example.rabbitmqproducer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;



@Configuration
public class MQConfig {
	
	@Bean
	public Queue queue() {
		
		return new Queue("message_queue");
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("message_exchange");
	}
	
	@Bean
	public Binding binding(Queue queue,TopicExchange topicExchange) {
		
		return BindingBuilder.bind(queue).to(topicExchange).with("message_routingKey");
		
	}
	
	@Bean
	public MessageConverter messageCoverter () {
		
		return new Jackson2JsonMessageConverter();
	}
	
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		
		template.setMessageConverter(messageCoverter());
		return template;
		
	}

}
