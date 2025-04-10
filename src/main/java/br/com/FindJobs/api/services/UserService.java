package br.com.FindJobs.api.services;

import br.com.FindJobs.api.components.ValidId;
import br.com.FindJobs.api.components.ValidModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.models.enums.StatusUser;
import br.com.FindJobs.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository repository;
    private ValidId validId;
    private ValidModel validModel;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> create(UserModel user){
        try {
            var check = this.repository.existsByEmail(user.getEmail());

            if (check) {
                return new ResponseEntity<>("Email is inavailable", HttpStatus.BAD_REQUEST);
            }

            this.repository.save(user);
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(UserModel user){
        try {
            boolean check = this.repository.existsById(user.getId());

            if (!check) {
                return new ResponseEntity<>("User not exists", HttpStatus.NOT_FOUND);
            }

            UserModel userUpdate = this.repository.save(user);

            return new ResponseEntity<>(userUpdate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public UserModel get(Long id) {
        try {
            this.validId.ValidNotNull(id);

            UserModel user = this.repository.findById(id).orElse(null);

            this.validModel.ValidNotNull(user, "User not found");

            return user;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long id){
        try {
            UserModel user = this.get(id);

            this.repository.delete(user);

            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> login(String email, String password){
        try {
            var optional = this.repository.findByEmail(email);

            if (optional.isEmpty()) {
                return new ResponseEntity<>("",HttpStatus.UNAUTHORIZED);
            }

            UserModel user = optional.get();

            if (!user.getPassword().equals(password)) {
                return new ResponseEntity<>("",HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> becomeARecruiter(Long id) {
        try {
            UserModel user = this.get(id);

            user.setRole(StatusUser.recruiter);

            this.repository.save(user);
            return new ResponseEntity<>("Now you are a recruter!!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}