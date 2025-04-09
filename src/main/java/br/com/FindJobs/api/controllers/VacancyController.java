package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.AddressUserDto;
import br.com.FindJobs.api.dtos.VacancyDto;
import br.com.FindJobs.api.models.VacancyModel;
import br.com.FindJobs.api.services.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Vacancy")
public class VacancyController {

    private final VacancyService service;

    public VacancyController(VacancyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public VacancyModel get(@PathVariable Long id) {
        return this.service.get(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return this.service.getAll();
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

    @GetMapping("/getAllByCategory/{title}")
    public ResponseEntity<?> getAllByTitle(@PathVariable String title) {
        return this.service.getAllByTitle(title);
    }

    @GetMapping("/getAllByCategory/{category}")
    public ResponseEntity<?> getAllByCategory(@PathVariable String category) {
        return this.service.getAllByCategory(category);
    }


}


