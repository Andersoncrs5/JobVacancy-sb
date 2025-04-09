package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.ApplicationModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel, Long> {
    Optional<ApplicationModel> findByVacancyAndUser(VacancyModel vacancy, UserModel user);
    boolean existsByVacancyAndUser(VacancyModel vacancy, UserModel user);
    List<ApplicationModel> findAllByUser(UserModel user);
    List<ApplicationModel> findAllByVacancy(VacancyModel vacancy);
}
