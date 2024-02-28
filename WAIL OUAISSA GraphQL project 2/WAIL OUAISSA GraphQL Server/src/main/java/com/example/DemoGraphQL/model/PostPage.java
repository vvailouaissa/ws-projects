package com.example.DemoGraphQL.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostPage {
    private List<Post> data;
    private int page;
    private int total;
    private int limit;

    private long totalElements;
    private int totalPages;

    public PostPage(List<Post> content,
                    int totalElements,
                    int number,
                    int size) {

        this.data=content;
        this.totalElements=totalElements;
        this.total=number;
        this.limit=size;

    }




}
