package com.telkomsel.kezbek.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

	@Value("${rabbitmq.email.queue}")
	String emailQueueName;

	@Value("${rabbitmq.transaction.queue}")
	String transactionQueueName;

	@Value("${rabbitmq.exchange}")
	String exchange;

	@Bean
	Queue emailQueue() {
		return new Queue(emailQueueName, false);
	}

	@Bean
	Queue transactionQueue() {
		return new Queue(transactionQueueName, false);
	}

	@Bean
	DirectExchange topicExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding bindingEmail(DirectExchange exchange) {
		return BindingBuilder.bind(emailQueue()).to(exchange).with(emailQueue().getName());
	}

	@Bean
	Binding bindingTransaction(DirectExchange exchange) {
		return BindingBuilder.bind(transactionQueue()).to(exchange).with(transactionQueue().getName());
	}

	@Bean
	MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	AmqpTemplate rabbitQueueTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
