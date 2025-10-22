package com.mobydigital.academy.mobyapp.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobydigital.academy.mobyapp.user.dto.FieldsDTO;
import lombok.Getter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
public class AirtableUserResponse {
    private String id;
    private FieldsDTO fields;

    public String getId() {
            return id;
    }

    public FieldsDTO getFields() {
        return fields;
    }
}

