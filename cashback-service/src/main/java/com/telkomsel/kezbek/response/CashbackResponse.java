package com.telkomsel.kezbek.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CashbackResponse {

	private Double tieringCashback;

	private Double quantityCashback;
	
	private Double totalCashback;

	private Double totalAmount;

	private String email;

	private String phoneNumber;

}
