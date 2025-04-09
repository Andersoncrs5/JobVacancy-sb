package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.enums.StatusUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserDto(
        Long id,

        @NotBlank(message = "The field name is required")
        @Length(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
        String name,

        @NotBlank(message = "The field email is required")
        @Email(message = "Invalid email format")
        @Length(min = 3, max = 100, message = "The email must be between 10 and 150 characters")
        String email,

        @NotBlank(message = "The field password is required")
        @Length(min = 3, max = 100, message = "The password must be between 8 and 50 characters")
        String password,

        @Length(max = 500, message = "The refresh token cannot exceed 500 characters")
        String refreshToken,

        StatusUser role
) {
    public UserModel mappearToCreate() {
        UserModel user = new UserModel();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRefreshToken(refreshToken);
        user.setRole(StatusUser.candidate);

        return user;
    }

    public UserModel mappearToUpdate() {
        UserModel user = new UserModel();

        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRefreshToken(refreshToken);
        user.setRole(role);

        return user;
    }
}
