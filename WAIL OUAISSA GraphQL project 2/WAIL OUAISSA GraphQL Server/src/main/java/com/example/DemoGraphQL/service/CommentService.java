package com.example.DemoGraphQL.service;

import com.example.DemoGraphQL.model.Comment;
import com.example.DemoGraphQL.model.Post;
import com.example.DemoGraphQL.model.User;
import com.example.DemoGraphQL.repository.CommentRepository;
import com.example.DemoGraphQL.repository.PostRepository;
import com.example.DemoGraphQL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    public Page<Comment> getAllComments(int page, int limit) {
        return commentRepository.findAll(PageRequest.of(page, limit));
    }

    public Page<Comment> getCommentsByPost(Long postId, int page, int limit) {
        return commentRepository.findByPostId(postId, PageRequest.of(page, limit));
    }

    public Page<Comment> getCommentsByUser(Long userId, int page, int limit) {
        return commentRepository.findByUserId(userId, PageRequest.of(page, limit));
    }

    @Transactional
    public Comment addComment(String content, Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));

         User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Comment newComment = new Comment();
        newComment.setContent(content);
        newComment.setPost(post);

        newComment.setUser(user);

        return commentRepository.save(newComment);
    }

    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Comment> findAllComments(PageRequest of) {
        return commentRepository.findAll(of);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
