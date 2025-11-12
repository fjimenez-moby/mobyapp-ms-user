package com.mobydigital.academy.mobyapp.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldsDTO {

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("Apellido")
    private String apellido;

    @JsonProperty("Correo Moby")
    private String correoMoby;

    @JsonProperty("Foto de Perfil URL")
    private String fotoPerfilUrl;

    @JsonProperty("Provincia")
    private String provincia;

    @JsonProperty("Localidad")
    private String localidad;

    @JsonProperty("Referente")
    private UserReferenceDTO referente;

    @JsonProperty("Talent Partner")
    private UserReferenceDTO talentPartner;

    @JsonProperty("Fecha de Alta")
    private String fechaAlta;

    @JsonProperty("Proyectos")
    private List<String> proyectos;

    @JsonProperty("Direccion")
    private String direccion;

    @JsonProperty("Observaciones")
    private String observacion;

    @JsonProperty("Tecnología Actual")
    private List<String> tecnologiaActual;

    @JsonProperty("Historial de Tecnologías")
    private List<String> historialTecnologias;

}
