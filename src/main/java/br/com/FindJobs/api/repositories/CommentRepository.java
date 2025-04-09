package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.CommentModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    Page<CommentModel> findAllByUser(UserModel user, Pageable pageable);
    Page<CommentModel> findAllByVacancy(VacancyModel vacancy, Pageable pageable);
    Page<CommentModel> findAllByParentId(Long parentId, Pageable pageable);
}