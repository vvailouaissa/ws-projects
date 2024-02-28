package com.ynov.wsproject1.DTO;

import lombok.Data;

@Data
public class CommentCreateRequest {

    private String ownerId;
    private String postId;
    private String message;
    private String publishDate;
}
