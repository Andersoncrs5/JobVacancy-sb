package br.com.FindJobs.api.repositories;

import br.com.FindJobs.api.models.AddressUserModel;
import br.com.FindJobs.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressUserRepository extends JpaRepository<AddressUserModel, Long> {
    Optional<AddressUserModel> findByUser(UserModel user);
}
