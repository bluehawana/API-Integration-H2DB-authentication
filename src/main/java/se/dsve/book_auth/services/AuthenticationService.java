package se.dsve.book_auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.dsve.book_auth.dtos.LoginUserDto;
import se.dsve.book_auth.dtos.RegisterUserDto;
import se.dsve.book_auth.model.LoginResponse;
import se.dsve.book_auth.model.User;
import se.dsve.book_auth.repository.UserRepository;
import se.dsve.book_auth.services.JwtService;

import java.util.NoSuchElementException;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;



    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
          
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
       }

    public User signup(RegisterUserDto input) {
        User user = User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .fullName(input.getFullName())
                .build();
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        User user = (User) userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        String token = jwtService.generateToken(user);
        long expiresIn = jwtService.getExpirationTime();

        return user;  }
}