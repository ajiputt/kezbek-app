package com.telkomsel.kezbek.service.impl;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telkomsel.kezbek.common.bean.Response;
import com.telkomsel.kezbek.common.enumeration.CommonResponse;
import com.telkomsel.kezbek.model.Transaction;
import com.telkomsel.kezbek.request.CashbackRequest;
import com.telkomsel.kezbek.response.CashbackResponse;
import com.telkomsel.kezbek.service.CalucalteCashbackService;
import com.telkomsel.kezbek.service.CashbackService;
import com.telkomsel.kezbek.service.QueueSenderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CashbackServiceImpl implements CashbackService {

	private static Logger logger = LoggerFactory.getLogger(CashbackServiceImpl.class);

	private final CalucalteCashbackService calucalteCashbackService;

	private final QueueSenderService queueSenderService;

	@Override
	public ResponseEntity<Response> getCashback(CashbackRequest request) {
		Response response = null;
		try {
			Double resultQuantity = calucalteCashbackService.calculateCashbackQuantity(request);
			Double resultTiering = calucalteCashbackService.calculateCashbackTier(request);

			Transaction transaction = Transaction.builder()
					.email(request.getEmail())
					.partnerId(request.getPartnerId())
					.tierCashback(resultTiering)
					.quantityCashback(resultQuantity)
					.quantity(request.getQuantity())
					.price(request.getTotalAmount())
					.phoneNumber(request.getPhoneNumber())
					.createdDate(LocalDate.now())
					.build();

			CashbackResponse cashbackResponse = CashbackResponse.builder()
					.quantityCashback(resultQuantity)
					.tieringCashback(resultTiering)
					.totalCashback(Double.sum(resultQuantity, resultTiering))
					.totalAmount(request.getTotalAmount())
					.phoneNumber(request.getPhoneNumber())
					.email(request.getEmail())
					.build();

			response = Response.builder().commonResponse(CommonResponse.SUCCESS).data(cashbackResponse).build();

			queueSenderService.sendQueue(transaction);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.builder().commonResponse(CommonResponse.UNDEFINED_ERROR).build();
		}

		return ResponseEntity.ok(response);
	}

}
