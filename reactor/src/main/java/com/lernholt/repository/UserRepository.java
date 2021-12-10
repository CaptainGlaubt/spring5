package com.lernholt.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.lernholt.domain.user.User;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

}
