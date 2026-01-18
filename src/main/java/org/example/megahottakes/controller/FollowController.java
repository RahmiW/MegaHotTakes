package org.example.megahottakes.controller;

import org.example.megahottakes.entities.Follow;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.repositories.UserRepository;
import org.example.megahottakes.services.FollowService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;

    public FollowController(FollowService followService, UserRepository userRepository) {
        this.followService = followService;
    }
    // Create
    @PostMapping("/{followerId}/{followedId}")
    public void follow(@PathVariable Long followerId, @PathVariable Long followedId){
        followService.follow(followerId, followedId);
    }
    @DeleteMapping("/{followerId}/{followedId}")
    public void unfollow(@PathVariable Long followerId, @PathVariable Long followedId){
        followService.unfollow(follower, followed);
    }
    @GetMapping("/isFollowing/{followerId}/{followedId}")
    public boolean isFollowing(@PathVariable Long followerId, @PathVariable Long followedId){
        return followService.isFollowing(follower, followed);
    }
    @GetMapping("/followcount/{id}")
    public int getFollowCount(@PathVariable Long id){
        return followService.getFollowCount(user);
    }
    @GetMapping("/followingcount/{id}")
    public int getFollowingCount(@PathVariable Long id){
        return followService.getFollowingCount(user);
    }
}
