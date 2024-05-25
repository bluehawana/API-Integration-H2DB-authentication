package se.dsve.book_auth.services;

import org.springframework.stereotype.Service;
import se.dsve.book_auth.dtos.RegisterUserDto;
import se.dsve.book_auth.model.User;
import se.dsve.book_auth.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        // TODO: Write your code here
        return (List<User>) userRepository.findAll();
    }

    public User createUser(RegisterUserDto input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        return userRepository.save(user);

    }
}
