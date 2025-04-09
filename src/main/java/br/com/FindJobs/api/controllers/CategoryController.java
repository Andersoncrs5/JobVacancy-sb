package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.CategoryDto;
import br.com.FindJobs.api.models.CategoryModel;
import br.com.FindJobs.api.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "Categories")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody @Valid CategoryDto dto) {
        return this.service.create(id, dto.mappearToCreate());
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid CategoryDto dto) {
        return this.service.update(dto.mappearToUpdate());
    }

    @GetMapping("/{id}")
    public CategoryModel get(@PathVariable Long id) {
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

    @GetMapping("/checkName/{name}")
    public ResponseEntity<?> checkName(@PathVariable String name) {
        return this.service.checkName(name);
    }

    @GetMapping("/getAllToAdm")
    public ResponseEntity<?> getAllToAdm() {
        return this.service.getAllToAdm();
    }




}
