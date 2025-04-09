package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.CategoryDto;
import br.com.FindJobs.api.dtos.JobTypeDto;
import br.com.FindJobs.api.models.JobTypeModel;
import br.com.FindJobs.api.services.JobTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/JobType")
public class JobTypeController {

    private final JobTypeService service;

    public JobTypeController(JobTypeService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable Long userId, @RequestBody @Valid JobTypeDto dto) {
        return this.service.create(userId, dto.mappearToCreate());
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid JobTypeDto dto) {
        return this.service.update(dto.mappearToUpdate());
    }

    @GetMapping("/{id}")
    public JobTypeModel get(@PathVariable Long id) {
        return this.service.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/changeStatusActive/{id}")
    public ResponseEntity<?> changeStatusActive(@PathVariable Long id) {
        return this.service.changeStatusActive(id);
    }

    @GetMapping("/getAllToAdm")
    public ResponseEntity<?> getAllToAdm() {
        return this.service.getAllForAdm();
    }

}
