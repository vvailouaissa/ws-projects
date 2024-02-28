package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.*;
import com.example.DemoGraphQL.model.Post;
import com.example.DemoGraphQL.model.User;
import com.example.DemoGraphQL.repository.PostRepository;
import com.example.DemoGraphQL.repository.UserRepository;
import com.example.DemoGraphQL.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PostResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PostRepository postRepository;





    public PostPage posts(Integer page, Integer limit, String sortBy) {
        Page<Post> postPage = postRepository.findAll(
                PageRequest.of(page != null ? page : 0, limit != null ? limit : 10,
                        Sort.by(sortBy != null ? sortBy : "creationDate")));

        return new PostPage(postPage.getContent(), (int) postPage.getTotalElements(),
                postPage.getNumber(), postPage.getSize());
    }
}
