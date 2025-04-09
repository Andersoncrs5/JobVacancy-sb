package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.EnterpriseModel;
import br.com.FindJobs.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<EnterpriseModel, Long> {
    Boolean existsByUser(UserModel user);
    Optional<EnterpriseModel> findByUser(UserModel user);
}
