package com.wallet.domain.exception;

import com.wallet.domain.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
public class InvalidAmountException extends Exception {
	private ErrorType errorType;
	private String message;

	public InvalidAmountException(String message) {
		super(message);
		this.errorType = ErrorType.INVALID_AMOUNT;
		this.message = message;
	}
	
	
}
