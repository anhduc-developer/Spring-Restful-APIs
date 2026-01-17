package com.example.duck.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.duck.Entity.User;
import com.example.duck.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> handleGetUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    public User handleGetUser(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    public User handleCreateUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        return this.userRepository.save(newUser);
    }

    public void handleUpdateUser(Long id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User newUser = optionalUser.get();
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            this.userRepository.save(newUser);
        }
    }

    public void handleDeleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
