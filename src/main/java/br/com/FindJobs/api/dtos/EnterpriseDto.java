package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.EnterpriseModel;
import br.com.FindJobs.api.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record EnterpriseDto(
        Long id,

        @NotBlank(message = "The field name is required")
        @Length(min = 3, max = 150, message = "The name must be between 3 and 150 characters")
        String name,

        @NotBlank(message = "The field description is required")
        @Length(min = 100, max = 2500, message = "The description must be between 100 and 2500 characters")
        String description,

        @URL(message = "Url is invalid")
        @Length(max = 300, message = "The field site cannot excess 300 characters")
        String site
) {
    public EnterpriseModel mappearToCreate() {
        EnterpriseModel enterprise = new EnterpriseModel();

        enterprise.setName(name);
        enterprise.setDescription(description);
        enterprise.setSite(site);

        return enterprise;
    }

    public EnterpriseModel mappearToUpdate() {
        EnterpriseModel enterprise = new EnterpriseModel();

        enterprise.setId(id);
        enterprise.setName(name);
        enterprise.setDescription(description);
        enterprise.setSite(site);

        return enterprise;
    }

}
