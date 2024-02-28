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
public class Comment {

    @Id
    private String id;
    private String message;

    @ManyToOne
    private UserFull owner;
    private String postId;
    private String publishDate;
}
