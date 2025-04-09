package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByEmail(String email);
}