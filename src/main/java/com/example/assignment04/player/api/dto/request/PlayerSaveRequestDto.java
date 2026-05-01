package com.example.assignment04.player.api.dto.request;


import jakarta.validation.constraints.*;

public record PlayerSaveRequestDto (
        @NotBlank
        String name,

        @NotBlank
        String position,

        @PositiveOrZero //등번호 0번도 존재
        @Max(100)
        int uniformNumber,

        @NotNull
        @Positive
        Long teamId
){}
