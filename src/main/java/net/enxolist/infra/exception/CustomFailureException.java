package net.enxolist.infra.exception;

import net.enxolist.application.dto.failure.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomFailureException {

    @ExceptionHandler(NotFoundFailure.class)
    public ResponseEntity<?> threatNotFoundFailure(NotFoundFailure failure)
    {
        var failureResponse = new FailureResponse(failure.getMessage(), failure.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failureResponse);
    }

}
