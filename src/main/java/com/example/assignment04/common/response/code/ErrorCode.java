package com.example.assignment04.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    PLAYER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 선수를 찾을 수 없습니다."),
    TEAM_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 팀을 찾을 수 없습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서러 에러 발생"),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
