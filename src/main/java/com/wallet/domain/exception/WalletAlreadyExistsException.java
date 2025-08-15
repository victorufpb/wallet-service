package com.wallet.domain.exception;

import com.wallet.domain.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
public class WalletAlreadyExistsException extends Exception {
	private ErrorType errorType;
	private String message;

	public WalletAlreadyExistsException(String message) {
		super(message);
		this.errorType = ErrorType.ALREADY_EXISTS;
		this.message = message;
	}
	
	
}
