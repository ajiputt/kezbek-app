package com.telkomsel.kezbek.consumer.model;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_transaction", schema = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private Integer quantity;
	
	private Double price;
	
	private String partnerId;
	
	private String email;

	private String phoneNumber;
	
	private Double quantityCashback;
	
	private Double tierCashback;

	private LocalDate createdDate;

	private String topupStatus;


}
