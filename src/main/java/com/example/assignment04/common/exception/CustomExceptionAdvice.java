package com.example.assignment04.common.exception;

import com.example.assignment04.common.response.code.ErrorCode;
import com.example.assignment04.common.template.ApiResTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class CustomExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResTemplate<Void>> handleBusinessException(BusinessException e) {
        log.warn("BusinessException: {}", e.getCustomMessage());

        ApiResTemplate<Void> response = ApiResTemplate.errorResponse(
                e.getErrorCode(),
                e.getCustomMessage()
        );

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResTemplate<Void>> handleException(Exception e) {
        log.error("Internal Server Error", e);

        ApiResTemplate<Void> response = ApiResTemplate.errorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR
        );

        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResTemplate<Void>> handValidationExceptions (MethodArgumentNotValidException e) {
        Map<String,String> erorMap = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            erorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
            ApiResTemplate.errorResponse(
                ErrorCode.VALIDATION_EXCEPTION,
                ErrorCode.VALIDATION_EXCEPTION.getMessage() + converMapToString(erorMap)
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    private String converMapToString(Map<String,String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append(", ");
        }
        sb.deleteCharAt(sb.length() -1);
        sb.deleteCharAt(sb.length() -1);
        return sb.toString();
    }
}
