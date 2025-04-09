package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.VacancyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface VacancyRepository extends JpaRepository<VacancyModel, Long> {
    Page<VacancyModel> findAllByTitleContaining(String title, Pageable pageable);
    Page<VacancyModel> findAllByCategory(String category, Pageable pageable);
    Page<VacancyModel> findAll(Pageable pageable);
}