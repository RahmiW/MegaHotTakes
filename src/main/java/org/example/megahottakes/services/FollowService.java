package org.example.megahottakes.services;

import org.example.megahottakes.entities.Follow;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.repositories.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FollowService {
    private FollowRepository followRepository;
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }
    public void follow(User follower, User followed){
        if (follower.getId().equals(followed.getId())) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }
        // boolean for checking if users already follow each other
        boolean alreadyFollowing = followRepository.existsByFollowerAndFollowed(follower, followed);
        if (alreadyFollowing) {
            throw new IllegalArgumentException("Cannot follow yourself");
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
}
