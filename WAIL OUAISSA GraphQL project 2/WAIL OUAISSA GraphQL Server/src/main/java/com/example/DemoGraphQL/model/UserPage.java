package com.example.DemoGraphQL.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPage {
    private List<User> data;
    private int total;
    private int page;
    private int limit;



}
