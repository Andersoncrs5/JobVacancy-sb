package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.ResumeModel;
import br.com.FindJobs.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<ResumeModel, Long> {
    ResumeModel findByUser(UserModel user);

    ResumeModel findByFilePath(String filePath);
}

