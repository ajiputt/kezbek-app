package com.telkomsel.kezbek.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telkomsel.kezbek.common.bean.Response;
import com.telkomsel.kezbek.request.CashbackRequest;
import com.telkomsel.kezbek.service.CashbackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CashbackController {

	private final CashbackService cashbackService;

	@PostMapping("/cashback")
	public ResponseEntity<Response> getCashback(@RequestBody @Valid CashbackRequest cashbackRequest) {
		return cashbackService.getCashback(cashbackRequest);
	}

}
