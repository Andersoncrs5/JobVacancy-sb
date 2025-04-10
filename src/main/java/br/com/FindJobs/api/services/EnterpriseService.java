package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.EnterpriseModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.repositories.EnterpriseRepository;
import br.com.FindJobs.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EnterpriseService {

    private final EnterpriseRepository repository;
    private final UserRepository userRepository;
    private final UserService userService;

    public EnterpriseService(EnterpriseRepository repository, UserRepository userRepository, UserService userService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public ResponseEntity<?> create(Long idUser, EnterpriseModel enterprise) {
        try {
            UserModel user = this.userService.get(idUser);
            boolean exists = this.repository.existsByUser(user);

            if (exists) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You already have an enterprise");
            }

            enterprise.setUser(user);

            this.repository.save(enterprise);
            return ResponseEntity.status(HttpStatus.CREATED).body("Enterprise created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> update(Long userId, EnterpriseModel enterprise) {
        try {
            if (enterprise.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enterprise ID is required for update");
            }

            UserModel user = this.userService.get(userId);

            enterprise.setUser(user);

            var save = this.repository.save(enterprise);

            return ResponseEntity.status(HttpStatus.OK).body(save);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    public EnterpriseModel get(Long idUser) {
        try {
            UserModel user = this.userService.get(idUser);

            Optional<EnterpriseModel> enterprise = this.repository.findByUser(user);

            if (enterprise.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enterprise not found");
            }

            return enterprise.get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            EnterpriseModel enterprise = this.get(id);

            this.repository.delete(enterprise);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
