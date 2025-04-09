package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.VacancyDto;
import br.com.FindJobs.api.models.VacancyModel;
import br.com.FindJobs.api.services.VacancyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/Vacancy")
public class VacancyController {

    private final VacancyService service;

    public VacancyController(VacancyService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return this.service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public VacancyModel get(@PathVariable Long id) {
        return this.service.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody @Valid VacancyDto dto) {
        return this.service.create(id, dto.mappearToCreate());
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody @Valid VacancyDto dto) {
        return this.service.update(dto.mappearToUpdate());
    }

    @GetMapping("/changeStatus/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        return this.service.changeStatus(id);
    }

    @GetMapping("/getAllByTitle/{title}")
    public ResponseEntity<?> getAllByTitle(
            @PathVariable String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return this.service.getAllByTitle(title, pageable);
    }

    @GetMapping("/getAllByCategory/{category}")
    public ResponseEntity<?> getAllByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return this.service.getAllByCategory(category, pageable);
    }
}