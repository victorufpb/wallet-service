package com.wallet.domain.exception;

import com.wallet.domain.enums.ErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.logging.Logger;

import static com.wallet.domain.enums.ErrorType.UNSUCCESSFUL_OPERATION;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionHandler {
    Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

	@org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse> alreadyExistsUserHandler(UserAlreadyExistsException exception) {
        logger.warning("Handling UserAlreadyExistsException, message=" +  exception.getMessage());
		return ResponseEntity
				.status(BAD_REQUEST)
				.body(new ExceptionResponse(exception.getErrorType(), exception.getMessage()));
	}

    @org.springframework.web.bind.annotation.ExceptionHandler(WalletAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> alreadyExistsWalletHandler(WalletAlreadyExistsException exception) {
        logger.warning("Handling WalletAlreadyExistsException, message=" +  exception.getMessage());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ExceptionResponse(exception.getErrorType(), exception.getMessage()));
    }

	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(NotFoundException exception) {
        logger.warning("Handling NotFoundException, message=" +  exception.getMessage());
		return ResponseEntity
				.status(NOT_FOUND)
				.body(new ExceptionResponse(exception.getErrorType(), exception.getMessage()));
	}

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ExceptionResponse> invalidAmountHandler(InvalidAmountException exception) {
        logger.warning("Handling InvalidAmountException, message=" +  exception.getMessage());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ExceptionResponse(exception.getErrorType(), exception.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<ExceptionResponse> alreadyExistsUserHandler(UnexpectedRollbackException exception) {
        logger.warning("Handling UnexpectedRollbackException, message=" +  exception.getMessage());
        return ResponseEntity
                .status(FORBIDDEN)
                .body(new ExceptionResponse(UNSUCCESSFUL_OPERATION, "there was an error processing your request"));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> internalExceptionHandler(Exception exception) {
        logger.warning("Handling generic exception, message=" +  exception.getMessage());
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(ErrorType.INTERNAL_ERROR, "Internal Server Error"));
    }
}
