package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.ExperienceDto;
import br.com.FindJobs.api.models.ExperienceModel;
import br.com.FindJobs.api.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExperienceController {

    @Autowired
    private ExperienceService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ExperienceModel get(@PathVariable() Long id) {
        return this.service.get(id);
    }

    @GetMapping("/getAll/{userId}")
    @ResponseStatus(HttpStatus.FOUND)
    public  ResponseEntity<Page<ExperienceModel>> getAll(
            @PathVariable() Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return this.service.getAll(userId, pageable);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> remove(@PathVariable() Long userId) {
        return this.service.remove(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> save(
            @PathVariable() Long userId,
            @RequestBody() ExperienceDto dto) {
        return this.service.save(userId, dto.mappearToCreate());
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.FOUND)
    public ExperienceModel update(@RequestBody() ExperienceDto dto) {
        return this.service.update(dto.mappearToUpdate());
    }

}
