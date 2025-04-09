package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.CommentModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CommentDto(
        Long id,

        @NotBlank(message = "The content field is required")
        @Length(min = 3, max = 500, message = "The content must be between 3 and 500 characters")
        String content,

        Boolean isEdited,

        Long parentId

) {
    public CommentModel mappearToCreate() {
        CommentModel comment = new CommentModel();
        comment.setContent(content);
        comment.setEdited(false);
        comment.setParentId(parentId);
        return comment;
    }

    public CommentModel mappearToUpdate() {
        CommentModel comment = new CommentModel();
        comment.setContent(content);
        comment.setEdited(true);
        comment.setParentId(parentId);
        return comment;
    }
}
