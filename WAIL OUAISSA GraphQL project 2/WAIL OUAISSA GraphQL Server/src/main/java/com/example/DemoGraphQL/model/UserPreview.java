package com.example.DemoGraphQL.model;


import lombok.Data;

@Data
public class UserPreview {
    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;

    public UserPreview(User user) {
        this.id = user.getId();
        this.title = user.getTitle();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.picture = user.getPicture();
    }

}
