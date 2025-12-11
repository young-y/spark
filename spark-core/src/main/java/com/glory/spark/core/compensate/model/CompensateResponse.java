/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.glory.spark.core.compensate.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.glory.data.jpa.domain.type.ResultStatus;
import jakarta.persistence.Convert;

/**
 * @author : YY
 * @date : 2025/12/2
 * @descprition :
 *
 */

public class CompensateResponse {
	@Convert(converter = ResultStatus.ResultStatusConverter.class)
	private ResultStatus status = ResultStatus.Success;

	private String message;

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
	public static CompensateResponse success(){
		return new CompensateResponse();
	}

	@JsonIgnore
	public static CompensateResponse fail(String message){
		CompensateResponse response = new CompensateResponse();
		response.setStatus(ResultStatus.Fail);
		response.setMessage(message);
		return  response;
	}
}
