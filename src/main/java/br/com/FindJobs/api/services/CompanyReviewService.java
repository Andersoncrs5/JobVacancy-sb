package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.CompanyReviewModel;
import br.com.FindJobs.api.models.EnterpriseModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.repositories.CompanyReviewRepository;
import br.com.FindJobs.api.repositories.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyReviewService {

    private final CompanyReviewRepository repository;
    private final EnterpriseRepository enterpriseRepository;
    private final UserService userService;
    private final EnterpriseService enterpriseService;

    public CompanyReviewService(CompanyReviewRepository repository, EnterpriseRepository enterpriseRepository, UserService userService, EnterpriseService enterpriseService) {
        this.repository = repository;
        this.enterpriseRepository = enterpriseRepository;
        this.userService = userService;
        this.enterpriseService = enterpriseService;
    }

    public ResponseEntity<?> create(Long userId, CompanyReviewModel model) {
        try {
            UserModel user = this.userService.get(userId);

            Optional<EnterpriseModel> enterprise = this.enterpriseRepository.findByUser(user);

            if (enterprise.isEmpty()) {
                return new ResponseEntity<>("Enterprise not found", HttpStatus.NOT_FOUND);
            }

            model.setEnterprise(enterprise.get());
            model.setUser(user);

            this.repository.save(model);

            return new ResponseEntity<>("Review created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getReviewOfEnterprise(Long userId) {
        try {
            UserModel user = this.userService.get(userId);

            EnterpriseModel enterprise = this.enterpriseService.get(userId);

            List<CompanyReviewModel> list = this.repository.findAllByEnterprise(enterprise);

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> avgOfReviewOfEnterprise(Long userId) {
        try {
            EnterpriseModel enterprise = this.enterpriseService.get(userId);

            Double value = this.repository.findAverageRatingByEnterpriseId(enterprise.getId());

            return new ResponseEntity<>(value, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
