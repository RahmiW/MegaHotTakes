package org.example.megahottakes.services;

import org.example.megahottakes.entities.Comment;
import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.repositories.CommentRepository;
import org.example.megahottakes.repositories.HotTakeRepository;
import org.example.megahottakes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Needed Methods:
// Get hotTakes with userId
// Get commments with userId
// Get likedHotTakes

@Service
public class UserService {
    private UserRepository userRepository;
    private HotTakeRepository hotTakeRepository;
    private CommentRepository commentRepository;
    public UserService(UserRepository userRepository,  HotTakeRepository hotTakeRepository,  CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.hotTakeRepository = hotTakeRepository;
        this.commentRepository = commentRepository;
    }
    // Create Section
    public User createUser(String name, String bioContent){
        User user = new User();
        user.setUserName(name);
        user.setBio(bioContent);
        return userRepository.save(user);
    }
    // Read Section
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not found"));
    }
    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }
    public List<HotTake> getHotTakesByUserId(Long userId) {
        return hotTakeRepository.findByAuthorId(userId);
    }
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByAuthorId(userId);
    }
}
