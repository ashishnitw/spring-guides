package com.ashishnitw.springbootmysql.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    String name;
    String email;
}
