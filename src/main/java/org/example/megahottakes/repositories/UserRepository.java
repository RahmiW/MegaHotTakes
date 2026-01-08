package org.example.megahottakes.repositories;


import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
