package org.example.megahottakes.controller;

import org.example.megahottakes.entities.User;
import org.example.megahottakes.repositories.FollowRepository;
import org.example.megahottakes.repositories.UserRepository;
import org.example.megahottakes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // Create
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getUserName(), user.getBio());
    }
    // Read
    @GetMapping
    public List<User> findAll() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @GetMapping("/{name}")
    public User getUser(@PathVariable String name){
        return userService.getUserByName(name);
    }
}
