package com.mobydigital.academy.mobyapp.user.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReferentNotFoundException extends Exception {
    public ReferentNotFoundException() {
        super("There aren't referents available.");
    }

    public ReferentNotFoundException(String message) {
        super(message);
    }
}
