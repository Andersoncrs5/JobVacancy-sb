package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.ExperienceModel;
import br.com.FindJobs.api.models.UserModel;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record ExperienceDto(
        Long id,

        @NotBlank(message = "Enterprise name is required.")
        @Size(max = 150, message = "Enterprise name must be at most 150 characters.")
        String enterprise,

        @NotBlank(message = "Position is required.")
        @Size(max = 150, message = "Position must be at most 150 characters.")
        String position,

        @NotBlank(message = "Description is required.")
        String description,

        @NotNull(message = "Start date is required.")
        LocalDateTime startDate,

        @NotNull(message = "End date is required.")
        LocalDateTime endDate,

        @NotNull(message = "Currently working status is required.")
        Boolean currentlyWorking

) {
    public ExperienceModel mappearToCreate(){
        ExperienceModel model = new ExperienceModel();

        model.setId(null);
        model.setEnterprise(enterprise);
        model.setPosition(position);
        model.setDescription(description);
        model.setStartDate(startDate);
        model.setEndDate(endDate);
        model.setCurrentlyWorking(currentlyWorking);
        model.setUser(new UserModel());

        return model;
    }

    public ExperienceModel mappearToUpdate(){
        ExperienceModel model = new ExperienceModel();

        model.setId(id);
        model.setEnterprise(enterprise);
        model.setPosition(position);
        model.setDescription(description);
        model.setStartDate(startDate);
        model.setEndDate(endDate);
        model.setCurrentlyWorking(currentlyWorking);
        model.setUser(new UserModel());

        return model;
    }

}
