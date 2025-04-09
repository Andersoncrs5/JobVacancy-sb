package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository repository;

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

    public UserModel get(Long id) {
        try {
            if (id == null || id <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id of user is required");
            }

            UserModel user = this.repository.findById(id).orElse(null);

            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }

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

    public ResponseEntity<?> delete2(Long id){
        try {
            UserModel user = this.get(id);

            this.repository.delete(user);

            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}