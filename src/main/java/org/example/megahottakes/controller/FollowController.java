package org.example.megahottakes.controller;

import org.example.megahottakes.entities.Follow;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.repositories.UserRepository;
import org.example.megahottakes.services.FollowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;
    private final UserRepository userRepository;

    public FollowController(FollowService followService, UserRepository userRepository) {
        this.followService = followService;
        this.userRepository = userRepository;
    }
    // Create
    @PostMapping("/{followerId}/{followedId}")
    public void follow(@PathVariable Long followerId, @PathVariable Long followedId){
        User follower = userRepository.findById(followerId).orElseThrow(() -> new IllegalArgumentException("Follower ID not found"));
        User followed = userRepository.findById(followedId).orElseThrow(() -> new IllegalArgumentException("Followed ID not found"));
        followService.follow(follower, followed);
    }
    @DeleteMapping("/{followerId}/{followedId}")
    public void unfollow(@PathVariable Long followerId, @PathVariable Long followedId){
        User follower = userRepository.findById(followerId).orElseThrow(() -> new IllegalArgumentException("Follower ID not found"));
        User followed = userRepository.findById(followedId).orElseThrow(() -> new IllegalArgumentException("Followed ID not found"));
        followService.unfollow(follower, followed);
    }
    @GetMapping("/isFollowing/{followerId}/{followedId}")
    public boolean isFollowing(@PathVariable Long followerId, @PathVariable Long followedId){
        User follower = userRepository.findById(followerId).orElseThrow(() -> new IllegalArgumentException("Follower ID not found"));
        User followed = userRepository.findById(followedId).orElseThrow(() -> new IllegalArgumentException("Followed ID not found"));
        return followService.isFollowing(follower, followed);
    }
    @GetMapping("/followcount/{id}")
    public int getFollowCount(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return followService.getFollowCount(user);
    }
    @GetMapping("/followingcount/{id}")
    public int getFollowingCount(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return followService.getFollowingCount(user);
    }
}
