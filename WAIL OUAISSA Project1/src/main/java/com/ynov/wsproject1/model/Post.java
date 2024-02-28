package com.ynov.wsproject1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String text;
    private String image;
    private int likes;
    private String link;
    private String[] tags;
    private String publishDate;

    @ManyToOne
    private UserPreview owner;
}
