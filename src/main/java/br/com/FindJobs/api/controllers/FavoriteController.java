package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.dtos.ApplicationDto;
import br.com.FindJobs.api.dtos.FavoriteDto;
import br.com.FindJobs.api.services.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable() Long id ) {
        return this.service.remove(id);
    }

    @GetMapping("/{userId}/{vacancyId}")
    public ResponseEntity<?> check(@PathVariable() Long userId, @PathVariable() Long vacancyId ) {
        return this.service.check(vacancyId, userId);
    }

    @GetMapping("/findAllFavoriteVacancyOfUser/{id}")
    public ResponseEntity<?> findAllFavoriteVacancyOfUser(@PathVariable() Long id ) {
        return this.service.findAllFavoriteVacancyOfUser(id);
    }

    @GetMapping("/amountFavoriteInVacancy/{id}")
    public ResponseEntity<?> amountFavoriteInVacancy(@PathVariable() Long id ) {
        return this.service.amountFavoriteInVacancy(id);
    }



}
