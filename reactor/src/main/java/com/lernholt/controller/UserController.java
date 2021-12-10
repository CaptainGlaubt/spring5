package com.lernholt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lernholt.domain.user.User;
import com.lernholt.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public Flux<User> findUsers() {
        return userRepository.findAll().take(12);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<User> registerUser(@RequestBody Mono<User> user) {
        return userRepository.saveAll(user).next();
    }

    @GetMapping("/{id}")
    public Mono<User> findUserById(@PathVariable("id") String id) {
        return userRepository.findById(id);
    }
}
