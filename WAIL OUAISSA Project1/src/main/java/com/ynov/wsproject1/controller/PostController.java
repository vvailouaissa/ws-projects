package com.ynov.wsproject1.controller;

import com.ynov.wsproject1.DTO.PostCreateRequest;
import com.ynov.wsproject1.DTO.PostUpdateRequest;
import com.ynov.wsproject1.model.Post;
import com.ynov.wsproject1.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Page<Post> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(
            @PathVariable("userId") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("publishDate").descending());
        List<Post> posts = postService.getPostsByUser(userId, pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Post>> getPostsByTag(
            @PathVariable("tag") String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("publishDate").descending());
        List<Post> posts = postService.getPostsByTag(tag, pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") String postId) {
        Post post = postService.getPostById(postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        try {
            Post createdPost = postService.createPost(postCreateRequest);
            return ResponseEntity.ok(createdPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") String postId,
                                           @RequestBody PostUpdateRequest postUpdateRequest) {
        Post updatedPost = postService.updatePost(postId, postUpdateRequest);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        boolean deleted = postService.deletePostById(postId);
        if (deleted) {
            return new ResponseEntity<>("Post with ID " + postId + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post with ID " + postId + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
