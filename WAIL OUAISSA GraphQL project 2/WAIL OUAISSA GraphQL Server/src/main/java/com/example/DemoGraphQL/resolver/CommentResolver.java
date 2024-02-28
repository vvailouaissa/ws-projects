package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.Comment;
import com.example.DemoGraphQL.model.CommentPage;
import com.example.DemoGraphQL.service.CommentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class CommentResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CommentService commentService;

    public CommentPage comments(int page, int limit) {
        Page<Comment> commentPage = commentService.getAllComments(page, limit);
        List<Comment> comments = commentPage.getContent();
        return new CommentPage(comments, (int) commentPage.getTotalElements(), commentPage.getNumber(), commentPage.getSize());
    }

    public CommentPage commentsByPost(Long postId, int page, int limit) {
        Page<Comment> commentPage = commentService.getCommentsByPost(postId, page, limit);
        List<Comment> comments = commentPage.getContent();
        return new CommentPage(comments, (int) commentPage.getTotalElements(), commentPage.getNumber(), commentPage.getSize());
    }

    public CommentPage commentsByUser(Long userId, int page, int limit) {
        Page<Comment> commentPage = commentService.getCommentsByUser(userId, page, limit);
        List<Comment> comments = commentPage.getContent();
        return new CommentPage(comments, (int) commentPage.getTotalElements(), commentPage.getNumber(), commentPage.getSize());
    }

    public Comment createComment(String content, Long postId, Long userId) {
        return commentService.addComment(content, postId, userId);
    }

    public boolean deleteComment(Long id) {
        return commentService.deleteComment(id);
    }

    public String getCreationDate(Comment comment) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(comment.getCreationDate());
    }
}
