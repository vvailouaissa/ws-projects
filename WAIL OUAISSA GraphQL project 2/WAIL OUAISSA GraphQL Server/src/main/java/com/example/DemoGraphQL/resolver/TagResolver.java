package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.Post;
import com.example.DemoGraphQL.model.Tag;
import com.example.DemoGraphQL.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagResolver implements GraphQLResolver<Tag> {
    private final PostService postService;

    @Autowired
    public TagResolver(PostService postService) {
        this.postService = postService;
    }

    public List<Post> getPosts(Tag tag) {
        return postService.findPostsByTagId(Long.valueOf(tag.getId()));
    }
}
