package com.mobydigital.academy.mobyapp.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserReferenceDTO {

    private String name;
    private String lastName;
    private String email;
}