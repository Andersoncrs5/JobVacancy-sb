package br.com.FindJobs.api.services;

import br.com.FindJobs.api.models.ResumeModel;
import br.com.FindJobs.api.models.UserModel;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import br.com.FindJobs.api.repositories.ResumeRepository;
import br.com.FindJobs.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResumeService {

    private final String pathStorage = "uploads/resumes/";
    private final ResumeRepository repository;
    private final UserService userService;

    public ResumeService(ResumeRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public ResponseEntity<?> create(Long userId, MultipartFile file) {
        try {

            UserModel user = this.userService.get(userId);

            if (file.isEmpty()) {
                return new ResponseEntity<>("File is required", HttpStatus.BAD_REQUEST);
            }

            File directory = new File(this.pathStorage);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String uniqueFileName = UUID.randomUUID() + fileExtension;
            String filePath = this.pathStorage + uniqueFileName;

            file.transferTo(new File(filePath));

            ResumeModel resume = repository.findByUser(user);
            if (resume != null) {

                File oldFile = new File(resume.getFilePath());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
                resume.setFilePath(filePath);
                repository.save(resume);
                return new ResponseEntity<>("File updated successfully", HttpStatus.OK);
            }

            resume = new ResumeModel();
            resume.setFilePath(filePath);
            resume.setUser(user);
            repository.save(resume);

            return new ResponseEntity<>("File saved", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Resource getFile(String filename) {
        try {
            File file = new File(pathStorage + filename);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filename);
            }
            return new FileSystemResource(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public ResponseEntity<String> deleteFile(String filename) {
        try {
            File file = new File(pathStorage + filename);
            if (file.exists() && file.delete()) {
                ResumeModel resume = repository.findByFilePath(pathStorage + filename);
                if (resume != null) {
                    repository.delete(resume);
                }
                return ResponseEntity.ok("File deleted successfully");
            }
            return new ResponseEntity<>("File not found or could not be deleted", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}