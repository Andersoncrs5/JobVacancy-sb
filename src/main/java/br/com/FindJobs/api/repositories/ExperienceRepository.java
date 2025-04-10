package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.ExperienceModel;
import br.com.FindJobs.api.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<ExperienceModel, Long> {
    Optional<ExperienceModel> findByUser(UserModel user);

    Page<ExperienceModel> findAllByUser(UserModel user, Pageable pageable);
}
