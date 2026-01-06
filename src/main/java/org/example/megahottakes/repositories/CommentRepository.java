package org.example.megahottakes.repositories;


import org.example.megahottakes.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
