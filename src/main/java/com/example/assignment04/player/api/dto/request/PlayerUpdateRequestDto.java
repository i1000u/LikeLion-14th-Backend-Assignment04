package com.example.assignment04.player.api.dto.request;

import jakarta.validation.constraints.*;

public record PlayerUpdateRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String position,
        @PositiveOrZero
        @Max(100)
        int uniformNumber,
        @NotNull
        @Positive
        Long teamId
) {}
