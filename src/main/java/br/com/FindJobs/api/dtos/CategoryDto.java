package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.CategoryModel;
import br.com.FindJobs.api.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDto(
        Long id,

        @NotBlank(message = "The field name of category is required")
        @Size(max = 200, message = "The name of category must be at most 200 characters long")
        String name,

        Boolean isActived
) {
    public CategoryModel mappearToCreate() {
        CategoryModel category = new CategoryModel();

        category.setName(name);
        category.setActived(isActived != null ? isActived : true);

        return category;
    }

    public CategoryModel mappearToUpdate() {
        CategoryModel category = new CategoryModel();

        category.setId(id);
        category.setName(name);
        category.setActived(isActived);
        return category;
    }
}

