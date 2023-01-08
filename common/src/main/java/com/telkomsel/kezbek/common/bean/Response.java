package com.telkomsel.kezbek.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.telkomsel.kezbek.common.enumeration.CommonResponse;

import lombok.Builder;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
@Builder
public class Response {

	@JsonIgnore
	private CommonResponse commonResponse;

	private String responseCode;

	private String responseDescEn;

	private String responseDescId;

	private Object data;

	public static class ResponseBuilder {

		public ResponseBuilder commonResponse(CommonResponse commonResponse) {
			this.responseCode = commonResponse.getCode();
			this.responseDescEn = commonResponse.getDescEn();
			this.responseDescId = commonResponse.getDescId();
			return this;
		}
	}

}
