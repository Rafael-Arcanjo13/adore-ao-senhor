package com.adoreaosenhor.adore_ao_senhor.infra.handler;

import com.adoreaosenhor.adore_ao_senhor.infra.exceptions.EmailAlreadyRegisteredException;
import com.adoreaosenhor.adore_ao_senhor.infra.exceptions.NonExistentEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    private ResponseEntity<RestErrorMessage> emailRegisteredException(EmailAlreadyRegisteredException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(NonExistentEmailException.class)
    private ResponseEntity<RestErrorMessage> nonExistentEmailException(NonExistentEmailException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

}
