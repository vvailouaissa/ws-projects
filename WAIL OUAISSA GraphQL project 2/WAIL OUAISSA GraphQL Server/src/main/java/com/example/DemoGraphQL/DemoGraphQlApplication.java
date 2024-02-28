package com.example.DemoGraphQL;

import com.example.DemoGraphQL.exception.GraphQLErrorAdapter;
import com.example.DemoGraphQL.model.User;
import com.example.DemoGraphQL.model.Post;
import com.example.DemoGraphQL.repository.UserRepository;
import com.example.DemoGraphQL.repository.PostRepository;
import com.example.DemoGraphQL.resolver.PostResolver;
import com.example.DemoGraphQL.resolver.Mutation;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
public class DemoGraphQlApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoGraphQlApplication.class, args);
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

	@Bean
	public PostResolver postResolver(UserRepository userRepository) {
		return new PostResolver();
	}



	@Bean
	public Mutation mutation(UserRepository userRepository, PostRepository postRepository) {
		return new Mutation(userRepository, postRepository);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, PostRepository postRepository) {
		return (args) -> {


		};
	}
}
