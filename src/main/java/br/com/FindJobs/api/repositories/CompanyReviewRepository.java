package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.CompanyReviewModel;
import br.com.FindJobs.api.models.EnterpriseModel;
import br.com.FindJobs.api.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyReviewRepository extends JpaRepository<CompanyReviewModel, Long> {
    Page<CompanyReviewModel> findAllByUser(UserModel user, Pageable pageable);
    Page<CompanyReviewModel> findAllByEnterprise(EnterpriseModel model, Pageable pageable);

    @Query("SELECT AVG(c.rating) FROM CompanyReviewModel c WHERE c.enterprise.id = :enterpriseId")
    Double findAverageRatingByEnterpriseId(@Param("enterpriseId") Long enterpriseId);
}
