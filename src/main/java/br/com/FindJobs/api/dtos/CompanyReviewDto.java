package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.CompanyReviewModel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record CompanyReviewDto(
        Long id,
        @Max(10)
        @Min(0)
        Integer rating,
        @Max(500)
        String comment
) {
    public CompanyReviewModel mappearToCreate(){
        CompanyReviewModel review = new CompanyReviewModel();

        review.setComment(comment);
        review.setRating(rating);

        return review;
    }

    public CompanyReviewModel mappearToUpdate(){
        CompanyReviewModel review = new CompanyReviewModel();

        review.setId(id);
        review.setComment(comment);
        review.setRating(rating);

        return review;
    }

}
