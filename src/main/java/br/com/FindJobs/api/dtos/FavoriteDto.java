package br.com.FindJobs.api.dtos;

import jakarta.validation.constraints.NotNull;

public record FavoriteDto(
        @NotNull
        Long userId,

        @NotNull
        Long vacancyId
) {
}
