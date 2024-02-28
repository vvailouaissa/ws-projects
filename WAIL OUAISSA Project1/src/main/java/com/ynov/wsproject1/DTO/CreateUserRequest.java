package com.ynov.wsproject1.DTO;

import com.ynov.wsproject1.model.Location;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
public class CreateUserRequest {

    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String registerDate;
    private String phone;
    private String picture;
}