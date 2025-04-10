package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.CategoryModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.repositories.CategoryRepository;
import br.com.FindJobs.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final UserService userService;

    public CategoryService(CategoryRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public ResponseEntity<?> create(Long userId, CategoryModel category) {
        try {
            UserModel user = this.userService.get(userId);

            if (!user.getAdm()) {
                return new ResponseEntity<>("You have not authorized", HttpStatus.UNAUTHORIZED);
            }

            category.setUser(user);
            this.repository.save(category);
            return new ResponseEntity<>("Category created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(CategoryModel category) {
        try {
            CategoryModel check = this.get(category.getId());

            this.repository.save(category);
            return new ResponseEntity<>("Category updated", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public CategoryModel get(Long id) {
        try {
            if (id == null || id <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is required");
            }

            CategoryModel category = this.repository.findById(id).orElse(null);

            if (category == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
            }

            return category;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            CategoryModel category = this.get(id);

            this.repository.delete(category);
            return new ResponseEntity<>("Category deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> changeStatusActive(Long id) {
        try {
            CategoryModel category = this.get(id);

            category.setActived(!category.getActived());

            this.repository.save(category);
            return new ResponseEntity<>("Status active altered!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> checkName(String name) {
        try {
            if (name == null) {
                return new ResponseEntity<>("name of category is required", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(this.repository.existsByName(name), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> getAll(Pageable pageable) {
        try {
            return new ResponseEntity<>(this.repository.findAllByIsActivedTrue(pageable), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<?> getAllToAdm() {
        try {
            return new ResponseEntity<>(this.repository.findAll(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
