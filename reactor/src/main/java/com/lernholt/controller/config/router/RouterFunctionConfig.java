package com.lernholt.controller.config.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.lernholt.domain.user.User;
import com.lernholt.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

//@Configuration
@RequiredArgsConstructor
public class RouterFunctionConfig {

    private final UserRepository userRepository;

    @Bean
    public RouterFunction<?> helloRouterFunction() {
        return route(GET("/hello"), request -> ok().body(just("Hello World!"), String.class)).andRoute(GET("/bye"),
                request -> ok().body(just("See ya!"), String.class));
    }

    @Bean
    public RouterFunction<?> routerFunction() {
        return route(GET("/users"), this::recents).andRoute(POST("/design"), this::postUser);
    }

    public Mono<ServerResponse> recents(ServerRequest request) {
        return ServerResponse.ok().body(userRepository.findAll().take(12), User.class);
    }

    public Mono<ServerResponse> postUser(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        User savedUser = userRepository.saveAll(user).next().block();
        URI getUserEndpoint = URI.create(String.format("http://localhost:8080/users/%s", savedUser.getId()));
        return ServerResponse.created(getUserEndpoint).body(savedUser, User.class);
    }
}
