package org.example.megahottakes.repositories;


import org.example.megahottakes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
