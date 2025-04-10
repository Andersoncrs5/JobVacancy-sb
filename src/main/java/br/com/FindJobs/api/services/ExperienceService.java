package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.ExperienceModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import br.com.FindJobs.api.repositories.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ExperienceService {

    private final ExperienceRepository repository;
    private final UserService userService;

    public ExperienceService(ExperienceRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public ResponseEntity<String> save(Long userId, ExperienceModel experience) {
        try {
            if (experience.getId() != null) {
                return new ResponseEntity<>("Id should be egual the 0 or null", HttpStatus.BAD_REQUEST);
            }

            UserModel user = this.userService.get(userId);

            experience.setUser(user);

            this.repository.save(experience);

            return new ResponseEntity<>("Experience created", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Transactional
    public ExperienceModel get(Long id) {
        try {
            ExperienceModel model = this.repository.findById(id).orElse(null);

            if (model == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found");
            }

            return model;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<Page<ExperienceModel>> getAll(Long userId, Pageable pageable) {
        try {
            UserModel user = this.userService.get(userId);

            Page<ExperienceModel> experiences = this.repository.findAllByUser(user, pageable);

            return new ResponseEntity<>(experiences, HttpStatus.FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<String> remove(Long userId) {
        try {
            UserModel user = this.userService.get(userId);
            ExperienceModel model = this.get(userId);

            this.repository.delete(model);

            return new ResponseEntity<>("Experience deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ExperienceModel update(ExperienceModel experience) {
        try {
            if (experience.getId() == 0 || experience.getId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id should not be egual the 0 or null");
            }

            var check = this.get(experience.getId());

            experience.setUser(check.getUser());

            this.repository.save(experience);

            return this.get(experience.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
