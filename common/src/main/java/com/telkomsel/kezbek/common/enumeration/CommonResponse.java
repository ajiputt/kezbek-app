package com.telkomsel.kezbek.common.enumeration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public enum CommonResponse {

	SUCCESS("00", "Success", "Sukses"),
	UNDEFINED_ERROR("99", "System unavaible, please try again later",
			"Sistem tidak tersedia, silahkan coba beberapa saat lagi");

	private final String code;

	private final String descEn;

	private final String descId;

	private CommonResponse(String code, String descEn, String descId) {
		this.code = code;
		this.descEn = descEn;
		this.descId = descId;
	}

	public String getCode() {
		return code;
	}

	public String getDescEn() {
		return descEn;
	}

	public String getDescId() {
		return descId;
	}

}
