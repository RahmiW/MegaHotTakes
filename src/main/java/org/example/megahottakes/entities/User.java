package org.example.megahottakes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Table(name = "app_user")
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String bio;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<HotTake> hotTakes = new HashSet<>();
    // will include all the hot takes per user and adds uniqueness to each hot take
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "user_liked_hottakes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hottake_id")
    )
    private Set<HotTake> likedHotTakes = new HashSet<>();
    // the liked hot takes will be stored here
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Follow> followingRelations = new HashSet<>();

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Follow> followerRelations = new HashSet<>();

    public void addHotTake(HotTake take) {
        hotTakes.add(take);
        take.setAuthor(this);
    }
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setAuthor(this);
    }
    public void likeHotTake(HotTake take) {
        likedHotTakes.add(take);
        take.getLikedByUsers().add(this);
    }
    public void follow(User targetUser) {
        Follow follow = new Follow();
        follow.setFollower(this);
        follow.setFollowed(targetUser);

        followingRelations.add(follow);
        targetUser.getFollowerRelations().add(follow);
    }
}
