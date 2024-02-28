package com.ynov.wsproject1.service;

import com.ynov.wsproject1.DTO.CommentCreateRequest;
import com.ynov.wsproject1.exception.CommentNotFoundException;
import com.ynov.wsproject1.model.Post;
import com.ynov.wsproject1.model.UserFull;
import com.ynov.wsproject1.model.UserPreview;
import com.ynov.wsproject1.repository.PostRepository;
import com.ynov.wsproject1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import com.ynov.wsproject1.model.Comment;
import com.ynov.wsproject1.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    @Override
    public List<Comment> getCommentsSortedByCreationDate(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public List<Comment> getCommentsByPostIdSortedByCreationDate(String postId, int page, int size) {
        return commentRepository.findByPostIdOrderByPublishDate(postId, PageRequest.of(page, size));
    }

    @Override
    public List<Comment> getCommentsByUserIdSortedByCreationDate(String userId, int page, int size) {
         return commentRepository.findByOwner_IdOrderByPublishDateDesc(userId, PageRequest.of(page, size));
    }

    @Override
    public Comment createComment(CommentCreateRequest commentCreateRequest) {
         if (commentCreateRequest.getOwnerId() == null || commentCreateRequest.getPostId() == null) {
            throw new IllegalArgumentException("Owner and post fields are required");
        }

         UserFull owner = userRepository.findById(commentCreateRequest.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        Post post = postRepository.findById(commentCreateRequest.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = new Comment();
        comment.setId(UUID.randomUUID().toString());
        comment.setOwner(owner);
        comment.setPostId(post.getId());
        comment.setMessage(commentCreateRequest.getMessage());
        comment.setPublishDate(commentCreateRequest.getPublishDate());
        return commentRepository.save(comment);
    }


    @Override
    public String deleteCommentById(String commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with ID: " + commentId));

        commentRepository.delete(comment);
        return commentId;
    }
}
