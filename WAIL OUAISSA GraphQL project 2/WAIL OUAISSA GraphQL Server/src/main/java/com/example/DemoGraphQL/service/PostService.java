package com.example.DemoGraphQL.service;

import com.example.DemoGraphQL.model.Post;
import com.example.DemoGraphQL.model.PostPage;
import com.example.DemoGraphQL.model.User;
import com.example.DemoGraphQL.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private UserService userService;
    public PostPage getPosts(int page, int limit, String sortBy) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy));
        Page<Post> postPage = postRepository.findAll(pageable);
        return toPostPage(postPage);
    }


    public PostPage findPostsByTag(String tag, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Post> postPage = postRepository.findByTagName(tag, pageable);
        return toPostPage(postPage);
    }

    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findPostsByTagId(Long tagId) {

        return postRepository.findByTagId(tagId);
    }
    public PostPage toPostPage(Page<Post> page) {
        PostPage postPage = new PostPage();
        postPage.setData(page.getContent());
        postPage.setPage(page.getNumber());
        postPage.setTotal(page.getSize());
        postPage.setTotalElements(page.getTotalElements());
        postPage.setTotalPages(page.getTotalPages());
        return postPage;
    }
}
