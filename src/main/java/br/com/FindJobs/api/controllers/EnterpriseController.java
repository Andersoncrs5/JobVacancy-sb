package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.EnterpriseDto;
import br.com.FindJobs.api.models.EnterpriseModel;
import br.com.FindJobs.api.services.EnterpriseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService service;

    @GetMapping("/{id}")
    public EnterpriseModel get(@PathVariable Long id) {
        return this.service.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody @Valid EnterpriseDto dto) {
        return this.service.create(id, dto.mappearToCreate());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId, @RequestBody @Valid EnterpriseDto dto) {
        return this.service.update(userId, dto.mappearToUpdate());
    }

}
