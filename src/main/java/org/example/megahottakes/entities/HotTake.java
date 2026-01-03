package org.example.megahottakes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class HotTake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // Time the HotTake post was created
    // Remember to also include in comment entity
    private LocalDateTime creationDate =  LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "hottake", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(mappedBy = "likedHotTakes")
    private Set<User> likedByUsers = new HashSet<>();

    public int getHeatScore(){
        return likedByUsers.size();
    }
}
