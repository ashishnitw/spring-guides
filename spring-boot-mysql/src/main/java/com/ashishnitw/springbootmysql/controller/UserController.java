package com.ashishnitw.springbootmysql.controller;

import com.ashishnitw.springbootmysql.dto.CreateUserRequest;
import com.ashishnitw.springbootmysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @PostMapping()
    ResponseEntity<Object> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
