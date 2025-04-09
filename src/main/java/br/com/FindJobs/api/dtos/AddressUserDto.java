package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.AddressUserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressUserDto(
        Long id,

        @NotNull(message = "The field numeric is required")
        Integer numeric,

        @NotBlank(message = "The field street is required")
        @Size(max = 200, message = "The street cannot exceed 200 characters")
        String street,

        @NotBlank(message = "The field city is required")
        @Size(max = 200, message = "The city cannot exceed 200 characters")
        String city,

        @NotBlank(message = "The field state is required")
        @Size(max = 200, message = "The state cannot exceed 200 characters")
        String state,

        @NotBlank(message = "The field country is required")
        @Size(max = 200, message = "The country cannot exceed 200 characters")
        String country,

        @NotBlank(message = "The field complementary is required")
        @Size(max = 1000, message = "The complementary cannot exceed 1000 characters")
        String complementary
) {
    public AddressUserModel mappearToCreate() {
        AddressUserModel address = new AddressUserModel();

        address.setNumeric(numeric);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setComplementary(complementary);

        return address;
    }

    public AddressUserModel mappearToUpdate() {
        AddressUserModel address = new AddressUserModel();

        address.setId(id);
        address.setNumeric(numeric);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setComplementary(complementary);

        return address;
    }
}
