package org.example.megahottakes.repositories;


import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotTakeRepository extends JpaRepository<HotTake, Long> {
}
