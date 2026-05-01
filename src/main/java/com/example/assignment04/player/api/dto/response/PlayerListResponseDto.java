package com.example.assignment04.player.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PlayerListResponseDto(
    String teamName,
    List<PlayerInfoResponseDto> players
) {
    public static PlayerListResponseDto from(String teamName,List<PlayerInfoResponseDto> players) {
        return PlayerListResponseDto.builder()
                .teamName(teamName)
                .players(players)
                .build();
    }
}
