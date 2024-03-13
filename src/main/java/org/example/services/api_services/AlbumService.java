package org.example.services.api_services;

import org.example.responses.AlbumsResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AlbumService
        implements ApiService {

    private final WebClient webClient;

    public AlbumService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com/").build();
    }

    @Override
    @Cacheable(value = "albumsCache", key = "#id")
    public Mono<?> get(Long id, String path) {
        return webClient.get()
                .uri(path, id)
                .retrieve()
                .bodyToMono(AlbumsResponse.class);
    }

    @Override
    public Mono<?> post(String path, @RequestBody Object request) {
        return webClient.post()
                .uri(path)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(AlbumsResponse.class);
    }

    @Override
    public Mono<?> put(Long id, String path, Object request) {
        return webClient.put()
                .uri(path, id)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(AlbumsResponse.class);
    }

    @Override
    public Mono<?> delete(Long id, String path) {
        return webClient.delete()
                .uri(path, id)
                .retrieve()
                .bodyToMono(AlbumsResponse.class);
    }

}
