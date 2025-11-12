package com.mobydigital.academy.mobyapp.user.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO {

    private String name;
    private String lastName;
    private String email;
    private String profilePicture;
    private String province;
    private String locality;
    private UserReferenceDTO referent;
    private UserReferenceDTO talentPartner;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateEntered;
    private String signatureUrl;
    private String address;
    private String observation;
    private List<String> projects; //Desde Airtable mando name nada mas
    private List<String> currentTech; //Desde Airtable mando name nada mas
}