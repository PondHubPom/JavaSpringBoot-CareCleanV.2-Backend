package com.Hoime.CareClean.middleware;

import com.Hoime.CareClean.model.domain.UtilDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<UtilDomain> responseException(ResponseStatusException e) {
        String errorMessage = e.getMessage();
        int errorCode = e.getStatus().value();

        assert errorMessage != null;
        if (errorMessage.startsWith(errorCode + " ")) {
            errorMessage = errorMessage.substring((errorCode + " ").length());
        }

        return ResponseEntity.status(e.getStatus()).body(new UtilDomain(errorCode, errorMessage));
    }
}