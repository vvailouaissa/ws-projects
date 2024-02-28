package com.ynov.wsproject1.service;

import com.ynov.wsproject1.DTO.PostCreateRequest;
import com.ynov.wsproject1.DTO.PostUpdateRequest;
import com.ynov.wsproject1.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Page<Post> getPosts(Pageable pageable);

    List<Post> getPostsByUser(String userId, Pageable pageable);

    List<Post> getPostsByTag(String tag, Pageable pageable);

    Post getPostById(String postId);

    Post createPost(PostCreateRequest postCreateRequest);

    Post updatePost(String postId, PostUpdateRequest postUpdateRequest);

    boolean deletePostById(String postId);
}
