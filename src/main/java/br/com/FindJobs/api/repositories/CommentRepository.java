package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.CommentModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    List<CommentModel> findAllByUser(UserModel user);
    List<CommentModel> findAllByVacancy(VacancyModel vacancy);
    List<CommentModel> findAllByParentId(Long parentId);
}