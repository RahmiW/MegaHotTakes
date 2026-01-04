package org.example.megahottakes.repositories;


import org.example.megahottakes.entities.Follow;
import org.example.megahottakes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
