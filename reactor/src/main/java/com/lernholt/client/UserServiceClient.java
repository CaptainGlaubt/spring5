package com.lernholt.client;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.lernholt.domain.user.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceClient {

    public Flux<User> findUsers() {
        Flux<User> userFlux = WebClient.create()
                .get()
                .uri("http://localhost:8080/users")
                .retrieve()
                .bodyToFlux(User.class);
        userFlux.timeout(Duration.ofSeconds(1))
                .subscribe(user -> System.out.println(user), e -> System.out.println("timed out!"));
        return userFlux;
    }

    public Mono<User> findUserById(String userId) {
        Mono<User> userMono = WebClient.create("http://localhost:8080")
                .get()
                .uri("/users/{id}", userId)
                .exchangeToMono(cr -> {
                    if (cr.headers().header("X_UNAVAILABLE").contains("true")) {
                        return Mono.empty();
                    }
                    return Mono.just(cr);
                })
                .flatMap(cr -> cr.bodyToMono(User.class));
        userMono.subscribe(user -> System.out.println(user));
        return userMono;
    }

    public Mono<User> findUserByIdExchange(String userId) {
        Mono<User> userMono = WebClient.create("http://localhost:8080")
                .get()
                .uri("/users/{id}", userId)
                .retrieve()
                .bodyToMono(User.class);
        userMono.subscribe(user -> System.out.println(user));
        return userMono;
    }

    public Mono<User> registerUser(Mono<User> userMono) {
        Mono<User> resultUserMono = WebClient.create("http://localhost:8080")
                .post()
                .uri("/users")
                .body(userMono, User.class)
                .retrieve()
                .bodyToMono(User.class);
        resultUserMono.subscribe(user -> System.out.println(user));
        return resultUserMono;
    }

    public Mono<Void> changeUser(User user) {
        Mono<Void> result = WebClient.create("http://localhost:8080")
                .put()
                .uri("/users/{id}", user.getId())
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Void.class);
        result.subscribe();
        return result;
    }

    public Mono<Void> deleteUser(String userId) {
        Mono<Void> result = WebClient.create("http://localhost:8080")
                .delete()
                .uri("/users/{id}", userId)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new IllegalStateException()))
                .bodyToMono(Void.class);
        result.subscribe();
        return result;
    }
}