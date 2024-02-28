package com.ynov.wsproject1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PostPreview {

    @Id
    private String id;
    private String text;
    private String image;
    private int likes;
    private String[] tags;
    private String publishDate;

    @ManyToOne
    private UserPreview owner;
}
