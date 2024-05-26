package se.dsve.book_auth.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.dsve.book_auth.dtos.RegisterUserDto;
import se.dsve.book_auth.model.User;
import se.dsve.book_auth.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User createUser(RegisterUserDto input) {
        User user = User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();
        return userRepository.save(user);
    }
}
