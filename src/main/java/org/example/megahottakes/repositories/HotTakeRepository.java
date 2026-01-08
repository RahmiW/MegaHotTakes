package org.example.megahottakes.repositories;


import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotTakeRepository extends JpaRepository<HotTake, Long> {
    List<HotTake> findAllByOrderByLikedByUsersDesc();
    List<HotTake> findByContentContainingIgnoreCase(String keyword);
    List<HotTake> findByAuthorId(Long userId);
}
