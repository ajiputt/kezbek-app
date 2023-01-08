package com.telkomsel.kezbek.consumer.component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.telkomsel.kezbek.consumer.model.Transaction;
import com.telkomsel.kezbek.consumer.repository.TransactionRepository;
import com.telkomsel.kezbek.consumer.service.impl.EmailSenderServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueueReceiver {

	Logger logger = LoggerFactory.getLogger(QueueReceiver.class);

	@Value("${url.topup.partner}")
	String url;

	private final EmailSenderServiceImpl emailSenderServiceImpl;

	private final TransactionRepository transactionRepository;

	private final RestTemplate restTemplate;

	@RabbitListener(queues = "${rabbitmq.email.queue}")
	public void recievedEmailMessage(Transaction transaction) {
		try {
			emailSenderServiceImpl.sendEmail(transaction);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

	@RabbitListener(queues = "${rabbitmq.transaction.queue}")
	public void recievedTopupMessage(Transaction transaction) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		Map<String, Object> map = new HashMap<>();
		map.put("userId", 1);
		map.put("title", "Introduction to Spring Boot");
		map.put("body", "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class, 1);

		if (response.getStatusCode() == HttpStatus.OK) {

			transaction.setTopupStatus("Success");

		} else {
			transaction.setTopupStatus("Failed");
		}
		
		transaction.setCreatedDate(LocalDate.now());
		transactionRepository.save(transaction);

	}

}
