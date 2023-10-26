package com.ashishnitw.springbootmongodb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private List<String> roles;
}
