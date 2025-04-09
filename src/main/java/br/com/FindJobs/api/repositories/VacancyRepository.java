package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.VacancyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<VacancyModel, Long> {
    List<VacancyModel> findAllByTitleContaining(String title);
    List<VacancyModel> findAllByCategory(String category);
}