package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.ApplicationDto;
import br.com.FindJobs.api.dtos.FavoriteDto;
import br.com.FindJobs.api.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "Favorites")
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody() @Valid FavoriteDto dto) {
        return this.service.create(dto.userId(), dto.vacancyId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable() Long id ) {
        return this.service.remove(id);
    }

    @GetMapping("/check/{userId}/{vacancyId}")
    public ResponseEntity<?> check(@PathVariable() Long userId, @PathVariable() Long vacancyId ) {
        return this.service.check(vacancyId, userId);
    }

    @GetMapping("/findAllFavoriteVacancyOfUser/{id}")
    public ResponseEntity<?> findAllFavoriteVacancyOfUser(
            @PathVariable() Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return this.service.findAllFavoriteVacancyOfUser(id, pageable);
    }

    @GetMapping("/amountFavoriteInVacancy/{id}")
    public ResponseEntity<?> amountFavoriteInVacancy(@PathVariable() Long id ) {
        return this.service.amountFavoriteInVacancy(id);
    }
}
