package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.exception.PostNotFoundException;
import com.example.DemoGraphQL.model.User;
import com.example.DemoGraphQL.model.Post;
import com.example.DemoGraphQL.repository.UserRepository;
import com.example.DemoGraphQL.repository.PostRepository;
import com.example.DemoGraphQL.repository.PostRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;

import java.util.Optional;

public class Mutation implements GraphQLMutationResolver {
    private final PostRepository PostRepository;
    private final UserRepository UserRepository;

    public Mutation(UserRepository UserRepository, PostRepository PostRepository) {
        this.UserRepository = UserRepository;
        this.PostRepository = PostRepository;
    }

    public User newUser(String name) {
        User User = new User();
        User.setFirstName(name);

        UserRepository.save(User);

        return User;
    }

    public Post newPost(String content,Long UserId) {
        Post Post = new Post();
        Post.setUser(new User());
        Post.setContent(content);
        PostRepository.save(Post);

        return Post;
    }

    public boolean deletePost(Long id) {
        PostRepository.deleteById(id);
        return true;
    }
    public Post updatePost(Long id, String content) {
        return PostRepository.findById(id)
                .map(post -> {
                    post.setContent(content);
                    return PostRepository.save(post);
                }).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

}
