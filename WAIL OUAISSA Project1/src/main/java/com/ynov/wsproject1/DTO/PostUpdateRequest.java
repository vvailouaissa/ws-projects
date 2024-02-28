package com.ynov.wsproject1.DTO;

import com.ynov.wsproject1.model.UserPreview;
import lombok.Data;

@Data
public class PostUpdateRequest {

    private String text;
    private String image;
    private String link;
    private String[] tags;
    private UserPreview owner;
}
