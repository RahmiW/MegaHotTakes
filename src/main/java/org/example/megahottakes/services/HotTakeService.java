package org.example.megahottakes.services;

import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.entities.Comment;
import org.example.megahottakes.repositories.CommentRepository;
import org.example.megahottakes.repositories.HotTakeRepository;
import org.example.megahottakes.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


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
    // Create

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
    // Read
    public HotTake getHotTake(Long hotTakeId){
        return hotTakeRepository.findById(hotTakeId)
                .orElseThrow(() -> new IllegalArgumentException("The HotTake was not found"));
    }
    public List<HotTake> getHotTakeFeed(){
        return hotTakeRepository.findAllByOrderByLikedByUsersDesc();
    }
    public Set<HotTake> getHotTakesByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not Found"));
        return user.getHotTakes();
    }
    //
    public Set<Comment> getCommentsByHotTake(Long hotTakeId){
        HotTake hotTake = hotTakeRepository.findById(hotTakeId).orElseThrow(() -> new IllegalArgumentException("The HotTake was not found"));
        return hotTake.getComments();
    }
    public List<HotTake> searchHotTakes(String keyword){
        return hotTakeRepository.findByContentContainingIgnoreCase(keyword);
    }
    // Update
    public HotTake updateHotTake(Long hotTakeId, String newContent){
        HotTake hotTake = hotTakeRepository.findById(hotTakeId).orElseThrow(() -> new IllegalArgumentException("The HotTake was not found"));
        hotTake.setContent(newContent);
        return hotTakeRepository.save(hotTake);
    }
    // Delete
    public void deleteHotTake(Long hotTakeId){
        hotTakeRepository.deleteById(hotTakeId);
    }
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
