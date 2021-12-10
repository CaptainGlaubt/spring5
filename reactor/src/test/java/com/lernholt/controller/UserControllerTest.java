package com.lernholt.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.lernholt.domain.user.AddressInfo;
import com.lernholt.domain.user.ContactInfo;
import com.lernholt.domain.user.PaymentInfo;
import com.lernholt.domain.user.RegistrationInfo;
import com.lernholt.domain.user.User;
import com.lernholt.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserControllerTest {

    @Test
    public void shouldReturnRecentTacos() {
        List<User> users = generateTestUsers();
        Flux<User> userFlux = Flux.fromIterable(users);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        doReturn(userFlux).when(userRepository).findAll();
        WebTestClient testClient = WebTestClient.bindToController(new UserController(userRepository)).build();
        testClient.get()
                .uri("/design/recent")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$")
                .isArray()
                .jsonPath("$")
                .isNotEmpty()
                .jsonPath("$[0].id")
                .isEqualTo(users.get(0).getId().toString())
                .jsonPath("$[0].name")
                .isEqualTo("Taco 1")
                .jsonPath("$[1].id")
                .isEqualTo(users.get(1).getId().toString())
                .jsonPath("$[1].name")
                .isEqualTo("Taco 2")
                .jsonPath("$[11].id")
                .isEqualTo(users.get(11).getId().toString())
                .jsonPath("$[11].name")
                .isEqualTo("Taco 12")
                .jsonPath("$[12]")
                .doesNotExist()
                .jsonPath("$[12]")
                .doesNotExist();
    }

    @Test
    public void shouldSaveATaco() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = generateTestUser(1);
        Mono<User> unsavedUserMono = Mono.just(user);
        Mono<User> savedUserMono = Mono.just(user);
        doReturn(savedUserMono).when(userRepository).save(any());
        WebTestClient testClient = WebTestClient.bindToController(new UserController(userRepository)).build();
        testClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(unsavedUserMono, User.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(User.class)
                .isEqualTo(user);
    }

    private List<User> generateTestUsers() {
        List<User> testUsers = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            User testUser = generateTestUser(i);
            testUsers.add(testUser);
        }
        return testUsers;
    }

    private User generateTestUser(int counter) {
        return User.builder()
                .id(generateUUID())
                .firstName(String.format("user-%s", counter))
                .middleName("middlename")
                .lastName("lastname")
                .addressInfo(AddressInfo.builder()
                        .id(generateUUID())
                        .apartmentNumber("1")
                        .postCode("20586")
                        .state("Christmas Land")
                        .street("Santa street 6")
                        .build())
                .contactInfo(ContactInfo.builder()
                        .id(generateUUID())
                        .privateEmail("email@priv.com")
                        .privatePhoneNumber("555-55555")
                        .workEmail("email@work.com")
                        .workPhoneNumber("666-66666")
                        .build())
                .paymentInfo(PaymentInfo.builder().id(generateUUID()).creditCardNumber("8468551325").ccv("785").build())
                .registrationInfo(RegistrationInfo.builder()
                        .id(generateUUID())
                        .isActive(true)
                        .registeredAt(Date.from(Instant.now()))
                        .build())
                .build();
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
