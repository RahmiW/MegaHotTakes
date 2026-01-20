package org.example.megahottakes.services;

import jakarta.transaction.Transactional;
import org.example.megahottakes.entities.Comment;
import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.repositories.CommentRepository;
import org.example.megahottakes.repositories.HotTakeRepository;
import org.example.megahottakes.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CommentService {
    private final HotTakeRepository hotTakeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    public CommentService(HotTakeRepository hotTakeRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.hotTakeRepository = hotTakeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }
    @Transactional
    public Comment addComment(Long hotTakeId, Long authorId, String contentOfComment) {
        Comment newComment = new Comment();
        HotTake hotTakeObject = hotTakeRepository.findById(hotTakeId)
                .orElseThrow(() -> new IllegalArgumentException("The HotTake Post was not Found"));
        User authorObject = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("The User was not Found"));
        newComment.setAuthor(authorObject);
        newComment.setHotTake(hotTakeObject);
        newComment.setContent(contentOfComment);
        newComment.setCreatedDate(LocalDateTime.now());
        return commentRepository.save(newComment);
    }
    public Set<Comment> getCommentsByHotTake(Long hotTakeId){
        HotTake hotTake = hotTakeRepository.findById(hotTakeId).orElseThrow(() -> new IllegalArgumentException("The HotTake was not found"));
        return hotTake.getComments();
    }
    @Transactional
    public void deleteComment(Long commentId){
        if (!commentRepository.existsById(commentId)){
            throw new IllegalArgumentException("The Comment was not Found");
        }
        commentRepository.deleteById(commentId);
    }
}
