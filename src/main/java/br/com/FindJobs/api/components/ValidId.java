package br.com.FindJobs.api.components;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidId {
    public void ValidNotNull(Long id){
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is required");
        }
    }
}
