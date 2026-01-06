package org.example.megahottakes.services;

import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.entities.Comment;
import org.example.megahottakes.repositories.CommentRepository;
import org.example.megahottakes.repositories.HotTakeRepository;
import org.example.megahottakes.repositories.UserRepository;
import org.springframework.stereotype.Service;


// Layout:
// CRUD (Create Post and save to DB)
// Add comment

@Service
public class HotTakeService {
    private HotTakeRepository hotTakeRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    public HotTakeService(HotTakeRepository hotTakeRepository,  UserRepository userRepository, CommentRepository commentRepository) {
        this.hotTakeRepository = hotTakeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public HotTake createHotTake(Long id, String contentOfHotTake) {
        User authorOfHotTake =  userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The User was not Found")); // grab id of User
        if (contentOfHotTake == null || contentOfHotTake.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        HotTake hotTake = new HotTake();
        hotTake.setContent(contentOfHotTake);
        hotTake.setAuthor(authorOfHotTake);
        return hotTakeRepository.save(hotTake);
    }
    public Comment addComment(Long hotTakeId, Long authorId, String contentOfComment) {
        Comment newComment = new Comment();
        HotTake hotTakeObject = hotTakeRepository.findById(hotTakeId)
                .orElseThrow(() -> new IllegalArgumentException("The HotTake Post was not Found"));
        User authorObject = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("The User was not Found"));
        newComment.setAuthor(authorObject);
        newComment.setHotTake(hotTakeObject);
        newComment.setContent(contentOfComment);
        return commentRepository.save(newComment);
    }
}
