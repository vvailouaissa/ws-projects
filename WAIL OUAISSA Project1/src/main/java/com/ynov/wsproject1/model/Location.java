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
public class Location {

    @Id
    private String id;

    private String street;
    private String city;
    private String state;
    private String country;
    private String timezone;
}
