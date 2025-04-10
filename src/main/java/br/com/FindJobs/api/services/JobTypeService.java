package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.JobTypeModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.repositories.JobTypeRepository;
import br.com.FindJobs.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JobTypeService {

    private final JobTypeRepository repository;
    private final UserRepository userRepository;
    private final UserService userService;

    public JobTypeService(JobTypeRepository repository, UserRepository userRepository, UserService userService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public ResponseEntity<?> create(Long userId, JobTypeModel jobType) {
        try {
            if (jobType.getId() != null) {
                return new ResponseEntity<>("Id should be null", HttpStatus.BAD_REQUEST);
            }

            UserModel user = this.userService.get(userId);

            jobType.setUser(user);

            this.repository.save(jobType);
            return new ResponseEntity<>("Job type created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(JobTypeModel jobType) {
        try {
            var check = this.get(jobType.getId());

            jobType.setUser(check.getUser());

            return new ResponseEntity<>(this.repository.save(jobType), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public JobTypeModel get(Long id) {
        try {
            if (id == null || id == 0 ) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id should be different of null");
            }

            JobTypeModel check = this.repository.findById(id).orElse(null);

            if (check == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Type not found");
            }

            return check;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            JobTypeModel check = this.get(id);

            this.repository.delete(check);
            return new ResponseEntity<>("job type deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(this.repository.findAllByIsActivedTrue(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> getAllForAdm(Pageable pageable) {
        try {
            return new ResponseEntity<>(this.repository.findAll(pageable), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> changeStatusActive(Long id) {
        try {
            JobTypeModel check = this.get(id);

            check.setActived(!check.getActived());

            this.repository.save(check);

            return new ResponseEntity<>("Status altered!!!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
