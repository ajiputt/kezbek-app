package com.telkomsel.kezbek.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.telkomsel.kezbek.common.bean.Response;
import com.telkomsel.kezbek.request.CashbackRequest;
import com.telkomsel.kezbek.service.CalucalteCashbackService;
import com.telkomsel.kezbek.service.QueueSenderService;

@ExtendWith(MockitoExtension.class)
class CashbackServiceImplTest {

	@Mock
	private CalucalteCashbackService calucalteCashbackService;

	@Mock
	private QueueSenderService queueSenderService;

	@Autowired
	private CashbackServiceImpl cashbackServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		cashbackServiceImpl = new CashbackServiceImpl(calucalteCashbackService,
				queueSenderService);
	}

	@Test
	void shouldReturn_Cashback() {
		CashbackRequest cashbackRequest = CashbackRequest.builder()
				.email("test@email.com")
				.quantity(1)
				.partnerId("PARTNER-TEST")
				.totalAmount(Double.valueOf(1000000))
				.build();

		ResponseEntity<Response> resp = cashbackServiceImpl.getCashback(cashbackRequest);

		

		Assert.assertNotNull("Return Cashback Detail", resp.getBody());

	}

}
