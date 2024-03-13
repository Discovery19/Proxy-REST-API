package org.example.controllers.api_controllers;

import org.example.services.api_services.PostsService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/posts")
public class PostsController implements ApiController {
    private static final String PATH = "posts/{id}";

    private final PostsService apiService;

    public PostsController(PostsService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Mono<?> get(Long id) {
        return apiService.get(id, PATH);
    }

    @Override
    public Mono<?> post(Object request) {
        return apiService.post("posts", request);
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
