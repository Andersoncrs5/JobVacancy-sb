package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.FavoriteModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteModel, Long> {

    Optional<FavoriteModel> findByVacancyAndUser(VacancyModel vacancy, UserModel user);

    boolean existsByVacancyAndUser(VacancyModel vacancy, UserModel user);

    Page<FavoriteModel> findAllByUser(UserModel user, Pageable pageable);

    Page<FavoriteModel> findAllByVacancy(VacancyModel vacancy, Pageable pageable);

    Long countByVacancy(VacancyModel vacancy);
}
