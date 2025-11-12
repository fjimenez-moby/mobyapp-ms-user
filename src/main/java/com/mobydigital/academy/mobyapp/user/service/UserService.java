package com.mobydigital.academy.mobyapp.user.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.mobydigital.academy.mobyapp.user.dto.UserDTO;
import com.mobydigital.academy.mobyapp.user.model.AirtableMapper;
import com.mobydigital.academy.mobyapp.user.model.AirtableUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.mobydigital.academy.mobyapp.user.exception.ReferentNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.TalentPartnerNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.TechnologyNotFoundException;
import com.mobydigital.academy.mobyapp.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import com.mobydigital.academy.mobyapp.user.dto.UserReferenceDTO;

@Service
public class UserService implements IUserService{

    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final AirtableMapper airtableMapper;

    @Autowired
    RestTemplate restTemplate;

    @Value("${URL_BASE}")
    private String urlBase;

    public UserService(AirtableMapper airtableMapper) {
        this.airtableMapper = airtableMapper;
    }

    @Override
    public List<UserReferenceDTO> getAllUsers() throws NullPointerException {
        String urlGet = urlBase + "getalluser";
        UserReferenceDTO[] users = restTemplate.getForObject(urlGet, UserReferenceDTO[].class);
        if(users != null && users.length > 0){
            return Arrays.asList(users);
        }
        throw new NullPointerException("Usuarios no encontrados");
    }

    @Override
    public UserDTO findUserByEmail(String email) throws UserNotFoundException {
        String urlGet = urlBase + "user/{email}";
        UserDTO userDTO = restTemplate.getForObject(
                urlGet,
                UserDTO.class,
                email
        );
        if(userDTO != null){
            return userDTO;
        }
        throw new UserNotFoundException("Usuario no encontrado");
    }

    @Override
    public UserDTO updateUser(String email, UserDTO updatedUser) throws IllegalArgumentException {
        String urlUpdate = urlBase + "user?email={email}";
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(updatedUser);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("email", email);
        try {
            ResponseEntity<AirtableUserResponse> response = restTemplate.exchange(
                    urlUpdate,
                    HttpMethod.PUT,
                    requestEntity,
                    AirtableUserResponse.class,
                    uriVariables
            );
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                AirtableUserResponse airtableResponse = response.getBody();
                return airtableMapper.fromAirtable(airtableResponse);
            }
            throw new RuntimeException("El microservicio devolvió un código 2xx, pero el cuerpo de la respuesta está vacío.");
        } catch (HttpClientErrorException e) {
            logger.severe("Error 4xx del microservicio de Airtable." + e.getStatusCode() + e.getResponseBodyAsString());
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new IllegalArgumentException("Error de validación de datos en el microservicio: " + e.getResponseBodyAsString());
            }
            throw new RuntimeException("Error en la comunicación con el microservicio de Airtable: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.severe("Fallo general de comunicación con Airtable: " + e.getMessage());
            throw new RuntimeException("Fallo en la comunicación con el microservicio de Airtable: " + e.getMessage(), e);
        }
    }
    @Override
    public UserDTO updatePicture(String email, String pictureUrl) throws IllegalArgumentException {
        String urlUpdate = urlBase + "user/newPicture?email={email}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("email", email);

        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of("pictureUrl", pictureUrl);
        HttpEntity<Map<String,String>> req = new HttpEntity<>(body, h);

        try {
            ResponseEntity<AirtableUserResponse> response = restTemplate.exchange(
                    urlUpdate,
                    HttpMethod.PATCH,
                    req,
                    AirtableUserResponse.class,
                    uriVariables
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                AirtableUserResponse airtableResponse = response.getBody();
                return airtableMapper.fromAirtable(airtableResponse);
            }
            throw new RuntimeException("El microservicio devolvió un código 2xx, pero el cuerpo está vacío.");

        } catch (HttpClientErrorException e) {
            logger.severe("Error 4xx del microservicio: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new IllegalArgumentException("Error de validación de datos: " + e.getResponseBodyAsString());
            }
            throw new RuntimeException("Error en la comunicación con el microservicio: " + e.getMessage(), e);

        } catch (Exception e) {
            logger.severe("Fallo general de comunicación: " + e.getMessage());
            throw new RuntimeException("Fallo en la comunicación con el microservicio: " + e.getMessage(), e);
        }
    }

	@Override
	public List<UserReferenceDTO> getAllReferent() throws ReferentNotFoundException {
        String urlReferents = urlBase + "getallreferent";
        UserReferenceDTO[] users = restTemplate.getForObject(urlReferents, UserReferenceDTO[].class);
        if(users != null && users.length > 0){
            return Arrays.asList(users);
        }
        throw new ReferentNotFoundException("Unimplemented method 'getAllReferent'");
	}

	@Override
	public List<UserReferenceDTO> getAllTalentPartners() throws TalentPartnerNotFoundException {
        String urlTalentPartners = urlBase + "getallpartner";
        UserReferenceDTO[] users = restTemplate.getForObject(urlTalentPartners,UserReferenceDTO[].class);
        if(users != null && users.length > 0){
            return Arrays.asList(users);
        }
		throw new TalentPartnerNotFoundException("Unimplemented method 'getAllTalentPartners'");
	}

    //Verificar si va a andar
	@Override
	public List<UserReferenceDTO> getByTechnology(String technology) throws TechnologyNotFoundException {
		String urlTechnology = urlBase + "tecno?tec={technology}";
        UserReferenceDTO[] users = restTemplate.getForObject(
                urlTechnology,
                UserReferenceDTO[].class,
                technology
        );
        if(users != null && users.length > 0){
            return Arrays.asList(users);
        }
		throw new TechnologyNotFoundException("Usuarios con la tecnología solicitada " + technology + " no existen");
	}

    @Override
    public String getFullNameByEmail(String email) throws UserNotFoundException {
        String urlGet = urlBase + "user/fullName?email={email}";
        String fullName = restTemplate.getForObject(
                urlGet,
                String.class,
                email
        );
        if(fullName != null){
            return fullName;
        }
        throw new UserNotFoundException("Usuario no encontrado");
    }
}