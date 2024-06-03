package se.dsve.book_auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.dsve.book_auth.dtos.LoginUserDto;
import se.dsve.book_auth.dtos.RegisterUserDto;
import se.dsve.book_auth.model.User;
import se.dsve.book_auth.repository.UserRepository;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        // TODO: Write your code here
        User user = new User();
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    public Optional<Object> authenticate(LoginUserDto input) {
        // TODO: Write your code here
        try {
            org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(), input.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return userRepository.findByEmail(input.getEmail());
            }
        } catch (org.springframework.security.core.AuthenticationException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.empty();
    }
}