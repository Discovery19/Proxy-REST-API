package org.example.controllers.api_controllers;

import org.example.services.api_services.UsersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UsersController implements ApiController{
    private static final String PATH = "users/{id}";

    private final UsersService apiService;
    public UsersController(UsersService apiService) {
        this.apiService = apiService;
    }
    @Override
    public Mono<?> get(Long id) {
        return apiService.get(id, PATH);
    }

    @Override
    public Mono<?> post(Object request) {
        return apiService.post("users", request);
    }

    @Override
    public Mono<?> put(Long id, Object request) {
        return apiService.put(id, PATH, request);
    }

    @Override
    public Mono<?> delete(Long id) {
        return apiService.delete(id, PATH);
    }
}
