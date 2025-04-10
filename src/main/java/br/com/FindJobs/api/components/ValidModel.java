package br.com.FindJobs.api.components;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidModel {
    public void ValidNotNull(Object entity, String message) {
        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
    }

}
