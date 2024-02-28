package com.ynov.wsproject1.service;

import com.ynov.wsproject1.DTO.CommentCreateRequest;
import com.ynov.wsproject1.exception.CommentNotFoundException;
import com.ynov.wsproject1.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsSortedByCreationDate(int page, int size);

    List<Comment> getCommentsByPostIdSortedByCreationDate(String postId, int page, int size);

    List<Comment> getCommentsByUserIdSortedByCreationDate(String userId, int page, int size);

    Comment createComment(CommentCreateRequest commentCreateRequest);

    String deleteCommentById(String commentId) throws CommentNotFoundException;


}
