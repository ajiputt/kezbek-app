package com.telkomsel.kezbek.service;

import org.springframework.http.ResponseEntity;

import com.telkomsel.kezbek.common.bean.Response;
import com.telkomsel.kezbek.request.CashbackRequest;

public interface CashbackService {

	ResponseEntity<Response> getCashback(CashbackRequest cashbackRequest);
}
