package com.example.basicapplication.controller;

import com.example.basicapplication.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public String addUser(@RequestBody User user) {
        users.add(user);
        return "User added successfully!";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        users.removeIf(user -> user.getId().equals(id));
        updatedUser.setId(id);
        users.add(updatedUser);
        return "User updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
        return "User deleted successfully!";
    }
}

