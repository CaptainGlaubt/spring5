package se.lernholt.security;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import se.lernholt.repository.jpa.UserRepository;
import se.lernholt.tacos.User;

@Service
@RequiredArgsConstructor
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.nonNull(user)) {
            return user;
        }
        String errorMessage = String.format("User '%s' not found.", username);
        throw new UsernameNotFoundException(errorMessage);
    }
}
