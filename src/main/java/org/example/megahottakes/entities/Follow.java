package org.example.megahottakes.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower_id")
    @JsonIgnore
    private User follower;
    @ManyToOne
    @JoinColumn(name = "followed_id")
    @JsonIgnore
    private User followed;
}
