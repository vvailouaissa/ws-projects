package com.example.DemoGraphQL.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String firstName;
    private String lastName;
    private String picture;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public User() {

    }
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
