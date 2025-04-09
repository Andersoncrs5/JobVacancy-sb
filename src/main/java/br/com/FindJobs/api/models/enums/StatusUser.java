package br.com.FindJobs.api.models.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum StatusUser {
    @Schema(description = "For candidate")
    candidate,

    @Schema(description = "For recruiter")
    recruiter
}
