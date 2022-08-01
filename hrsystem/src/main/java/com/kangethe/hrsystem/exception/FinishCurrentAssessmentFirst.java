package com.kangethe.hrsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FinishCurrentAssessmentFirst extends ResponseStatusException {
    public FinishCurrentAssessmentFirst(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
