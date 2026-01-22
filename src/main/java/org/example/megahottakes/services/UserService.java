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
import java.util.stream.Collectors;

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
    public UserDTO createUser(String name, String bioContent){
        User user = new User();
        user.setUserName(name);
        user.setBio(bioContent);
        return convertDTO(userRepository.save(user));
    }
    // Read Section
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertDTO)
                .toList();
    }
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not found"));
        return convertDTO(user);
    }
    public String getBio(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not found"));
        return user.getBio();
    }
    public UserDTO getUserByName(String userName) {
        User user = userRepository.findByUserName(userName);
        return convertDTO(user);
    }
    public List<HotTake> getHotTakesByUserId(Long userId) {
        return hotTakeRepository.findByAuthorId(userId);
    }
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByAuthorId(userId);
    }
    // Update
    @Transactional
    public UserDTO changeName(Long userId, String newName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not found"));
        user.setUserName(newName);
        return convertDTO(userRepository.save(user));
    }
    @Transactional
    public UserDTO changeBio(Long userId, String newBio) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not found"));
        user.setBio(newBio);
        return convertDTO(userRepository.save(user));
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
