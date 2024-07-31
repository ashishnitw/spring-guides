package com.ashishnitw.springbootmongodb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateUserRequest {
    private String name;
    private String email;
    private List<String> roles;
    private String phoneNumber;
}
