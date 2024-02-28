package com.ynov.wsproject1.service;

import com.ynov.wsproject1.DTO.PostCreateRequest;
import com.ynov.wsproject1.DTO.PostUpdateRequest;
import com.ynov.wsproject1.exception.ResourceNotFoundException;
import com.ynov.wsproject1.model.Post;
import com.ynov.wsproject1.model.UserPreview;
import com.ynov.wsproject1.repository.PostRepository;
import com.ynov.wsproject1.repository.UserPreviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserPreviewRepository userPreviewRepository;


    @Override
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAllByOrderByPublishDateDesc(pageable);
    }

    public List<Post> getPostsByUser(String userId, Pageable pageable) {
        return postRepository.findByOwnerId(userId, pageable);
    }

    public List<Post> getPostsByTag(String tag, Pageable pageable) {
        return postRepository.findByTagsContains(tag, pageable);
    }

    public Post getPostById(String postId) {
        return postRepository.findById(postId).orElse(null);
    }



    public Post createPost(PostCreateRequest postCreateRequest) {

        if (StringUtils.isEmpty(postCreateRequest.getOwnerId()) || StringUtils.isEmpty(postCreateRequest.getText()) || postCreateRequest.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner ID, text, and owner fields are required");
        }

         UserPreview owner = userPreviewRepository.findById(postCreateRequest.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

         Post post = new Post();
        post.setOwner(owner);
        post.setText(postCreateRequest.getText());
        post.setImage(postCreateRequest.getImage());
        post.setLink(postCreateRequest.getLink());
        post.setTags(postCreateRequest.getTags());

        return postRepository.save(post);
    }


    public Post updatePost(String postId, PostUpdateRequest postUpdateRequest) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        if (postUpdateRequest.getOwner() != null) {
            throw new IllegalArgumentException("Owner field cannot be updated");
        }

        existingPost.setText(postUpdateRequest.getText());
        existingPost.setImage(postUpdateRequest.getImage());
        existingPost.setLink(postUpdateRequest.getLink());
        existingPost.setTags(postUpdateRequest.getTags());

        return postRepository.save(existingPost);
    }

    public boolean deletePostById(String postId) {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
            return true;
        }
        return false;
    }
}
