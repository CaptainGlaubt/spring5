package se.lernholt.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import se.lernholt.repository.jpa.UserRepository;
import se.lernholt.tacos.RegistrationForm;
import se.lernholt.tacos.User;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm registrationForm) {
        User user = registrationForm.toUser(passwordEncoder);
        userRepository.save(user);
        return "redirect:/login";
    }
}
