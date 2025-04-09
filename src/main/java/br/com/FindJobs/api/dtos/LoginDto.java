package br.com.FindJobs.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginDto(
        @NotBlank(message = "The field email is required")
        @Email(message = "Invalid email format")
        @Length(min = 3, max = 100, message = "The email must be between 10 and 150 characters")
        String email,

        @NotBlank(message = "The field password is required")
        @Length(min = 3, max = 100, message = "The password must be between 8 and 50 characters")
        String password
) {
}
