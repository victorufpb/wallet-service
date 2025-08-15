package com.wallet.domain.exception;

import com.wallet.domain.enums.ErrorType;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionResponse {
	private ErrorType type;
	private String message;
}
