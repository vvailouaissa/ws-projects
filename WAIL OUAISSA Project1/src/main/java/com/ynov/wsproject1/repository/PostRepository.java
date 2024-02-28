package com.ynov.wsproject1.repository;

import com.ynov.wsproject1.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    Page<Post> findAllByOrderByPublishDateDesc(Pageable pageable);

    List<Post> findByOwnerId(String userId, Pageable pageable);

    List<Post> findByTagsContains(String tag, Pageable pageable);

}
