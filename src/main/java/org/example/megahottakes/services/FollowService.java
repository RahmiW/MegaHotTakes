package org.example.megahottakes.services;

import jakarta.transaction.Transactional;
import org.example.megahottakes.entities.Follow;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.repositories.FollowRepository;
import org.example.megahottakes.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FollowService {
    private final UserRepository userRepository;
    private FollowRepository followRepository;
    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }
    // follow logic
    @Transactional
    public void follow(Long followerId, Long followedId){
        if (followerId.equals(followedId)) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }
        // boolean for checking if users already follow each other
        User follower = userRepository.findById(followerId)
            .orElseThrow(() -> new IllegalArgumentException("Follower was not found"));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new IllegalArgumentException("Followed user was not found"));
        if (followRepository.existsByFollowerAndFollowed(follower, followed)) {
            throw new IllegalArgumentException("You are already following the user");
        }
        Follow newFollow = new Follow();
        newFollow.setFollower(follower);
        newFollow.setFollowed(followed);
        followRepository.save(newFollow);
    }
    // unfollow logic
    public void unfollow(User follower, User followed){
        Follow followRelationship = followRepository.findByFollowerAndFollowed(follower, followed)
                .orElseThrow(() -> new IllegalArgumentException("This relationship was not found"));
        followRepository.delete(followRelationship);
    }
    // isFollowing checker
    public boolean isFollowing(User follower, User followed){
        return  followRepository.existsByFollowerAndFollowed(follower, followed);
    }
    // Methods to get list of followers and following
    public int getFollowCount(User user){
        return  followRepository.countByFollowed(user);
    }
    public int getFollowingCount(User user){
        return  followRepository.countByFollower(user);
    }
}
