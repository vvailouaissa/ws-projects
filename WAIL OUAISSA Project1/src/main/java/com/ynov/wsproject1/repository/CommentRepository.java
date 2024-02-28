package com.ynov.wsproject1.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ynov.wsproject1.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByPostIdOrderByPublishDate(String postId, PageRequest of);

    List<Comment> findByOwner_IdOrderByPublishDateDesc(String userId, PageRequest of);
}
