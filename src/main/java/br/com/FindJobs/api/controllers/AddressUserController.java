package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.AddressUserDto;
import br.com.FindJobs.api.models.AddressUserModel;
import br.com.FindJobs.api.services.AddressUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/AddressUser")
public class AddressUserController {

    @Autowired
    private AddressUserService service;

    @GetMapping("/{id}")
    public AddressUserModel get(@PathVariable Long id) {
        return this.service.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody @Valid AddressUserDto dto) {
        return this.service.create(id, dto.mappearToCreate());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid AddressUserDto dto) {
        return this.service.update(id, dto.mappearToUpdate());
    }

}
