package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.CommentModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import br.com.FindJobs.api.repositories.CommentRepository;
import br.com.FindJobs.api.repositories.UserRepository;
import br.com.FindJobs.api.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final VacancyService vacancyService;

    public CommentService(CommentRepository repository, UserRepository userRepository, VacancyRepository vacancyRepository, UserService userService, VacancyService vacancyService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.vacancyRepository = vacancyRepository;
        this.userService = userService;
        this.vacancyService = vacancyService;
    }

    public ResponseEntity<?> create(Long userId, Long vacancyId, CommentModel comment) {
        try {
            UserModel user = this.userService.get(userId);

            VacancyModel vacancy = this.vacancyService.get(vacancyId);

            comment.setUser(user);
            comment.setVacancy(vacancy);

            this.repository.save(comment);
            return new ResponseEntity<>("Comment created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllOfUser(Long userId){
        try {
            UserModel user = this.userService.get(userId);

            return ResponseEntity.status(HttpStatus.FOUND).body(this.repository.findAllByUser(user));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllOfVacancy(Long vacancyId){
        try {
            VacancyModel vacancy = this.vacancyService.get(vacancyId);

            return ResponseEntity.status(HttpStatus.FOUND).body(this.repository.findAllByVacancy(vacancy));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public CommentModel get(Long id){
        try {
            if (id == null || id == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is required");
            }

            CommentModel comment = this.repository.findById(id).orElse(null);

            if (comment == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment not found");
            }

            return comment;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id){
        try {
            CommentModel comment = this.get(id);

            this.repository.delete(comment);

            return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(CommentModel comment) {
        try {
            if (comment.getId() == null) {
                return new ResponseEntity<>("Id of Comment is required", HttpStatus.BAD_REQUEST);
            }

            CommentModel check = this.get(comment.getId());

            this.repository.save(comment);
            return new ResponseEntity<>(this.repository.save(comment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
