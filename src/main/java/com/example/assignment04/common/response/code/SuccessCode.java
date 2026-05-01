package com.example.assignment04.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    PLAYER_SAVE_SUCCESS(HttpStatus.CREATED, "선수 정보 저장 성공"),
    PLAYER_FIND_SUCCESS(HttpStatus.OK, "선수 조회 성공"),
    PLAYER_UPDATE_SUCCESS(HttpStatus.OK, "선수 정보 수정 성공"),
    PLAYER_DELETE_SUCCESS(HttpStatus.OK, "해당 선수 1군 말소"),

    TEAM_SAVE_SUCCESS(HttpStatus.CREATED, "팀 정보 저장 성공"),
    TEAM_FIND_SUCCESS(HttpStatus.OK, "팀 정보 조회 성공"),
    TEAM_FIND_ALL_SUCCESS(HttpStatus.OK,"모든 팀 정보 조회 성공"),
    TEAM_FIND_PLAYERLIST(HttpStatus.OK,"팀 내 소속 선수 조회 성공"),
    TEAM_UPDATE_SUCCESS(HttpStatus.OK, "팀 정보 수정 성공"),
    TEAM_DELETE_SUCCESS(HttpStatus.OK, "해당 팀 해체");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
