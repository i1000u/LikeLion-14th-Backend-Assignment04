package com.example.assignment04.team.api.dto.response;

import com.example.assignment04.team.domain.Team;
import lombok.Builder;

@Builder
public record TeamInfoResponseDto (
        String location,
        String name
){
public static TeamInfoResponseDto from(Team team) {
return TeamInfoResponseDto.builder()
        .location(team.getLocation())
        .name(team.getName())
        .build();
}

}
