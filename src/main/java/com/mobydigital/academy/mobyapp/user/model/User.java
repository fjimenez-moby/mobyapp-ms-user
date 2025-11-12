package com.mobydigital.academy.mobyapp.user.model;

import java.util.ArrayList;
import java.util.List;
import com.mobydigital.academy.mobyapp.user.dto.UserDTO;
import com.mobydigital.academy.mobyapp.user.dto.UserReferenceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private String name;
    private String lastName;
    private String email;
    private Long provinceId;
    private Long localityId;
    private String address;
    private String observation;
    private UserReferenceDTO referent;
    private UserReferenceDTO talentPartner;
    private List<String> projects = new ArrayList<>();
    private List<String> currentTech = new ArrayList<>();

    public UserDTO toDTO() {
        UserDTO userDto = new UserDTO();

        userDto.setName(this.name);
        userDto.setLastName(this.lastName);
        userDto.setEmail(this.email);
        userDto.setProvince(this.provinceId.toString());
        userDto.setLocality(this.localityId.toString());
        userDto.setCurrentTech(this.currentTech);
        userDto.setReferent(this.referent);
        userDto.setTalentPartner(this.talentPartner);
        userDto.setAddress(this.address);
        userDto.setObservation(this.observation);
        return userDto;
    }
}
