package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.FavoriteModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import br.com.FindJobs.api.repositories.FavoriteRepository;
import br.com.FindJobs.api.repositories.UserRepository;
import br.com.FindJobs.api.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository repository;
    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final VacancyService vacancyService;

    public FavoriteService(FavoriteRepository repository, UserRepository userRepository, VacancyRepository vacancyRepository, UserService userService, VacancyService vacancyService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.vacancyRepository = vacancyRepository;
        this.userService = userService;
        this.vacancyService = vacancyService;
    }

    public ResponseEntity<?> create(Long userId, Long vacancyId) {
        try {
            UserModel user = this.userService.get(userId);
            VacancyModel vacancy = this.vacancyService.get(vacancyId);

            var check = this.repository.existsByVacancyAndUser(vacancy, user);

            if (check) {
                return new ResponseEntity<>("Vacancy already save!", HttpStatus.NO_CONTENT);
            }

            FavoriteModel favorite = new FavoriteModel();
            favorite.setVacancy(vacancy);
            favorite.setUser(user);

            this.repository.save(favorite);

            return new ResponseEntity<>("Added!!",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> remove(Long id) {
        try {
            if (id == null || id == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is required");
            }

            FavoriteModel model = this.repository.findById(id).orElse(null);

            if (model == null) {
                return new ResponseEntity<>("Favorite not found", HttpStatus.NOT_FOUND);
            }

            this.repository.delete(model);

            return new ResponseEntity<>("removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> check(Long vacancyId, Long userId) {
        try {
            UserModel user = this.userService.get(userId);
            VacancyModel vacancy = this.vacancyService.get(vacancyId);

            boolean exists = this.repository.existsByVacancyAndUser(vacancy, user);

            return new ResponseEntity<>(exists, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findAllFavoriteVacancyOfUser(Long id, Pageable pageable) {
        try {
            UserModel user = this.userService.get(id);

            Page<FavoriteModel> favorites = this.repository.findAllByUser(user, pageable);

            return new ResponseEntity<>(favorites, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> amountFavoriteInVacancy(Long id) {
        try {
            VacancyModel vacancy = this.vacancyService.get(id);

            Long amount = this.repository.countByVacancy(vacancy);

            return new ResponseEntity<>(amount, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
