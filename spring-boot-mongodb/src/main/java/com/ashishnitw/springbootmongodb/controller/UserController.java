package com.ashishnitw.springbootmongodb.controller;

import com.ashishnitw.springbootmongodb.dto.CreateUserRequest;
import com.ashishnitw.springbootmongodb.model.User;
import com.ashishnitw.springbootmongodb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserRequest request) {
        User user = userService.createCustomer(request);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getUser() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchUsers(@RequestParam String name) {
        List<User> users = userService.searchUsers(name, null);
        return ResponseEntity.ok().body(users);
    }
}
