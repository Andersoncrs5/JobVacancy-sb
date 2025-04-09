package br.com.FindJobs.api.services;

import br.com.FindJobs.api.dtos.ApplicationDto;
import br.com.FindJobs.api.models.ApplicationModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.VacancyModel;
import br.com.FindJobs.api.repositories.ApplicationRepository;
import br.com.FindJobs.api.repositories.VacancyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository repository;
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final VacancyService vacancyService;

    public ApplicationService(ApplicationRepository repository, VacancyRepository vacancyRepository, UserService userService, VacancyService vacancyService) {
        this.repository = repository;
        this.vacancyRepository = vacancyRepository;
        this.userService = userService;
        this.vacancyService = vacancyService;
    }

    public ResponseEntity<?> apply(ApplicationDto dto){
        try {
            VacancyModel vacancy = this.vacancyService.get(dto.vacancyId());
            UserModel user = this.userService.get(dto.userId());

            var check = this.repository.existsByVacancyAndUser(vacancy, user);

            if (check) {
                return new ResponseEntity<>("User Already apllied!", HttpStatus.NO_CONTENT);
            }

            ApplicationModel application = new ApplicationModel();

            application.setUser(user);
            application.setVacancy(vacancy);

            vacancy.setNumericApplication(vacancy.getNumericApplication() + 1);

            this.repository.save(application);
            this.vacancyRepository.save(vacancy);

            return new ResponseEntity<>("Applied", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> remove(Long id) {
        try {
            if (id == null || id == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is required");
            }

            ApplicationModel model = this.repository.findById(id).orElse(null);

            if (model == null) {
                return new ResponseEntity<>("Application not found", HttpStatus.NOT_FOUND);
            }

            this.repository.delete(model);

            return new ResponseEntity<>("removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> check(Long vacancyId, Long userId) {
        try {
            VacancyModel vacancy = this.vacancyService.get(vacancyId);
            UserModel user = this.userService.get(userId);

            boolean exists = this.repository.existsByVacancyAndUser(vacancy, user);

            return new ResponseEntity<>(exists, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findAllApplicationsOfUser(Long id) {
        try {
            UserModel user = this.userService.get(id);

            List<ApplicationModel> applications = this.repository.findAllByUser(user);

            return new ResponseEntity<>(applications, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findAllApplicationsOfVacancy(Long id) {
        try {
            VacancyModel vacancy = this.vacancyService.get(id);

            List<ApplicationModel> applications = this.repository.findAllByVacancy(vacancy);

            return new ResponseEntity<>(applications, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
