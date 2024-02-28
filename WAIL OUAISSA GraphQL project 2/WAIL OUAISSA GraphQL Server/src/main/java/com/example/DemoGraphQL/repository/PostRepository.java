package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.Post;
import com.example.DemoGraphQL.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.name = :tag")
    Page<Post> findByTagName(@Param("tag") String tag, Pageable pageable);

    @Query("SELECT DISTINCT p.tags FROM Post p")
    List<String> findAllUniqueTags();

    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.id = :tagId")
    List<Post> findByTagId(@Param("tagId") Long tagId);
}
