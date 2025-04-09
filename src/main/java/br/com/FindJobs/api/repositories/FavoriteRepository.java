package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.FavoriteModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteModel, Long> {

    Optional<FavoriteModel> findByVacancyAndUser(VacancyModel vacancy, UserModel user);

    boolean existsByVacancyAndUser(VacancyModel vacancy, UserModel user);

    List<FavoriteModel> findAllByUser(UserModel user);

    List<FavoriteModel> findAllByVacancy(VacancyModel vacancy);

    Long countByVacancy(VacancyModel vacancy);
}
