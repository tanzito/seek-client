package com.seek.client.error;


import com.seek.client.context.client.domain.exception.DomainException;
import com.seek.client.context.client.domain.exception.NotFoundException;
import com.seek.client.context.security.domain.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorInfo> handleException(DomainException ex, HttpServletRequest request) {
        ErrorInfo errorInfo = ErrorInfo.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .typeError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(errorInfo);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> handleException(NotFoundException ex, HttpServletRequest request) {
        ErrorInfo errorInfo = ErrorInfo.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .typeError(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(exception = {AuthException.class, BadCredentialsException.class})
    public ResponseEntity<ErrorInfo> handleException(RuntimeException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorInfo errorInfo = ErrorInfo.builder()
                .message("Username or password is incorrect")
                .status(HttpStatus.UNAUTHORIZED.value())
                .typeError(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(errorInfo, HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleException(Exception ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorInfo errorInfo = ErrorInfo.builder()
                .message("Internal error.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .typeError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.internalServerError().body(errorInfo);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorInfo> handleException(AuthorizationDeniedException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorInfo errorInfo = ErrorInfo.builder()
                .message("Forbidden.")
                .status(HttpStatus.FORBIDDEN.value())
                .typeError(HttpStatus.FORBIDDEN.getReasonPhrase())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(errorInfo, HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder("Errores de validación: ");
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            sb.append("[").append(error.getField()).append(": ").append(error.getDefaultMessage()).append("] ");
        });

        ErrorInfo errorInfo = ErrorInfo.builder()
                .message(sb.toString().trim())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .typeError(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.unprocessableEntity().body(errorInfo);
    }


}
