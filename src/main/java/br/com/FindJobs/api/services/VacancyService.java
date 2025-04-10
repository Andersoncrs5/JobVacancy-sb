package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.EnterpriseModel;
import br.com.FindJobs.api.models.VacancyModel;
import br.com.FindJobs.api.repositories.EnterpriseRepository;
import br.com.FindJobs.api.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VacancyService {

    private final VacancyRepository repository;
    private final EnterpriseRepository enterpriseRepository;

    public VacancyService(VacancyRepository repository, EnterpriseRepository enterpriseRepository) {
        this.repository = repository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Transactional
    public ResponseEntity<?> getAll(Pageable pageable) {
        try {
            Page<VacancyModel> vacancies = this.repository.findAll(pageable);
            return new ResponseEntity<>(vacancies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> create(Long enterpriseId, VacancyModel model) {
        try {
            if (enterpriseId == null || enterpriseId == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of enterprise is required");
            }

            EnterpriseModel enterprise = this.enterpriseRepository.findById(enterpriseId).orElse(null);

            if (enterprise == null) {
                return new ResponseEntity<>("Enterprise not found!", HttpStatus.NOT_FOUND);
            }

            model.setEnterprise(enterprise);

            this.repository.save(model);
            return new ResponseEntity<>("Vacancy created!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(VacancyModel model) {
        try {
            boolean check = this.repository.existsById(model.getId());

            if (!check) {
                return new ResponseEntity<>("Vacancy not found!", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(this.repository.save(model), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public VacancyModel get(Long id) {
        try {
            if (id == null || id <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id of enterprise is required");
            }

            VacancyModel vacancy = this.repository.findById(id).orElse(null);

            if (vacancy == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found");
            }

            return vacancy;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            if (id == null || id == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of enteprise is required");
            }

            VacancyModel vacancy = this.repository.findById(id).orElse(null);

            if (vacancy == null) {
                return new ResponseEntity<>("Vacancy not found", HttpStatus.NOT_FOUND);
            }

            this.repository.delete(vacancy);

            return new ResponseEntity<>("vacancy deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> changeStatus(Long id) {
        try {
            if (id == null || id == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of enteprise is required");
            }

            VacancyModel vacancy = this.repository.findById(id).orElse(null);

            if (vacancy == null) {
                return new ResponseEntity<>("Vacancy not found", HttpStatus.NOT_FOUND);
            }

            vacancy.setStatus(!vacancy.getStatus());

            this.repository.save(vacancy);

            return new ResponseEntity<>("Altered", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> getAllByTitle(String title, Pageable pageable) {
        try {
            Page<VacancyModel> vacancies = this.repository.findAllByTitleContaining(title, pageable);
            return new ResponseEntity<>(vacancies, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> getAllByCategory(String category, Pageable pageable) {
        try {
            Page<VacancyModel> vacancies = this.repository.findAllByCategory(category, pageable);
            return new ResponseEntity<>(vacancies, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
