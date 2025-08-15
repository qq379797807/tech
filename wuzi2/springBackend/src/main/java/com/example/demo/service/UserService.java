package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public UserService() {
        // Initialize with some dummy data
        createUser(new User(null, "Alice", "alice@example.com"));
        createUser(new User(null, "Bob", "bob@example.com"));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User createUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return existingUser;
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}