package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.VacancyModel;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record VacancyDto(
        Long id,

        @NotBlank(message = "The field title is required")
        @Size(max = 300, message = "The title cannot exceed 300 characters")
        String title,

        @NotBlank(message = "The field description is required")
        String description,

        @NotBlank(message = "The field typeContraction is required")
        @Size(max = 300, message = "The typeContraction cannot exceed 300 characters")
        String typeContraction,

        @NotBlank(message = "The field locate is required")
        @Size(max = 300, message = "The locate cannot exceed 300 characters")
        String locate,

        @NotNull(message = "The field salary is required")
        @Positive(message = "The salary must be positive")
        Double salary,

        @NotNull(message = "The field total is required")
        @Positive(message = "The total must be positive")
        Integer total,

        @NotNull(message = "The field status is required")
        Boolean status,

        @NotNull(message = "The field numericApplication is required")
        @PositiveOrZero(message = "The numericApplication must be zero or positive")
        Integer numericApplication,

        @Size(max = 300, message = "The category cannot exceed 300 characters")
        String category

) {
    public VacancyModel mappearToCreate() {
        VacancyModel vacancy = new VacancyModel();

        vacancy.setTitle(title);
        vacancy.setDescription(description);
        vacancy.setTypeContraction(typeContraction);
        vacancy.setLocate(locate);
        vacancy.setSalary(salary);
        vacancy.setTotal(total);
        vacancy.setStatus(status);
        vacancy.setNumericApplication(numericApplication);
        vacancy.setCategory(category);

        return vacancy;
    }

    public VacancyModel mappearToUpdate() {
        VacancyModel vacancy = new VacancyModel();

        vacancy.setId(id);
        vacancy.setTitle(title);
        vacancy.setDescription(description);
        vacancy.setTypeContraction(typeContraction);
        vacancy.setLocate(locate);
        vacancy.setSalary(salary);
        vacancy.setTotal(total);
        vacancy.setStatus(status);
        vacancy.setNumericApplication(numericApplication);
        vacancy.setCategory(category);

        return vacancy;
    }
}
