package br.com.FindJobs.api.dtos;

import jakarta.validation.constraints.NotNull;

public record ApplicationDto(

        @NotNull
        Long userId,

        @NotNull
        Long vacancyId
) {
}
