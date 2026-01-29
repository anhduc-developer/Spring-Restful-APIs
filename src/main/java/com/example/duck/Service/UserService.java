package com.example.duck.Service;

import java.util.List;
import java.util.Optional;

import com.example.duck.Entity.User;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    public User updateUser(Long id, User updateUser);

    public void deleteUser(Long id);
}
