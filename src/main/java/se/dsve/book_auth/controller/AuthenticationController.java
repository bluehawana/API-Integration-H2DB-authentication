package se.dsve.book_auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.dsve.book_auth.dtos.LoginUserDto;
import se.dsve.book_auth.dtos.RegisterUserDto;
import se.dsve.book_auth.model.LoginResponse;
import se.dsve.book_auth.model.User;
import se.dsve.book_auth.services.AuthenticationService;
import se.dsve.book_auth.services.JwtService;
import se.dsve.book_auth.model.AuthenticationRequest;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        // TODO: Write your code here
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        // TODO: Write your code here
        User user = authenticationService.authenticate(loginUserDto);
        String token = jwtService.generateToken(user);
        long expiresIn = jwtService.getExpirationTime();
        LoginResponse loginResponse = new LoginResponse(token, expiresIn);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody AuthenticationRequest request) {
        LoginUserDto loginUserDto = new LoginUserDto(request.getEmail(), request.getPassword());
        User user = authenticationService.authenticate(loginUserDto);
        String token = jwtService.generateToken(user);
        long expiresIn = jwtService.getExpirationTime();
        LoginResponse loginResponse = new LoginResponse(token, expiresIn);
        return ResponseEntity.ok(loginResponse);
    }
}