package com.example.DemoGraphQL.model;

import lombok.Data;

import java.util.List;


@Data
public class UserList {
        private List<UserPreview> data;
        private int total;
        private int page;
        private int limit;}