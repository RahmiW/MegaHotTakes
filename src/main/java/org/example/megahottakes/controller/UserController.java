package org.example.megahottakes.controller;

import org.example.megahottakes.entities.Comment;
import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.repositories.FollowRepository;
import org.example.megahottakes.repositories.UserRepository;
import org.example.megahottakes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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
    @GetMapping("/{id}/bio")
    public String getUserBioById(@PathVariable Long id) {
        return userService.getBio(id);
    }
    @GetMapping("/by-name/{name}")
    public User getByUserName(@PathVariable String name){return userService.getUserByName(name);
    }
    @GetMapping("/{id}/hottakes")
    public List<HotTake> getHotTakesByUser(@PathVariable Long id){
        return userService.getHotTakesByUserId(id);
    }
    @GetMapping("/{id}/comments")
    public List<Comment>  getCommentsByUser(@PathVariable Long id){
        return userService.getCommentsByUserId(id);
    }
    // Update Section will include: changeName, changeBio
    @PutMapping("/{id}/username")
    public User updateUserName(@PathVariable Long id, @RequestBody User user){
        return userService.changeName(id, user.getUserName());
    }
    @PutMapping("/{id}/bio")
    public User updateUserBio(@PathVariable Long id, @RequestBody User user){
        return userService.changeBio(id, user.getBio());
    }
    // Delete Section
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
