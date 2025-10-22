package com.mobydigital.academy.mobyapp.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TechnologyNotFoundException extends Exception {
    public TechnologyNotFoundException() {
        super("There aren't  users with that technology.");
    }

    public TechnologyNotFoundException(String message) {
        super(message);
    }

}
