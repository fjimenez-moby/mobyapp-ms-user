package com.mobydigital.academy.mobyapp.user.service;

import java.util.List;
import com.mobydigital.academy.mobyapp.user.dto.UserDTO;
import com.mobydigital.academy.mobyapp.user.dto.UserReferenceDTO;
import com.mobydigital.academy.mobyapp.user.exception.UserNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.ReferentNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.TalentPartnerNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.TechnologyNotFoundException;

public interface IUserService {

    List<UserReferenceDTO> getAllUsers();
    List<UserReferenceDTO> getAllReferent()  throws ReferentNotFoundException;
    List<UserReferenceDTO> getAllTalentPartners()  throws TalentPartnerNotFoundException;
    List<UserReferenceDTO> getByTechnology(String technology)  throws TechnologyNotFoundException;
    UserDTO findUserByEmail(String id) throws UserNotFoundException;
    UserDTO updateUser(String id, UserDTO updatedUser) throws IllegalArgumentException;
    UserDTO updatePicture(String id, String pictureUrl) throws IllegalArgumentException;
    String getFullNameByEmail(String email) throws UserNotFoundException;
}
