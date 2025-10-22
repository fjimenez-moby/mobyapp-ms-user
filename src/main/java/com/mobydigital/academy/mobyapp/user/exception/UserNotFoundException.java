package com.mobydigital.academy.mobyapp.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("The user doesn't exist.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
