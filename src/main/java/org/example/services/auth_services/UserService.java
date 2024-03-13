package org.example.services.auth_services;


import org.example.DTO.UserRegistrationDto;
import org.example.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    List<User> getAll();
}