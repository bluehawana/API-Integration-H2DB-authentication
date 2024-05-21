package se.dsve.book_auth.controller;

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
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        // TODO: Write your code here
        return null;
    }
}
