package br.com.FindJobs.api.services;

import br.com.FindJobs.api.components.ValidId;
import br.com.FindJobs.api.components.ValidModel;
import br.com.FindJobs.api.models.EnterpriseModel;
import br.com.FindJobs.api.models.VacancyModel;
import br.com.FindJobs.api.repositories.EnterpriseRepository;
import br.com.FindJobs.api.repositories.VacancyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VacancyService {

    private final VacancyRepository repository;
    private final EnterpriseRepository enterpriseRepository;
    private ValidId validId;
    private ValidModel validModel;

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

            this.validId.ValidNotNull(enterpriseId);

            EnterpriseModel enterprise = this.enterpriseRepository.findById(enterpriseId).orElse(null);

            this.validModel.ValidNotNull(enterprise, "Enterprise not found");

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
            this.validId.ValidNotNull(id);

            VacancyModel vacancy = this.repository.findById(id).orElse(null);

            this.validModel.ValidNotNull(vacancy, "Vacancy not found");

            return vacancy;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            this.validId.ValidNotNull(id);

            VacancyModel vacancy = this.repository.findById(id).orElse(null);

            this.validModel.ValidNotNull(vacancy, "Vacancy not found");

            this.repository.delete(vacancy);

            return new ResponseEntity<>("vacancy deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> changeStatus(Long id) {
        try {
            this.validId.ValidNotNull(id);

            VacancyModel vacancy = this.repository.findById(id).orElse(null);

            this.validModel.ValidNotNull(vacancy, "Vacancy not found");

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
