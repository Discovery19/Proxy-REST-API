package org.example.services.api_services;

import reactor.core.publisher.Mono;
public interface ApiService {

    Mono<?> get(Long id, String path);

    Mono<?> post(String path, Object request);

    Mono<?> put(Long id, String path, Object request);

    Mono<?> delete(Long id, String path);
}
