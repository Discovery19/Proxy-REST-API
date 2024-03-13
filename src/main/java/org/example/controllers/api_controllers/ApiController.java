package org.example.controllers.api_controllers;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public interface ApiController {
    //get all mapping TODO
    @GetMapping("/{id}")
    Mono<?> get(@PathVariable Long id);

    @PostMapping()
    Mono<?> post(@RequestBody Object request);

    @PutMapping("/{id}")
    Mono<?> put(@PathVariable Long id, @RequestBody Object request);

    @DeleteMapping("/{id}")
    Mono<?> delete(@PathVariable Long id);
}
