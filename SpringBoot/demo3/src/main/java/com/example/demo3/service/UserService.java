package com.example.demo3.service;

import com.example.demo3.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("https://67e0467e7635238f9aad1340.mockapi.io/api/v1")
    private String baseUrl;

    private final String resourceUrl = "/users";

    @CircuitBreaker(name = "CIRCUIT_CONTROLLER", fallbackMethod = "fallbackUsers")
    public List<UserDto> getUsers() {
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(baseUrl + resourceUrl,
                                                                        HttpMethod.GET,
                                                            null,
                                                                        new ParameterizedTypeReference<List<UserDto>>() {});
        return response.getBody();
    }

    @CircuitBreaker(name = "CIRCUIT_CONTROLLER", fallbackMethod = "fallbackUsers")
    public UserDto getUser(Integer id) {
        return restTemplate.getForEntity(baseUrl + resourceUrl + "/" + id, UserDto.class).getBody();
    }

    @CircuitBreaker(name = "CIRCUIT_CONTROLLER", fallbackMethod = "fallbackUsers")
    public UserDto saveUser(UserDto user) {
        return restTemplate.postForObject(baseUrl + resourceUrl, user, UserDto.class);
    }

    @CircuitBreaker(name = "CIRCUIT_CONTROLLER", fallbackMethod = "fallbackUsers")
    public UserDto updateUser(Integer id, UserDto user) {
        HttpEntity<UserDto> request = new HttpEntity<>(user);
        ResponseEntity<UserDto> response = restTemplate.exchange(baseUrl + resourceUrl + "/" + id, HttpMethod.PUT, request, UserDto.class);
        return response.getBody();
    }

    @CircuitBreaker(name = "CIRCUIT_CONTROLLER", fallbackMethod = "fallbackUsers")
    public void deleteUser(Integer id) {
        restTemplate.delete(baseUrl + resourceUrl + "/" + id);
    }

    //Fallbacks
    public String fallbackUsers() {
        return  "Se está experimentando dificultades tecnicas, por favor intente más tarde";
    }

}
