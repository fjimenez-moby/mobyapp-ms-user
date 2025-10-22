package com.mobydigital.academy.mobyapp.user.model;

import com.mobydigital.academy.mobyapp.user.dto.FieldsDTO;
import coms.dto.UserDTO;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class AirtableMapper {

    public UserDTO fromAirtable(AirtableUserResponse airtableUser) {
        UserDTO dto = new UserDTO();

        if (airtableUser == null || airtableUser.getFields() == null) {
            return dto;
        }

        FieldsDTO f = airtableUser.getFields();
        if (f.getNombre() != null && !f.getNombre().isBlank()) {
            dto.setName(f.getNombre());
        }
        if (f.getApellido() != null && !f.getApellido().isBlank()) {
            dto.setLastName(f.getApellido());
        }
        if (f.getCorreoMoby() != null && !f.getCorreoMoby().isBlank()) {
            dto.setEmail(f.getCorreoMoby());
        }
        if (f.getFotoPerfilUrl() != null && !f.getFotoPerfilUrl().isBlank()) {
            dto.setProfilePicture(f.getFotoPerfilUrl());
        }
        if (f.getProvincia() != null && !f.getProvincia().isBlank()) {
            dto.setProvince(f.getProvincia());
        }
        if (f.getLocalidad() != null && !f.getLocalidad().isBlank()) {
            dto.setLocality(f.getLocalidad());
        }
        if (f.getCurrentTechString() != null && !f.getCurrentTechString().isBlank()) {
            dto.setCurrentTech(f.getCurrentTechString());
        }
        dto.setReferent(f.getReferente());
        dto.setTalentPartner(f.getTalentPartner());
        dto.setProjects(f.getProyectos());
        if (f.getFechaAlta() != null && !f.getFechaAlta().isBlank()) {
            LocalDate date = LocalDate.parse(f.getFechaAlta());
            dto.setDateEntered(date);
        }
        return dto;
    }
}
