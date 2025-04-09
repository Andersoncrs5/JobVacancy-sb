package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.AddressUserModel;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.repositories.AddressUserRepository;
import br.com.FindJobs.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AddressUserService {

    private final AddressUserRepository repository;
    private final UserRepository userRepository;
    private final UserService userService;

    public AddressUserService(AddressUserRepository repository, UserRepository userRepository, UserService userService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public AddressUserModel get(Long idUser) {
        try {
            if (idUser == null || idUser == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is required");
            }

            UserModel user = this.userService.get(idUser);

            Optional<AddressUserModel> address = this.repository.findByUser(user);

            if (address.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
            }

            return  address.get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<?> delete(Long idUser) {
        try {
            AddressUserModel address = this.get(idUser);

            this.repository.delete(address);

            return new ResponseEntity<>("Address deleted", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> create(Long idUser, AddressUserModel model){
        try {
            UserModel user = this.userService.get(idUser);

            model.setUser(user);

            this.repository.save(model);

            return new ResponseEntity<>("Address created", HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> update(AddressUserModel model){
        try {
            var address = this.repository.save(model);

            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
