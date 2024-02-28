package com.ynov.wsproject1.controller;

import com.ynov.wsproject1.DTO.CommentCreateRequest;
import com.ynov.wsproject1.exception.CommentNotFoundException;
import com.ynov.wsproject1.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ynov.wsproject1.model.Comment;
import com.ynov.wsproject1.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    private CommentRepository commentRepository;


    @GetMapping("/comments")
    public List<Comment> getCommentsSortedByCreationDate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return commentService.getCommentsSortedByCreationDate(page, size);
    }

    @GetMapping
    public List<Comment> getCommentsByPostIdSortedByCreationDate(
            @RequestParam String postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return commentService.getCommentsByPostIdSortedByCreationDate(postId, page, size);
    }
    @GetMapping("/user")
    public List<Comment> getCommentsByUserIdSortedByCreationDate(
            @RequestParam String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return commentService.getCommentsByUserIdSortedByCreationDate(userId, page, size);
    }


    @PostMapping("/comment")
    public ResponseEntity<Comment> createComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        Comment createdComment = commentService.createComment(commentCreateRequest);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable String commentId) {
        try {
            String deletedCommentId = commentService.deleteCommentById(commentId);
            return new ResponseEntity<>(deletedCommentId, HttpStatus.OK);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
