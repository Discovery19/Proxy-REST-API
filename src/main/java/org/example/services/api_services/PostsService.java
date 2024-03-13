package org.example.services.api_services;

import org.example.responses.PostsResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class PostsService implements ApiService{
    private final WebClient webClient;

    public PostsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com/").build();
    }

    @Override
    @Cacheable(value = "postsCache", key = "#id")
    public Mono<?> get(Long id, String path) {
        return webClient.get()
                .uri(path, id)
                .retrieve()
                .bodyToMono(PostsResponse.class);
    }

    @Override
    public Mono<?> post(String path, Object request) {
        return webClient.post()
                .uri(path)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(PostsResponse.class);
    }

    @Override
    public Mono<?> put(Long id, String path, Object request) {
        return webClient.put()
                .uri(path, id)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(PostsResponse.class);
    }

    @Override
    public Mono<?> delete(Long id, String path) {
        return webClient.delete()
                .uri(path, id)
                .retrieve()
                .bodyToMono(PostsResponse.class);
    }
}
