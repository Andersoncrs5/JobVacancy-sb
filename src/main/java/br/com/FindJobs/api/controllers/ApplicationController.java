package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.ApplicationDto;
import br.com.FindJobs.api.services.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Applications")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @PostMapping()
    public ResponseEntity<?> apply(@RequestBody() @Valid ApplicationDto dto) {
        return this.service.apply(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable() Long id ) {
        return this.service.remove(id);
    }

    @GetMapping("/{userId}/{vacancyId}")
    public ResponseEntity<?> check(@PathVariable() Long userId, @PathVariable() Long vacancyId ) {
        return this.service.check(vacancyId, userId);
    }

    @GetMapping("/findAllApplicationsOfUser/{id}")
    public ResponseEntity<?> findAllApplicationsOfUser(@PathVariable() Long id) {
        return this.service.findAllApplicationsOfUser(id);
    }

    @GetMapping("/findAllApplicationsOfVacancy/{id}")
    public ResponseEntity<?> findAllApplicationsOfVacancy(@PathVariable() Long id) {
        return this.service.findAllApplicationsOfVacancy(id);
    }

}
