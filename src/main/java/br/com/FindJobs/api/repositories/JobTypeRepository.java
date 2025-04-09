package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.CategoryModel;
import br.com.FindJobs.api.models.JobTypeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobTypeRepository extends JpaRepository<JobTypeModel, Long> {
    List<JobTypeModel> findAllByIsActivedTrue();

    @Override
    Page<JobTypeModel> findAll(Pageable pageable);
}

