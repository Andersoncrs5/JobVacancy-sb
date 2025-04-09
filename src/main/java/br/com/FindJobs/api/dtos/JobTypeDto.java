package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.JobTypeModel;
import br.com.FindJobs.api.models.UserModel;
import jakarta.validation.constraints.NotBlank;

public record JobTypeDto(
        Long id,

        @NotBlank
        String name
) {
    public JobTypeModel mappearToCreate() {
        JobTypeModel jobType = new JobTypeModel();

        jobType.setName(name);

        return jobType;
    }

    public JobTypeModel mappearToUpdate() {
        JobTypeModel jobType = new JobTypeModel();

        jobType.setId(id);
        jobType.setName(name);
        jobType.setUser(new UserModel());

        return jobType;
    }

}
