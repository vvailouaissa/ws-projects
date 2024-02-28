package com.ynov.wsproject1.DTO;


import lombok.Data;

@Data
public class PostCreateRequest {

    private Long ownerId;
    private String text;
    private String image;
    private String link;
    private String[] tags;

}
