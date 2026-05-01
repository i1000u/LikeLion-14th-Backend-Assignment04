package com.example.assignment04.player.api.dto.response;

import com.example.assignment04.player.domain.Player;
import lombok.Builder;


@Builder
public record PlayerDetailResponseDto(
        Long playerId,
        String name,
        int uniformNumber,
        String position,
        String teamName
) {
    public static PlayerDetailResponseDto from(Player player) {
        return PlayerDetailResponseDto.builder()
                .playerId(player.getPlayerId())
                .name(player.getName())
                .uniformNumber(player.getUniformNumber())
                .position(player.getPosition())
                .teamName(player.getTeam().getName())
                .build();
    }
}
