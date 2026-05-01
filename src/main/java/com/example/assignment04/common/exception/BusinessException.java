package com.example.assignment04.common.exception;

//예외 발생시 실제로 던지는 객체(야구공임 아주)
import com.example.assignment04.common.response.code.ErrorCode;
import lombok.Getter;
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String customMessage;

    public BusinessException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }
}
