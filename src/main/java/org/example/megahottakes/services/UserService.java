package org.example.megahottakes.services;

import jakarta.transaction.Transactional;
import org.example.megahottakes.dto.UserDTO;
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
    private final UserRepository userRepository;
    private final HotTakeRepository hotTakeRepository;
    private final CommentRepository commentRepository;
    public UserService(UserRepository userRepository,  HotTakeRepository hotTakeRepository,  CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.hotTakeRepository = hotTakeRepository;
        this.commentRepository = commentRepository;
    }
    // Convert to DTO
    private UserDTO convertDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
    // Create Section
    @Transactional
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
    public String getBio(Long userId) {
        User user = getUserById(userId);
        return user.getBio();
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
    // Update
    @Transactional
    public User changeName(Long userId, String newName) {
        User user = getUserById(userId);
        user.setUserName(newName);
        return userRepository.save(user);
    }
    @Transactional
    public User changeBio(Long userId, String newBio) {
        User user = getUserById(userId);
        user.setBio(newBio);
        return userRepository.save(user);
    }
    // Delete
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("The User was not found");
        }
        userRepository.deleteById(userId);
    }
}
