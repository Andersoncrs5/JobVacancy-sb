package br.com.FindJobs.api.dtos;

import br.com.FindJobs.api.models.CompanyReviewModel;

public record CompanyReviewDto(
        Long id,
        Integer rating,
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
