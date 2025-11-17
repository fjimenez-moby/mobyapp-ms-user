package com.mobydigital.academy.mobyapp.user.controller;

import java.util.List;

import com.mobydigital.academy.mobyapp.user.dto.UserDTO;
import com.mobydigital.academy.mobyapp.user.dto.UserReferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mobydigital.academy.mobyapp.user.exception.ReferentNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.TalentPartnerNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.TechnologyNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.UserNotFoundException;
import com.mobydigital.academy.mobyapp.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "User",description = "Manejo de los usuarios dentro de la aplicacion")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    @Operation(
        summary = "Obtener todos los usuarios",
        description = "Devuelve una lista con todos los usuarios registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
        @ApiResponse(responseCode = "503", description = "El servicio no está disponible")
    })
    @GetMapping("/all")
    public ResponseEntity<List<UserReferenceDTO>> getAllUsers() {
        List<UserReferenceDTO> usersList = userService.getAllUsers();
        if(usersList != null)
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }


    @Operation(
        summary = "Obtener todos los referentes",
        description = "Devuelve una lista de todos los referentes del usario registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de referentes obtenida correctamente"),
        @ApiResponse(responseCode = "503", description = "El servicio no esta disponible")
    })
    @GetMapping("/referents")
    public ResponseEntity<List<UserReferenceDTO>> getAllReferents() throws ReferentNotFoundException{
        List<UserReferenceDTO> usersList = userService.getAllReferent();

        if(usersList != null)
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Operation(
        summary = "Obtener todos los talent-partner",
        description = "Devuelve una lista de talent-partner del usario registrado en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de talent-partner obtenida correctamente"),
        @ApiResponse(responseCode = "503", description = "El servicio no esta disponible")
    })
    @GetMapping("/talent-partner")
    public ResponseEntity<List<UserReferenceDTO>> getAllTalentPartners() throws TalentPartnerNotFoundException{
        
        List<UserReferenceDTO> usersList = userService.getAllTalentPartners();

        if(usersList != null)
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Operation(
        summary = "Obtener todos la tecnologia actual",
        description = "Devuelve una lista de tecnologia actual del usario en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tecnologia obtenida correctamente"),
        @ApiResponse(responseCode = "503", description = "El servicio no esta disponible")
    })
    @GetMapping("/technology/{technology}")
    public ResponseEntity<List<UserReferenceDTO>> getUsersByTechnology(@PathVariable String technology) throws TechnologyNotFoundException {
        List<UserReferenceDTO> usersList = userService.getByTechnology(technology);
        if(usersList != null)
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Operation(
        summary = "Obtener el usuario por email",
        description = "Devuelve una usuario a traves del emial en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue obtenido correctamente"),
        @ApiResponse(responseCode = "503", description = "El servicio no esta disponible")
    })
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @Operation(
        summary = "Actualizar el usuario a traves del email",
        description = "Actualiza un usuario a traves del emial en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue actualizado correctamente"),
        @ApiResponse(responseCode = "503", description = "El servicio no esta disponible")
    })
    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String email, @RequestBody UserDTO updatedUser) throws IllegalArgumentException {
        UserDTO updatedUserResult = userService.updateUser(email, updatedUser);
        return new ResponseEntity<>(updatedUserResult, HttpStatus.OK);
    }

    @Operation(
        summary = "Actualizar el usuario a traves del email",
        description = "Actualiza un usuario a traves del emial en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue actualizado correctamente"),
        @ApiResponse(responseCode = "503", description = "El servicio no esta disponible")
    })
    @PatchMapping("/picture/{email}")
    public ResponseEntity<UserDTO> updateUserProfilePic(
            @PathVariable String email,
            @RequestBody UserDTO profilePicture
    ) {

        UserDTO updated = userService.updatePicture(email, profilePicture.getProfilePicture());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @Operation(
        summary = "Obtener el nombre completo del usuario a traves del email",
        description = "Obtiene el nombre completo de un usuario a traves del emial en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El nombre del usuario fue obtenido correctamente"),
        @ApiResponse(responseCode = "503", description = "El servicio no esta disponible")
    })
    @GetMapping("/fullName/{email}")
    public ResponseEntity<String> getUserFullName(@PathVariable String email) throws UserNotFoundException{
        String fullName = userService.getFullNameByEmail(email);
        if (fullName == null) {
            throw new UserNotFoundException("User not found with email: " + email);
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fullName.trim(), HttpStatus.OK);
    }
}
