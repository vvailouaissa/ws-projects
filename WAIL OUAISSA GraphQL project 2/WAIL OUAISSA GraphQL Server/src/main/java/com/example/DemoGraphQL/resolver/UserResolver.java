package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.*;
import com.example.DemoGraphQL.repository.CommentRepository;
import com.example.DemoGraphQL.repository.PostRepository;
import com.example.DemoGraphQL.repository.UserRepository;
import com.example.DemoGraphQL.service.CommentService;
import com.example.DemoGraphQL.service.PostService;
import com.example.DemoGraphQL.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final UserService userService;
    private final PostRepository postRepository;
    private final CommentService commentService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    public Post createPost(String content, Long userId) {
        if (content == null) {
            throw new IllegalArgumentException("Post content cannot be null");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Post newPost = new Post();
        newPost.setContent(content);
        newPost.setUser(user);
        newPost.setCreationDate(new Date());


        return postRepository.save(newPost);
    }


    public Post getCreatePost(String content, Long userId) {
        return createPost(content, userId);
    }

    public List<String> tags() {
        return postRepository.findAllUniqueTags();
    }

    public List<String> getTags() {
        return tags();
    }




    public User user(Long id) {
        return userService.getUserById(id);
    }

    public User createUser(String firstName, String lastName, String email) {
        return userService.createUser(firstName, lastName, email);
    }

    public User updateUser(Long id, String firstName, String lastName) {
        return userService.updateUser(id, firstName, lastName);
    }

    public String deleteUser(Long id) {
        return userService.deleteUser(id);
    }








    public PostPage postsByTag(String tag, int page, int limit) {
        return postService.findPostsByTag(tag, page, limit);
    }

    public PostPage getPostsByTag(String tag, int page, int limit) {
        return postsByTag(tag, page, limit);
    }
    public Post post(Long id) {
        Optional<Post> postOptional = postService.findPostById(id);
        return postOptional.orElse(null);
    }
    public Post getPost(Long id) {
        return post(id); }


    public PostPage postsByUser(Long userId, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Post> postPage = postRepository.findByUserId(userId, pageable);
        return postService.toPostPage(postPage);
    }


    public UserPage users(int page, int limit, String sortBy) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy));
        Page<User> userPage = userRepository.findAll(pageable);

        List<User> users = userPage.getContent();
        long total = userPage.getTotalElements();

        return new UserPage(users, (int) total, page, limit);
    }

    public String id(Long id) {
      return "";
    }

}
