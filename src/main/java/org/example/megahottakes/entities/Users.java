package org.example.megahottakes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String bio;

    @OneToMany(mappedBy = "author")
    private Set<HotTake> hotTakes = new HashSet<>();
    // will include all the hot takes per user and adds uniqueness to each hot take
    @OneToMany(mappedBy = "author")
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_liked_hottakes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hottake_id")
    )
    private Set<HotTake> likedHotTakes = new HashSet<>();
    // the liked hot takes will be stored here
    @OneToMany(mappedBy = "follower")
    private Set<Follow> followingRelations = new HashSet<>();

    @OneToMany(mappedBy = "followed")
    private Set<Follow> followerRelations = new HashSet<>();
}
