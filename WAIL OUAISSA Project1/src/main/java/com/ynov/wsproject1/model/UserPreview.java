package com.ynov.wsproject1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserPreview {

    @Id
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;
}
