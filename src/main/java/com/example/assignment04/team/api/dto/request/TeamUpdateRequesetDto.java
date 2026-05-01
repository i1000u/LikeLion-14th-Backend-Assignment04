package com.example.assignment04.team.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TeamUpdateRequesetDto(
        @NotBlank
        String location,
        @NotBlank
        String name
) {}
