package com.mobydigital.academy.mobyapp.user.controller;

import java.util.List;

import com.mobydigital.academy.mobyapp.user.dto.PictureDto;
import com.mobydigital.academy.mobyapp.user.model.User;
import coms.dto.UserDTO;
import coms.dto.UserReferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mobydigital.academy.mobyapp.user.exception.ReferentNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.TalentPartnerNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.TechnologyNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.UserNotFoundException;
import com.mobydigital.academy.mobyapp.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserReferenceDTO>> getAllUsers() {
        List<UserReferenceDTO> usersList = userService.getAllUsers();
        if(usersList != null)
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/referents")
    public ResponseEntity<List<UserReferenceDTO>> getAllReferents() throws ReferentNotFoundException{
        List<UserReferenceDTO> usersList = userService.getAllReferent();

        if(usersList != null)
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/talent-partner")
    public ResponseEntity<List<UserReferenceDTO>> getAllTalentPartners() throws TalentPartnerNotFoundException{
        
        List<UserReferenceDTO> usersList = userService.getAllTalentPartners();

        if(usersList != null)
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/technology/{technology}")
    public ResponseEntity<List<UserReferenceDTO>> getUsersByTechnology(@PathVariable String technology) throws TechnologyNotFoundException {
        List<UserReferenceDTO> usersList = userService.getByTechnology(technology);
        if(usersList != null)
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String email, @RequestBody UserDTO updatedUser) throws IllegalArgumentException {
        UserDTO updatedUserResult = userService.updateUser(email, updatedUser);
        return new ResponseEntity<>(updatedUserResult, HttpStatus.OK);
    }

    @PatchMapping("/picture/{email}")
    public ResponseEntity<UserDTO> updateUserProfilePic(
            @PathVariable String email,
            @RequestBody UserDTO profilePicture
    ) {

        UserDTO updated = userService.updatePicture(email, profilePicture.getProfilePicture());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

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
