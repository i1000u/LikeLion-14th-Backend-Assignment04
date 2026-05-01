package com.example.assignment04.team.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record TeamListResponseDto (
List<TeamInfoResponseDto> teams
)
{
 public static TeamListResponseDto from(List<TeamInfoResponseDto> teams) {
     return TeamListResponseDto.builder()
             .teams(teams)
             .build();
 }
}
