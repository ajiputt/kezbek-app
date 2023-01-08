package com.telkomsel.kezbek.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class CashbackRequest {

	@NotNull
	@Min(1)
	private Double totalAmount;

	@NotNull
	@Min(1)
	private Integer quantity;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	private String partnerId;

}
