package com.telkomsel.kezbek.model;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	private String id;
	
	private Integer quantity;
	
	private Double price;
	
	private String partnerId;
	
	private String email;

	private String phoneNumber;
	
	private Double quantityCashback;
	
	private Double tierCashback;

	private LocalDate createdDate;

}
