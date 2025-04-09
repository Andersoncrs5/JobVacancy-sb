package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    boolean existsByName(String name);
    List<CategoryModel> findAllByIsActivedTrue();
}

