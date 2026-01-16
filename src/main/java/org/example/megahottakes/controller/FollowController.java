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
        User follower = userRepository.findById(followerId).get();
        User followed = userRepository.findById(followedId).get();
        followService.follow(follower, followed);
    }
}
