package se.lernholt.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.lernholt.model.Answer;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> getAnswer(Authentication auth) {
        Answer answer = Answer.builder().answer(String.format("Hello %s!", auth.getName())).build();
        return ResponseEntity.ok(answer);
    }
}
