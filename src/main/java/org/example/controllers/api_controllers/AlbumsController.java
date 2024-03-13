package org.example.controllers.api_controllers;

import org.example.services.api_services.AlbumService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/albums")
public class AlbumsController implements ApiController {
    private static final String PATH = "albums/{id}";
    private final AlbumService apiService;

    public AlbumsController(AlbumService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Mono<?> get(Long id) {
        return apiService.get(id, PATH);
    }

    @Override
    public Mono<?> post(Object request) {
        return apiService.post("albums", request);
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
