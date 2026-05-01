package com.example.assignment04.team.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TeamSaveRequestDto(
            @NotBlank
            String location,

            @NotBlank
            String name
    ) {}
