package br.com.FindJobs.api.controllers;

import br.com.FindJobs.api.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024;
    private final ResumeService service;

    public ResumeController(ResumeService service) {
        this.service = service;
    }

    @PostMapping("{userId}")
    public ResponseEntity<?> create(@PathVariable() Long userId, @RequestParam("file") MultipartFile file){
        if (file.getSize() > MAX_FILE_SIZE) {
            return new ResponseEntity<>("File size exceeds the maximum limit", HttpStatus.BAD_REQUEST);
        }
        return this.service.create(userId, file);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = service.getFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(file);
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        return service.deleteFile(filename);
    }


}
