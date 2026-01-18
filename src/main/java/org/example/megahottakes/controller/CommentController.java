package org.example.megahottakes.controller;


import org.example.megahottakes.entities.Comment;
import org.example.megahottakes.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Create
    @PostMapping("/hottake/{hotTakeId}/user/{userId}")
    public Comment createComment(@PathVariable Long hotTakeId, @PathVariable Long userId, @RequestBody Comment comment) {
        return commentService.addComment(hotTakeId, userId, comment.getContent());
    }

    // Read
    @GetMapping("/{id}/all")
    public Set<Comment> getAllComments(@PathVariable Long id) {
        return commentService.getCommentsByHotTake(id);
    }
    // Delete
    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
