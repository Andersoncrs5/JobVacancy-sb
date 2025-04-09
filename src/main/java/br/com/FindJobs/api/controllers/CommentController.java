package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.CommentDto;
import br.com.FindJobs.api.models.CommentModel;
import br.com.FindJobs.api.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "Comments")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("/{userId}/{vacancyId}")
    public ResponseEntity<?> create(
            @PathVariable Long userId,
            @PathVariable Long vacancyId,
            @RequestBody @Valid CommentDto dto
    ) {
        return this.service.create(userId, vacancyId, dto.mappearToCreate());
    }

    @GetMapping("/getAllOfUser/{id}")
    public ResponseEntity<?> getAllOfUser(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return this.service.getAllOfUser(id, pageable);
    }

    @GetMapping("/getAllOfVacancy/{vacancyId}")
    public ResponseEntity<?> getAllOfVacancy(
            @PathVariable Long vacancyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return this.service.getAllOfVacancy(vacancyId, pageable);
    }

    @GetMapping("/{id}")
    public CommentModel get(@PathVariable Long id) {
        return this.service.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody @Valid CommentDto dto) {
        return this.service.update(dto.mappearToUpdate());
    }
}
