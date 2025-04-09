package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.LoginDto;
import br.com.FindJobs.api.dtos.UserDto;
import br.com.FindJobs.api.models.UserModel;
import br.com.FindJobs.api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "Users")
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid UserDto user) {
        return this.service.create(user.mappearToCreate());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.get(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody @Valid UserDto user) {
        return this.service.update(user.mappearToUpdate());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto dto) {
        return this.service.login(dto.email(), dto.password());
    }

    @GetMapping("/becomeARecruiter/{id}")
    public ResponseEntity<?> becomeARecruiter(@PathVariable Long id) {
        return this.service.becomeARecruiter(id);
    }


}
