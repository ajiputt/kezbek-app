package com.telkomsel.kezbek.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.telkomsel.kezbek.model.Transaction;
import com.telkomsel.kezbek.service.QueueSenderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueueSenderServiceImpl implements QueueSenderService {

	private final AmqpTemplate rabbitTemplate;

	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.email.queue}")
	String emailQueueName;

	@Value("${rabbitmq.transaction.queue}")
	String transactionQueueName;

	@Override
	public void sendQueue(Transaction transaction) {
		rabbitTemplate.convertAndSend(exchange, emailQueueName, transaction);
		rabbitTemplate.convertAndSend(exchange, transactionQueueName, transaction);
	}

}
