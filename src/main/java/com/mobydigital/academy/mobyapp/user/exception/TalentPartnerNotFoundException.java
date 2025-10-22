package com.mobydigital.academy.mobyapp.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TalentPartnerNotFoundException extends Exception {
    public TalentPartnerNotFoundException() {
        super("There aren't talent partners available.");
    }

    public TalentPartnerNotFoundException(String message) {
        super(message);
    }

}
