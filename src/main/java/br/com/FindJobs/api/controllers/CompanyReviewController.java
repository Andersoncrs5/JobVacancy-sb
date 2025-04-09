package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.CompanyReviewDto;
import br.com.FindJobs.api.services.CompanyReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CompanyReview")
public class CompanyReviewController {

    @Autowired
    private CompanyReviewService service;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getReviewOfEnterprise(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return this.service.getReviewOfEnterprise(userId, pageable);
    }

    @GetMapping("/avgOfReviewOfEnterprise/{userId}")
    public ResponseEntity<?> avgOfReviewOfEnterprise(@PathVariable Long userId) {
        return this.service.avgOfReviewOfEnterprise(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable Long userId, @RequestBody CompanyReviewDto dto) {
        return this.service.create(userId, dto.mappearToCreate());
    }
}
